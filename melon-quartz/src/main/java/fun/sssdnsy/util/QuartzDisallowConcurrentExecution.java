package fun.sssdnsy.util;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import fun.sssdnsy.domain.SysJob;

/**
 * 定时任务处理（禁止并发执行）
 *
 * @author sssdnsy
 *
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception
    {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
