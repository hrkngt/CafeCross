USE cafecross_main;

DROP TABLE IF EXISTS products;
CREATE TABLE products (
  id           INT(5) ZEROFILL NOT NULL AUTO_INCREMENT,
  product_name VARCHAR(255)    NOT NULL,
  description  VARCHAR(255)    NOT NULL,
  price        INT(4)          NOT NULL,
  image        VARCHAR(255),
  PRIMARY KEY (Id)
);