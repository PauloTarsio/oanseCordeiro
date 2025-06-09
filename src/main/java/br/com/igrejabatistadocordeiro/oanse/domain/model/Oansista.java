package br.com.igrejabatistadocordeiro.oanse.domain.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "responsavel_id")
    private Responsavel responsavel;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "OansistaManual",
            joinColumns = @JoinColumn(name = "oansista_id"),
            inverseJoinColumns = @JoinColumn(name = "manual_id")
    )
    private List<Manual> manuais = new ArrayList<>();

    @OneToMany(mappedBy = "oansista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OansistaSecao> oansistaSecao = new ArrayList<>();

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

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = Date.valueOf(dataNascimento);
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

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public List<Manual> getManuais() {
		return manuais;
	}

	public void setManuais(List<Manual> manuais) {
		this.manuais = manuais;
	}

	public List<OansistaSecao> getOansistaSecao() {
		return oansistaSecao;
	}

	public void setOansistaSecao(List<OansistaSecao> oansistaSecao) {
		this.oansistaSecao = oansistaSecao;
	}

	public void atualizaCom(Oansista oansista) {
		this.nome = oansista.getNome();
		this.dataNascimento = oansista.getDataNascimento();
		this.rua = oansista.getRua();
		this.numero = oansista.getNumero();
		this.bairro = oansista.getBairro();
		if (oansista.getResponsavel() != null) {
			if (this.responsavel == null) {
				this.responsavel = new Responsavel();
			}
			this.responsavel.setId(oansista.getResponsavel().getId());
		} else {
			this.responsavel = null;
		}		
	}
	
}
