package com.vvs.springrxpostgresqlapp.handler;

import com.vvs.springrxpostgresqlapp.dto.PersonDTO;
import com.vvs.springrxpostgresqlapp.dto.TodoDTO;
import com.vvs.springrxpostgresqlapp.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.time.LocalDate;

import static org.springframework.http.HttpStatus.CREATED;

@Component
public class PersonHandler {

  @Autowired
  private PersonService personService;
  
  public Mono<ServerResponse> getAllPersons(ServerRequest request) {
    return ServerResponse
      .ok()
      .contentType(APPLICATION_JSON)
      .body(personService.getAllPersons(), PersonDTO.class);
  }

  public Mono<ServerResponse> getPerson(ServerRequest request) {    
    return personService.getPerson(request.pathVariable("id"))
      .flatMap(person -> ServerResponse
        .ok()
        .contentType(APPLICATION_JSON)
        .body(BodyInserters.fromValue(person)))
      .switchIfEmpty(ServerResponse.badRequest().build());
  }

  public Mono<ServerResponse> createPerson(ServerRequest request) {
    return request.bodyToMono(PersonDTO.class)
      .flatMap(person -> ServerResponse
        .status(CREATED)
        .contentType(APPLICATION_JSON)
        .body(personService.createPerson(person), PersonDTO.class))
      .switchIfEmpty(ServerResponse.badRequest().build());
  }

  public Mono<ServerResponse> editPerson(ServerRequest request) {
    return request.bodyToMono(PersonDTO.class)
      .flatMap(personDTO -> ServerResponse
        .ok()
        .contentType(APPLICATION_JSON)
        .body(personService.editPerson(personDTO, request.pathVariable("id")), PersonDTO.class))
      .switchIfEmpty(ServerResponse.badRequest().build());
  }

  public Mono<ServerResponse> deletePerson(ServerRequest request) {
    return personService.deletePerson(request.pathVariable("id"))
      .flatMap(person -> ServerResponse
        .ok()
        .contentType(APPLICATION_JSON)
        .body(person, PersonDTO.class))
      .switchIfEmpty(ServerResponse.badRequest().build());
  }

  public Mono<ServerResponse> deleteAllPerson(ServerRequest request) {
    return ServerResponse
      .ok()
      .contentType(APPLICATION_JSON)
      .body(personService.deleteAllPerson(), PersonDTO.class);
  }

  public Mono<ServerResponse> getAllPersonTodos(ServerRequest request) {
    return ServerResponse
      .ok()
      .contentType(APPLICATION_JSON)
      .body(personService.getAllPersonTodos(request.pathVariable("id"))
        .switchIfEmpty(ServerResponse.badRequest().build().cast(TodoDTO.class)), TodoDTO.class);
  }

  public Mono<ServerResponse> getPersonTodosBefore(ServerRequest request) {
    return ServerResponse
      .ok()
      .contentType(APPLICATION_JSON)
      .body(personService.getPersonTodosBefore(request.pathVariable("id"), LocalDate.parse(request.pathVariable("date")))
        .switchIfEmpty(ServerResponse.badRequest().build().cast(TodoDTO.class)), TodoDTO.class);
  }

}
