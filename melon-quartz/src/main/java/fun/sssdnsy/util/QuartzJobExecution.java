package fun.sssdnsy.util;

import org.quartz.JobExecutionContext;
import fun.sssdnsy.domain.SysJob;

/**
 * 定时任务处理（允许并发执行）
 *
 * @author sssdnsy
 *
 */
public class QuartzJobExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception
    {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
