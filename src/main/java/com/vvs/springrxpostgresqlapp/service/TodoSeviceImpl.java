package com.vvs.springrxpostgresqlapp.service;

import java.time.LocalDate;

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
      .doOnNext(t -> {
        t.setDate_added(LocalDate.now());
        if (todoDTO.getDue_date().isBefore(t.getDate_added())) t.setDue_date(t.getDate_added());
      })
      .flatMap(todoRepository::save)
      .map(todoMapper::toDTO);
  }

  @Override
  public Mono<TodoDTO> editTodo(TodoDTO todoDTO, String id) {
    return todoRepository.findById(id)
      .flatMap(todo -> Mono.just(todoDTO)
        .map(todoMapper::fromDTO)
        .doOnNext(t -> {
          t.setId(id);
          t.setDate_added(todo.getDate_added());
          if (todoDTO.getDue_date().isBefore(t.getDate_added())) t.setDue_date(t.getDate_added());
        }))
      .flatMap(todoRepository::save)
      .map(todoMapper::toDTO);
  }

  @Override
  public Mono<Void> deleteTodo(String id) {
    return todoRepository.deleteById(id);
  }

  @Override
  public Mono<Void> deleteAllTodo() {
    return todoRepository.deleteAll();
  }

}
