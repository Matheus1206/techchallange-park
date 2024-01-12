package br.com.fiap.park.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PARKINGMETER")
public class ParkingMeter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean status = true;

    private boolean operation = false;

    @Deprecated
    public ParkingMeter(){}

    public ParkingMeter(boolean status, boolean operation){
        this.status = status;
        this.operation = operation;
    }

    public Long getId() {
        return id;
    }

    public boolean isStatus() {
        return status;
    }

    public boolean isFuncionamento() {
        return operation;
    }

    public void setFuncionamento(boolean funcionamento) {
        this.operation = funcionamento;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
