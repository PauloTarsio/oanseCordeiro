package br.com.igrejabatistadocordeiro.oanse.domain.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class StringUtils {
	
	private static final Pattern CPF_PATTERN = Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    private static final Pattern CNPJ_PATTERN = Pattern.compile("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}");
    private static final Pattern RG_PATTERN = Pattern.compile("\\d{2}\\.\\d{3}\\.\\d{3}-[A-Za-z0-9]");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}");
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");
    
    private static final DateTimeFormatter FORMATADOR_PADRAO = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public static boolean isBlank(String str) {
		return str == null || str.trim().isEmpty();
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	public static String trim(String str) {
		return isBlank(str) ? null : str.trim();
	}

	public static String toLowerCase(String str) {
		return isBlank(str) ? null : str.toLowerCase();
	}

	public static String toUpperCase(String str) {
		return isBlank(str) ? null : str.toUpperCase();
	}
	
	/**
     * Valida CPF no formato XXX.XXX.XXX-XX.
     */
    public static boolean isCPFValido(String cpf) {
        return CPF_PATTERN.matcher(cpf).matches();
    }
    
    public static boolean isCNPJValido(String cnpj) {    	
        return CNPJ_PATTERN.matcher(formatCNPJ(cnpj)).matches();
    }

    /**
     * Valida RG no formato XX.XXX.XXX-X.
     */
    public static boolean isRGValido(String rg) {
        return RG_PATTERN.matcher(rg).matches();
    }

    /**
     * Valida se o e-mail está em um formato válido.
     */
    public static boolean isEmailValido(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Valida se o telefone está no formato (XX) XXXXX-XXXX ou (XX) XXXX-XXXX.
     */
    public static boolean isTelefoneValido(String phone) {    	
        return PHONE_PATTERN.matcher(formatPhone(phone)).matches();
    }

    /**
     * Verifica se a string representa um valor numérico.
     */
    public static boolean isNumeric(String str) {
        if (isBlank(str)) return false;
        return NUMERIC_PATTERN.matcher(str).matches();
    }

    /**
     * Verifica se a string contém apenas letras.
     */
    public static boolean isAlphabetic(String str) {
        return str != null && str.matches("[a-zA-Z]+");
    }

    /**
     * Verifica se a string contém apenas letras e espaços.
     */
    public static boolean isAlphabeticWithSpaces(String str) {
        return str != null && str.matches("[a-zA-Z\\s]+");
    }

    /**
     * Verifica se a string contém apenas letras e números.
     */
    public static boolean isAlphanumeric(String str) {
        return str != null && str.matches("[a-zA-Z0-9]+");
    }
    
    /**
     * Formata um CNPJ bruto (somente dígitos) para o formato XX.XXX.XXX/0001-XX.
     */
    public static String formatCNPJ(String cnpj) {
        if (cnpj != null && cnpj.matches("\\d{14}")) {
            return cnpj.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
        }
        return cnpj;
    }

    /**
     * Verifica se uma string é um CEP no formato XXXXX-XXX.
     */
    public static boolean isCEPValid(String cep) {
        return cep != null && formatCEP(cep).matches("\\d{5}-\\d{3}");
    }

    /**
     * Valida se uma URL está em um formato básico válido.
     */
    public static boolean isURLValid(String url) {
        return url != null && url.matches("^(http|https)://[^\\s/$.?#].[^\\s]*$");
    }
    
    /**
     * Formata um telefone bruto para formatado 
     * @param phone XXXXXXXXXXX
     * @return (XX) XXXXX-XXXX
     */
    public static String formatPhone(String phone) {
        if (phone != null && phone.matches("\\d{10,11}")) {
            if (phone.length() == 10) {
                return phone.replaceFirst("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
            } else {
                return phone.replaceFirst("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
            }
        }
        return phone;
    }

    /**
     * Formata um CEP bruto (somente dígitos) para o formato XXXXX-XXX.
     */
    public static String formatCEP(String cep) {
        if (cep != null && cep.matches("\\d{8}")) {
            return cep.replaceFirst("(\\d{5})(\\d{3})", "$1-$2");
        }
        return cep;
    }

    /**
     * Remove todos os caracteres que não sejam dígitos de uma string.
     * 
     *	onlyDigits("abc123xyz"));	// Saída: "123"
	 *	onlyDigits("1a2b3c!@#"));	// Saída: "123"
	 *	onlyDigits("2024-03-15"));	// Saída: "20240315"
	 *	onlyDigits(null));			// Saída: null
     */
    public static String apenasNumeros(String str) {
        return str != null ? str.replaceAll("\\D", "") : null;
    }
    
    public static boolean temDiferenca(String s1, String s2) {
		if (s1 == null && s2 == null)
			return false;
		if (s1 == null || s2 == null)
			return true;
		return !s1.equals(s2);
	}

	public static boolean temDiferenca(Boolean b1, Boolean b2) {
    	return b1 != b2;
    }
	
	public static boolean temDiferenca(boolean b1, boolean b2) {
		return b1 != b2;
	}

	public static boolean temDiferenca(Double d1, Double d2) {
    	return d1.compareTo(d2)!=0;
    }
	
	public static boolean temDiferenca(double d1, double d2) {
		return d1 != d2;
	}
	
	public static boolean temDiferenca(Integer i1, Integer i2) {
		return i1.compareTo(i2)!=0;
	}
	
	public static boolean temDiferenca(int i1, int i2) {
		return i1 != i2;
	}
	
	public static boolean temDiferenca(BigDecimal d1, BigDecimal d2) {
    	return d1.compareTo(d2)!=0;
    }
	
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
}
