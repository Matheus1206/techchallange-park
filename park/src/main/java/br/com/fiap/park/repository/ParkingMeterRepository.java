package br.com.fiap.park.repository;

import br.com.fiap.park.model.ParkingMeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingMeterRepository extends JpaRepository<ParkingMeter, Long> {
}
