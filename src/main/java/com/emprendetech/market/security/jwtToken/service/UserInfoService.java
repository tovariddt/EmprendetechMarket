package com.emprendetech.market.security.jwtToken.service;

import com.emprendetech.market.dao.PlataformaDao;
import com.emprendetech.market.entitys.Usuario;
import com.emprendetech.market.repositorys.UsuarioRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

	private static final Log LOG = LogFactory.getLog(UserInfoService.class);

	
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PlataformaDao idusarioDao;

    // Implementación del método de UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
       
		LOG.info("llegue a UserInfoService ");

    	 String idusuario=idusarioDao.getIdUsuario(name);
    	 
    	Optional<Usuario> userDetail = repository.findById(Integer.parseInt(idusuario));

        // Convierte userDetail a UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + name));
    }


}

