CREATE TABLE IF NOT EXISTS tb_boleto (
  codigo bigint(30) NOT NULL AUTO_INCREMENT,
  marca varchar(30) NOT NULL,
  modelo varchar(30) NOT NULL,
  valor double NOT NULL,
  data_vencimento datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (codigo)
) ENGINE=InnoDB;

INSERT INTO tb_boleto(marca,modelo,valor) VALUES ('vw', 'jetta', '7000');
INSERT INTO tb_boleto(marca,modelo,valor) VALUES ('vw', 'Fsuca', '4000');
INSERT INTO tb_boleto(marca,modelo,valor) VALUES ('vw', 'Gol', '5000');
INSERT INTO tb_boleto(marca,modelo,valor) VALUES ('vw', 'Polo', '5000');
INSERT INTO tb_boleto(marca,modelo,valor) VALUES ('BWM', 'M5', '7000');
INSERT INTO tb_boleto(marca,modelo,valor) VALUES ('BWM', 'M4', '7000');
INSERT INTO tb_boleto(marca,modelo,valor) VALUES ('BWM', 'M3', '7000');
INSERT INTO tb_boleto(marca,modelo,valor) VALUES ('vw', 'Parati', '7000');
INSERT INTO tb_boleto(marca,modelo,valor) VALUES ('vw', 'Vitus', '7000');