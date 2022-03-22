package com.vvs.springrxpostgresqlapp.model;

import java.util.List;

import com.mongodb.lang.NonNull;

import org.springframework.data.annotation.Id;
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
  @NonNull
  private String name;
  @NonNull
  private String email;
  @NonNull
  private String password;

  private List<String> todosIds;

}
