package br.com.fiap.park.dto.request;

import java.time.LocalDateTime;

public record TotalParkInfoResponse(LocalDateTime initialTime, LocalDateTime finalTime, String placa, Float preco){
}
