package com.springes.elasticsearchspringtest.config;

import com.springes.elasticsearchspringtest.user.repository.UserSearchQueryRepository;
import com.springes.elasticsearchspringtest.user.repository.UserSearchRepository;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;

// Nori 분석시 사용하려고 할 시 ES container에 Nori 분석기도 설치
@TestConfiguration
@EnableElasticsearchRepositories(basePackageClasses = {
    UserSearchRepository.class,
    UserSearchQueryRepository.class
})
public class ElasticSearchTestContainerWithNori extends AbstractElasticsearchConfig {

  private static final GenericContainer genericContainer;

  static {
    genericContainer = new GenericContainer(
        new ImageFromDockerfile()
            .withDockerfileFromBuilder(dockerfileBuilder -> {
              dockerfileBuilder
                  .from("docker.elastic.co/elasticsearch/elasticsearch:7.15.2") // es 이미지 가져오기
                  .run("bin/elasticsearch-plugin install analysis-nori") // nori 분석기 설치
                  .build();
            })
    )
        .withExposedPorts(9200, 9300)  // 기본 포트 설정
        .withEnv("discovery.type", "single-node"); // ES 가 싱글노드로 돌아가도록 설정

    genericContainer.start();
  }


  @Override
  public RestHighLevelClient elasticsearchClient() {

    // ES container에서 제공해주던 httpHostAddress를 사용할 수 없어 직접 만들어줘야 한다.
    String hostAddress = new StringBuilder()
        .append(genericContainer.getHost())
        .append(":")
        .append(genericContainer.getMappedPort(9200))
        .toString();

    ClientConfiguration clientConfiguration = ClientConfiguration.builder()
        .connectedTo(hostAddress)
        .build();

    return RestClients.create(clientConfiguration).rest();
  }
}
