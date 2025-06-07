package br.com.igrejabatistadocordeiro.oanse.domain.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataUtilString {

    // Define um formato padrão (ex: 01/06/2025)
    private static final DateTimeFormatter FORMATADOR_PADRAO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Verifica se a String é uma data válida no formato dd/MM/yyyy
     */
    public static boolean isDataValida(String dataStr) {
        try {
            LocalDate.parse(dataStr, FORMATADOR_PADRAO);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Converte uma String para LocalDate (formato dd/MM/yyyy)
     */
    public static LocalDate paraLocalDate(String dataStr) {
        return LocalDate.parse(dataStr, FORMATADOR_PADRAO);
    }

    /**
     * Converte LocalDate para String (formato dd/MM/yyyy)
     */
    public static String paraString(LocalDate data) {
        return data.format(FORMATADOR_PADRAO);
    }

    /**
     * Retorna true se a data for no passado
     */
    public static boolean isDataPassada(LocalDate data) {
        return data.isBefore(LocalDate.now());
    }

    /**
     * Retorna true se a data for no futuro
     */
    public static boolean isDataFutura(LocalDate data) {
        return data.isAfter(LocalDate.now());
    }

    /**
     * Calcula idade com base em uma data de nascimento
     */
    public static int calcularIdade(LocalDate dataNascimento) {
        return LocalDate.now().getYear() - dataNascimento.getYear();
    }

    // Você pode adicionar outros formatos ou métodos conforme a necessidade
}
