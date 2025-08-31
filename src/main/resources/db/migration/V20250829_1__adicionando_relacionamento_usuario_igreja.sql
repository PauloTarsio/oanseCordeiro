-- Adiciona o campo u_igreja_id na tabela usuario e cria a relação com igreja
ALTER TABLE usuario
ADD COLUMN u_igreja_id BIGINT;

ALTER TABLE usuario
ADD CONSTRAINT fk_usuario_igreja
FOREIGN KEY (u_igreja_id)
REFERENCES igreja(i_id);
