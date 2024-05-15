package com.emprendetech.market.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;

import com.emprendetech.market.entitys.Personas;

public interface PersonasRepository extends CrudRepository<Personas, Integer> {


	
}
