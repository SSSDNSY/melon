package fun.sssdnsy.listener;

import fun.sssdnsy.domain.Leaveapply;
import fun.sssdnsy.mapper.LeaveapplyMapper;
import fun.sssdnsy.utils.spring.SpringUtils;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.util.List;

public class DataImport implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        LeaveapplyMapper leaveapplyMapper = SpringUtils.getBean(LeaveapplyMapper.class);
        List<Leaveapply> applys           = leaveapplyMapper.selectLeaveapplyList(new Leaveapply());
        for (Leaveapply apply : applys) {
            System.out.println("请假信息" + apply.toString());
        }
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
