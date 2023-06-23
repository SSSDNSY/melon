package fun.sssdnsy.service.impl;

import fun.sssdnsy.core.text.Convert;
import fun.sssdnsy.domain.Meeting;
import fun.sssdnsy.mapper.MeetingMapper;
import fun.sssdnsy.mapper.PurchaseMapper;
import fun.sssdnsy.service.IMeetingService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 会议Service业务层处理
 *
 * @author shenzhanwang
 * @date 2022-05-30
 */
@Service
@Transactional
public class MeetingServiceImpl implements IMeetingService {
    @Resource
    IdentityService identityService;
    @Resource
    HistoryService historyService;
    @Resource
    private MeetingMapper meetingMapper;
    @Resource
    private PurchaseMapper purchaseMapper;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;

    /**
     * 查询会议
     *
     * @param id 会议主键
     * @return 会议
     */
    @Override
    public Meeting selectMeetingById(Long id) {
        return meetingMapper.selectMeetingById(id);
    }

    /**
     * 查询会议列表
     *
     * @param meeting 会议
     * @return 会议
     */
    @Override
    public List<Meeting> selectMeetingList(Meeting meeting) {
        return meetingMapper.selectMeetingList(meeting);
    }

    /**
     * 新增会议
     *
     * @param meeting 会议
     * @return 结果
     */
    @Override
    public int insertMeeting(Meeting meeting) {
        int row = meetingMapper.insertMeeting(meeting);
        // 启动会议流程
        identityService.setAuthenticatedUserId(meeting.getHost());
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("host", meeting.getHost());
        String[] person = meeting.getPeoplelist().split(",");
        variables.put("people", Arrays.asList(person));
        runtimeService.startProcessInstanceByKey("meeting", String.valueOf(meeting.getId()), variables);
        return row;
    }

    /**
     * 修改会议
     *
     * @param meeting 会议
     * @return 结果
     */
    @Override
    public int updateMeeting(Meeting meeting) {
        return meetingMapper.updateMeeting(meeting);
    }

    /**
     * 批量删除会议
     *
     * @param ids 需要删除的会议主键
     * @return 结果
     */
    @Override
    public int deleteMeetingByIds(String ids) {
        String[] keys = Convert.toStrArray(ids);
        for (String key : keys) {
            ProcessInstance process = runtimeService.createProcessInstanceQuery().processDefinitionKey("meeting").processInstanceBusinessKey(key).singleResult();
            if (process != null) {
                runtimeService.deleteProcessInstance(process.getId(), "删除");
            }
            // 删除历史数据
            HistoricProcessInstance history = historyService.createHistoricProcessInstanceQuery().processDefinitionKey("meeting").processInstanceBusinessKey(key).singleResult();
            if (history != null) {
                historyService.deleteHistoricProcessInstance(history.getId());
            }
            meetingMapper.deleteMeetingByIds(Convert.toStrArray(ids));
        }
        return keys.length;
    }

    /**
     * 删除会议信息
     *
     * @param id 会议主键
     * @return 结果
     */
    @Override
    public int deleteMeetingById(Long id) {
        return meetingMapper.deleteMeetingById(id);
    }
}
