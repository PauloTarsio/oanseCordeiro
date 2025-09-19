CREATE TABLE aluno_secao (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    aluno_manual_id BIGINT NOT NULL,
    trilha_id BIGINT NOT NULL,
    secao_id BIGINT NOT NULL,
    data_conclusao DATETIME NOT NULL,
    CONSTRAINT fk_aluno_manual FOREIGN KEY (aluno_manual_id) REFERENCES aluno_manual(id),
    CONSTRAINT fk_trilha FOREIGN KEY (trilha_id) REFERENCES trilha(t_id),
    CONSTRAINT fk_secao FOREIGN KEY (secao_id) REFERENCES secao(s_id)
);
