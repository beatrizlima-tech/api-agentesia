package br.com.cotiinformatica.api_agentesia;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"openai.apikey=chave-ficticia-para-testes",
		"openai.model=gpt-4o-mini"
})
class ApiAgentesiaApplicationTests {

	@Test
	void contextLoads() {
	}
}