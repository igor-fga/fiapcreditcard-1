package br.com.fiap.fiapcreditcard.dto;

import br.com.fiap.fiapcreditcard.model.Transacao;

public class TransacaoDTO {

    private Long id;
    private String produto;
    private String beneficiario;
    private Long aluno;
    private Double valor;

    public TransacaoDTO() {}

    public TransacaoDTO(Transacao transacao) {
        this.id = transacao.getId();
        this.produto = transacao.getProduto();;
        this.beneficiario = transacao.getBeneficiario();
        this.valor = transacao.getValor();
        this.aluno = transacao.getAluno().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public Long getAluno() {
        return aluno;
    }

    public void setAluno(Long aluno) {
        this.aluno = aluno;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
