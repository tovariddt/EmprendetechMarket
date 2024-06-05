package com.emprendetech.market.servicios.registro;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ResponseBody
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)

@RestController
@RequestMapping(path = "/api/registro")
public class RegistroService {
	private static final Log LOG = LogFactory.getLog(RegistroService.class);

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "word") String name) {

		LOG.info("si funciona");
		return String.format("Hello %s!", name);

	}

}
