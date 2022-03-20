package com.vvs.springrxpostgresqlapp.service;

import com.vvs.springrxpostgresqlapp.dto.PersonDTO;
import com.vvs.springrxpostgresqlapp.model.Person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {
  
  public Flux<PersonDTO> getAllPersons();
  public Mono<PersonDTO> getPerson(String id);
  public Mono<PersonDTO> createPerson(Person person);
  public Mono<PersonDTO> editPerson(Person person);
  public Mono<Void> deletePerson(String id);

}
