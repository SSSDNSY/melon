package fun.sssdnsy.activiti.config;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class ActivitiConfig implements ProcessEngineConfigurationConfigurer {

    @Resource
    private ICustomProcessDiagramGenerator customProcessDiagramGenerator;

    /**
     * 解決工作流生成图片乱码问题
     *
     * @param processEngineConfiguration
     */
    @Override
    public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {
        processEngineConfiguration.setActivityFontName("宋体");
        processEngineConfiguration.setAnnotationFontName("宋体");
        processEngineConfiguration.setLabelFontName("宋体");
        processEngineConfiguration.setProcessDiagramGenerator(customProcessDiagramGenerator);

        /**
         * 自定义id
         */
        processEngineConfiguration.setIdGenerator(() -> IdWorker.getIdStr());
    }
}
