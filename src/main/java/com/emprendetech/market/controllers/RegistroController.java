package com.emprendetech.market.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.entitys.Emprendimientos;
import com.emprendetech.market.entitys.Personas;
import com.emprendetech.market.entitys.Usuario;
import com.emprendetech.market.repositorys.EmprendimientosRepository;
import com.emprendetech.market.repositorys.PersonasRepository;
import com.emprendetech.market.repositorys.UsuarioRepository;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.requestDto.RegistroDto;
import com.emprendetech.market.service.responseDto.PlataformaDao;
import com.emprendetech.market.utils.Constantes;
import com.emprendetech.market.utils.Utils;

import lombok.Data;

@Data
@Controller
public class RegistroController {

	private static final Log LOG = LogFactory.getLog(RegistroController.class);

	@Autowired
	private PlataformaDao perfilDao;
	@Autowired
	private PersonasRepository personasRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private EmprendimientosRepository emprendimientosRepository;

	public String AltaUsuario(@RequestBody RegistroDto registroDto) throws Exception {
		LOG.info("createAlta Registro - createAlta Registro() Method");

		String responseAltaUsuario = null;
		try {
			Personas personasInsert = new Personas();
			personasInsert.setNombre(registroDto.getNombre());
			personasInsert.setApellido_materno(registroDto.getApellido_materno());
			personasInsert.setApellido_paterno(registroDto.getApellido_paterno());
			personasInsert.setTelefono(registroDto.getTelefono());
			personasInsert.setDireccion(registroDto.getDireccion());
			personasInsert.setReferencia(registroDto.getReferencia());
			personasInsert.setCreadoridusuario(registroDto.getCreadoridusuario());
			personasInsert.setId_codigo_postal(registroDto.getId_codigo_postal());

			Utils util = new Utils();
			personasInsert.setFechacreacion(util.currentDate());
			personasInsert.setFechamodificacion(util.currentDate());
			LOG.info("createAltaUsuario - createAltaUsuario() Method " + personasInsert.toString());

			personasInsert = personasRepository.save(personasInsert);

			String NombreUsuario = registroDto.getNombre() + " " + registroDto.getApellido_paterno();

			if (personasRepository.existsById(personasInsert.getIdpersona())) {

				LOG.info("createAlta Usuario - createAlta Usuario() Method " + personasInsert.getIdpersona());

				Usuario usuarioInsert = new Usuario();


				usuarioInsert.setIdpersona(personasInsert.getIdpersona());
				usuarioInsert.setContrasena(registroDto.getContrasena());
				usuarioInsert.setCorreo(registroDto.getCorreo());
				usuarioInsert.setIdperfil(registroDto.getIdperfil());
				usuarioInsert.setNombreusuario(NombreUsuario);
				usuarioInsert.setCreadoridusuario(registroDto.getCreadoridusuario());
				usuarioInsert.setFechacreacion(util.currentDate());
				usuarioInsert.setFechamodificacion(util.currentDate());
			
				usuarioInsert = usuarioRepository.save(usuarioInsert);

				responseAltaUsuario = Constantes.FELICIDADESALTAUSUARIO+ usuarioInsert.getNombreusuario();
				LOG.info(personasInsert.getIdpersona());
			}

			Integer perfilCom = perfilDao.getEmp(registroDto.getIdperfil());
			boolean sonIguales = (perfilCom == 3);
			LOG.info(perfilCom);
			if (sonIguales) {
				LOG.info("createAltaEmprendimiento - createAltaEmprendimiento() Method "
						+ personasInsert.getIdpersona());
				LOG.debug("createAlta Emprendimiento Error:: " + personasInsert.getIdpersona());

				Emprendimientos emprendimientoInsert = new Emprendimientos();

				emprendimientoInsert.setIdpersona(personasInsert.getIdpersona());
				emprendimientoInsert.setNombre(registroDto.getNombre_empren());
				emprendimientoInsert.setDescripcion(registroDto.getDescripcion());
				emprendimientoInsert.setIndustria(registroDto.getIndustria());
				emprendimientoInsert.setCreadoridusuario(registroDto.getCreadoridusuario());
				emprendimientoInsert.setFechacreacion(util.currentDate());
				emprendimientoInsert.setFechamodificacion(util.currentDate());
				emprendimientoInsert = emprendimientosRepository.save(emprendimientoInsert);

				responseAltaUsuario = Constantes.FELICIDADES + personasInsert.getNombre() + Constantes.FELICIDADESALTAEMPRENDIMIENTO+ emprendimientoInsert.getNombre();
				LOG.info(emprendimientoInsert.getNombre());
			}

		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", registroDto.toString());
		}
		return responseAltaUsuario;
	}
	
	public String actualizarPersonas(@RequestBody Personas personas) throws Exception {
		LOG.info("update Personas - update Personas() Method");

		String response = null;

		try {
			Personas PersonasExistente = personasRepository.findById(personas.getIdpersona()).orElseThrow();

			PersonasExistente.setNombre(personas.getNombre());
			PersonasExistente.setApellido_materno(personas.getApellido_materno());
			PersonasExistente.setApellido_paterno(personas.getApellido_paterno());
			PersonasExistente.setTelefono(personas.getTelefono());
			PersonasExistente.setDireccion(personas.getDireccion());
			PersonasExistente.setReferencia(personas.getReferencia());
			PersonasExistente.setId_codigo_postal(personas.getId_codigo_postal());
			Utils util = new Utils();
			PersonasExistente.setFechamodificacion(util.currentDate());

			LOG.info("update personas - update personas() Method " + PersonasExistente.toString());

			PersonasExistente = personasRepository.save(PersonasExistente);

			response = Constantes.FELICIDADESACTUALIZARPERSONAS  + PersonasExistente.getNombre() ;
		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", personas.toString());
		}
		return response;
	}	
	
	    public String actualizarUsuario(@RequestBody Usuario usuario) throws Exception {
		LOG.info("update Usuario - update Usuario() Method");

		String response = null;

		try {
			Usuario UsuarioExistente = usuarioRepository.findById(usuario.getIdusuario()).orElseThrow();

			UsuarioExistente.setIdperfil(usuario.getIdperfil());
			UsuarioExistente.setNombreusuario(usuario.getNombreusuario());
	
			Utils util = new Utils();
			UsuarioExistente.setFechamodificacion(util.currentDate());

			LOG.info("update Usuario - update Usuario() Method " + UsuarioExistente.toString());

			UsuarioExistente = usuarioRepository.save(UsuarioExistente);

			response =  Constantes.FELICIDADESACTUALIZARUSUARIO + UsuarioExistente.getNombreusuario() ;
		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", usuario.toString());
		}
		return response;
	}
	    
	    public String actualizarEmprendimientos(@RequestBody Emprendimientos emprendimientos) throws Exception {
		LOG.info("update Emprendimientos - update Emprendimientos() Method");

		String response = null;

		try {
			Emprendimientos EmprendimientosExistente = emprendimientosRepository.findById(emprendimientos.getIdemprendimiento()).orElseThrow();

			EmprendimientosExistente.setNombre(emprendimientos.getNombre());
			EmprendimientosExistente.setDescripcion(emprendimientos.getDescripcion());
			EmprendimientosExistente.setIndustria(emprendimientos.getIndustria());
	
			Utils util = new Utils();
			EmprendimientosExistente.setFechamodificacion(util.currentDate());

			LOG.info("update Emprendimientos - update Emprendimientos() Method " + EmprendimientosExistente.toString());

			EmprendimientosExistente = emprendimientosRepository.save(EmprendimientosExistente);

			response = Constantes.FELICIDADESACTUALIZAREMPRENDIMIENTOS + EmprendimientosExistente.getNombre() ;
		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", emprendimientos.toString());
		}
		return response;
	}
	

}
