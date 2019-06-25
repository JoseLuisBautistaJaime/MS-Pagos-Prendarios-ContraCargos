package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.Layout;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:34:11 PM
 */
@Repository
public interface LayoutsRepository extends JpaRepository<Layout,Long>{
    
	@Modifying
	@Query("delete from LayoutLinea l where l.layout.id = :id")
	public void  eliminarUnLayoutLineas(@Param("id") final Long id);
	@Modifying
	@Query("delete from LayoutHeader l where l.layout.id = :id")
	public void  eliminarUnLayoutHeader(@Param("id") final Long id);
	
	@Query("from Layout l inner join l.layoutHeader inner join l.layoutLineas where l.idConciliacion = :idConciliacion and l.tipo = :tipo")
	public List<Object[]> findByIdConciliacionAndTipo(@Param("idConciliacion") final Long idConciliacion, @Param("tipo") final String tipo);
	
	public List<Layout> findByIdConciliacion(@Param("idConciliacion") final Long idConciliacion);
	
	public List<Layout> findByTipo(@Param("tipo") final String tipo);
	
}





