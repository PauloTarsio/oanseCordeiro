CREATE TABLE aluno_secao (
    as_id BIGSERIAL PRIMARY KEY,
    as_aluno_manual_id BIGINT NOT NULL,
    as_trilha_id BIGINT NOT NULL,
    as_secao_id BIGINT NOT NULL,
    as_data_conclusao DATE,
    CONSTRAINT fk_aluno_manual FOREIGN KEY (as_aluno_manual_id) REFERENCES aluno_manual(am_id),
    CONSTRAINT fk_trilha FOREIGN KEY (as_trilha_id) REFERENCES trilha(t_id),
    CONSTRAINT fk_secao FOREIGN KEY (as_secao_id) REFERENCES secao(s_id)
);