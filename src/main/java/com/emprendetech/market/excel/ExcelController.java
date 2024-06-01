package com.emprendetech.market.excel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.controllers.ClientesController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelController {

	private static final Log LOG = LogFactory.getLog(ExcelController.class);
	
    @Autowired
    private PlataformaExcelDao ExcelDao , ExcelEmprendimientoDao ;

    public ByteArrayOutputStream generarExcel() throws IOException {
        List<ProductoEmprendimiento> listaExcelDao = ExcelDao.obtenerProductosEmprendimientos();

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Productos y Emprendimientos");

        // Crear el encabezado
        String[] columns = {"ID Emprendimiento", "Nombre Emprendimiento", "ID Producto", "SKU", "Nombre Producto", "ID Categoria", "Descripción", "Cantidad Disponible"};
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Llenar las filas con los datos
        int rowNum = 1;
        for (ProductoEmprendimiento pe : listaExcelDao) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(pe.getIdEmprendimiento());
            row.createCell(1).setCellValue(pe.getNombreEmprendimiento());
            row.createCell(2).setCellValue(pe.getIdProducto());
            row.createCell(3).setCellValue(pe.getSku());
            row.createCell(4).setCellValue(pe.getNombreProducto());
            row.createCell(5).setCellValue(pe.getIdCategoria());
            row.createCell(6).setCellValue(pe.getDescripcion());
            row.createCell(7).setCellValue(pe.getCantidadDisponible());


        }

        // Ajustar el tamaño de las columnas
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream;
        }
    
    
    public ByteArrayOutputStream generarExcelEmprendimientos(String idEmprendimiento) throws IOException {
	
    	LOG.info("La ID es "+ idEmprendimiento);


        List<ProductoEmprendimiento> listaExcelEmprendimientoDao = ExcelEmprendimientoDao.obtenerProductosEmprendimientosID(idEmprendimiento);

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Productos y Emprendimientos por Emprendimiento");

        // Crear el encabezado
        String[] columns = {"ID Emprendimiento", "Nombre Emprendimiento", "ID Producto", "SKU", "Nombre Producto", "ID Categoria", "Descripción", "Cantidad Disponible"};
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Llenar las filas con los datos
        int rowNum = 1;
        for (ProductoEmprendimiento pe : listaExcelEmprendimientoDao) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(pe.getIdEmprendimiento());
            row.createCell(1).setCellValue(pe.getNombreEmprendimiento());
            row.createCell(2).setCellValue(pe.getIdProducto());
            row.createCell(3).setCellValue(pe.getSku());
            row.createCell(4).setCellValue(pe.getNombreProducto());
            row.createCell(5).setCellValue(pe.getIdCategoria());
            row.createCell(6).setCellValue(pe.getDescripcion());
            row.createCell(7).setCellValue(pe.getCantidadDisponible());


        }

        // Ajustar el tamaño de las columnas
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream;
        }
}
