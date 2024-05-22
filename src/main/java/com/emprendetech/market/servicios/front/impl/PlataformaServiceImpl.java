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
		
		@GetMapping("/perfiles")
		public ResponseEntity<?> getAllPerfiles() {
	
			LOG.info("getAllPerfiles - getAllPerfiles() Method");
			ResponseEntity<?> response = null;
	
			try {
				List<PerfilesRespDto> result = new ArrayList<PerfilesRespDto>();
	
				result = perfilesDao.getPefiles();
	
				final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Perfiles");
				responseContenido.setContenido(result);
				response = new ResponseEntity<>(responseContenido, HttpStatus.OK);
	
			} catch (Exception e) {
				LOG.error("eror" + e.getStackTrace());
				final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Perfiles");
			}
	
			return response;
		}

		@GetMapping("/codigopostal")
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
		public ResponseEntity<?> postRegistro(@RequestBody RegistroDto registrodto){
			
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
	
		@GetMapping("/Correo")
		public ResponseEntity<?> getCorreo(@RequestBody Usuario request) {
		            LOG.info("getCorreoComprobacion - getCorreoComprobacion() Method");

		            ResponseEntity<?> response = null;
		           
		        	try { List<CompCorreoRespDto> result = new ArrayList<CompCorreoRespDto>();
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
		            
		            result.add(PruebaCorreo);				    	
		            final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Codigo");
		            responseContenido.setContenido(result);
		            response = new ResponseEntity<>(responseContenido, HttpStatus.OK);
		        	} catch (Exception e) {
						LOG.error("eror codigo" + e.getStackTrace());
						final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Codigo");
					}
		            return response;
		        }
	
		
		@GetMapping("/Consulta")
		public ResponseEntity<?> getUsuarioPersona(@RequestBody UsuarioContraseñaDto CorroContraseña) {
		            LOG.info("getUsuarioPersona - getUsuarioPersona() Method");

		            ResponseEntity<?> response = null;
		            String correo = CorroContraseña.getCorreo();
		            String contraseña = CorroContraseña.getContraseña();

		        	try {

			    		List<PersonaUsuarioRespDTO> result = new ArrayList<PersonaUsuarioRespDTO>();					
						result =consultaDao.getUsuarioPersona(correo, contraseña);
						
						List<EmprendimientosRespDTO> result2 = new ArrayList<EmprendimientosRespDTO>();					
						result2 =consultaDao.getEmprendimientos(correo, contraseña);
						
						UsuarioEmprendimientoRespDto usuarioemprendimiento = new UsuarioEmprendimientoRespDto();
						usuarioemprendimiento.setEmprendimiento(result2);
						usuarioemprendimiento.setPersonausuario(result);
                       
			            LOG.info(" llego al trygetUsuarioPersona - getUsuarioPersona() Method");

						final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Usuario");
						responseContenido.setContenido(usuarioemprendimiento);
						response = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		        	} catch (Exception e) {
						LOG.error("eror codigo" + e.getStackTrace());
						final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Codigo");
					}
		            return response;
		        }
		
		@GetMapping("/categorias")
		public ResponseEntity<?> getAllCategorias() {
	
			LOG.info("getAllCategorias - getAllCategorias() Method");
			ResponseEntity<?> response = null;
	
			try {
				List<CategoriasRespDto> result = new ArrayList<CategoriasRespDto>();
	
				result = categoriaDao.getcategoria();
	
				final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Roles");
				responseContenido.setContenido(result);
				response = new ResponseEntity<>(responseContenido, HttpStatus.OK);
	
			} catch (Exception e) {
				LOG.error("eror" + e.getStackTrace());
				final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Roles");
			}
	
			return response;
		}
		
		@PostMapping("/AltaProducto")
		public ResponseEntity<?> postProducto(@RequestBody ProductoDto productoDto){
			
			LOG.info("getProducto - getProducto() Method");
			ResponseEntity<?> response= null;	    
			
			try {
				
				String altaproducto = new String();
				
				altaproducto = AltaProducto.AltaProducto(productoDto);
				final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Codigo");
				responseContenido.setContenido(altaproducto);
				response = new ResponseEntity<>(responseContenido, HttpStatus.OK);
				
			} catch (Exception e) {
				LOG.error("eror codigo" + e.getStackTrace());
				final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Codigo");
			}
			return response;
		}

	
		@PostMapping("/ActualizarProducto")
		public ResponseEntity<?> updateProducto(@RequestBody Productos productos){
		    
		    LOG.info("updateProducto - updateProducto() Method");
		    ResponseEntity<?> response= null;        
		    
		    try {
		        
		        String actualizarProducto = new String();
		        
		        actualizarProducto = ActualizarProducto.actualizarProducto(productos);
		        final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Codigo");
		        responseContenido.setContenido(actualizarProducto);
		        response = new ResponseEntity<>(responseContenido, HttpStatus.OK);
		        
		    } catch (Exception e) {
		        LOG.error("error codigo" + e.getStackTrace());
		        final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Codigo");
		    }
		    return response;
		}
		
		


	
}
