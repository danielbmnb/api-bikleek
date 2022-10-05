package co.bikleek.productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.bikleek.productos.models.Productos;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Long>{

	@Query(value = "SELECT (COALESCE(MAX(ID), 0) + 1) as CONSECUTIVO FROM bikleek.PRODUCTOS", nativeQuery = true)
	public Long consecutivo();
}
