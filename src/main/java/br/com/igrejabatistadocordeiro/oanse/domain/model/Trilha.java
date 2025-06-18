package br.com.igrejabatistadocordeiro.oanse.domain.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Trilha {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manual_id")
    private Manual manual;

    @OneToMany(mappedBy = "trilha", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sessao> sessoes = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Manual getManual() {
		return manual;
	}

	public void setManual(Manual manual) {
		this.manual = manual;
	}

	public List<Sessao> getSessoes() {
		return sessoes;
	}

	public void setSessoes(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}
    
}
