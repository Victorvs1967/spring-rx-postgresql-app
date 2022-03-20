package com.vvs.springrxpostgresqlapp.model;

import java.util.List;

import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

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

  @DocumentReference
  private List<Todo> todos;

}
