package co.bikleek.productos.interfaces;

import java.util.List;
import java.util.Map;
import co.bikleek.productos.dto.ProductoDTO;
import co.bikleek.productos.models.Productos;

public interface IProductos {

	List<Productos> listarProductos();
	Map<String, Object> encontrarProducto(Long id);
	Map<String, Object> guardarProducto(ProductoDTO productoDTO);
	Map<String, Object> editarProducto(Long id, ProductoDTO productoDTO);
	Map<String, Object> cambiarEstado(Long id, ProductoDTO productoDTO);
	Map<String, Object> eliminarProducto(Long id);
}
