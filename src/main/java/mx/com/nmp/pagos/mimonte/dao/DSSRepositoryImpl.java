/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao;

import java.math.BigInteger;
import java.sql.SQLDataException;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Nombre: DSSRepositoryImpl Descripcion: Clase que implementa las operaciones
 * encargadas de consultar informacion referente a DSS de la aplicacion en
 * consultas nativas dinamicas
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate: 18/12/2018 14:55 hrs.
 * @version 0.1
 */
@Transactional
@Component("dssRepositoryImpl")
public class DSSRepositoryImpl implements DSSRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Metodo que ejecuta una consulta de regla de negocios y recibe objeto de tipo
	 * BigInteger como respuesta
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public BigInteger execQuery(String query) throws DataIntegrityViolationException, SQLDataException, SQLException {
		BigInteger res = null;
		Query q = em.createNativeQuery(query);
		res = (BigInteger) q.getSingleResult();
		return res;
	}

}
