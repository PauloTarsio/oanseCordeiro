CREATE TABLE Oansista (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    data_nascimento DATE NOT NULL,
    rua VARCHAR(255),
    numero INTEGER,
    bairro VARCHAR(255)
);

CREATE TABLE Responsavel (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(255)
);

CREATE TABLE Manual (
    id SERIAL PRIMARY KEY,
    clube VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL
);

CREATE TABLE Trilha (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    manual_id INTEGER REFERENCES Manual(id) ON DELETE CASCADE
);

CREATE TABLE Sessao (
    id SERIAL PRIMARY KEY,
    numero INTEGER NOT NULL,
    trilha_id INTEGER REFERENCES Trilha(id) ON DELETE CASCADE
);

CREATE TABLE Oansista_Manual (
    id SERIAL PRIMARY KEY,
    oansista_id INTEGER REFERENCES Oansista(id) ON DELETE CASCADE,
    manual_id INTEGER REFERENCES Manual(id) ON DELETE CASCADE,
    data_inicio DATE,
    data_conclusao DATE,
    concluida BOOLEAN
);

CREATE TABLE Oansista_sessao (
    id SERIAL PRIMARY KEY,
    oansista_id INTEGER REFERENCES Oansista(id) ON DELETE CASCADE,
    sessao_id INTEGER REFERENCES Sessao(id) ON DELETE CASCADE,
    data_inicio DATE,
    data_conclusao DATE,
    concluida BOOLEAN
);

ALTER TABLE Oansista
ADD COLUMN responsavel_id INTEGER REFERENCES Responsavel(id) ON DELETE CASCADE;