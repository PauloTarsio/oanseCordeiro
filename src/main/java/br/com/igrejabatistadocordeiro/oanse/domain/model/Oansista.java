package br.com.igrejabatistadocordeiro.oanse.domain.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Oansista {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	private String nome;
    
	@Temporal(TemporalType.DATE)
    @Column(name="data_nascimento")
    private Date dataNascimento;
    
	private String rua;
    
	private Integer numero;
    
	private String bairro;
    
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
	    name = "Oansista_Responsavel",
	    joinColumns = @JoinColumn(name = "oansista_id"),
	    inverseJoinColumns = @JoinColumn(name = "responsavel_id")
	)
	private List<Responsavel> responsaveis = new ArrayList<>();

    @OneToMany(mappedBy = "oansista", cascade = CascadeType.ALL)
    private List<ManualDoOansista> manuais = new ArrayList<>();

    @OneToMany(mappedBy = "oansista", cascade = CascadeType.ALL)
    private List<SessaoDoOansista> sessoes = new ArrayList<>();

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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public List<ManualDoOansista> getManuais() {
		return manuais;
	}

	public void setManuais(List<ManualDoOansista> manuais) {
		this.manuais = manuais;
	}

	public List<SessaoDoOansista> getSessoes() {
		return sessoes;
	}

	public void setSessoes(List<SessaoDoOansista> sessoes) {
		this.sessoes = sessoes;
	}

	public void atualizaCom(Oansista oansista) {
	    this.nome = oansista.getNome();
	    this.dataNascimento = oansista.getDataNascimento();
	    this.rua = oansista.getRua();
	    this.numero = oansista.getNumero();
	    this.bairro = oansista.getBairro();

	    if (!oansista.getResponsaveis().isEmpty()) {
	        this.responsaveis.clear();
	        for (Responsavel r : oansista.getResponsaveis())
	            this.responsaveis.add(r);
	    } else {
	        this.responsaveis.clear();
	    }
	}

	public List<Responsavel> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(List<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}
	
}
