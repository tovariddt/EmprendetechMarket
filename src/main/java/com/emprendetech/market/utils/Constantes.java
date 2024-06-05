package com.emprendetech.market.utils;

public class Constantes {

	    public static final String FELICIDADESALTACATEGORIAS = "Felicidades su Categoria fue registrado como =";
	    public static final String FELICIDADESACTUALIZARCATEGORIAS = "Felicidades su Categoria fue actualizado como =";

	    public static final String FELICIDADESALTACLIENTES = "Felicidades su Clientes fue registrado como =";
	    public static final String FELICIDADESACTUALIZARCLIENTES = "Felicidades su Cliente fue actualizado como =";

	    public static final String FELICIDADESALTADETALLESPEDIDO = "Felicidades su Detalles de Pedido fue registrado como =";
	    public static final String FELICIDADESACTUALIZARDETALLESPEDIDO = "Felicidades su Detalles de Pedido fue actualizado como =";

	    public static final String FELICIDADESALTAMEDIDAS = "Felicidades su Medida fue registrado como =";
	    public static final String FELICIDADESACTUALIZARMEDIDAS = "Felicidades su Medida fue actualizado como =";

	    public static final String FELICIDADESALTAMETODOSPAGO = "Felicidades su Metodo pago fue registrado como =";
	    public static final String FELICIDADESACTUALIZARMETODOSPAGO = "Felicidades su Metodo pago fue actualizado como =";

	    public static final String FELICIDADESALTAPEDIDOS = "Felicidades su Pedido fue registrado como =";
	    public static final String FELICIDADESACTUALIZARPEDIDOS = "Felicidades su Pedido fue actualizado como =";

	    public static final String FELICIDADESALTAPERMISOS = "Felicidades su Permiso fue registrado como =";
	    public static final String FELICIDADESACTUALIZARPERMISOS = "Felicidades su Permiso fue actualizado como =";

	    public static final String FELICIDADESALTAPRECIO = "Felicidades su Precio fue registrado como =";
	    public static final String FELICIDADESACTUALIZARPRECIO = "Felicidades su Precio fue actualizado como =";

	    public static final String FELICIDADESALTAPRODUCTO = "Felicidades su Producto fue registrado como =";
	    public static final String FELICIDADESACTUALIZARPRODUCTO = "Felicidades su Producto fue actualizado como =";

	    public static final String FELICIDADESALTAPRODUCTOSUNIDAD = "Felicidades su Producto Unidad fue registrado como =";
	    public static final String FELICIDADESACTUALIZARPRODUCTOSUNIDAD = "Felicidades su Producto Unidad fue actualizado como =";

	    public static final String FELICIDADESALTAUSUARIO = "Felicidades su Usuario fue registrado como =";
	    public static final String FELICIDADES = "Felicidades ";
	    public static final String FELICIDADESALTAEMPRENDIMIENTO = "Su emprendimiento fue registrado como =";
	    public static final String FELICIDADESACTUALIZARPERSONAS = "Felicidades su Persona fue actualizado como =";
	    public static final String FELICIDADESACTUALIZARUSUARIO = "Felicidades su Usuario fue actualizado como =";
	    public static final String FELICIDADESACTUALIZAREMPRENDIMIENTOS = "Felicidades su Emprendimiento fue actualizado como =";

	    public static final String FELICIDADESALTAROLPERMISOS = "Felicidades su Rol Permiso fue registrado como =";
	    public static final String FELICIDADESACTUALIZARROLPERMISOS = "Felicidades su Rol Permiso fue actualizado como =";

	    public static final String FELICIDADESALTAVENTAS = "Felicidades su Venta fue registrado como =";
	    public static final String FELICIDADESACTUALIZARVENTAS = "Felicidades su Venta fue actualizado como =";

	    public static final String SQLGETROLES = "SELECT idrol, nombre FROM emprendetech_market.roles";
	    
	    public static final String SQLGETPEFILES = "SELECT idperfil, nombre, descripcion, idrol FROM emprendetech_market.perfiles;";

	
	    public static final String SQLGETCODIGO = "SELECT * FROM emprendetech_market.codigo_postal where clave=";

	    public static final String SQLGETCORREO = "SELECT correo FROM emprendetech_market.usuario where correo='";

	    public static final String SQLGETEMP = "SELECT idperfil FROM emprendetech_market.perfiles where idperfil=" ;

	    public static final String SQLGETUSUARIOPERSONA = "SELECT nombreusuario, correo ,idperfil , nombre,apellido_paterno,apellido_materno,telefono, direccion ,id_codigo_postal\r\n"
				+ "FROM emprendetech_market.usuario\r\n"
				+ "INNER JOIN emprendetech_market.personas ON emprendetech_market.usuario.idpersona = emprendetech_market.personas.idpersona\r\n"
				+ "where emprendetech_market.usuario.contrasena='";

	    public static final String SQLGETUSUARIOPERSONACORREO = "' and emprendetech_market.usuario.correo= '";

	    public static final String SQLGETEMPRENDIMIENTOS = "SELECT emprendetech_market.emprendimientos.nombre,emprendetech_market.emprendimientos.descripcion, emprendetech_market.emprendimientos.industria\r\n"
				+ "FROM emprendetech_market.usuario\r\n"
				+ "INNER JOIN emprendetech_market.personas ON emprendetech_market.usuario.idpersona = emprendetech_market.personas.idpersona\r\n"
				+ "INNER JOIN emprendetech_market.emprendimientos ON emprendetech_market.usuario.idpersona = emprendetech_market.emprendimientos.idpersona\r\n"
				+ "where emprendetech_market.usuario.contrasena='";

	    
	    public static final String SQLGETEMPRENDIMIENTOSCORREO = "' and emprendetech_market.usuario.correo='" ;

	    public static final String SQLGETPRODUCTOEMPRENDIMIENTO ="SELECT * FROM emprendetech_market.productos where idemprendimiento= '" ;

	    public static final String SQLGETPRODUCTOEMPRENDIMIENTOCATEGORIA = "SELECT * FROM emprendetech_market.productos where idemprendimiento= '" ;

	    public static final String SQLGETPRODUCTOEMPRENDIMIENTOCATEGORIAID = "' and idcategoria='" ;

	    public static final String SQLGETCATEGORIA = "SELECT * FROM emprendetech_market.categorias;" ;

	    public static final String SQLGETPRODUCTOEMPRENDIMIENTONOMBRELIKE = "SELECT * FROM emprendetech_market.productos WHERE nombre LIKE '" ;

	    public static final String SQLGETPRODUCTOEMPRENDIMIENTONOMBRELIKEAND = "%' AND idemprendimiento = " ;

	    public static final String SQLOBTENERPRODUCTOSEMPRENDIMIENTOS ="SELECT emprendetech_market.emprendimientos.idemprendimiento, emprendetech_market.emprendimientos.nombre as nombreEmprendimiento, emprendetech_market.productos.idproducto, emprendetech_market.productos.sku, emprendetech_market.productos.nombre as nombreProducto, emprendetech_market.productos.idcategoria, emprendetech_market.productos.descripcion, emprendetech_market.productos.cantidad_disponible FROM emprendetech_market.productos INNER JOIN emprendetech_market.emprendimientos ON emprendetech_market.productos.idemprendimiento = emprendetech_market.emprendimientos.idemprendimiento;";;
	    
	    public static final String SQLOBTENERPRODUCTOSEMPRENDIMIENTOSID = "SELECT emprendetech_market.emprendimientos.idemprendimiento, emprendetech_market.emprendimientos.nombre as nombreEmprendimiento, emprendetech_market.productos.idproducto,emprendetech_market.productos.sku ,emprendetech_market.productos.nombre  as nombreProducto, emprendetech_market.productos.idcategoria , emprendetech_market.productos.descripcion, emprendetech_market.productos.cantidad_disponible FROM emprendetech_market.productos INNER JOIN emprendetech_market.emprendimientos ON emprendetech_market.productos.idemprendimiento = emprendetech_market.emprendimientos.idemprendimiento where emprendetech_market.emprendimientos.idemprendimiento = '";

    
}
