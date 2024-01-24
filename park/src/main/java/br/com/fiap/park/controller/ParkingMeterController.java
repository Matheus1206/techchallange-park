package br.com.fiap.park.controller;

import br.com.fiap.park.config.CarNotParkingException;
import br.com.fiap.park.dto.*;
import br.com.fiap.park.model.ParkingMeter;
import br.com.fiap.park.service.ParkingMeterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("{id}")
    public Optional<ParkingMeter> get(@PathVariable Long id){
        return parkingMeterService.getParkingMeter(id);
    }

//    @PostMapping("park")
//    public ResponseEntity<ParkInfoResponse> park(@RequestBody @Valid ParkRequest parkParquimetroRequest) throws CarNotParkingException {
//        return ResponseEntity.ok(parkingMeterService.parkCar(parkParquimetroRequest));
//    }
//
//    @PostMapping("exit")
//    public ResponseEntity<TotalParkInfoResponse> exit(@RequestBody @Valid ParkRequest parkParquimetroRequest) throws CarNotParkingException {
//        return ResponseEntity.ok(parkingMeterService.exitCar(parkParquimetroRequest));
//    }

    @GetMapping("status/{id}")
    public ResponseEntity<StatusParkMeterResponse> status(@PathVariable Long id){
        return ResponseEntity.ok(parkingMeterService.statusParkingMeter(id));

    }

    @PutMapping("status/{id}")
    public ResponseEntity<StatusParkMeterResponse> setStatus(@PathVariable Long id, @RequestParam boolean status){
        return ResponseEntity.ok(parkingMeterService.setStatusParkingMeter(id, status));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok(parkingMeterService.deleteParkingMeter(id));
    }

}
