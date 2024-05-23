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
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.requestDto.RegistroDto;
import com.emprendetech.market.utils.Utils;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
		LOG.info("createAlta - createAlta() Method");
		LOG.debug("createAlta :: " + registroDto.toString());

		String responseAltaUsuario = null;
		try {
			Personas personasInsert = new Personas();
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
			LOG.info("createAltaUsuario - createAltaUsuario() Method " + personasInsert.toString());

			personasInsert = personasRepository.save(personasInsert);

			if (personasRepository.existsById(personasInsert.getIdpersona())) {

				LOG.info("createAltaUsuario - createAltaUsuario() Method " + personasInsert.getIdpersona());
				LOG.debug("createAlta Usuario Error:: " + personasInsert.getIdpersona());

				Usuario usuarioInsert = new Usuario();

				String NombreUsuario = registroDto.getNombre() + " " + registroDto.getApellido_paterno();

				usuarioInsert.setIdpersona(personasInsert.getIdpersona());
				usuarioInsert.setContraseña(registroDto.getContraseña());
				usuarioInsert.setCorreo(registroDto.getCorreo());
				usuarioInsert.setIdrol(registroDto.getIdrol());
				usuarioInsert.setIdperfil(registroDto.getIdperfil());
				usuarioInsert.setNombreusuario(NombreUsuario);
				usuarioInsert.setCreadoridusuario(registroDto.getCreadoridusuario());
				usuarioInsert.setFechacreacion(util.currentDate());
				usuarioInsert.setFechamodificacion(util.currentDate());
				usuarioInsert = usuarioRepository.save(usuarioInsert);

				responseAltaUsuario = "Felicidades fue registrado como=" + usuarioInsert.getNombreusuario();
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

				responseAltaUsuario = "Felicidades " + personasInsert.getNombre() + " Su emprendimiento fue registrado como = "
						+ emprendimientoInsert.getNombre();
				LOG.info(emprendimientoInsert.getNombre());
			}

		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", registroDto.toString());
		}
		return responseAltaUsuario;
	}

}
