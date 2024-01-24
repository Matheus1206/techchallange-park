package br.com.fiap.park.service;

import br.com.fiap.park.config.CarNotParkingException;
import br.com.fiap.park.dto.*;
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

    public ParkingMeterService(ParkingMeterRepository parkingMeterRepository, VehicleRepository vehicleRepository){
        this.parkingMeterRepository = parkingMeterRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public ParkingMeter toModel(ParkingMeterRequest parkingMeterRequest) {
        ParkingMeter parkingMeter = new ParkingMeter(parkingMeterRequest.status());
        parkingMeterRepository.save(parkingMeter);
        return parkingMeter;
    }

//    public ParkInfoResponse parkCar(ParkRequest parkParkingMeterRequest) throws CarNotParkingException {
//        Optional<ParkingMeter> parkingMeter = parkingMeterRepository.findById(parkParkingMeterRequest.idParkingMeter());
//        Optional<Vehicle> vehicle = vehicleRepository.findById(parkParkingMeterRequest.licensePlate());
//        if(parkingMeter.get().getStatus()) throw new CarNotParkingException("ParkingMeter is used now");
//        parkingMeter.ifPresent(p -> p.setStatus(true));
//        parkingMeter.get().setInitialTime(LocalDateTime.now());
//        parkingMeterRepository.save(parkingMeter.get());
//        return new ParkInfoResponse(parkingMeter.get().getInitialTime(), vehicle.get().getLicensePlate(),parkingMeter.get().getId());
//    }

//    public TotalParkInfoResponse exitCar(ParkRequest parkParquimetroRequest) throws CarNotParkingException {
//        Optional<ParkingMeter> parkingMeter = parkingMeterRepository.findById(parkParquimetroRequest.idParkingMeter());
//        LocalDateTime finalTime = LocalDateTime.now();
//        if(parkingMeter.get().getInitialTime() == null) throw new CarNotParkingException("Car not parking yet, please check again !");
//        parkingMeter.get().setFinalTime(finalTime);
//        parkingMeterRepository.save(parkingMeter.get());
//        Duration duration = Duration.between(parkingMeter.get().getInitialTime(), finalTime);
//        return new TotalParkInfoResponse(parkParquimetroRequest.licensePlate(),parkingMeter.get().getInitialTime(), finalTime, calculateParking(duration));
//    }

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

    public StatusParkMeterResponse statusParkingMeter(Long id) {
        Optional<ParkingMeter> parkingMeter = parkingMeterRepository.findById(id);
        return new StatusParkMeterResponse(parkingMeter.get().getId(), parkingMeter.get().getStatus());
    }

    public StatusParkMeterResponse setStatusParkingMeter(Long id, boolean status) {
        Optional<ParkingMeter> parkingMeter = parkingMeterRepository.findById(id);
        parkingMeter.get().setStatus(status);
        parkingMeterRepository.save(parkingMeter.get());
        return new StatusParkMeterResponse(parkingMeter.get().getId(), parkingMeter.get().getStatus());
    }

    public String deleteParkingMeter(Long id) {
        parkingMeterRepository.deleteById(id);
        return "ParkingMeter delete with success";
    }

    public Optional<ParkingMeter> getParkingMeter(Long id) {
        return parkingMeterRepository.findById(id);
    }
}
