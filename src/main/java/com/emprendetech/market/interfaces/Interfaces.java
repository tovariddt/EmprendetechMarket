package com.emprendetech.market.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.entitys.Categorias;
import com.emprendetech.market.entitys.Clientes;
import com.emprendetech.market.entitys.Detallespedido;
import com.emprendetech.market.entitys.Emprendimientos;
import com.emprendetech.market.entitys.Medidas;
import com.emprendetech.market.entitys.Metodospago;
import com.emprendetech.market.entitys.Pedidos;
import com.emprendetech.market.entitys.Permisos;
import com.emprendetech.market.entitys.Personas;
import com.emprendetech.market.entitys.Productos;
import com.emprendetech.market.entitys.Productosunidad;
import com.emprendetech.market.entitys.Roles;
import com.emprendetech.market.entitys.Rolpermisos;
import com.emprendetech.market.entitys.Usuario;
import com.emprendetech.market.entitys.Ventas;
import com.emprendetech.market.service.requestDto.CategoriaDto;
import com.emprendetech.market.service.requestDto.ClientesDto;
import com.emprendetech.market.service.requestDto.DetallespedidoDto;
import com.emprendetech.market.service.requestDto.MedidasDto;
import com.emprendetech.market.service.requestDto.MetodospagoDto;
import com.emprendetech.market.service.requestDto.PedidosDto;
import com.emprendetech.market.service.requestDto.PermisosDto;
import com.emprendetech.market.service.requestDto.ProductoDto;
import com.emprendetech.market.service.requestDto.ProductosunidadDto;
import com.emprendetech.market.service.requestDto.RegistroDto;
import com.emprendetech.market.service.requestDto.RolesDto;
import com.emprendetech.market.service.requestDto.RolpermisosDto;
import com.emprendetech.market.service.requestDto.UsuarioContrasenaDto;
import com.emprendetech.market.service.requestDto.VentasDto;
import com.emprendetech.market.service.responseDto.PostalRespDto;
import com.emprendetech.market.service.responseDto.ProductosRespDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface Interfaces {

	@GetMapping("/roles")
	@Operation(summary = "Obtiene una lista de roles")
	@ApiResponse(responseCode = "200", description = "Con este metodo se obtiene la lista de roles")
	ResponseEntity<?> getAllRoles();

	@GetMapping("/perfiles")
	@Operation(summary = "Obtiene una lista de perfiles")
	@ApiResponse(responseCode = "200", description = "Con este metodo se obtiene la lista de perfiles")
	ResponseEntity<?> getAllPerfiles();

	@GetMapping("/codigopostal")
	@Operation(summary = "Obtiene la informacion de un codigo postal")
    @ApiResponse(responseCode = "200", description = "Con este metodo se obtiene la informacion de un codigo postal solicitado ")
	ResponseEntity<?> getAllCodigoPostal(@RequestBody PostalRespDto postalrespDto);
	
	@PostMapping("/alta")
	@Operation(summary = "Se realiza un registro de Persona, Usuario y Emprendimiento")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza un registro de Persona, Usuario y Emprendimiento")
	public ResponseEntity<?> postRegistro(@RequestBody RegistroDto registrodto);
	
	@PostMapping("/actualizar/usuario")
	@Operation(summary = "Se realiza una actualizacion de Usuario")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una actualizacion de Usuario")
	public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario);
	
	@PostMapping("/actualizar/personas")
	@Operation(summary = "Se realiza una actualizacion de Persona")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una actualizacion de Persona")
	public ResponseEntity<?> updatePersonas(@RequestBody Personas personas);
	
	@PostMapping("/actualizar/emprendimientos")
	@Operation(summary = "Se realiza un actualizacion de Emprendimiento")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una actualizacion de Emprendimiento")
	public ResponseEntity<?> updateEmprendimientos(@RequestBody Emprendimientos emprendimientos);
	
	@GetMapping("/correo")
	@Operation(summary = "Se realiza una comprobacion de que el correo existe")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una comprobacion de que el correo existe en la base de datos")
	public ResponseEntity<?> getCorreo(@RequestBody Usuario correodto);
	
	@GetMapping("/consulta")
	@Operation(summary = "Se realiza una consulta a Usuario")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una consulta a Usuario")
	public ResponseEntity<?> getUsuarioPersona(@RequestBody UsuarioContrasenaDto CorroContrasena);
	
	@GetMapping("/categorias")
	@Operation(summary = "Obtiene una lista de categorias")
	@ApiResponse(responseCode = "200", description = "Con este metodo se obtiene la lista de categorias")
	public ResponseEntity<?> getAllCategorias();
	
	@PostMapping("/alta/producto")
	@Operation(summary = "Se realiza un registro de producto")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza un registro de producto")
	public ResponseEntity<?> postProducto(@RequestBody ProductoDto productoDto);
	
	@PostMapping("/actualizar/producto")
	@Operation(summary = "Se realiza una actualizacion de producto")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una actualizacion de producto")
	public ResponseEntity<?> updateProducto(@RequestBody Productos productos);
	
	@GetMapping("/consulta/productoemprendimiento")
	@Operation(summary = "Se realiza una consulta a producto emprendimiento")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una consulta a producto emprendimiento")
	public ResponseEntity<?> getproductoEmprendimiento(@RequestBody ProductosRespDto idemprendimientos);
	
	@GetMapping("/consulta/productoemprendimientocategoria")
	@Operation(summary = "Se realiza una consulta a producto emprendimiento categoria")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una consulta a producto emprendimiento categoria")
	public ResponseEntity<?> getproductoEmprendimientoCategoria(@RequestBody ProductosRespDto emprendimientoscategoria );
	
	@GetMapping("/consulta/productoemprendimientonombrelike")
	@Operation(summary = "Se realiza una consulta por producto emprendimiento nombrelike")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una consulta por producto emprendimiento nombrelike")
	public ResponseEntity<?> getproductoEmprendimientoNombreLIKE(@RequestBody ProductosRespDto emprendimientosNombreLIKE );
	
	@PostMapping("/alta/categoria")
	@Operation(summary = "Se realiza un registro de categoria")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza un registro de categoria")
	public ResponseEntity<?> postCategoria(@RequestBody CategoriaDto categoriaDto); 
	
	@PostMapping("/actualizar/categoria")
	@Operation(summary = "Se realiza una actualizacion de categoria")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una actualizacion de categoria")
	public ResponseEntity<?> updateCategorias(@RequestBody Categorias categorias) ;
	
	@PostMapping("/alta/clientes")
	@Operation(summary = "Se realiza un registro de clientes")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza un registro de clientes")
	public ResponseEntity<?> postClientes(@RequestBody ClientesDto clientesDto);
	
	@PostMapping("/actualizar/clientes")
	@Operation(summary = "Se realiza una actualizacion de clientes")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una actualizacion de clientes")
	public ResponseEntity<?> updateClientes(@RequestBody Clientes clientes);
	
	@PostMapping("/alta/medidas")
	@Operation(summary = "Se realiza un registro de medidas")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza un registro de medidas")
	public ResponseEntity<?> postMedidas(@RequestBody MedidasDto medidasDto);
	
	@PostMapping("/actualizar/medidas")
	@Operation(summary = "Se realiza una actualizacion de medidas")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una actualizacion de medidas")
	public ResponseEntity<?> updateMedidas(@RequestBody Medidas medidas);

	
	@PostMapping("/alta/detallespedido")
	@Operation(summary = "Se realiza un registro de detallespedido")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza un registro de detallespedido")
	public ResponseEntity<?> postDetallespedido(@RequestBody DetallespedidoDto detallespedidoDto);
	
	@PostMapping("/actualizar/detallespedido")
	@Operation(summary = "Se realiza una actualizacion de detallespedido")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una actualizacion de detallespedido")
	public ResponseEntity<?> updateDetallespedido(@RequestBody Detallespedido detallespedido);
	
	@PostMapping("/alta/pedidos")
	@Operation(summary = "Se realiza un registro de pedidos")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza un registro de pedidos")
	public ResponseEntity<?> postPedidos(@RequestBody PedidosDto pedidosDto);
	
	@PostMapping("/actualizar/pedidos")
	@Operation(summary = "Se realiza una actualizacion de pedidos")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una actualizacion de pedidos")
	public ResponseEntity<?> updatePedidos(@RequestBody Pedidos pedidos);
	
	@PostMapping("/alta/metodospago")
	@Operation(summary = "Se realiza un registro de metodospago")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza un registro de metodospago")
	public ResponseEntity<?> postMetodospago(@RequestBody MetodospagoDto metodospagoDto);
	
	@PostMapping("/actualizar/metodospago")
	@Operation(summary = "Se realiza una actualizacion de metodospago")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una actualizacion de metodospago")
	public ResponseEntity<?> updateMetodospago(@RequestBody Metodospago metodospago);
		
	@PostMapping("/alta/productosunidad")
	@Operation(summary = "Se realiza un registro de productosunidad")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza un registro de productosunidad")
	public ResponseEntity<?> postProductosunidad(@RequestBody ProductosunidadDto  productosunidadDto);
	
	@PostMapping("/actualizar/productosunidad")
	@Operation(summary = "Se realiza una actualizacion de productosunidad")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una actualizacion de productosunidad")
	public ResponseEntity<?> updateProductosunidad(@RequestBody Productosunidad productosunidad);
	
	@PostMapping("/alta/permisos")
	@Operation(summary = "Se realiza un registro de permisos")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza un registro de permisos")
	public ResponseEntity<?> postPermisos(@RequestBody PermisosDto  permisosDto);
	

	@PostMapping("/actualizar/permisos")
	@Operation(summary = "Se realiza una actualizacion de permisos")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una actualizacion de permisos")
	public ResponseEntity<?> updatePermisos(@RequestBody Permisos permisos);
	
	@PostMapping("/alta/rolpermisos")
	@Operation(summary = "Se realiza un registro de rolpermisos")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza un registro de rolpermisos")
	public ResponseEntity<?> postRolpermisos(@RequestBody RolpermisosDto  rolpermisosDto);
	
	@PostMapping("/actualizar/rolpermisos")
	@Operation(summary = "Se realiza una actualizacion de rolpermisos")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una actualizacion de rolpermisos")
	public ResponseEntity<?> updateRolpermisos(@RequestBody Rolpermisos rolpermisos);
	
	
	@PostMapping("/alta/roles")
	@Operation(summary = "Se realiza un registro de roles")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza un registro de roles")
	public ResponseEntity<?> postRoles(@RequestBody RolesDto  rolesDto);
	
	
	@PostMapping("/actualizar/roles")
	@Operation(summary = "Se realiza una actualizacion de roles")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una actualizacion de roles")
	public ResponseEntity<?> updateRoles(@RequestBody Roles roles);
	
	@PostMapping("/alta/ventas")
	@Operation(summary = "Se realiza un registro de ventas")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza un registro de ventas")
	public ResponseEntity<?> postVentas(@RequestBody VentasDto  ventasDto);
	
	@PostMapping("/actualizar/ventas")
	@Operation(summary = "Se realiza una actualizacion de ventas")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una actualizacion de ventas")
	public ResponseEntity<?> updateVentas(@RequestBody Ventas ventas);
	
}
