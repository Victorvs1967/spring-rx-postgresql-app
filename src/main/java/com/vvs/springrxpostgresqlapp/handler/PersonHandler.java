package com.vvs.springrxpostgresqlapp.handler;

import com.vvs.springrxpostgresqlapp.dto.PersonDTO;
import com.vvs.springrxpostgresqlapp.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.HttpStatus.CREATED;

@Component
public class PersonHandler {

  @Autowired
  private PersonService personService;
  
  public Mono<ServerResponse> getAllPersons(ServerRequest request) {
    Flux<PersonDTO> persons = personService.getAllPersons();

    return ServerResponse
      .ok()
      .contentType(APPLICATION_JSON)
      .body(persons, PersonDTO.class);
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
    return ServerResponse
      .ok()
      .contentType(APPLICATION_JSON)
      .body(personService.deletePerson(request.pathVariable("id")), PersonDTO.class);
  }

  public Mono<ServerResponse> deleteAllPerson(ServerRequest request) {
    return ServerResponse
      .ok()
      .contentType(APPLICATION_JSON)
      .body(personService.deleteAllPerson(), PersonDTO.class);
  }

}
