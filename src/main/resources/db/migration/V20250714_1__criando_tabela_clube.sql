CREATE TABLE clube (
    c_id SERIAL PRIMARY KEY,
    c_nome VARCHAR(20) NOT NULL UNIQUE,
    c_faixa_etaria VARCHAR(100)
);

INSERT INTO clube (c_nome, c_faixa_etaria) VALUES
('URSINHO', 'Crianças entre 4 e 5 anos'),
('FAISCA', 'Crianças entre 6 e 7 anos'),
('FLAMA', 'Crianças entre 8 e 9 anos'),
('TOCHA', 'Crianças entre 10 e 11 anos'),
('JV', 'Crianças entre 12 e 13 anos');