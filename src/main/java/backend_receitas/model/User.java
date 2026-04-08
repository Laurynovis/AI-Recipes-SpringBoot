package backend_receitas.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password; // Aqui vai a senha ENCRIPTADA

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Receita> recipes;
}
