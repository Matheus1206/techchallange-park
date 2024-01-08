package br.com.fiap.park.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Parquimetro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean status = true;

    private boolean funcionamento = false;

    @Deprecated
    public Parquimetro(){}

    public Parquimetro(boolean status, boolean funcionamento){
        this.status = status;
        this.funcionamento = funcionamento;
    }
}
