package com.springes.elasticsearchspringtest.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserRequest {

  private String name;

  private String nickname;

  private int age;

  private String description;

}
