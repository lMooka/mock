package br.com.guiabolso.mock;

import br.com.guiabolso.mock.resource.MockResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Calendar;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MockResource.class)
class MockResourceResource {

    @Autowired
    private MockMvc mvc;

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
