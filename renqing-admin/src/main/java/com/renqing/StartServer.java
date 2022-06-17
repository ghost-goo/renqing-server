package com.renqing;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;

import java.io.File;
import java.net.InetAddress;

/**
 * 启动程序
 *
 * @author renqing
 */
@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class StartServer implements ApplicationListener<WebServerInitializedEvent> {

    public static final String CHANGE_LINE = System.getProperty("line.separator");

    public static void main(String[] args) {

        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(StartServer.class, args);
        System.out.println(" ██████████ ██                        ██                         ██     ██                     ██   ██  ██  ██   ██             ██                          \n" +
                "░░░░░██░░░ ░██                 █████ ░██                        ░██    ░░                     ░██  ░░  ░██ ░██  ░░             ░██                          \n" +
                "    ░██    ░██       █████    ██░░░██░██       ██████   ██████ ██████   ██  ██████    ██████ ██████ ██ ░██ ░██   ██ ███████    ░██  ██████  ██    ██  █████ \n" +
                "    ░██    ░██████  ██░░░██  ░██  ░██░██████  ██░░░░██ ██░░░░ ░░░██░   ░██ ██░░░░    ██░░░░ ░░░██░ ░██ ░██ ░██  ░██░░██░░░██   ░██ ██░░░░██░██   ░██ ██░░░██\n" +
                "    ░██    ░██░░░██░███████  ░░██████░██░░░██░██   ░██░░█████   ░██    ░██░░█████   ░░█████   ░██  ░██ ░██ ░██  ░██ ░██  ░██   ░██░██   ░██░░██ ░██ ░███████\n" +
                "    ░██    ░██  ░██░██░░░░    ░░░░░██░██  ░██░██   ░██ ░░░░░██  ░██    ░██ ░░░░░██   ░░░░░██  ░██  ░██ ░██ ░██  ░██ ░██  ░██   ░██░██   ░██ ░░████  ░██░░░░ \n" +
                "    ░██    ░██  ░██░░██████    █████ ░██  ░██░░██████  ██████   ░░██   ░██ ██████    ██████   ░░██ ░██ ███ ███  ░██ ███  ░██   ███░░██████   ░░██   ░░██████\n" +
                "    ░░     ░░   ░░  ░░░░░░    ░░░░░  ░░   ░░  ░░░░░░  ░░░░░░     ░░    ░░ ░░░░░░    ░░░░░░     ░░  ░░ ░░░ ░░░   ░░ ░░░   ░░   ░░░  ░░░░░░     ░░     ░░░░░░ \n");
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {

        WebServer server = event.getWebServer();
        WebServerApplicationContext context = event.getApplicationContext();
        Environment env = context.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        int port = server.getPort();
        String contextPath = env.getProperty("server.servlet.context-path");
        if (contextPath == null) {
            contextPath = "";
        }
        log.info(File.separator + "---------------------------------------------------------"
                + CHANGE_LINE +
                "\tStartServer is running! Access address:"
                + CHANGE_LINE +
                "\tLocal:\t\thttp://localhost:{}{}" +
                CHANGE_LINE +
                "\tExternal:\thttp://{}:{}{}" +
                CHANGE_LINE + "---------------------------------------------------------" +
                CHANGE_LINE, port, contextPath, ip, port, contextPath);

    }
}
