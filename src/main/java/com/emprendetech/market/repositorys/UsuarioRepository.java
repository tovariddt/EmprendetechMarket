package com.emprendetech.market.repositorys;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.emprendetech.market.entitys.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

	Optional<Usuario> findById(Integer idusuario);

}
