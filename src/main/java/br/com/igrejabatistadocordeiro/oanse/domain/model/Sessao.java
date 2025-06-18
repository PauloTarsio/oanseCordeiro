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
public class Sessao {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numero;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trilha_id")
    private Trilha trilha;

    @OneToMany(mappedBy = "sessao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OansistaSessao> oansistaSessao = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Trilha getTrilha() {
		return trilha;
	}

	public void setTrilha(Trilha trilha) {
		this.trilha = trilha;
	}

	public List<OansistaSessao> getOansistaAtividades() {
		return oansistaSessao;
	}

	public void setOansistaAtividades(List<OansistaSessao> oansistaAtividades) {
		this.oansistaSessao = oansistaAtividades;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
    
}
