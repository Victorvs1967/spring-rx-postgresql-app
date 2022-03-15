package com.vvs.springrxpostgresqlapp.service;

import com.vvs.springrxpostgresqlapp.model.Person;
import com.vvs.springrxpostgresqlapp.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonServiceImpl implements PersonService {
  
  @Autowired
  private PersonRepository personRepository;

  @Override
  public Flux<Person> getAllPersons() {
    return personRepository.findAll();
  }

  @Override
  public Mono<Person> getPerson(Long id) {
    return personRepository.findById(id);
  }

  @Override
  public Mono<Person> createPerson(Person person) {
    return personRepository.save(person);
  }

  @Override
  public Mono<Person> editPerson(Person person) {
    return personRepository.save(person);
  }

  @Override
  public Mono<Void> deletePerson(Long id) {
    return personRepository.deleteById(id);
  }

}
