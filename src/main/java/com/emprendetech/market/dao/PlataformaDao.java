package com.emprendetech.market.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.service.responseDto.PostalRespDto;
import com.emprendetech.market.service.responseDto.RolesRespDto;

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
	        String sql2 = "SELECT correo FROM emprendetech_market.usuario where correo='"+Correo+"';";
	        return jdbcTemplate.queryForObject(sql2, new Object[] {}, (rs, rowNum) -> {
	            return rs.getString("correo"); // Aquí puedes seleccionar la columna que deseas retornar
	        });
	    }

	
}
	
