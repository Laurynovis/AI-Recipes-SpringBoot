package backend_receitas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ReceitaGeradaDto(
        String titulo,
        
        String descricao,
        
        // Usamos o @JsonProperty para o Java saber ler o campo com "underline" que vem do Python
        @JsonProperty("tempo_preparo_minutos")
        Integer tempoPreparoMinutos,
        
        List<String> ingredientes,
        
        List<String> passos
) {}