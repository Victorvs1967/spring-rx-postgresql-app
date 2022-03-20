package com.vvs.springrxpostgresqlapp.mapper;

import com.vvs.springrxpostgresqlapp.dto.PersonDTO;
import com.vvs.springrxpostgresqlapp.model.Person;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PersonMapperImpl implements PersonMapper {
  
  @Override
  public Person fromDTO(PersonDTO personDTO) {
    Person person = new Person();
    BeanUtils.copyProperties(personDTO, person);
    return person;
  }
  
  @Override
  public PersonDTO toDTO(Person person) {
    PersonDTO personDTO = new PersonDTO();
    BeanUtils.copyProperties(person, personDTO);
    return personDTO;
  }
  
}
