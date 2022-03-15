package com.vvs.springrxpostgresqlapp.handler;

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
    Mono<Todo> todoMono = todoService.getTodo(Long.parseLong(request.pathVariable("id")));

    return todoMono.flatMap(todo -> ServerResponse
        .ok()
        .contentType(APPLICATION_JSON)
        .body(BodyInserters.fromValue(todo)))
      .switchIfEmpty(ServerResponse.badRequest().build());
  }
  
  public Mono<ServerResponse> createTodo(ServerRequest request) {
    Mono<Todo> todoMono = request.bodyToMono(Todo.class);

    return todoMono.flatMap(todo -> ServerResponse
        .status(CREATED)
        .contentType(APPLICATION_JSON)
        .body(todoService.createTodo(todo), Todo.class))
      .switchIfEmpty(ServerResponse.badRequest().build());
  }

  public Mono<ServerResponse> editTodo(ServerRequest request) {
    Long id = Long.parseLong(request.pathVariable("id"));
    Mono<Todo> todoMonoExist = todoService.getTodo(id);
    Mono<Todo> todoMono = request.bodyToMono(Todo.class);
  
    return todoMono
      .zipWith(todoMonoExist, (todo, todoExist) -> Todo.builder()
        .id(todoExist.getId())
        .name(todo.getName())
        .due_date(todo.getDue_date())
        .date_added(todo.getDate_added())
        .person_id(todo.getPerson_id())
        .build())
      .flatMap(todo -> ServerResponse
        .ok()
        .contentType(APPLICATION_JSON)
        .body(todoService.editTodo(todo), Todo.class))
      .switchIfEmpty(ServerResponse.badRequest().build());
  }

  public Mono<ServerResponse> deleteTodo(ServerRequest request) {
    Long id = Long.parseLong(request.pathVariable("id"));
    return ServerResponse
      .ok()
      .contentType(APPLICATION_JSON)
      .body(todoService.deleteTodo(id), Todo.class);
  }

}
