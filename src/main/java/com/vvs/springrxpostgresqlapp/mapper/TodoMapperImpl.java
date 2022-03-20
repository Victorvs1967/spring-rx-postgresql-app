package com.vvs.springrxpostgresqlapp.mapper;

import com.vvs.springrxpostgresqlapp.dto.TodoDTO;
import com.vvs.springrxpostgresqlapp.model.Todo;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TodoMapperImpl implements TodoMapper {

  @Override
  public Todo fromDTO(TodoDTO todoDTO) {
    Todo todo = new Todo();
    BeanUtils.copyProperties(todoDTO, todo);
    return todo;
  }

  @Override
  public TodoDTO toDTO(Todo todo) {
    TodoDTO todoDTO = new TodoDTO();
    BeanUtils.copyProperties(todo, todoDTO);
    return todoDTO;
  }

}
