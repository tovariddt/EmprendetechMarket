package com.emprendetech.market.servicios.front.impl;

import java.util.ArrayList;
import java.util.List;

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

import com.emprendetech.market.controllers.RegistroController;
import com.emprendetech.market.dao.PlataformaDao;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.responseDto.PostalRespDto;
import com.emprendetech.market.service.responseDto.RolesRespDto;
import com.emprendetech.market.servicios.registro.RegistroService;

import com.emprendetech.market.service.responseDto.RolesRespDto;
import com.emprendetech.market.service.requestDto.RegistroDto;
import org.springframework.http.HttpStatus;

@ResponseBody
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)

@RestController
@RequestMapping(path = "/api/plataforma")
public class PlataformaServiceImpl {
	private static final Log LOG = LogFactory.getLog(RegistroService.class);

	@Autowired
	private PlataformaDao rolesDao, codigoDao, CorreoDAo;
	
	@Autowired
	private RegistroController AltaUsuario;

	@GetMapping("/roles")
	public ResponseEntity<?> getAllRoles() {

		LOG.info("getAllRoles - getAllRoles() Method");
		ResponseEntity<?> response = null;

		try {
			List<RolesRespDto> result = new ArrayList<RolesRespDto>();

			result = rolesDao.getRoles();

			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Roles");
			responseContenido.setContenido(result);
			response = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Roles");
		}

		return response;
	}
	

	@PostMapping("/codigopostal")
	public ResponseEntity<?> getAllCodigoPostal(@RequestBody PostalRespDto request) {

		LOG.info("getAllCodigoPostal - getAllCodigoPostal() Method");
		ResponseEntity<?> response = null;
		String clave = request.getClave();
		try {
			List<PostalRespDto> resultados = new ArrayList<PostalRespDto>();
			resultados = codigoDao.getCodigo(clave);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Codigo");
			responseContenido.setContenido(resultados);
			response = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Codigo");
		}
		return response;
	}
	
	
	@PostMapping("/Alta")
	public ResponseEntity<?> getRegistro(@RequestBody RegistroDto registrodto){
		
		LOG.info("getRegistro - getRegistro() Method");
		ResponseEntity<?> response= null;	    
		try {
			String altausuario = new String();
			altausuario = AltaUsuario.AltaUsuario(registrodto);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Codigo");
			responseContenido.setContenido(altausuario);
			response = new ResponseEntity<>(responseContenido, HttpStatus.OK);
			
		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Codigo");
		}
		
		
		return response;
	}
	
	@PostMapping("/Correo")
	public ResponseEntity<?> getCorreo(@RequestBody RegistroDto request) {

		LOG.info("getCorreoComprobacion - getCorreoComprobacion() Method");
		ResponseEntity<?> response = null;
		String correo = request.getCorreo();
		try {
			String  Comprobacion = new String();
			Comprobacion = CorreoDAo.getCorreo(correo);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Codigo");
			responseContenido.setContenido(Comprobacion+" Este correo ya existe");
			response = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Codigo");
			responseContenido.setContenido(correo+" Este correo no existe");
			response = new ResponseEntity<>(responseContenido, HttpStatus.OK);
		}
		return response;
	}
	
	
	
}
