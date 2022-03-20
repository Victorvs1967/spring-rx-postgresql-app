package com.vvs.springrxpostgresqlapp.repository;

import com.vvs.springrxpostgresqlapp.model.Todo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TodoRepository extends ReactiveMongoRepository<Todo, String> {  
}