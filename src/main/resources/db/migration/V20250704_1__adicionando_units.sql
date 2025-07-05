ALTER TABLE Responsavel ADD CONSTRAINT unique_telefone_email UNIQUE (telefone, email);

ALTER TABLE Oansista ADD CONSTRAINT unique_nome_dataNascimento UNIQUE (nome, data_nascimento);

ALTER TABLE Manual ADD CONSTRAINT unique_clube_descricao UNIQUE (clube, descricao);

ALTER TABLE Trilha ADD CONSTRAINT unique_nome_manual UNIQUE (nome, manual_id);

ALTER TABLE Sessao ADD CONSTRAINT unique_numero_trilha UNIQUE (numero, trilha_id);

ALTER TABLE Oansista_Manual ADD CONSTRAINT unique_oansista_manual UNIQUE (oansista_id, manual_id);

ALTER TABLE Oansista_sessao ADD CONSTRAINT unique_oansista_sessao UNIQUE (oansista_id, sessao_id);
