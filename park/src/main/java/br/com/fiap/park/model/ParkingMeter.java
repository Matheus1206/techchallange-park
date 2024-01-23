package br.com.fiap.park.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PARKINGMETER")
public class ParkingMeter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean status = true;
    @Deprecated
    public ParkingMeter(){}

    public ParkingMeter(boolean status){
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
