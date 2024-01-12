package br.com.fiap.park.dto.request;

import java.time.LocalDateTime;

public record ParkInfoResponse(LocalDateTime initialParkTime, String placa, Long idPaquimetro) {
}
