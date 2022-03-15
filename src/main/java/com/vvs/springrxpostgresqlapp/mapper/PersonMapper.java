package com.vvs.springrxpostgresqlapp.mapper;

import com.vvs.springrxpostgresqlapp.dto.PersonDTO;
import com.vvs.springrxpostgresqlapp.model.Person;

public interface PersonMapper {
  
  public Person fromDTO(PersonDTO personDTO);
  public PersonDTO toDTO(Person person);
  
}
