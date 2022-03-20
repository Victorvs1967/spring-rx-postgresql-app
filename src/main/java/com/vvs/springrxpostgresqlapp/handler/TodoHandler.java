package com.vvs.springrxpostgresqlapp.handler;

import com.vvs.springrxpostgresqlapp.dto.TodoDTO;
import com.vvs.springrxpostgresqlapp.model.Todo;
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
      .body(todoService.getAllTodos(), Todo.class);
  }
  
  public Mono<ServerResponse> getTodo(ServerRequest request) {
    Mono<TodoDTO> todoMono = todoService.getTodo(request.pathVariable("id"));

    return todoMono
      .flatMap(todo -> ServerResponse
        .ok()
        .contentType(APPLICATION_JSON)
        .body(BodyInserters.fromValue(todo)))
      .switchIfEmpty(ServerResponse.badRequest().build());
  }
  
  public Mono<ServerResponse> createTodo(ServerRequest request) {
    Mono<TodoDTO> todoDTOMono = request.bodyToMono(TodoDTO.class);

    return todoDTOMono
      .flatMap(todo -> ServerResponse
        .status(CREATED)
        .contentType(APPLICATION_JSON)
        .body(todoService.createTodo(todo), TodoDTO.class))
      .switchIfEmpty(ServerResponse.badRequest().build());
  }

  public Mono<ServerResponse> editTodo(ServerRequest request) {
    String id = request.pathVariable("id");
    Mono<TodoDTO> todoDTOMono = request.bodyToMono(TodoDTO.class);
  
    return todoDTOMono
      .flatMap(todoDTO -> ServerResponse
        .ok()
        .contentType(APPLICATION_JSON)
        .body(todoService.editTodo(todoDTO, id), TodoDTO.class))
      .switchIfEmpty(ServerResponse.badRequest().build());
  }

  public Mono<ServerResponse> deleteTodo(ServerRequest request) {
    String id = request.pathVariable("id");
    return ServerResponse
      .ok()
      .contentType(APPLICATION_JSON)
      .body(todoService.deleteTodo(id), Todo.class);
  }

}
