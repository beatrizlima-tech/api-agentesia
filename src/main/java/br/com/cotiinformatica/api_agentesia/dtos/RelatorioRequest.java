package br.com.cotiinformatica.api_agentesia.dtos;

import java.time.LocalDate;

public record RelatorioRequest(
        String usuario,
        LocalDate dataInicio,
        LocalDate dataFim,
        String dadosAnalise
) {
}
