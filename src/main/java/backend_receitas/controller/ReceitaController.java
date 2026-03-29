package backend_receitas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Diz ao Spring que esta classe vai responder requisições web
@RequestMapping("/api/receitas") // O caminho base da nossa API
public class ReceitaController {

    @GetMapping("/status")
    public String checarStatus() {
        return "🚀 API de Receitas rodando e conectada ao Postgres com sucesso!";
    }
}