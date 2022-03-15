package com.vvs.springrxpostgresqlapp.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("todo")
public class Todo {

  @Id
  private Long id;
  private String name;

  @LastModifiedDate
  private LocalDate due_date;
  @CreatedDate
  private LocalDate date_added;

  private Long person_id;

}
