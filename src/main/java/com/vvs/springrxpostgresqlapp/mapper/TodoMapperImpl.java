package com.vvs.springrxpostgresqlapp.mapper;

import com.vvs.springrxpostgresqlapp.dto.TodoDTO;
import com.vvs.springrxpostgresqlapp.model.Todo;

import org.springframework.stereotype.Service;

@Service
public class TodoMapperImpl implements TodoMapper {

  @Override
  public Todo fromDTO(TodoDTO todoDTO) {
    return Todo.builder()
      .id(todoDTO.getId())
      .name(todoDTO.getName())
      .due_date(todoDTO.getDue_date())
      .date_added(todoDTO.getDate_added())
      .person_id(todoDTO.getPerson_id())
      .build();
  }

  @Override
  public TodoDTO toDTO(Todo todo) {
    return TodoDTO.builder()
      .id(todo.getId())
      .name(todo.getName())
      .due_date(todo.getDue_date())
      .date_added(todo.getDate_added())
      .person_id(todo.getPerson_id())
      .build();
  }
  
}
