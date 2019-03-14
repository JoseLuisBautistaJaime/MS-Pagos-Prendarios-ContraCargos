package mx.com.nmp.pagos.mimonte.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.Cuenta;

@Repository("cuentaRepository")
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

	public List<Cuenta> findByEntidades_Id(final Long idEntidad);

}
