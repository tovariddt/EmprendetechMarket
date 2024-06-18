package com.emprendetech.market.controllers;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emprendetech.market.dao.PlataformaExcelDao;
import com.emprendetech.market.service.responseDto.DetallesPedidoEmprendimientoRespDto;
import com.emprendetech.market.service.responseDto.IventarioEmprendimientoRespDto;
import com.emprendetech.market.service.responseDto.IventarioRespDto;
import com.emprendetech.market.service.responseDto.PedidosFechaRespDto;
import com.emprendetech.market.service.responseDto.ProductoEmprendimientoRespDto;
import com.emprendetech.market.service.responseDto.UnidadPorFechaRespDto;
import com.emprendetech.market.service.responseDto.VentasFechaRespDto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelController {

	private static final Log LOG = LogFactory.getLog(ExcelController.class);
	
    @Autowired
    private PlataformaExcelDao ExcelDao,IventarioExcelDao , ExcelEmprendimientoDao ,ExcelEmprendimientoPorFechaDao ,ExcelIventarioEmprendimientoDao,
    ExcelVentasPorFechaDao, ExcelPedidosFechaDao, ExcelUnidadFechaDao;

    public ByteArrayOutputStream generarExcel() throws IOException {
        ByteArrayOutputStream outputStream = null;

    	try {
    	List<ProductoEmprendimientoRespDto> listaExcelDao = ExcelDao.obtenerProductosEmprendimientos();
        List<IventarioRespDto> listaIventarioDao = IventarioExcelDao.obtenerIventario();
    

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Productos y Emprendimientos");

        // Crear el encabezado
        String[] columns = {"ID Emprendimiento", "Nombre Emprendimiento", "ID Producto", "SKU", "Nombre Producto", "ID Categoria", "Descripción"};
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Llenar las filas con los datos
        int rowNum = 1;
        for (ProductoEmprendimientoRespDto pe : listaExcelDao) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(pe.getIdEmprendimiento());
            row.createCell(1).setCellValue(pe.getNombreEmprendimiento());
            row.createCell(2).setCellValue(pe.getIdProducto());
            row.createCell(3).setCellValue(pe.getSku());
            row.createCell(4).setCellValue(pe.getNombreProducto());
            row.createCell(5).setCellValue(pe.getIdCategoria());
            row.createCell(6).setCellValue(pe.getDescripcion());


        }

        // Ajustar el tamaño de las columnas
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        // Crear la segunda hoja
        Sheet sheet2 = workbook.createSheet("Invetario");
       
        // Crear el encabezado para la segunda hoja
        String[] columns2 = {"ID Producto", "Nombre Producto", "Sku", "Descripcion", "ID Categoria", "Nombre del Emprendimiento","Nombre Unidad", "ID Medida", "Precio", "Cantidad Restante"};
        Row headerRow2 = sheet2.createRow(0);
        for (int i = 0; i < columns2.length; i++) {
            Cell cell2 = headerRow2.createCell(i);
            cell2.setCellValue(columns2[i]);
        }

        // Llenar las filas con los datos para la segunda hoja
        int rowNum2 = 1;
        for (IventarioRespDto inventario :  listaIventarioDao) {
            Row row2 = sheet2.createRow(rowNum2++);
            row2.createCell(0).setCellValue(inventario.getIdproducto());
            row2.createCell(1).setCellValue(inventario.getNombre());
            row2.createCell(2).setCellValue(inventario.getSku());
            row2.createCell(3).setCellValue(inventario.getDescripcion());
            row2.createCell(4).setCellValue(inventario.getIdcategoria());
            row2.createCell(5).setCellValue(inventario.getNombreemprendimiento());
            row2.createCell(6).setCellValue(inventario.getNombreUnidad());
            row2.createCell(7).setCellValue(inventario.getIdmedida());
            row2.createCell(8).setCellValue(inventario.getPrecio());
            row2.createCell(9).setCellValue(inventario.getCantidadRestante());

        }

        // Ajustar el tamaño de las columnas para la segunda hoja
        for (int i = 0; i < columns2.length; i++) {
            sheet2.autoSizeColumn(i);
        }
        
        
        
        outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
    	
        if (listaExcelDao == null || listaExcelDao.isEmpty()) {
            throw new IOException("La lista de productos del emprendimiento no puede ser null o vacía");
       }
    	} catch (IOException e) {
             LOG.error("Error al generar el Excel de emprendimientos: " + e.getMessage());
             // Manejo de la excepción
        }
        return outputStream;
        }
    
    
    public ByteArrayOutputStream generarExcelEmprendimientos(String idEmprendimiento, String fechamin, String fechamax) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            LOG.info("La ID es " + idEmprendimiento + " La fecha minima " + fechamin + " La fecha Maxima " + fechamax);

            List<ProductoEmprendimientoRespDto> listaExcelEmprendimientoDao = ExcelEmprendimientoDao.obtenerProductosEmprendimientosID(idEmprendimiento);
            LOG.info("La respuesta es listaExcelEmprendimientoDao " + listaExcelEmprendimientoDao);
            
            List<DetallesPedidoEmprendimientoRespDto> listaExcelEmprendimientoPorFechaDao = ExcelEmprendimientoPorFechaDao.obtenerProductosEmprendimientosIDyPorfecha(idEmprendimiento, fechamin, fechamax);
            LOG.info("La respuesta es listaExcelEmprendimientoPorFechaDao " + listaExcelEmprendimientoPorFechaDao);
            
            List<IventarioEmprendimientoRespDto> listaIventarioEmprendimientoDao = ExcelIventarioEmprendimientoDao.obtenerIventarioEmprendimientos(idEmprendimiento);
            LOG.info("La respuesta es listaIventarioEmprendimientoDao " + listaIventarioEmprendimientoDao);

            XSSFWorkbook workbook = new XSSFWorkbook();
            
            // Crear la primera hoja
            Sheet sheet1 = workbook.createSheet("Productos y Emprendimientos por Emprendimiento");
           
            // Crear el encabezado para la primera hoja
            String[] columns1 = {"ID Emprendimiento", "Nombre Emprendimiento", "ID Producto", "SKU", "Nombre Producto", "ID Categoria", "Descripción"};
            Row headerRow1 = sheet1.createRow(0);
            for (int i = 0; i < columns1.length; i++) {
                Cell cell = headerRow1.createCell(i);
                cell.setCellValue(columns1[i]);
            }

            // Llenar las filas con los datos para la primera hoja
            int rowNum1 = 1;
            for (ProductoEmprendimientoRespDto pe : listaExcelEmprendimientoDao) {
                Row row = sheet1.createRow(rowNum1++);
                row.createCell(0).setCellValue(pe.getIdEmprendimiento());
                row.createCell(1).setCellValue(pe.getNombreEmprendimiento());
                row.createCell(2).setCellValue(pe.getIdProducto());
                row.createCell(3).setCellValue(pe.getSku());
                row.createCell(4).setCellValue(pe.getNombreProducto());
                row.createCell(5).setCellValue(pe.getIdCategoria());
                row.createCell(6).setCellValue(pe.getDescripcion());
            }

            // Ajustar el tamaño de las columnas para la primera hoja
            for (int i = 0; i < columns1.length; i++) {
                sheet1.autoSizeColumn(i);
            }

            // Crear la segunda hoja
            Sheet sheet2 = workbook.createSheet("Ventas por fechas");
           
            // Crear el encabezado para la segunda hoja
            String[] columns2 = {"ID Venta", "Fecha Creación", "Cantidad", "Nombre Unidad", "ID Medida", "Precio", "ID Producto", "SKU", "Nombre Producto", "Descripción", "ID Categoria", "Subtotal"};
            Row headerRow2 = sheet2.createRow(0);
            for (int i = 0; i < columns2.length; i++) {
                Cell cell2 = headerRow2.createCell(i);
                cell2.setCellValue(columns2[i]);
            }

            // Llenar las filas con los datos para la segunda hoja
            int rowNum2 = 1;
            for (DetallesPedidoEmprendimientoRespDto detalleVenta : listaExcelEmprendimientoPorFechaDao) {
                Row row2 = sheet2.createRow(rowNum2++);
                row2.createCell(0).setCellValue(detalleVenta.getIdventa());
                row2.createCell(1).setCellValue(detalleVenta.getFechacreacion().toString());
                row2.createCell(2).setCellValue(detalleVenta.getCantidad());
                row2.createCell(3).setCellValue(detalleVenta.getNombreUnidad());
                row2.createCell(4).setCellValue(detalleVenta.getIdmedida());
                row2.createCell(5).setCellValue(detalleVenta.getPrecio());
                row2.createCell(6).setCellValue(detalleVenta.getIdproducto());
                row2.createCell(7).setCellValue(detalleVenta.getSku());
                row2.createCell(8).setCellValue(detalleVenta.getNombreProducto());
                row2.createCell(9).setCellValue(detalleVenta.getDescripcion());
                row2.createCell(10).setCellValue(detalleVenta.getIdcategoria());
                row2.createCell(11).setCellValue(detalleVenta.getSubtotal());
            }

            // Ajustar el tamaño de las columnas para la segunda hoja
            for (int i = 0; i < columns2.length; i++) {
                sheet2.autoSizeColumn(i);
            }
            
            // Crear la tercera hoja
            Sheet sheet3 = workbook.createSheet("Invetario Actual");
           
            // Crear el encabezado para la tercera hoja
            String[] columns3 = {"ID Producto", "Nombre Producto", "Sku", "Descripcion", "ID Categoria", "Nombre Unidad", "ID Medida", "Precio", "Cantidad Restante"};
            Row headerRow3 = sheet3.createRow(0);
            for (int i = 0; i < columns3.length; i++) {
                Cell cell3 = headerRow3.createCell(i);
                cell3.setCellValue(columns3[i]);
            }

            // Llenar las filas con los datos para la tercera hoja
            int rowNum3 = 1;
            for (IventarioEmprendimientoRespDto inventario :  listaIventarioEmprendimientoDao) {
                Row row3 = sheet3.createRow(rowNum3++);
                row3.createCell(0).setCellValue(inventario.getIdproducto());
                row3.createCell(1).setCellValue(inventario.getNombre());
                row3.createCell(2).setCellValue(inventario.getSku());
                row3.createCell(3).setCellValue(inventario.getDescripcion());
                row3.createCell(4).setCellValue(inventario.getIdcategoria());
                row3.createCell(5).setCellValue(inventario.getNombreUnidad());
                row3.createCell(6).setCellValue(inventario.getIdmedida());
                row3.createCell(7).setCellValue(inventario.getPrecio());
                row3.createCell(8).setCellValue(inventario.getCantidadRestante());

            }

            // Ajustar el tamaño de las columnas para la tercera hoja
            for (int i = 0; i < columns3.length; i++) {
                sheet3.autoSizeColumn(i);
            }
            
            
            outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();
            
       
            if (listaExcelEmprendimientoDao == null || listaExcelEmprendimientoDao.isEmpty()) {
                throw new IOException("La lista de productos del emprendimiento no puede ser null o vacía");
           }
        	} catch (IOException e) {
                 LOG.error("Error al generar el Excel de emprendimientos: " + e.getMessage());
                 // Manejo de la excepción
            }
            return outputStream;
            }
    
    public ByteArrayOutputStream generarExcelVentasPorFecha(String fechamin, String fechamax) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {

            List<VentasFechaRespDto> listaExcelVentasPorFechaDao = ExcelVentasPorFechaDao.obtenerVentasPorfecha(fechamin, fechamax);
            LOG.info("La respuesta es listaExcelVentasPorFechaDao " + listaExcelVentasPorFechaDao);
            List<PedidosFechaRespDto> listaExcelPedidosFechaDao= ExcelPedidosFechaDao.obtenerPedidosPorfecha(fechamin, fechamax);
            LOG.info("La respuesta es listaExcelPedidosFechaDao " + listaExcelPedidosFechaDao);
            List<UnidadPorFechaRespDto> listaExcelUnidadFechaDao= ExcelUnidadFechaDao.obtenerUnidadPorfecha(fechamin, fechamax);
            LOG.info("La respuesta es listaExcelUnidadFechaDao " + listaExcelUnidadFechaDao);

            
            XSSFWorkbook workbook = new XSSFWorkbook();
            
            // Crear la primera hoja
            Sheet sheet1 = workbook.createSheet("Ventas por Fecha");
           
            // Crear el encabezado para la primera hoja
            String[] columns1 = {"ID Venta", "Fecha De Venta", "Estatus", "Tipo", "Total de Venta", "ID Creador Usuario"};
            Row headerRow1 = sheet1.createRow(0);
            for (int i = 0; i < columns1.length; i++) {
                Cell cell = headerRow1.createCell(i);
                cell.setCellValue(columns1[i]);
            }

            // Llenar las filas con los datos para la primera hoja
            int rowNum1 = 1;
            for (VentasFechaRespDto ventas : listaExcelVentasPorFechaDao) {
                Row row = sheet1.createRow(rowNum1++);
                row.createCell(0).setCellValue(ventas.getIdventa());
                row.createCell(1).setCellValue(ventas.getFechaventa().toString());
                row.createCell(2).setCellValue(ventas.getEstatus());
                row.createCell(3).setCellValue(ventas.getTipo());
                row.createCell(4).setCellValue(ventas.getTotal());
                row.createCell(5).setCellValue(ventas.getCreadoridusuario());

            }

            // Ajustar el tamaño de las columnas para la primera hoja
            for (int i = 0; i < columns1.length; i++) {
                sheet1.autoSizeColumn(i);
            }

            // Crear la segunga hoja
            Sheet sheet2 = workbook.createSheet("Pedidos por Fecha");


            // Crear el encabezado para la segunda hoja
            String[] columns2 = {"ID Venta", "Fecha De Pedido", "Estatus de Ventas", "Tipo", "Total de Venta", "Estatus de Pedido", "Nombre del Cliente",
            		"Apellido Paterno del Cliente", "Apellido Materno del Cliente", "Direccion del Cliente", "Referencia del Cliente", "Telefono del Cliente" ,"ID Creador Usuario"};
            Row headerRow2 = sheet2.createRow(0);
            for (int i = 0; i < columns2.length; i++) {
                Cell cell2 = headerRow2.createCell(i);
                cell2.setCellValue(columns2[i]);
            }

            // Llenar las filas con los datos para la segunda hoja
            int rowNum2 = 1;
            for (PedidosFechaRespDto pedidos : listaExcelPedidosFechaDao) {
                Row row = sheet2.createRow(rowNum2++);
                row.createCell(0).setCellValue(pedidos.getIdventa());
                row.createCell(1).setCellValue(pedidos.getFechapedido().toString());
                row.createCell(2).setCellValue(pedidos.getVentas_estatus());
                row.createCell(3).setCellValue(pedidos.getTipo());
                row.createCell(4).setCellValue(pedidos.getTotal());
                row.createCell(5).setCellValue(pedidos.getPedidos_estatus());
                row.createCell(6).setCellValue(pedidos.getNombre());
                row.createCell(7).setCellValue(pedidos.getApellido_paterno());
                row.createCell(8).setCellValue(pedidos.getApellido_materno());
                row.createCell(9).setCellValue(pedidos.getDireccion());
                row.createCell(10).setCellValue(pedidos.getReferencia());
                row.createCell(11).setCellValue(pedidos.getTelefono());
                row.createCell(12).setCellValue(pedidos.getCreadoridusuario());


            }

            // Ajustar el tamaño de las columnas para la segunda hoja
            for (int j = 0; j < columns2.length; j++) {
                sheet2.autoSizeColumn(j);
            }
            

            // Crear la tercera hoja
            Sheet sheet3 = workbook.createSheet("Unidad por Fecha");


            // Crear el encabezado para la tercera hoja
            String[] columns3 = {"ID Venta", "Fecha", "Cantidad", "Nombre por Unidad", "ID Medida", "Precio", "ID Producto", "sku",
            		"Nombre del Producto", "Descripcion", "ID Categoria", "Subtotal", "Estatus", "Tipo"};
            Row headerRow3 = sheet3.createRow(0);
            for (int i = 0; i < columns3.length; i++) {
                Cell cell3 = headerRow3.createCell(i);
                cell3.setCellValue(columns3[i]);
            }

            // Llenar las filas con los datos para la segunda hoja
            int rowNum3 = 1;
            for (UnidadPorFechaRespDto unidad : listaExcelUnidadFechaDao) {
            	Row row = sheet3.createRow(rowNum3++);
            	row.createCell(0).setCellValue(unidad.getIdventa());
            	row.createCell(1).setCellValue(unidad.getFechacreacion().toString());
            	row.createCell(2).setCellValue(unidad.getCantidad());
            	row.createCell(3).setCellValue(unidad.getNombreUnidad());
            	row.createCell(4).setCellValue(unidad.getIdmedida());
            	row.createCell(5).setCellValue(unidad.getPrecio());
            	row.createCell(6).setCellValue(unidad.getIdproducto());
            	row.createCell(7).setCellValue(unidad.getSku());
            	row.createCell(8).setCellValue(unidad.getNombreProducto());
            	row.createCell(9).setCellValue(unidad.getDescripcion());
            	row.createCell(10).setCellValue(unidad.getIdcategoria());
            	row.createCell(11).setCellValue(unidad.getSubtotal());
            	row.createCell(12).setCellValue(unidad.getEstatus());
            	row.createCell(13).setCellValue(unidad.getTipo());



            }

            // Ajustar el tamaño de las columnas para la segunda hoja
            for (int i = 0; i < columns3.length; i++) {
                sheet3.autoSizeColumn(i);
            }
            
            
            
            outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();
            
       
            if (listaExcelVentasPorFechaDao == null || listaExcelVentasPorFechaDao.isEmpty()) {
                throw new IOException("La lista de ventas  no puede ser null o vacía");
           }
        	} catch (IOException e) {
                 LOG.error("Error al generar el Excel de emprendimientos: " + e.getMessage());
                 // Manejo de la excepción
            }
            return outputStream;
            }
    
}
