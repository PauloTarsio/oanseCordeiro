package br.com.igrejabatistadocordeiro.oanse.domain.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "aluno_secao")
public class AlunoSecao {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "as_id")
    private Long id;

    @Column(name = "as_aluno_manual_id", nullable = false)
    private Long alunoManualId;

    @Column(name = "as_trilha_id", nullable = false)
    private Long trilhaId;

    @Column(name = "as_secao_id", nullable = false)
    private Long secaoId;

    @Column(name = "as_data_conclusao")
    private LocalDate dataConclusao;

    @Column(name = "as_trilha_descricao")
    private String trilhaDescricao;

    @Column(name = "as_secao_descricao")
    private String secaoDescricao;

    @Column(name = "as_livro_descricao")
    private String livroDescricao;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAlunoManualId() { return alunoManualId; }
    public void setAlunoManualId(Long alunoManualId) { this.alunoManualId = alunoManualId; }

    public Long getTrilhaId() { return trilhaId; }
    public void setTrilhaId(Long trilhaId) { this.trilhaId = trilhaId; }

    public Long getSecaoId() { return secaoId; }
    public void setSecaoId(Long secaoId) { this.secaoId = secaoId; }

    public LocalDate getDataConclusao() { return dataConclusao; }
    public void setDataConclusao(LocalDate dataConclusao) { this.dataConclusao = dataConclusao; }

    public String getTrilhaDescricao() { return trilhaDescricao; }
    public void setTrilhaDescricao(String trilhaDescricao) { this.trilhaDescricao = trilhaDescricao; }

    public String getSecaoDescricao() { return secaoDescricao; }
    public void setSecaoDescricao(String secaoDescricao) { this.secaoDescricao = secaoDescricao; }

    public String getLivroDescricao() { return livroDescricao; }
    public void setLivroDescricao(String livroDescricao) { this.livroDescricao = livroDescricao; }
}