package co.bikleek.productos.dto;

import java.io.Serializable;
import co.bikleek.productos.models.Categorias;
import co.bikleek.productos.models.Estados;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoDTO implements Serializable{
	
	private static final long serialVersionUID = -3467294440838294175L;

	private String nombreProducto;
	private String descripcionProducto;
	private Double precio;
	private Long cantidad;
	private Estados estado;
	private Categorias categoria;
	
}
