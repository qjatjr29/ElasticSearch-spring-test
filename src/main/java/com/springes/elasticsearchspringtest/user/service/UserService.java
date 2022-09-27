package com.springes.elasticsearchspringtest.user.service;

import com.springes.elasticsearchspringtest.user.dto.SaveAllUserRequest;
import com.springes.elasticsearchspringtest.user.dto.SaveUserRequest;
import com.springes.elasticsearchspringtest.user.dto.SearchCondition;
import com.springes.elasticsearchspringtest.user.dto.UserResponse;
import com.springes.elasticsearchspringtest.user.entity.User;
import com.springes.elasticsearchspringtest.user.entity.UserDocument;
import com.springes.elasticsearchspringtest.user.repository.UserRepository;
import com.springes.elasticsearchspringtest.user.repository.UserSearchQueryRepository;
import com.springes.elasticsearchspringtest.user.repository.UserSearchRepository;
import com.springes.elasticsearchspringtest.user.util.UserConverter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;
  private final UserSearchRepository userSearchRepository;
  private final UserSearchQueryRepository userSearchQueryRepository;

  public UserService(
      UserRepository userRepository,
      UserSearchRepository userSearchRepository,
      UserSearchQueryRepository userSearchQueryRepository) {
    this.userRepository = userRepository;
    this.userSearchRepository = userSearchRepository;
    this.userSearchQueryRepository = userSearchQueryRepository;
  }

  @Transactional
  public void saveAllUser(SaveAllUserRequest userRequest) {
    List<SaveUserRequest> userRequestList = userRequest.getUserRequestList();
    System.out.println(userRequestList.size());

    List<User> userList = userRequest.getUserRequestList()
        .stream()
        .map(UserConverter::to)
        .collect(Collectors.toList());

    userRepository.saveAll(userList);
    System.out.println("finish");
  }

  @Transactional
  public void saveAllUserDocuments() {
    List<UserDocument> userDocumentList = userRepository
        .findAll()
        .stream()
        .map(UserConverter::from)
        .collect(Collectors.toList());

    System.out.println("done1");

    userSearchRepository.saveAll(userDocumentList);
  }

  public Page<UserResponse> findAllByNickname(String nickname, Pageable pageable) {
    return userSearchRepository.findByNickname(nickname, pageable)
        .map(UserConverter::toUserResponse);
  }

  public List<UserResponse> findAllByAge(int age) {
    return userSearchRepository.findByAge(age)
        .stream()
        .map(UserConverter::toUserResponse)
        .collect(Collectors.toList());
  }

  public List<UserResponse> searchByCondition(SearchCondition searchCondition, Pageable pageable) {
    return userSearchQueryRepository.findByCondition(searchCondition, pageable)
        .stream()
        .map(UserConverter::toUserResponse)
        .collect(Collectors.toList());
  }

  public List<UserResponse> findAllByDescription(String description, Pageable pageable) {
    return userSearchQueryRepository.findByMatchedDescription(description, pageable)
        .stream()
        .map(UserConverter::toUserResponse)
        .collect(Collectors.toList());
  }

  public List<UserResponse> findAllByContainedDescription(String description, Pageable pageable) {
    return userSearchQueryRepository.findByContainsDescription(description, pageable)
        .stream()
        .map(UserConverter::toUserResponse)
        .collect(Collectors.toList());
  }
}
