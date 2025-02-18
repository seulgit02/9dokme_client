package com.example.server_9dokme;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Server9dokmeApplication {

    public static void main(String[] args) {
        SpringApplication.run(Server9dokmeApplication.class, args);
    }

}
