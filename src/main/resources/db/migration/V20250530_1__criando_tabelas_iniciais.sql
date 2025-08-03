CREATE TABLE endereco (
    e_id SERIAL PRIMARY KEY,
    e_rua VARCHAR(255),
    e_numero VARCHAR(20),
    e_bairro VARCHAR(100),
    e_cidade VARCHAR(100),
    e_uf CHAR(2)
);

CREATE TABLE dados_pessoais (
    dp_id SERIAL PRIMARY KEY,
    dp_descricao VARCHAR(255),
    dp_tipo VARCHAR(10) NOT NULL,
    dp_rg VARCHAR(20),
    dp_cpf VARCHAR(20),
    dp_cnpj VARCHAR(20),
    dp_data_nascimento DATE,
    dp_telefone1 VARCHAR(20),
    dp_telefone2 VARCHAR(20),
    dp_telefone3 VARCHAR(20),
    dp_email VARCHAR(100),
    dp_endereco_id INTEGER REFERENCES endereco(e_id)
);

CREATE TABLE igreja (
    i_id SERIAL PRIMARY KEY,
    i_dados_pessoais_id INTEGER REFERENCES dados_pessoais(dp_id)
);

CREATE TABLE aluno (
    a_id SERIAL PRIMARY KEY,
    a_dados_pessoais_id INTEGER REFERENCES dados_pessoais(dp_id),
    a_igreja_id INTEGER REFERENCES igreja(i_id)
);
