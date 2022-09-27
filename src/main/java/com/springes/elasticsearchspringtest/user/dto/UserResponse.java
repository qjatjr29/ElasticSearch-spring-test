package com.springes.elasticsearchspringtest.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {

  private String name;

  private String nickname;

  private int age;

  private String description;


}
