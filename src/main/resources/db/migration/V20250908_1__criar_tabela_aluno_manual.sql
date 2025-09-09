-- Migration para criar a tabela aluno_manual
CREATE TABLE aluno_manual (
    am_id BIGSERIAL PRIMARY KEY,
    am_aluno_id BIGINT NOT NULL,
    am_livro_id BIGINT NOT NULL,
    CONSTRAINT fk_aluno_manual_aluno FOREIGN KEY (am_aluno_id) REFERENCES aluno(a_id),
    CONSTRAINT fk_aluno_manual_livro FOREIGN KEY (am_livro_id) REFERENCES livro(l_id)
);
