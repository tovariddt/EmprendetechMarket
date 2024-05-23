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
import com.emprendetech.market.service.responseDto.ProductosRespDto;
import com.emprendetech.market.service.responseDto.RolesRespDto;
import com.emprendetech.market.service.responseDto.UsuarioEmprendimientoRespDto;
import com.emprendetech.market.servicios.registro.RegistroService;

import com.emprendetech.market.service.responseDto.RolesRespDto;
import com.emprendetech.market.service.requestDto.ProductoDto;
import com.emprendetech.market.service.requestDto.RegistroDto;
import com.emprendetech.market.service.requestDto.UsuarioContrasenaDto;

import org.springframework.http.HttpStatus;

@ResponseBody
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)

@RestController
@RequestMapping(path = "/api/plataforma")
public class PlataformaServiceImpl {
	private static final Log LOG = LogFactory.getLog(RegistroService.class);

	@Autowired
	private PlataformaDao rolesDao, perfilesDao, codigoDao, correoDao, consultaDao, categoriaDao, productoEmprendimientoDao;

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
			responseroles = new ResponseEntity<>(responseContenido, HttpStatus.OK);

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
			responseperfiles = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}

		return responseperfiles;
	}

	
	@GetMapping("/codigopostal")
	public ResponseEntity<?> getAllCodigoPostal(@RequestBody PostalRespDto postalrespDto) {

		LOG.info("getAllCodigoPostal - getAllCodigoPostal() Method");
		ResponseEntity<?> responsecodigopostal = null;
		String clave = postalrespDto.getClave();
		
		List<PostalRespDto> resultcodigopostal = new ArrayList<PostalRespDto>();

		try {
			resultcodigopostal = codigoDao.getCodigo(clave);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Codigo");
			responseContenido.setContenido(resultcodigopostal);
			responsecodigopostal = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Codigo");
			responsecodigopostal = new ResponseEntity<>(responseContenido, HttpStatus.OK);

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
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Registro");
			responseContenido.setContenido(altausuario);
			responsealta = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Registro");
			responsealta = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}

		return responsealta;
	}

	
	@GetMapping("/Correo")
	public ResponseEntity<?> getCorreo(@RequestBody Usuario correodto) {
		LOG.info("getCorreoComprobacion - getCorreoComprobacion() Method");

		ResponseEntity<?> responsecorreo = null;
		List<CompCorreoRespDto> resultcorreo  = new ArrayList<CompCorreoRespDto>();

		try {
			String correo = correodto.getCorreo();
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
	public ResponseEntity<?> getUsuarioPersona(@RequestBody UsuarioContrasenaDto CorroContrasena) {
		LOG.info("getUsuarioPersona - getUsuarioPersona() Method");

		ResponseEntity<?> responseconsulta = null;
		try {
		String correo = CorroContrasena.getCorreo();
		String contrasena = CorroContrasena.getContrasena();
			List<PersonaUsuarioRespDTO> resultpersonausuario = new ArrayList<PersonaUsuarioRespDTO>();
			resultpersonausuario = consultaDao.getUsuarioPersona(correo, contrasena);

			List<EmprendimientosRespDTO> resultemprendimientos = new ArrayList<EmprendimientosRespDTO>();
			resultemprendimientos = consultaDao.getEmprendimientos(correo, contrasena);

			UsuarioEmprendimientoRespDto usuarioemprendimiento = new UsuarioEmprendimientoRespDto();
			usuarioemprendimiento.setEmprendimiento(resultemprendimientos);
			usuarioemprendimiento.setPersonausuario(resultpersonausuario);

			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Consulta Usuario");
			responseContenido.setContenido(usuarioemprendimiento);
			responseconsulta = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Consulta Usuario");
			responseconsulta = new ResponseEntity<>(responseContenido, HttpStatus.OK);
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

			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Categorias");
			responseContenido.setContenido(resultcategorias);
			responsecategorias = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Categorias");
			responsecategorias = new ResponseEntity<>(responseContenido, HttpStatus.OK);

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
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "AltaProducto");
			responseContenido.setContenido(altaproducto);
			responsealtaproducto = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "AltaProducto");
			responsealtaproducto = new ResponseEntity<>(responseContenido, HttpStatus.OK);

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
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "ActualizarProducto");
			responseContenido.setContenido(actualizarProducto);
			responseactualizarproducto = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("error codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "ActualizarProducto");
			responseactualizarproducto = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responseactualizarproducto;
	}
	
	@GetMapping("/productoEmprendimiento")
	public ResponseEntity<?> getproductoEmprendimiento(@RequestBody ProductosRespDto idemprendimientos) {

		LOG.info("getproductoEmprendimiento - getproductoEmprendimiento() Method");
		ResponseEntity<?> responseproductoEmprendimiento = null;
		Integer idemprendimiento = idemprendimientos.getIdemprendimiento();

		try {
		
		List<ProductosRespDto> resultproductosRespDto = new ArrayList<ProductosRespDto>();

		resultproductosRespDto=productoEmprendimientoDao.getproductoEmprendimiento(idemprendimiento);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "ProductoEmprendimiento");
			responseContenido.setContenido(resultproductosRespDto);
			responseproductoEmprendimiento = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "ProductoEmprendimiento");
			responseproductoEmprendimiento = new ResponseEntity<>(responseContenido, HttpStatus.OK);
		}
		return responseproductoEmprendimiento;
	}


}
