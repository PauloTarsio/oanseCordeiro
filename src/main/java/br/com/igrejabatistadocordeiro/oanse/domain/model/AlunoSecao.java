package br.com.igrejabatistadocordeiro.oanse.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "aluno_secao")
public class AlunoSecao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "aluno_manual_id", nullable = false)
    private Long alunoManualId;

    @Column(name = "trilha_id", nullable = false)
    private Long trilhaId;

    @Column(name = "secao_id", nullable = false)
    private Long secaoId;

    @Column(name = "data_conclusao", nullable = false)
    private LocalDateTime dataConclusao;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAlunoManualId() { return alunoManualId; }
    public void setAlunoManualId(Long alunoManualId) { this.alunoManualId = alunoManualId; }

    public Long getTrilhaId() { return trilhaId; }
    public void setTrilhaId(Long trilhaId) { this.trilhaId = trilhaId; }

    public Long getSecaoId() { return secaoId; }
    public void setSecaoId(Long secaoId) { this.secaoId = secaoId; }

    public LocalDateTime getDataConclusao() { return dataConclusao; }
    public void setDataConclusao(LocalDateTime dataConclusao) { this.dataConclusao = dataConclusao; }
}
