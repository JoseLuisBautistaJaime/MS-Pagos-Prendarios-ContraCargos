/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao;


import mx.com.nmp.pagos.mimonte.model.Catalogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Nombre: CatalogoRepository
 * Descripcion: Repositorio que realiza las consultas sobre la entidad {@link Catalogo}
 *
 * @author Javier Hernandez jhernandez@quarksoft.net
 * Fecha: 22/02/2018 11:06 AM
 * @version 0.1
 */
@Repository
public interface CatalogoRepository extends JpaRepository<Catalogo, Integer> {

    /**
     * Metodo que obtiene el {@link Catalogo} correspondiente a la descripcion especificada.
     * @param descripcionCorta Descripcion corta que pertenece al catalogo
     * @return Catalogo Objeto con los metadatos correspondientes al catalogo.
     */
    Catalogo findByDescripcionCorta(String descripcionCorta);

    /**
     * Metodo que obtiene el listado de los extrafilter activos registrados en el sistema.
     * @return List<Catalogo> Lista con los extrafilter activos registrados en el sistema.
     */
    @Query("SELECT catalogo FROM Catalogo catalogo WHERE catalogo.activo = true")
    List<Catalogo> findAllByActivoIsTrue();
    
    
    /**
     * Metodo que obtiene el listado de los extrafilter activos registrados en el sistema.
     * @return List<Catalogo> Lista con los extrafilter activos registrados en el sistema.
     */
    @Query("SELECT catalogo.descripcionCorta FROM Catalogo catalogo WHERE catalogo.activo = true")
    List<Catalogo> findByDescripcionCorta();
    
    
    /**
     * Metodo que obtiene un listado de tipo Object de los catalogos registrados en el sistema.
     * @param descripcionCorta
     * @return List<Object[]> Lista del Objeto de los catalogos registrados en el sistema.
     */
    @Query(value = "SELECT \r\n" + 
    		"    catalogo.id as id, \r\n" + 
    		"    catalogo.descripcion_corta as descripcion_corta, \r\n" + 
    		"    catalogo.descripcion as descripcion\r\n" + 
    		"FROM\r\n" + 
    		"    compose.tk_catalogo as catalogo\r\n" + 
    		"WHERE\r\n" + 
    		"    catalogo.descripcion_corta like :descripcionCorta ", nativeQuery = true)
	public List<Object[]> findByDescripcion(@Param("descripcionCorta") String descripcionCorta);


}
