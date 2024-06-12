package com.emprendetech.market.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.entitys.Productos;
import com.emprendetech.market.repositorys.ProductosRepository;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.requestDto.ProductoDto;
import com.emprendetech.market.utils.Constantes;
import com.emprendetech.market.utils.Utils;

import lombok.Data;

@Data
@Controller
public class ProductoController {

	private static final Log LOG = LogFactory.getLog(ProductoController.class);

	@Autowired
	private ProductosRepository productosRepository;

	public String AltaProducto(@RequestBody ProductoDto productoDto) throws Exception {
		LOG.info("createAlta Producto - createAlta Producto() Method");

		String response = null;
		try {
			Productos ProductosInsert = new Productos();

			ProductosInsert.setSku(productoDto.getSku());
			ProductosInsert.setNombre(productoDto.getNombre());
			ProductosInsert.setDescripcion(productoDto.getDescripcion());
			ProductosInsert.setImagen(productoDto.getImagen());
			ProductosInsert.setIdcategoria(productoDto.getIdcategoria());
			ProductosInsert.setCreadoridusuario(productoDto.getCreadoridusuario());
            ProductosInsert.setIdemprendimiento(productoDto.getIdemprendimiento());
            
			Utils util = new Utils();
			ProductosInsert.setFechacreacion(util.currentDate());
			ProductosInsert.setFechamodificacion(util.currentDate());
			LOG.info("Alta Producto - Alta Producto() Method " + ProductosInsert.toString());

			ProductosInsert = productosRepository.save(ProductosInsert);

			response = Constantes.FELICIDADESALTAPRODUCTO+ ProductosInsert.getNombre();

		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", productoDto.toString());
		}
		return response;
	}

	public String actualizarProducto(@RequestBody Productos productos) throws Exception {
		LOG.info("update Producto - update Producto() Method");

		String response = null;

		try {
			Productos productoExistente = productosRepository.findById(productos.getIdproducto()).orElseThrow();

			productoExistente.setSku(productos.getSku());
			productoExistente.setNombre(productos.getNombre());
			productoExistente.setDescripcion(productos.getDescripcion());
			productoExistente.setImagen(productos.getImagen());
			productoExistente.setIdcategoria(productos.getIdcategoria());
			productoExistente.setIdemprendimiento(productos.getIdemprendimiento());


			Utils util = new Utils();
			productoExistente.setFechamodificacion(util.currentDate());

			LOG.info("update Producto - update Producto() Method " + productoExistente.toString());

			productoExistente = productosRepository.save(productoExistente);

			response =  Constantes.FELICIDADESACTUALIZARPRODUCTO+ productoExistente.getNombre() ;
		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", productos.toString());
		}
		return response;
	}

}