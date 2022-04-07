/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;


import mx.com.nmp.pagos.mimonte.dto.conciliacion.DiaInhabilDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FiltroDiaInhabilDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CatalogoDiaInhabil;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 30-Nov-2021 11:03:32 AM
 */
@Service("diaInhabilService")
public interface DiaInhabilService {

	/**
	 * Método que regresa el objeto de tipo  CatalogoDiaInhabil con la
	 * información del día inhábil a partir de de su fecha de calendarización
	 *
	 * @param fecha
	 * @return
	 */
	public CatalogoDiaInhabil buscarByFecha(Date fecha);

	/**
	 * Método que regresa una lista de tipo DiaInhabilDTO con la
	 * información de los días inhábiles a partir de un objeto de tipo
	 * FiltroDiaInhabilDTO.
	 *
	 * @param filtroDiaInhabilDTO
	 * @return
	 */
	public List<DiaInhabilDTO> consultarByPropiedades(FiltroDiaInhabilDTO filtroDiaInhabilDTO);

	/**
	 * Metodo que guarda un día inhábil.
	 *
	 * @param diaInhabilDTO
	 * @return
	 */
	public DiaInhabilDTO saveDiaInhabil(DiaInhabilDTO diaInhabilDTO);
}