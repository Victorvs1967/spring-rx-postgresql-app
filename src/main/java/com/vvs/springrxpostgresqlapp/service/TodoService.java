package com.vvs.springrxpostgresqlapp.service;

import com.vvs.springrxpostgresqlapp.model.Todo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TodoService {

  public Flux<Todo> getAllTodos();  
  public Mono<Todo> getTodo(Long id);
  public Mono<Todo> createTodo(Todo todo);
  public Mono<Todo> editTodo(Todo todo);
  public Mono<Void> deleteTodo(Long id);
}
