package com.emprendetech.market.servicios.front.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.emprendetech.market.controllers.RegistroController;
import com.emprendetech.market.controllers.Rol_permisosController;
import com.emprendetech.market.controllers.RolesController;
import com.emprendetech.market.controllers.VentasController;
import com.emprendetech.market.controllers.CategoriaController;
import com.emprendetech.market.controllers.ClientesController;
import com.emprendetech.market.controllers.DetallespedidoController;
import com.emprendetech.market.controllers.MedidasController;
import com.emprendetech.market.controllers.MetodospagoController;
import com.emprendetech.market.controllers.PedidosController;
import com.emprendetech.market.controllers.PermisosController;
import com.emprendetech.market.controllers.PrecioController;
import com.emprendetech.market.controllers.ProductoController;
import com.emprendetech.market.controllers.ProductosunidadController;
import com.emprendetech.market.dao.PlataformaDao;
import com.emprendetech.market.entitys.Categorias;
import com.emprendetech.market.entitys.Clientes;
import com.emprendetech.market.entitys.Detallespedido;
import com.emprendetech.market.entitys.Emprendimientos;
import com.emprendetech.market.entitys.Medidas;
import com.emprendetech.market.entitys.Metodospago;
import com.emprendetech.market.entitys.Pedidos;
import com.emprendetech.market.entitys.Permisos;
import com.emprendetech.market.entitys.Personas;
import com.emprendetech.market.entitys.Precio;
import com.emprendetech.market.entitys.Productos;
import com.emprendetech.market.entitys.Productosunidad;
import com.emprendetech.market.entitys.Rol_permisos;
import com.emprendetech.market.entitys.Roles;
import com.emprendetech.market.entitys.Usuario;
import com.emprendetech.market.entitys.Ventas;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.responseDto.CategoriasRespDto;
import com.emprendetech.market.service.responseDto.CompCorreoRespDto;
import com.emprendetech.market.service.responseDto.EmprendimientosRespDTO;
import com.emprendetech.market.service.responseDto.PerfilesRespDto;
import com.emprendetech.market.service.responseDto.PersonaUsuarioRespDTO;
import com.emprendetech.market.service.responseDto.PostalRespDto;
import com.emprendetech.market.service.responseDto.ProductosRespDto;
import com.emprendetech.market.service.responseDto.RolesRespDto;
import com.emprendetech.market.service.responseDto.UsuarioEmprendimientoRespDto;
import com.emprendetech.market.servicios.registro.RegistroService;

import com.emprendetech.market.service.requestDto.CategoriaDto;
import com.emprendetech.market.service.requestDto.ClientesDto;
import com.emprendetech.market.service.requestDto.DetallespedidoDto;
import com.emprendetech.market.service.requestDto.MedidasDto;
import com.emprendetech.market.service.requestDto.MetodospagoDto;
import com.emprendetech.market.service.requestDto.PedidosDto;
import com.emprendetech.market.service.requestDto.PermisosDto;
import com.emprendetech.market.service.requestDto.PrecioDto;
import com.emprendetech.market.service.requestDto.ProductoDto;
import com.emprendetech.market.service.requestDto.ProductosunidadDto;
import com.emprendetech.market.service.requestDto.RegistroDto;
import com.emprendetech.market.service.requestDto.Rol_permisosDto;
import com.emprendetech.market.service.requestDto.RolesDto;
import com.emprendetech.market.service.requestDto.UsuarioContrasenaDto;
import com.emprendetech.market.service.requestDto.VentasDto;

import org.springframework.http.HttpStatus;

@ResponseBody
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)

@RestController
@RequestMapping(path = "/api/plataforma")
public class PlataformaServiceImpl {
	private static final Log LOG = LogFactory.getLog(RegistroService.class);

	@Autowired
	private PlataformaDao rolesDao, perfilesDao, codigoDao, correoDao, consultaDao, categoriaDao, productoEmprendimientoDao ,productoEmprendimientoNombreLIKEDao,  productoEmprendimientoCategoriaDao;

	@Autowired
	private RegistroController AltaUsuario , ActualizarPersonas ,ActualizarUsuario, ActualizarEmprendimientos;
	@Autowired
	private ProductoController AltaProducto, ActualizarProducto;
	@Autowired
	private CategoriaController AltaCategoria, ActualizarCategoria;
	@Autowired
	private ClientesController AltaClientes , ActualizarClientes;
	@Autowired
	private MedidasController AltaMedidas , ActualizarMedidas;
	@Autowired
	private DetallespedidoController AltaDetallespedido , ActualizarDetallespedido;
	@Autowired
	private MetodospagoController AltaMetodospago ,ActualizarMetodospago;
	@Autowired
	private PedidosController AltaPedidos, ActualizarPedidos;	
	@Autowired
	private PrecioController AltaPrecio, ActualizarPrecio;
	@Autowired
	private ProductosunidadController AltaProductosunidad, ActualizarProductosunidad;
	@Autowired
	private PermisosController AltaPermisos, ActualizarPermisos;
	@Autowired
	private Rol_permisosController AltaRol_permisos, ActualizarRol_permisos;
	@Autowired
	private RolesController AltaRoles, ActualizarRoles;
	@Autowired
	private VentasController AltaVentas , ActualizarVentas;

	@GetMapping("/roles")
	public ResponseEntity<?> getAllRoles() {

		LOG.info("get AllRoles - get AllRoles() Method");
		ResponseEntity<?> responseRoles = null;
		List<RolesRespDto> resultRoles = new ArrayList<RolesRespDto>();

		try {

			resultRoles = rolesDao.getRoles();

			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Roles");
			responseContenido.setContenido(resultRoles);
			responseRoles = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Roles");
			responseRoles = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}

		return responseRoles;
	}

	@GetMapping("/perfiles")
	public ResponseEntity<?> getAllPerfiles() {

		LOG.info("getAllPerfiles - getAllPerfiles() Method");
		ResponseEntity<?> responseperfiles = null;
		List<PerfilesRespDto> resultperfiles = new ArrayList<PerfilesRespDto>();
		try {

			resultperfiles = perfilesDao.getPefiles();

			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Perfiles");
			responseContenido.setContenido(resultperfiles);
			responseperfiles = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Perfiles");
			responseperfiles = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}

		return responseperfiles;
	}

	
	@GetMapping("/codigopostal")
	public ResponseEntity<?> getAllCodigoPostal(@RequestBody PostalRespDto postalrespDto) {

		LOG.info("get AllCodigoPostal - get AllCodigoPostal() Method");
		ResponseEntity<?> responseCodigopostal = null;
		String clave = postalrespDto.getClave();
		
		List<PostalRespDto> resultCodigopostal = new ArrayList<PostalRespDto>();

		try {
			resultCodigopostal = codigoDao.getCodigo(clave);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Codigo");
			responseContenido.setContenido(resultCodigopostal);
			responseCodigopostal = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Codigo");
			responseCodigopostal = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responseCodigopostal;
	}

	@PostMapping("/Alta")
	public ResponseEntity<?> postRegistro(@RequestBody RegistroDto registrodto) {

		LOG.info("get Registro - get Registro() Method");
		ResponseEntity<?> responseAlta = null;
		try {
			String altaUsuario = new String();
			altaUsuario = AltaUsuario.AltaUsuario(registrodto);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Registro");
			responseContenido.setContenido(altaUsuario);
			responseAlta = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Registro");
			responseAlta = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}

		return responseAlta;
	}

	@PostMapping("/ActualizarUsuario")
	public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario) {

		LOG.info("update Usuario - update Usuario() Method");
	
		ResponseEntity<?> responseactualizarUsuario = null;

		try {

			String actualizarUsuario = null;

			actualizarUsuario = ActualizarUsuario.actualizarUsuario(usuario);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Actualizar  Usuario");
			responseContenido.setContenido(actualizarUsuario);
			responseactualizarUsuario= new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("error codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Actualizar Usuario");
			responseactualizarUsuario = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responseactualizarUsuario;
	}
	
	
	@PostMapping("/ActualizarPersonas")
	public ResponseEntity<?> updatePersonas(@RequestBody Personas personas) {

		LOG.info("update Personas - update Personas() Method");
	
		ResponseEntity<?> responseactualizarPersonas = null;

		try {

			String actualizarPersonas = null;

			actualizarPersonas = ActualizarPersonas.actualizarPersonas(personas);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Actualizar Personas");
			responseContenido.setContenido(actualizarPersonas);
			responseactualizarPersonas= new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("error codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Actualizar Personas");
			responseactualizarPersonas = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responseactualizarPersonas;
	}
	
	@PostMapping("/ActualizarEmprendimientos")
	public ResponseEntity<?> updateEmprendimientos(@RequestBody Emprendimientos emprendimientos) {

		LOG.info("update Emprendimientos - update Emprendimientos() Method");
	
		ResponseEntity<?> responseactualizarEmprendimientos = null;

		try {

			String actualizarEmprendimientos = null;

			actualizarEmprendimientos = ActualizarEmprendimientos.actualizarEmprendimientos(emprendimientos);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Actualizar  Emprendimientos");
			responseContenido.setContenido(actualizarEmprendimientos);
			responseactualizarEmprendimientos= new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("error codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Actualizar Emprendimientos");
			responseactualizarEmprendimientos = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responseactualizarEmprendimientos;
	}
	
	
	
	@GetMapping("/Correo")
	public ResponseEntity<?> getCorreo(@RequestBody Usuario correodto) {
		LOG.info("get CorreoComprobacion - get CorreoComprobacion() Method");

		ResponseEntity<?> responsecorreo = null;
		List<CompCorreoRespDto> resultcorreo  = new ArrayList<CompCorreoRespDto>();

		try {
			String correo = correodto.getCorreo();
			String Comprobacion = correoDao.getCorreo(correo);
			boolean Existe = Comprobacion == null;

			CompCorreoRespDto PruebaCorreo = new CompCorreoRespDto();
			PruebaCorreo.setCorreo(correo);
			PruebaCorreo.setValor(Existe);

			if (Comprobacion == null) {
				PruebaCorreo.setMensaje("No Existe");
			} else {
				PruebaCorreo.setMensaje("Existe");
			}

			resultcorreo.add(PruebaCorreo);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Codigo");
			responseContenido.setContenido(resultcorreo);
			responsecorreo = new ResponseEntity<>(responseContenido, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Codigo");
		}
		return responsecorreo;
	}

	
	@GetMapping("/Consulta")
	public ResponseEntity<?> getUsuarioPersona(@RequestBody UsuarioContrasenaDto CorroContrasena) {
		LOG.info("get UsuarioPersona - get UsuarioPersona() Method");

		ResponseEntity<?> responseConsulta = null;
		try {
		String correo = CorroContrasena.getCorreo();
		String contrasena = CorroContrasena.getContrasena();
			List<PersonaUsuarioRespDTO> resultPersonaUsuario = new ArrayList<PersonaUsuarioRespDTO>();
			resultPersonaUsuario = consultaDao.getUsuarioPersona(correo, contrasena);

			List<EmprendimientosRespDTO> resultEmprendimientos = new ArrayList<EmprendimientosRespDTO>();
			resultEmprendimientos = consultaDao.getEmprendimientos(correo, contrasena);

			UsuarioEmprendimientoRespDto usuarioEmprendimiento = new UsuarioEmprendimientoRespDto();
			usuarioEmprendimiento.setEmprendimiento(resultEmprendimientos);
			usuarioEmprendimiento.setPersonausuario(resultPersonaUsuario);

			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Consulta Usuario");
			responseContenido.setContenido(usuarioEmprendimiento);
			responseConsulta = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Consulta Usuario");
			responseConsulta = new ResponseEntity<>(responseContenido, HttpStatus.OK);
		}
		return responseConsulta;
	}

	@GetMapping("/categorias")
	public ResponseEntity<?> getAllCategorias() {

		LOG.info("get AllCategorias - get AllCategorias() Method");
		ResponseEntity<?> responseCategorias = null;

		try {
			List<CategoriasRespDto> resultCategorias = new ArrayList<CategoriasRespDto>();

			resultCategorias = categoriaDao.getcategoria();

			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Categorias");
			responseContenido.setContenido(resultCategorias);
			responseCategorias = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Categorias");
			responseCategorias = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}

		return responseCategorias;
	}

	@PostMapping("/AltaProducto")
	public ResponseEntity<?> postProducto(@RequestBody ProductoDto productoDto) {

		LOG.info("get Producto - get Producto() Method");
		ResponseEntity<?> responsealtaProducto = null;

		try {

			String altaProducto = null;

			altaProducto = AltaProducto.AltaProducto(productoDto);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Alta Producto");
			responseContenido.setContenido(altaProducto);
			responsealtaProducto = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Alta Producto");
			responsealtaProducto = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responsealtaProducto;
	}

	@PostMapping("/ActualizarProducto")
	public ResponseEntity<?> updateProducto(@RequestBody Productos productos) {

		LOG.info("update Producto - update Producto() Method");
		ResponseEntity<?> responseactualizarProducto = null;

		try {

			String actualizarProducto = null;

			actualizarProducto = ActualizarProducto.actualizarProducto(productos);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Actualizar Producto");
			responseContenido.setContenido(actualizarProducto);
			responseactualizarProducto = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("error codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Actualizar Producto");
			responseactualizarProducto = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responseactualizarProducto;
	}
	
	@GetMapping("/ConsultaProductoEmprendimiento")
	public ResponseEntity<?> getproductoEmprendimiento(@RequestBody ProductosRespDto idemprendimientos) {

		LOG.info("get productoEmprendimiento - get productoEmprendimiento() Method");
		ResponseEntity<?> responseproductoEmprendimiento = null;
		Integer idemprendimiento = idemprendimientos.getIdemprendimiento();

		try {
		
		List<ProductosRespDto> resultProductosRespDto = new ArrayList<ProductosRespDto>();

		resultProductosRespDto=productoEmprendimientoDao.getproductoEmprendimiento(idemprendimiento);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "ProductoEmprendimiento");
			responseContenido.setContenido(resultProductosRespDto);
			responseproductoEmprendimiento = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "ProductoEmprendimiento");
			responseproductoEmprendimiento = new ResponseEntity<>(responseContenido, HttpStatus.OK);
		}
		return responseproductoEmprendimiento;
	}
	
	@GetMapping("/ConsultaProductoEmprendimientoCategoria")
	public ResponseEntity<?> getproductoEmprendimientoCategoria(@RequestBody ProductosRespDto emprendimientoscategoria ) {

		LOG.info("get productoEmprendimientoCategoria - get productoEmprendimientoCategoria() Method");
		ResponseEntity<?> responseproductoEmprendimientoCategoria= null;
		Integer idemprendimiento = emprendimientoscategoria.getIdemprendimiento();
		Integer idcategoria = emprendimientoscategoria.getIdcategoria();

		try {
		
		List<ProductosRespDto> resultProductosCategoriaRespDto = new ArrayList<ProductosRespDto>();

		resultProductosCategoriaRespDto=productoEmprendimientoCategoriaDao.getproductoEmprendimientoCategoria(idemprendimiento, idcategoria);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "ProductoEmprendimientoCategoria");
			responseContenido.setContenido(resultProductosCategoriaRespDto);
			responseproductoEmprendimientoCategoria = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "ProductoEmprendimientoCategoria");
			responseproductoEmprendimientoCategoria = new ResponseEntity<>(responseContenido, HttpStatus.OK);
		}
		return responseproductoEmprendimientoCategoria;
	}
	
	@GetMapping("/ConsultaProductoEmprendimientoNombreLIKE")
	public ResponseEntity<?> getproductoEmprendimientoNombreLIKE(@RequestBody ProductosRespDto emprendimientosNombreLIKE ) {

		LOG.info("get productoEmprendimientoNombreLIKE - get productoEmprendimientoNombreLIKE() Method");
		ResponseEntity<?> responseproductoEmprendimientoNombreLIKE= null;
		Integer idemprendimiento = emprendimientosNombreLIKE.getIdemprendimiento();
		String nombreLIKE = emprendimientosNombreLIKE.getNombre();

		try {
		
		List<ProductosRespDto> resultProductosNombreLIKERespDto = new ArrayList<ProductosRespDto>();

		resultProductosNombreLIKERespDto=productoEmprendimientoNombreLIKEDao.getproductoEmprendimientoNombreLIKE(idemprendimiento, nombreLIKE);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "ProductoEmprendimientonombreLIKE");
			responseContenido.setContenido(resultProductosNombreLIKERespDto);
			responseproductoEmprendimientoNombreLIKE = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "ProductoEmprendimientonombreLIKE");
			responseproductoEmprendimientoNombreLIKE = new ResponseEntity<>(responseContenido, HttpStatus.OK);
		}
		return responseproductoEmprendimientoNombreLIKE;
	}



	@PostMapping("/AltaCategoria")
	public ResponseEntity<?> postCategoria(@RequestBody CategoriaDto categoriaDto) {

		LOG.info("get Categoria - get Categoria() Method");
		ResponseEntity<?> responsealtaCategoria = null;

		try {

			String altaCategoria = null;

			altaCategoria = AltaCategoria.AltaCategoria(categoriaDto);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Alta Categoria");
			responseContenido.setContenido(altaCategoria);
			responsealtaCategoria = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Alta Categoria");
			responsealtaCategoria = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responsealtaCategoria;
	}

	@PostMapping("/ActualizarCategoria")
	public ResponseEntity<?> updateCategorias(@RequestBody Categorias categorias) {

		LOG.info("update Categorias - update Categorias() Method");
	
		ResponseEntity<?> responseactualizarCategoria = null;

		try {

			String actualizarCategoria = null;

			actualizarCategoria = ActualizarCategoria.actualizarCategorias(categorias);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Actualizar Categoria");
			responseContenido.setContenido(actualizarCategoria);
			responseactualizarCategoria = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("error codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Actualizar Categoria");
			responseactualizarCategoria = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responseactualizarCategoria;
	}
	
	
	@PostMapping("/AltaClientes")
	public ResponseEntity<?> postClientes(@RequestBody ClientesDto clientesDto) {

		LOG.info("get Clientes - get Clientes() Method");
		ResponseEntity<?> responsealtaClientes = null;

		try {

			String altaClientes = null;

			altaClientes = AltaClientes.AltaClientes(clientesDto);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Alta Clientes");
			responseContenido.setContenido(altaClientes);
			responsealtaClientes = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Alta Clientes");
			responsealtaClientes = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responsealtaClientes;
	}

	@PostMapping("/ActualizarClientes")
	public ResponseEntity<?> updateClientes(@RequestBody Clientes clientes) {

		LOG.info("update Clientes - update Clientes() Method");
	
		ResponseEntity<?> responseactualizarClientes = null;

		try {

			String actualizarClientes = null;

			actualizarClientes = ActualizarClientes.actualizarClientes(clientes);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Actualizar Clientes");
			responseContenido.setContenido(actualizarClientes);
			responseactualizarClientes = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("error codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Actualizar Clientes");
			responseactualizarClientes = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responseactualizarClientes;
	}
	
	@PostMapping("/AltaMedidas")
	public ResponseEntity<?> postMedidas(@RequestBody MedidasDto medidasDto) {

		LOG.info("get Medidas - get Medidas() Method");
		ResponseEntity<?> responsealtaMedidas = null;

		try {

			String altaMedidas = null;

			altaMedidas = AltaMedidas.AltaMedidas(medidasDto);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Alta Medidas");
			responseContenido.setContenido(altaMedidas);
			responsealtaMedidas = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Alta Medidas");
			responsealtaMedidas = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responsealtaMedidas;
	}

	@PostMapping("/ActualizarMedidas")
	public ResponseEntity<?> updateMedidas(@RequestBody Medidas medidas) {

		LOG.info("update Medidas - update Medidas() Method");
	
		ResponseEntity<?> responseactualizarMedidas = null;

		try {

			String actualizarMedidas = null;

			actualizarMedidas = ActualizarMedidas.actualizarMedidas(medidas);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Actualizar Medidas");
			responseContenido.setContenido(actualizarMedidas);
			responseactualizarMedidas = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("error codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Actualizar Medidas");
			responseactualizarMedidas = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responseactualizarMedidas;
	}
	
	@PostMapping("/AltaDetallespedido")
	public ResponseEntity<?> postDetallespedido(@RequestBody DetallespedidoDto detallespedidoDto) {

		LOG.info("get Detallespedido - get Detallespedido() Method");
		ResponseEntity<?> responsealtaDetallespedido = null;

		try {

			String altaDetallespedido = null;

			altaDetallespedido = AltaDetallespedido.AltaDetallespedido(detallespedidoDto);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Alta Detallespedido");
			responseContenido.setContenido(altaDetallespedido);
			responsealtaDetallespedido = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Alta Detallespedido");
			responsealtaDetallespedido = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responsealtaDetallespedido;
	}

	@PostMapping("/ActualizarDetallespedido")
	public ResponseEntity<?> updateDetallespedido(@RequestBody Detallespedido detallespedido) {


		LOG.info("update Detallespedido - update Detallespedido() Method");
	
		ResponseEntity<?> responseactualizarDetallespedido = null;

		try {

			String actualizarDetallespedido = null;

			actualizarDetallespedido = ActualizarDetallespedido.actualizarDetallespedido(detallespedido);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Actualizar Detallespedido");
			responseContenido.setContenido(actualizarDetallespedido);
			responseactualizarDetallespedido = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("error codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Actualizar Detallespedido");
			responseactualizarDetallespedido = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responseactualizarDetallespedido;
	}
	
	@PostMapping("/AltaPedidos")
	public ResponseEntity<?> postPedidos(@RequestBody PedidosDto pedidosDto) {

		LOG.info("get Pedidos - get Pedidos() Method");
		ResponseEntity<?> responsealtaPedidos = null;

		try {

			String altaPedidos = null;

			altaPedidos = AltaPedidos.AltaPedidos(pedidosDto);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Alta Pedidos");
			responseContenido.setContenido(altaPedidos);
			responsealtaPedidos = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Alta Pedidos");
			responsealtaPedidos = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responsealtaPedidos;
	}

	@PostMapping("/ActualizarPedidos")
	public ResponseEntity<?> updatePedidos(@RequestBody Pedidos pedidos) {

		LOG.info("update Pedidos - update Pedidos() Method");
	
		ResponseEntity<?> responseactualizarPedidos = null;

		try {

			String actualizarPedidos = null;

			actualizarPedidos = ActualizarPedidos.actualizarPedidos(pedidos);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Actualizar Pedidos");
			responseContenido.setContenido(actualizarPedidos);
			responseactualizarPedidos = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("error codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Actualizar Pedidos");
			responseactualizarPedidos = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responseactualizarPedidos;
	}
	
	@PostMapping("/AltaMetodospago")
	public ResponseEntity<?> postMetodospago(@RequestBody MetodospagoDto metodospagoDto) {

		LOG.info("get Metodospago - get Metodospago() Method");
		ResponseEntity<?> responsealtaMetodospago = null;

		try {

			String altaMetodospago = null;

			altaMetodospago = AltaMetodospago.AltaMetodospago(metodospagoDto);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Alta Metodospago");
			responseContenido.setContenido(altaMetodospago);
			responsealtaMetodospago = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Alta Metodospago");
			responsealtaMetodospago = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responsealtaMetodospago;
	}

	@PostMapping("/ActualizarMetodospago")
	public ResponseEntity<?> updateMetodospago(@RequestBody Metodospago metodospago) {

		LOG.info("update Metodospago - update Metodospago() Method");
	
		ResponseEntity<?> responseactualizarMetodospago = null;

		try {

			String actualizarMetodospago = null;

			actualizarMetodospago = ActualizarMetodospago.actualizarMetodospago(metodospago);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Actualizar Metodospago");
			responseContenido.setContenido(actualizarMetodospago);
			responseactualizarMetodospago = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("error codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Actualizar Metodospago");
			responseactualizarMetodospago = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responseactualizarMetodospago;
	}
	

	@PostMapping("/AltaPrecio")
	public ResponseEntity<?> postPrecio(@RequestBody PrecioDto precioDto) {

		LOG.info("get Precio - get Precio() Method");
		ResponseEntity<?> responsealtaPrecio = null;

		try {

			String altaPrecio = null;

			altaPrecio = AltaPrecio.AltaPrecio(precioDto);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Alta Precio");
			responseContenido.setContenido(altaPrecio);
			responsealtaPrecio = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Alta Precio");
			responsealtaPrecio = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responsealtaPrecio;
	}

	@PostMapping("/ActualizarPrecio")
	public ResponseEntity<?> updateMetodospago(@RequestBody Precio precio) {

		LOG.info("update Precio - update Metodospago() Method");
	
		ResponseEntity<?> responseactualizarPrecio = null;

		try {

			String actualizarPrecio = null;

			actualizarPrecio = ActualizarPrecio.actualizarPrecio(precio);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Actualizar Precio");
			responseContenido.setContenido(actualizarPrecio);
			responseactualizarPrecio = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("error codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Actualizar Precio");
			responseactualizarPrecio = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responseactualizarPrecio;
	}
	

	@PostMapping("/AltaProductosunidad")
	public ResponseEntity<?> postProductosunidad(@RequestBody ProductosunidadDto  productosunidadDto) {

		LOG.info("get Productosunidad - get Productosunidad() Method");
		ResponseEntity<?> responsealtaProductosunidad= null;

		try {

			String altaProductosunidad = null;

			altaProductosunidad = AltaProductosunidad.AltaProductosunidad(productosunidadDto);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Alta Productosunidad");
			responseContenido.setContenido(altaProductosunidad);
			responsealtaProductosunidad = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Alta Productosunidad");
			responsealtaProductosunidad = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responsealtaProductosunidad;
	}

	@PostMapping("/ActualizarProductosunidad")
	public ResponseEntity<?> updateProductosunidad(@RequestBody Productosunidad productosunidad) {

		LOG.info("update Productosunidad - update Productosunidad() Method");
	
		ResponseEntity<?> responseactualizarProductosunidad = null;

		try {

			String actualizarProductosunidad = null;

			actualizarProductosunidad = ActualizarProductosunidad.actualizarProductosunidad(productosunidad);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Actualizar Productosunidad");
			responseContenido.setContenido(actualizarProductosunidad);
			responseactualizarProductosunidad = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("error codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Actualizar Productosunidad");
			responseactualizarProductosunidad = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responseactualizarProductosunidad;
	}
	
	@PostMapping("/AltaPermisos")
	public ResponseEntity<?> postPermisos(@RequestBody PermisosDto  permisosDto) {

		LOG.info("get Permisos - get Permisos() Method");
		ResponseEntity<?> responsealtaPermisos= null;

		try {

			String altaPermisos = null;

			altaPermisos = AltaPermisos.AltaPermisos(permisosDto);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Alta Permisos");
			responseContenido.setContenido(altaPermisos);
			responsealtaPermisos = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Alta Permisos");
			responsealtaPermisos = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responsealtaPermisos;
	}

	@PostMapping("/ActualizarPermisos")
	public ResponseEntity<?> updatePermisos(@RequestBody Permisos permisos) {

		LOG.info("update Permisos - update Permisos() Method");
	
		ResponseEntity<?> responseactualizarPermisos = null;

		try {

			String actualizarPermisos = null;

			actualizarPermisos = ActualizarPermisos.actualizarPermisos(permisos);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Actualizar Permisos");
			responseContenido.setContenido(actualizarPermisos);
			responseactualizarPermisos = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("error codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Actualizar Permisos");
			responseactualizarPermisos = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responseactualizarPermisos;
	}
	
	@PostMapping("/AltaRol_permisos")
	public ResponseEntity<?> postRol_permisos(@RequestBody Rol_permisosDto  rol_permisosDto) {

		LOG.info("get Rol_permisos - get Rol_permisos() Method");
		ResponseEntity<?> responsealtaPermisos= null;

		try {

			String altaRol_permisos = null;

			altaRol_permisos = AltaRol_permisos.AltaRol_permisos(rol_permisosDto);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Alta Rol_permisos");
			responseContenido.setContenido(altaRol_permisos);
			responsealtaPermisos = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Alta Rol_permisos");
			responsealtaPermisos = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responsealtaPermisos;
	}

	@PostMapping("/ActualizarRol_permisos")
	public ResponseEntity<?> updateRol_permisos(@RequestBody Rol_permisos rol_permisos) {

		LOG.info("update Rol_permisos - update Rol_permisos() Method");
	
		ResponseEntity<?> responseactualizarRol_permisos = null;

		try {

			String actualizarRol_permisos = null;

			actualizarRol_permisos = ActualizarRol_permisos.actualizarRol_permisos(rol_permisos);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Actualizar Rol_permisos");
			responseContenido.setContenido(actualizarRol_permisos);
			responseactualizarRol_permisos= new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("error codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Actualizar Rol_permisos");
			responseactualizarRol_permisos = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responseactualizarRol_permisos;
	}
	
	@PostMapping("/AltaRoles")
	public ResponseEntity<?> postRoles(@RequestBody RolesDto  rolesDto) {

		LOG.info("get Roles - get Roles() Method");
		ResponseEntity<?> responsealtaRoles= null;

		try {

			String altaRoles = null;

			altaRoles = AltaRoles.AltaRoles(rolesDto);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Alta Roles");
			responseContenido.setContenido(altaRoles);
			responsealtaRoles = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Alta Roles");
			responsealtaRoles = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responsealtaRoles;
	}

	@PostMapping("/ActualizarRoles")
	public ResponseEntity<?> updateRoles(@RequestBody Roles roles) {

		LOG.info("update Roles - update Roles() Method");
	
		ResponseEntity<?> responseactualizarRoles = null;

		try {

			String actualizarRoles = null;

			actualizarRoles = ActualizarRoles.actualizarRoles(roles);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Actualizar Roles");
			responseContenido.setContenido(actualizarRoles);
			responseactualizarRoles= new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("error codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Actualizar Roles");
			responseactualizarRoles = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responseactualizarRoles;
	}
	
	
	@PostMapping("/AltaVentas")
	public ResponseEntity<?> postVentas(@RequestBody VentasDto  ventasDto) {

		LOG.info("get Ventas - get Ventas() Method");
		ResponseEntity<?> responsealtaVentas= null;

		try {

			String altaVentas = null;

			altaVentas = AltaVentas.AltaVentas(ventasDto);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Alta Ventas");
			responseContenido.setContenido(altaVentas);
			responsealtaVentas = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Alta Ventas");
			responsealtaVentas = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responsealtaVentas;
	}

	@PostMapping("/ActualizarVentas")
	public ResponseEntity<?> updateVentas(@RequestBody Ventas ventas) {

		LOG.info("update Ventas - update Ventas() Method");
	
		ResponseEntity<?> responseactualizarVentas = null;

		try {

			String actualizarVentas = null;

			actualizarVentas = ActualizarVentas.actualizarVentas(ventas);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Actualizar Ventas");
			responseContenido.setContenido(actualizarVentas);
			responseactualizarVentas= new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("error codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Actualizar Ventas");
			responseactualizarVentas = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responseactualizarVentas;
	}
	
	
}
