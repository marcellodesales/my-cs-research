# MySQL dump 8.13
#
# Host: localhost    Database: classA
#--------------------------------------------------------
# Server version	3.23.36-log

#
# Table structure for table 't_anunciante'
#

CREATE TABLE t_anunciante (
  cod_anunciante varchar(15) binary NOT NULL default '',
  nome_anunciante varchar(20) NOT NULL default '',
  cod_profissao tinyint(5) NOT NULL default '0',
  cod_faixa_etaria tinyint(2) NOT NULL default '0',
  sexo_anunciante char(1) NOT NULL default '',
  fone_anunciante varchar(15) NOT NULL default '',
  celular_anunciante varchar(15) NOT NULL default '',
  email_anunciante varchar(70) NOT NULL default '',
  icq_anunciante varchar(11) default NULL,
  sobrenome_anunciante varchar(30) NOT NULL default '0',
  PRIMARY KEY  (cod_anunciante),
  UNIQUE KEY cod_anunciante (cod_anunciante)
) TYPE=MyISAM;

#
# Dumping data for table 't_anunciante'
#

INSERT INTO t_anunciante VALUES ('AE5QP5fM5UeU50o','Marcello',10,4,'M','82-326-2884','82-9997-1415','marcellojunior@hotmail.com','60853653','Junior');
INSERT INTO t_anunciante VALUES ('AEw1z7iKpnaqe39','Plinio',10,4,'M','82-327-7009','82-9973-0026','plinio7@bol.com.br','','Silva');

#
# Table structure for table 't_anuncio'
#

CREATE TABLE t_anuncio (
  cod_anuncio varchar(15) binary NOT NULL default '',
  cod_anunciante varchar(15) binary NOT NULL default '',
  cod_tipo_anuncio tinyint(2) NOT NULL default '0',
  cod_subcategoria_anuncio tinyint(3) NOT NULL default '0',
  data_ini_anuncio date NOT NULL default '0000-00-00',
  data_end_anuncio date NOT NULL default '0000-00-00',
  desc_anuncio text NOT NULL,
  imagem_anuncio char(3) default NULL,
  periodo_anuncio tinyint(2) NOT NULL default '0',
  preco_anuncio double(7,2) default NULL,
  cod_categoria_anuncio tinyint(2) default NULL,
  titulo_anuncio varchar(50) NOT NULL default '',
  ip_anunciante_anuncio varchar(15) NOT NULL default '',
  quando_anuncio int(12) NOT NULL default '0',
  PRIMARY KEY  (cod_anuncio),
  UNIQUE KEY cod_anuncio (cod_anuncio)
) TYPE=MyISAM;

#
# Dumping data for table 't_anuncio'
#

INSERT INTO t_anuncio VALUES ('AOpOIjuaCiXhAxv','AEw1z7iKpnaqe39',2,0,'2001-10-13','2001-10-23','dkjvfvjfv<BR>lgrkgv<BR>rknfjrvrnvknrjv','',10,100.00,5,'cao de guarda','200.241.146.213',1003012227);
INSERT INTO t_anuncio VALUES ('AOoWLJoRFgEYVTl','AEw1z7iKpnaqe39',6,0,'2001-10-13','2001-10-14','ksjdhfh<BR>hfuhf<BR>jfhrfhrjvhj','',1,100.00,24,'tem de tudo!!!!','200.241.146.213',1003012275);
INSERT INTO t_anuncio VALUES ('AOBHwvjCAoqSlE7','AEw1z7iKpnaqe39',4,0,'2001-10-13','2001-10-14','eff','gif',1,100.00,24,'nada disso!!!','200.241.146.213',1003012313);
INSERT INTO t_anuncio VALUES ('AOwM20TVX8KMO79','AEw1z7iKpnaqe39',5,0,'2001-10-13','2001-10-14','nada','',1,100.00,18,'festa do bode','200.241.146.213',1003012350);
INSERT INTO t_anuncio VALUES ('AOYRoRoISHaPEsM','AEw1z7iKpnaqe39',2,0,'2001-10-13','2001-10-14','BNGH','',1,100.00,24,'QUERO MAIS!!!','200.241.146.213',1003012430);

#
# Table structure for table 't_associado'
#

CREATE TABLE t_associado (
  cod_associado varchar(15) NOT NULL default '',
  nome_associado varchar(30) NOT NULL default '',
  texto_associado text NOT NULL,
  endereco_associado varchar(150) NOT NULL default '',
  fones_associado varchar(20) NOT NULL default '',
  email_associado varchar(30) NOT NULL default '',
  url_associado varchar(100) NOT NULL default '',
  imagem_associado char(3) NOT NULL default '',
  banner_associado char(3) NOT NULL default '',
  PRIMARY KEY  (cod_associado),
  UNIQUE KEY cod_associado (cod_associado),
  KEY cod_associado_2 (cod_associado)
) TYPE=MyISAM;

#
# Dumping data for table 't_associado'
#

INSERT INTO t_associado VALUES ('AStUwc9oZJy6Hrk','AeroTurismo','Testando cadastro de sócio.','Rua dos aviões quebrados, 43. Cabora. SP','1203232','aeroturismo@classi-a.com.br','http://www.aerotour.com.br','jpg','gif');
INSERT INTO t_associado VALUES ('ASlPsqeymO0kljp','A Farinhada','De teor político, mas sem sair do discurso panfletário, o espetáculo começa com o estupro de uma empregada pelo senhor da propriedade (fato comum nas lavouras do interior). Mas este fato desperta a consciência dos trabalhadores, que começam a questionar os direitos de seus patrões coronéis.\r\n	O cheiro de arruda e flores, o cenário artístico rústico, a música de forró, e o pó de farinha no ar. Atores em cena, começa um espetáculo de poesia e musicalidade, no \"dialeto nordestino\", mostrando a eterna relação de subserviência e poder entre senhores e empregados das fazendas de interior. Com estes elementos, o espectador de \"A Farinhada\" é levado a uma viagem imaginária a qualquer moínho de mandioca do Nordeste. O espetáculo apresentado pela Associação Teatral Joana Gajuru, da Universidade Federal de Alagoas, agradou vários públicos.','Rua Lafaiata da Gororoba de Farinha. 203. Centro. Maceió-AL','2541546; 2215411','farinhada@classi-a.com.br','http://www.farinhada.com.br','jpg','gif');
INSERT INTO t_associado VALUES ('ASmXmkmS3JgNsMs','Colegio Batista','Colégio Batista Alagoano.','Rua Solimões, 45. Centro','2320123','batista@classi-a.com.br','http://www.colegiobatista.com.br','jpg','gif');
INSERT INTO t_associado VALUES ('ASqIk9GRFZnHvkD','Fisk','Sartre usa como exemplo um objeto fabricado qualquer, como um livro ou um corta-papel: neles a essência precede a existência; da mesma forma, se imaginarmos um Deus criador, o identificamos a um artífice superior que cria o homem segundo um modelo, tal qual o artífice fabrica um corta-papel. Daí deriva a noção de que o homem tem uma natureza humana, encontrada igualmente em todos os homens. Portanto, nessa concepção, a essência do homem precederia a existência. Não é essa, no entanto, a posição de Sartre ao afirmar que a existência precede a essência: \"Significa que o homem primeiramente existe, se descobre, surge no mundo; e que só depois se define. O homem, tal como o concebe o existencialista, se não é definível, é porque primeiramente não é nada. Só depois será alguma coisa e tal como a si próprio se fizer. Assim, não há natureza humana, visto que não há Deus para a conceber. O homem é, não apenas como ele se concebe, mas como ele quer que seja, como ele se concebe depois da existência, como ele se deseja após este impulso para a existência; o homem não é mais que o que ele faz. Tal é o primeiro princípio do existencialismo\".','Rua Iturica, 89. Centro.','2321123','fisk@classi-a.com.br','http://www.fisk.com.br','jpg','gif');
INSERT INTO t_associado VALUES ('AStKrPdS2ciPQ9B','EcoPark Aquático','De teor político, mas sem sair do discurso panfletário, o espetáculo começa com o estupro de uma empregada pelo senhor da propriedade (fato comum nas lavouras do interior). Mas este fato desperta a consciência dos trabalhadores, que começam a questionar os direitos de seus patrões coronéis.\r\n	O cheiro de arruda e flores, o cenário artístico rústico, a música de forró, e o pó de farinha no ar. Atores em cena, começa um espetáculo de poesia e musicalidade, no \"dialeto nordestino\", mostrando a eterna relação de subserviência e poder entre senhores e empregados das fazendas de interior.','Avenida Joaquim Nabuco Lima, 1204. Jacarecica. Maceió-AL','3265885','eco@classi-a.com.br','http://www.ecopark.com.br','jpg','gif');

#
# Table structure for table 't_bairro'
#

CREATE TABLE t_bairro (
  cod_bairro tinyint(3) NOT NULL auto_increment,
  desc_bairro varchar(30) NOT NULL default '',
  PRIMARY KEY  (cod_bairro)
) TYPE=MyISAM;

#
# Dumping data for table 't_bairro'
#

INSERT INTO t_bairro VALUES (1,'Farol');
INSERT INTO t_bairro VALUES (2,'Tabuleiro dos Martins');
INSERT INTO t_bairro VALUES (3,'Ponta Verde');
INSERT INTO t_bairro VALUES (4,'Jatiúca');
INSERT INTO t_bairro VALUES (5,'Benedito Bentes');
INSERT INTO t_bairro VALUES (6,'Poço');
INSERT INTO t_bairro VALUES (7,'Trapiche');
INSERT INTO t_bairro VALUES (8,'Pontal');
INSERT INTO t_bairro VALUES (9,'Barro Duro');
INSERT INTO t_bairro VALUES (10,'Serraria');
INSERT INTO t_bairro VALUES (11,'Jacintinho');
INSERT INTO t_bairro VALUES (12,'Stela Mares');
INSERT INTO t_bairro VALUES (13,'Cruz das Almas');
INSERT INTO t_bairro VALUES (14,'Pajuçara');
INSERT INTO t_bairro VALUES (15,'Outro');
INSERT INTO t_bairro VALUES (16,'Centro');
INSERT INTO t_bairro VALUES (17,'Bebedouro');
INSERT INTO t_bairro VALUES (18,'Jaraguá');
INSERT INTO t_bairro VALUES (19,'Aldebaran');
INSERT INTO t_bairro VALUES (20,'Murilópolis');

#
# Table structure for table 't_categoria_anuncio'
#

CREATE TABLE t_categoria_anuncio (
  cod_categoria_anuncio tinyint(2) NOT NULL auto_increment,
  desc_categoria_anuncio varchar(50) NOT NULL default '',
  PRIMARY KEY  (cod_categoria_anuncio)
) TYPE=MyISAM;

#
# Dumping data for table 't_categoria_anuncio'
#

INSERT INTO t_categoria_anuncio VALUES (1,'Veículos');
INSERT INTO t_categoria_anuncio VALUES (2,'Imóveis');
INSERT INTO t_categoria_anuncio VALUES (3,'Marmitas');
INSERT INTO t_categoria_anuncio VALUES (4,'Telefones');
INSERT INTO t_categoria_anuncio VALUES (5,'Animais');
INSERT INTO t_categoria_anuncio VALUES (6,'Avisos');
INSERT INTO t_categoria_anuncio VALUES (7,'Outros');
INSERT INTO t_categoria_anuncio VALUES (8,'Empregos');
INSERT INTO t_categoria_anuncio VALUES (9,'Pensionatos');
INSERT INTO t_categoria_anuncio VALUES (10,'Cursos');
INSERT INTO t_categoria_anuncio VALUES (11,'Títulos');
INSERT INTO t_categoria_anuncio VALUES (12,'Utilidades Públicas');
INSERT INTO t_categoria_anuncio VALUES (13,'Festas e Eventos');
INSERT INTO t_categoria_anuncio VALUES (14,'Consórcios');
INSERT INTO t_categoria_anuncio VALUES (15,'Serviços');
INSERT INTO t_categoria_anuncio VALUES (16,'Comidas');
INSERT INTO t_categoria_anuncio VALUES (17,'Máquinas e Motores');
INSERT INTO t_categoria_anuncio VALUES (18,'Dicas e Lazer');
INSERT INTO t_categoria_anuncio VALUES (19,'Informática');
INSERT INTO t_categoria_anuncio VALUES (21,'Artes');
INSERT INTO t_categoria_anuncio VALUES (22,'Eletrodomésticos');
INSERT INTO t_categoria_anuncio VALUES (23,'Livros');
INSERT INTO t_categoria_anuncio VALUES (24,'CDs');
INSERT INTO t_categoria_anuncio VALUES (25,'Inst. Musicais');
INSERT INTO t_categoria_anuncio VALUES (26,'Jóias');
INSERT INTO t_categoria_anuncio VALUES (27,'Móveis e Decorações');

#
# Table structure for table 't_chegada_usuario'
#

CREATE TABLE t_chegada_usuario (
  cod_chegada tinyint(2) NOT NULL auto_increment,
  desc_chegada varchar(50) NOT NULL default '',
  PRIMARY KEY  (cod_chegada)
) TYPE=MyISAM;

#
# Dumping data for table 't_chegada_usuario'
#

INSERT INTO t_chegada_usuario VALUES (1,'Publicidade na Internet');
INSERT INTO t_chegada_usuario VALUES (2,'Sites de busca');
INSERT INTO t_chegada_usuario VALUES (3,'Jornal ou revista');
INSERT INTO t_chegada_usuario VALUES (4,'Rádio ou TV');
INSERT INTO t_chegada_usuario VALUES (5,'Evento ou Outdoor');
INSERT INTO t_chegada_usuario VALUES (6,'Indicação de um amigo');
INSERT INTO t_chegada_usuario VALUES (7,'Outro');

#
# Table structure for table 't_endereco'
#

CREATE TABLE t_endereco (
  cod_anunciante varchar(15) binary NOT NULL default '0',
  cod_estado tinyint(2) NOT NULL default '0',
  cod_tipo_endereco tinyint(1) NOT NULL default '0',
  cod_bairro tinyint(3) NOT NULL default '0',
  numero varchar(6) NOT NULL default '0',
  logradouro varchar(70) NOT NULL default '',
  localidade varchar(25) NOT NULL default '',
  cep varchar(9) NOT NULL default '',
  cod_pais tinyint(3) NOT NULL default '0',
  PRIMARY KEY  (cod_anunciante),
  UNIQUE KEY cod_anunciante (cod_anunciante)
) TYPE=MyISAM;

#
# Dumping data for table 't_endereco'
#

INSERT INTO t_endereco VALUES ('AE5QP5fM5UeU50o',2,1,1,'58','Gonçalves Dias','Maceió','57051330',1);
INSERT INTO t_endereco VALUES ('AEw1z7iKpnaqe39',2,1,4,'sdsd','dssd','Mac','57000000',1);

#
# Table structure for table 't_estado'
#

CREATE TABLE t_estado (
  cod_estado tinyint(2) NOT NULL auto_increment,
  uf_estado char(2) NOT NULL default '',
  desc_estado varchar(30) NOT NULL default '',
  PRIMARY KEY  (cod_estado)
) TYPE=MyISAM;

#
# Dumping data for table 't_estado'
#

INSERT INTO t_estado VALUES (1,'AC','Acre');
INSERT INTO t_estado VALUES (2,'AL','Alagoas');
INSERT INTO t_estado VALUES (3,'AP','Amapá');
INSERT INTO t_estado VALUES (4,'AM','Amazonas');
INSERT INTO t_estado VALUES (5,'BA','Bahia');
INSERT INTO t_estado VALUES (6,'CE','Ceará');
INSERT INTO t_estado VALUES (7,'DF','Distrito Federal');
INSERT INTO t_estado VALUES (8,'ES','Espírito Santo');
INSERT INTO t_estado VALUES (9,'GO','Goiás');
INSERT INTO t_estado VALUES (10,'MA','Maranhão');
INSERT INTO t_estado VALUES (11,'MT','Mato Grosso');
INSERT INTO t_estado VALUES (12,'MS','Mato Grosso do Sul');
INSERT INTO t_estado VALUES (13,'MG','Minas Gerais');
INSERT INTO t_estado VALUES (14,'PA','Pará');
INSERT INTO t_estado VALUES (15,'PB','Paraíba');
INSERT INTO t_estado VALUES (16,'PR','Paraná');
INSERT INTO t_estado VALUES (17,'PE','Pernambuco');
INSERT INTO t_estado VALUES (18,'PI','Piauí');
INSERT INTO t_estado VALUES (19,'RJ','Rio de Janeiro');
INSERT INTO t_estado VALUES (20,'RN','Rio Grande do Norte');
INSERT INTO t_estado VALUES (21,'RS','Rio Grande do Sul');
INSERT INTO t_estado VALUES (22,'RO','Rondônia');
INSERT INTO t_estado VALUES (23,'RR','Roraima');
INSERT INTO t_estado VALUES (24,'SC','Santa Catarina');
INSERT INTO t_estado VALUES (25,'SP','São Paulo');
INSERT INTO t_estado VALUES (26,'SE','Sergipe');
INSERT INTO t_estado VALUES (27,'TO','Tocantins');

#
# Table structure for table 't_faixa_etaria'
#

CREATE TABLE t_faixa_etaria (
  cod_faixa_etaria tinyint(2) NOT NULL default '0',
  desc_faixa_etaria varchar(11) NOT NULL default '',
  PRIMARY KEY  (cod_faixa_etaria)
) TYPE=MyISAM;

#
# Dumping data for table 't_faixa_etaria'
#

INSERT INTO t_faixa_etaria VALUES (1,'Até 10 anos');
INSERT INTO t_faixa_etaria VALUES (2,'10 - 15');
INSERT INTO t_faixa_etaria VALUES (3,'15 - 20');
INSERT INTO t_faixa_etaria VALUES (4,'20 - 30');
INSERT INTO t_faixa_etaria VALUES (5,'30 - 40');
INSERT INTO t_faixa_etaria VALUES (6,'40 - 50');
INSERT INTO t_faixa_etaria VALUES (7,'Mais de 50');

#
# Table structure for table 't_imovel'
#

CREATE TABLE t_imovel (
  cod_anuncio varchar(15) binary NOT NULL default '0',
  cod_bairro tinyint(3) NOT NULL default '0',
  PRIMARY KEY  (cod_anuncio)
) TYPE=MyISAM;

#
# Dumping data for table 't_imovel'
#


#
# Table structure for table 't_marca_veiculo'
#

CREATE TABLE t_marca_veiculo (
  cod_marca_veiculo tinyint(2) NOT NULL auto_increment,
  desc_marca_veiculo varchar(20) NOT NULL default '',
  PRIMARY KEY  (cod_marca_veiculo)
) TYPE=MyISAM;

#
# Dumping data for table 't_marca_veiculo'
#

INSERT INTO t_marca_veiculo VALUES (1,'Chevrolet');
INSERT INTO t_marca_veiculo VALUES (2,'Fiat');
INSERT INTO t_marca_veiculo VALUES (3,'Ford');
INSERT INTO t_marca_veiculo VALUES (4,'Volkswagen');
INSERT INTO t_marca_veiculo VALUES (5,'Outro');
INSERT INTO t_marca_veiculo VALUES (6,'Importado');

#
# Table structure for table 't_pais'
#

CREATE TABLE t_pais (
  cod_pais smallint(3) NOT NULL default '0',
  desc_pais varchar(30) NOT NULL default '',
  PRIMARY KEY  (cod_pais)
) TYPE=MyISAM;

#
# Dumping data for table 't_pais'
#

INSERT INTO t_pais VALUES (1,'Brasil');
INSERT INTO t_pais VALUES (2,'Exterior');

#
# Table structure for table 't_profissao'
#

CREATE TABLE t_profissao (
  cod_profissao tinyint(2) NOT NULL auto_increment,
  desc_profissao varchar(50) NOT NULL default '',
  PRIMARY KEY  (cod_profissao)
) TYPE=MyISAM;

#
# Dumping data for table 't_profissao'
#

INSERT INTO t_profissao VALUES (1,'executivo/gerente');
INSERT INTO t_profissao VALUES (2,'profissional liberal (médico, advogado, etc.)');
INSERT INTO t_profissao VALUES (3,'acadêmico/professor');
INSERT INTO t_profissao VALUES (4,'informática/engenheiro');
INSERT INTO t_profissao VALUES (5,'técnico/engenheiro');
INSERT INTO t_profissao VALUES (6,'serviço/atendimento ao cliente');
INSERT INTO t_profissao VALUES (7,'eclesiástico/administrativo');
INSERT INTO t_profissao VALUES (8,'vendas/marketing');
INSERT INTO t_profissao VALUES (9,'comerciante/artesão');
INSERT INTO t_profissao VALUES (10,'estudante/universitário');
INSERT INTO t_profissao VALUES (11,'estudante/secundarista');
INSERT INTO t_profissao VALUES (12,'autônomo/empresário');
INSERT INTO t_profissao VALUES (13,'desempregado, à procura de trabalho');
INSERT INTO t_profissao VALUES (14,'aposentado');
INSERT INTO t_profissao VALUES (15,'outra');

#
# Table structure for table 't_subcategoria_anuncio'
#

CREATE TABLE t_subcategoria_anuncio (
  cod_subcategoria_anuncio tinyint(3) NOT NULL auto_increment,
  desc_subcategoria_anuncio varchar(50) NOT NULL default '',
  cod_categoria_anuncio tinyint(3) NOT NULL default '0',
  PRIMARY KEY  (cod_subcategoria_anuncio)
) TYPE=MyISAM;

#
# Dumping data for table 't_subcategoria_anuncio'
#

INSERT INTO t_subcategoria_anuncio VALUES (1,'Carro',1);
INSERT INTO t_subcategoria_anuncio VALUES (2,'Caminhão',1);
INSERT INTO t_subcategoria_anuncio VALUES (3,'Trator',1);
INSERT INTO t_subcategoria_anuncio VALUES (4,'Moto',1);
INSERT INTO t_subcategoria_anuncio VALUES (5,'Especiais',1);
INSERT INTO t_subcategoria_anuncio VALUES (6,'Utilitários',1);
INSERT INTO t_subcategoria_anuncio VALUES (7,'Náutica',1);
INSERT INTO t_subcategoria_anuncio VALUES (9,'Casa',2);
INSERT INTO t_subcategoria_anuncio VALUES (10,'Apartamento',2);
INSERT INTO t_subcategoria_anuncio VALUES (11,'Sítio',2);
INSERT INTO t_subcategoria_anuncio VALUES (12,'Terrenos',2);
INSERT INTO t_subcategoria_anuncio VALUES (13,'Ponto Comercial',2);
INSERT INTO t_subcategoria_anuncio VALUES (14,'Residencial',4);
INSERT INTO t_subcategoria_anuncio VALUES (15,'Celular',4);
INSERT INTO t_subcategoria_anuncio VALUES (16,'Antiguidade',21);
INSERT INTO t_subcategoria_anuncio VALUES (27,'Quadros',21);
INSERT INTO t_subcategoria_anuncio VALUES (29,'Domínios',19);
INSERT INTO t_subcategoria_anuncio VALUES (28,'CPU´s',19);
INSERT INTO t_subcategoria_anuncio VALUES (20,'Acessórios',19);
INSERT INTO t_subcategoria_anuncio VALUES (21,'Computador Desktop',19);
INSERT INTO t_subcategoria_anuncio VALUES (22,'Computador Portátil',19);
INSERT INTO t_subcategoria_anuncio VALUES (23,'Impressoras',19);
INSERT INTO t_subcategoria_anuncio VALUES (24,'Serviços',19);
INSERT INTO t_subcategoria_anuncio VALUES (25,'Software',19);
INSERT INTO t_subcategoria_anuncio VALUES (26,'Fazenda',2);
INSERT INTO t_subcategoria_anuncio VALUES (30,'Driver',19);
INSERT INTO t_subcategoria_anuncio VALUES (31,'Hardware',19);
INSERT INTO t_subcategoria_anuncio VALUES (32,'Memória',19);
INSERT INTO t_subcategoria_anuncio VALUES (33,'Monitor',19);
INSERT INTO t_subcategoria_anuncio VALUES (34,'MP3',19);
INSERT INTO t_subcategoria_anuncio VALUES (35,'Notebook',19);
INSERT INTO t_subcategoria_anuncio VALUES (36,'Placas',19);
INSERT INTO t_subcategoria_anuncio VALUES (37,'Scanner',19);
INSERT INTO t_subcategoria_anuncio VALUES (38,'Teclado',19);
INSERT INTO t_subcategoria_anuncio VALUES (39,'Outros',19);
INSERT INTO t_subcategoria_anuncio VALUES (40,'Sem Cateroria',7);

#
# Table structure for table 't_tipo_anuncio'
#

CREATE TABLE t_tipo_anuncio (
  cod_tipo_anuncio tinyint(1) NOT NULL auto_increment,
  desc_tipo_anuncio varchar(15) NOT NULL default '',
  PRIMARY KEY  (cod_tipo_anuncio)
) TYPE=MyISAM;

#
# Dumping data for table 't_tipo_anuncio'
#

INSERT INTO t_tipo_anuncio VALUES (1,'Venda');
INSERT INTO t_tipo_anuncio VALUES (2,'Aluguel');
INSERT INTO t_tipo_anuncio VALUES (3,'Venda ou Troca');
INSERT INTO t_tipo_anuncio VALUES (4,'Troca');
INSERT INTO t_tipo_anuncio VALUES (5,'Outros');
INSERT INTO t_tipo_anuncio VALUES (6,'Compra');

#
# Table structure for table 't_tipo_endereco'
#

CREATE TABLE t_tipo_endereco (
  cod_tipo_endereco tinyint(1) NOT NULL auto_increment,
  desc_tipo_endereco varchar(10) NOT NULL default '0',
  PRIMARY KEY  (cod_tipo_endereco)
) TYPE=MyISAM;

#
# Dumping data for table 't_tipo_endereco'
#

INSERT INTO t_tipo_endereco VALUES (1,'Rua');
INSERT INTO t_tipo_endereco VALUES (2,'Avenida');
INSERT INTO t_tipo_endereco VALUES (3,'Travessa');
INSERT INTO t_tipo_endereco VALUES (4,'Praça');
INSERT INTO t_tipo_endereco VALUES (5,'Alameda');
INSERT INTO t_tipo_endereco VALUES (6,'Outro');

#
# Table structure for table 't_tipo_usuario'
#

CREATE TABLE t_tipo_usuario (
  cod_tipo_usuario char(1) NOT NULL default '0',
  desc_tipo_usuario varchar(10) NOT NULL default '',
  PRIMARY KEY  (cod_tipo_usuario)
) TYPE=MyISAM;

#
# Dumping data for table 't_tipo_usuario'
#

INSERT INTO t_tipo_usuario VALUES ('S','Special');
INSERT INTO t_tipo_usuario VALUES ('W','Web');
INSERT INTO t_tipo_usuario VALUES ('A','Admin');

#
# Table structure for table 't_usuario'
#

CREATE TABLE t_usuario (
  cod_anunciante varchar(15) binary NOT NULL default '0',
  login varchar(16) binary NOT NULL default '',
  password varchar(8) binary NOT NULL default '',
  cod_tipo_usuario char(1) NOT NULL default '0',
  cod_chegada tinyint(2) NOT NULL default '0',
  quando_chegou int(12) NOT NULL default '0',
  ultimo_acesso int(12) NOT NULL default '0',
  ultimo_ip varchar(15) NOT NULL default '0',
  PRIMARY KEY  (cod_anunciante),
  UNIQUE KEY cod_anunciante (cod_anunciante)
) TYPE=MyISAM;

#
# Dumping data for table 't_usuario'
#

INSERT INTO t_usuario VALUES ('AE5QP5fM5UeU50o','marcellojunior','51527981','A',6,1002825586,1002914161,'200.227.200.96');
INSERT INTO t_usuario VALUES ('AEw1z7iKpnaqe39','plinio','12345','A',6,1002826231,1003144178,'200.199.51.95');

#
# Table structure for table 't_veiculo'
#

CREATE TABLE t_veiculo (
  cod_anuncio varchar(15) binary NOT NULL default '0',
  cod_marca_veiculo tinyint(2) NOT NULL default '0',
  PRIMARY KEY  (cod_anuncio)
) TYPE=MyISAM;

#
# Dumping data for table 't_veiculo'
#


