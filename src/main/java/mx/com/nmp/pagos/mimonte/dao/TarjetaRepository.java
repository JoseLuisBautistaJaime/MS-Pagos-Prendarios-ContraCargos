package mx.com.nmp.pagos.mimonte.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.dto.PagoDTO;
import mx.com.nmp.pagos.mimonte.model.Tarjetas;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjetas, String> {

	public abstract PagoDTO savePago(PagoDTO pagoDTO);
	
}
