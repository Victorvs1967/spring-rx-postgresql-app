package com.vvs.springrxpostgresqlapp.service;

import com.vvs.springrxpostgresqlapp.dto.TodoDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TodoService {

  public Flux<TodoDTO> getAllTodos();  
  public Mono<TodoDTO> getTodo(String id);
  public Mono<TodoDTO> createTodo(TodoDTO todoDTO);
  public Mono<TodoDTO> editTodo(TodoDTO todoDTO, String id);
  public Mono<Void> deleteTodo(String id);
  public Mono<Void> deleteAllTodo();
}
