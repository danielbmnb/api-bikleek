package co.bikleek.productos.models;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUCTOS")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Productos implements Serializable{

	private static final long serialVersionUID = 7715625188089324368L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "NOMBRE_PRODUCTO", length = 100, nullable = false)
	private String nombreProducto;
	
	@Column(name = "DESCRIPCION_PRODUCTO", length = 200, nullable = false)
	private String descripcionProducto;
	
	@Column(name = "PRECIO", nullable = false)
	private Double precio;
	
	@Column(name = "CANTIDAD", nullable = false)
	private Long cantidad;
	
	@Column(name = "VALOR_TOTAL_PRODUCTO", nullable = false)
	private Double valorTotalProducto;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "CATEGORIA_ID", nullable = false)
	private Categorias categoriaId;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ESTADO_ID", nullable = false)
	private Estados estadoId;
	
	@Column(name = "FECHA_REGISTRO", nullable = false)
	private Date fechaRegistro;
	
}
