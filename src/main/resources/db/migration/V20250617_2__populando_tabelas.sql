-- Inserção do Manual Carneiro
WITH manual_carneiro_insert AS (
  INSERT INTO Manual (clube, descricao)
  VALUES ('TOCHA', 'Carneiro')
  RETURNING id
)

-- Inserção das Trilhas do Manual Carneiro
INSERT INTO Trilha (nome, manual_id) VALUES
('Prova de Ingresso',         (SELECT id FROM manual_carneiro_insert)),
('Prova do grau',             (SELECT id FROM manual_carneiro_insert)),
('Exercício Bíblico 1',       (SELECT id FROM manual_carneiro_insert)),
('Exercício Bíblico 2',       (SELECT id FROM manual_carneiro_insert)),
('Exercício Bíblico 3',       (SELECT id FROM manual_carneiro_insert)),
('Exercício Bíblico 4',       (SELECT id FROM manual_carneiro_insert)),
('Missões',                   (SELECT id FROM manual_carneiro_insert)),
('Patriotismo',               (SELECT id FROM manual_carneiro_insert)),
('Meio ambiente e Saúde',     (SELECT id FROM manual_carneiro_insert)),
('Serviço',                   (SELECT id FROM manual_carneiro_insert)),
('Crédito extra',             (SELECT id FROM manual_carneiro_insert));

-- Prova de Ingresso
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 6) s(num)
JOIN Trilha t ON t.nome = 'Prova de Ingresso'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'TOCHA' AND m.descricao = 'Carneiro';

-- Prova do grau
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 12) s(num)
JOIN Trilha t ON t.nome = 'Prova do grau'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'TOCHA' AND m.descricao = 'Carneiro';

-- Exercício Bíblico 1
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 11) s(num)
JOIN Trilha t ON t.nome = 'Exercício Bíblico 1'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'TOCHA' AND m.descricao = 'Carneiro';

-- Exercício Bíblico 2
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 11) s(num)
JOIN Trilha t ON t.nome = 'Exercício Bíblico 2'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'TOCHA' AND m.descricao = 'Carneiro';

-- Exercício Bíblico 3
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 11) s(num)
JOIN Trilha t ON t.nome = 'Exercício Bíblico 3'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'TOCHA' AND m.descricao = 'Carneiro';

-- Exercício Bíblico 4
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 8) s(num)
JOIN Trilha t ON t.nome = 'Exercício Bíblico 4'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'TOCHA' AND m.descricao = 'Carneiro';

-- Missões
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 4) s(num)
JOIN Trilha t ON t.nome = 'Missões'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'TOCHA' AND m.descricao = 'Carneiro';

-- Patriotismo
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 3) s(num)
JOIN Trilha t ON t.nome = 'Patriotismo'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'TOCHA' AND m.descricao = 'Carneiro';

-- Meio ambiente e Saúde
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 4) s(num)
JOIN Trilha t ON t.nome = 'Meio ambiente e Saúde'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'TOCHA' AND m.descricao = 'Carneiro';

-- Serviço
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 3) s(num)
JOIN Trilha t ON t.nome = 'Serviço'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'TOCHA' AND m.descricao = 'Carneiro';

-- Crédito extra
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 7) s(num)
JOIN Trilha t ON t.nome = 'Crédito extra'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'TOCHA' AND m.descricao = 'Carneiro';


-- Inserção do Manual Leão
WITH manual_leao_insert AS (
  INSERT INTO Manual (clube, descricao)
  VALUES ('TOCHA', 'Leão')
  RETURNING id
)

-- Inserção das Trilhas do Manual Leão
INSERT INTO Trilha (nome, manual_id) VALUES
('Prova de Ingresso',         (SELECT id FROM manual_leao_insert)),
('Prova do grau',             (SELECT id FROM manual_leao_insert)),
('Exercício Bíblico 1',       (SELECT id FROM manual_leao_insert)),
('Exercício Bíblico 2',       (SELECT id FROM manual_leao_insert)),
('Exercício Bíblico 3',       (SELECT id FROM manual_leao_insert)),
('Exercício Bíblico 4',       (SELECT id FROM manual_leao_insert)),
('Missões',                   (SELECT id FROM manual_leao_insert)),
('Patriotismo',               (SELECT id FROM manual_leao_insert)),
('Meio ambiente e Saúde',     (SELECT id FROM manual_leao_insert)),
('Serviço',                   (SELECT id FROM manual_leao_insert)),
('Crédito extra',             (SELECT id FROM manual_leao_insert));

-- Prova de Ingresso
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 6) s(num)
JOIN Trilha t ON t.nome = 'Prova de Ingresso'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'TOCHA' AND m.descricao = 'Leão';

-- Prova do grau
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 11) s(num)
JOIN Trilha t ON t.nome = 'Prova do grau'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'TOCHA' AND m.descricao = 'Leão';

-- Exercício Bíblico 1
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 13) s(num)
JOIN Trilha t ON t.nome = 'Exercício Bíblico 1'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'TOCHA' AND m.descricao = 'Leão';

-- Exercício Bíblico 2
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 11) s(num)
JOIN Trilha t ON t.nome = 'Exercício Bíblico 2'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'TOCHA' AND m.descricao = 'Leão';

-- Exercício Bíblico 3
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 11) s(num)
JOIN Trilha t ON t.nome = 'Exercício Bíblico 3'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'TOCHA' AND m.descricao = 'Leão';

-- Exercício Bíblico 4
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 13) s(num)
JOIN Trilha t ON t.nome = 'Exercício Bíblico 4'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'TOCHA' AND m.descricao = 'Leão';

-- Missões
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 4) s(num)
JOIN Trilha t ON t.nome = 'Missões'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'TOCHA' AND m.descricao = 'Leão';

-- Patriotismo
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 2) s(num)
JOIN Trilha t ON t.nome = 'Patriotismo'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'TOCHA' AND m.descricao = 'Leão';

-- Meio ambiente e Saúde
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 4) s(num)
JOIN Trilha t ON t.nome = 'Meio ambiente e Saúde'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'TOCHA' AND m.descricao = 'Leão';

-- Serviço
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 3) s(num)
JOIN Trilha t ON t.nome = 'Serviço'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'TOCHA' AND m.descricao = 'Leão';

-- Crédito extra
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 7) s(num)
JOIN Trilha t ON t.nome = 'Crédito extra'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'TOCHA' AND m.descricao = 'Leão';
