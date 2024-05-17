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
import com.emprendetech.market.service.responseDto.RolesRespDto;
import com.emprendetech.market.service.responseDto.PersonaUsuarioRespDTO;
import com.emprendetech.market.service.requestDto.UsuarioContraseñaDto;
import com.emprendetech.market.service.responseDto.EmprendimientosRespDTO;

@Repository
public class PlataformaDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final Log LOG = LogFactory.getLog(PlataformaDao.class);
	
	public List<RolesRespDto> getRoles(){	
		
		List<RolesRespDto> result= new ArrayList<RolesRespDto>() ;

			String sql =  "SELECT idrol, nombre FROM emprendetech_market.roles";
			
				 return jdbcTemplate.query(sql, new Object[] {  },(rs, rowNum) ->{
					 RolesRespDto dto = new RolesRespDto();
					 dto.setidrol(rs.getInt("idrol"));
					 dto.setnombre(rs.getString("nombre"));
					 result.add(dto);
					 return dto;
				 });	
	}		 	   
    
	    public List<PostalRespDto> getCodigo(String clave) {
		   String sql2 ="SELECT * FROM emprendetech_market.codigo_postal where clave="+clave+";";			
		   return jdbcTemplate.query(sql2, new Object[] { },(rs, rowNum) ->{
			   PostalRespDto dto2 = new PostalRespDto();
			   dto2.setId_codigo_postal(rs.getInt("id_codigo_postal"));
			   dto2.setClave(rs.getString("clave"));
			   dto2.setNombre_estado(rs.getString("nombre_estado"));
			   dto2.setNombre_municipio(rs.getString("nombre_municipio"));
			   dto2.setNombre_localidad(rs.getString("nombre_localidad"));
			   return dto2;
		   });
}	
	
	    public String getCorreo(String Correo) {
	        try {
	            String sql2 = "SELECT correo FROM emprendetech_market.usuario where correo='"+Correo+"';";
	            return jdbcTemplate.queryForObject(sql2, new Object[] {}, (rs, rowNum) -> {
	                return rs.getString("correo"); // Aquí puedes seleccionar la columna que deseas retornar
	            });
	        } catch (EmptyResultDataAccessException e) {
	            return null;
	        }
	    }
 
	    
	    public int getEmp(Integer Perfilemp) {
	        String sql3 = "SELECT idperfil FROM emprendetech_market.perfiles where idperfil="+Perfilemp+";";
	        return jdbcTemplate.queryForObject(sql3, new Object[] {}, (rs, rowNum) -> {
	            return rs.getInt("idperfil"); // Aquí puedes seleccionar la columna que deseas retornar
	        });
	    }
	    
	    
	    public List<PersonaUsuarioRespDTO> getUsuarioPersona(String correo, String contraseña) {
	            String sql4 = "SELECT nombreusuario, correo ,idrol,idperfil , nombre,apellido_paterno,apellido_materno,telefono, direccion ,id_codigo_postal\r\n"
	            		+ "FROM emprendetech_market.usuario\r\n"
	            		+ "INNER JOIN emprendetech_market.personas ON emprendetech_market.usuario.idpersona = emprendetech_market.personas.idpersona\r\n"
	            		+ "where emprendetech_market.usuario.contrasena='"+contraseña+"' and emprendetech_market.usuario.correo= '"+correo+"';";
	            return jdbcTemplate.query(sql4, new Object[] {}, (rs, rowNum) -> {
	               PersonaUsuarioRespDTO dto4 = new PersonaUsuarioRespDTO ();
				   dto4.setNombreusuario(rs.getString("nombreusuario"));
				   dto4.setCorreo(rs.getString("correo"));
				   dto4.setIdrol(rs.getInt("idrol"));
				   dto4.setIdperfil(rs.getInt("idperfil"));
				   dto4.setNombre(rs.getString("nombre"));
				   dto4.setApellido_paterno(rs.getString("apellido_paterno"));
				   dto4.setApellido_materno(rs.getString("apellido_materno"));
				   dto4.setTelefono(rs.getString("telefono"));
				   dto4.setDireccion(rs.getString("direccion"));
				   dto4.setId_codigo_postal(rs.getInt("id_codigo_postal"));
	              return dto4; // Aquí puedes seleccionar la columna que deseas retornar
	            });
	         }
	    
	    public List<EmprendimientosRespDTO> getEmprendimientos(String correo, String contraseña) {
	        try {
	            String sql5 = "SELECT emprendetech_market.emprendimientos.nombre,emprendetech_market.emprendimientos.descripcion, emprendetech_market.emprendimientos.industria\r\n"
	            		+ "FROM emprendetech_market.usuario\r\n"
	            		+ "INNER JOIN emprendetech_market.personas ON emprendetech_market.usuario.idpersona = emprendetech_market.personas.idpersona\r\n"
	            		+ "INNER JOIN emprendetech_market.emprendimientos ON emprendetech_market.usuario.idpersona = emprendetech_market.emprendimientos.idpersona\r\n"
	            		+ "where emprendetech_market.usuario.contrasena='"+contraseña+"' and emprendetech_market.usuario.correo='"+correo+"';";
	          
	            
	            return jdbcTemplate.query(sql5, new Object[] {}, (rs, rowNum) -> {
	               	EmprendimientosRespDTO dto5 = new EmprendimientosRespDTO();
	            	dto5.setNombre(rs.getString("nombre"));
	                dto5.setDescripcion(rs.getString("descripcion"));
	                dto5.setIndustria(rs.getString("industria"));
	            	return dto5; // Aquí puedes seleccionar la columna que deseas retornar
	            });
	        } catch (EmptyResultDataAccessException e) {
	            return null;
	        }
	    }

	    
	    
	
}
	
