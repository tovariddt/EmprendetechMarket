package com.emprendetech.market.repositorys;

import org.springframework.data.repository.CrudRepository;

import com.emprendetech.market.entitys.Clientes;

public interface ClientesRepository extends CrudRepository<Clientes, Integer> {
	
}
