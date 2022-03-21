package com.vvs.springrxpostgresqlapp.service;

import com.vvs.springrxpostgresqlapp.dto.TodoDTO;
import com.vvs.springrxpostgresqlapp.mapper.TodoMapper;
import com.vvs.springrxpostgresqlapp.repository.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TodoSeviceImpl implements TodoService {
  
  @Autowired
  private TodoRepository todoRepository;

  @Autowired
  private TodoMapper todoMapper;

  @Override
  public Flux<TodoDTO> getAllTodos() {
    return todoRepository.findAll()
      .map(todoMapper::toDTO);
  }

  @Override
  public Mono<TodoDTO> getTodo(String id) {
    return todoRepository.findById(id)
      .map(todoMapper::toDTO);
  }

  @Override
  public Mono<TodoDTO> createTodo(TodoDTO todoDTO) {
    return Mono.just(todoDTO)
      .map(todoMapper::fromDTO)
      .flatMap(todoRepository::save)
      .map(todoMapper::toDTO);
  }

  @Override
  public Mono<TodoDTO> editTodo(TodoDTO todoDTO, String id) {
    return todoRepository.findById(id)
      .flatMap(todo -> Mono.just(todoDTO)
        .map(todoMapper::fromDTO)
        .doOnNext(t -> t.setId(id)))
      .flatMap(todoRepository::save)
      .map(todoMapper::toDTO);
  }

  @Override
  public Mono<Void> deleteTodo(String id) {
    return todoRepository.deleteById(id);
  }

}
