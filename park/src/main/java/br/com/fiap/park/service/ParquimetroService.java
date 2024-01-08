package br.com.fiap.park.service;

import br.com.fiap.park.dto.request.ParquimetroRequest;
import br.com.fiap.park.model.Parquimetro;
import br.com.fiap.park.repository.ParquimetroRepository;
import org.springframework.stereotype.Service;

@Service
public class ParquimetroService {
    private ParquimetroRepository parquimetroRepository;
    public ParquimetroService(ParquimetroRepository parquimetroRepository){
        this.parquimetroRepository = parquimetroRepository;
    }

    public Parquimetro toModel(ParquimetroRequest parquimetroRequest) {
        Parquimetro parquimetro = new Parquimetro(parquimetroRequest.status(), parquimetroRequest.funcionamento());
        parquimetroRepository.save(parquimetro);
        return parquimetro;
    }
}
