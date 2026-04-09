package backend_receitas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // <-- Adicionamos esta anotação para ativar as regras web
public class SecurityConfig {

    // O seu código original continua aqui (Excelente para o futuro!)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // A regra que vai falar para o Spring parar de bloquear o seu Angular
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desliga a proteção CSRF, obrigatório quando o Angular (4200) fala com o Java (8080)
                .csrf(csrf -> csrf.disable())

                // Libera todas as rotas da nossa API sem precisar de senha (por enquanto!)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}