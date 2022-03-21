package com.vvs.springrxpostgresqlapp.model;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("todo")
public class Todo {

  @Id
  private String id;
  private String name;
  private LocalDate due_date;

  @CreatedDate
  private LocalDate date_added;

  private String personId;

}
