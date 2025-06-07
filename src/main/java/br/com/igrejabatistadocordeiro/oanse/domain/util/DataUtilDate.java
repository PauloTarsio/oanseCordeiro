package br.com.igrejabatistadocordeiro.oanse.domain.util;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DataUtilDate {

    /**
     * Verifica se a data está no passado.
     */
    public static boolean isDataPassada(Date data) {
        return data.before(new Date());
    }

    /**
     * Verifica se a data está no futuro.
     */
    public static boolean isDataFutura(Date data) {
        return data.after(new Date());
    }

    /**
     * Verifica se duas datas são iguais (ignorando horas/minutos/segundos).
     */
    public static boolean isMesmaData(Date d1, Date d2) {
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
    public static boolean isEntre(Date data, Date inicio, Date fim) {
        return !data.before(inicio) && !data.after(fim);
    }

    /**
     * Retorna a diferença em dias entre duas datas.
     */
    public static long diferencaEmDias(Date d1, Date d2) {
        long diffMillis = Math.abs(d1.getTime() - d2.getTime());
        return TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
    }

    /**
     * Calcula idade aproximada com base em uma data de nascimento.
     */
    public static int calcularIdade(Date dataNascimento) {
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

    /**
     * Zera a hora, minuto, segundo e milissegundo da data.
     */
    public static Date zerarHorario(Date data) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
