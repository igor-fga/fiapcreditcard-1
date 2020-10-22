package br.com.fiap.fiapcreditcard.dto;

public class AlunoCreateUpdateDTO {
	private String nome;
	private String numeroCartao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
}
