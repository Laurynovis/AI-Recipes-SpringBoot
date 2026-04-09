package backend_receitas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity // Diz ao Spring que esta classe vai virar uma tabela no banco
@Table(name = "receitas")
@Data // Anotação do Lombok que gera Getters, Setters e toString automaticamente
@NoArgsConstructor
@AllArgsConstructor
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "tempo_preparo_minutos")
    private Integer tempoPreparoMinutos;

    // AQUI ESTÁ O RELACIONAMENTO (Agora dentro da classe!)
    @ManyToOne
    @JoinColumn(name = "user_id") // Isso vai criar a coluna "user_id" na tabela de receitas
    private User user;

    // @ElementCollection cria uma tabela auxiliar para guardar a lista de Strings
    @ElementCollection
    @CollectionTable(name = "receita_ingredientes", joinColumns = @JoinColumn(name = "receita_id"))
    @Column(name = "ingrediente")
    private List<String> ingredientes;

    @ElementCollection
    @CollectionTable(name = "receita_passos", joinColumns = @JoinColumn(name = "receita_id"))
    @Column(name = "passo", columnDefinition = "TEXT")
    private List<String> passos;
}