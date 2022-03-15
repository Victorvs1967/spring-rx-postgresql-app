package com.vvs.springrxpostgresqlapp.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("person")
public class Person {
  
  @Id
  private Long id;
  private String name;
  private String email;
  private String password;

  @Transient
  private List<Todo> todos;

}
