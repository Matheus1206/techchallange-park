package br.com.fiap.park.repository;

import br.com.fiap.park.model.ParkingMeter;
import br.com.fiap.park.model.ParkingmeterVehicle;
import br.com.fiap.park.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingMeterVehicleRepository extends JpaRepository<ParkingmeterVehicle, Long> {

    @Query(value = "SELECT * FROM parkingmeter_vehicle WHERE PARKING_METER_ID = ?1 AND VEHICLE_ID = ?2 ORDER BY INITIAL_TIME DESC LIMIT 1", nativeQuery = true)
    Optional<ParkingmeterVehicle> findByParkingMeterAndVehicle(Long parkingMeterId, Long vehicleId);
}
