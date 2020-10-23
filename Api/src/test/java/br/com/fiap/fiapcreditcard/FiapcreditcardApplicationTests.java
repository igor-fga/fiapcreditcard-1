package br.com.fiap.fiapcreditcard;

import br.com.fiap.fiapcreditcard.dto.AlunoCreateUpdateDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.match.JsonPathRequestMatchers;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:mem:database")
class FiapcreditcardApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void createAluno() throws Exception {
		String json = "{\"nome\":\"Igor\",\"numeroCartao\":\"123456\"}";

		mockMvc.perform(
				MockMvcRequestBuilders.post("/alunos")
				.content(json)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
		)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(jsonPath("$.nome", Matchers.is("Igor")));

	}

}
