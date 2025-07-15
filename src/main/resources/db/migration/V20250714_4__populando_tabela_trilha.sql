INSERT INTO trilha (t_descricao, t_livro_id) VALUES 
  ('Prova de Ingresso', (SELECT l_id FROM livro WHERE l_descricao = 'Prova de Ingresso' AND l_clube = 'TOCHA'));

INSERT INTO trilha (t_descricao, t_livro_id) VALUES 
  ('Prova do grau', 	  (SELECT l_id FROM livro WHERE l_descricao = 'Manual Carneiro' AND l_clube = 'TOCHA')),
  ('Exercício Bíblico 1', (SELECT l_id FROM livro WHERE l_descricao = 'Manual Carneiro' AND l_clube = 'TOCHA')),
  ('Exercício Bíblico 2', (SELECT l_id FROM livro WHERE l_descricao = 'Manual Carneiro' AND l_clube = 'TOCHA')),
  ('Exercício Bíblico 3', (SELECT l_id FROM livro WHERE l_descricao = 'Manual Carneiro' AND l_clube = 'TOCHA')),
  ('Exercício Bíblico 4', (SELECT l_id FROM livro WHERE l_descricao = 'Manual Carneiro' AND l_clube = 'TOCHA')),
  ('Missões', 			  (SELECT l_id FROM livro WHERE l_descricao = 'Manual Carneiro' AND l_clube = 'TOCHA')),
  ('Patriotismo', 		  (SELECT l_id FROM livro WHERE l_descricao = 'Manual Carneiro' AND l_clube = 'TOCHA')),
  ('Meio ambiente e Saúde', (SELECT l_id FROM livro WHERE l_descricao = 'Manual Carneiro' AND l_clube = 'TOCHA')),
  ('Serviço', 				(SELECT l_id FROM livro WHERE l_descricao = 'Manual Carneiro' AND l_clube = 'TOCHA')),
  ('Crédito extra', 		(SELECT l_id FROM livro WHERE l_descricao = 'Manual Carneiro' AND l_clube = 'TOCHA'));

INSERT INTO trilha (t_descricao, t_livro_id) VALUES 
  ('Prova do grau', 		(SELECT l_id FROM livro WHERE l_descricao = 'Manual Leão' AND l_clube = 'TOCHA')),
  ('Exercício Bíblico 1', 	(SELECT l_id FROM livro WHERE l_descricao = 'Manual Leão' AND l_clube = 'TOCHA')),
  ('Exercício Bíblico 2', 	(SELECT l_id FROM livro WHERE l_descricao = 'Manual Leão' AND l_clube = 'TOCHA')),
  ('Exercício Bíblico 3', 	(SELECT l_id FROM livro WHERE l_descricao = 'Manual Leão' AND l_clube = 'TOCHA')),
  ('Exercício Bíblico 4', 	(SELECT l_id FROM livro WHERE l_descricao = 'Manual Leão' AND l_clube = 'TOCHA')),
  ('Missões', 				(SELECT l_id FROM livro WHERE l_descricao = 'Manual Leão' AND l_clube = 'TOCHA')),
  ('Patriotismo', 			(SELECT l_id FROM livro WHERE l_descricao = 'Manual Leão' AND l_clube = 'TOCHA')),
  ('Meio ambiente e Saúde', (SELECT l_id FROM livro WHERE l_descricao = 'Manual Leão' AND l_clube = 'TOCHA')),
  ('Serviço', 				(SELECT l_id FROM livro WHERE l_descricao = 'Manual Leão' AND l_clube = 'TOCHA')),
  ('Crédito extra', 		(SELECT l_id FROM livro WHERE l_descricao = 'Manual Leão' AND l_clube = 'TOCHA'));
  
INSERT INTO trilha (t_descricao, t_livro_id) VALUES 
  ('Prova de Ingresso', (SELECT l_id FROM livro WHERE l_descricao = 'Prova de Ingresso' AND l_clube = 'FLAMA'));
  
INSERT INTO trilha (t_descricao, t_livro_id) VALUES 
  ('Prova do Grau',         (SELECT l_id FROM livro WHERE l_descricao = 'Manual Sabiá' AND l_clube = 'FLAMA')),
  ('Exercício Bíblico 1',   (SELECT l_id FROM livro WHERE l_descricao = 'Manual Sabiá' AND l_clube = 'FLAMA')),
  ('Exercício Bíblico 2',   (SELECT l_id FROM livro WHERE l_descricao = 'Manual Sabiá' AND l_clube = 'FLAMA')),
  ('Exercício Bíblico 3',   (SELECT l_id FROM livro WHERE l_descricao = 'Manual Sabiá' AND l_clube = 'FLAMA')),
  ('Exercício Bíblico 4',   (SELECT l_id FROM livro WHERE l_descricao = 'Manual Sabiá' AND l_clube = 'FLAMA')),
  ('Atividades e Missões 1',(SELECT l_id FROM livro WHERE l_descricao = 'Manual Sabiá' AND l_clube = 'FLAMA')),
  ('Atividades e Pátria 2', (SELECT l_id FROM livro WHERE l_descricao = 'Manual Sabiá' AND l_clube = 'FLAMA')),
  ('Atividades e Saúde 3',  (SELECT l_id FROM livro WHERE l_descricao = 'Manual Sabiá' AND l_clube = 'FLAMA')),
  ('Atividades e Serviço 4',(SELECT l_id FROM livro WHERE l_descricao = 'Manual Sabiá' AND l_clube = 'FLAMA')),
  ('Créditos Extras',       (SELECT l_id FROM livro WHERE l_descricao = 'Manual Sabiá' AND l_clube = 'FLAMA'));
