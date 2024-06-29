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


	ResponseEntity<?> getAllRoles();


	ResponseEntity<?> getAllPerfiles();


	ResponseEntity<?> getAllCodigoPostal(@RequestBody PostalRespDto postalrespDto);
	

	public ResponseEntity<?> postRegistro(@RequestBody RegistroDto registrodto);
	

	public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario);
	

	public ResponseEntity<?> updatePersonas(@RequestBody Personas personas);
	

	public ResponseEntity<?> updateEmprendimientos(@RequestBody Emprendimientos emprendimientos);

	public ResponseEntity<?> getCorreo(@RequestBody Usuario correodto);
	

	public ResponseEntity<?> getUsuarioPersona(@RequestBody UsuarioContrasenaDto CorroContrasena);
	

	public ResponseEntity<?> getAllCategorias();
	

	public ResponseEntity<?> postProducto(@RequestBody ProductoDto productoDto);
	

	public ResponseEntity<?> updateProducto(@RequestBody Productos productos);
	

	public ResponseEntity<?> getproductoEmprendimiento(@RequestBody ProductosRespDto idemprendimientos);
	
	public ResponseEntity<?> getproductoEmprendimientoCategoria(@RequestBody ProductosRespDto emprendimientoscategoria );
	
    public ResponseEntity<?> getproductoEmprendimientoNombreLIKE(@RequestBody ProductosRespDto emprendimientosNombreLIKE );
	
    public ResponseEntity<?> postCategoria(@RequestBody CategoriaDto categoriaDto); 
	
    public ResponseEntity<?> updateCategorias(@RequestBody Categorias categorias) ;
	
    public ResponseEntity<?> postClientes(@RequestBody ClientesDto clientesDto);
	
    public ResponseEntity<?> updateClientes(@RequestBody Clientes clientes);
	
    public ResponseEntity<?> postMedidas(@RequestBody MedidasDto medidasDto);
	
    public ResponseEntity<?> updateMedidas(@RequestBody Medidas medidas);

	public ResponseEntity<?> postDetallespedido(@RequestBody DetallespedidoDto detallespedidoDto);
	
	public ResponseEntity<?> updateDetallespedido(@RequestBody Detallespedido detallespedido);
	
	public ResponseEntity<?> postPedidos(@RequestBody PedidosDto pedidosDto);
	
	public ResponseEntity<?> updatePedidos(@RequestBody Pedidos pedidos);
	
	public ResponseEntity<?> postMetodospago(@RequestBody MetodospagoDto metodospagoDto);
	
    public ResponseEntity<?> updateMetodospago(@RequestBody Metodospago metodospago);
		
    public ResponseEntity<?> postProductosunidad(@RequestBody ProductosunidadDto  productosunidadDto);
	
    public ResponseEntity<?> updateProductosunidad(@RequestBody Productosunidad productosunidad);
    
    public ResponseEntity<?> postPermisos(@RequestBody PermisosDto  permisosDto);
	
    public ResponseEntity<?> updatePermisos(@RequestBody Permisos permisos);
	
    public ResponseEntity<?> postRolpermisos(@RequestBody RolpermisosDto  rolpermisosDto);
	
    public ResponseEntity<?> updateRolpermisos(@RequestBody Rolpermisos rolpermisos);
	
	
    public ResponseEntity<?> postRoles(@RequestBody RolesDto  rolesDto);
	
	public ResponseEntity<?> updateRoles(@RequestBody Roles roles);
	
    public ResponseEntity<?> postVentas(@RequestBody VentasDto  ventasDto);
	
    public ResponseEntity<?> updateVentas(@RequestBody Ventas ventas);
	
}
