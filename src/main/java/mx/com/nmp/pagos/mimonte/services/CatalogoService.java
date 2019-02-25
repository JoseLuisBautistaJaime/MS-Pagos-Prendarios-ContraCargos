package mx.com.nmp.pagos.mimonte.services;


import mx.com.nmp.pagos.mimonte.dto.CatalogoDTO;
import mx.com.nmp.pagos.mimonte.model.extrafilter.Filtro;

import java.util.List;

/**
 * Nombre: CatalogoService
 * Descripcion: Interface que define la operacion encargada de obtener los registros del catalogo
 *
 * @author Javier Hernandez jhernandez@quarksoft.net
 * Fecha: 07/02/2018 08:00 PM
 * @version 0.1
 */
public interface CatalogoService {
    /**
     * Metodo que obtiene la lista de extrafilter del sistema y devuelve la lista con los nombres de los mismos
     * @return Lista con los nombres de los extrafilter registrados en el sistema para poder consultarlos
     */
    List<String> getCatalogosSistema();

    /**
     * Metodo que realiza la busqueda de los registros del catalogo especificado
     * @param nombreCatalogo Nombre del catalogo a consultar
     * @return Lista con las entradas de registros del catalogo
     */
    CatalogoDTO getRegistrosCatalogo(String nombreCatalogo);

    /**
     * Metodo que realiza la busqueda de los registros del catalogo que cumplan con el filtro especificado
     *
     * @param nombreCatalogo Nombre del catalogo a consultar
     * @param filtro Filtro para la busqueda
     * @param parametros Valores requeridos por el filtro
     *
     * @return Lista con las entradas de registros del catalogo
     */
    CatalogoDTO getRegistrosCatalogo(String nombreCatalogo, Filtro filtro, List<?> parametros);

}
