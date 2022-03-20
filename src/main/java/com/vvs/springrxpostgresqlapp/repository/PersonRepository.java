package com.vvs.springrxpostgresqlapp.repository;

import com.vvs.springrxpostgresqlapp.model.Person;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PersonRepository extends ReactiveMongoRepository<Person, String> {
}