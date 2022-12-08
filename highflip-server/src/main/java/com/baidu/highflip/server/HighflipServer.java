package com.baidu.highflip.server;

import io.grpc.Server;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.context.GRpcServerInitializedEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

@Slf4j
@SpringBootApplication
@EnableJpaAuditing
@EntityScan(basePackages = {"com.baidu.highflip"})
public class HighflipServer {
    public static void main(String[] args) {
        new SpringApplication(HighflipServer.class)
                .run(args);
    }

    @EventListener
    public void handleReturnedEvent(GRpcServerInitializedEvent event) {
        log.info("HighFlip Server started.");

        Environment env = event.getApplicationContext().getEnvironment();
        log.info("Database located at {}", env.getProperty("spring.datasource.url"));

        Server server = event.getServer();
        for (SocketAddress socks : server.getListenSockets()) {
            InetSocketAddress address = (InetSocketAddress) socks;
            log.info("Listening on grpc://{}:{}",
                    address.getAddress().getHostAddress(),
                    address.getPort());
        }
    }
}
