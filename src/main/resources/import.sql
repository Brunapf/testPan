INSERT INTO product VALUES (1, 'CARTAO', 0, 0);
INSERT INTO product VALUES (2, 'EMPRESTIMO', 0, 1);
INSERT INTO product VALUES (3, 'CAPTALIZACAO', 0, 2);
INSERT INTO product VALUES (4, 'INVESTIMENTOS', 0, 3);

INSERT INTO address (id,name,number,cep,state,country) VALUES (1,'Divinopolis', 7, '38400432', 'MG', 'BR');

INSERT INTO client (client_id,name,cpf,address_id,status) VALUES (1, 'Bruna', '12345678915', 1,0)