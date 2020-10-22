package br.com.fiap.fiapcreditcard.service;

import java.util.List;

import br.com.fiap.fiapcreditcard.dto.AlunoCreateUpdateDTO;
import br.com.fiap.fiapcreditcard.dto.AlunoDTO;

public interface AlunoService {	

	List<AlunoDTO> findAll(String search);

	AlunoDTO findById(Long id);

	AlunoDTO create(AlunoCreateUpdateDTO alunoCreateUpdateDTO);

	AlunoDTO update(AlunoCreateUpdateDTO alunoCreateUpdateDTO, Long id);

	void delete(Long id);
}
