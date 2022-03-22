package com.vvs.springrxpostgresqlapp.handler;

import com.vvs.springrxpostgresqlapp.dto.TodoDTO;
import com.vvs.springrxpostgresqlapp.service.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import static org.springframework.http.HttpStatus.CREATED;

@Component
public class TodoHandler {
  
  @Autowired
  private TodoService todoService;

  public Mono<ServerResponse> getAllTodos(ServerRequest reqest) {
    return ServerResponse
      .ok()
      .contentType(APPLICATION_JSON)
      .body(todoService.getAllTodos(), TodoDTO.class);
  }
  
  public Mono<ServerResponse> getTodo(ServerRequest request) {
    return todoService
      .getTodo(request.pathVariable("id"))
      .flatMap(todo -> ServerResponse
        .ok()
        .contentType(APPLICATION_JSON)
        .body(BodyInserters.fromValue(todo)))
      .switchIfEmpty(ServerResponse.badRequest().build());
  }
  
  public Mono<ServerResponse> createTodo(ServerRequest request) {
    return request.bodyToMono(TodoDTO.class)
      .flatMap(todo -> ServerResponse
        .status(CREATED)
        .contentType(APPLICATION_JSON)
        .body(todoService.createTodo(todo), TodoDTO.class))
      .switchIfEmpty(ServerResponse.badRequest().build());
  }

  public Mono<ServerResponse> editTodo(ServerRequest request) {
    return request.bodyToMono(TodoDTO.class)
      .flatMap(todoDTO -> ServerResponse
        .ok()
        .contentType(APPLICATION_JSON)
        .body(todoService.editTodo(todoDTO, request.pathVariable("id")), TodoDTO.class))
      .switchIfEmpty(ServerResponse.badRequest().build());
  }

  public Mono<ServerResponse> deleteTodo(ServerRequest request) {
    return todoService.deleteTodo(request.pathVariable("id"))
      .flatMap(todo -> ServerResponse
        .ok()
        .contentType(APPLICATION_JSON)
        .body(todo, TodoDTO.class))
      .switchIfEmpty(ServerResponse.badRequest().build());
  }

  public Mono<ServerResponse> deleteAllTodo(ServerRequest request) {
    return ServerResponse
      .ok()
      .contentType(APPLICATION_JSON)
      .body(todoService.deleteAllTodo(), TodoDTO.class);
  }

}
