package br.com.fiap.fiapcreditcard.controller;

import br.com.fiap.fiapcreditcard.dto.ExtratoDTO;
import br.com.fiap.fiapcreditcard.dto.TransacaoDTO;
import br.com.fiap.fiapcreditcard.service.ExtratoService;
import br.com.fiap.fiapcreditcard.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("extrato")
public class ExtratoController {

    private final ExtratoService extratoService;
    private final TransacaoService transacaoService;

    public ExtratoController(ExtratoService extratoService, TransacaoService transacaoService){

        this.extratoService = extratoService;
        this.transacaoService = transacaoService;
    }

    @GetMapping
    @Operation(description = "Listagem de alunos por nome")
    @ResponseStatus(HttpStatus.OK)
    public void sendExtract(@RequestParam Long id, String email) {
        ExtratoDTO extratoDTO = new ExtratoDTO();
        List<TransacaoDTO> lstTransacao = transacaoService.findByAluno(id);
        extratoDTO.setIdAluno(id);
        extratoDTO.setEmail(email);
        extratoDTO.setTransacoes(lstTransacao);

        extratoService.SendExtrato(extratoDTO);
    }

}
