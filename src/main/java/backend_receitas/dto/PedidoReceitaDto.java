package backend_receitas.dto;
// Um record simples que espera receber um JSON no formato: {"mensagem": "quero um bolo..."}
public record PedidoReceitaDto(String mensagem) {
}