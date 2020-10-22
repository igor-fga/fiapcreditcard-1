package br.com.fiap.fiapcreditcard.repository;

import br.com.fiap.fiapcreditcard.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    //List<Aluno> findAllByNomeContainingAndAtivoIsTrue(String nome);

    @Query("from Aluno a where a.nome like %:nome% and a.ativo = true")
    List<Aluno> buscaPorNome(String nome);

    Optional<Aluno> findByIdAndAtivoIsTrue(Long id);
}
