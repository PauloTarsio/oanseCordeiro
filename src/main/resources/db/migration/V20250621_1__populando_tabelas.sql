-- Inserção do Manual Sabiá
WITH manual_sabia_insert AS (
  INSERT INTO Manual (clube, descricao)
  VALUES ('FLAMA', 'Sabiá')
  RETURNING id
)

-- Trilhas do Manual Sabiá
INSERT INTO Trilha (nome, manual_id) VALUES
('Prova do grau',             (SELECT id FROM manual_sabia_insert)),
('Exercício Bíblico 1',       (SELECT id FROM manual_sabia_insert)),
('Exercício Bíblico 2',       (SELECT id FROM manual_sabia_insert)),
('Exercício Bíblico 3',       (SELECT id FROM manual_sabia_insert)),
('Exercício Bíblico 4',       (SELECT id FROM manual_sabia_insert)),
('Atividades e Missões 1',    (SELECT id FROM manual_sabia_insert)),
('Atividades e Pátria 2',     (SELECT id FROM manual_sabia_insert)),
('Atividades e Saúde 3',      (SELECT id FROM manual_sabia_insert)),
('Atividades e Serviço 4',    (SELECT id FROM manual_sabia_insert)),
('Créditos Extras',           (SELECT id FROM manual_sabia_insert));

-- Sessoes do Manual Sabiá
-- Prova do Grau
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 11) s(num)
JOIN Trilha t ON t.nome = 'Prova do grau'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FLAMA' AND m.descricao = 'Sabiá';

-- Exercícios Bíblicos
DO $$ 
DECLARE i INT;
BEGIN
  FOR i IN 1..4 LOOP
    INSERT INTO Sessao (numero, trilha_id)
    SELECT s.num, t.id
    FROM generate_series(1, 11) s(num)
    JOIN Trilha t ON t.nome = format('Exercício Bíblico %s', i)
    JOIN Manual m ON t.manual_id = m.id
    WHERE m.clube = 'FLAMA' AND m.descricao = 'Sabiá';
  END LOOP;
END $$;

-- Atividades e Missões 1
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 4) s(num)
JOIN Trilha t ON t.nome = 'Atividades e Missões 1'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FLAMA' AND m.descricao = 'Sabiá';

-- Atividades e Pátria 2
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 4) s(num)
JOIN Trilha t ON t.nome = 'Atividades e Pátria 2'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FLAMA' AND m.descricao = 'Sabiá';

-- Atividades e Saúde 3
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 4) s(num)
JOIN Trilha t ON t.nome = 'Atividades e Saúde 3'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FLAMA' AND m.descricao = 'Sabiá';

-- Atividades e Serviço 4
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 7) s(num)
JOIN Trilha t ON t.nome = 'Atividades e Serviço 4'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FLAMA' AND m.descricao = 'Sabiá';

-- Créditos Extras
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 7) s(num)
JOIN Trilha t ON t.nome = 'Créditos Extras'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FLAMA' AND m.descricao = 'Sabiá';


-- Inserção do Manual Águia
WITH manual_aguia_insert AS (
  INSERT INTO Manual (clube, descricao)
  VALUES ('FLAMA', 'Águia')
  RETURNING id
)

-- Trilhas do Manual Águia
INSERT INTO Trilha (nome, manual_id) VALUES
('Prova do grau',             (SELECT id FROM manual_aguia_insert)),
('Exercício Bíblico 1',       (SELECT id FROM manual_aguia_insert)),
('Exercício Bíblico 2',       (SELECT id FROM manual_aguia_insert)),
('Exercício Bíblico 3',       (SELECT id FROM manual_aguia_insert)),
('Exercício Bíblico 4',       (SELECT id FROM manual_aguia_insert)),
('Atividades e Missões 1',    (SELECT id FROM manual_aguia_insert)),
('Atividades e Pátria 2',     (SELECT id FROM manual_aguia_insert)),
('Atividades e Saúde 3',      (SELECT id FROM manual_aguia_insert)),
('Atividades e Serviço 4',    (SELECT id FROM manual_aguia_insert)),
('Créditos Extras',           (SELECT id FROM manual_aguia_insert));

-- Sessoes do Manual Águia
-- Prova do Grau
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 10) s(num)
JOIN Trilha t ON t.nome = 'Prova do grau'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FLAMA' AND m.descricao = 'Águia';

-- Exercícios Bíblicos
DO $$ 
DECLARE i INT;
BEGIN
  FOR i IN 1..4 LOOP
    INSERT INTO Sessao (numero, trilha_id)
    SELECT s.num, t.id
    FROM generate_series(1, 11) s(num)
    JOIN Trilha t ON t.nome = format('Exercício Bíblico %s', i)
    JOIN Manual m ON t.manual_id = m.id
    WHERE m.clube = 'FLAMA' AND m.descricao = 'Águia';
  END LOOP;
END $$;

-- Atividades
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM (
  SELECT 'Atividades e Missões 1' AS nome, 4 AS total
  UNION ALL SELECT 'Atividades e Pátria 2', 4
  UNION ALL SELECT 'Atividades e Saúde 3', 4
  UNION ALL SELECT 'Atividades e Serviço 4', 4
  UNION ALL SELECT 'Créditos Extras', 7
) AS atividade
JOIN generate_series(1, atividade.total) s(num) ON true
JOIN Trilha t ON t.nome = atividade.nome
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FLAMA' AND m.descricao = 'Águia';
