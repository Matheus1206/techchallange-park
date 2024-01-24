package br.com.fiap.park.dto;

import br.com.fiap.park.config.annotations.ExistId;
import br.com.fiap.park.model.ParkingMeter;
import br.com.fiap.park.model.Vehicle;

public record ParkRequest(@ExistId(fieldName = "id", domainClass = ParkingMeter.class) Long idParkingMeter, @ExistId(fieldName = "licensePlate", domainClass = Vehicle.class) String licensePlate) {
}

