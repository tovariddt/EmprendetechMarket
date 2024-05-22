package com.emprendetech.market.servicios.front.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import com.emprendetech.market.controllers.ProductoController;

import com.emprendetech.market.dao.PlataformaDao;
import com.emprendetech.market.entitys.Emprendimientos;
import com.emprendetech.market.entitys.Productos;
import com.emprendetech.market.entitys.Usuario;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.responseDto.CategoriasRespDto;
import com.emprendetech.market.service.responseDto.CompCorreoRespDto;
import com.emprendetech.market.service.responseDto.EmprendimientosRespDTO;
import com.emprendetech.market.service.responseDto.PerfilesRespDto;
import com.emprendetech.market.service.responseDto.PersonaUsuarioRespDTO;
import com.emprendetech.market.service.responseDto.PostalRespDto;
import com.emprendetech.market.service.responseDto.RolesRespDto;
import com.emprendetech.market.service.responseDto.UsuarioEmprendimientoRespDto;
import com.emprendetech.market.servicios.registro.RegistroService;

import com.emprendetech.market.service.responseDto.RolesRespDto;
import com.emprendetech.market.service.requestDto.ProductoDto;
import com.emprendetech.market.service.requestDto.RegistroDto;
import com.emprendetech.market.service.requestDto.UsuarioContraseñaDto;

import org.springframework.http.HttpStatus;

@ResponseBody
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)

@RestController
@RequestMapping(path = "/api/plataforma")
public class PlataformaServiceImpl {
	private static final Log LOG = LogFactory.getLog(RegistroService.class);

	@Autowired
	private PlataformaDao rolesDao, perfilesDao, codigoDao, correoDao, consultaDao, categoriaDao;

	@Autowired
	private RegistroController AltaUsuario;
	@Autowired
	private ProductoController AltaProducto, ActualizarProducto;

	@GetMapping("/roles")
	public ResponseEntity<?> getAllRoles() {

		LOG.info("getAllRoles - getAllRoles() Method");
		ResponseEntity<?> responseroles = null;
		List<RolesRespDto> resultroles = new ArrayList<RolesRespDto>();

		try {

			resultroles = rolesDao.getRoles();

			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Roles");
			responseContenido.setContenido(resultroles);
			responseroles = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Roles");
		}

		return responseroles;
	}

	@GetMapping("/perfiles")
	public ResponseEntity<?> getAllPerfiles() {

		LOG.info("getAllPerfiles - getAllPerfiles() Method");
		ResponseEntity<?> responseperfiles = null;
		List<PerfilesRespDto> resultperfiles = new ArrayList<PerfilesRespDto>();
		try {

			resultperfiles = perfilesDao.getPefiles();

			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Perfiles");
			responseContenido.setContenido(resultperfiles);
			responseperfiles = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Perfiles");
		}

		return responseperfiles;
	}

	
	@GetMapping("/codigopostal")
	public ResponseEntity<?> getAllCodigoPostal(@RequestBody PostalRespDto request) {

		LOG.info("getAllCodigoPostal - getAllCodigoPostal() Method");
		ResponseEntity<?> responsecodigopostal = null;
		String clave = request.getClave();
		
		List<PostalRespDto> resultcodigopostal = new ArrayList<PostalRespDto>();

		try {
			resultcodigopostal = codigoDao.getCodigo(clave);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Codigo");
			responseContenido.setContenido(resultcodigopostal);
			responsecodigopostal = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Codigo");
		}
		return responsecodigopostal;
	}

	@PostMapping("/Alta")
	public ResponseEntity<?> postRegistro(@RequestBody RegistroDto registrodto) {

		LOG.info("getRegistro - getRegistro() Method");
		ResponseEntity<?> responsealta = null;
		try {
			String altausuario = new String();
			altausuario = AltaUsuario.AltaUsuario(registrodto);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Codigo");
			responseContenido.setContenido(altausuario);
			responsealta = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Codigo");
		}

		return responsealta;
	}

	
	@GetMapping("/Correo")
	public ResponseEntity<?> getCorreo(@RequestBody Usuario request) {
		LOG.info("getCorreoComprobacion - getCorreoComprobacion() Method");

		ResponseEntity<?> responsecorreo = null;
		List<CompCorreoRespDto> resultcorreo  = new ArrayList<CompCorreoRespDto>();

		try {
			String correo = request.getCorreo();
			String Comprobacion = correoDao.getCorreo(correo);
			boolean Existe = Comprobacion == null;

			CompCorreoRespDto PruebaCorreo = new CompCorreoRespDto();
			PruebaCorreo.setCorreo(correo);
			PruebaCorreo.setValor(Existe);

			if (Comprobacion == null) {
				PruebaCorreo.setMensaje("No Existe");
			} else {
				PruebaCorreo.setMensaje("Existe");
			}

			resultcorreo.add(PruebaCorreo);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Codigo");
			responseContenido.setContenido(resultcorreo);
			responsecorreo = new ResponseEntity<>(responseContenido, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Codigo");
		}
		return responsecorreo;
	}

	
	@GetMapping("/Consulta")
	public ResponseEntity<?> getUsuarioPersona(@RequestBody UsuarioContraseñaDto CorroContraseña) {
		LOG.info("getUsuarioPersona - getUsuarioPersona() Method");

		ResponseEntity<?> responseconsulta = null;
		String correo = CorroContraseña.getCorreo();
		String contraseña = CorroContraseña.getContraseña();

		try {

			List<PersonaUsuarioRespDTO> resultpersonausuario = new ArrayList<PersonaUsuarioRespDTO>();
			resultpersonausuario = consultaDao.getUsuarioPersona(correo, contraseña);

			List<EmprendimientosRespDTO> resultemprendimientos = new ArrayList<EmprendimientosRespDTO>();
			resultemprendimientos = consultaDao.getEmprendimientos(correo, contraseña);

			UsuarioEmprendimientoRespDto usuarioemprendimiento = new UsuarioEmprendimientoRespDto();
			usuarioemprendimiento.setEmprendimiento(resultemprendimientos);
			usuarioemprendimiento.setPersonausuario(resultpersonausuario);

			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Usuario");
			responseContenido.setContenido(usuarioemprendimiento);
			responseconsulta = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Codigo");
		}
		return responseconsulta;
	}

	@GetMapping("/categorias")
	public ResponseEntity<?> getAllCategorias() {

		LOG.info("getAllCategorias - getAllCategorias() Method");
		ResponseEntity<?> responsecategorias = null;

		try {
			List<CategoriasRespDto> resultcategorias = new ArrayList<CategoriasRespDto>();

			resultcategorias = categoriaDao.getcategoria();

			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Roles");
			responseContenido.setContenido(resultcategorias);
			responsecategorias = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Roles");
		}

		return responsecategorias;
	}

	@PostMapping("/AltaProducto")
	public ResponseEntity<?> postProducto(@RequestBody ProductoDto productoDto) {

		LOG.info("getProducto - getProducto() Method");
		ResponseEntity<?> responsealtaproducto = null;

		try {

			String altaproducto = null;

			altaproducto = AltaProducto.AltaProducto(productoDto);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Codigo");
			responseContenido.setContenido(altaproducto);
			responsealtaproducto = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Codigo");
		}
		return responsealtaproducto;
	}

	@PostMapping("/ActualizarProducto")
	public ResponseEntity<?> updateProducto(@RequestBody Productos productos) {

		LOG.info("updateProducto - updateProducto() Method");
		ResponseEntity<?> responseactualizarproducto = null;

		try {

			String actualizarProducto = null;

			actualizarProducto = ActualizarProducto.actualizarProducto(productos);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Codigo");
			responseContenido.setContenido(actualizarProducto);
			responseactualizarproducto = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("error codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Codigo");
		}
		return responseactualizarproducto;
	}

}
