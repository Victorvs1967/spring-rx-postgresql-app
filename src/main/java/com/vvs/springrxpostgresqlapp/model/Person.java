package com.vvs.springrxpostgresqlapp.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("person")
public class Person {
  
  @Id
  private String id;
  private String name;
  private String email;
  private String password;

  @DBRef
  private List<String> todoIds;

}
