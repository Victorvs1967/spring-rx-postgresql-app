package com.vvs.springrxpostgresqlapp.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import com.vvs.springrxpostgresqlapp.handler.PersonHandler;

@Configuration
public class PersonRouter {

  private String baseLink = "/api/person";
  
  @Bean
  public RouterFunction<ServerResponse> personRouterFunction(PersonHandler personHandler) {
    return RouterFunctions
      .route(GET(baseLink).and(accept(APPLICATION_JSON)), personHandler::getAllPersons)
      .andRoute(GET(baseLink.concat("/{id}")).and(accept(APPLICATION_JSON)), personHandler::getPerson)
      .andRoute(POST(baseLink).and(accept(APPLICATION_JSON)), personHandler::createPerson)
      .andRoute(PUT(baseLink.concat("/{id}")), personHandler::editPerson)
      .andRoute(DELETE(baseLink.concat("/{id}")), personHandler::deletePerson);
  }

}
