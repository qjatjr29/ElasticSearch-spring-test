package com.springes.elasticsearchspringtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ElasticSearchSpringTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(ElasticSearchSpringTestApplication.class, args);
  }

}
