package com.vvs.springrxpostgresqlapp.mapper;

import com.vvs.springrxpostgresqlapp.dto.TodoDTO;
import com.vvs.springrxpostgresqlapp.model.Todo;

public interface TodoMapper {
  
  public Todo fromDTO(TodoDTO todoDTO);
  public TodoDTO toDTO(Todo todo);
  
}
