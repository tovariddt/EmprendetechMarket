package com.emprendetech.market.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.service.responseDto.PostalRespDto;
import com.emprendetech.market.service.responseDto.ProductosRespDto;
import com.emprendetech.market.service.responseDto.RolesRespDto;
import com.emprendetech.market.service.responseDto.PersonaUsuarioRespDTO;
import com.emprendetech.market.entitys.Productos;
import com.emprendetech.market.service.requestDto.UsuarioContraseñaDto;
import com.emprendetech.market.service.responseDto.CategoriasRespDto;
import com.emprendetech.market.service.responseDto.EmprendimientosRespDTO;
import com.emprendetech.market.service.responseDto.PerfilesRespDto;

@Repository
public class PlataformaDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Log LOG = LogFactory.getLog(PlataformaDao.class);

	public List<RolesRespDto> getRoles() {
		try {
			List<RolesRespDto> resultroles = new ArrayList<RolesRespDto>();

			String sqlroles = "SELECT idrol, nombre FROM emprendetech_market.roles";

			return jdbcTemplate.query(sqlroles, new Object[] {}, (rs, rowNum) -> {
				RolesRespDto dtoroles = new RolesRespDto();
				dtoroles.setIdrol(rs.getInt("idrol"));
				dtoroles.setNombre(rs.getString("nombre"));
				resultroles.add(dtoroles);
				return dtoroles;
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<PerfilesRespDto> getPefiles() {
		try {
			List<PerfilesRespDto> resultperfiles = new ArrayList<PerfilesRespDto>();

			String sqlperfiles = "SELECT * FROM emprendetech_market.perfiles;";

			return jdbcTemplate.query(sqlperfiles, new Object[] {}, (rs, rowNum) -> {
				PerfilesRespDto dtoperfiles = new PerfilesRespDto();
				dtoperfiles.setIdperfil(rs.getInt("idperfil"));
				dtoperfiles.setNombre(rs.getString("nombre"));
				dtoperfiles.setDescripcion(rs.getNString("descripcion"));
				resultperfiles.add(dtoperfiles);
				return dtoperfiles;
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<PostalRespDto> getCodigo(String clave) {
		try {
			String sqlcodigo_postal = "SELECT * FROM emprendetech_market.codigo_postal where clave=" + clave + ";";
			return jdbcTemplate.query(sqlcodigo_postal, new Object[] {}, (rs, rowNum) -> {
				PostalRespDto dtocodigo_postal = new PostalRespDto();
				dtocodigo_postal.setId_codigo_postal(rs.getInt("id_codigo_postal"));
				dtocodigo_postal.setClave(rs.getString("clave"));
				dtocodigo_postal.setNombre_estado(rs.getString("nombre_estado"));
				dtocodigo_postal.setNombre_municipio(rs.getString("nombre_municipio"));
				dtocodigo_postal.setNombre_localidad(rs.getString("nombre_localidad"));
				return dtocodigo_postal;
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public String getCorreo(String Correo) {
		try {
			String sqlcorreo = "SELECT correo FROM emprendetech_market.usuario where correo='" + Correo + "';";
			return jdbcTemplate.queryForObject(sqlcorreo, new Object[] {}, (rs, rowNum) -> {
				return rs.getString("correo"); // Aquí puedes seleccionar la columna que deseas retornar
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public int getEmp(Integer Perfilemp) {
		try {
			String sqlperfiles = "SELECT idperfil FROM emprendetech_market.perfiles where idperfil=" + Perfilemp + ";";
			return jdbcTemplate.queryForObject(sqlperfiles, new Object[] {}, (rs, rowNum) -> {
				return rs.getInt("idperfil"); // Aquí puedes seleccionar la columna que deseas retornar
			});
		} catch (EmptyResultDataAccessException e) {
			return (Integer) null;
		}
	}

	
	public List<PersonaUsuarioRespDTO> getUsuarioPersona(String correo, String contraseña) {
		try {
			String sqlusuariopersona = "SELECT nombreusuario, correo ,idrol,idperfil , nombre,apellido_paterno,apellido_materno,telefono, direccion ,id_codigo_postal\r\n"
					+ "FROM emprendetech_market.usuario\r\n"
					+ "INNER JOIN emprendetech_market.personas ON emprendetech_market.usuario.idpersona = emprendetech_market.personas.idpersona\r\n"
					+ "where emprendetech_market.usuario.contraseña='" + contraseña
					+ "' and emprendetech_market.usuario.correo= '" + correo + "';";
			
			return jdbcTemplate.query(sqlusuariopersona, new Object[] {}, (rs, rowNum) -> {
				PersonaUsuarioRespDTO dtousuariopersona = new PersonaUsuarioRespDTO();
				dtousuariopersona.setNombreusuario(rs.getString("nombreusuario"));
				dtousuariopersona.setCorreo(rs.getString("correo"));
				dtousuariopersona.setIdrol(rs.getInt("idrol"));
				dtousuariopersona.setIdperfil(rs.getInt("idperfil"));
				dtousuariopersona.setNombre(rs.getString("nombre"));
				dtousuariopersona.setApellido_paterno(rs.getString("apellido_paterno"));
				dtousuariopersona.setApellido_materno(rs.getString("apellido_materno"));
				dtousuariopersona.setTelefono(rs.getString("telefono"));
				dtousuariopersona.setDireccion(rs.getString("direccion"));
				dtousuariopersona.setId_codigo_postal(rs.getInt("id_codigo_postal"));
				return dtousuariopersona; // Aquí puedes seleccionar la columna que deseas retornar
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<EmprendimientosRespDTO> getEmprendimientos(String correo, String contraseña) {
		try {
			String sqlemprendimientos = "SELECT emprendetech_market.emprendimientos.nombre,emprendetech_market.emprendimientos.descripcion, emprendetech_market.emprendimientos.industria\r\n"
					+ "FROM emprendetech_market.usuario\r\n"
					+ "INNER JOIN emprendetech_market.personas ON emprendetech_market.usuario.idpersona = emprendetech_market.personas.idpersona\r\n"
					+ "INNER JOIN emprendetech_market.emprendimientos ON emprendetech_market.usuario.idpersona = emprendetech_market.emprendimientos.idpersona\r\n"
					+ "where emprendetech_market.usuario.contraseña='" + contraseña
					+ "' and emprendetech_market.usuario.correo='" + correo + "';";

			return jdbcTemplate.query(sqlemprendimientos, new Object[] {}, (rs, rowNum) -> {
				EmprendimientosRespDTO dtoemprendimientos = new EmprendimientosRespDTO();
				dtoemprendimientos.setNombre(rs.getString("nombre"));
				dtoemprendimientos.setDescripcion(rs.getString("descripcion"));
				dtoemprendimientos.setIndustria(rs.getString("industria"));
				return dtoemprendimientos; // Aquí puedes seleccionar la columna que deseas retornar
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<CategoriasRespDto> getcategoria() {
		try {
			List<CategoriasRespDto> resultcategoria = new ArrayList<CategoriasRespDto>();

			String sqlcategoria = "SELECT * FROM emprendetech_market.categorias;";

			return jdbcTemplate.query(sqlcategoria, new Object[] {}, (rs, rowNum) -> {
				CategoriasRespDto dtocategoria = new CategoriasRespDto();
				dtocategoria.setIdcategoria(rs.getInt("idcategoria"));
				dtocategoria.setNombre(rs.getString("nombre"));
				resultcategoria.add(dtocategoria);
				return dtocategoria;
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<ProductosRespDto> getproductoEmprendimiento(Integer ididemprendimiento) {
		try {

			String sqlproductos = "SELECT * FROM emprendetech_market.productos where idemprendimiento= '"+ididemprendimiento+"';";
			return jdbcTemplate.query(sqlproductos, new Object[] {}, (rs, rowNum) -> {
			
				ProductosRespDto dtoproductos = new ProductosRespDto();
				dtoproductos.setSku(rs.getString("sku"));
				dtoproductos.setNombre(rs.getString("nombre"));
				dtoproductos.setDescripcion(rs.getString("descripcion"));
				dtoproductos.setImagen(rs.getString("imagen"));
				dtoproductos.setCantidad_disponible(rs.getInt("cantidad_disponible"));
				dtoproductos.setIdcategoria(rs.getInt("idcategoria"));
				dtoproductos.setIdemprendimiento(rs.getInt("idemprendimiento"));
				return dtoproductos;
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
