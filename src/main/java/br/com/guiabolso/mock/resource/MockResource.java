package br.com.guiabolso.mock.resource;

import br.com.guiabolso.mock.entity.MockTransaction;
import br.com.guiabolso.mock.exception.ResponseStatusCodeException;
import br.com.guiabolso.mock.util.WordGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class MockResource {

    @GetMapping("/{id}/transacoes/{year}/{month}")
    public ResponseEntity<?> getTransactions(
            @PathVariable("id") int id,
            @PathVariable("year") int year,
            @PathVariable("month") int month,
            @RequestParam(value = "shift", defaultValue = "0") int shift
    ) {
        Calendar now = Calendar.getInstance();
        // dia do mês atual
        int currentDay = now.get(Calendar.DAY_OF_MONTH);
        // mês atual, 0 = janeiro então é necessário adicionar + 1
        int currentMonth = now.get(Calendar.MONTH) + 1;
        // ano atual
        int currentYear = now.get(Calendar.YEAR);

        // validação do domínio do dado Id
        if (id < 1000 || id > 100000)
            throw new ResponseStatusCodeException(HttpStatus.BAD_REQUEST, "Intervalo do campo Id fora do domínio. (1000~100000)", "id_out_of_range");

        if (month < 1 || month > 12) {
            throw new ResponseStatusCodeException(HttpStatus.BAD_REQUEST, "Intervalo de mês inválido. (1~12)", "month_out_of_range");
        }

        // verificar data maior que atual
        if (year > currentYear || month > currentMonth)
            throw new ResponseStatusCodeException(HttpStatus.BAD_REQUEST, "Data informada maior que a data atual.", "future_date_not_allowed");

        // gera a seed do random
        int seed = id + id * month + id * year;
        // garante que seja positiva
        seed = seed * seed;
        // inicia a instância random com a seed gerada
        Random random = new Random(seed + shift);
        // calcula quantidade de transações
        int transactionAmount = Integer.parseInt(String.valueOf(id).substring(0, 1)) * month;

        List<MockTransaction> transactions = new ArrayList<>();

        Calendar calendarAux = Calendar.getInstance();
        for (int i = 0; i < transactionAmount; i++) {
            MockTransaction mockTransaction = new MockTransaction();
            // gera a descrição de forma aleatória
            mockTransaction.setDescricao(WordGenerator.generatePhrase(random));
            // define o valor dentro do intervalo de -9.999.999 e 9.999.999 (inclusive).
            int value = Math.max(Math.min((random.nextBoolean() ? -1 : 1) * id * (i + 1) * month, 9999999), -9999999);
            mockTransaction.setValor(value);

            // Gera data aleatória para o mês
            int monthLastDay = 0;
            if (year == currentYear && month == currentMonth) {
                // necessário tratamento até o dia atual
                monthLastDay = currentDay;
            } else {
                // define como ultimo dia do mês
                monthLastDay = now.getActualMaximum(Calendar.DAY_OF_MONTH);
            }

            int rDay = (random.nextInt(monthLastDay + 1)); // necessário +1 pois .nextInt() é exclusive
            // O valor 0 no dia faz a data voltar para mês anterior.
            rDay = rDay == 0 ? 1 : rDay;

            // Define aleatoriamente o calendário
            calendarAux.set(
                    year,
                    month,
                    rDay,
                    random.nextInt(24),
                    random.nextInt(60),
                    random.nextInt(60)
            );
            calendarAux.set(Calendar.MILLISECOND, random.nextInt(1000));

            Date transactionDate = calendarAux.getTime();
            mockTransaction.setData(String.valueOf(transactionDate.getTime()));
            transactions.add(mockTransaction);
        }
        return ResponseEntity.ok(transactions);
    }
}
