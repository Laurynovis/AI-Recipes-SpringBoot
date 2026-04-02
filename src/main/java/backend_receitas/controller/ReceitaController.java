package backend_receitas.controller;

import backend_receitas.model.Receita;
import backend_receitas.dto.PedidoReceitaDto;
import backend_receitas.service.ReceitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/receitas")
public class ReceitaController {

    private final ReceitaService receitaService;

    // Injeção de dependência do nosso serviço (o Spring faz a ligação automaticamente)
    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @GetMapping("/status")
    public String checarStatus() {
        return "🚀 API de Receitas rodando e conectada ao Postgres com sucesso!";
    }

    // --- O NOSSO NOVO ENDPOINT DE IA ---
    @PostMapping("/gerar")
    public ResponseEntity<Receita> gerarNovaReceita(@RequestBody PedidoReceitaDto pedido) {
        
        System.out.println("Recebendo pedido de receita: " + pedido.mensagem());
        
        // 1. O Controller manda a mensagem para o Service
        // 2. O Service pede para o Python gerar a receita usando o Groq
        // 3. O Service salva a resposta no banco e nos devolve a entidade pronta
        Receita receitaSalva = receitaService.gerarESalvarReceita(pedido.mensagem());
        
        // Retornamos a receita completa (agora com ID do banco) para o usuário!
        return ResponseEntity.ok(receitaSalva);
    }
}
