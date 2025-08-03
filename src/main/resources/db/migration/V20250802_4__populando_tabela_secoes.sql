DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Prova de Ingresso' AND l.l_descricao = 'Prova de Ingresso' AND l.l_clube = 'URSINHO';
  FOR i IN 1..5 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Trilha do grau alegria' AND l.l_descricao = 'Celebrações de alegria' AND l.l_clube = 'URSINHO';
  FOR i IN 1..2 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Trilha da ovelinha um' AND l.l_descricao = 'Celebrações de alegria' AND l.l_clube = 'URSINHO';
  FOR i IN 1..2 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i + 2, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Caminhada do elefante um' AND l.l_descricao = 'Celebrações de alegria' AND l.l_clube = 'URSINHO';
  FOR i IN 1..2 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i + 4, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Trilha da ovelinha dois' AND l.l_descricao = 'Celebrações de alegria' AND l.l_clube = 'URSINHO';
  FOR i IN 1..3 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i + 6, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Caminhada do elefante dois' AND l.l_descricao = 'Celebrações de alegria' AND l.l_clube = 'URSINHO';
  FOR i IN 1..3 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i + 9, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Trilha da ovelha três' AND l.l_descricao = 'Celebrações de alegria' AND l.l_clube = 'URSINHO';
  FOR i IN 1..3 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i + 12, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Caminhada do elefante três' AND l.l_descricao = 'Celebrações de alegria' AND l.l_clube = 'URSINHO';
  FOR i IN 1..3 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i + 15, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Trilha da ovelha quatro' AND l.l_descricao = 'Celebrações de alegria' AND l.l_clube = 'URSINHO';
  FOR i IN 1..3 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i + 18, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Caminhada do elefante quatro' AND l.l_descricao = 'Celebrações de alegria' AND l.l_clube = 'URSINHO';
  FOR i IN 1..3 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i + 21, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Celebração da alegria' AND l.l_descricao = 'Celebrações de alegria' AND l.l_clube = 'URSINHO';
  FOR i IN 1..7 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Prova de Ingresso' AND l.l_descricao = 'Prova de Ingresso' AND l.l_clube = 'FAISCA';
  FOR i IN 1..6 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Trilha do grau' AND l.l_descricao = 'Manual Saltador' AND l.l_clube = 'FAISCA';
  FOR i IN 1..6 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Vermelha 1' AND l.l_descricao = 'Manual Saltador' AND l.l_clube = 'FAISCA';
  FOR i IN 1..4 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Vermelha 2' AND l.l_descricao = 'Manual Saltador' AND l.l_clube = 'FAISCA';
  FOR i IN 1..4 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

-- Joia Vermelha 3 (Manual Saltador)
DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Vermelha 3' AND l.l_descricao = 'Manual Saltador' AND l.l_clube = 'FAISCA';
  FOR i IN 1..9 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Vermelha 4' AND l.l_descricao = 'Manual Saltador' AND l.l_clube = 'FAISCA';
  FOR i IN 1..6 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Verde 1' AND l.l_descricao = 'Manual Saltador' AND l.l_clube = 'FAISCA';
  FOR i IN 1..1 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Verde 2' AND l.l_descricao = 'Manual Saltador' AND l.l_clube = 'FAISCA';
  FOR i IN 1..2 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Verde 3' AND l.l_descricao = 'Manual Saltador' AND l.l_clube = 'FAISCA';
  FOR i IN 1..4 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Verde 4' AND l.l_descricao = 'Manual Saltador' AND l.l_clube = 'FAISCA';
  FOR i IN 1..2 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Campina do Saltador' AND l.l_descricao = 'Manual Saltador' AND l.l_clube = 'FAISCA';
  FOR i IN 1..7 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Trilha do grau' AND l.l_descricao = 'Manual Caminhante' AND l.l_clube = 'FAISCA';
  FOR i IN 1..6 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Vermelha 1' AND l.l_descricao = 'Manual Caminhante' AND l.l_clube = 'FAISCA';
  FOR i IN 1..6 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Vermelha 2' AND l.l_descricao = 'Manual Caminhante' AND l.l_clube = 'FAISCA';
  FOR i IN 1..5 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Vermelha 3' AND l.l_descricao = 'Manual Caminhante' AND l.l_clube = 'FAISCA';
  FOR i IN 1..10 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Vermelha 4' AND l.l_descricao = 'Manual Caminhante' AND l.l_clube = 'FAISCA';
  FOR i IN 1..8 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Verde 1' AND l.l_descricao = 'Manual Caminhante' AND l.l_clube = 'FAISCA';
  FOR i IN 1..2 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Verde 2' AND l.l_descricao = 'Manual Caminhante' AND l.l_clube = 'FAISCA';
  FOR i IN 1..3 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Verde 3' AND l.l_descricao = 'Manual Caminhante' AND l.l_clube = 'FAISCA';
  FOR i IN 1..5 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Verde 4' AND l.l_descricao = 'Manual Caminhante' AND l.l_clube = 'FAISCA';
  FOR i IN 1..4 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Campina do Caminhante' AND l.l_descricao = 'Manual Caminhante' AND l.l_clube = 'FAISCA';
  FOR i IN 1..7 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Trilha do grau' AND l.l_descricao = 'Manual Escalador' AND l.l_clube = 'FAISCA';
  FOR i IN 1..6 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Vermelha 1' AND l.l_descricao = 'Manual Escalador' AND l.l_clube = 'FAISCA';
  FOR i IN 1..8 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Vermelha 2' AND l.l_descricao = 'Manual Escalador' AND l.l_clube = 'FAISCA';
  FOR i IN 1..7 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Vermelha 3' AND l.l_descricao = 'Manual Escalador' AND l.l_clube = 'FAISCA';
  FOR i IN 1..7 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Vermelha 4' AND l.l_descricao = 'Manual Escalador' AND l.l_clube = 'FAISCA';
  FOR i IN 1..7 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Verde 1' AND l.l_descricao = 'Manual Escalador' AND l.l_clube = 'FAISCA';
  FOR i IN 1..3 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Verde 2' AND l.l_descricao = 'Manual Escalador' AND l.l_clube = 'FAISCA';
  FOR i IN 1..4 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Verde 3' AND l.l_descricao = 'Manual Escalador' AND l.l_clube = 'FAISCA';
  FOR i IN 1..6 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Joia Verde 4' AND l.l_descricao = 'Manual Escalador' AND l.l_clube = 'FAISCA';
  FOR i IN 1..1 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Campina do Escalador' AND l.l_descricao = 'Manual Escalador' AND l.l_clube = 'FAISCA';
  FOR i IN 1..7 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;