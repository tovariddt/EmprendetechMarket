package com.emprendetech.market.repositorys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;

import com.emprendetech.market.entitys.Roles;

public interface RolesRepository extends CrudRepository<Roles, Integer> {
	
}
