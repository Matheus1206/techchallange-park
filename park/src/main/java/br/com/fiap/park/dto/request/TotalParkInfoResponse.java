package br.com.fiap.park.dto.request;

import java.time.LocalDateTime;

public record TotalParkInfoResponse(String placa,LocalDateTime initialTime, LocalDateTime finalTime, Float preco){
}
