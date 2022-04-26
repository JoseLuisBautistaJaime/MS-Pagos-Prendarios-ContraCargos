package mx.com.nmp.pagos.mimonte.services;

import mx.com.nmp.pagos.mimonte.dto.BayonetReglasDTO;

/**
 * Nombre: BayonetReglasService Descripcion: Interfaz que contiene las operaciones
 * encargadas de realizar consultas relacionadas con las respuestas regresadas por bayonet
 *
 * @author Felix Manuel Galicia  fmgalicia@quarksoft.net
 * @creationDate: 21/04/2022 20:11 hrs.
 * @version 0.1
 */
public interface BayonetReglasService {

	public BayonetReglasDTO getReglasBayonetByStatus(String status);
}
