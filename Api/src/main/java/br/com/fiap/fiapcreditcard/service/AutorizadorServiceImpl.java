package br.com.fiap.fiapcreditcard.service;

import br.com.fiap.fiapcreditcard.dto.TransacaoCreateDTO;
import br.com.fiap.fiapcreditcard.model.Aluno;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class AutorizadorServiceImpl implements AutorizadorService {
    @Override
    public Boolean obterAutorizacaoTransacao(TransacaoCreateDTO transacaoCreateDTO) throws URISyntaxException {
        String url = "http://localhost:3000/autorizar";
        Boolean autorizar = false;

        final String baseUrl = "http://localhost:3000/autorizar";
        URI uri = new URI(baseUrl);

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<String> response = restTemplate.postForEntity(uri, transacaoCreateDTO, String.class);

        if(response.getStatusCode() == HttpStatus.ACCEPTED) {
            autorizar = true;
        }

        return autorizar;
    }
}
