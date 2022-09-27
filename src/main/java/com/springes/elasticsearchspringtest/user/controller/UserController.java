package com.springes.elasticsearchspringtest.user.controller;

import com.springes.elasticsearchspringtest.user.dto.SaveAllUserRequest;
import com.springes.elasticsearchspringtest.user.dto.SearchCondition;
import com.springes.elasticsearchspringtest.user.dto.UserResponse;
import com.springes.elasticsearchspringtest.user.service.UserService;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping()
  public ResponseEntity<Void> saveAll(@RequestBody SaveAllUserRequest userRequest) {
    userService.saveAllUser(userRequest);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/Documents")
  public ResponseEntity<Void> saveAllUserDocuments() {
    userService.saveAllUserDocuments();
    System.out.println("done2");
    return ResponseEntity.ok().build();
  }

  @GetMapping()
  public ResponseEntity<List<UserResponse>> searchByName(
      SearchCondition searchCondition,
      @PageableDefault(page = 0, size = 10) Pageable pageable) {

    List<UserResponse> userList = userService.searchByCondition(searchCondition, pageable);
    return ResponseEntity.ok(userList);
  }

  @GetMapping("/age")
  public ResponseEntity<List<UserResponse>> getByAge(@RequestParam int age) {

    List<UserResponse> userList = userService.findAllByAge(age);
    return ResponseEntity.ok(userList);
  }

  @GetMapping("/nickname")
  public ResponseEntity<Page<UserResponse>> getByNickname(
      @RequestParam String nickname,
      @PageableDefault(page = 0, size = 10) Pageable pageable) {

    Page<UserResponse> userList = userService.findAllByNickname(nickname, pageable);
    return ResponseEntity.ok(userList);
  }


  @GetMapping("/matchedDescription")
  public ResponseEntity<List<UserResponse>> getByMatchedDescription(
      @RequestParam String description,
      @PageableDefault(page = 0, size = 10) Pageable pageable) {

    List<UserResponse> userList = userService.findAllByDescription(description, pageable);
    return ResponseEntity.ok(userList);
  }

  @GetMapping("/containedDescription")
  public ResponseEntity<List<UserResponse>> getByContainedDescription(
      @RequestParam String description,
      @PageableDefault(page = 0, size = 10) Pageable pageable) {

    List<UserResponse> userList = userService.findAllByContainedDescription(description, pageable);
    return ResponseEntity.ok(userList);
  }
}
