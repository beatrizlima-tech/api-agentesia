package br.com.cotiinformatica.api_agentesia.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Document(collection = "historico_relatorio")
public class HistoricoRelatorio {

    @Id
    private String id;

    @Field(name = "data_hora")
    private LocalDateTime dataHora;

    @Field(name = "usuario")
    private String usuario;

    @Field(name = "dados_analise")
    private String dadosAnalise;

    @Field(name = "resultado_analise")
    private String resultadoAnalise;
}
