package br.com.igrejabatistadocordeiro.oanse.domain.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Manual {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Enumerated(EnumType.STRING)
    private Clubes clube;
    
	private String descricao;

    @OneToMany(mappedBy = "manual")
    private List<ManualDoOansista> manuaisDoOansista = new ArrayList<>();

    @OneToMany(mappedBy = "manual", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trilha> trilhas = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Trilha> getTrilhas() {
		return trilhas;
	}

	public void setTrilhas(List<Trilha> trilhas) {
		this.trilhas = trilhas;
	}

	public Clubes getClube() {
		return clube;
	}

	public void setClube(Clubes clube) {
		this.clube = clube;
	}

	public List<ManualDoOansista> getManuaisDoOansista() {
		return manuaisDoOansista;
	}

	public void setManuaisDoOansista(List<ManualDoOansista> manuaisDoOansista) {
		this.manuaisDoOansista = manuaisDoOansista;
	}
    
}
