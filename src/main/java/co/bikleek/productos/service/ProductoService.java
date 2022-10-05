package co.bikleek.productos.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.bikleek.productos.dto.ProductoDTO;
import co.bikleek.productos.interfaces.IProductos;
import co.bikleek.productos.models.Estados;
import co.bikleek.productos.models.Productos;
import co.bikleek.productos.repository.CategoriasRepository;
import co.bikleek.productos.repository.ProductosRepository;

@Service
public class ProductoService implements IProductos {

	@Autowired
	private ProductosRepository productosRepository;

	@Autowired
	private CategoriasRepository categoriasRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Productos> listarProductos() {
		return productosRepository.findAll();
	}

	@Override
	public Map<String, Object> encontrarProducto(Long id) {

		var retornoApi = new HashMap<String, Object>();
		var productoEncontrado = productosRepository.findById(id).orElse(null);

		try {

			if (productoEncontrado == null) {
				retornoApi.put("productoNoEncontrado", "El producto con la identificación " + id + " no fue "
						+ "encontrado, por favor verifique nuevamente.");
				return retornoApi;
			}
		} catch (Exception e) {
			retornoApi.put("error", "No se pudo encontrar el producto indicado, por favor intente más tarde.");
		}

		retornoApi.put("producto", productoEncontrado);
		return retornoApi;
	}

	@Override
	public Map<String, Object> guardarProducto(ProductoDTO productoDTO) {

		var retornoApi = new HashMap<String, Object>();
		var producto = new Productos();

		try {
			retornoApi = validarDatos(productoDTO);
			if (!retornoApi.isEmpty()) {
				return retornoApi;
			}

			producto = this.setearDatos(productoDTO);
			var estado = new Estados();
			estado.setId(2L);
			producto.setEstadoId(estado);

			if (producto != null) {

				// generamos el consecutivo del ID

				var consecutivo = productosRepository.consecutivo();

				producto.setId(consecutivo);

				productosRepository.save(producto);
			}

		} catch (Exception e) {
			retornoApi.put("resultadoTransaccion", -1);
			retornoApi.put("mensajeError",
					"Ocurrio un error al guardar el producto, por favor intentelo" + "más tarde.");
			return retornoApi;
		}

		retornoApi.put("resultadoTransaccion", 1);
		retornoApi.put("mensajeGuardar", "El producto se ha registrado con éxito");
		retornoApi.put("retornoApi", producto);

		return retornoApi;
	}

	@Override
	public Map<String, Object> editarProducto(Long id, ProductoDTO productoDTO) {

		var retornoApi = new HashMap<String, Object>();
		var productoEditar = productosRepository.findById(id).orElse(null);
		var productos = new Productos();
		try {

			retornoApi = validarDatos(productoDTO);
			if (!retornoApi.isEmpty()) {
				return retornoApi;
			}

			if (productoEditar != null) {

				productos = this.setearDatos(productoDTO);
				productos.setId(id);
				productos.setEstadoId(productoDTO.getEstado());
				productosRepository.save(productos);

			} else {
				retornoApi.put("", "No se encontró el producto con la identificación " + id + " por favor"
						+ " verifique nuevamente");
				return retornoApi;
			}
		} catch (Exception e) {
			retornoApi.put("resultadoTransaccion", -1);
			retornoApi.put("mensajeError",
					"Ocurrio un error al editar el producto, por favor intentelo" + "más tarde.");
			return retornoApi;
		}

		retornoApi.put("resultadoTransaccion", 1);
		retornoApi.put("mensajeEditar", "El producto se ha editado con éxito");
		retornoApi.put("retornoApi", productoEditar);
		return retornoApi;
	}

	@Override
	public Map<String, Object> cambiarEstado(Long id, ProductoDTO productoDTO) {

		var retornoApi = new HashMap<String, Object>();

		var productoCambiarEstado = productosRepository.findById(id).orElse(null);

		if (productoCambiarEstado != null) {

			productoCambiarEstado.setEstadoId(productoDTO.getEstado());
			productosRepository.save(productoCambiarEstado);

		} else {
			retornoApi.put("",
					"No se encontró el producto con la identificación " + id + " por favor" + " verifique nuevamente");
			return retornoApi;
		}

		retornoApi.put("resultadoTransaccion", 1);
		retornoApi.put("mensajeCambioEstado", "El producto se ha cambiado de estado éxitosamente");
		retornoApi.put("retornoApi", productoCambiarEstado);
		return retornoApi;
	}

	@Override
	public Map<String, Object> eliminarProducto(Long id) {

		var retornoApi = new HashMap<String, Object>();

		var productoEliminar = productosRepository.findById(id).orElse(null);

		if (productoEliminar != null) {
			productosRepository.deleteById(id);

		} else {
			retornoApi.put("",
					"No se encontró el producto con la identificación " + id + " por favor" + " verifique nuevamente");
			return retornoApi;
		}

		retornoApi.put("resultadoTransaccion", 1);
		retornoApi.put("mensajeEliminado", "El producto se ha eliminado éxitosamente");
		return retornoApi;
	}

	private HashMap<String, Object> validarDatos(ProductoDTO productoDTO) {

		var retornoApi = new HashMap<String, Object>();

		var categoriaExistente = categoriasRepository.existeCategoria(productoDTO.getCategoria().getId());

		if (categoriaExistente <= 0) {

			retornoApi.put("categoriaNoExiste",
					"La categoria con la identificacion " + productoDTO.getCategoria().getId() + " no "
							+ " existe, por favor ingresa una categoria existente.");
		}

		if (productoDTO.getNombreProducto() == null) {
			retornoApi.put("nombreRequerido", "Debe indicar el nombre del producto");
		}

		if (productoDTO.getDescripcionProducto() == null) {
			retornoApi.put("descripcionRequerido", "Debe indicar la descripcion del producto");
		}

		if (productoDTO.getPrecio() == null) {
			retornoApi.put("precioRequerido", "Debe indicar el precio del producto");
		} else if (productoDTO.getPrecio() <= 0) {
			retornoApi.put("precioMayorCero", "El precio del producto debe ser mayor a cero (0)");
		}

		if (productoDTO.getCategoria() == null) {
			retornoApi.put("categoriaRequerida", "Debes agregar una categoria");
		}

		if (productoDTO.getCantidad() == null) {
			retornoApi.put("cantidadRequerida", "Debe indicar la cantidad del producto");
		} else if (productoDTO.getCantidad() <= 0) {
			retornoApi.put("precioMayorCero", "La cantidad del producto debe ser mayor a cero (0)");
		}

		return retornoApi;
	}

	private Productos setearDatos(ProductoDTO productoDTO) {

		var valorTotalProducto = 0.0;
		valorTotalProducto = productoDTO.getCantidad() * productoDTO.getPrecio();
		var producto = new Productos();

		producto.setNombreProducto(productoDTO.getNombreProducto());
		producto.setDescripcionProducto(productoDTO.getDescripcionProducto());
		producto.setCantidad(productoDTO.getCantidad());
		producto.setPrecio(productoDTO.getPrecio());
		producto.setValorTotalProducto(valorTotalProducto);
		producto.setCategoriaId(productoDTO.getCategoria());
		producto.setFechaRegistro(new Date());

		return producto;
	}
}
