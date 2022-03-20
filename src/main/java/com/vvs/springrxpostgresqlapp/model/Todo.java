package com.vvs.springrxpostgresqlapp.model;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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

  @LastModifiedDate
  private LocalDate due_date;
  @CreatedDate
  private LocalDate date_added;

  private String personId;

}
