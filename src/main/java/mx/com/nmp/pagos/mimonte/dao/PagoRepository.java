package mx.com.nmp.pagos.mimonte.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.Pago;

/**
 * Nombre: PagoRepository Descripcion: Repositorio que realiza consultas
 * referentes a pagos
 *
 * @author: Ismael Flores iaguilar@quarksoft.net
 * @creationDate 23/11/2018 10:44 hrs.
 * @version: 0.1
 */
@Repository("pagoRepository")
public interface PagoRepository extends JpaRepository<Pago, Integer> {

	/**
	 * 
	 * Metodo que consulta si ya existe un pago a registrar en base de datos para no
	 * duplicarlo
	 * 
	 * @param nombre
	 * @param idcliente
	 * @param monto
	 * @return
	 */
	@Query("SELECT COUNT(p.id) FROM Pago p WHERE p.idTransaccionMidas = :idTransaccionMidas AND p.folioPartida = :folioContrato AND p.idOperacion = :idOperacion AND p.monto = :montoOperacion")
	public Integer checkIfPagoExists(@Param("idTransaccionMidas") Integer idTransaccionMidas,
			@Param("folioContrato") Integer folioContrato, @Param("idOperacion") Integer idOperacion,
			@Param("montoOperacion") Double montoOperacion);
}
