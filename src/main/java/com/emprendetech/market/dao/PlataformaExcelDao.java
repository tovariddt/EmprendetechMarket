package com.emprendetech.market.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.emprendetech.market.service.responseDto.DetallesPedidoEmprendimientoRespDto;
import com.emprendetech.market.service.responseDto.IventarioEmprendimientoRespDto;
import com.emprendetech.market.service.responseDto.IventarioRespDto;
import com.emprendetech.market.service.responseDto.PedidosFechaRespDto;
import com.emprendetech.market.service.responseDto.ProductoEmprendimientoRespDto;
import com.emprendetech.market.service.responseDto.UnidadPorFechaRespDto;
import com.emprendetech.market.service.responseDto.VentasFechaRespDto;
import com.emprendetech.market.utils.Constantes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public List<DetallesPedidoEmprendimientoRespDto> obtenerProductosEmprendimientosIDyPorfecha(String idEmprendimiento, String fechamin, String fechamax) {
        List<DetallesPedidoEmprendimientoRespDto> resultProductosEmprendimientosPorFecha = new ArrayList<>();
        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            conn = jdbcTemplate.getDataSource().getConnection();
            String storedProc = Constantes.SQLCALLGETDETALLEVENTAPOREMPRENDIMIENTO; 
            cstmt = conn.prepareCall(storedProc);
            
            cstmt.setString(1, idEmprendimiento); 
            cstmt.setString(2, fechamin); 
            cstmt.setString(3, fechamax);
            
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                DetallesPedidoEmprendimientoRespDto producto = new DetallesPedidoEmprendimientoRespDto();
                
                producto.setIdventa(rs.getInt("idventa"));
                producto.setFechacreacion(rs.getTimestamp("fechacreacion"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setNombreUnidad(rs.getString("nombreUnidad"));
                producto.setIdmedida(rs.getString("idmedida"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setIdproducto(rs.getInt("idproducto"));
                producto.setSku(rs.getString("sku"));
                producto.setNombreProducto(rs.getString("nombreProducto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setIdcategoria(rs.getInt("idcategoria"));
                producto.setSubtotal(rs.getDouble("subtotal"));

                resultProductosEmprendimientosPorFecha.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return resultProductosEmprendimientosPorFecha;
    }
    public List<IventarioEmprendimientoRespDto> obtenerIventarioEmprendimientos(String idEmprendimiento) {
        List<IventarioEmprendimientoRespDto> resultIventarioEmprendimiento = new ArrayList<>();
        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            conn = jdbcTemplate.getDataSource().getConnection();
            String storedProc = Constantes.SQLIVENTARIOPOREMPRENDIMIENTO; 
            cstmt = conn.prepareCall(storedProc);
            
            cstmt.setString(1, idEmprendimiento); 

            
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
            	IventarioEmprendimientoRespDto producto = new IventarioEmprendimientoRespDto();
                
                producto.setIdproducto(rs.getInt("idproducto"));
                producto.setNombre(rs.getString("nombre")); 
                producto.setSku(rs.getString("sku"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setIdcategoria(rs.getInt("idcategoria"));
                producto.setNombreUnidad(rs.getString("nombreUnidad"));
                producto.setIdmedida(rs.getInt("idmedida"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setCantidadRestante(rs.getInt("CantidadRestante"));

                resultIventarioEmprendimiento.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return resultIventarioEmprendimiento;
    }
    public List<IventarioRespDto> obtenerIventario() {
        List<IventarioRespDto> resultIventario = new ArrayList<>();
        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            conn = jdbcTemplate.getDataSource().getConnection();
            String storedProc = Constantes.SQLIVENTARIOGENERAL; 
            cstmt = conn.prepareCall(storedProc);
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                IventarioRespDto producto = new IventarioRespDto();
                
                producto.setIdproducto(rs.getInt("idproducto"));
                producto.setNombre(rs.getString("nombre")); 
                producto.setSku(rs.getString("sku"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setIdcategoria(rs.getInt("idcategoria"));
                producto.setNombreUnidad(rs.getString("nombreUnidad"));
                producto.setNombreemprendimiento(rs.getString("nombreEmprendimiento"));
                producto.setIdmedida(rs.getInt("idmedida"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setCantidadRestante(rs.getInt("CantidadRestante"));

                resultIventario.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return resultIventario;
    }

    public List<VentasFechaRespDto> obtenerVentasPorfecha(String fechamin, String fechamax) {
        List<VentasFechaRespDto> resultVentasPorFecha = new ArrayList<>();
        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            conn = jdbcTemplate.getDataSource().getConnection();
            String storedProc = Constantes.SQLVENTAPORFECHA; 
            cstmt = conn.prepareCall(storedProc);
            
            cstmt.setString(1, fechamin); 
            cstmt.setString(2, fechamax);
            
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
            	VentasFechaRespDto ventas = new VentasFechaRespDto();
            	ventas.setIdventa(rs.getInt("idventa"));
            	ventas.setFechaventa(rs.getTimestamp("fechaventa"));
            	ventas.setEstatus(rs.getString("estatus"));
            	ventas.setTipo(rs.getString("tipo"));
            	ventas.setTotal(rs.getDouble("total"));
            	ventas.setCreadoridusuario(rs.getInt("creadoridusuario"));


                resultVentasPorFecha.add(ventas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return resultVentasPorFecha;
    }
    
    public List<PedidosFechaRespDto> obtenerPedidosPorfecha(String fechamin, String fechamax) {
        List<PedidosFechaRespDto> resultPedidosPorFecha = new ArrayList<>();
        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            conn = jdbcTemplate.getDataSource().getConnection();
            String storedProc = Constantes.SQLPEDIDOPORFECHA; 
            cstmt = conn.prepareCall(storedProc);
            
            cstmt.setString(1, fechamin); 
            cstmt.setString(2, fechamax);
            
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
            	PedidosFechaRespDto pedidos = new PedidosFechaRespDto();
            	pedidos.setIdventa(rs.getInt("idventa"));
            	pedidos.setFechapedido(rs.getTimestamp("fechapedido"));
            	pedidos.setVentas_estatus(rs.getString("ventas_estatus"));
            	pedidos.setTipo(rs.getString("tipo"));
            	pedidos.setTotal(rs.getDouble("total"));
            	pedidos.setPedidos_estatus(rs.getString("pedidos_estatus"));
            	pedidos.setNombre(rs.getString("nombre"));
            	pedidos.setApellido_materno(rs.getString("apellido_materno"));
            	pedidos.setApellido_paterno(rs.getString("apellido_paterno"));
            	pedidos.setDireccion(rs.getString("direccion"));
            	pedidos.setReferencia(rs.getString("referencia"));
            	pedidos.setTelefono(rs.getString("telefono"));
            	pedidos.setCreadoridusuario(rs.getInt("creadoridusuario"));


            	resultPedidosPorFecha.add(pedidos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return resultPedidosPorFecha;
    }

    
    public List<UnidadPorFechaRespDto> obtenerUnidadPorfecha(String fechamin, String fechamax) {
        List<UnidadPorFechaRespDto> resultUnidadPorFecha = new ArrayList<>();
        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            conn = jdbcTemplate.getDataSource().getConnection();
            String storedProc = Constantes.SQLUNIDADPORFECHA; 
            cstmt = conn.prepareCall(storedProc);
            
            cstmt.setString(1, fechamin); 
            cstmt.setString(2, fechamax);
            
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
            	UnidadPorFechaRespDto unidad = new UnidadPorFechaRespDto();
            	unidad.setIdventa(rs.getInt("idventa"));
            	unidad.setFechacreacion(rs.getTimestamp("fechacreacion"));
            	unidad.setCantidad(rs.getInt("cantidad"));
            	unidad.setNombreUnidad(rs.getString("nombreUnidad"));
            	unidad.setIdmedida(rs.getString("idmedida"));
            	unidad.setPrecio(rs.getDouble("precio"));
            	unidad.setIdproducto(rs.getInt("idproducto"));
            	unidad.setSku(rs.getString("sku"));
            	unidad.setNombreProducto(rs.getString("nombreProducto"));
            	unidad.setDescripcion(rs.getString("descripcion"));
            	unidad.setIdcategoria(rs.getInt("idcategoria"));
            	unidad.setSubtotal(rs.getDouble("subtotal"));
            	unidad.setEstatus(rs.getString("estatus"));
            	unidad.setTipo(rs.getString("tipo"));


            	resultUnidadPorFecha.add(unidad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return resultUnidadPorFecha;
    }
}
