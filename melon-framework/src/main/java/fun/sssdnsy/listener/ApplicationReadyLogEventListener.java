package fun.sssdnsy.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.info.GitProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @author fun.pengzh
 * @class fun.sssdnsy.listener.ApplicationReadyLogEventListener
 * @desc 启动事件打印
 * @since 2023-01-18
 */

@Component
public class ApplicationReadyLogEventListener implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationReadyLogEventListener.class);

    @Autowired
    private GitProperties git;

    public void onApplicationEvent(ApplicationReadyEvent event) {

        Environment env = event.getApplicationContext().getEnvironment();
        String applicationName = env.getProperty("spring.application.name", "UNKNOWN");
        String port = env.getProperty("server.port", "8080");
        String path = env.getProperty("server.servlet.context-path", "");
        String activeProfiles = String.join(",", Arrays.asList(env.getActiveProfiles()));
        LOG.info("=========================  Application Ready =========================");
        LOG.info("\tApplicationName : {}", applicationName);
        LOG.info("\tActiveProfiles : {}", activeProfiles);
        if (git != null) {
            LOG.info("\tBuild-Version : {}", git.get("build.version"));
            LOG.info("\tBuild-Time : {}", git.get("build.time"));
            LOG.info("\tGit-Remote : {}", git.get("remote.origin.url"));
            LOG.info("\tGit-Branch : {}", git.getBranch());
            LOG.info("\tGit-CommitId : {}", git.getCommitId());
            LOG.info("\tGit-CommitTime : {}", git.get("commit.time"));
        }

        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            LOG.info("\tInfoEndpoint : http://{}:{}{}/actuator/info", new Object[]{ip, port, path});
            LOG.info("\tEnvEndpoint : http://{}:{}{}/actuator/env", new Object[]{ip, port, path});
            LOG.info("\tHealthEndpoint : http://{}:{}{}/actuator/health", new Object[]{ip, port, path});
        } catch (UnknownHostException e) {
            LOG.warn("UnknownHostException: {}", e.getMessage());
        }

        LOG.info("======================================================================");

    }
}
