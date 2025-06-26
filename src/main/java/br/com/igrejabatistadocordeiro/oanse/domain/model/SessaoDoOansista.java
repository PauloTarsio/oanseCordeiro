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
@Table(name="OANSISTA_SESSAO")
public class SessaoDoOansista {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oansista_id")
    private Oansista oansista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sessao_id")
    private Sessao sessao;
    
    @Temporal(TemporalType.DATE)
    @Column(name="data_inicio")
    private Date dataInicio;
    
    @Temporal(TemporalType.DATE)
    @Column(name="data_conclusao")
    private Date dataConclusao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oansista_manual_id")
    private ManualDoOansista manualDoOansista;
    
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
	public Sessao getSessao() {
		return sessao;
	}
	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
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
	public ManualDoOansista getManualDoOansista() {
		return manualDoOansista;
	}
	public void setManualDoOansista(ManualDoOansista manualDoOansista) {
		this.manualDoOansista = manualDoOansista;
	}
    
}
