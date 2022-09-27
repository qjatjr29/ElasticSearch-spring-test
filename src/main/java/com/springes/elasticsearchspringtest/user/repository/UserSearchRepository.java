package com.springes.elasticsearchspringtest.user.repository;

import com.springes.elasticsearchspringtest.user.entity.UserDocument;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserSearchRepository extends ElasticsearchRepository<UserDocument, Long> {

  List<UserDocument> findByAge(int age);

  Page<UserDocument> findByNickname(String nickname, Pageable pageable);

}
