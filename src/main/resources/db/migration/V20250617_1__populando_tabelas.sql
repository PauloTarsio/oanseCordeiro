-- Manual da Prova de Ingresso ---------------------------------------------------------------------------
WITH manual_faisca_prova_ingresso_insert AS (
  INSERT INTO Manual (clube, descricao)
  VALUES ('CLUBE', 'Prova de Ingresso')
  RETURNING id
)

INSERT INTO Trilha (nome, manual_id)
VALUES ('Prova de Ingresso', (SELECT id FROM manual_faisca_prova_ingresso_insert));

INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 6) AS s(num)
JOIN Trilha t ON t.nome = 'Prova de ingresso';


-- FAISCAS - Manual do Saltador -----------------------------------------------------------------------------------
WITH manual_saltador_insert AS (
  INSERT INTO Manual (clube, descricao)
  VALUES ('FAISCAS', 'Manual do Saltador')
  RETURNING id
)
INSERT INTO Trilha (nome, manual_id) VALUES
('Trilha do Grau',         (SELECT id FROM manual_saltador_insert)),
('Joia Vermelha 1',        (SELECT id FROM manual_saltador_insert)),
('Joia Vermelha 2',        (SELECT id FROM manual_saltador_insert)),
('Joia Vermelha 3',        (SELECT id FROM manual_saltador_insert)),
('Joia Vermelha 4',        (SELECT id FROM manual_saltador_insert)),
('Joia Verde 1',           (SELECT id FROM manual_saltador_insert)),
('Joia Verde 2',           (SELECT id FROM manual_saltador_insert)),
('Joia Verde 3',           (SELECT id FROM manual_saltador_insert)),
('Joia Verde 4',           (SELECT id FROM manual_saltador_insert)),
('Campina do Saltador',    (SELECT id FROM manual_saltador_insert));

-- Sessões para "Trilha do Grau"
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 6) AS s(num)
JOIN Trilha t ON t.nome = 'Trilha do Grau'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Saltador';

-- Sessões para "Joia Vermelha 1"
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 4) AS s(num)
JOIN Trilha t ON t.nome = 'Joia Vermelha 1'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Saltador';

-- Sessões para "Joia Vermelha 2"
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 4) AS s(num)
JOIN Trilha t ON t.nome = 'Joia Vermelha 2'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Saltador';

-- Sessões para "Joia Vermelha 3"
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 9) AS s(num)
JOIN Trilha t ON t.nome = 'Joia Vermelha 3'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Saltador';

-- Sessões para "Joia Vermelha 4"
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 6) AS s(num)
JOIN Trilha t ON t.nome = 'Joia Vermelha 4'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Saltador';

-- Sessões para "Joia Verde 1"
INSERT INTO Sessao (numero, trilha_id)
VALUES (1, (
  SELECT t.id
  FROM Trilha t
  JOIN Manual m ON t.manual_id = m.id
  WHERE t.nome = 'Joia Verde 1' AND m.clube = 'FAISCAS' AND m.descricao = 'Manual do Saltador'
));

-- Sessões para "Joia Verde 2"
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 2) AS s(num)
JOIN Trilha t ON t.nome = 'Joia Verde 2'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Saltador';

-- Sessões para "Joia Verde 3"
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 4) AS s(num)
JOIN Trilha t ON t.nome = 'Joia Verde 3'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Saltador';

-- Sessões para "Joia Verde 4"
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 2) AS s(num)
JOIN Trilha t ON t.nome = 'Joia Verde 4'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Saltador';

-- Sessões para "Campina do Saltador"
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 7) AS s(num)
JOIN Trilha t ON t.nome = 'Campina do Saltador'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Saltador';

-- FAISCAS - Manual do Caminhante -----------------------------------------------------------------------------------
WITH manual_caminhante_insert AS (
  INSERT INTO Manual (clube, descricao)
  VALUES ('FAISCAS', 'Manual do Caminhante')
  RETURNING id
)
INSERT INTO Trilha (nome, manual_id) VALUES
('Trilha do Grau',         (SELECT id FROM manual_caminhante_insert)),
('Joia Vermelha 1',        (SELECT id FROM manual_caminhante_insert)),
('Joia Vermelha 2',        (SELECT id FROM manual_caminhante_insert)),
('Joia Vermelha 3',        (SELECT id FROM manual_caminhante_insert)),
('Joia Vermelha 4',        (SELECT id FROM manual_caminhante_insert)),
('Joia Verde 1',           (SELECT id FROM manual_caminhante_insert)),
('Joia Verde 2',           (SELECT id FROM manual_caminhante_insert)),
('Joia Verde 3',           (SELECT id FROM manual_caminhante_insert)),
('Joia Verde 4',           (SELECT id FROM manual_caminhante_insert)),
('Campina do Caminhante',  (SELECT id FROM manual_caminhante_insert));

INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 6) AS s(num)
JOIN Trilha t ON t.nome = 'Trilha do Grau'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Caminhante';

-- Sessões para "Joia Vermelha 1"
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 6) AS s(num)
JOIN Trilha t ON t.nome = 'Joia Vermelha 1'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Caminhante';

-- Sessões para "Joia Vermelha 2"
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 5) AS s(num)
JOIN Trilha t ON t.nome = 'Joia Vermelha 2'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Caminhante';

-- Sessões para "Joia Vermelha 3"
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 10) AS s(num)
JOIN Trilha t ON t.nome = 'Joia Vermelha 3'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Caminhante';

-- Sessões para "Joia Vermelha 4"
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 8) AS s(num)
JOIN Trilha t ON t.nome = 'Joia Vermelha 4'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Caminhante';

-- Sessões para "Joia Verde 1"
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 2) AS s(num)
JOIN Trilha t ON t.nome = 'Joia Verde 1'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Caminhante';

-- Sessões para "Joia Verde 2"
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 3) AS s(num)
JOIN Trilha t ON t.nome = 'Joia Verde 2'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Caminhante';

-- Sessões para "Joia Verde 3"
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 5) AS s(num)
JOIN Trilha t ON t.nome = 'Joia Verde 3'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Caminhante';

-- Sessões para "Joia Verde 4"
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 4) AS s(num)
JOIN Trilha t ON t.nome = 'Joia Verde 4'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Caminhante';

-- Sessões para "Campina do Caminhante"
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 7) AS s(num)
JOIN Trilha t ON t.nome = 'Campina do Caminhante'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Caminhante';

-- FAISCAS - Manual do Escalador -----------------------------------------------------------------------------------
WITH manual_escalador_insert AS (
  INSERT INTO Manual (clube, descricao)
  VALUES ('FAISCAS', 'Manual do Escalador')
  RETURNING id
)
INSERT INTO Trilha (nome, manual_id) VALUES
('Trilha do Grau',         (SELECT id FROM manual_escalador_insert)),
('Joia Vermelha 1',        (SELECT id FROM manual_escalador_insert)),
('Joia Vermelha 2',        (SELECT id FROM manual_escalador_insert)),
('Joia Vermelha 3',        (SELECT id FROM manual_escalador_insert)),
('Joia Vermelha 4',        (SELECT id FROM manual_escalador_insert)),
('Joia Verde 1',           (SELECT id FROM manual_escalador_insert)),
('Joia Verde 2',           (SELECT id FROM manual_escalador_insert)),
('Joia Verde 3',           (SELECT id FROM manual_escalador_insert)),
('Joia Verde 4',           (SELECT id FROM manual_escalador_insert)),
('Planalto do Escalador',  (SELECT id FROM manual_escalador_insert));

-- Trilha do Grau
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 6) s(num)
JOIN Trilha t ON t.nome = 'Trilha do Grau'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Escalador';

-- Joia Vermelha 1
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 7) s(num)
JOIN Trilha t ON t.nome = 'Joia Vermelha 1'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Escalador';

-- Joia Vermelha 2
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 7) s(num)
JOIN Trilha t ON t.nome = 'Joia Vermelha 2'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Escalador';

-- Joia Vermelha 3
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 7) s(num)
JOIN Trilha t ON t.nome = 'Joia Vermelha 3'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Escalador';

-- Joia Vermelha 4
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 7) s(num)
JOIN Trilha t ON t.nome = 'Joia Vermelha 4'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Escalador';

-- Joia Verde 1
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 3) s(num)
JOIN Trilha t ON t.nome = 'Joia Verde 1'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Escalador';

-- Joia Verde 2
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 4) s(num)
JOIN Trilha t ON t.nome = 'Joia Verde 2'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Escalador';

-- Joia Verde 3
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 6) s(num)
JOIN Trilha t ON t.nome = 'Joia Verde 3'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Escalador';

-- Joia Verde 4
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 1) s(num)
JOIN Trilha t ON t.nome = 'Joia Verde 4'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Escalador';

-- Planalto do Escalador
INSERT INTO Sessao (numero, trilha_id)
SELECT s.num, t.id
FROM generate_series(1, 7) s(num)
JOIN Trilha t ON t.nome = 'Planalto do Escalador'
JOIN Manual m ON t.manual_id = m.id
WHERE m.clube = 'FAISCAS' AND m.descricao = 'Manual do Escalador';
