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

  private Long id;
  private String name;
  private LocalDate due_date;
  private LocalDate date_added;
  private Long person_id;
  
}
