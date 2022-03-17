package com.vvs.springrxpostgresqlapp.handler;

import com.vvs.springrxpostgresqlapp.dto.PersonDTO;
import com.vvs.springrxpostgresqlapp.mapper.PersonMapper;
import com.vvs.springrxpostgresqlapp.model.Person;
import com.vvs.springrxpostgresqlapp.model.Todo;
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
  @Autowired
  private PersonMapper personMapper;
  
  public Mono<ServerResponse> getAllPersons(ServerRequest request) {
    Flux<Person> persons = personService.getAllPersons();

    return ServerResponse
      .ok()
      .contentType(APPLICATION_JSON)
      .body(persons, Person.class);
  }

  public Mono<ServerResponse> getPerson(ServerRequest request) {
    Long id = Long.parseLong(request.pathVariable("id"));
    
    return personService.getPerson(id)
      .flatMap(person -> ServerResponse
        .ok()
        .contentType(APPLICATION_JSON)
        .body(BodyInserters.fromValue(person)))
      .switchIfEmpty(ServerResponse.badRequest().build());
  }

  public Mono<ServerResponse> createPerson(ServerRequest request) {
    Mono<PersonDTO> personDtoMono = request.bodyToMono(PersonDTO.class);

    return personDtoMono
      .map(personMapper::fromDTO)
      .flatMap(person -> ServerResponse
        .status(CREATED)
        .contentType(APPLICATION_JSON)
        .body(personService.createPerson(person), Person.class))
      .switchIfEmpty(ServerResponse.badRequest().build());
  }

  public Mono<ServerResponse> editPerson(ServerRequest request) {
    Long id = Long.parseLong(request.pathVariable("id"));
    Mono<PersonDTO> personDtoMono = request.bodyToMono(PersonDTO.class);
    Mono<Person> personMonoExist = personService.getPerson(id);

    return personDtoMono
      .map(personMapper::fromDTO)
      .zipWith(personMonoExist, (person, personExist) -> Person.builder()
        .id(personExist.getId())
        .name(person.getName())
        .email(person.getEmail())
        .password(person.getPassword())
        .todos(person.getTodos())
        .build())
      .flatMap(person -> ServerResponse
        .ok()
        .contentType(APPLICATION_JSON)
        .body(personService.createPerson(person), Person.class))
      .switchIfEmpty(ServerResponse.badRequest().build());
  }

  public Mono<ServerResponse> deletePerson(ServerRequest request) {
    Long id = Long.parseLong(request.pathVariable("id"));

    return ServerResponse
      .ok()
      .contentType(APPLICATION_JSON)
      .body(personService.deletePerson(id), Person.class);
  }

  public Mono<ServerResponse> getTodosById(ServerRequest request) {
    Long id = Long.parseLong(request.pathVariable("id"));

    return ServerResponse
        .ok()
        .contentType(APPLICATION_JSON)
        .body(personService.getTodosById(id), Todo.class);
  }

}
