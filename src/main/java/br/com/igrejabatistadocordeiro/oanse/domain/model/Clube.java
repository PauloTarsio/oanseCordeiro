package br.com.igrejabatistadocordeiro.oanse.domain.model;

public enum Clube {
	CLUBE("Clube"),
	URSINHO("Ursinho"),
	FAISCA("Faisca"),
	FLAMA("Flama"),
	TOCHA("Tocha"),
	JV("JV");
	
	private final String nome;

	Clube(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return nome;
	}
	
}
