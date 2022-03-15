package com.vvs.springrxpostgresqlapp.mapper;

import com.vvs.springrxpostgresqlapp.dto.PersonDTO;
import com.vvs.springrxpostgresqlapp.model.Person;

import org.springframework.stereotype.Service;

@Service
public class PersonMapperImpl implements PersonMapper {

  @Override
  public Person fromDTO(PersonDTO personDTO) {
    return Person.builder()
      .id(personDTO.getId())
      .name(personDTO.getName())
      .email(personDTO.getEmail())
      .password(personDTO.getPassword())
      .todos(personDTO.getTodos())
      .build();
  }

  @Override
  public PersonDTO toDTO(Person person) {
    return null;
  }
  
}
