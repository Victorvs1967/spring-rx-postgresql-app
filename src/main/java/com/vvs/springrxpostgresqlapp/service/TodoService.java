package com.vvs.springrxpostgresqlapp.service;

import com.vvs.springrxpostgresqlapp.dto.TodoDTO;
import com.vvs.springrxpostgresqlapp.model.Todo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TodoService {

  public Flux<TodoDTO> getAllTodos();  
  public Mono<TodoDTO> getTodo(String id);
  public Mono<TodoDTO> createTodo(Todo todo);
  public Mono<TodoDTO> editTodo(Todo todo);
  public Mono<Void> deleteTodo(String id);
}
