package com.gemepro;

import com.gemepro.common.httpClient.EnableHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan
@SpringBootApplication
@EnableTransactionManagement
@EnableHttpClient
@EnableAsync
public class DataskyBackApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DataskyBackApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DataskyBackApplication.class);
    }

}
