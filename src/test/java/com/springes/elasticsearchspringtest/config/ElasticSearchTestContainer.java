//package com.springes.elasticsearchspringtest.config;
//
//import com.springes.elasticsearchspringtest.user.repository.UserSearchQueryRepository;
//import com.springes.elasticsearchspringtest.user.repository.UserSearchRepository;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//import org.testcontainers.elasticsearch.ElasticsearchContainer;
//import org.testcontainers.utility.DockerImageName;
//
//// Nori 분석기를 사용하지 않고 ES 컨테이너만 사용시 사용
//
//@TestConfiguration
//@EnableElasticsearchRepositories(basePackageClasses = {
//    UserSearchRepository.class,
//    UserSearchQueryRepository.class
//})
//public class ElasticSearchTestContainer extends AbstractElasticsearchConfig {
//
//  private static final String ELASTICSEARCH_VERSION = "7.15.2";
//  private static final DockerImageName ELASTICSEARCH_IMAGE =
//      DockerImageName
//          .parse("docker.elastic.co/elasticsearch/elasticsearch")
//          .withTag(ELASTICSEARCH_VERSION);
//
//  private static final ElasticsearchContainer container;
//
//  static {
//    container = new ElasticsearchContainer(ELASTICSEARCH_IMAGE);
//    container.start();
//  }
//
//  @Override
//  public RestHighLevelClient elasticsearchClient() {
//    ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//        .connectedTo(container.getHttpHostAddress())
//        .build();
//
//    return RestClients.create(clientConfiguration).rest();
//  }
//}
