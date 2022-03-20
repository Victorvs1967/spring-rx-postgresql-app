package com.vvs.springrxpostgresqlapp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
  
  private String id;
  private String name;
  private String email;
  private String password;

  private List<String> todosIds;

}
