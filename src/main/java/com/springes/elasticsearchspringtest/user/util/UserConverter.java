package com.springes.elasticsearchspringtest.user.util;

import com.springes.elasticsearchspringtest.user.dto.SaveUserRequest;
import com.springes.elasticsearchspringtest.user.dto.UserResponse;
import com.springes.elasticsearchspringtest.user.entity.User;
import com.springes.elasticsearchspringtest.user.entity.UserDocument;

public class UserConverter {

  public static UserDocument from(User user) {
    return UserDocument.builder()
        .id(user.getId())
        .name(user.getName())
        .nickname(user.getNickname())
        .age(user.getAge())
        .description(user.getDescription())
        .createdAt(user.getCreatedAt())
        .build();
  }

  public static User to(SaveUserRequest request) {
    return User.builder()
        .name(request.getName())
        .nickname(request.getNickname())
        .age(request.getAge())
        .description(request.getDescription())
        .build();
  }


  public static UserResponse toUserResponse(UserDocument userDocument) {
    return UserResponse.builder()
        .name(userDocument.getName())
        .nickname(userDocument.getNickname())
        .age(userDocument.getAge())
        .description(userDocument.getDescription())
        .build();
  }
}
