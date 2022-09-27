package com.springes.elasticsearchspringtest.config;

import org.apache.http.HttpHost;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ElasticsearchProperties {

  @Value("${elasticsearch.host}")
  private String host;

  @Value("${elasticsearch.port}")
  private Integer port;

  public HttpHost httpHost() {
    return new HttpHost(host, port, "http");
  }

}
