package com.emprendetech.market.controllers;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.dao.PlataformaDao;
import com.emprendetech.market.entitys.Emprendimientos;
import com.emprendetech.market.entitys.Personas;
import com.emprendetech.market.entitys.Usuario;
import com.emprendetech.market.repositorys.EmprendimientosRepository;
import com.emprendetech.market.repositorys.PersonasRepository;
import com.emprendetech.market.repositorys.UsuarioRepository;
import com.emprendetech.market.service.requestDto.RegistroDto;
import com.emprendetech.market.utils.Utils;


@Controller
public class RegistroController {
	
	private static final Log LOG = LogFactory.getLog(RegistroController.class);
	
	@Autowired
	private PlataformaDao perfilDao;
	@Autowired
	private  PersonasRepository personasRepository;
	@Autowired
	private  UsuarioRepository usuarioRepository;
	@Autowired
	private  EmprendimientosRepository emprendimientosRepository;
	
	
	public String AltaUsuario(@RequestBody RegistroDto registroDto)throws Exception{
	LOG.info("createAlta - createAlta() Method");
	LOG.debug("createAlta :: " + registroDto.toString());
	
	String response= null;	 
	
	Personas personasInsert=new Personas();
	personasInsert.setNombre(registroDto.getNombre());
	personasInsert.setApellido_materno(registroDto.getApellido_materno());
	personasInsert.setApellido_paterno(registroDto.getApellido_paterno());
	personasInsert.setTelefono(registroDto.getTelefono());
	personasInsert.setDireccion(registroDto.getDireccion());
    personasInsert.setRefencia(registroDto.getReferencia());
    personasInsert.setCreadoridusuario(registroDto.getCreadoridusuario());
    personasInsert.setId_codigo_postal(registroDto.getId_codigo_postal());
   
    Utils util = new Utils();
    personasInsert.setFechacreacion(util.currentDate());	
    personasInsert.setFechamodificacion(util.currentDate());
	LOG.info("createAltaUsuario - createAltaUsuario() Method "+ personasInsert.toString());

    personasInsert=personasRepository.save(personasInsert);

				    if (personasRepository.existsById(personasInsert.getIdpersona())) {
				    		
				    	LOG.info("createAltaUsuario - createAltaUsuario() Method "+ personasInsert.getIdpersona());
				    	LOG.debug("createAlta Usuario Error:: " + personasInsert.getIdpersona());
				   
				    Usuario usuarioInsert =new Usuario();
				   
				    String NombreUsuario=registroDto.getNombre()+" "+registroDto.getApellido_paterno();
				    
				    usuarioInsert.setIdpersona(personasInsert.getIdpersona());
				    usuarioInsert.setContrasena(registroDto.getContrasena());
				    usuarioInsert.setCorreo(registroDto.getCorreo());
				    usuarioInsert.setIdrol(registroDto.getIdrol());
				    usuarioInsert.setIdperfil(registroDto.getIdperfil());
				    usuarioInsert.setNombreusuarui(NombreUsuario);
				    usuarioInsert.setCreadoridusuario(registroDto.getCreadoridusuario());  
				    usuarioInsert.setFechacreacion(util.currentDate());	
				    usuarioInsert.setFechamodificacion(util.currentDate());
				    usuarioInsert=usuarioRepository.save(usuarioInsert);	 
				    
				    response ="Felicidades fue registrado como="+usuarioInsert.getNombreusuarui();
					LOG.info(personasInsert.getIdpersona());

					}
					    String perfilCom = perfilDao.getEmp(registroDto.getIdperfil());	
				        boolean sonIguales = perfilCom.equals("Emprendedor");
						LOG.info(perfilCom);
					    if (sonIguales)
					    {
					    	LOG.info("createAltaEmprendimiento - createAltaEmprendimiento() Method "+ personasInsert.getIdpersona());
					    	LOG.debug("createAlta Emprendimiento Error:: " + personasInsert.getIdpersona());
					   
					    Emprendimientos emprendimientoInsert =new Emprendimientos();
					   				    
					    emprendimientoInsert.setIdpersona(personasInsert.getIdpersona());
					    emprendimientoInsert.setNombre(registroDto.getNombre_empren());
					    emprendimientoInsert.setDescripcion(registroDto.getDescripcion());
					    emprendimientoInsert.setIndustria(registroDto.getIndustria());
					    emprendimientoInsert.setCreadoridusuario(registroDto.getCreadoridusuario());  
					    emprendimientoInsert.setFechacreacion(util.currentDate());	
					    emprendimientoInsert.setFechamodificacion(util.currentDate());
					    emprendimientoInsert=emprendimientosRepository.save(emprendimientoInsert);	 
					    
					    response ="Felicidades "+ personasInsert.getNombre() +" Su emprendimiento fue registrado como = "+emprendimientoInsert.getNombre();
						LOG.info(emprendimientoInsert.getNombre());
					    }
					
	
	return response  ;
	}
			
}

