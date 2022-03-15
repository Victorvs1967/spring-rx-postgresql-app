package com.vvs.springrxpostgresqlapp.dto;

import java.util.List;

import com.vvs.springrxpostgresqlapp.model.Todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
  
  private Long id;
  private String name;
  private String email;
  private String password;

  private List<Todo> todos;
  
}
