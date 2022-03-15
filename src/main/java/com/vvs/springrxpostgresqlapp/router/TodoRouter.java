package com.vvs.springrxpostgresqlapp.router;

import com.vvs.springrxpostgresqlapp.handler.TodoHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Configuration
public class TodoRouter {

  private String baseLink = "api/todo";

  @Bean
  public RouterFunction<ServerResponse> todoRouterFunction(TodoHandler todoHandler) {
    return RouterFunctions
      .route(GET(baseLink).and(accept(APPLICATION_JSON)), todoHandler::getAllTodos)
      .andRoute(POST(baseLink).and(accept(APPLICATION_JSON)), todoHandler::createTodo)
      .andRoute(GET(baseLink.concat("/{id}")).and(accept(APPLICATION_JSON)), todoHandler::getTodo)
      .andRoute(PUT(baseLink.concat("/{id}")).and(accept(APPLICATION_JSON)), todoHandler::editTodo)
      .andRoute(DELETE(baseLink.concat("/{id}")).and(accept(APPLICATION_JSON)), todoHandler::deleteTodo);
  }
  
}
