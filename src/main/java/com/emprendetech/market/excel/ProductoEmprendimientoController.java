package com.emprendetech.market.excel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emprendetech.market.entitys.Personas;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/productos-emprendimientos")
public class ProductoEmprendimientoController {

	private static final Log LOG = LogFactory.getLog(ProductoEmprendimientoController.class);

	
    @Autowired
    private ProductoEmprendimientoService service , service2;

    @GetMapping("/excel")
    public ResponseEntity<ByteArrayResource> descargarExcel() throws IOException {
      
    	ByteArrayOutputStream outputStream = service.generarExcel();
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ProductosYEmprendimientos.xlsx");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .body(resource);
    }
    
    @GetMapping("/excelID")
    public ResponseEntity<ByteArrayResource> descargarExcelEmprendimiento(@RequestBody ProductoEmprendimiento idEmprendimiento ) throws IOException {
       
    	LOG.info(" ProductoEmprendimientoController La ID es"+ idEmprendimiento.getIdEmprendimiento());

    	String IDemp = String.valueOf(idEmprendimiento.getIdEmprendimiento());
    	ByteArrayOutputStream outputStream = service2.generarExcelEmprendimientos(IDemp);
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ProductosPorEmprendimientos.xlsx");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .body(resource);
    }
}


