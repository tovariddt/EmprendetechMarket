package com.emprendetech.market.service.responseDto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.emprendetech.market.utils.Constantes;

@Repository
public class PlataformaDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Log LOG = LogFactory.getLog(PlataformaDao.class);

	public List<RolesRespDto> getRoles() {
	    try {
	        String sqlroles = Constantes.SQLGETROLES;
	        return jdbcTemplate.query(sqlroles, (rs, rowNum) -> {
	            RolesRespDto dtoroles = new RolesRespDto();
	            dtoroles.setIdrol(rs.getInt("idrol"));
	            dtoroles.setNombre(rs.getString("nombre"));
	            return dtoroles;
	        });
	    } catch (EmptyResultDataAccessException e) {
	        return null;
	    }
	}


	public List<PerfilesRespDto> getPefiles() {
		try {
	        String sqlperfiles = Constantes.SQLGETPEFILES;
	        return jdbcTemplate.query(sqlperfiles, (rs, rowNum) -> {
	            PerfilesRespDto dtoperfiles = new PerfilesRespDto();
	            dtoperfiles.setIdperfil(rs.getInt("idperfil"));
	            dtoperfiles.setNombre(rs.getString("nombre"));
	            dtoperfiles.setDescripcion(rs.getNString("descripcion"));
	            dtoperfiles.setIdrol(rs.getInt("idrol"));
	            return dtoperfiles;
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	

	public List<PostalRespDto> getCodigo(String clave) {
		try {
			String sqlcodigo_postal = Constantes.SQLGETCODIGO + clave + ";";
			return jdbcTemplate.query(sqlcodigo_postal,(rs, rowNum) -> {
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
			String sqlcorreo = Constantes.SQLGETCORREO + Correo + "';";
			return jdbcTemplate.queryForObject(sqlcorreo,(rs, rowNum) -> {
				return rs.getString("correo"); 
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public int getEmp(Integer Perfilemp) {
		try {
			String sqlperfiles = Constantes.SQLGETEMP+ Perfilemp + ";";
			return jdbcTemplate.queryForObject(sqlperfiles, (rs, rowNum) -> {
				return rs.getInt("idperfil"); // Aquí puedes seleccionar la columna que deseas retornar
			});
		} catch (EmptyResultDataAccessException e) {
			return (Integer) null;
		}
	}

	
	public List<PersonaUsuarioRespDTO> getUsuarioPersona(String correo, String contrasena) {
		try {
			String sqlusuariopersona = Constantes.SQLGETUSUARIOPERSONA + contrasena + Constantes.SQLGETUSUARIOPERSONACORREO + correo + "';";
			
			return jdbcTemplate.query(sqlusuariopersona,(rs, rowNum) -> {
				PersonaUsuarioRespDTO dtousuariopersona = new PersonaUsuarioRespDTO();
				dtousuariopersona.setNombreusuario(rs.getString("nombreusuario"));
				dtousuariopersona.setCorreo(rs.getString("correo"));
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

	public List<EmprendimientosRespDTO> getEmprendimientos(String correo, String contrasena) {
		try {
			String sqlemprendimientos = Constantes.SQLGETEMPRENDIMIENTOS + contrasena + Constantes.SQLGETEMPRENDIMIENTOSCORREO+ correo + "';";

			return jdbcTemplate.query(sqlemprendimientos,(rs, rowNum) -> {
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

			String sqlcategoria = Constantes.SQLGETCATEGORIA;

			return jdbcTemplate.query(sqlcategoria,(rs, rowNum) -> {
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

			String sqlproductos = Constantes.SQLGETPRODUCTOEMPRENDIMIENTO+ididemprendimiento+"';";
			return jdbcTemplate.query(sqlproductos, (rs, rowNum) -> {
			
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
	public List<ProductosRespDto> getproductoEmprendimientoCategoria(Integer ididemprendimiento , Integer idcategoria) {
		try {

			String sqlproductosCategoria = Constantes.SQLGETPRODUCTOEMPRENDIMIENTOCATEGORIA+ididemprendimiento+Constantes.SQLGETPRODUCTOEMPRENDIMIENTOCATEGORIAID+idcategoria+"';";
			return jdbcTemplate.query(sqlproductosCategoria,(rs, rowNum) -> {
			
				ProductosRespDto dtoproductosCategoria = new ProductosRespDto();
				dtoproductosCategoria.setSku(rs.getString("sku"));
				dtoproductosCategoria.setNombre(rs.getString("nombre"));
				dtoproductosCategoria.setDescripcion(rs.getString("descripcion"));
				dtoproductosCategoria.setImagen(rs.getString("imagen"));
				dtoproductosCategoria.setCantidad_disponible(rs.getInt("cantidad_disponible"));
				dtoproductosCategoria.setIdcategoria(rs.getInt("idcategoria"));
				dtoproductosCategoria.setIdemprendimiento(rs.getInt("idemprendimiento"));
				return dtoproductosCategoria;
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<ProductosRespDto> getproductoEmprendimientoNombreLIKE(Integer ididemprendimiento , String nombre) {
		try {

			String sqlproductosNombreLIKE = Constantes.SQLGETPRODUCTOEMPRENDIMIENTONOMBRELIKE+nombre+Constantes.SQLGETPRODUCTOEMPRENDIMIENTONOMBRELIKEAND+ididemprendimiento+";";
			return jdbcTemplate.query(sqlproductosNombreLIKE,  (rs, rowNum) -> {
			
				ProductosRespDto dtoproductosNombreLIKE = new ProductosRespDto();
				dtoproductosNombreLIKE.setSku(rs.getString("sku"));
				dtoproductosNombreLIKE.setNombre(rs.getString("nombre"));
				dtoproductosNombreLIKE.setDescripcion(rs.getString("descripcion"));
				dtoproductosNombreLIKE.setImagen(rs.getString("imagen"));
				dtoproductosNombreLIKE.setCantidad_disponible(rs.getInt("cantidad_disponible"));
				dtoproductosNombreLIKE.setIdcategoria(rs.getInt("idcategoria"));
				dtoproductosNombreLIKE.setIdemprendimiento(rs.getInt("idemprendimiento"));
				return dtoproductosNombreLIKE;
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
