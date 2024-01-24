package br.com.fiap.park.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Embeddable
@Table(name = "PARKINGMETER_VEHICLE")
public class ParkingmeterVehicle {

    @EmbeddedId
    @OneToOne
    private ParkingMeter parkingMeter;

    @EmbeddedId
    @OneToOne
    private Vehicle vehicle;

    private LocalDateTime initialTime;

    private LocalDateTime finalTime;

    public ParkingMeter getParkingMeter() {
        return parkingMeter;
    }

    public void setParkingMeter(ParkingMeter parkingMeter) {
        this.parkingMeter = parkingMeter;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(LocalDateTime initialTime) {
        this.initialTime = initialTime;
    }

    public LocalDateTime getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(LocalDateTime finalTime) {
        this.finalTime = finalTime;
    }
}
