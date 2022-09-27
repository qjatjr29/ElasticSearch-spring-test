package com.springes.elasticsearchspringtest.user.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class SaveAllUserRequest {

  private List<SaveUserRequest> userRequestList;

}
