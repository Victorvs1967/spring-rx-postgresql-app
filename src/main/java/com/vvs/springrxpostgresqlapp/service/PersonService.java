package com.vvs.springrxpostgresqlapp.service;

import com.vvs.springrxpostgresqlapp.model.Person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {
  
  public Flux<Person> getAllPersons();
  public Mono<Person> getPerson(Long id);
  public Mono<Person> createPerson(Person person);
  public Mono<Person> editPerson(Person person);
  public Mono<Void> deletePerson(Long id);

}
