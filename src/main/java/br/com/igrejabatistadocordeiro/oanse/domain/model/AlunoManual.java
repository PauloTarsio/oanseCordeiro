package br.com.igrejabatistadocordeiro.oanse.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "aluno_manual")
public class AlunoManual {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "am_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "am_aluno_id")
    private Aluno aluno;

    @ManyToOne(optional = false)
    @JoinColumn(name = "am_livro_id")
    private Livro livro;

    @Column(name = "am_data_conclusao")
    private java.time.LocalDate dataConclusao;

    // Constructors
    public AlunoManual() {}
    public AlunoManual(Aluno aluno, Livro livro) {
        this.aluno = aluno;
        this.livro = livro;
    }

    // Getters and setters
    public Long getId() { return id; }
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
    public Livro getLivro() { return livro; }
    public void setLivro(Livro livro) { this.livro = livro; }
    public java.time.LocalDate getDataConclusao() { return dataConclusao; }
    public void setDataConclusao(java.time.LocalDate dataConclusao) { this.dataConclusao = dataConclusao; }
	public Boolean isConcluido() {		
		return dataConclusao != null;
	}
}