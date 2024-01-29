package br.com.fiap.park.service;

import br.com.fiap.park.config.CarNotParkingException;
import br.com.fiap.park.dto.*;
import br.com.fiap.park.model.ParkingMeter;
import br.com.fiap.park.model.ParkingmeterVehicle;
import br.com.fiap.park.model.Vehicle;
import br.com.fiap.park.repository.ParkingMeterRepository;
import br.com.fiap.park.repository.ParkingMeterVehicleRepository;
import br.com.fiap.park.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ParkingMeterService {
    private ParkingMeterRepository parkingMeterRepository;

    private VehicleRepository vehicleRepository;

    private ParkingMeterVehicleRepository parkingMeterVehicleRepository;

    public ParkingMeterService(ParkingMeterRepository parkingMeterRepository, VehicleRepository vehicleRepository, ParkingMeterVehicleRepository parkingMeterVehicleRepository){
        this.parkingMeterRepository = parkingMeterRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingMeterVehicleRepository = parkingMeterVehicleRepository;
    }

    public ParkingMeter toModel() {
        ParkingMeter parkingMeter = new ParkingMeter();
        parkingMeterRepository.save(parkingMeter);
        return parkingMeter;
    }

    public ParkInfoResponse parkCar(ParkRequest parkParkingMeterRequest) throws CarNotParkingException {
        Optional<ParkingMeter> parkingMeter = parkingMeterRepository.findById(parkParkingMeterRequest.id());
        Optional<Vehicle> vehicle = vehicleRepository.findByLicensePlate(parkParkingMeterRequest.licensePlate());

        if(parkingMeter.get().getStatus()) throw new CarNotParkingException("ParkingMeter is used now");

        parkingMeter.ifPresent(p -> p.setStatus(true));

        ParkingmeterVehicle parkingmeterVehicle = new ParkingmeterVehicle(parkingMeter.get(), vehicle.get());
        parkingMeterVehicleRepository.save(parkingmeterVehicle);

        parkingMeterRepository.save(parkingMeter.get());

        return new ParkInfoResponse(parkingmeterVehicle.getInitialTime(), vehicle.get().getLicensePlate(),parkingMeter.get().getId());
    }

    public TotalParkInfoResponse exitCar(ParkRequest parkParquimetroRequest) throws CarNotParkingException {
        Optional<ParkingMeter> parkingMeter = parkingMeterRepository.findById(parkParquimetroRequest.id());
        Optional<Vehicle> vehicle = vehicleRepository.findByLicensePlate(parkParquimetroRequest.licensePlate());
        Optional<ParkingmeterVehicle> parkingmeterVehicle = parkingMeterVehicleRepository.findByParkingMeterAndVehicle(parkingMeter.get().getId(), vehicle.get().getId());

        LocalDateTime finalTime = LocalDateTime.now();

        if(parkingmeterVehicle.get().getInitialTime() == null || !parkingMeter.get().getStatus()) throw new CarNotParkingException("Car not parked yet, please check again !");

        parkingmeterVehicle.get().setFinalTime(finalTime);
        parkingMeterVehicleRepository.save(parkingmeterVehicle.get());

        Duration duration = Duration.between(parkingmeterVehicle.get().getInitialTime(), finalTime);

        parkingMeter.get().setStatus(false);
        parkingMeterRepository.save(parkingMeter.get());

        return new TotalParkInfoResponse(parkParquimetroRequest.licensePlate(),parkingmeterVehicle.get().getInitialTime(), finalTime, calculateParking(duration));
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
