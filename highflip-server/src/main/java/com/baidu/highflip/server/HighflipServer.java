package com.baidu.highflip.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Slf4j
@SpringBootApplication
@EnableJpaAuditing
@EntityScan(basePackages = {"com.baidu.highflip"})
public class HighflipServer{
    public static void main( String[] args ){
        SpringApplication app = new SpringApplication(HighflipServer.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
