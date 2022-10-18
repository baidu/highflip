package com.baidu.highflip.console.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Data
@Configuration
@PropertySource("classpath:git.properties")
public class GitVersionConfig {

    @Value("${git.branch}")
    String branch;

    @Value("${git.build.time}")
    String buildTime;

    @Value("${git.build.version}")
    String buildVersion;

    @Value("${git.commit.id.full}")
    String commitId;

}
