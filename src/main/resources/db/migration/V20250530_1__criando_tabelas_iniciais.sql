CREATE TABLE endereco (
    id SERIAL PRIMARY KEY,
    rua VARCHAR(255),
    numero VARCHAR(20),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    uf CHAR(2)
);

CREATE TABLE pessoa (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255),
    rg VARCHAR(20),
    cpf VARCHAR(20),
    cnpj VARCHAR(20),
    data_nascimento DATE,
    telefone1 VARCHAR(20),
    telefone2 VARCHAR(20),
    telefone3 VARCHAR(20),
    email VARCHAR(100),
    endereco_id INTEGER REFERENCES endereco(id)
);

CREATE TABLE pessoa_igreja (
    id SERIAL PRIMARY KEY,
    pessoa_id INTEGER REFERENCES pessoa(id)
);

CREATE TABLE pessoa_lider (
    id SERIAL PRIMARY KEY,
    pessoa_id INTEGER REFERENCES pessoa(id),
    pessoa_igreja_id INTEGER REFERENCES pessoa_igreja(id)
);

CREATE TABLE pessoa_aluno_igreja (
    id SERIAL PRIMARY KEY,
    pessoa_id INTEGER REFERENCES pessoa(id),
    pessoa_igreja_id INTEGER REFERENCES pessoa_igreja(id)
);
