package com.vvs.springrxpostgresqlapp.service;

import com.vvs.springrxpostgresqlapp.model.Todo;
import com.vvs.springrxpostgresqlapp.repository.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TodoSeviceImpl implements TodoService {
  
  @Autowired
  private TodoRepository todoRepository;

  @Override
  public Flux<Todo> getAllTodos() {
    return todoRepository.findAll();
  }

  @Override
  public Mono<Todo> getTodo(Long id) {
    return todoRepository.findById(id);
  }

  @Override
  public Mono<Todo> createTodo(Todo todo) {
    return todoRepository.save(todo);
  }

  @Override
  public Mono<Todo> editTodo(Todo todo) {
    return todoRepository.save(todo);
  }

  @Override
  public Mono<Void> deleteTodo(Long id) {
    return todoRepository.deleteById(id);
  }

}
