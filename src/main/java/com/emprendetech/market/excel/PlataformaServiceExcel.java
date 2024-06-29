package com.emprendetech.market.excel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emprendetech.market.controllers.ExcelController;
import com.emprendetech.market.interfaces.InterfecesExcel;
import com.emprendetech.market.service.requestDto.EmprendimientoFechaDto;
import com.emprendetech.market.service.requestDto.FechaDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/productos-emprendimientos")
public class PlataformaServiceExcel implements InterfecesExcel{

	private static final Log LOG = LogFactory.getLog(PlataformaServiceExcel.class);

	@Autowired
	private ExcelController DescargarExcel, DescargarExcelEmprendimiento, DescargarExcelVentas;

    @Override
	@GetMapping("/excel")
	@Operation(summary = "Se crea un registro en un archivo excel")
    @ApiResponse(responseCode = "200", description = "Con este metodo se crea un registro en un archivo excel")
	public ResponseEntity<?> descargarExcel() {
	    LOG.info("Productos y Emprendimientos");
	    ResponseEntity<?> responseEntity;

	    try {
	        ByteArrayOutputStream outputStream = DescargarExcel.generarExcel();
	        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

	        HttpHeaders headers = new HttpHeaders();
	        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ProductosYEmprendimientos.xlsx");
	        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

	        responseEntity = ResponseEntity.ok().headers(headers).contentLength(resource.contentLength()).body(resource);
	    } catch (IOException e) {
	        LOG.error("Error al generar o descargar el archivo Excel: ", e);
	        responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error al generar o descargar el archivo Excel.");
	    }

	    return responseEntity;
	}


    @Override
	@GetMapping("/excelid")
	@Operation(summary = "Se crea un registro en un archivo excel con datos de emprendimiento y fecha")
    @ApiResponse(responseCode = "200", description = "Con este metodo se crea un registro en un archivo excel con datos de emprendimiento y fecha")
    public ResponseEntity<?> descargarExcelEmprendimiento(@RequestBody EmprendimientoFechaDto datosidfecha) {
		LOG.info("Emprendimiento La ID es " + datosidfecha.getIdemprendimiento());

		try {
			String IDemp = String.valueOf(datosidfecha.getIdemprendimiento());
			String fechamin= datosidfecha.getFechaminima();
			String fechamax= datosidfecha.getFechamaxima();
			LOG.info("Emprendimiento La  " +fechamin) ;

			
			ByteArrayOutputStream outputStream = DescargarExcelEmprendimiento.generarExcelEmprendimientos(IDemp , fechamin, fechamax);
			ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ProductosPorEmprendimientos.xlsx");
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

			return ResponseEntity.ok().headers(headers).contentLength(resource.contentLength()).body(resource);
		} catch (IOException e) {
			LOG.error("Error al generar o descargar el archivo Excel: ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al generar o descargar el archivo Excel.");
		}
	
	}
	
	@Override
	@GetMapping("/excelventaspedidos")
	@Operation(summary = "Se crea un registro en un archivo excel con datos de fecha")
    @ApiResponse(responseCode = "200", description = "Con este metodo se crea un registro en un archivo excel con datos de fecha")
	public ResponseEntity<?> descargarExcelventas(@RequestBody FechaDto datosidfecha) {
		LOG.info(" La fecha es " + datosidfecha.getFechaminima());

		try {
			String fechamin= datosidfecha.getFechaminima();
			String fechamax= datosidfecha.getFechamaxima();
			LOG.info("Fecha minima " +fechamin) ;

			
			ByteArrayOutputStream outputStream = DescargarExcelVentas.generarExcelVentasPorFecha(fechamin, fechamax);
			ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=VentasYPedidos.xlsx");
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

			return ResponseEntity.ok().headers(headers).contentLength(resource.contentLength()).body(resource);
		} catch (IOException e) {
			LOG.error("Error al generar o descargar el archivo Excel: ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al generar o descargar el archivo Excel.");
		}
	
	}
	
	
	
}
