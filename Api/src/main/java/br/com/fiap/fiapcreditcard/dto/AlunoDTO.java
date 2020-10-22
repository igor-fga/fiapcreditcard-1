package br.com.fiap.fiapcreditcard.dto;

import br.com.fiap.fiapcreditcard.model.Aluno;

import java.util.Date;

public class AlunoDTO {

	private Long id;
	private String nome;
	private String numeroCartao;
	private Double saldo;

	public AlunoDTO(){}

	public AlunoDTO(Aluno aluno) {
		this.id = aluno.getId();
		this.nome = aluno.getNome();
		this.numeroCartao = aluno.getNumeroCartao();
		this.saldo = aluno.getSaldo();
	}

	public String getNome() {
		return nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
}
