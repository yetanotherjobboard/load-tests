package com.yajb.loadtest.advertgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdvertGeneratorMain {

  public static void main(String... args) {
    SpringApplication
        .run(AdvertGeneratorMain.class, args)
        .getBean(AdvertGenerator.class)
        .run();
  }



}
