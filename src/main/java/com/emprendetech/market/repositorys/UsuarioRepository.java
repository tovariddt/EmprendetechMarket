package com.emprendetech.market.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;

import com.emprendetech.market.entitys.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {


	
}
