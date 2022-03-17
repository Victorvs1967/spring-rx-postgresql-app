package com.vvs.springrxpostgresqlapp.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

import com.vvs.springrxpostgresqlapp.model.Todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

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
  
  public Flux<Todo> getTodosBeforeDate(LocalDate due) {
    ArrayList<Todo> todosBeforeDate = new ArrayList<>();
    for (Todo todo : todos) {
      if (todo.getDue_date().isBefore(due)) {
        todosBeforeDate.add(todo);
      }
    }
    return Flux.fromStream(todosBeforeDate.stream());
  }

}
