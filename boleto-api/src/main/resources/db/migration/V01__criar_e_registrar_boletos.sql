DROP TABLE IF EXISTS tb_boleto;

CREATE TABLE IF NOT EXISTS tb_boleto (
  codigo bigint(30) NOT NULL AUTO_INCREMENT,
  marca varchar(30) NOT NULL,
  modelo varchar(30) NOT NULL,
  valor  DECIMAL(20,0) NOT NULL DEFAULT '0',
  data_vencimento datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (codigo)
) ENGINE=InnoDB;

INSERT INTO tb_boleto(marca,modelo,valor) VALUES ('vw', 'jetta', '5000');
INSERT INTO tb_boleto(marca,modelo,valor) VALUES ('vw', 'Fsuca', '6000');
INSERT INTO tb_boleto(marca,modelo,valor) VALUES ('vw', 'Gol', '7000');
INSERT INTO tb_boleto(marca,modelo,valor) VALUES ('vw', 'Polo', '8000');
INSERT INTO tb_boleto(marca,modelo,valor) VALUES ('BWM', 'M5', '9000');
INSERT INTO tb_boleto(marca,modelo,valor) VALUES ('BWM', 'M4', '4000');
INSERT INTO tb_boleto(marca,modelo,valor) VALUES ('BWM', 'M3', '3000');
INSERT INTO tb_boleto(marca,modelo,valor) VALUES ('vw', 'Parati', '27000');
INSERT INTO tb_boleto(marca,modelo,valor) VALUES ('vw', 'Vitus', '1000');