package br.com.cotiinformatica.api_agentesia.controllers;

import br.com.cotiinformatica.api_agentesia.dtos.RelatorioRequest;
import br.com.cotiinformatica.api_agentesia.services.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    //Injeção de dependência
    @Autowired
    private RelatorioService relatorioService;

    @PostMapping
    public ResponseEntity<?> post(@RequestBody RelatorioRequest request) {

        //Gerando o relatório através da camada de serviço
        var response = relatorioService.gerarRelatorio(request);

        //Retornar a resposta
        return ResponseEntity.status(201).body(response);
    }
}