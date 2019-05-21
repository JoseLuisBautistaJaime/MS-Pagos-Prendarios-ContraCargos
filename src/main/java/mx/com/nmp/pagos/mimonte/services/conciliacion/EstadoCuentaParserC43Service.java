/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.dto.EstadoCuentaWraper;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstadoCuentaFileLayout;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstadoCuentaFileLayoutTipoEnum;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstadoCuentaLine;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstadoCuentaCabecera;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstadoCuentaTotales;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstadoCuentaTotalesAdicional;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.services.EstadoCuentaParserService;
import mx.com.nmp.pagos.mimonte.util.DateUtil;
import mx.com.nmp.pagos.mimonte.util.EstadoCuentaLineQueue;

/**
 * @name EstadoCuentaReader43
 * 
 * @description Clase que se encarga del parseo del archivo de estado de cuenta usando la implementacion cuaderno 43
 * @author Quarksoft
 * @version 1.0
 */
@Component
public class EstadoCuentaParserC43Service implements EstadoCuentaParserService {


	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.services.EstadoCuentaParser#extract(mx.com.nmp.pagos.mimonte.dto.conciliacion.EstadoCuentaFileLayout)
	 */
	public EstadoCuentaWraper extract(EstadoCuentaFileLayout estadoCuentaFile) {

		if (estadoCuentaFile == null) {
			throw new ConciliacionException("Archivo de estado de cuenta invalido");
		}

		
		// Extraer cabecera del estado de cuenta
		EstadoCuentaCabecera cabecera = extractCabecera(estadoCuentaFile);
		List<MovimientoEstadoCuenta> movimientos = extractMovimientoEstadoCuenta(estadoCuentaFile);
		EstadoCuentaTotales totales = extractTotales(estadoCuentaFile);
		EstadoCuentaTotalesAdicional totalesAdicional = extractTotalesAdicional(estadoCuentaFile);

		if (cabecera == null) {
			throw new ConciliacionException("Archivo de estado de cuenta contiene cabecera incorrecta");
		}

		EstadoCuentaWraper estadoCuenta = new EstadoCuentaWraper();
		estadoCuenta.cabecera = cabecera;
		estadoCuenta.totales = totales;
		estadoCuenta.totalesAdicional = totalesAdicional;
		estadoCuenta.movimientos = movimientos;

		return estadoCuenta;
	}


	private EstadoCuentaCabecera extractCabecera(EstadoCuentaFileLayout estadoCuentaFile) throws ConciliacionException {

		EstadoCuentaCabecera cabecera = null;
		
		for (EstadoCuentaLine line : estadoCuentaFile.getRegistros()) {
			if (line.getTipo() == EstadoCuentaFileLayoutTipoEnum.C43_11) {

				if (line.getTipo().valido(line.getLinea())) {
					throw new ConciliacionException("La linea " + line.getIndex() + " tiene una longitud invalida");
				}

				EstadoCuentaLineQueue lineQueue = new EstadoCuentaLineQueue(line.getLinea());
				cabecera = new EstadoCuentaCabecera();
				lineQueue.get(2); // Registro
				cabecera.setClavePais(lineQueue.get(4)); // Clave Pais
				cabecera.setSucursal(lineQueue.get(4)); // Sucursal
				cabecera.setCuenta(lineQueue.get(10)); // Cuenta
				cabecera.setFechaInicial(DateUtil.getDate(lineQueue.get(6), "yyMMdd")); // Fecha inicial
				cabecera.setFechaFinal(DateUtil.getDate(lineQueue.get(6), "yyMMdd")); // Fecha final
				cabecera.setTipoSaldo(Integer.valueOf(lineQueue.get(1))); // Tipo Saldo
				cabecera.setSaldoInicial(new BigDecimal(lineQueue.get(14))); // Saldo inicial1
				cabecera.setMonedaAlfabetica(lineQueue.get(3)); // Moneda alfabetica
				cabecera.setDigitoCuentaClabe(lineQueue.get(1)); // Digito cuenta clabe
				cabecera.setTitularCuenta(lineQueue.get(23)); // Titular cuenta
				cabecera.setPlazaCuentaClabe(lineQueue.get(3)); // Plaza cuenta clabe
				cabecera.setLibre(lineQueue.get(3)); // Libre
				break;
			}
		}

		return cabecera;
	}


	/**
	 * Extrae el movimiento de estado de cuenta
	 * @param estadoCuentaFile
	 * @return
	 * @throws ConciliacionException
	 */
	private List<MovimientoEstadoCuenta> extractMovimientoEstadoCuenta(EstadoCuentaFileLayout estadoCuentaFile) throws ConciliacionException {

		List<MovimientoEstadoCuenta> lineas = new ArrayList<MovimientoEstadoCuenta>();
		
		for (EstadoCuentaLine line : estadoCuentaFile.getRegistros()) {
			if (line.getTipo() == EstadoCuentaFileLayoutTipoEnum.C43_22) {

				if (line.getTipo().valido(line.getLinea())) {
					throw new ConciliacionException("La linea " + line.getIndex() + " tiene una longitud invalida");
				}

				EstadoCuentaLineQueue lineQueue = new EstadoCuentaLineQueue(line.getLinea());
				
				MovimientoEstadoCuenta movimiento = new MovimientoEstadoCuenta();
				lineQueue.get(2); // Registro
				movimiento.setClavePais(lineQueue.get(4)); // Clave Pais
				movimiento.setSucursal(lineQueue.get(4)); // Sucursal
				movimiento.setFechaOperacion(DateUtil.getDate(lineQueue.get(6), "yyMMdd")); // Fecha operacion
				movimiento.setFechaValor(DateUtil.getDate(lineQueue.get(6), "yyMMdd")); // Fecha valor
				movimiento.setLibre(lineQueue.get(2)); // Uso futuro
				movimiento.setClaveLeyenda(lineQueue.get(3)); // TODO: Catalogo de leyendas
				movimiento.setTipoMovimiento(Integer.valueOf(lineQueue.get(1))); // Cargo = 1, Abono = 2
				movimiento.setImporte(new BigDecimal(lineQueue.get(14))); // Importe
				movimiento.setDato(lineQueue.get(10)); // Dato
				movimiento.setConcepto(lineQueue.get(28)); // Concepto

				lineas.add(movimiento);
			}
		}

		return lineas;
	}


	private EstadoCuentaTotales extractTotales(EstadoCuentaFileLayout estadoCuentaFile) throws ConciliacionException {

		EstadoCuentaTotales totales = null;
		
		for (EstadoCuentaLine line : estadoCuentaFile.getRegistros()) {
			if (line.getTipo() == EstadoCuentaFileLayoutTipoEnum.C43_32) {

				if (line.getTipo().valido(line.getLinea())) {
					throw new ConciliacionException("La linea " + line.getIndex() + " tiene una longitud invalida");
				}

				EstadoCuentaLineQueue lineQueue = new EstadoCuentaLineQueue(line.getLinea());
				totales = new EstadoCuentaTotales();
				lineQueue.get(2); // Registro
				totales.setClavePais(lineQueue.get(3)); // Clave Pais
				totales.setSubcodigoRegistro(lineQueue.get(2)); // Subcodigo registro
				totales.setInformacion1(lineQueue.get(35)); // Informacion 1
				totales.setInformacion2(lineQueue.get(35)); // Informacion 2
				break;
			}
		}

		return totales;
	}


	private EstadoCuentaTotalesAdicional extractTotalesAdicional(EstadoCuentaFileLayout estadoCuentaFile) throws ConciliacionException {

		EstadoCuentaTotalesAdicional totalesAdicional = null;
		
		for (EstadoCuentaLine line : estadoCuentaFile.getRegistros()) {
			if (line.getTipo() == EstadoCuentaFileLayoutTipoEnum.C43_33) {

				if (line.getTipo().valido(line.getLinea())) {
					throw new ConciliacionException("La linea " + line.getIndex() + " tiene una longitud invalida");
				}

				EstadoCuentaLineQueue lineQueue = new EstadoCuentaLineQueue(line.getLinea());
				totalesAdicional = new EstadoCuentaTotalesAdicional();
				lineQueue.get(2); // Registro
				totalesAdicional.setClavePais(lineQueue.get(4)); // Clave Pais
				totalesAdicional.setSucursal(lineQueue.get(4)); // Sucursal Cuenta
				totalesAdicional.setCuenta(lineQueue.get(10)); // Cuenta
				totalesAdicional.setNoCargos(lineQueue.get(10)); // No. de Cargos
				totalesAdicional.setImporteTotalAbonos(new BigDecimal(lineQueue.get(14))); // Total abonos
				totalesAdicional.setTipoSaldo(new Integer(lineQueue.get(1))); // Saldo 2(+) 1(-)
				totalesAdicional.setSaldoFinal(new BigDecimal(lineQueue.get(14))); // Saldo final
				totalesAdicional.setMonedaAlfabetica(lineQueue.get(3)); // Moneda alfabetica
				break;
			}
		}

		return totalesAdicional;
	}

}
