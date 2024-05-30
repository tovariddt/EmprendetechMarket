package com.emprendetech.market.excel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductoEmprendimientoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ProductoEmprendimiento> obtenerProductosEmprendimientos() {
        try {
            List<ProductoEmprendimiento> resultProductosEmprendimientos = new ArrayList<>();

            String sql = "SELECT emprendetech_market.emprendimientos.idemprendimiento, emprendetech_market.emprendimientos.nombre as nombreEmprendimiento, emprendetech_market.productos.idproducto, emprendetech_market.productos.sku, emprendetech_market.productos.nombre as nombreProducto, emprendetech_market.productos.idcategoria, emprendetech_market.productos.descripcion, emprendetech_market.productos.cantidad_disponible FROM emprendetech_market.productos INNER JOIN emprendetech_market.emprendimientos ON emprendetech_market.productos.idemprendimiento = emprendetech_market.emprendimientos.idemprendimiento;";

            return jdbcTemplate.query(sql, new Object[]{}, (rs, rowNum) -> {
                ProductoEmprendimiento productoEmprendimiento = new ProductoEmprendimiento();
                productoEmprendimiento.setIdEmprendimiento(rs.getInt("idemprendimiento"));
                productoEmprendimiento.setNombreEmprendimiento(rs.getString("nombreEmprendimiento"));
                productoEmprendimiento.setIdProducto(rs.getInt("idproducto"));
                productoEmprendimiento.setSku(rs.getString("sku"));
                productoEmprendimiento.setNombreProducto(rs.getString("nombreProducto"));
                productoEmprendimiento.setIdCategoria(rs.getInt("idcategoria"));
                productoEmprendimiento.setDescripcion(rs.getString("descripcion"));
                productoEmprendimiento.setCantidadDisponible(rs.getInt("cantidad_disponible"));
                resultProductosEmprendimientos.add(productoEmprendimiento);

                return productoEmprendimiento;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    public List<ProductoEmprendimiento> obtenerProductosEmprendimientosID(String idEmprendimiento) {
        try {
            List<ProductoEmprendimiento> resultProductosEmprendimientos = new ArrayList<>();

            String sql = "SELECT emprendetech_market.emprendimientos.idemprendimiento, emprendetech_market.emprendimientos.nombre as nombreEmprendimiento, emprendetech_market.productos.idproducto,emprendetech_market.productos.sku ,emprendetech_market.productos.nombre  as nombreProducto, emprendetech_market.productos.idcategoria , emprendetech_market.productos.descripcion, emprendetech_market.productos.cantidad_disponible FROM emprendetech_market.productos INNER JOIN emprendetech_market.emprendimientos ON emprendetech_market.productos.idemprendimiento = emprendetech_market.emprendimientos.idemprendimiento where emprendetech_market.emprendimientos.idemprendimiento = '"+idEmprendimiento+"'; " ;

            return jdbcTemplate.query(sql, new Object[]{}, (rs, rowNum) -> {
                ProductoEmprendimiento productoEmprendimiento = new ProductoEmprendimiento();
                productoEmprendimiento.setIdEmprendimiento(rs.getInt("idemprendimiento"));
                productoEmprendimiento.setNombreEmprendimiento(rs.getString("nombreEmprendimiento"));
                productoEmprendimiento.setIdProducto(rs.getInt("idproducto"));
                productoEmprendimiento.setSku(rs.getString("sku"));
                productoEmprendimiento.setNombreProducto(rs.getString("nombreProducto"));
                productoEmprendimiento.setIdCategoria(rs.getInt("idcategoria"));
                productoEmprendimiento.setDescripcion(rs.getString("descripcion"));
                productoEmprendimiento.setCantidadDisponible(rs.getInt("cantidad_disponible"));
                resultProductosEmprendimientos.add(productoEmprendimiento);

                return productoEmprendimiento;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    
}
