-- Adiciona coluna de relacionamento entre aluno e clube
ALTER TABLE aluno ADD COLUMN a_clube_id BIGINT;

-- Cria chave estrangeira para clube
ALTER TABLE aluno ADD CONSTRAINT fk_aluno_clube FOREIGN KEY (a_clube_id)
REFERENCES clube(c_id);
