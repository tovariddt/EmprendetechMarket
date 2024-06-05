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
import com.emprendetech.market.service.responseDto.ProductoEmprendimientoRespDto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/productos-emprendimientos")
public class PlataformaServiceExcel {

	private static final Log LOG = LogFactory.getLog(PlataformaServiceExcel.class);

	@Autowired
	private ExcelController DescargarExcel, DescargarExcelEmprendimiento;

	@GetMapping("/excel")
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


	@GetMapping("/excelid")
	public ResponseEntity<?> descargarExcelEmprendimiento(@RequestBody ProductoEmprendimientoRespDto idEmprendimiento) {
		LOG.info("Emprendimiento La ID es " + idEmprendimiento.getIdEmprendimiento());

		try {
			String IDemp = String.valueOf(idEmprendimiento.getIdEmprendimiento());
			ByteArrayOutputStream outputStream = DescargarExcelEmprendimiento.generarExcelEmprendimientos(IDemp);
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
	
}
