package co.bikleek.productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import co.bikleek.productos.models.Categorias;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Long>{

	@Query(value = "SELECT COUNT(*) FROM bikleek.CATEGORIAS WHERE ID = :id", nativeQuery = true)
	public Integer existeCategoria(@Param("id")Long id);
}
