package backend_receitas.service;

import backend_receitas.model.Receita;
import backend_receitas.dto.ReceitaGeradaDto;
import backend_receitas.repository.ReceitaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Service // Diz ao Spring que esta classe contém as regras de negócio
public class ReceitaService {

    private final ReceitaRepository repository;
    private final WebClient webClient;

    // Injeção de dependências via construtor (A forma mais segura e recomendada pelo mercado)
    public ReceitaService(ReceitaRepository repository, WebClient.Builder webClientBuilder) {
        this.repository = repository;
        // Apontamos o alvo para a porta onde a nossa IA em Python está escutando
        this.webClient = webClientBuilder.baseUrl("http://127.0.0.1:8000").build();
    }

    public Receita gerarESalvarReceita(String pedidoUsuario) {
        // 1. Faz a chamada HTTP POST para o Python
        ReceitaGeradaDto dto = webClient.post()
                .uri("/api/gerar-receita")
                .bodyValue(Map.of("mensagem", pedidoUsuario)) // Cria o JSON {"mensagem": "pedido"}
                .retrieve()
                .bodyToMono(ReceitaGeradaDto.class) // Transforma o JSON de resposta no nosso DTO
                .block(); // Faz o Java esperar a resposta da IA

        // Verificação de segurança
        if (dto == null) {
            throw new RuntimeException("Falha ao gerar receita na IA");
        }

        // 2. Transforma o DTO na Entidade que vai para o Banco de Dados
        Receita novaReceita = new Receita();
        novaReceita.setTitulo(dto.titulo());
        novaReceita.setDescricao(dto.descricao());
        novaReceita.setTempoPreparoMinutos(dto.tempoPreparoMinutos());
        novaReceita.setIngredientes(dto.ingredientes());
        novaReceita.setPassos(dto.passos());

        // 3. Salva no PostgreSQL e retorna a receita salva (agora com um ID gerado)
        return repository.save(novaReceita);
    }
}