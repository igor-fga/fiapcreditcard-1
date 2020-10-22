package br.com.fiap.fiapcreditcard.repository;

import br.com.fiap.fiapcreditcard.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    @Query("FROM Transacao t WHERE t.aluno.id = :id")
    List<Transacao> findByAluno(Long id);
}
