package co.bikleek.productos.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.bikleek.productos.dto.ProductoDTO;
import co.bikleek.productos.models.Productos;
import co.bikleek.productos.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(value = "productos")
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@Operation(summary = "Método para listar los productos", description = "Este metodo nos permite"
			+ "listar los productos")
	@ApiResponse(responseCode = "202", description = "Listado de productos exitosamente")
	@ApiResponse(responseCode = "400", description = "Se ha generado el recurso por la petición sin respuesta")
	@ApiResponse(responseCode = "404", description = "No se encontró la petición para el servidor")
	@ApiResponse(responseCode = "500", description = "Internal Error - No se puede procesar la petición solicitada")
	@GetMapping(value = "/listarProductos")
	public ResponseEntity<List<Productos>> listarProductos(){
		
		var listaProductos = productoService.listarProductos();
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(listaProductos);
	}
	
	@Operation(summary = "Método para mostrar el detalle del producto", description = "Este metodo nos permite"
			+ "mostrar el detalle del producto")
	@ApiResponse(responseCode = "202", description = "Detalle del producto exitosamente")
	@ApiResponse(responseCode = "400", description = "Se ha generado el recurso por la petición sin respuesta")
	@ApiResponse(responseCode = "404", description = "No se encontró la petición para el servidor")
	@ApiResponse(responseCode = "500", description = "Internal Error - No se puede procesar la petición solicitada")
	@GetMapping(value = "/detalleProducto/{id}")
	public ResponseEntity<Map<String, Object>> detalleProducto(@PathVariable Long id){
		
		var detalleProducto = productoService.encontrarProducto(id);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(detalleProducto);
		
	}
	
	@Operation(summary = "Método para guardar un producto", description = "Este metodo nos permite"
			+ "realizar el guardado de un producto")
	@ApiResponse(responseCode = "202", description = "Producto guardado exitosamente")
	@ApiResponse(responseCode = "400", description = "Se ha generado el recurso por la petición sin respuesta")
	@ApiResponse(responseCode = "404", description = "No se encontró la petición para el servidor")
	@ApiResponse(responseCode = "500", description = "Internal Error - No se puede procesar la petición solicitada")
	@PostMapping(value = "/guardarProducto")
	public ResponseEntity<Map<String, Object>> guardarProducto(@RequestBody ProductoDTO productoDTO){
		
		var productoGuardado = productoService.guardarProducto(productoDTO);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(productoGuardado);
		
	}
	
	@Operation(summary = "Método para editar un producto", description = "Este metodo nos permite"
			+ "realizar el editar de un producto")
	@ApiResponse(responseCode = "202", description = "Producto actualizado exitosamente")
	@ApiResponse(responseCode = "400", description = "Se ha generado el recurso por la petición sin respuesta")
	@ApiResponse(responseCode = "404", description = "No se encontró la petición para el servidor")
	@ApiResponse(responseCode = "500", description = "Internal Error - No se puede procesar la petición solicitada")
	@PutMapping(value = "/editarProducto/{id}")
	public ResponseEntity<Map<String, Object>> editarProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO){
		
		var productoEditado = productoService.editarProducto(id, productoDTO);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(productoEditado);
		
	}
	
	@Operation(summary = "Método para editar un producto", description = "Este metodo nos permite"
			+ "realizar el editar de un producto")
	@ApiResponse(responseCode = "202", description = "Producto actualizado exitosamente")
	@ApiResponse(responseCode = "400", description = "Se ha generado el recurso por la petición sin respuesta")
	@ApiResponse(responseCode = "404", description = "No se encontró la petición para el servidor")
	@ApiResponse(responseCode = "500", description = "Internal Error - No se puede procesar la petición solicitada")
	@PutMapping(value = "/cambiarEstadoProducto/{id}")
	public ResponseEntity<Map<String, Object>> cambiarEstadoProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO){
		
		var productoCambiadoEstado = productoService.cambiarEstado(id, productoDTO);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(productoCambiadoEstado);
		
	}
	
	@Operation(summary = "Método para eliminar un producto", description = "Este metodo nos permite"
			+ "realizar el borrado de un producto")
	@ApiResponse(responseCode = "202", description = "Producto actualizado exitosamente")
	@ApiResponse(responseCode = "400", description = "Se ha generado el recurso por la petición sin respuesta")
	@ApiResponse(responseCode = "404", description = "No se encontró la petición para el servidor")
	@ApiResponse(responseCode = "500", description = "Internal Error - No se puede procesar la petición solicitada")
	@DeleteMapping(value = "/eliminarProducto/{id}")
	public ResponseEntity<Map<String, Object>> eliminarProducto(@PathVariable Long id){
		
		var productoEliminado = productoService.eliminarProducto(id);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(productoEliminado);
		
	}
	
}
