package br.com.fiap.park.service;

import br.com.fiap.park.dto.VehicleRequest;
import br.com.fiap.park.model.Vehicle;
import br.com.fiap.park.repository.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle toModel(VehicleRequest vehicleRequest) {
        Vehicle vehicle = new Vehicle(vehicleRequest.licensePlate());
        vehicleRepository.save(vehicle);
        return vehicle;
    }
}
