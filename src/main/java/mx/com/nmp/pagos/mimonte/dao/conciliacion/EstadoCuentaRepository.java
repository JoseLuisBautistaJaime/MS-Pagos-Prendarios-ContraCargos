package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.util.Date;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstadoCuenta;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:59 PM
 */
@Repository("estadoCuentaRepository")
public interface EstadoCuentaRepository extends PagingAndSortingRepository<EstadoCuenta, Long> {

	/**
	 * Regresa un estado de cuenta por id de reporte y fecha carga
	 * @param idReporte
	 * @param fechaCarga
	 * @return
	 */
	public EstadoCuenta findOneByIdReporteAndFechaCarga(Long idReporte, Date fechaCarga);

}