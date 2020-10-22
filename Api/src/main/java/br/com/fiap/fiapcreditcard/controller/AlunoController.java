package br.com.fiap.fiapcreditcard.controller;

import br.com.fiap.fiapcreditcard.dto.AlunoCreateUpdateDTO;
import br.com.fiap.fiapcreditcard.dto.AlunoDTO;
import br.com.fiap.fiapcreditcard.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("alunos")
public class AlunoController {

	private final AlunoService alunoService;

	public AlunoController(AlunoService alunoService){
		this.alunoService = alunoService;
	}

	@GetMapping
	@Operation(description = "Listagem de alunos por nome")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "OK"),
					@ApiResponse(responseCode = "404", description = "Nenhum aluno encontrado")
			}
	)
	public List<AlunoDTO> findAll(@RequestParam(required = false, value = "nome") String nome) {
		return alunoService.findAll(nome);
	}

	@GetMapping("{id}")
	public AlunoDTO getById(@PathVariable Long id) {
		return alunoService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AlunoDTO create(@RequestBody AlunoCreateUpdateDTO alunoCreateUpdateDTO) {
		return alunoService.create(alunoCreateUpdateDTO);
	}

	@PutMapping("{id}")
	public AlunoDTO update(@RequestBody AlunoCreateUpdateDTO alunoCreateUpdateDTO, @PathVariable Long id) {
		return alunoService.update(alunoCreateUpdateDTO, id);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		alunoService.delete(id);
	}
}
