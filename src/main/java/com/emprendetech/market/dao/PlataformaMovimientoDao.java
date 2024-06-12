package com.emprendetech.market.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.emprendetech.market.service.requestDto.AltaDetallesVentaDto;
import com.emprendetech.market.service.requestDto.AltaVentasDto;
import com.emprendetech.market.utils.Constantes;
@Repository
public class PlataformaMovimientoDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

	private static final Log LOG = LogFactory.getLog(PlataformaMovimientoDao.class);

	public BigDecimal getTotalVentas(Integer idVenta) {
	    try {
	        String sqlVentaTotal = Constantes.SQLOBTENERTOTALVENTA;
	        return jdbcTemplate.queryForObject(sqlVentaTotal, new Object[]{idVenta}, (rs, rowNum) -> rs.getBigDecimal("totalventa"));
	    } catch (EmptyResultDataAccessException e) {
	        return BigDecimal.ZERO;
	    }
	}

	
	public List<AltaVentasDto> getMovimientoVenta(Integer idventa) {
		try {
			String sqlunaventa = Constantes.SQLOBTENERUNAVENTA + idventa +";";
			
			return jdbcTemplate.query(sqlunaventa,(rs, rowNum) -> {
				AltaVentasDto dtounaventa = new AltaVentasDto();
				dtounaventa.setIdventa(rs.getInt("idventa"));
				dtounaventa.setIdmetodospago(rs.getInt("idmetodospago"));
				dtounaventa.setFechaventa(rs.getDate("fechaventa"));
				dtounaventa.setEstatus(rs.getNString("estatus"));
				dtounaventa.setTipo(rs.getString("tipo"));
				dtounaventa.setTotal(rs.getFloat("total"));
				dtounaventa.setCreadoridusuario(rs.getInt("creadoridusuario"));

				return dtounaventa; // Aquí puedes seleccionar la columna que deseas retornar
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<AltaDetallesVentaDto> getDelleventa(Integer idventa) {
		try {
			String sqlunaventa = Constantes.SQLOBTENERDETALLEVENTA + idventa +";";
			
			return jdbcTemplate.query(sqlunaventa,(rs, rowNum) -> {
				AltaDetallesVentaDto detallesventaDto = new AltaDetallesVentaDto();
				detallesventaDto.setIdproductounidad(rs.getInt("idproductounidad"));
				detallesventaDto.setNombre(rs.getNString("nombre"));
				detallesventaDto.setPrecio(rs.getFloat("precio"));	
				detallesventaDto.setCantidad(rs.getInt("cantidad"));
				detallesventaDto.setSubtotal(rs.getFloat("subtotal"));
				return detallesventaDto; 
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public Float getPrecioProductoUnidad(Integer idproductounidad ) {
	    try {
	        String sqlPrecioProductoUnidad = Constantes.SQLOBTENERPRECIOPRODUCTOUNIDAD+idproductounidad+";";
				return (Float) jdbcTemplate.queryForObject(sqlPrecioProductoUnidad,(rs, rowNum) -> {
					return rs.getObject("precio"); 
				});
			} catch (EmptyResultDataAccessException e) {
				return null;
	    }
	}
	
		public Integer getCantidadProductoUnidad(Integer idproductounidad ) {
		    try {
		        String sqlCantidadProductoUnidad= Constantes.SQLOBTENERCANTIDADPRODUCTOUNIDAD+idproductounidad+";";
					return  (Integer) jdbcTemplate.queryForObject(sqlCantidadProductoUnidad,(rs, rowNum) -> {
						return rs.getObject("cantidad"); 
					});
				} catch (EmptyResultDataAccessException e) {
					return null;
		    }
	}
		
		
		public Integer getCliente(Integer idcliente) {
			try {
				String sqlcorreo = Constantes.SQLIDCLIENTE + idcliente + ";";
				return (Integer) jdbcTemplate.queryForObject(sqlcorreo,(rs, rowNum) -> {
					return rs.getObject("idcliente"); 
				});
			} catch (EmptyResultDataAccessException e) {
				return null;
			}
		}
}
