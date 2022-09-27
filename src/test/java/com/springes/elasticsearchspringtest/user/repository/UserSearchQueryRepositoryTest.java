package com.springes.elasticsearchspringtest.user.repository;


import static org.assertj.core.api.Assertions.assertThat;

//import com.springes.elasticsearchspringtest.config.ElasticSearchTestContainer;
import com.springes.elasticsearchspringtest.config.ElasticSearchTestContainerWithNori;
import com.springes.elasticsearchspringtest.user.entity.User;
import com.springes.elasticsearchspringtest.user.entity.UserDocument;
import com.springes.elasticsearchspringtest.user.util.UserConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(ElasticSearchTestContainerWithNori.class)
@SpringBootTest(classes = UserSearchRepository.class)
public class UserSearchQueryRepositoryTest {

  @Autowired
  UserSearchRepository userSearchRepository;

  @Test
  void test() {
    // Given
    UserDocument userDocument = UserConverter.from(
        User.builder()
            .id(1L)
            .name("beomseok")
            .nickname("beomsic")
            .age(26)
            .build());

    // When
    userSearchRepository.save(userDocument);

    // Then
    UserDocument result = userSearchRepository.findById(1L)
        .orElseThrow(RuntimeException::new);

    assertThat(result.getId()).isEqualTo(userDocument.getId());
  }
}
