package com.vvs.springrxpostgresqlapp.service;

import com.vvs.springrxpostgresqlapp.dto.PersonDTO;
import com.vvs.springrxpostgresqlapp.dto.TodoDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {
  
  public Flux<PersonDTO> getAllPersons();
  public Mono<PersonDTO> getPerson(String id);
  public Mono<PersonDTO> createPerson(PersonDTO personDTO);
  public Mono<PersonDTO> editPerson(PersonDTO personDTO, String id);
  public Mono<Void> deletePerson(String id);
  public Mono<Void> deleteAllPerson();
  public Flux<TodoDTO> getAllPersonTodos(String id);

}
