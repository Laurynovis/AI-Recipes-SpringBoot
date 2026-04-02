package backend_receitas.config; // Ajuste este pacote para o nome correto do seu projeto, se necessário

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration // Diz ao Spring: "Leia este arquivo antes de ligar o servidor"
public class WebClientConfig {

    @Bean // Diz ao Spring: "Pegue o resultado deste método e guarde na sua caixa de ferramentas"
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}