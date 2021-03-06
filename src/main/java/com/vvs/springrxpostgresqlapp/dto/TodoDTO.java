package com.vvs.springrxpostgresqlapp.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {

  private String id;
  private String name;
  private LocalDate due_date;
  private LocalDate date_added;
  private String personId;  

}
