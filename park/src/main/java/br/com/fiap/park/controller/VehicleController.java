package br.com.fiap.park.controller;

import br.com.fiap.park.dto.VehicleRequest;
import br.com.fiap.park.model.ParkingMeter;
import br.com.fiap.park.model.Vehicle;
import br.com.fiap.park.service.VehicleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/vehicle")
public class VehicleController {

    VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("create")
    public Vehicle create(@RequestBody VehicleRequest vehicleRequest){
        return vehicleService.toModel(vehicleRequest);
    }
}
