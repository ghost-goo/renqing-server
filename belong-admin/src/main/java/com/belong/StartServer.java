package com.belong;

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
 * @author belong
 */
@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class StartServer implements ApplicationListener<WebServerInitializedEvent> {

    public static final String CHANGE_LINE = System.getProperty("line.separator");

    public static void main(String[] args) {

        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(StartServer.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  belong模版启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "88888888ba             88                                      \n" +
                "88      \"8b           88                                      \n" +
                "88      ,8P            88                                      \n" +
                "88aaaaaa8P'  ,adPPYba, 88  ,adPPYba,  8b,dPPYba,   ,adPPYb,d8  \n" +
                "88\"\"\"\"\"\"8b, a8P_____88 88 a8\"     \"8a 88P'   `\"8a a8\"    `Y88  \n" +
                "88      `8b 8PP\"\"\"\"\"\"\" 88 8b       d8 88       88 8b       88  \n" +
                "88      a8P \"8b,   ,aa 88 \"8a,   ,a8\" 88       88 \"8a,   ,d88  \n" +
                "88888888P\"   `\"Ybbd8\"' 88  `\"YbbdP\"'  88       88  `\"YbbdP\"Y8  \n" +
                "                                                   aa,    ,88  \n" +
                "                                                    \"Y8bbdP\"   \n");
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
