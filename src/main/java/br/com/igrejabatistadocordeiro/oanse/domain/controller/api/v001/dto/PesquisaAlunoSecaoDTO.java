package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import br.com.igrejabatistadocordeiro.oanse.domain.model.AlunoSecao;

public class PesquisaAlunoSecaoDTO {

	public Long id;
	public Long alunoManualId;
	public Long secaoId;
	private String livro;
	private String trilha;
	private String secao;
	public String dataConclusao;
	
	public PesquisaAlunoSecaoDTO(AlunoSecao alunoSecao) {
		this.id = alunoSecao.getId();
		this.alunoManualId = alunoSecao.getAlunoManualId();
		this.secaoId = alunoSecao.getSecaoId();
		this.dataConclusao = alunoSecao.getDataConclusao() != null ? alunoSecao.getDataConclusao().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null;
		this.setTrilha(alunoSecao.getTrilhaDescricao());
		this.setSecao(alunoSecao.getSecaoDescricao());
		this.livro = alunoSecao.getLivroDescricao();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAlunoManualId() {
		return alunoManualId;
	}
	public void setAlunoManualId(Long alunoManualId) {
		this.alunoManualId = alunoManualId;
	}
	public Long getSecaoId() {
		return secaoId;
	}
	public void setSecaoId(Long secaoId) {
		this.secaoId = secaoId;
	}
	public String getDataConclusao() {
		return dataConclusao;
	}
	public void setDataConclusao(String dataConclusao) {
		this.dataConclusao = dataConclusao;
	}
	public String getLivro() {
		return livro;
	}

	public void setLivro(String livro) {
		this.livro = livro;
	}

	public String getTrilha() {
		return trilha;
	}

	public void setTrilha(String trilha) {
		this.trilha = trilha;
	}

	public String getSecao() {
		return secao;
	}

	public void setSecao(String secao) {
		this.secao = secao;
	}
	
}
