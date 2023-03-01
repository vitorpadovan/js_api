truncate evento;
truncate missao cascade;
ALTER SEQUENCE evento_seq RESTART WITH 1;
ALTER SEQUENCE missao_seq RESTART WITH 1;
insert into missao(idmissao,nomemissao, endereco, estado,cidade )
values
(nextval('missao_seq'),'Ribeirão Preto','R. José Jaime Delibo, 181', 'SP','Ribeirão Preto'),
(nextval('missao_seq'),'São Paulo Apóstolo','R. José da Silva, 603', 'SP','Ribeirão Preto'),
(nextval('missao_seq'),'Nossa Senhora dos Anjos','R. Valter Poloni, 190', 'SP','Ribeirão Preto'),
(nextval('missao_seq'),'São Matheus','R. Campinas, 3250', 'SP','Ribeirão Preto');