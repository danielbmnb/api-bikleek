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
@Table(name = "CATEGORIAS")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Categorias implements Serializable{
	
	private static final long serialVersionUID = -7745522290509377747L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "NOMBRE_CATEGORIA", length = 100, nullable = false)
	private String nombreProducto;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ESTADO_ID", nullable = false)
	private Estados estadoId;
	
	@Column(name = "FECHA_REGISTRO", nullable = false)
	private Date fechaRegistro;
}
