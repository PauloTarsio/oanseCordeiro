DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Prova de Ingresso' 
    AND l.l_descricao = 'Prova de Ingresso'
    AND l.l_clube = 'TOCHA';
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
  WHERE t.t_descricao = 'Prova do grau' 
    AND l.l_descricao = 'Manual Carneiro'
    AND l.l_clube = 'TOCHA';
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
  WHERE t.t_descricao = 'Exercício Bíblico 1' 
    AND l.l_descricao = 'Manual Carneiro'
    AND l.l_clube = 'TOCHA';
  FOR i IN 1..11 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
end $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Exercício Bíblico 2' 
    AND l.l_descricao = 'Manual Carneiro'
    AND l.l_clube = 'TOCHA';
  FOR i IN 1..11 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Exercício Bíblico 3' 
    AND l.l_descricao = 'Manual Carneiro'
    AND l.l_clube = 'TOCHA';
  FOR i IN 1..12 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Exercício Bíblico 4' 
    AND l.l_descricao = 'Manual Carneiro'
    AND l.l_clube = 'TOCHA';
  FOR i IN 1..11 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

DO $$
DECLARE trilha_id INTEGER;
BEGIN
  SELECT t.t_id INTO trilha_id
  FROM trilha t 
  JOIN livro l ON t.t_livro_id = l.l_id
  WHERE t.t_descricao = 'Missões' 
    AND l.l_descricao = 'Manual Carneiro'
    AND l.l_clube = 'TOCHA';
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
  WHERE t.t_descricao = 'Patriotismo' 
    AND l.l_descricao = 'Manual Carneiro'
    AND l.l_clube = 'TOCHA';
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
  WHERE t.t_descricao = 'Meio ambiente e Saúde' 
    AND l.l_descricao = 'Manual Carneiro'
    AND l.l_clube = 'TOCHA';
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
  WHERE t.t_descricao = 'Serviço' 
    AND l.l_descricao = 'Manual Carneiro'
    AND l.l_clube = 'TOCHA';
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
  WHERE t.t_descricao = 'Crédito extra' 
    AND l.l_descricao = 'Manual Carneiro'
    AND l.l_clube = 'TOCHA';
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
  WHERE t.t_descricao = 'Prova de Ingresso' 
    AND l.l_descricao = 'Prova de Ingresso'
    AND l.l_clube = 'FLAMA';
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
  WHERE t.t_descricao = 'Prova do Grau' 
    AND l.l_descricao = 'Manual Sabiá'
    AND l.l_clube = 'FLAMA';
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
  WHERE t.t_descricao = 'Exercício Bíblico 1' 
    AND l.l_descricao = 'Manual Sabiá'
    AND l.l_clube = 'FLAMA';
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
  WHERE t.t_descricao = 'Exercício Bíblico 2' 
    AND l.l_descricao = 'Manual Sabiá'
    AND l.l_clube = 'FLAMA';
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
  WHERE t.t_descricao = 'Exercício Bíblico 3' 
    AND l.l_descricao = 'Manual Sabiá'
    AND l.l_clube = 'FLAMA';
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
  WHERE t.t_descricao = 'Exercício Bíblico 4' 
    AND l.l_descricao = 'Manual Sabiá'
    AND l.l_clube = 'FLAMA';
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
  WHERE t.t_descricao = 'Atividades e Missões 1' 
    AND l.l_descricao = 'Manual Sabiá'
    AND l.l_clube = 'FLAMA';
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
  WHERE t.t_descricao = 'Atividades e Pátria 2' 
    AND l.l_descricao = 'Manual Sabiá'
    AND l.l_clube = 'FLAMA';
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
  WHERE t.t_descricao = 'Atividades e Saúde 3' 
    AND l.l_descricao = 'Manual Sabiá'
    AND l.l_clube = 'FLAMA';
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
  WHERE t.t_descricao = 'Atividades e Serviço 4' 
    AND l.l_descricao = 'Manual Sabiá'
    AND l.l_clube = 'FLAMA';
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
  WHERE t.t_descricao = 'Créditos Extras' 
    AND l.l_descricao = 'Manual Sabiá'
    AND l.l_clube = 'FLAMA';
  FOR i IN 1..7 LOOP
    INSERT INTO secao (s_numero, s_trilha_id) VALUES (i, trilha_id);
  END LOOP;
END $$;

