package br.com.fiap.park.service;

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
        ParkingMeter parkingMeter = new ParkingMeter(parkingMeterRequest.status(), parkingMeterRequest.funcionamento());
        parkingMeterRepository.save(parkingMeter);
        return parkingMeter;
    }

    public ParkInfoResponse parkCar(ParkRequest parkParkingMeterRequest) {
        Optional<ParkingMeter> parkingMeter = parkingMeterRepository.findById(parkParkingMeterRequest.idParkingMeter());
        //TODO: Validação se o parkingMeter já tiver sendo usado
        Optional<Vehicle> vehicle = vehicleRepository.findById(parkParkingMeterRequest.licensePlate());
        //TODO: Pensar de deixar somente um atributo no parkquimetro
        parkingMeter.ifPresent(p -> {
            p.setStatus(true);
            p.setFuncionamento(true);
        });
        parkingMeterRepository.save(parkingMeter.get());
        initialTime = LocalDateTime.now();
        return new ParkInfoResponse(initialTime, vehicle.get().getLicensePlate(),parkingMeter.get().getId());
    }

    public TotalParkInfoResponse exitCar(ParkRequest parkParquimetroRequest){
        LocalDateTime finalTime = LocalDateTime.now();
        Duration duration = Duration.between(initialTime, finalTime);
        return new TotalParkInfoResponse(initialTime, finalTime, parkParquimetroRequest.licensePlate(), calculateParking(duration));
    }

    private Float calculateParking(Duration duration) {
        Long seconds = duration.toSeconds();
        Long minutes = duration.toMinutes();
        Long hours = duration.toHours();
        Long days = duration.toDays();

        return 2f;
    }

}
