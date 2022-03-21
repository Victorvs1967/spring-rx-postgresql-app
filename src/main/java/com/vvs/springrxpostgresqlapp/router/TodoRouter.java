package com.vvs.springrxpostgresqlapp.router;

import com.vvs.springrxpostgresqlapp.handler.TodoHandler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Configuration
public class TodoRouter {

  @Value("${api.todo.link}")
  private String todoLink;

  @Bean
  public RouterFunction<ServerResponse> todoRouterFunction(TodoHandler todoHandler) {
    return RouterFunctions
      .route(GET(todoLink).and(accept(APPLICATION_JSON)), todoHandler::getAllTodos)
      .andRoute(POST(todoLink).and(accept(APPLICATION_JSON)), todoHandler::createTodo)
      .andRoute(GET(todoLink.concat("/{id}")).and(accept(APPLICATION_JSON)), todoHandler::getTodo)
      .andRoute(PUT(todoLink.concat("/{id}")).and(accept(APPLICATION_JSON)), todoHandler::editTodo)
      .andRoute(DELETE(todoLink.concat("/{id}")).and(accept(APPLICATION_JSON)), todoHandler::deleteTodo)
      .andRoute(DELETE(todoLink).and(accept(APPLICATION_JSON)), todoHandler::deleteAllTodo);
  }
  
}
