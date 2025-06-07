package br.com.igrejabatistadocordeiro.oanse.domain.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="OANSISTA_MANUAL")
public class OansistaManual {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oansista_id")
    private Oansista oansista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manual_id")
    private Manual manual;

    @Temporal(TemporalType.DATE)
    @Column(name="data_inicio")
    private Date dataInicio;
    @Temporal(TemporalType.DATE)
    @Column(name="data_conclusao")
    private Date dataConclusao;    
    private Boolean concluido;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Oansista getOansista() {
		return oansista;
	}
	public void setOansista(Oansista oansista) {
		this.oansista = oansista;
	}
	public Manual getManual() {
		return manual;
	}
	public void setManual(Manual manual) {
		this.manual = manual;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataConclusao() {
		return dataConclusao;
	}
	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}
	public Boolean getConcluido() {
		return concluido;
	}
	public void setConcluido(Boolean concluido) {
		this.concluido = concluido;
	}
    
}
