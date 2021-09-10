CREATE DATABASE SmallBusinessSales;


CREATE USER 'admin'@'%' IDENTIFIED BY '123';

GRANT ALL ON *.* TO 'admin'@'%' WITH GRANT OPTION;


flush privileges;


USE SmallBusinessSales;

/***** TABELA CLIENTES *****/
CREATE TABLE tb_clientes (
  id int auto_increment primary key,
  nome varchar(100),
  rg varchar (30),
  cpf varchar (20),
  email varchar(200),
  telefone varchar(30),
  celular varchar(30),
  cep varchar(100),
  endereco varchar (255),
  numero int,
  complemento varchar (200),
  bairro varchar (100),
  cidade varchar (100),
  estado varchar (2)
);
/*****************/

/***** TABELA FORNECEDORES *****/
CREATE TABLE tb_fornecedores (
  id int auto_increment primary key,
  nome varchar(100),
  cnpj varchar (100),
  email varchar(200),
  telefone varchar(30),
  celular varchar(30),
  cep varchar(100),
  endereco varchar (255),
  numero int,
  complemento varchar (200),
  bairro varchar (100),
  cidade varchar (100),
  estado varchar (2)
);
/*****************/

/***** TABELA FUNCIONARIOS *****/
CREATE TABLE tb_funcionarios (
  id int auto_increment primary key,
  nome varchar(100),
  rg varchar (30),
  cpf varchar (20),
  email varchar(200),
  senha varchar(10),
  cargo varchar(100),
  nivel_acesso varchar(50),
  telefone varchar(30),
  celular varchar(30),
  cep varchar(100),
  endereco varchar (255),
  numero int,
  complemento varchar (200),
  bairro varchar (100),
  cidade varchar (100),
  estado varchar (2)
);
/*****************/


/***** TABELA PRODUTOS *****/
CREATE TABLE tb_produtos (
  id int auto_increment primary key,
  status varchar(12),
  data_cadastro datetime,
  produto varchar(32),
  qtd_estoque int,
  descricao varchar(1000),
  estoque_minimo int,
  estoque_maximo int,
  preco_compra decimal(12,2),
  preco_venda decimal(12,2),
  fator decimal(12,2),
  cod_barras varchar(13),  
  imagem longblob,
  for_id int,
  FOREIGN KEY (for_id) REFERENCES tb_fornecedores(id)
);
/*****************/

/***** TABELA VENDAS *****/
CREATE TABLE tb_vendas (
  id int auto_increment primary key,
  cliente_id int,
  data_venda datetime,
  total_venda decimal (10,2),
  observacoes text,

  FOREIGN KEY (cliente_id) REFERENCES tb_clientes(id)
);
/*****************/

/***** TABELA ITENS_VENDAS *****/
CREATE TABLE tb_itensvendas (
  id int auto_increment primary key,
  venda_id int,
  produto_id int,
  qtd int,
  subtotal decimal (10,2),

  FOREIGN KEY (venda_id) REFERENCES tb_vendas(id),
  FOREIGN KEY (produto_id) REFERENCES tb_produtos(id)
);
/*****************/

insert into tb_funcionarios (nome, rg, cpf, email, senha, cargo, nivel_acesso, telefone, 
							 celular, cep, endereco, numero, complemento, bairro, cidade, estado) 
					  value ('Admin','00.000.000-0','000.000.000-00','admin','123','Diretor','Administrador', '(00)0000-0000',
                             '(00)00000-0000','00000-000','XY',00,'YZ','XX','YY', 'SP');

insert into tb_clientes (nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado) 
			      value ('Sem CPF','00.000.000-0','000.000.000-00', 'email', '(00)0000-0000', '(00)00000-0000','00000-000','XY',00,'YZ','XX','YY', 'SP');

