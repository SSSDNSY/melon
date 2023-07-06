package fun.sssdnsy.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import fun.sssdnsy.client.util.PropUtil;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.domain.XxlConfNode;
import fun.sssdnsy.domain.XxlConfNodeLog;
import fun.sssdnsy.domain.XxlConfNodeMsg;
import fun.sssdnsy.domain.XxlConfProject;
import fun.sssdnsy.mapper.*;
import fun.sssdnsy.service.IXxlConfNodeService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 配置
 *
 * @author xuxueli 2016-08-15 22:53
 */
@Service
public class XxlConfNodeServiceImpl implements IXxlConfNodeService, InitializingBean, DisposableBean {

    private static Logger logger = LoggerFactory.getLogger(XxlConfNodeServiceImpl.class);

    @Resource
    private XxlConfNodeDao xxlConfNodeDao;

    @Resource
    private XxlConfProjectDao xxlConfProjectDao;

    @Resource
    private XxlConfNodeLogDao xxlConfNodeLogDao;

    @Resource
    private XxlConfEnvDao envDao;

    @Resource
    private XxlConfProjectDao projectDao;

    @Resource
    private XxlConfNodeMsgDao xxlConfNodeMsgDao;

    @Value("${melon.switch}")
    private Boolean confSwitch = false;

    @Value("${melon.conf.file-path}")
    private String dataFilePath;

    @Value("${melon.conf.beat-time}")
    private          int                               beatTime              = 30;
    private          ExecutorService                   executorService       = Executors.newCachedThreadPool();
    private volatile boolean                           executorStoped        = false;
    private volatile List<Integer>                     readedMessageIds      = Collections.synchronizedList(new ArrayList());
    private          Map<String, List<DeferredResult>> confDeferredResultMap = new ConcurrentHashMap<>();

    @Override
    public List<XxlConfNode> list(XxlConfNode confNode) {
        return xxlConfNodeDao.list(confNode);
    }

    @Override
    public Map<String, List> listEnvApp() {
        Map<String, List> map = new HashMap<>(2);
        map.put("env", envDao.findAll());
        map.put("app", projectDao.findAll());
        return map;
    }

    @Override
    public int delete(XxlConfNode confNode) {

        String env = confNode.getEnv();
        String key = confNode.getKey();
        int deleteNum = xxlConfNodeDao.delete(env, key);
        xxlConfNodeLogDao.deleteTimeout(env, key, 0);
        // conf msg
        sendConfMsg(env, key, null);
        return deleteNum;
    }


    /**
     * conf broadcast msg
     */
    private void sendConfMsg(String env, String key, String value) {

        XxlConfNodeMsg confNodeMsg = new XxlConfNodeMsg();
        confNodeMsg.setEnv(env);
        confNodeMsg.setKey(key);
        confNodeMsg.setValue(value);

        xxlConfNodeMsgDao.add(confNodeMsg);
    }

    @Override
    public int add(XxlConfNode xxlConfNode) {
        int addNum = -1;
        // valid
        if (StringUtils.isBlank(xxlConfNode.getAppname())) {
            return addNum;
        }

        // valid group
        XxlConfProject group = xxlConfProjectDao.load(xxlConfNode.getAppname());
        if (group == null) {
            return addNum;
        }

        // value force null to ""
        if (xxlConfNode.getValue() == null) {
            xxlConfNode.setValue("");
        }

        // add node
        addNum = xxlConfNodeDao.insert(xxlConfNode);

        // node log
        XxlConfNodeLog nodeLog = new XxlConfNodeLog();
        nodeLog.setEnv(xxlConfNode.getEnv());
        nodeLog.setKey(xxlConfNode.getKey());
        nodeLog.setTitle(xxlConfNode.getTitle() + "(配置新增)");
        nodeLog.setValue(xxlConfNode.getValue());
        //TODO
        xxlConfNodeLogDao.add(nodeLog);

        // conf msg
        sendConfMsg(xxlConfNode.getEnv(), xxlConfNode.getKey(), xxlConfNode.getValue());

        return addNum;
    }

    @Override
    public int update(XxlConfNode xxlConfNode) {

        XxlConfNode existNode = xxlConfNodeDao.load(xxlConfNode.getEnv(), xxlConfNode.getKey());

        // value force null to ""
        if (xxlConfNode.getValue() == null) {
            xxlConfNode.setValue("");
        }
        existNode.setTitle(xxlConfNode.getTitle());
        existNode.setValue(xxlConfNode.getValue());
        int ret = xxlConfNodeDao.update(existNode);

        // node log
        XxlConfNodeLog nodeLog = new XxlConfNodeLog();
        nodeLog.setEnv(existNode.getEnv());
        nodeLog.setKey(existNode.getKey());
        nodeLog.setTitle(existNode.getTitle() + "(配置更新)");
        nodeLog.setValue(existNode.getValue());
        //TODO
//        nodeLog.setOptuser(loginUser.getUsername());
        xxlConfNodeLogDao.add(nodeLog);
        xxlConfNodeLogDao.deleteTimeout(existNode.getEnv(), existNode.getKey(), 10);

        // conf msg
        sendConfMsg(xxlConfNode.getEnv(), xxlConfNode.getKey(), xxlConfNode.getValue());

        return ret;
    }

    @Override
    public XxlConfNode get(XxlConfNode confNode) {
        return xxlConfNodeDao.load(confNode.getEnv(), confNode.getKey());
    }

    @Override
    public boolean exist(XxlConfNode confNode) {
        return get(confNode) != null;
    }

    @Override
    public int delete(List<XxlConfNode> confNodeLis) {
        int sum = 0;
        for (XxlConfNode confNode : confNodeLis) {
            sum += delete(confNode);
        }
        return sum;
    }

    @Override
    public Map<String, String> find(JSONObject jsonObject) {

        JSONArray keys = jsonObject.getJSONArray("keys");
        String env = jsonObject.getString("env");
        Assert.isTrue(CollectionUtils.isNotEmpty(keys) && StringUtils.isNotBlank(env), "入参keys、env不能为空！");

        // result
        Map<String, String> result = new HashMap<String, String>();
        for (Object key : keys) {


            if (key == null || StringUtils.isBlank(key.toString())) {
                continue;
            }

            String value = getConfDataFile(env, key.toString());

            result.put(key.toString(), value);
        }

        return result;
    }

    @Override
    public DeferredResult<AjaxResult> monitor(JSONObject jsonObject) {

        DeferredResult deferredResult = new DeferredResult(beatTime * 1000L, AjaxResult.success("Monitor timeout, no key updated."));

        JSONArray keys = jsonObject.getJSONArray("keys");
        String env = jsonObject.getString("env");
        Assert.isTrue(CollectionUtils.isNotEmpty(keys) && StringUtils.isNotBlank(env), "入参keys、env不能为空！");

        // result
        Map<String, String> result = new HashMap<String, String>();
        for (Object key : keys) {
            if (key == null || StringUtils.isBlank(key.toString())) {
                deferredResult.setResult(AjaxResult.error("keys Invalid."));
                return deferredResult;
            }

            // monitor each key
            String fileName = parseConfDataFileName(env, key.toString());

            List<DeferredResult> deferredResultList = confDeferredResultMap.get(fileName);
            if (deferredResultList == null) {
                deferredResultList = new ArrayList<>();
                confDeferredResultMap.put(fileName, deferredResultList);
            }
            deferredResultList.add(deferredResult);

        }

        return deferredResult;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        startThead();
    }

    @Override
    public void destroy() throws Exception {
        stopThread();
    }

    public void startThead() throws Exception {
        if (!confSwitch) {
            return;
        }

        /**
         * deal  msg, sync  file, for "add、update、delete" operation
         */
        executorService.execute(() -> {
            while (!executorStoped) {
                try {
                    // new message, filter read
                    List<XxlConfNodeMsg> messageList = xxlConfNodeMsgDao.findMsg(readedMessageIds);
                    if (messageList != null && messageList.size() > 0) {
                        for (XxlConfNodeMsg message : messageList) {
                            readedMessageIds.add(message.getId());
                            // sync file
                            setConfDataFile(message.getEnv(), message.getKey(), message.getValue());
                        }
                    }

                    // clean old message
                    if ((System.currentTimeMillis() / 1000) % beatTime == 0) {
                        xxlConfNodeMsgDao.cleanMessage(beatTime);
                        readedMessageIds.clear();
                    }
                } catch (Exception e) {
                    if (!executorStoped) {
                        logger.error(e.getMessage(), e);
                    }
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    if (!executorStoped) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        });


        /**
         *  sync total conf-data, db + file      (1+N/30s)
         *  clean deleted conf-data file
         */
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (!executorStoped) {

                    // align to beattime
                    try {
                        long sleepSecond = beatTime - (System.currentTimeMillis() / 1000) % beatTime;
                        if (sleepSecond > 0 && sleepSecond < beatTime) {
                            TimeUnit.SECONDS.sleep(sleepSecond);
                        }
                    } catch (Exception e) {
                        if (!executorStoped) {
                            logger.error(e.getMessage(), e);
                        }
                    }

                    try {
                        // sync registry-data, db + file
                        List<String> confDataFileList = new ArrayList<>();
                        List<XxlConfNode> confNodeList = xxlConfNodeDao.list(null);
                        if (confNodeList != null && confNodeList.size() > 0) {
                            for (XxlConfNode confNoteItem : confNodeList) {
                                // sync file
                                String confDataFile = setConfDataFile(confNoteItem.getEnv(), confNoteItem.getKey(), confNoteItem.getValue());
                                // collect confDataFile
                                confDataFileList.add(confDataFile);
                            }
                        }
                        // clean old registry-data file
                        cleanConfDataFile(confDataFileList);
                        logger.debug(">>>>>>>>>>> conf, sync total conf data success, sync conf count = {}", confDataFileList.size());
                    } catch (Exception e) {
                        if (!executorStoped) {
                            logger.error(e.getMessage(), e);
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(beatTime);
                    } catch (Exception e) {
                        if (!executorStoped) {
                            logger.error(e.getMessage(), e);
                        }
                    }
                }
            }
        });
    }

    private void stopThread() {
        if (!confSwitch) {
            return;
        }
        executorStoped = true;
        executorService.shutdownNow();
    }


    // get
    public String getConfDataFile(String env, String key) {

        // fileName
        String confFileName = parseConfDataFileName(env, key);

        // read
        Properties existProp = PropUtil.loadFileProp(confFileName);
        if (existProp != null && existProp.containsKey("value")) {
            String value = existProp.getProperty("value");
            return StringUtils.isBlank(value) ? StringUtils.EMPTY : value;
        }
        return StringUtils.EMPTY;
    }

    private String parseConfDataFileName(String env, String key) {
        // fileName
        String fileName = dataFilePath
                .concat(File.separator).concat(env)
                .concat(File.separator).concat(key)
                .concat(".properties");
        return fileName;
    }

    // set
    private String setConfDataFile(String env, String key, String value) {

        // fileName
        String confFileName = parseConfDataFileName(env, key);

        // valid repeat update
        Properties existProp = PropUtil.loadFileProp(confFileName);
        if (existProp != null
                && value != null
                && value.equals(existProp.getProperty("value"))
        ) {
            return new File(confFileName).getPath();
        }

        // write
        Properties prop = new Properties();
        if (value == null) {
            prop.setProperty("value-deleted", "true");
        } else {
            prop.setProperty("value", value);
        }

        PropUtil.writeFileProp(prop, confFileName);
        logger.info(">>>>>>>>>>> conf, setFileConfData: confFileName={}, value={}", confFileName, value);

        // broadcast monitor client
        List<DeferredResult> deferredResultList = confDeferredResultMap.get(confFileName);
        if (deferredResultList != null) {
            confDeferredResultMap.remove(confFileName);
            for (DeferredResult deferredResult : deferredResultList) {
                deferredResult.setResult("Monitor key update.");
            }
        }

        return new File(confFileName).getPath();
    }

    /**
     * clean conf data file
     */
    public void cleanConfDataFile(List<String> confDataFileList) {
        filterChildPath(new File(dataFilePath), confDataFileList);
    }


    public void filterChildPath(File parentPath, final List<String> confDataFileList) {
        if (!parentPath.exists() || parentPath.list() == null || parentPath.list().length == 0) {
            return;
        }
        File[] childFileList = parentPath.listFiles();
        for (File childFile : childFileList) {
            if (childFile.isFile() && !confDataFileList.contains(childFile.getPath())) {
                childFile.delete();
                logger.info(">>>>>>>>>>> conf, cleanFileConfData, ConfDataFile={}", childFile.getPath());
            }
            if (childFile.isDirectory()) {
                if (parentPath.listFiles() != null && parentPath.listFiles().length > 0) {
                    filterChildPath(childFile, confDataFileList);
                } else {
                    childFile.delete();
                }
            }
        }
    }

}
