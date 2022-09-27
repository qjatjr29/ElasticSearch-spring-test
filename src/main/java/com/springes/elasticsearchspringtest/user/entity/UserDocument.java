package com.springes.elasticsearchspringtest.user.entity;

import java.time.LocalDateTime;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName = "user")
@Mapping(mappingPath = "elastic/user-mapping.json")
@Setting(settingPath = "elastic/user-setting.json")
@Getter
@Builder
public class UserDocument {

  @Id
  private Long id;

  private String name;

  private String nickname;

  private int age;

  private String description;

  @Field(type = FieldType.Date, format = {DateFormat.date_hour_minute_second_millis, DateFormat.epoch_millis})
  private LocalDateTime createdAt;

}
