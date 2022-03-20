package com.vvs.springrxpostgresqlapp.router;

import org.springframework.beans.factory.annotation.Value;
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

  @Value("${api.person.link}")
  private String personLink;
  
  @Bean
  public RouterFunction<ServerResponse> personRouterFunction(PersonHandler personHandler) {
    return RouterFunctions
      .route(GET(personLink).and(accept(APPLICATION_JSON)), personHandler::getAllPersons)
      .andRoute(POST(personLink).and(accept(APPLICATION_JSON)), personHandler::createPerson)
      .andRoute(GET(personLink.concat("/{id}")).and(accept(APPLICATION_JSON)), personHandler::getPerson)
      .andRoute(PUT(personLink.concat("/{id}")), personHandler::editPerson)
      .andRoute(DELETE(personLink.concat("/{id}")), personHandler::deletePerson);
      // .andRoute(GET(personLink.concat("/{id}/todos")).and(accept(APPLICATION_JSON)), personHandler::getTodosById);
  }

}
