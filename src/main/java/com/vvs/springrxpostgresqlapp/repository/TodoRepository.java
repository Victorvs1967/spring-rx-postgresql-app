package com.vvs.springrxpostgresqlapp.repository;

import com.vvs.springrxpostgresqlapp.model.Todo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends ReactiveCrudRepository<Todo, Long> {
  
}
