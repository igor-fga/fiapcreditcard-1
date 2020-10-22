package br.com.fiap.fiapcreditcard.controller;

import br.com.fiap.fiapcreditcard.dto.AlunoDTO;
import br.com.fiap.fiapcreditcard.dto.TransacaoCreateDTO;
import br.com.fiap.fiapcreditcard.dto.TransacaoDTO;
import br.com.fiap.fiapcreditcard.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService){
        this.transacaoService = transacaoService;
    }

    @GetMapping
    @Operation(description = "Listagem de todas as transações")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Nenhuma transação encontrada")
            }
    )
    public List<TransacaoDTO> findAll() {
        return transacaoService.findAll();
    }


    @GetMapping("{id}")
    @Operation(description = "Listagem de todas as transações por aluno")
    public List<TransacaoDTO> findByAluno(@PathVariable Long id) {
        return transacaoService.findByAluno(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransacaoDTO create(@RequestBody TransacaoCreateDTO transacaoCreateDTO) {
        try {
            return transacaoService.create(transacaoCreateDTO);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
