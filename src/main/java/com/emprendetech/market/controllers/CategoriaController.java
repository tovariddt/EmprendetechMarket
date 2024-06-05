package com.emprendetech.market.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.entitys.Categorias;
import com.emprendetech.market.repositorys.CategoriasRepository;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.requestDto.CategoriaDto;
import com.emprendetech.market.utils.Constantes;
import com.emprendetech.market.utils.Utils;

import lombok.Data;

@Data
@Controller
public class CategoriaController {

	private static final Log LOG = LogFactory.getLog(CategoriaController.class);

	@Autowired
	private CategoriasRepository categoriasRepository;

	public String AltaCategoria(@RequestBody CategoriaDto categoriaDto) throws Exception {
		LOG.info("createAlta Categoria- createAlta Categoria() Method");
		String response = null;
		try {
			Categorias CategoriasInsert = new Categorias();

			CategoriasInsert.setNombre(categoriaDto.getNombre());
			CategoriasInsert.setCreadoridusuario(categoriaDto.getCreadoridusuario());

			Utils util = new Utils();
			CategoriasInsert.setFechacreacion(util.currentDate());
			CategoriasInsert.setFechamodificacion(util.currentDate());

			LOG.info("Alta Categoria - Alta Categoria() Method " + CategoriasInsert.toString());

			CategoriasInsert = categoriasRepository.save(CategoriasInsert);

			response = Constantes.FELICIDADESALTACATEGORIAS + CategoriasInsert.getNombre();

		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", categoriaDto.toString());
		}
		return response;
	}

	public String actualizarCategorias(@RequestBody Categorias categorias) throws Exception {
		LOG.info("update Categorias - update Categorias() Method");
		String response = null;
		try {
			Categorias categoriasExistente = categoriasRepository.findById(categorias.getIdcategoria()).orElseThrow();

			categoriasExistente.setNombre(categorias.getNombre());
			categoriasExistente.setCreadoridusuario(categorias.getCreadoridusuario());

			Utils util = new Utils();
			categoriasExistente.setFechamodificacion(util.currentDate());

			LOG.info("update Categorias - update Categorias() Method " + categoriasExistente.toString());

			categoriasExistente = categoriasRepository.save(categoriasExistente);

			response = Constantes.FELICIDADESACTUALIZARCATEGORIAS + categoriasExistente.getNombre();
		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", categorias.toString());
		}
		return response;
	}

}
