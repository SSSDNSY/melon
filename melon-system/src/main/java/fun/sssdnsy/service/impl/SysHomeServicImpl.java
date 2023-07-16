package fun.sssdnsy.service.impl;

import fun.sssdnsy.domain.SysHome;
import fun.sssdnsy.mapper.SysNoticeMapper;
import fun.sssdnsy.mapper.SysOperLogMapper;
import fun.sssdnsy.mapper.SysUserMapper;
import fun.sssdnsy.service.ISysHomeService;
import org.activiti.engine.RepositoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author pengzh
 * @desc 首页服务
 * @since 2023-07-16
 */
@Service
public class SysHomeServicImpl implements ISysHomeService {

    @Resource
    SysUserMapper userMapper;
    @Resource
    SysOperLogMapper operLogMapper;
    @Resource
    SysNoticeMapper noticeMapper;

    @Resource
    private RepositoryService repositoryService;

    @Override
    public SysHome list() {
        SysHome sysHomeInfo = new SysHome();
        sysHomeInfo.setUserNumber(operLogMapper.selectVisitorNumber());
        sysHomeInfo.setMessageNumber(noticeMapper.selectNoticeCount());
        sysHomeInfo.setFlowNumber(repositoryService.createProcessDefinitionQuery().list().size());
        sysHomeInfo.setTaskNumber(111);
        return sysHomeInfo;
    }
}
