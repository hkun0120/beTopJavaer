package com.clic.thinkinginspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages={"com.soap"})
public class ThinkingInSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThinkingInSpringbootApplication.class, args);
    }

}
