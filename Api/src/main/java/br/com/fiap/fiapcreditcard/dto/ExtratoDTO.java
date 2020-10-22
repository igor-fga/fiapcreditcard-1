package br.com.fiap.fiapcreditcard.dto;

import java.util.List;

public class ExtratoDTO {
    private Long idAluno;
    private String email;
    private List<TransacaoDTO> transacoes;

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TransacaoDTO> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<TransacaoDTO> transacoes) {
        this.transacoes = transacoes;
    }
}
