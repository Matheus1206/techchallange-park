package br.com.fiap.park.repository;

import br.com.fiap.park.model.ParkingmeterVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingMeterVehicleRepository extends JpaRepository<ParkingmeterVehicle, Long> {

    @Query(value = "SELECT TOP 1 * FROM PARKINGMETER_VEHICLE WHERE PARKING_METER_ID = ?1 AND VEHICLE_ID = ?2 ORDER BY INITIAL_TIME DESC", nativeQuery = true)
    Optional<ParkingmeterVehicle> findByParkingMeterAndVehicle(Long id, Long vehicleId);
}
