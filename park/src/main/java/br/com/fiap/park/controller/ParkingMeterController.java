package br.com.fiap.park.controller;

import br.com.fiap.park.dto.request.ParkInfoResponse;
import br.com.fiap.park.dto.request.ParkRequest;
import br.com.fiap.park.dto.request.ParkingMeterRequest;
import br.com.fiap.park.dto.request.TotalParkInfoResponse;
import br.com.fiap.park.model.ParkingMeter;
import br.com.fiap.park.service.ParkingMeterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/parkingmeter")
public class ParkingMeterController {

    private ParkingMeterService parkingMeterService;

    public ParkingMeterController(ParkingMeterService parkingMeterService){
        this.parkingMeterService = parkingMeterService;
    }

    @PostMapping("create")
    public ParkingMeter create(@RequestBody ParkingMeterRequest parkingMeterRequest){
        return parkingMeterService.toModel(parkingMeterRequest);
    }

    @PostMapping("park")
    public ResponseEntity<ParkInfoResponse> park(@RequestBody @Valid ParkRequest parkParquimetroRequest){
        return ResponseEntity.ok(parkingMeterService.parkCar(parkParquimetroRequest));
    }

    @PostMapping("exit")
    public ResponseEntity<TotalParkInfoResponse> exit(@RequestBody @Valid ParkRequest parkParquimetroRequest){
        return ResponseEntity.ok(parkingMeterService.exitCar(parkParquimetroRequest));
    }


}
