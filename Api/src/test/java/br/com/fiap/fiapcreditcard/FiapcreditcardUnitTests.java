package br.com.fiap.fiapcreditcard;

import br.com.fiap.fiapcreditcard.dto.AlunoDTO;
import br.com.fiap.fiapcreditcard.model.Aluno;
import br.com.fiap.fiapcreditcard.repository.AlunoRepository;
import br.com.fiap.fiapcreditcard.service.AlunoService;
import br.com.fiap.fiapcreditcard.service.AlunoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class FiapcreditcardUnitTests {

    @Test
    public void findAllAlunos() {
        AlunoRepository alunoRepository = Mockito.mock(AlunoRepository.class);
        List<Aluno> alunoList = new ArrayList<>();
        Aluno aluno = new Aluno();
        aluno.setNome("Igor");
        aluno.setNumeroCartao("505050");
        alunoList.add(aluno);

        Mockito.when(alunoRepository.buscaPorNome("Igor")).thenReturn(alunoList);

        AlunoService alunoService = new AlunoServiceImpl(alunoRepository);
        List<AlunoDTO> alunoDTOList = alunoService.findAll("Igor");

        Assertions.assertEquals(1, alunoDTOList.size());
        Assertions.assertEquals("Igor", alunoDTOList.get(0).getNome());
        Assertions.assertEquals("505050", alunoDTOList.get(0).getNumeroCartao());
    }
}
