package br.com.igrejabatistadocordeiro.oanse.domain.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "livro")
public class Livro {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "l_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "l_clube", nullable = false)
    private Clubes clube;
    
    @Column(name = "l_descricao", nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "l_tipo_livro", nullable = false)
    private TipoLivro tipoLivro;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trilha> trilhas = new ArrayList<>();
    
    @Column(name = "a_ativo", nullable = false)
    private boolean ativo = true;

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

	public Clubes getClube() {
		return clube;
	}

	public void setClube(Clubes clube) {
		this.clube = clube;
	}

	public TipoLivro getTipoLivro() {
		return tipoLivro;
	}

	public void setTipoLivro(TipoLivro tipoLivro) {
		this.tipoLivro = tipoLivro;
	}

	public List<Trilha> getTrilhas() {
		return trilhas;
	}

	public void setTrilhas(List<Trilha> trilhas) {
		this.trilhas = trilhas;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
       
}