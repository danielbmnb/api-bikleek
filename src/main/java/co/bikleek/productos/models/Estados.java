package co.bikleek.productos.models;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ESTADOS")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Estados implements Serializable{

	private static final long serialVersionUID = -4787866730230018405L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "ESTADO", length = 30, nullable = false)
	private String estado;
	
	@Column(name = "FECHA_REGISTRO", nullable = false)
	private Date fechaRegistro;
	
}
