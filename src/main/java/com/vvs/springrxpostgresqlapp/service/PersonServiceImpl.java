package com.vvs.springrxpostgresqlapp.service;

import java.time.LocalDate;

import com.vvs.springrxpostgresqlapp.dto.PersonDTO;
import com.vvs.springrxpostgresqlapp.dto.TodoDTO;
import com.vvs.springrxpostgresqlapp.mapper.PersonMapper;
import com.vvs.springrxpostgresqlapp.mapper.TodoMapper;
import com.vvs.springrxpostgresqlapp.repository.PersonRepository;
import com.vvs.springrxpostgresqlapp.repository.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonServiceImpl implements PersonService {
  
  @Autowired
  private PersonRepository personRepository;
  @Autowired
  private TodoRepository todoRepository;
  @Autowired
  private PersonMapper personMapper;
  @Autowired
  private TodoMapper todoMapper;

  @Override
  public Flux<PersonDTO> getAllPersons() {
    return personRepository.findAll().map(personMapper::toDTO);
  }

  @Override
  public Mono<PersonDTO> getPerson(String id) {
    return personRepository.findById(id).map(personMapper::toDTO);
  }

  @Override
  public Mono<PersonDTO> createPerson(PersonDTO personDTO) {
    return Mono.just(personDTO)
      .map(personMapper::fromDTO)
      .flatMap(personRepository::save)
      .map(personMapper::toDTO);
  }

  @Override
  public Mono<PersonDTO> editPerson(PersonDTO personDTO, String id) {
    return personRepository.findById(id)
      .flatMap(person -> Mono.just(personDTO)
        .map(personMapper::fromDTO)
        .doOnNext(p -> p.setId(id)))
      .flatMap(personRepository::save)
      .map(personMapper::toDTO);
  }

  @Override
  public Mono<Void> deletePerson(String id) {
    return personRepository.deleteById(id);
  }

  @Override
  public Mono<Void> deleteAllPerson() {
    return personRepository.deleteAll();
  }

  @Override
  public Flux<TodoDTO> getAllPersonTodos(String id) {
    return personRepository.findById(id)
      .flatMapIterable(person -> person.getTodosIds())
      .flatMap(todoRepository::findById)
      .map(todoMapper::toDTO);
  }

  @Override
  public Flux<TodoDTO> getPersonTodosBefore(String id, LocalDate date) {
    return personRepository.findById(id)
      .flatMapIterable(person -> person.getTodosIds())
      .flatMap(todoRepository::findById).log()
      .filter(todo -> todo.getDue_date().isBefore(date))
      .map(todoMapper::toDTO);
  }

}
