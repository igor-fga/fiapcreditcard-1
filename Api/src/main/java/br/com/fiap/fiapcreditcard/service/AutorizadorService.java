package br.com.fiap.fiapcreditcard.service;

import br.com.fiap.fiapcreditcard.dto.TransacaoCreateDTO;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;

public interface AutorizadorService {

    Boolean obterAutorizacaoTransacao(TransacaoCreateDTO transacaoCreateDTO) throws URISyntaxException;
}
