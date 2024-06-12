package com.emprendetech.market.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.emprendetech.market.service.responseDto.ProductoEmprendimientoRespDto;
import com.emprendetech.market.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlataformaExcelDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ProductoEmprendimientoRespDto> obtenerProductosEmprendimientos() {
        try {
            List<ProductoEmprendimientoRespDto> resultProductosEmprendimientos = new ArrayList<>();

            String sql = Constantes.SQLOBTENERPRODUCTOSEMPRENDIMIENTOS;
            return jdbcTemplate.query(sql,(rs, rowNum) -> {
            	ProductoEmprendimientoRespDto productoEmprendimiento = new ProductoEmprendimientoRespDto();
                productoEmprendimiento.setIdEmprendimiento(rs.getInt("idemprendimiento"));
                productoEmprendimiento.setNombreEmprendimiento(rs.getString("nombreEmprendimiento"));
                productoEmprendimiento.setIdProducto(rs.getInt("idproducto"));
                productoEmprendimiento.setSku(rs.getString("sku"));
                productoEmprendimiento.setNombreProducto(rs.getString("nombreProducto"));
                productoEmprendimiento.setIdCategoria(rs.getInt("idcategoria"));
                productoEmprendimiento.setDescripcion(rs.getString("descripcion"));
                resultProductosEmprendimientos.add(productoEmprendimiento);

                return productoEmprendimiento;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    public List<ProductoEmprendimientoRespDto> obtenerProductosEmprendimientosID(String idEmprendimiento) {
        try {
            List<ProductoEmprendimientoRespDto> resultProductosEmprendimientos = new ArrayList<>();

            String sql = Constantes.SQLOBTENERPRODUCTOSEMPRENDIMIENTOSID+idEmprendimiento+"'; " ;

            return jdbcTemplate.query(sql,  (rs, rowNum) -> {
            	ProductoEmprendimientoRespDto productoEmprendimiento = new ProductoEmprendimientoRespDto();
                productoEmprendimiento.setIdEmprendimiento(rs.getInt("idemprendimiento"));
                productoEmprendimiento.setNombreEmprendimiento(rs.getString("nombreEmprendimiento"));
                productoEmprendimiento.setIdProducto(rs.getInt("idproducto"));
                productoEmprendimiento.setSku(rs.getString("sku"));
                productoEmprendimiento.setNombreProducto(rs.getString("nombreProducto"));
                productoEmprendimiento.setIdCategoria(rs.getInt("idcategoria"));
                productoEmprendimiento.setDescripcion(rs.getString("descripcion"));
                resultProductosEmprendimientos.add(productoEmprendimiento);

                return productoEmprendimiento;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    
}
