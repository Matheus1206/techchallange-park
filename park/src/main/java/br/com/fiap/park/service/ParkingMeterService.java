package br.com.fiap.park.service;

import br.com.fiap.park.config.annotations.CarNotParkingException;
import br.com.fiap.park.dto.request.ParkInfoResponse;
import br.com.fiap.park.dto.request.ParkRequest;
import br.com.fiap.park.dto.request.ParkingMeterRequest;
import br.com.fiap.park.dto.request.TotalParkInfoResponse;
import br.com.fiap.park.model.ParkingMeter;
import br.com.fiap.park.model.Vehicle;
import br.com.fiap.park.repository.ParkingMeterRepository;
import br.com.fiap.park.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ParkingMeterService {
    private ParkingMeterRepository parkingMeterRepository;
    private VehicleRepository vehicleRepository;

    private LocalDateTime initialTime;

    public ParkingMeterService(ParkingMeterRepository parkingMeterRepository, VehicleRepository vehicleRepository){
        this.parkingMeterRepository = parkingMeterRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public ParkingMeter toModel(ParkingMeterRequest parkingMeterRequest) {
        ParkingMeter parkingMeter = new ParkingMeter(parkingMeterRequest.status());
        parkingMeterRepository.save(parkingMeter);
        return parkingMeter;
    }

    public ParkInfoResponse parkCar(ParkRequest parkParkingMeterRequest) {
        Optional<ParkingMeter> parkingMeter = parkingMeterRepository.findById(parkParkingMeterRequest.idParkingMeter());
        Optional<Vehicle> vehicle = vehicleRepository.findById(parkParkingMeterRequest.licensePlate());
        parkingMeter.ifPresent(p -> p.setStatus(true));
        parkingMeterRepository.save(parkingMeter.get());
        initialTime = LocalDateTime.now();
        return new ParkInfoResponse(initialTime, vehicle.get().getLicensePlate(),parkingMeter.get().getId());
    }

    public TotalParkInfoResponse exitCar(ParkRequest parkParquimetroRequest){
        LocalDateTime finalTime = LocalDateTime.now();
        if(initialTime == null){
            try {
                throw new CarNotParkingException("Car not parking yet, please check again !");
            } catch (CarNotParkingException e) {
                throw new RuntimeException(e);
            }
        }
        Duration duration = Duration.between(initialTime, finalTime);
        return new TotalParkInfoResponse(parkParquimetroRequest.licensePlate(),initialTime, finalTime, calculateParking(duration));
    }

    private Float calculateParking(Duration duration) {
        long minutes = duration.toMinutes();
        long days = duration.toDays();
        if(days > 1){
            return (float) days * 200;
        } else if (minutes <= 10){
            return 0.0f;
        }
        return minutes * 0.3f;
    }

}
