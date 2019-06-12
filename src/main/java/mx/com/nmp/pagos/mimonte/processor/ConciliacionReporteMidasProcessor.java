/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.processor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosTransitoBuilder;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportesWrapper;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoTransito;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;
import mx.com.nmp.pagos.mimonte.observer.MergeReporteHandler;
import mx.com.nmp.pagos.mimonte.util.ConciliacionMathUtil;

/**
 * Nombre: ConciliacionProcessor
 * Descripcion: Clase que se encarga de procesar el reporte midas y generar los movimientos correspondientes (transito)
 *
 * @author JGALVEZ
 * Fecha: 04/06/2019 9:44 PM
 * @version 0.1
 */
public class ConciliacionReporteMidasProcessor extends ConciliacionProcessorChain {

	public ConciliacionReporteMidasProcessor(MergeReporteHandler mergeReporteHandler) {
		super(mergeReporteHandler);
	}


	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.processor.ConciliacionProcessorChain#process(mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte)
	 */
	public void process(ReportesWrapper reportesWrapper) throws ConciliacionException {
	
		if (reportesWrapper.contains(TipoReporteEnum.MIDAS) && reportesWrapper.contains(TipoReporteEnum.PROVEEDOR)) {

			// Se extraen los movimientos con error, se vuelve a validar montos
			List<MovimientoMidas> movsMidas = getMovimientosMidas(reportesWrapper.getIdReporte(TipoReporteEnum.MIDAS));
			List<MovimientoProveedor> movsProveedor = getMovimientosProveedor(reportesWrapper.getIdReporte(TipoReporteEnum.PROVEEDOR));

			List<MovimientoTransito> movsTransito = extraerMovimientosTransito(movsMidas, movsProveedor);

			if (CollectionUtils.isNotEmpty(movsTransito)) {
				if (CollectionUtils.isNotEmpty(movsTransito)) {
					mergeReporteHandler.getMovimientoTransitoRepository().saveAll(movsTransito);
				}
			}
			
		}
		processNext(reportesWrapper);
	}


	/**
	 * Compara los montos de los movimientos en transito VS los montos de los movimientos midas
	 * @param movsMidas
	 * @param movsProveedor
	 * @return
	 */
	private List<MovimientoTransito> extraerMovimientosTransito(List<MovimientoMidas> movsMidas, List<MovimientoProveedor> movsProveedor) {

		Map<String, List<MovimientoMidas>> movsMidasByTransaction = mapMovMidasByTransaction(movsMidas);
		Map<String, List<MovimientoProveedor>> movsProveedorByTransaction = mapByMovsTransactionByTransaction(movsProveedor);

		List<MovimientoTransito> movsTransito = new ArrayList<MovimientoTransito>();
		
		if (!movsProveedorByTransaction.isEmpty()) {
			for (Map.Entry<String, List<MovimientoProveedor>> movProveedor : movsProveedorByTransaction.entrySet()) {
				
				List<MovimientoProveedor> movsProveedorTransaccion = movProveedor.getValue();
				List<MovimientoMidas> movsMidasTransaccion = movsMidasByTransaction.get(movProveedor.getKey());
				
				// Validar monto de la transaccion sea igual al reportado en midas
				BigDecimal montoProveedor = ConciliacionMathUtil.getImporteProveedor(movsProveedorTransaccion);
				BigDecimal montoMidas = ConciliacionMathUtil.getImporteMidas(movsMidasTransaccion);
				if (montoProveedor.compareTo(montoMidas) != 0) {
					for (MovimientoMidas movMidasTransaccion : movsMidasTransaccion) {
						movsTransito.add(MovimientosTransitoBuilder.buildMovTransitoFromMovMidas(movMidasTransaccion));
					}
				}
			}
		}

		return movsTransito;
	}


	/**
	 * Agrupa los movimientos midas por transaccion id
	 * @param movsMidas
	 * @return
	 */
	private Map<String, List<MovimientoMidas>> mapMovMidasByTransaction(List<MovimientoMidas> movsMidas) {
		Map<String, List<MovimientoMidas>> movsMidasByTransaction = new LinkedHashMap<String, List<MovimientoMidas>>();
		if (movsMidas != null) {
			for (MovimientoMidas movMidas : movsMidas) {
				List<MovimientoMidas> movsMidasTransaccion = movsMidasByTransaction.get(movMidas.getTransaccion());
				if (movsMidasTransaccion == null) {
					movsMidasByTransaction.put(String.valueOf(movMidas.getTransaccion()), new ArrayList<MovimientoMidas>());
				}
				movsMidasByTransaction.get(String.valueOf(movMidas.getTransaccion())).add(movMidas);
			}
		}
		return movsMidasByTransaction;
	}


	/**
	 * Agrupa los movimientos proveedor por order id (transaccion id)
	 * @param movsProveedor
	 * @return
	 */
	private Map<String, List<MovimientoProveedor>> mapByMovsTransactionByTransaction(List<MovimientoProveedor> movsProveedor) {
		Map<String, List<MovimientoProveedor>> movsProveedorByTransaction = new LinkedHashMap<String, List<MovimientoProveedor>>();
		if (movsProveedor != null) {
			for (MovimientoProveedor movProveedor : movsProveedor) {
				List<MovimientoProveedor> movsMidasTransaccion = movsProveedorByTransaction.get(movProveedor.getOrderId());
				if (movsMidasTransaccion == null) {
					movsProveedorByTransaction.put(movProveedor.getOrderId(), new ArrayList<MovimientoProveedor>());
				}
				movsProveedorByTransaction.get(movProveedor.getOrderId()).add(movProveedor);
			}
		}
		return movsProveedorByTransaction;
	}


	private List<MovimientoTransito> extraerMovimientosNoIdentificadosMidas(ReportesWrapper reportesWrapper, List<MovimientoMidas> movsMidas, List<MovimientoProveedor> movsProveedor) {
		
		List<MovimientoTransito> transito = new ArrayList<MovimientoTransito>();

		// Verificar los movimientos con error
		for (MovimientoMidas movMidas : movsMidas) {
			if (!movMidas.getEstatus()) {

				MovimientoTransito movTransito = MovimientosTransitoBuilder.buildMovTransitoFromMovMidas(movMidas);

				// Verificar si ya existe el movimiento de transito con anteriodad
				List<MovimientoTransito> movimientosTransitoBD = this.mergeReporteHandler.getMovimientoTransitoRepository().findByIdConciliacionAndMovimientoMidasId(reportesWrapper.getIdConciliacion(), movMidas.getId());
				if (CollectionUtils.isNotEmpty(movimientosTransitoBD)) {
					movTransito.setId(movimientosTransitoBD.get(0).getId());
					movTransito.setLastModifiedBy(ConciliacionConstants.USER_SYSTEM);
					movTransito.setLastModifiedDate(new Date());
				}
				else {
					movTransito.setIdConciliacion(reportesWrapper.getIdConciliacion());
					transito.add(movTransito);
				}
			}
		}

		return transito;
	}

}
