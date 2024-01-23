package br.com.fiap.park.config.annotations;

public class CarNotParkingException extends Exception {

    public CarNotParkingException(){

    }

    public CarNotParkingException(String message){
        super(message);
    }
}
