CREATE TABLE tb_automovel (
	codigo BIGINT(20) NOT NULL AUTO_INCREMENT,
	marca VARCHAR(100) NOT NULL COLLATE 'latin1_swedish_ci',
	modelo VARCHAR(100) NOT NULL COLLATE 'latin1_swedish_ci',
	valor DOUBLE(22,0) NOT NULL DEFAULT '0',
	data_cadastro TIMESTAMP NOT NULL DEFAULT sysdate(),
	PRIMARY KEY (codigo) USING BTREE
)
COMMENT='Cadastra o automóvel disponível para compra'
ENGINE=INNODB;

INSERT INTO  automoveisapi.tb_automovel( marca,modelo,valor) VALUES ('VW', 'JETTA', '65000');
INSERT INTO  automoveisapi.tb_automovel( marca,modelo,valor) VALUES ('VW', 'POLO', '45000');
INSERT INTO  automoveisapi.tb_automovel( marca,modelo,valor) VALUES ('VW', 'VIRTUS', '75000');
INSERT INTO  automoveisapi.tb_automovel( marca,modelo,valor) VALUES ('VW', 'GOL G5', '35000');
INSERT INTO  automoveisapi.tb_automovel( marca,modelo,valor) VALUES ('BMW', '320I', '95000');
INSERT INTO  automoveisapi.tb_automovel( marca,modelo,valor) VALUES ('BMW', '120I', '45000');
INSERT INTO  automoveisapi.tb_automovel( marca,modelo,valor) VALUES ('HONDA', 'CIVIC', '65000');
INSERT INTO  automoveisapi.tb_automovel( marca,modelo,valor) VALUES ('HONDA', 'FIAT', '65000');
