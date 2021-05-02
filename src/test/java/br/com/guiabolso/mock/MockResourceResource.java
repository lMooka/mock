package br.com.guiabolso.mock;

import br.com.guiabolso.mock.entity.MockTransaction;
import br.com.guiabolso.mock.resource.MockResource;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MockResource.class)
class MockResourceResource {

    /*@Autowired
    private RestTemplate localRestTemplate;

    @Autowired
    private MockResource mockResource;
    */
    @Autowired
    private MockMvc mvc;


/*    @Test
    @Order(1)
    void shouldFailWithFutureDate() {
        // Pega a data atual
        Calendar now = Calendar.getInstance();
        // Adiciona um dia para criar uma data no futuro.
        now.add(Calendar.DAY_OF_MONTH, 1);

        mockResource.getTransactions(1000, now.get(Calendar.YEAR), now.get(Calendar.MONTH) + 1, 0);

        ResponseEntity<List<MockTransaction>> response;
        try {
            // Realiza uma requisição com data inválida.
            response = localRestTemplate.exchange(
                    "/1000/transacoes/" + now.get(Calendar.YEAR) + "/" + now.get(Calendar.MONTH) + 1,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    }
            );
        } catch (HttpStatusCodeException e) {
            assertEquals(e.getStatusCode(), HttpStatus.BAD_REQUEST);
            return;
        }

        assertNotEquals(response.getStatusCode(), HttpStatus.OK);
    }*/

    @Test
    void shouldFailIdOutOfRange() throws Exception {
        // Pega a data atual
        Calendar now = Calendar.getInstance();
        // Adiciona um dia para criar uma data no futuro.
        now.add(Calendar.DAY_OF_MONTH, 1);

        mvc.perform(
                get("/999/transacoes/" + now.get(Calendar.YEAR) + "/" + now.get(Calendar.MONTH))
        ).andExpect(status().isBadRequest());

        mvc.perform(
                get("/100001/transacoes/" + now.get(Calendar.YEAR) + "/" + now.get(Calendar.MONTH))
        ).andExpect(status().isBadRequest());
    }

    @Test
    void shouldFailWithFutureDate() throws Exception {
        // Pega a data atual
        Calendar now = Calendar.getInstance();
        // Adiciona um dia para criar uma data no futuro.
        now.add(Calendar.DAY_OF_MONTH, 1);

        mvc.perform(
                get("/1000/transacoes/" + now.get(Calendar.YEAR) + "/" + now.get(Calendar.MONTH) + 1)
        ).andExpect(status().isBadRequest());
    }

    @Test
    void shouldFailWithMonthOutOfRange() throws Exception {
        // Pega a data atual
        Calendar now = Calendar.getInstance();

        mvc.perform(
                get("/1000/transacoes/" + now.get(Calendar.YEAR) + "/" + 0)
        ).andExpect(status().isBadRequest());

        mvc.perform(
                get("/1000/transacoes/" + now.get(Calendar.YEAR) + "/" + 13)
        ).andExpect(status().isBadRequest());
    }
}
