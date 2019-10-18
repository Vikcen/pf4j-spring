package org.pf4j.demo.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApp {
    public static void main(String[] args) {
//        System.setProperty("pf4j.mode", "development");
        SpringApplication.run(MainApp.class, args);
    }
}
