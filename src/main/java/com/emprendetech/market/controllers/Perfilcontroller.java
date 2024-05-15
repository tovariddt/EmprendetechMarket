package com.emprendetech.market.controllers;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.emprendetech.market.entitys.Perfiles;
import com.emprendetech.market.repositorys.PerfilesRepository;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.responseDto.RolesRespDto;
import com.emprendetech.market.utils.Utils;



public class Perfilcontroller {
	
	private static final Log LOG = LogFactory.getLog(Perfilcontroller.class);

@Autowired
private  PerfilesRepository perfilesRepository;

 public ResponseEntity<?> createPerfil(@RequestBody Perfiles perfiles) {
	LOG.info("createPerfil - createPerfil() Method");
	LOG.debug("perfiles :: " + perfiles.toString());
	
	Perfiles perfilInsert=perfiles;
	Perfiles front =new Perfiles();
	ResponseEntity<?> response = null;
	
	try {
	Utils util = new Utils();
	perfilInsert.setFechacreacion(util.currentDate());	
	perfilInsert.setFechamodificacion(util.currentDate());
	
	 perfilesRepository.save(perfilInsert);
	 
	 front =  perfilesRepository.findById(perfilInsert.getIdperfil()).get();
	
	final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "perfil");
	responseContenido.setContenido(front);

	response = new ResponseEntity<>(responseContenido, HttpStatus.OK);
	
	}catch(Exception e) {
			LOG.info("eror"+  e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error",perfiles.toString());
		}


	return response;

	
}

}
