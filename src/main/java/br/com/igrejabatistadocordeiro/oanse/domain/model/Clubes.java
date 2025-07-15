package br.com.igrejabatistadocordeiro.oanse.domain.model;

import java.util.Arrays;

public enum Clubes {	
	URSINHO("Ursinho"),
	FAISCA("Faisca"),
	FLAMA("Flama"),
	TOCHA("Tocha"),
	JV("JV");
	
	private final String nome;

	Clubes(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	public static boolean isNomeValido(String nome) {
	    return fromString(nome) != null;
	}
	
	public static Clubes fromString(String nome) {
	    return Arrays.stream(Clubes.values())
             .filter(c -> c.getNome().equalsIgnoreCase(nome) || c.name().equalsIgnoreCase(nome))
             .findFirst()
             .orElse(null);
	}
}
