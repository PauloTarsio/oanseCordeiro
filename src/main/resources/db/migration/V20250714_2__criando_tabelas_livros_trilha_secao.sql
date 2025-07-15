CREATE TABLE livro (
    l_id SERIAL PRIMARY KEY,
    l_clube VARCHAR(50) NOT NULL,
    l_descricao VARCHAR(255) NOT NULL,
    l_tipo_livro VARCHAR(50) NOT NULL
);

CREATE TABLE trilha (
    t_id SERIAL PRIMARY KEY,
    t_descricao VARCHAR(255) NOT NULL,
    t_livro_id INTEGER NOT NULL REFERENCES livro(l_id) ON DELETE CASCADE
);

CREATE TABLE secao (
    s_id SERIAL PRIMARY KEY,
    s_numero INTEGER NOT NULL,
    s_trilha_id INTEGER NOT NULL REFERENCES trilha(t_id) ON DELETE CASCADE
);