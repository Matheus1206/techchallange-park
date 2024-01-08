package br.com.fiap.park.controller;

import br.com.fiap.park.dto.request.ParquimetroRequest;
import br.com.fiap.park.model.Parquimetro;
import br.com.fiap.park.service.ParquimetroService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/parquimetro")
public class ParquimetroController {

    private ParquimetroService parquimetroService;

    public ParquimetroController(ParquimetroService parquimetroService){
        this.parquimetroService = parquimetroService;
    }

    @PostMapping
    public Parquimetro create(@RequestBody ParquimetroRequest parquimetroRequest){
        return parquimetroService.toModel(parquimetroRequest);
    }

}
