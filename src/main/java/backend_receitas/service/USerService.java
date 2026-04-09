package backend_receitas.service;

import backend_receitas.dto.UserRegisterDto;
import backend_receitas.dto.UserResponseDto;
import backend_receitas.model.User;
import backend_receitas.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class USerService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Injetado via Spring Security

    public USerService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto registerUser(UserRegisterDto data) {
        // 1. Verifica se o e-mail já está em uso
        if (userRepository.findByEmail(data.email()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado!"); // Depois podemos trocar por uma Exceção customizada
        }

        // 2. Cria o usuário e criptografa a senha com BCrypt
        User newUser = new User();
        newUser.setEmail(data.email());
        newUser.setPassword(passwordEncoder.encode(data.password()));

        // 3. Salva no banco
        User savedUser = userRepository.save(newUser);

        // 4. Retorna apenas os dados seguros
        return new UserResponseDto(savedUser.getId(), savedUser.getEmail());
    }
}