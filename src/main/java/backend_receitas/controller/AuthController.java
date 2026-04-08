package backend_receitas.controller;

import backend_receitas.dto.UserRegisterDto;
import backend_receitas.dto.UserResponseDto;
import backend_receitas.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRegisterDto registerDTO) {
        UserResponseDto response = userService.registerUser(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}