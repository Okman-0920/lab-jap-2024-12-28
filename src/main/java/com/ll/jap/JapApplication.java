package com.ll.jap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JapApplication {

    public static void main(String[] args) {
        SpringApplication.run(JapApplication.class, args);
    }

}
