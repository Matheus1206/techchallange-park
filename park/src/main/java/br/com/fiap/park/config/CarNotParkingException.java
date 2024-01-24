package br.com.fiap.park.config;

public class CarNotParkingException extends Exception {

    public CarNotParkingException(){

    }

    public CarNotParkingException(String message){
        super(message);
    }
}
