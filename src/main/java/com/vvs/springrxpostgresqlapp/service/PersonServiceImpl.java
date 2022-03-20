package com.vvs.springrxpostgresqlapp.service;

import com.vvs.springrxpostgresqlapp.dto.PersonDTO;
import com.vvs.springrxpostgresqlapp.mapper.PersonMapper;
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

  @Autowired
  private PersonMapper personMapper;

  @Override
  public Flux<PersonDTO> getAllPersons() {
    return personRepository.findAll().map(personMapper::toDTO);
  }

  @Override
  public Mono<PersonDTO> getPerson(String id) {
    return personRepository.findById(id).map(personMapper::toDTO);
  }

  @Override
  public Mono<PersonDTO> createPerson(Person person) {
    return personRepository.save(person).map(personMapper::toDTO);
  }

  @Override
  public Mono<PersonDTO> editPerson(Person person) {
    return personRepository.save(person).map(personMapper::toDTO);
  }

  @Override
  public Mono<Void> deletePerson(String id) {
    return personRepository.deleteById(id);
  }

}
