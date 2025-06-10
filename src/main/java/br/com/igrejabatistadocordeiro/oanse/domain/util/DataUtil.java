package br.com.igrejabatistadocordeiro.oanse.domain.util;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class DataUtil {

    /**
     * Verifica se duas datas são iguais (ignorando horas/minutos/segundos).
     */
    public boolean isMesmaData(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
               c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Verifica se uma data está entre duas outras.
     */
    public boolean isEntre(Date data, Date inicio, Date fim) {
        return !data.before(inicio) && !data.after(fim);
    }

    /**
     * Retorna a diferença em dias entre duas datas.
     */
    public long diferencaEmDias(Date d1, Date d2) {
        long diffMillis = Math.abs(d1.getTime() - d2.getTime());
        return TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
    }

    /**
     * Calcula idade aproximada com base em uma data de nascimento.
     */
    public int calcularIdade(Date dataNascimento) {
        Calendar nascimento = Calendar.getInstance();
        nascimento.setTime(dataNascimento);

        Calendar hoje = Calendar.getInstance();

        int idade = hoje.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);

        // Ajusta se ainda não fez aniversário este ano
        if (hoje.get(Calendar.MONTH) < nascimento.get(Calendar.MONTH) ||
            (hoje.get(Calendar.MONTH) == nascimento.get(Calendar.MONTH) &&
             hoje.get(Calendar.DAY_OF_MONTH) < nascimento.get(Calendar.DAY_OF_MONTH))) {
            idade--;
        }

        return idade;
    }
}
