# MySQL dump 7.1
#
# Host: localhost    Database: classA
#--------------------------------------------------------
# Server version	3.22.32-log

#
# Table structure for table 't_anunciante'
#
CREATE TABLE t_anunciante (
  cod_anunciante varchar(15) binary DEFAULT '' NOT NULL,
  nome_anunciante varchar(20) DEFAULT '' NOT NULL,
  cod_profissao tinyint(5) DEFAULT '0' NOT NULL,
  cod_faixa_etaria tinyint(2) DEFAULT '0' NOT NULL,
  sexo_anunciante char(1) DEFAULT '' NOT NULL,
  fone_anunciante varchar(15) DEFAULT '' NOT NULL,
  celular_anunciante varchar(15) DEFAULT '' NOT NULL,
  email_anunciante varchar(70) DEFAULT '' NOT NULL,
  icq_anunciante varchar(11),
  sobrenome_anunciante varchar(30) DEFAULT '0' NOT NULL,
  PRIMARY KEY (cod_anunciante),
  UNIQUE cod_anunciante (cod_anunciante)
);

#
# Dumping data for table 't_anunciante'
#

INSERT INTO t_anunciante VALUES ('AEY6aeimquyHMQU','Marcello',10,4,'M','82-326-2884','82-997-1415','marcellojunior@classi-a.com.br','60853653','Alves de Sales Junior');
INSERT INTO t_anunciante VALUES ('AEKruPaLeHaD6Ha','Henrique',15,2,'M','','','henriquecarmellino@globo.com','','Carmellino');
INSERT INTO t_anunciante VALUES ('AEItxBFJNRV048c','Leandro',11,3,'M','82-326-2884','82-971-1818','lms@etfal.g12.br','','Melo  de Sales');
INSERT INTO t_anunciante VALUES ('AE6QVY26aeinrvz','Plinio',9,4,'M','','82-973-0026','plinio7@bol.com.br','115329566','Silva Filho');
INSERT INTO t_anunciante VALUES ('AEfntl4VULKBsrq','Alzira',12,6,'F','','9746430','alzira_carmellino@bol.com.br','','Carmellino');
INSERT INTO t_anunciante VALUES ('AEr7cNUT0Z6dkjq','Broad',4,1,'M','82-326-2884','','broad@dirp.com','','Marsdsdsdsdds');
INSERT INTO t_anunciante VALUES ('AEF14hCdGhu5q1u','Classi-A',15,1,'M','','','business@classi-a.com.br','','Web Group Development');
INSERT INTO t_anunciante VALUES ('AEyTWhuXqLI3oR4','fulano',7,2,'M','82-235-4565','82-984-5456','alguem@algumlugar.br','','cicrano');
INSERT INTO t_anunciante VALUES ('AE1fqDQjM7AVoK5','Fernando',8,3,'M','33-3263-2318','','fernandodamiao@uol.com.br','82664279','Silva Dami�o');
INSERT INTO t_anunciante VALUES ('AEWC7P3xKeO2nZl','Olivio',11,3,'M','82-327-7009','82-979-6353','bob-16@bol.com.br','','Candido da silva Neto');
INSERT INTO t_anunciante VALUES ('AE8HCPiTudq1CdO','Jose Fabio S. Silva',6,4,'M','','82-9303-9494','jfabio@mailbr.com.br','','Fabio');
INSERT INTO t_anunciante VALUES ('AEWMHU7sVgJ4xSd','Tiagao',11,3,'M','82-9988797','9988797','tiago27cm@bol.com.br','','27cm');
INSERT INTO t_anunciante VALUES ('AEChYzqh8Zc4NEv','Bruno',4,3,'M','82-221.1981','82-978.3477','brunovida@globo.com','','Oliveira');
INSERT INTO t_anunciante VALUES ('AEjjKl4VMDmdWNE','Jos� Brasil',12,6,'M','82-357-6140','','claudiobrasil@mailbr.com.br','','Brasil');
INSERT INTO t_anunciante VALUES ('AEy4fIj2LmHq2Lu','Silvia',4,4,'F','82-231-0102','82-972-0049','hsilvia@trt19.gov.br','','Carvalho');
INSERT INTO t_anunciante VALUES ('AE1putsjipwvCJQ','Wilson',4,5,'M','82-334-4064','(082) 972-6448','wr.wilson@ig.com.br','','Almeida Batista');
INSERT INTO t_anunciante VALUES ('AElJyhg7YXOFEvm','Marcello',3,1,'M','','','marcello@dialnet.com.br','','Junior');
INSERT INTO t_anunciante VALUES ('AEyqfYPGxwxofe5','Rodrigo',12,3,'M','82-976-8278','82-976-8278','rodrigo@canalmaceio.com.br','','Calheiros');
INSERT INTO t_anunciante VALUES ('AEpZ27AVoRG1u5q','Eletro Silva',15,4,'M','82-221-1981','','elsilva@matrix.com.br','','Eletro Silva');
INSERT INTO t_anunciante VALUES ('AEcxkx0BcN0t4Fg','Igor',11,3,'M','82-327-2613','82-999-4040','igor_vasconcelos@bol.com.br','','Carvalhar Vasconcelos');
INSERT INTO t_anunciante VALUES ('AEu2NTZlqw6cpTn','andrea',7,5,'F','19-3287-7078','19-9601-7401','andrearpeixoto@zipmail.com.br','35144192','peixoto');
INSERT INTO t_anunciante VALUES ('AELdvAOaw0rxL7t','Glauce',3,6,'F','21-275-3768','','glauceb@mattosfilho.com.br','','Brand�o');
INSERT INTO t_anunciante VALUES ('AEKctXj3NhQsWya','Robson',11,4,'M','','82-977-2992','rpeixoto@ofm.com.br','','Peixoto da Rocha');
INSERT INTO t_anunciante VALUES ('AEV7FTeYI42En7J','Luis Gustavo',13,4,'M','82-334-1759','','iamprotheus@zipmail.com.br','','Otaviano Vasconcelos');
INSERT INTO t_anunciante VALUES ('AEiK9nB5zaC6F9D','Davi',1,3,'M','82-320-5172','82-991-6004','davipaulobb@ig.com.br','','Paulo');
INSERT INTO t_anunciante VALUES ('AEykebTBzoAGEtr','Kennedy',10,4,'M','','82-966-1018','kjcosta@bol.com.br','','Costa');
INSERT INTO t_anunciante VALUES ('AEX5IUKHxDr8Y42','Kenneth',10,4,'M','','','kcoutinho@zipmail.com.br','','Coutinho');
INSERT INTO t_anunciante VALUES ('AEFx3LkUmhzhQGo','Luiz Fernando',10,4,'M','82-221-8508','82-9331-4934','cankum@mailbr.com.br','','Chaves');
INSERT INTO t_anunciante VALUES ('AEu2pZPNSkolrF1','Andr�',11,2,'M','','','Domarques@ig.com.br','','Domarques');
INSERT INTO t_anunciante VALUES ('AEemmxJbK4uLrL4','Jos�',14,6,'M','','','marcschubert@hotmail.com','','Silva');
INSERT INTO t_anunciante VALUES ('AEBTxZygYyBrpf4','Ana Claudia',6,4,'F','','','anafelino@hotmail.com','','Barcellos');
INSERT INTO t_anunciante VALUES ('AEluomsxLRboCYc','Ana Paula',4,4,'F','','93051809','meloap@zipmail.com.br','','melo');
INSERT INTO t_anunciante VALUES ('AEfTaKINTZNKIO2','David',13,4,'M','','xx-231-4892','canso@bol.com.br','','C�ndido Soares');
INSERT INTO t_anunciante VALUES ('AEFFjmy8z1PpIaK','JOSE ARCANJO PEREIRA',2,5,'M','82-351-4860','82-972-4293','arcocontabil@ig.com.br','','ARCANJO');
INSERT INTO t_anunciante VALUES ('AEP7gekqEJ397kq','admon',7,4,'M','82-000-0000','82-966-4397','admon@ig.com.br','','filgueiras');
INSERT INTO t_anunciante VALUES ('AE2cmWLlb9OwecL','Alberto',12,4,'M','82-373-2114','82-9302-9660','albertomcz@hotmail.com','54065688','Moutinho');
INSERT INTO t_anunciante VALUES ('AEoMyB3vWoGKbLd','PAULO CALDAS JUNIOR',6,4,'M','82-221-2091','82-973-4365','pjrilumi@ig.com.br','','ALVES');
INSERT INTO t_anunciante VALUES ('AEAiptVm4EAhRzp','Silvio',4,4,'M','82-336-0628','82-962-6040','silvioandre@email.com.br','','Andr�');
INSERT INTO t_anunciante VALUES ('AEp24wd31ZgYywl','sandra',2,4,'F','','82-997-8880','mensfo@bol.com.br','','nogueira');
INSERT INTO t_anunciante VALUES ('AEfCo6NLJHKsiwt','Silvio Tsuyoshi',12,4,'M','','82-9331-8273','silvioth@ositemail.com.br','','H. BAldan');
INSERT INTO t_anunciante VALUES ('AEAYlVDti8c2RXV','Caf�',12,4,'M','','','almodel@zipmail.com.br','','sexysite');
INSERT INTO t_anunciante VALUES ('AElWQiZXVTOEmki','Saulo',10,3,'M','82-223-4162','','saulinh0@globo.com','8865824','Cardoso');
INSERT INTO t_anunciante VALUES ('AEoUODBzxCWa063','Anderson',12,4,'M','82-324-3977','','anderalves@zipmail.com.br','','Alves de Almeida');
INSERT INTO t_anunciante VALUES ('AEYCvXpfGwkaZHx','moises',12,4,'M','82-357-6140','82-962-9000','contato@teixnet.com','84982032','teix');
INSERT INTO t_anunciante VALUES ('AEyqb7z0Ai6VfPF','wellington soares',1,4,'M','82-325-7348','82-971-8910','wesl@uol.com.br','77974947','lucena');
INSERT INTO t_anunciante VALUES ('AEIJRVKsa0NDdNm','gardel miller',10,4,'M','','82-966-2770','mariaeunice@uol.com.br','','lima barros');
INSERT INTO t_anunciante VALUES ('AE8ij9BzECyo53T','Roberto',5,6,'M','82-338-0023','82-983-9343','rdsantana@zipmail.com.br','','Duarte');
INSERT INTO t_anunciante VALUES ('AEofLXoe42r9JPw','F�bio',10,3,'M','','82-9341-0647','fabiorpc@bol.com.br','','Chagas');
INSERT INTO t_anunciante VALUES ('AEN4s2Bj1RoQqg5','Carlos Wagner',12,4,'M','82-358-5313','82-9331-8481','wsimoes@matrix.com.br','','Ara�jo Sim�es');
INSERT INTO t_anunciante VALUES ('AE071JywCsERHFD','Emerson',12,4,'M','','82-977-4942','emersonlucas@ig.com.br','','Lopes Lucas');
INSERT INTO t_anunciante VALUES ('AErhMuWTRX964UK','Fla',15,4,'M','','','fla-maceio@bol.com.br','','Maceio');
INSERT INTO t_anunciante VALUES ('AEIm7XV1YWKIxDB','Daphne',10,4,'F','','','dmvargas@ig.com.br','30343210','Vargas');
INSERT INTO t_anunciante VALUES ('AEfv1k2KIyl3DAi','Fernanda',10,4,'F','','','nandaramires@zipmail.com.br','','Ramires');
INSERT INTO t_anunciante VALUES ('AEeSfPpeWws9ZPV','Ranildo',12,4,'M','','','costamaia@bol.com.br','','Paranhos');
INSERT INTO t_anunciante VALUES ('AEaibDd30I0Ixvd','Fabiano',6,4,'M','82-221-6028','82-9371-8764','fabyanoamorim@ig.com.br','','Amorim Ferreira');
INSERT INTO t_anunciante VALUES ('AEyQmpJjTA5hJOw','diogo',10,3,'M','','','diogo.maia@bol.com.br','','maia');
INSERT INTO t_anunciante VALUES ('AEslRbmOoQvPhRq','Jos�',6,4,'M','','','jdos@bol.com.br','','Douglas de Oliveira e Silva');
INSERT INTO t_anunciante VALUES ('AE7xHMSeANftH3g','Carlos',12,4,'M','','82-9309-0713','anderalves@bol.com.br','','Guimaraes');
INSERT INTO t_anunciante VALUES ('AEkun5VTY4SQFLB','Engenho',2,5,'M','(0xx82)372-2358','','engenho.arquitetura@bol.com.br','','Arquitetura');
INSERT INTO t_anunciante VALUES ('AEj5s206jv28Y4a','WEZLY NYRON',4,4,'M','','82-994-0071','nyron@ig.com.br','','LIMA DA SILVA');
INSERT INTO t_anunciante VALUES ('AEOmRr97OMIyfdb','saulo',5,4,'M','82-983-0723','','slrs@tci.ufal.br','','ramos');
INSERT INTO t_anunciante VALUES ('AECUFDZdyUmAN9n','Carlos Andr�',3,5,'M','82-353-3150','82-9341-7706','an.melo@ig.com.br','','Melo');
INSERT INTO t_anunciante VALUES ('AEaGj1BzwuGMJ5b','Rosane',12,5,'F','82-327-1574','82-979-0348','arthur_paredes@yahoo.com.br','','Ribeiro Leite');
INSERT INTO t_anunciante VALUES ('AEZfSsa0e3Zdbge','Patricia',10,4,'F','','','dvc5@zipmail.com.br','30343210','Vargas');
INSERT INTO t_anunciante VALUES ('AEee3EnmdcFEDCB','Cristina Mary',14,6,'F','21-9972-6631','21-9807-3951','marycris2000@aol.com','','Ribeiro');
INSERT INTO t_anunciante VALUES ('AEIqtN0lOheHaDY','Paulo S�rgio',5,4,'M','','','maikow@ig.com.br','','Costa Borges');
INSERT INTO t_anunciante VALUES ('AEOGtOTmP2YrEZA','Edinaldo',10,4,'M','82-231-2672','82-981-9299','emdjr@zipmail.com.br','','Marinho Dias J�nior');
INSERT INTO t_anunciante VALUES ('AEd3Q3wZkFSdGhK','Maur�cio Vit�rio',3,5,'M','','','mauricio.fachini@bol.com.br','','Fachini');
INSERT INTO t_anunciante VALUES ('AEKpkF0BcNKlWx0','Luiz',2,4,'M','','82-999-9909','lcvadv@zipmail.com.br','201.555','And');
INSERT INTO t_anunciante VALUES ('AEo5gBO9uPMfsVg','Celso',1,4,'M','','981-0307','som5@bol.com.br','','Rubens');
INSERT INTO t_anunciante VALUES ('AE68Hi1Cl4p8RAj','Melyssa',8,4,'F','82-358-1210','82-976-6596','araquino@globo.com','105858582','Miranda');
INSERT INTO t_anunciante VALUES ('AEHWnQra1K5GxgZ','Alexandre Hon�rio',11,4,'M','82-221-0667','','xandemaceio@hotmail.com.br','','dos Santos');
INSERT INTO t_anunciante VALUES ('AE8g5ypg7Yri1SJ','Mauro',5,4,'M','82-322-3109','82-998-2129','maurolemos@bol.com.br','','Lemos');
INSERT INTO t_anunciante VALUES ('AEPl2D6PyhCtc3U','Jeferson',4,3,'M','','82-967-0805','jefersonmelo@bol.com.br','','da Silva Melo');
INSERT INTO t_anunciante VALUES ('AEY4Tul4VE7YPyp','Paulo S�rgio',5,4,'M','82-241-0684','82-9341-5095','brad_jones@ig.com.br','','Costa Borges');
INSERT INTO t_anunciante VALUES ('AEecnsNgJchSdG9','Jos�',12,6,'M','82-328-7193','82-996-3701','jpitanga@ig.com.br','','Pitanga Santos Porto');
INSERT INTO t_anunciante VALUES ('AELQ9u5Op8l4FoR','Misael',15,4,'M','82-320-2074','82-979-6781','pisosal@bol.com.br','','Lima');
INSERT INTO t_anunciante VALUES ('AEv58tGhCd2vYrM','cristiano',10,4,'M','','82-972-6850','cristiano@maianobre.com.br','','costa');
INSERT INTO t_anunciante VALUES ('AE8m1ez2vYVgJca','Mic�as',15,5,'M','82-327-1280','82-9305-0076','nyron@vircom.com.br','','Lima');
INSERT INTO t_anunciante VALUES ('AEHP8tOpSty9u5y','Jean Pierre',4,4,'M','82-221-3730','','Janp@ig.com.br','','Lopes Toledo');
INSERT INTO t_anunciante VALUES ('AEHlwJcx0lqLezU','jorge',11,3,'M','','','jorgells@uol.com.br','','lopes');
INSERT INTO t_anunciante VALUES ('AE0H8BcVoZAj2Dm','Thiago',10,4,'M','','82-977-0245','thiagodavino@zipmail.com.br','','Davino');
INSERT INTO t_anunciante VALUES ('AENYpK5y9CPqTu5','Mateus',4,4,'M','82-372-4063','82-994-5545','mateus@hostsys.com.br','','Silveira Flores');
INSERT INTO t_anunciante VALUES ('AE2winI3oJG9uPi','Davi Pontes',11,3,'M','','','aninha@mariodias.com.br','','Pontes');
INSERT INTO t_anunciante VALUES ('AEHgbgBO1urEZsN','Esdras Francisco',15,4,'M','','','esdrasonline@bol.com.br','','Silva');
INSERT INTO t_anunciante VALUES ('AEYsvAVanQN8B4p','Pousada Village Lago',12,6,'M','79-246-3467','82-962-7663','villagelagoa@ieg.com.br','','Pousada Village Lagoa');
INSERT INTO t_anunciante VALUES ('AEYsCXaDeHUnQjU','Village Lagoa',12,6,'M','82-962-7663','79-246-3467','villagelagoa@bol.com.br','','Pousada Village Lagoa');
INSERT INTO t_anunciante VALUES ('AEoZERkNoR4x0B4','Village Lagoa',12,6,'M','82-962-7663','79-246-3467','villagelagoa@bol.com.br','','Pousada Village Lagoa');
INSERT INTO t_anunciante VALUES ('AEyLW1mPiTYr2D6','LUCAS THADEU',5,3,'M','82-320-2210','82-979-2003','lucasparanhos@bol.com.br','','RANGEL PARANHOS');
INSERT INTO t_anunciante VALUES ('AEwd0dqLezE7kV8','Jos� Adelson',4,4,'M','','','dedeloliveira@hotmail.com','','Melo Oliveira');
INSERT INTO t_anunciante VALUES ('AEHMXafIbEBOhSd','Tales',2,5,'M','82-326-0129','82-972-1150','talescardoso@zipmail.com.br','','Cardoso');
INSERT INTO t_anunciante VALUES ('AERITm5Gp0lWFgR','Teresa',2,5,'F','','','20.buscar@bol.com.br','','Fiel');
INSERT INTO t_anunciante VALUES ('AERq5inkpujwJW1','CRISTIANO',12,4,'M','82-354-4169','82-994-6585','momentosdocoracao@bol.com.br','g','MORAES');
INSERT INTO t_anunciante VALUES ('AEwBEZcFgJOhKdy','C�lio',2,4,'M','82-241-3652','','celio_ricardo@zipmail.com.br','','Ricardo Barbosa de Oliveira');
INSERT INTO t_anunciante VALUES ('AELJLQVgB49uPaD','E.',4,4,'M','','82-9351-0933','allyson@fejal.com.br','76697759','Allyson');
INSERT INTO t_anunciante VALUES ('AEQsLYjEZkFSdG1','Silvio',12,4,'M','','82-9331-8273','sitecafe@sitecafe.com.br','','T. H. Baldan');
INSERT INTO t_anunciante VALUES ('AEPzKXq1udiTmXq','Jathniel',1,4,'M','','','jath@fppmedia.com','','Azevedo');
INSERT INTO t_anunciante VALUES ('AEdjKXaDeP2vQrU','Roberto',3,5,'M','','82-978-5051','robertoskyze@hotmail.com','','Araujo');
INSERT INTO t_anunciante VALUES ('AEofqTmXqTYrUvY','Diego',4,3,'M','241-1445','','diego_paredes@yahoo.com','','Ribeiro Paredes');
INSERT INTO t_anunciante VALUES ('AE7dwJ4x0BG9u5y','Paulo S�rgio',15,4,'M','82-241-0684','','maikow@ig.com.br','','Costa Borges');
INSERT INTO t_anunciante VALUES ('AECPKXiTmXaD6Hi','Eduardo',13,4,'M','82-337-5249','82-977-3954','ecazumba@bol.com.br','','Pereira Silva Cazumba');
INSERT INTO t_anunciante VALUES ('AEoJM7kVoZcF8Bc','Roberto',4,4,'M','','','viagem@pele.net','','junior');
INSERT INTO t_anunciante VALUES ('AEkliTKBAziih87','Maria das Gra�as',6,4,'F','82-377-2070','82-9341-4775','graca_paredes@yahoo.com.br','','Correia Paredes');
INSERT INTO t_anunciante VALUES ('AEpgPyp0ZAFwf6P','ROBERTO COSTA',12,4,'M','','82-972-5289','marcozzi@sunnet.com.br','','COSTA');
INSERT INTO t_anunciante VALUES ('AEAqfQzypgJIzqh','ROBERTO COSTA',12,4,'M','','82-972-5289','marcozzi@sunnet.com.br','','COSTA');
INSERT INTO t_anunciante VALUES ('AE2yfYPypgJAra1','FERNANDO',11,4,'M','','','pinto.filho@bol.com.br','','FERNANDO');
INSERT INTO t_anunciante VALUES ('AEXGRs3w7I3EfQr','Marcelo',4,4,'M','82-324-5924','82-971-7791','mqao@hotmail.com','','Oliveira');
INSERT INTO t_anunciante VALUES ('AEnlotOhC51mPiL','Jos� Carlos',15,5,'M','82-216-8330','82-966-5581','sacajo@zipmail.com.br','','Santos');
INSERT INTO t_anunciante VALUES ('AEJI1mHiLmz2vYj','Jos� Carlos',10,4,'M','82-221-4427','82-974-3145','jcfsj@zipmail.com.br','0123','Ferreira Silva J�nior');
INSERT INTO t_anunciante VALUES ('AEK69C5qTmzUnYj','Wellington',10,4,'M','82-327-0066','82-9371-1582','julywel@bol.com.br','','Balbino Costa');
INSERT INTO t_anunciante VALUES ('AEVEP2v6PqD6r2D','Saulo',10,4,'M','82-320-1916','','sbmdigital@zipmail.com.br','','Mendon�a');
INSERT INTO t_anunciante VALUES ('AEiJEJW9uHwZcpC','CICERO',7,4,'M','82-218-3179','82-971-9216','cicerosm@bol.com.br','','DOS SANTOS SILVA');
INSERT INTO t_anunciante VALUES ('AEvyBG1uH2J4pKX','Antonio',15,5,'M','11-4675-7581','','zipzip@uol.com.br','','Pereira dos Santos Filho');
INSERT INTO t_anunciante VALUES ('AEmS3wR4F8dG9Kd','Jo�o Paulo',10,3,'M','82-231-8185','82-978-8687','jota_pe@uol.com.br','81433744','Lima Braga');
INSERT INTO t_anunciante VALUES ('AELO7kN8BchSlWp','antero',12,5,'M','','','anterosouza@globo.com.br','','souza');
INSERT INTO t_anunciante VALUES ('AESHCP2nIb8tWpK','Eduardo',4,5,'M','','','evl@ev.eti.br','','Vasconcellos');
INSERT INTO t_anunciante VALUES ('AEr830lG9CzUnQj','Tayrone',12,4,'M','00-357-4063','00-972-3040','criativetay@uol.com.br','','Davis');
INSERT INTO t_anunciante VALUES ('AE27afAVoBGTezU','M�rio Cesar',10,4,'M','82-350-2247','','macealpe@zipmail.com.br','','de Albuquerque Pessoa');
INSERT INTO t_anunciante VALUES ('AEbFI2nIbwtOhC5','Odair',15,3,'M','','82-9351-8497','odair@designer.com','','Seixas J�nior');
INSERT INTO t_anunciante VALUES ('AE8AL6jwZkhC5qT','S�rgio',4,5,'M','3205276','9780007','sergio@opp.com.br','','Santos');
INSERT INTO t_anunciante VALUES ('AEiEjwRkF8dy1uX','gustavo',15,1,'M','','','carmellino@globo.com','','carmellino');
INSERT INTO t_anunciante VALUES ('AEV9cNgRsbo7Ira','Paulo Fernando',10,5,'M','82-357-2459','82-9309-4459','olismith@bol.com.br','','Lopes Ferreira');
INSERT INTO t_anunciante VALUES ('AEf61mPqTmz2nYz','Ana Cla�dia',3,5,'F','82-338-3693','82-966-0599','catioly@bol.com.br','','de Medeiros Pereira');
INSERT INTO t_anunciante VALUES ('AEMzuzM7sNK5qDQ','luiz eliziario',11,4,'M','82-231-3839','82-972-0811','eliziarioneto@ig.com.br','','neto');
INSERT INTO t_anunciante VALUES ('AEeVezMfIbgJ4F0','luciano',12,4,'M','','82-963-8230','guerrasena@ig.com.br','','Guerra de Sena');
INSERT INTO t_anunciante VALUES ('AEQNYbwZA3gB4F8','Omega',4,1,'M','82-374-4126','963-8230','omega.informatica@bol.com.br','','Informatica');
INSERT INTO t_anunciante VALUES ('AE4GAVgB4puPiLe','F�bio',10,3,'M','','82-9341-0647','cdaovivo@zipmail.com.br','','Chagas');
INSERT INTO t_anunciante VALUES ('AEiMWx0C5GTuXy9','Mouzart',3,4,'M','82-223-2143','82-973-0477','mouzartphy@hotmail.com','','Morais dos Santos');
INSERT INTO t_anunciante VALUES ('AEloHMZsNg5yTmP','Euniton Clifton',2,5,'M','','82-9303-0190','clifton@ofm.com.br','','Cavalcanti');
INSERT INTO t_anunciante VALUES ('AEFYTeHaDejMfIb','antero',12,5,'M','','','JANISETE@BOL.COM.BR','','souza');
INSERT INTO t_anunciante VALUES ('AE8l8tWp0BOpSt4','MARCELO',12,4,'M','','82-976-9231','marcelo_rs2@zipmail.com.br','','ROCHA');
INSERT INTO t_anunciante VALUES ('AEnmFSlWp05G9Kl','HUMBERTO',2,4,'M','','','ANTEROSOUZA.SOUZA@BOL.COM.BR','','SILVA');
INSERT INTO t_anunciante VALUES ('AElN6zaLmXUv6Hi','DAVID',11,3,'M','','','DAVIDWEB@GLOBO.COM.BR','','SILVA SOUZA');
INSERT INTO t_anunciante VALUES ('AEQ2tWpStchSt4N','Thiago',11,3,'M','','','grave_seu_cd@yahoo.com.br','','CD');
INSERT INTO t_anunciante VALUES ('AE5MG1u5yamQr2D','Francisco',4,4,'M','','','faslima@bol.com.br','','Lima');
INSERT INTO t_anunciante VALUES ('AEDQLeHi1mzaLu5','Helga',1,4,'F','82-223-4382','82-974-9022','rocha@sunnet.com.br','','Albuquerque');
INSERT INTO t_anunciante VALUES ('AEAVIbEn6HMv6Ha','Sydney',10,4,'M','82-235-5171','82-971-8717','sydneypontes@globo.com','','Pontes de Miranda Filho');
INSERT INTO t_anunciante VALUES ('AEvY9KdWxgl4FgR','Alysson',10,3,'M','82-241-6764','82-9302-9492','alyssound@ig.cim.br','','Fernandes Cunha');
INSERT INTO t_anunciante VALUES ('AERA16rUnIN8B4x','Yara',3,6,'F','82-3032-0177','','ftcmaiaguanabens@bol.com.br','','Tavares da Costa');
INSERT INTO t_anunciante VALUES ('AErwHMRkN8XiD6z','Ricardo S�rgio',14,7,'M','82-337-0087','82-981-2746','nevesleao@globo.com','','Neves Le�o');
INSERT INTO t_anunciante VALUES ('AEwBMnIjUvIjUv6','Fred',3,5,'M','82-231-8299','82-991-8321','fredsarmento@globo.com','','Sarmento');
INSERT INTO t_anunciante VALUES ('AEaS3gtWhCHUnIj','SAULO',9,4,'M','82-354-4665','82-982-7058','romaqricola@ig.com.br','','GUEDES ARAUJO');
INSERT INTO t_anunciante VALUES ('AExvG9mPaLI3wZs','ROMAQ LTDA',9,1,'M','82-354-4665','82-982-7058','romaq@romaq-al.com.br','','ROMAQ');
INSERT INTO t_anunciante VALUES ('AEM7U7cpKtivI3o','ADRIANO PARANHOS',8,4,'M','82-327-0025','82-9341-7006','wellington@tribunadealagoas.com.br','','PARANHOS');
INSERT INTO t_anunciante VALUES ('AEGDWhKlWFKt4Fg','Adolfo',11,3,'M','','82-977-0606','adolfo@cade.com.br','','de Melo Silva');
INSERT INTO t_anunciante VALUES ('AEwm1mHaLerUnYr','Henrique',12,4,'M','85-9941-6717','','henriqueautran@bol.com.br','','Autran');
INSERT INTO t_anunciante VALUES ('AE4yBWpKlGTmPqL','Tercio',15,4,'M','82-231-0400','','tercio_junior@bol.com.br','','De Oliveira');
INSERT INTO t_anunciante VALUES ('AExEXqD6z2ZkVoR','EDSON LEITE BATISTA',13,6,'M','','','edsonleitebatista@ig.com.br','','BATISTA');
INSERT INTO t_anunciante VALUES ('AEIc7sN8B49C5y1','humberto',2,4,'M','82-983-7362','','humberto@ofm.com.br','','de carvalho');
INSERT INTO t_anunciante VALUES ('AEIDWp0Jk3oZAj2','Diogo Alencar',10,4,'M','82-231-2156','82-997-5454','diogosimons@yahoo.com','','Simons');
INSERT INTO t_anunciante VALUES ('AEetqfc9ejRWT6b','jair',11,3,'M','','82-9341-2127','juruna5@hotmail.com','3','henrique');
INSERT INTO t_anunciante VALUES ('AE80FujwtGfjoBG','Romildo',4,4,'M','','82-9371-5821','romgyver@mailbr.com.br','','Rodrigues');
INSERT INTO t_anunciante VALUES ('AEKbY305an49ejo','Cristina',3,3,'F','82-358-5053','82-9341-1278','crisnewton@bol.com.br','101010','Newton');
INSERT INTO t_anunciante VALUES ('AE7RURN0dqfAVgl','ANTONIO JORGE',12,6,'M','11-3784-6380','','jorgemusu@ig.com.br','','MUSUMECI');
INSERT INTO t_anunciante VALUES ('AEfREdqerwdqDIV','Luis Gustavo',4,4,'M','82-342-2798','82-9341-4144','brianbattler@ig.com.br','111955624','Otaviano de Vasconcelos');
INSERT INTO t_anunciante VALUES ('AEoB8dinA3CHMRO','F�bio',12,4,'M','19-3828-7706','19-9133-3482','fabioyabiku@ig.com.br','','Yabiku');
INSERT INTO t_anunciante VALUES ('AEnh4hCP2ncpCXi','f�bio',12,4,'M','82-326-5612','82-9305-87990','fguedes@pessego.zzn.com','','guedes');

#
# Table structure for table 't_anuncio'
#
CREATE TABLE t_anuncio (
  cod_anuncio varchar(15) binary DEFAULT '' NOT NULL,
  cod_anunciante varchar(15) binary DEFAULT '' NOT NULL,
  cod_tipo_anuncio tinyint(2) DEFAULT '0' NOT NULL,
  cod_subcategoria_anuncio tinyint(3) DEFAULT '0' NOT NULL,
  data_ini_anuncio date DEFAULT '0000-00-00' NOT NULL,
  data_end_anuncio date DEFAULT '0000-00-00' NOT NULL,
  desc_anuncio tinyblob,
  imagem_anuncio char(3),
  periodo_anuncio tinyint(2) DEFAULT '0' NOT NULL,
  preco_anuncio double(7,2),
  cod_categoria_anuncio tinyint(2),
  titulo_anuncio varchar(50) DEFAULT '' NOT NULL,
  ip_anunciante_anuncio varchar(15) DEFAULT '' NOT NULL,
  quando_anuncio int(12) DEFAULT '0' NOT NULL,
  PRIMARY KEY (cod_anuncio),
  UNIQUE cod_anuncio (cod_anuncio)
);

#
# Dumping data for table 't_anuncio'
#

INSERT INTO t_anuncio VALUES ('AOnVdivYjMZsNgt','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Todo em inox, adapt�vel para fritura.\r\n\r\nTratar: 9371-4447',NULL,10,0.00,7,'Carrinho de cachorro quente','200.241.146.213',973639431);
INSERT INTO t_anuncio VALUES ('AOg9OLYjEZWpK5y','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Tratar: 344-1376',NULL,10,0.00,7,'Antena parab�lica','200.241.146.213',973639481);
INSERT INTO t_anuncio VALUES ('AOJruH2vQj8B4x0','AE6QVY26aeinrvz',1,0,'2000-10-28','2000-11-07','Semi-nova, em perfeito estado',NULL,10,500.00,22,'M�quina de costura industrial overlock','200.241.146.213',972753967);
INSERT INTO t_anuncio VALUES ('AOq1WhmPiLYrMfA','AEF14hCdGhu5q1u',1,9,'2000-11-05','2000-11-15','Casa em bom estado',NULL,10,30000.00,2,'Casa travessa itatiaia','200.241.146.213',973425864);
INSERT INTO t_anuncio VALUES ('AOcfyDYz2DI3wZA','AEF14hCdGhu5q1u',3,9,'2000-11-05','2000-11-15','Exelente casa com 3 quartos, 2 sls, garagem p/ 3 carros, �rea de servi�o, dce.\r\n\r\nContato: 981-6707',NULL,10,0.00,2,'Casa 3/4','200.241.146.213',973425725);
INSERT INTO t_anuncio VALUES ('AOAEX2fAV85qDYj','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Tratar: 9321-2056',NULL,10,0.00,7,'3 frezers, geladeira, microondas.','200.241.146.213',973639321);
INSERT INTO t_anuncio VALUES ('AOFQT6rUfIVoRkV','AEF14hCdGhu5q1u',3,0,'2000-11-07','2000-11-17','Vendo ou troco por veiculo, aceito proposta.\r\n\r\nTratar:327-0778/ 981-4417/ 326-1120',NULL,10,0.00,7,'Locadora de video','200.241.146.213',973639166);
INSERT INTO t_anuncio VALUES ('AOc0ivIN8ByTmPa','AEY6aeimquyHMQU',1,23,'2000-11-07','2000-11-12','Vendo minha HP660C. Jato de tinta colorido. Acompanha manual, disket de instala��o e 2 tubos de tintas novos...',NULL,5,150.00,19,'Impressora HP660C usada','200.199.92.15',973563107);
INSERT INTO t_anuncio VALUES ('AOW0boJcNoB4FgR','AE6QVY26aeinrvz',3,12,'2000-10-28','2000-11-07','Perto da pousa da Lua',NULL,10,28000.00,2,'Barra de S�o MIguel 30x40','200.241.146.213',972757041);
INSERT INTO t_anuncio VALUES ('AOXZT6zUvQF0tOh','AEvyBG1uH2J4pKX',5,0,'2001-05-09','2001-05-19','Preciso de Duplas ou Individual, Adultos ou Infantil. Iniciantes. Que realmente gostem de cantar. Para qualquer evento. Ligue: (0xx11) 4675-7581. Das 12 �s 20 hs.',NULL,10,0.00,13,'CANTORAS E CANTORES','200.211.160.40',989384794);
INSERT INTO t_anuncio VALUES ('AOVRU7kN0txSlO9','AE6QVY26aeinrvz',3,12,'2000-10-28','2000-11-07','Bom para lotear!!!',NULL,10,15000.00,2,'Perto do aeroporto 30x60','200.241.146.213',972757170);
INSERT INTO t_anuncio VALUES ('AOXP7cG2oSj3mW9','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','Br. de Jaragu�, 348 Fone 326-4020',NULL,10,0.00,7,'AC�STICA - MAT E PROJETO','200.199.95.6',974590608);
INSERT INTO t_anuncio VALUES ('AOTwb8difs9mzER','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','tube Logic da Rolland, semi-valvulado c/ case 2x \r\n\r\nTratar c/ Fl�vio/ tel: 979-3871',NULL,10,0.00,7,'Vendo amplificador p/ guitarra','200.241.146.213',973639611);
INSERT INTO t_anuncio VALUES ('AOLTzEgKmIpLvRl','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','R. ComendadorPalmeira, 258 Farol \r\nFone 223-2187',NULL,10,0.00,7,'Gin�stica,  dan�a, muscula��o...','200.199.95.6',974589769);
INSERT INTO t_anuncio VALUES ('AOWIERtHjVLm6Is','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','Av. Moreira e Silva, 434 Farol\r\nFone 223-6217',NULL,10,0.00,7,'Abrigos para  Autom�veis','200.199.95.6',974589554);
INSERT INTO t_anuncio VALUES ('AOQCxDfJcGEgSl5','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','Ferbronze Fone 327-1869',NULL,10,0.00,7,'A�o inoxid�vel','200.199.95.6',974589998);
INSERT INTO t_anuncio VALUES ('AOzHflPbwKcy1fd','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','Telefone 221-8771',NULL,10,0.00,7,'Parafusos e Ferramentas','200.199.95.6',974588985);
INSERT INTO t_anuncio VALUES ('AO9svQ3oJWTeH2v','AEF14hCdGhu5q1u',1,10,'2000-11-01','2000-11-11','Casa moderna situada na ladeira da casa funda. \r\n\r\n                  Contato: 327-7009\r\n                           973-0026\r\n                           Paulo',NULL,10,5000.00,2,'Casa moderna','200.199.67.83',973083183);
INSERT INTO t_anuncio VALUES ('AOndeaCbv5mOgQh','AEofLXoe42r9JPw',1,15,'2000-12-25','2001-01-04','Vendo celular NEO, com um m�s de uso, na garantia e com nota fiscal!',NULL,10,550.00,4,'OPORTUNIDADE - CELULAR NEO','200.199.51.78',977712710);
INSERT INTO t_anuncio VALUES ('AO2JMZkNgRWhKdG','AEF14hCdGhu5q1u',1,9,'2000-11-05','2000-11-15','Casa com exelente projeto, 4 suites, 410m2 de �rea �til, c/ piscina,nasc, garagem 4 ve�culos,copa-cozinha, 2dce, toda avarandada, parcelo em 24 meses e aceito im�vel.\r\n\r\ntratar: 235-1978/ 973-8558 c-927',NULL,10,0.00,2,'Casa espetacular','200.241.146.213',973426094);
INSERT INTO t_anuncio VALUES ('AOMq6peb85gdaf4','AE80FujwtGfjoBG',5,0,'2001-05-16','2001-05-26','Diagrama��o de jornais e revistas. Cria��o de logomarcas, slogans e jingles. Arte final para Panfletos, cartazes e outdoors. Progrma��o visual em geral, incluindo Internet.','jpg',10,0.00,15,'Diagrma��o e Cria��o','200.191.20.17',990001968);
INSERT INTO t_anuncio VALUES ('AOM4QUBzxvGECQW','AEN4s2Bj1RoQqg5',1,0,'2000-12-26','2001-01-05','CD DE MALA PIONEER PARA 12 CDS, (MODELO FM), EM EXCELENTE ESTADO DE CONSERVA��O, OBS. S� VENDO � VISTA',NULL,10,380.00,7,'CD DE MALA PIONEER','200.199.67.31',977836230);
INSERT INTO t_anuncio VALUES ('AOW8V05ivQN05qL','AEF14hCdGhu5q1u',5,0,'2000-11-04','2000-11-14','Estala��es residencial, predial, alta e baixa tens�o, atendendo 24H.\r\n\r\nContato: 82.996-7793/373-4400 Oliveira ',NULL,10,0.00,15,'T�cnico em eletricidade ( eletricista)','200.199.67.118',973345164);
INSERT INTO t_anuncio VALUES ('AO5hkN0lG9IbwRc','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Tranfiro, � s� uma pechincha. Pre�o a combinar.\r\n\r\nTratar: 377-5078',NULL,10,0.00,7,'Direct Tv','200.241.146.213',973640004);
INSERT INTO t_anuncio VALUES ('AOPorM7IjU7IjUv','AE6QVY26aeinrvz',1,0,'2000-10-28','2000-11-07','Todas as cores e em qualquer quantidade','gif',10,32.90,4,'Telefones SIEMENS  R$32,90','200.241.146.213',972757287);
INSERT INTO t_anuncio VALUES ('AOeCFSdyTeV8tWh','AE6QVY26aeinrvz',1,0,'2000-10-28','2000-11-07','R�dio estilo talk, em perfeito estado de conserva��o, baterias novas.',NULL,10,450.00,7,'R�dio Motorola','200.241.146.213',972754964);
INSERT INTO t_anuncio VALUES ('AOo5RF6qSsfr1B2','AE8ij9BzECyo53T',1,10,'2000-12-23','2001-01-02','Vende-se, c/des�gio, Carta de Cr�d. da Habitacional Constru��es, atualiz. mensalm. pelo IGPM-FGV, val. atual R$ 34.820,00. Ideal para quem est� comprando ou planeja comprar apto/casa da Habitacional em Macei�, Aracaj� ou Salvador. Fones 3380023/9839343.',NULL,10,34820.00,2,'Carta de Cr�dito da Habitacional Constru��es','200.199.95.141',977539435);
INSERT INTO t_anuncio VALUES ('AObnO9eHavrUnIb','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Grade variedades de plantas ornamentais e �rvore diversas. \r\n\r\nTratar: Av. Jorge Barros, Sta. Am�lia.\r\nObs: Em frente a torre celular(tim)',NULL,10,0.00,7,'Mudas de fruteiras','200.241.146.213',973639781);
INSERT INTO t_anuncio VALUES ('AOEHSdy1C5iLeHi','AEF14hCdGhu5q1u',3,0,'2000-11-07','2000-11-17','Tratar: 320-2089',NULL,10,0.00,7,'Porta  studio tascan, baixo 5 cordas, micro s/fio','200.241.146.213',973639900);
INSERT INTO t_anuncio VALUES ('AOQuwRlzNhAO2oC','AEF14hCdGhu5q1u',1,0,'2000-11-20','2000-11-30','FONE 327-1869',NULL,10,0.00,7,'BRONZE','200.199.95.6',974724744);
INSERT INTO t_anuncio VALUES ('AOOJ27AbMnsVoRs','AE6QVY26aeinrvz',1,22,'2000-10-28','2000-11-07','Em perfeito estado de conserva��o, tela de 12pol. bivolt, com windows 95, office 98,e muito mais.',NULL,10,1400.00,19,'Notebook  pentium 133mhz Toshiba','200.241.146.213',972754770);
INSERT INTO t_anuncio VALUES ('AOwMI4Nx9TRs4WG','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','FONE 221-9450 ',NULL,10,0.00,7,'A�OUGUE DO PEDRO','200.199.95.6',974590178);
INSERT INTO t_anuncio VALUES ('AOtzw7Qzq9SBk3M','AE1putsjipwvCJQ',1,0,'2000-10-02','2000-10-12','MANUTEN��O CORRETIVA E PREVENTIVA EM COMPUTADORES E IMPRESSORAS COM ATENDIMENTO A DOMIC�LIO OU EMPRESARIAL.\r\nINSTALA��O DE PROGRAMAS, APLICATIVOS, INTERNET,ETC...\r\nOTIMIZA��O DO DESEMPENHO DO COMPUTADOR.',NULL,10,0.00,15,'Assist�ncia T�cnica em Computadores','200.241.154.39',970494236);
INSERT INTO t_anuncio VALUES ('AOsBgtG1mHERcF0','AEfntl4VULKBsrq',1,0,'2001-03-10','2001-03-20','Vendo quarto de crian�a completo\r\nFalar com Neide Fone 241-3901 ',NULL,10,0.00,27,'Quarto de Crian�a','200.199.95.13',984227842);
INSERT INTO t_anuncio VALUES ('AOWCFSdyTejMhC5','AEKruPaLeHaD6Ha',6,0,'2000-10-23','2000-11-02','VENDE-SE VIOL�O GIANNINI','jpg',10,150.00,7,'Viol�o  GIANNINI','200.199.95.6',972321925);
INSERT INTO t_anuncio VALUES ('AOCe9nZB5Ow0CeP','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','Av. C. Bezerra, 331 Fone 221.2789',NULL,10,0.00,16,'Av�cola Galo de Ouro','200.199.95.6',974589199);
INSERT INTO t_anuncio VALUES ('AOlFIV8BWpC5y1u','AEKruPaLeHaD6Ha',6,0,'2000-10-23','2000-11-02','  ',NULL,10,250.00,7,'Nintendo 64','200.199.95.6',972323246);
INSERT INTO t_anuncio VALUES ('AOjRqLPr2vz3w7I','AE6QVY26aeinrvz',1,0,'2000-10-28','2000-11-07','Monof�sico e Trif�sico na Eletro Silva','jpg',10,0.00,17,'Motores KOLBAHK a pre�o de f�brica','200.241.146.213',972755877);
INSERT INTO t_anuncio VALUES ('AOmFrEZs3EJcx0t','AEF14hCdGhu5q1u',3,9,'2000-11-05','2000-11-15','3 suites, lavabo, gabinete, dce, despensa. Ref. 1841\r\n\r\nContato: 231-8866 Creci-906j',NULL,10,0.00,2,'Aldebaran','200.241.146.213',973426298);
INSERT INTO t_anuncio VALUES ('AOKX0dqTmPUnQjM','AEF14hCdGhu5q1u',6,9,'2000-11-05','2000-11-15','Boa localiza��o, arquitetura moderna, c/ terreno ao lado ou pr�ximo 15x30.\r\n\r\nTratar: 983-7019',NULL,10,0.00,2,'Procuro casa','200.241.146.213',973426460);
INSERT INTO t_anuncio VALUES ('AOb0VgtWpS5P2vY','AEF14hCdGhu5q1u',1,9,'2000-11-05','2000-11-15','Casa c/ garagem, sala, 3/4 sendo 1 suite, wc, piscina.\r\n\r\nRef.302\r\nTratar: 231-4181 Creci.765',NULL,10,70000.00,2,'Gruta','200.241.146.213',973426577);
INSERT INTO t_anuncio VALUES ('AOJTN8BO9CzUfsV','AEF14hCdGhu5q1u',3,9,'2000-11-05','2000-11-15','Rua, Travessa Fautino Silveira,\r\n\r\nTratar: 9303-7172/ 336-8586',NULL,10,12000.00,2,'Casa Pitanguinha','200.241.146.213',973426918);
INSERT INTO t_anuncio VALUES ('AOwhApKdy1QbwRk','AEF14hCdGhu5q1u',1,9,'2000-11-05','2000-11-15','casa c/ 3/4 sendo 1 suite, dce, 2 salas, garagem p/ 4 carros.\r\n\r\nTratar: 9371-1476 c 928',NULL,10,70000.00,2,'Imperd�vel','200.241.146.213',973427064);
INSERT INTO t_anuncio VALUES ('AO0fafA3wZ4xSlO','AEF14hCdGhu5q1u',1,9,'2000-11-05','2000-11-15','casa 5/4 sendo 2 suites, 4 salas, acabamento luxo.\r\n\r\nTratar: 9371-1476 c928\r\n',NULL,10,110000.00,2,'Casa 5/4','200.241.146.213',973427190);
INSERT INTO t_anuncio VALUES ('AOzf2fsF0lavQbw','AEF14hCdGhu5q1u',1,9,'2000-11-05','2000-11-15','casa c/ 4/4 sendo 2 suites, garagem p/ 3 carros, c/ arm�rios, piscina.\r\n\r\nTratar:241-5804/ 9341-0066\r\n',NULL,10,100000.00,2,'Murilopolis, exelente casa 4/4','200.241.146.213',973427357);
INSERT INTO t_anuncio VALUES ('AOwqlOhKdGnQjMf','AEF14hCdGhu5q1u',4,9,'2000-11-05','2000-11-15','Casa em perfeito estado, 3/4 sendo 1 suite, 2salas, dce, varanda pr�ximo a Fernandes Lima\r\n\r\nTratar: 327-4760/ 971-4589',NULL,10,70000.00,2,'Casa 3/4 sendo 1 suite','200.241.146.213',973427617);
INSERT INTO t_anuncio VALUES ('AOvVYboJcFBWhKd','AEF14hCdGhu5q1u',1,9,'2000-11-05','2000-11-15','Caa c/ 4/4 sendo 2 suite, quintal, dce.\r\n\r\nContato: 977-4330 c821',NULL,10,90000.00,2,'Espetacular 4/4','200.241.146.213',973427786);
INSERT INTO t_anuncio VALUES ('AO0WINgBWhmPavY','AEF14hCdGhu5q1u',1,9,'2000-11-05','2000-11-15','Casa c/ 4 Quartos sendo 2 suites c/ arm�rio, piscina.\r\n\r\ncontato: 9311-4470 c927',NULL,10,170000.00,2,'Aldebaran Beta','200.241.146.213',973427901);
INSERT INTO t_anuncio VALUES ('AOf3lGLeH2ZkN8B','AEF14hCdGhu5q1u',1,9,'2000-11-05','2000-11-15','Casa 4 suites, 300m2 de �rea �til, hidro, nascente, parcelo em 24 meses e aceito im�vel.\r\n\r\ncontato: 235-5286/ 9311-4470',NULL,10,0.00,2,'Jardim do Horto','200.241.146.213',973428047);
INSERT INTO t_anuncio VALUES ('AOoWfAV7B4x0t4x','AEF14hCdGhu5q1u',1,9,'2000-11-05','2000-11-15','Casa c/ 4 suites, piscina, no lote 20x30, toda no arm�rio.\r\n\r\ncontato: 235-5431/ 973-8558',NULL,10,0.00,2,'4 suites no Jardim do Horto','200.241.146.213',973428179);
INSERT INTO t_anuncio VALUES ('AO24vH2vQbgBWpS','AEF14hCdGhu5q1u',1,9,'2000-11-05','2000-11-15','�tima p/ consult�rio/ cl�nica.\r\n\r\nTratar: 221-9115/ 338-2304',NULL,10,0.00,2,'Ao lado da igreja dos Mart�rios','200.241.146.213',973428351);
INSERT INTO t_anuncio VALUES ('AO6oHE0dG9KdG9u','AEF14hCdGhu5q1u',1,9,'2000-11-05','2000-11-15','com 3 wc, garagem privativa.\r\n\r\nTratar: 235-5286/ 9311-4470 c927',NULL,10,0.00,2,'Casa 4 salas p/ escrit�rio ou laborat�rio','200.241.146.213',973428590);
INSERT INTO t_anuncio VALUES ('AOcSV8BWpSPaDYr','AEF14hCdGhu5q1u',3,9,'2000-11-05','2000-11-15','Tratar: 972-1549',NULL,10,0.00,2,'Casa a 100m da praia','200.241.146.213',973428677);
INSERT INTO t_anuncio VALUES ('AOI7qnI3oRO9uXi','AEF14hCdGhu5q1u',1,9,'2000-11-05','2000-11-15','Excelente casa pr�x. ao mar, com 3/4 sendo 1 suite, jardim, varanda, dce, c/ arm�rios.\r\n\r\nTratar: 981-3455 c.675',NULL,10,130000.00,2,'Oportunidade!!!','200.241.146.213',973428838);
INSERT INTO t_anuncio VALUES ('AOdFQV8tWhezUnQ','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','Aluga-se Sala para consult�rio m�dico, mobiliada. R. Afonso Pena,99 Farol Tr. 326-3112; 981-5488; 325-8231. ',NULL,10,0.00,2,'SALA','200.199.95.6',973535827);
INSERT INTO t_anuncio VALUES ('AOuOBGT6jEZcxSd','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','Aluga-se sala no no Edf Macei� Work Center, toda mobiliada,persiana, ar condicionado, telefone, com equipamento completo para dentista. Tr. 973-8624 ',NULL,10,0.00,2,'SALA','200.199.95.6',973535405);
INSERT INTO t_anuncio VALUES ('AODadyvQbolGTmz','AEF14hCdGhu5q1u',6,0,'2000-11-05','2000-11-15','Pago a vista, tenho comtemplado.\r\n\r\nTratar: 9305-8958/ 9381-5547',NULL,10,0.00,14,'Compro contemplado ou n�o!!!','200.241.146.213',973431600);
INSERT INTO t_anuncio VALUES ('AOaqlinsN85anI3','AEF14hCdGhu5q1u',6,0,'2000-11-05','2000-11-15','Compre um cons�rcio de uma CG partida el�trica e ganhe outro.\r\n\r\nContato: 982-4098',NULL,10,0.00,14,'Compre um cons�rcio e ganhe outro.','200.241.146.213',973431734);
INSERT INTO t_anuncio VALUES ('AOJxINgJcxuPqTm','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Foram pagas, 15 parcelas\r\n\r\nTratar: 974-7468',NULL,10,0.00,14,'F-350 N�o comtemplado','200.241.146.213',973431829);
INSERT INTO t_anuncio VALUES ('AOG61YjE7Ax0lG9','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Consor. contemplado Volks. Cr�dito de R$14.300,00 e parcelas de R$ 235,00\r\n\r\nTratar: 983-5014',NULL,10,0.00,14,'Cons�cio contemplado','200.241.146.213',973431951);
INSERT INTO t_anuncio VALUES ('AOn1kx0tWhK5y1m','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Consertamos Tv e Video de todas as marcas e compro com defeito.\r\n\r\nTratar:  241-7042',NULL,10,0.00,15,'Tv e Video','200.241.146.213',973432114);
INSERT INTO t_anuncio VALUES ('AOp505AN8tpCXiD','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Temos profissionais qualificados com boas refer�ncias, bab�, arrumadeira, cozinheira e etc...\r\n\r\nContato: 326-9787/ 3032-1258\r\n',NULL,10,0.00,15,'Agencia Boa Esperan�a','200.241.146.213',973432261);
INSERT INTO t_anuncio VALUES ('AOb7afAN8e3wRcF','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Temos empregadas dom�ticas vinda do interior, com ref. que dorme no emprego e folga por quinzena.\r\n\r\nContato: 334-2437  Taxa R$50,00',NULL,10,50.00,15,'Ag�ncia FAL','200.241.146.213',973432418);
INSERT INTO t_anuncio VALUES ('AOhdgtOhKdaDYz2','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Dispomos de empregadas dom�ticas c/ ref. que folga por quinzena, e damos garantia de 90 dias. Taxa de R$ 50,00',NULL,10,50.00,15,'Ag�ncia de servi�os dom�ticos','200.241.146.213',973432550);
INSERT INTO t_anuncio VALUES ('AOrZafAVoR4pSlO','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Temos empregadas dom�stica vinda do interior c/ ref. que dor no emprego e folga por quinzena. Damos garantia de 90 dias para reposi��o.\r\nTaxa de R$ 50,00\r\nTratar: 337-5222/ 337-5308',NULL,10,50.00,15,'Ag�ncia Santos','200.241.146.213',973432714);
INSERT INTO t_anuncio VALUES ('AO4m0ly1mzoJcx0','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','PInte agora pelo menor pre�o de mercado, com ou sem material. pagt. facilitado com chegue pr�- datado. Cobrimos qualquer or�amento.\r\n\r\nContato: 235-4502/ 9305-3893',NULL,10,0.00,15,'Pintor ( pinte sua casa ou seu Apto.)','200.241.146.213',973432888);
INSERT INTO t_anuncio VALUES ('AOUWIN0dyTAVgBW','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Pisos, pedras e vidros Blindex, pagt. Facilitado c/ cheque pr�-datado.\r\n\r\nContato: 235-4502/ 3905-3893',NULL,10,0.00,15,'Revitaliza��o e restaura��o de transpar�ncia','200.241.146.213',973433053);
INSERT INTO t_anuncio VALUES ('AOqrKP2fI38tOhC','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Fotos, grava��es, flagrantes.\r\n\r\nLigue j�: 981-3046/ 3032-2124',NULL,10,0.00,15,'Detetive Luiz','200.241.146.213',973433140);
INSERT INTO t_anuncio VALUES ('AO5L49mXqT6zaD6','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Combate com gel, 30 anos de esperi�ncia.\r\nImunilar\r\n\r\nContato: 336-0057/ 978-1059',NULL,10,0.00,15,'Dedetiza��o, Desratiza��o, Descumpiza��o','200.241.146.213',973433300);
INSERT INTO t_anuncio VALUES ('AOamgB4F0tG1uXq','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Reformas de estofados em 4 dias, m�o de obra \r\n\r\nContato: 3032-1656/ 974-1694',NULL,10,0.00,15,'Disk sof�','200.241.146.213',973433400);
INSERT INTO t_anuncio VALUES ('AOQMbgJcF8dy1C5','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Massoterapia �urea e pele, massagem de relaxamento, stress, cansa�o,  e dores em geral, terapia alternativa, reequil�br�o f�sico e mental.\r\n\r\nContato: 979-5320',NULL,10,0.00,15,'Cl�nica de est�tica','200.241.146.213',973433575);
INSERT INTO t_anuncio VALUES ('AOZ3ebgtO9YjEQb','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Aluguel de micro-�nibus p/ viagens e excus�es, Tv e video.\r\n\r\nContato: 997-1066/ 982-7639',NULL,10,0.00,15,'Luau turimos','200.241.146.213',973433727);
INSERT INTO t_anuncio VALUES ('AOuqlqLYjEBWhCX','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','�timos pre�o em pinturas texturizadas e em apto.\r\n\r\nTratar: 338-3782/ 9304-3000',NULL,10,0.00,15,'Pinta Tudo!!!!','200.241.146.213',973433822);
INSERT INTO t_anuncio VALUES ('AOPJMRcF0lqD6z2','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Pedreiro, pintor, encanador, carpinteiro, eletricista. Or�amento Gr�tis.\r\n\r\nContato: 9305-0031',NULL,10,0.00,15,'Servi�os!!!!!','200.241.146.213',973433993);
INSERT INTO t_anuncio VALUES ('AOFloBO9uPwJ4hC','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Monografias, curriculos, contratos, trabalhos\r\n\r\nTratar: 326-4433',NULL,10,0.00,15,'Digita��o Profissional','200.241.146.213',973434635);
INSERT INTO t_anuncio VALUES ('AOLiBGDYjMJkN8t','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Abertura, encerramento e contabilidade em geral.\r\n\r\nContato: 338-2926/ 9304-8709',NULL,10,0.00,15,'Abertura e encerramento de Firma','200.241.146.213',973434748);
INSERT INTO t_anuncio VALUES ('AOiIfkF0tW1mPqT','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Massagem para relaxamento, combate ao stress em geral.\r\n\r\nContato: 979-6842',NULL,10,0.00,15,'Massoterapia','200.241.146.213',973434825);
INSERT INTO t_anuncio VALUES ('AO0NIN8tO96rM7A','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Servi�os: contabeis, pessoal, fiscal, \r\n\r\nContato: Cl�udio ou Cl�lia\r\nFone: 351-7247\r\n ',NULL,10,0.00,15,'Servi�os contabeis','200.241.146.213',973434961);
INSERT INTO t_anuncio VALUES ('AO5TyDIVgByL6rD','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','abertura de firma, baixa, s/pessoal, s/ fiscal, assesoria.\r\n\r\nTratar: 357-3515/ 963-4990/ 3032-1722',NULL,10,0.00,15,'Escrit�rio Cont�bil','200.241.146.213',973435082);
INSERT INTO t_anuncio VALUES ('AO4BwJWhC5anIbE','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Tratar: 327-1504/ 978-5147',NULL,10,0.00,15,'Encomendas( tortas, doces, salgados)','200.241.146.213',973435161);
INSERT INTO t_anuncio VALUES ('AO9GR4hK5qnIVoR','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Fa�o servi�os gerais, tenho 15 anos de experi�ncia.\r\n\r\nContato: 9331-4984/ 327-7733',NULL,10,0.00,15,'Daniel ( eletricista, encanador, pintor)','200.241.146.213',973435265);
INSERT INTO t_anuncio VALUES ('AOgGBW9C5yD6zUf','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Consertamos fax em domicilio.\r\nVisita Gr�tis.\r\nContato: 351-1573/ 963-3773',NULL,10,0.00,15,'Consertamos Fax','200.241.146.213',973435529);
INSERT INTO t_anuncio VALUES ('AOidoBy1erwJ4xS','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Atendimento aos domingos e feriados.\r\n\r\nContato: 982-8957/ 3032-2680\r\n',NULL,10,0.00,15,'DAVID ( Servi�os el�tricos/hidr�ulicos)','200.241.146.213',973435649);
INSERT INTO t_anuncio VALUES ('AOw9khuzUf4pK5q','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Fapinha, walk machine mini-moto\r\n\r\nContato: 966-9368',NULL,10,0.00,15,'Consertamos, mini -ve�culos','200.241.146.213',973435754);
INSERT INTO t_anuncio VALUES ('AO1PJGL6jEtO9uH','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Mauten��o, conserto e pintura em: Fog�es,m�q. de lavar, geladeiras, e ar condicionado.\r\n\r\nContato: 966-9368',NULL,10,0.00,15,'Ar condicionado...','200.241.146.213',973435986);
INSERT INTO t_anuncio VALUES ('AOymxC5qTmH2nYj','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Com motorista\r\n\r\nContato: 372-3048/ 972-9812',NULL,10,0.00,15,'Aluguel de carro (Elba)','200.241.146.213',973436096);
INSERT INTO t_anuncio VALUES ('AO13eRWhCdavYz2','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Atendimento em domicilio, domingo e feriado\r\n\r\nContato: 978-1795/ 351-2085',NULL,10,0.00,15,'Conserto de Tv , Som e Video','200.241.146.213',973436204);
INSERT INTO t_anuncio VALUES ('AO8GsNgRkV8BcNg','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Para reposi��o. Reproduzimos sob encomenda.\r\n\r\nContato: 342-3054',NULL,10,0.00,15,'Azulejos e cer�micas antigas','200.241.146.213',973436304);
INSERT INTO t_anuncio VALUES ('AO6wzM7sVolG1mP','AEF14hCdGhu5q1u',4,0,'2000-11-05','2000-11-15','Atendo em domic. Corte, escova. S� Ponta Verde, Jati�ca, Paju�ara.\r\n\r\nContato: 9311-6654',NULL,10,0.00,15,'Cabeleira experiente','200.241.146.213',973436453);
INSERT INTO t_anuncio VALUES ('AOPnqLYjMfAVgJc','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Em uniformes industriais e fardamentos em malhas.\r\n\r\nContato: 338-3588',NULL,10,0.00,15,'Fa�o Fac��o','200.241.146.213',973436548);
INSERT INTO t_anuncio VALUES ('AOxcfsF0lO1uPiL','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Confidencial, com fotos e filmagem gr�tis.\r\n\r\nTratar; 972-3232/ 374-5208/ 978-0699',NULL,10,0.00,15,'Detetives da Calheiros','200.241.146.213',973436659);
INSERT INTO t_anuncio VALUES ('AOpOR4xSlI9mH2n','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Tv, Video, micro e som. Servi�o com garantia. Visita Gr�tis.\r\n\r\nTratar: 327-3246/ 9305-5486',NULL,10,0.00,15,'Disk conserto','200.241.146.213',973436782);
INSERT INTO t_anuncio VALUES ('AOc7ansVoRG1mPi','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Sou Carlos e atendo os sete dias da semana.\r\n\r\nTratar: 3032-1352',NULL,10,0.00,15,'Eletricista','200.241.146.213',973436856);
INSERT INTO t_anuncio VALUES ('AOwkfkx0lOT5y2m','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Investiga��es criminais, grava��es, localiza��o de pessoas, endere�os e ve�culos roubados.\r\n\r\nContato: 3032-2034/ 336-0033',NULL,10,0.00,15,'Detetive Particular','200.241.146.213',973436986);
INSERT INTO t_anuncio VALUES ('AO8A1I3oZsx0lOh','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Anime sua festa com sucesso.\r\n\r\nContato: 221-1551/ 962-4159/ 962-2606',NULL,10,0.00,18,'Videok� mania','200.241.146.213',973437379);
INSERT INTO t_anuncio VALUES ('AO2c7dqvQb8tWhK','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Musica para todos os gostos. (1500)M�s.',NULL,10,0.00,18,'Fa�a sua festa diferente','200.241.146.213',973437479);
INSERT INTO t_anuncio VALUES ('AOcJU7AbMnsVw7I','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','O melhor do Brasil\r\n\r\nContato: 356-7010/ 979-2192',NULL,10,0.00,18,'Carnaval 2001 Salvador','200.241.146.213',973437558);
INSERT INTO t_anuncio VALUES ('AOIZ2ZkF0tqL6zM','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','100% de anima��o.\r\n\r\nContato: 328-1647/ 971-0702/ 241-8842',NULL,10,0.00,18,'Videok� do Ronaldo','200.241.146.213',973437644);
INSERT INTO t_anuncio VALUES ('AOnHCXy1m5UfQrU','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Contato: 231-7018/ 9311-5157/ 974-4195',NULL,10,0.00,18,'Anime sua festa alugue um videok�','200.241.146.213',973437736);
INSERT INTO t_anuncio VALUES ('AOFzCHavYjgB4xS','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Anivers�rios de 15 anos, casamentos com lindas paisagens, efeitos e fundos musicais.\r\n\r\ncontato: 9371-5809',NULL,10,0.00,18,'Fotos e Filmagens','200.241.146.213',973437840);
INSERT INTO t_anuncio VALUES ('AO6D4hwB4FS5GhS','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Tv , som completo, m�sicas lan�amento.\r\n\r\nTratar: 9304-3000/ 9371-6300',NULL,10,0.00,18,'Rob-som Videok�','200.241.146.213',973437980);
INSERT INTO t_anuncio VALUES ('AOgW7WhC5qvQ3wZ','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Anime sua festa, m�sica: Ana J�lia, agachadinho e muito mais.\r\n\r\nContato: 972-5957',NULL,10,0.00,18,'Videok� fest','200.241.146.213',973438102);
INSERT INTO t_anuncio VALUES ('AOpX7A3wZspSdGh','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Venha curtir, o melhor carnaval do Brasil.\r\nSalvador-BA\r\n\r\nContato: 979-1225/ 374-2162\r\n',NULL,10,0.00,18,'Carnaval 2001','200.241.146.213',973438217);
INSERT INTO t_anuncio VALUES ('AOjLGDYboJG1mH2','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Exc. janeiro de 2001.\r\n\r\nTratar: 336-1160/ 976-5404',NULL,10,0.00,18,'Fernando de Noronha','200.241.146.213',973438306);
INSERT INTO t_anuncio VALUES ('AO9G7cpSlOTez2v','AEF14hCdGhu5q1u',2,0,'2000-11-05','2000-11-15','Pensionato femino no Tabuleiro, tudo novo.\r\n\r\nTratar: 342-1723/ 353-5076',NULL,10,0.00,9,'Pensionato femino','200.241.146.213',973438548);
INSERT INTO t_anuncio VALUES ('AOZV6jE7IjoJcFg','AEF14hCdGhu5q1u',2,0,'2000-11-05','2000-11-15','Na ponta verde, mo�a n�o fumante.\r\n\r\nTratar: 377-0037/ 9331-5863/ ',NULL,10,0.00,18,'Divido apto. ( Mobiliado)','200.241.146.213',973438703);
INSERT INTO t_anuncio VALUES ('AO4uN8lOhSPqLmP','AEF14hCdGhu5q1u',2,0,'2000-11-05','2000-11-15','Tratar: 327-8206/ 982-0200',NULL,10,0.00,9,'Quarto mobiliado na Paju�ara e dou emprego','200.241.146.213',973438783);
INSERT INTO t_anuncio VALUES ('AOPvGDQ3oJhuH2n','AEF14hCdGhu5q1u',2,0,'2000-11-05','2000-11-15','Com Frigobar, TV, mobiliada e estacionamento pr�prio.\r\n\r\nTratar: 231-7392',NULL,10,0.00,9,'Su�te','200.241.146.213',973438900);
INSERT INTO t_anuncio VALUES ('AOb4nsN0tOTezUf','AEF14hCdGhu5q1u',2,0,'2000-11-05','2000-11-15','Bem localizado �timo pre�o.\r\n\r\nTratar: 972-1092',NULL,10,0.00,9,'Quartos c/ banheiro','200.241.146.213',973439000);
INSERT INTO t_anuncio VALUES ('AOquFSdG9CPiD6z','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Com ensacadeira\r\n\r\nTratar: 983-2752',NULL,10,0.00,17,'Forrageira trif�sica com motor 7,5cv','200.241.146.213',973439107);
INSERT INTO t_anuncio VALUES ('AOPf2fAVgBG1uPi','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Contato: 325-6622',NULL,10,0.00,3,'Disk Fil�','200.241.146.213',973439203);
INSERT INTO t_anuncio VALUES ('AO04JyLQ3oBOT6i','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Comida caseira, card�pio variado. Entrega em domic�lio.\r\n\r\nContato: 336-7813',NULL,10,0.00,3,'Marmitas e quentinhas','200.241.146.213',973439350);
INSERT INTO t_anuncio VALUES ('AO3RM7sVoR4x0Jc','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Marmitas Marylu. N�o perca esta oportunidade de comer a melhor comida da cidade. Temos marmitas LIGHT.\r\n\r\nLigue e confira: 235-6016',NULL,10,0.00,3,'Ligue quentinhas e marmitas','200.241.146.213',973439480);
INSERT INTO t_anuncio VALUES ('AOUQ9miL6zwRcF8','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Comida caseira\r\n\r\nContato: 231-7416',NULL,10,0.00,3,'Restaurante fornaece marmita','200.241.146.213',973439548);
INSERT INTO t_anuncio VALUES ('AOvilyTmPifI3wZ','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Deseja trabalhar nas horas vagas???\r\n\r\nligue: 983-7208',NULL,10,0.00,8,'Renda extra!!!!','200.241.146.213',973439653);
INSERT INTO t_anuncio VALUES ('AO736jw7A30lOhK','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Complemente sua renda mensal.\r\n\r\nLigue: 357-3333',NULL,10,0.00,8,'Sacoleiras e revendedoras','200.241.146.213',973439724);
INSERT INTO t_anuncio VALUES ('AOd7aeHavQlqL6r','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Motorista ou motoqueiro.\r\n\r\nTenho carro.\r\n\r\nContato: 979-6482',NULL,10,0.00,8,'Preciso trabalhar!!!','200.241.146.213',973439808);
INSERT INTO t_anuncio VALUES ('AOUXgdy1u52nQjM','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Falar com Cl�udio.\r\n\r\nTratar: 976-5364',NULL,10,0.00,8,'Auxiliar de cabelereiro','200.241.146.213',973439890);
INSERT INTO t_anuncio VALUES ('AOswzMZsNglG1uP','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Tenho experi�ncia de 20 anos, toma conta de idosos, e aceito viajar c/ o paciente.\r\n\r\nTratar: 336-6175',NULL,10,0.00,8,'Atendente de enfermagem','200.241.146.213',973440023);
INSERT INTO t_anuncio VALUES ('AO3YLYbE7AhCXqT','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Que durma no emprego, e tire folga quinzenal.\r\n\r\nTratar: 334-2437',NULL,10,0.00,8,'Preciso de empregada dom�stica','200.241.146.213',973440105);
INSERT INTO t_anuncio VALUES ('AO6JUBW9CXUfAVg','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Comiss�o de 50% e manicure, comiss�o de 40%.\r\n\r\nTratar: 966-9393',NULL,10,0.00,8,'Precisa-se de cabeleireira','200.241.146.213',973440208);
INSERT INTO t_anuncio VALUES ('AOlEzMZsVglOhKd','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Com pr�tica em overlock, galoneira e industrial.\r\n\r\nContato: 231-5274',NULL,10,0.00,8,'Precisa-se de costureira','200.241.146.213',973443582);
INSERT INTO t_anuncio VALUES ('AOrZ2ezU7kFSdiv','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Ensino: m�dio.\r\n\r\nTratar: 221-9350/ 976-6521',NULL,10,0.00,8,'Precisa-se de Prof. matem�tica com experi�ncia','200.241.146.213',973443333);
INSERT INTO t_anuncio VALUES ('AOTy5anAN0XivQb','AEcxkx0BcN0t4Fg',3,15,'2000-11-05','2000-11-15','Tenho v�rios tipos de aparelhor e marcas: Nokia 6120-5120, star tac, Ultra tac,erricson,neo,concept\r\n\r\nPre�os imbat�veis.\r\n\r\nContato: 999-4040/ 999-0909/ \r\n',NULL,10,0.00,4,'Compre um Celular AGORA!!!','200.241.146.213',973442406);
INSERT INTO t_anuncio VALUES ('AOWdoBWpKdavYrM','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Que saibam manusear bem c/ tintas, reflexos, megahair e cortes, experi�ncia m�nima de 5 anos.\r\n\r\nContato: 235-1346',NULL,10,0.00,8,'Percisa-se de cabeleireiros','200.241.146.213',973443710);
INSERT INTO t_anuncio VALUES ('AOk82fsVoRWpKlO','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Ganhe de R$ 300.00 a R$ 1.500,00 p/m�s\r\n\r\nContato: 372-3253',NULL,10,0.00,8,'Oportunidade, renda extra!!!!','200.241.146.213',973443968);
INSERT INTO t_anuncio VALUES ('AO0WIN0lyTQ3oJW','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Tratar com Luiz Henrique\r\n\r\nFone: 974-0699/ 966-9926/ 241-5117',NULL,10,0.00,8,'Procura-se vocalista e baixista de Pagode','200.241.146.213',973444105);
INSERT INTO t_anuncio VALUES ('AOxlgtOhCX2nIbw','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Possuo t�xi a mais de 10 anos. Fa�o contrato para levar seu filho a escola e busca-lo, levo vc ao servi�o.\r\n\r\nTratar: 3032-1969',NULL,10,0.00,8,'T�xi (contrato) presta��o','200.241.146.213',973444253);
INSERT INTO t_anuncio VALUES ('AOWuN8tWpKXqTuX','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Filhotes de Rottweiler\r\n\r\nTratar: 9341-0592/ 981-0131',NULL,10,0.00,5,'Canil Caraj�s','200.241.146.213',973444477);
INSERT INTO t_anuncio VALUES ('AODzSPaD8tqLez2','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Tratar: 320-2270',NULL,10,0.00,5,'Filhotes de pinsher ZERO','200.241.146.213',973444557);
INSERT INTO t_anuncio VALUES ('AOL2Xc9uPaZcxSl','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Filhotes puro nascidos em 07/09/2000, Cachorros d�ceis.\r\n\r\nTratar: 327-9426',NULL,10,0.00,5,'Boxer','200.241.146.213',973444644);
INSERT INTO t_anuncio VALUES ('AO8GQ38tOhezavY','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Lindos filhotes\r\n\r\nTratar: 983-8577/ 359-1764',NULL,10,0.00,5,'Rottwailler','200.241.146.213',973444718);
INSERT INTO t_anuncio VALUES ('AO0qly1C5GLeHaL','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Filhotes\r\n\r\nTratar: 354-0028/ 354-2210/ 991-8572',NULL,10,0.00,5,'Rotwailler','200.241.146.213',973444796);
INSERT INTO t_anuncio VALUES ('AO5uhCPiD63EZsV','AEF14hCdGhu5q1u',5,24,'2000-11-05','2000-11-15','Conserto e manuten��o de micros,impressoras, monitores,Up-grade. Com garantia. Atendemos em domic�lio. \r\n\r\nLigue: 221-1415',NULL,10,0.00,19,'Bayma net','200.241.146.213',973453294);
INSERT INTO t_anuncio VALUES ('AOw4nsN8tWDQjMf','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Atendemos a domic�lio.\r\n\r\nTratar: 328-2710 / 9311-8069',NULL,10,0.00,15,'Vacina��o e corte  de cauda','200.241.146.213',973444976);
INSERT INTO t_anuncio VALUES ('AOQvGT6zUncxSB4','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Tratar: 983-3765',NULL,10,250.00,5,'Filhotes de gatos PERSA','200.241.146.213',973445068);
INSERT INTO t_anuncio VALUES ('AOtFAFSdG1I3oJ4','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','Aluga-se 2 salas com 25 e 40m2, na R. Frei Caneca, 20 por tr�s da Star Cell, Farol. Tr. 974.0105  ',NULL,10,0.00,2,'SALAS','200.199.95.6',973534992);
INSERT INTO t_anuncio VALUES ('AOPJERcxSliL6H2','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Tratar: 336-9395/ 9351-5105',NULL,10,0.00,5,'Filhotes de Dalmata','200.241.146.213',973445135);
INSERT INTO t_anuncio VALUES ('AOAwryLez2ZkN8B','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Atendo inclusive feriados.\r\n\r\nTratar: 974-3729/ 328-2585\r\n',NULL,10,0.00,5,'Veterin�rio a domic�lio','200.241.146.213',973445233);
INSERT INTO t_anuncio VALUES ('AObAnA3wZsF0tWp','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Com 2 meses, j� vermifugados, exc. ninhada\r\n\r\nTratar: 999-0010',NULL,10,0.00,5,'Filhotes de PIT-BULL','200.241.146.213',973445316);
INSERT INTO t_anuncio VALUES ('AOh58lq1mPUfIbE','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Decorados, e tamb�m salgados.\r\n\r\nPre�os variados.\r\n\r\nTratar: 328-4287/ 328-4962',NULL,10,0.00,6,'Doces Finos','200.241.146.213',973445481);
INSERT INTO t_anuncio VALUES ('AOeP0XiLYjgBWpC','AEF14hCdGhu5q1u',2,0,'2000-11-05','2000-11-15','Alugamos para festa e eventos.\r\n\r\nTratar: 223-7191/ 973-8319',NULL,10,0.00,6,'Pipoca e algod�o','200.241.146.213',973445559);
INSERT INTO t_anuncio VALUES ('AO7FIV8B4xK5G9K','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Sem drogas, sem sacrif�cios.\r\n\r\nLigue agora:336-8876/ 982-8581',NULL,10,0.00,6,'Reduza at� 10Kg em 30 dias','200.241.146.213',973445682);
INSERT INTO t_anuncio VALUES ('AOjRafkxScP2fAV','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Vendemos mudas de Assa�\r\n\r\nTratar: 231-0557',NULL,10,0.00,6,'Rei das Frutas','200.241.146.213',973445754);
INSERT INTO t_anuncio VALUES ('AO5LqDQjE7W9mHU','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Cestas e flores p/ todas as ocasi�es, mensagem fonada, de segunda a domingo. \r\n\r\nLigue: 3723222',NULL,10,0.00,6,'Mega menssagens','200.241.146.213',973445901);
INSERT INTO t_anuncio VALUES ('AOWuV8B4x05qTuX','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Cestas e mensagens fonadas para toadas as ocasi�es.\r\n\r\nLigue: 372-3222',NULL,10,0.00,15,'Mega mensagens','200.241.146.213',973445977);
INSERT INTO t_anuncio VALUES ('AOgN6rEZsV0lOhK','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Toalhas e caixas t�rmicas para festas.\r\n\r\nLigue: 350-3015/ 981-0732',NULL,10,0.00,15,'Alugo mesas e cadeiras....','200.241.146.213',973446087);
INSERT INTO t_anuncio VALUES ('AOrRUfI3oRWhKdG','AEF14hCdGhu5q1u',1,15,'2000-11-05','2000-11-15','Celular CHROMA.\r\n\r\nTratar: 966-5818',NULL,10,490.00,4,'Chroma','200.241.146.213',973446427);
INSERT INTO t_anuncio VALUES ('AOpdoJWpKdiD6zU','AEF14hCdGhu5q1u',1,15,'2000-11-05','2000-11-15','Celular CHROMA.\r\n\r\nTratar: 966-5818',NULL,10,490.00,4,'Chroma','200.241.146.213',973446428);
INSERT INTO t_anuncio VALUES ('AOVJTQjEZspSdG1','AEF14hCdGhu5q1u',1,15,'2000-11-05','2000-11-15','Exc. Pre�o\r\n\r\nLigue J�: 991-1500',NULL,10,0.00,4,'Chroma e star tac','200.241.146.213',973446509);
INSERT INTO t_anuncio VALUES ('AOfrmzMfA38BWpS','AEF14hCdGhu5q1u',1,14,'2000-11-05','2000-11-15','Voc� s� paga ap�s ter ligad�.\r\n\r\nLigue: 962-0123/ 328-1480',NULL,10,0.00,4,'Telefones (334-324-314-352-354)','200.241.146.213',973446628);
INSERT INTO t_anuncio VALUES ('AOJxrwBONSzUfAV','AEF14hCdGhu5q1u',1,14,'2000-11-05','2000-11-15','Pague ligado!!!\r\n\r\nContato: 328-1480/ 962-0123\r\n',NULL,10,0.00,4,'Linhas(336-326)','200.241.146.213',973446716);
INSERT INTO t_anuncio VALUES ('AOmqJG1mHaZsNgB','AEF14hCdGhu5q1u',1,15,'2000-11-05','2000-11-15','Linha BCP Plano b�sico, ap super conservado.\r\n\r\nContato: 223-8441',NULL,10,100.00,4,'Nokia 2160','200.241.146.213',973446832);
INSERT INTO t_anuncio VALUES ('AOOZifAVgJG1mPi','AEF14hCdGhu5q1u',1,15,'2000-11-05','2000-11-15','Linha BCP Plano b�sico, ap super conservado.\r\n\r\nContato: 223-8441',NULL,10,100.00,4,'Nokia 2160','200.241.146.213',973446833);
INSERT INTO t_anuncio VALUES ('AOk25qL6z27A3Ef','AEF14hCdGhu5q1u',1,14,'2000-11-05','2000-11-15','Tratar: 357-1037/ 977-1247/ 324-6472',NULL,10,300.00,4,'Linha ( 342)','200.241.146.213',973446925);
INSERT INTO t_anuncio VALUES ('AOj1chK5y16rUnI','AEF14hCdGhu5q1u',1,14,'2000-11-05','2000-11-15','Tratar: 357-1037/ 977-1247/ 324-6472',NULL,10,300.00,4,'Linha ( 342)','200.241.146.213',973446926);
INSERT INTO t_anuncio VALUES ('AOm1W1mPaDIlqDY','AEF14hCdGhu5q1u',1,14,'2000-11-05','2000-11-15','Pref. (320-324-354-352-338-326-351-353 ETC...)\r\n\r\nTratar: 326-8388',NULL,10,250.00,4,'Instalado com garantia','200.241.146.213',973447024);
INSERT INTO t_anuncio VALUES ('AOr7anIbEROhC5A','AEF14hCdGhu5q1u',1,14,'2000-11-05','2000-11-15','Pref. (320-324-354-352-338-326-351-353 ETC...)\r\n\r\nTratar: 326-8388',NULL,10,250.00,4,'Instalado com garantia','200.241.146.213',973447024);
INSERT INTO t_anuncio VALUES ('AOilwBWpC5anQjE','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Completa na Jati�ca. �timo pre�o.\r\n\r\nTratar: 221-9982',NULL,10,0.00,7,'Barraca de Sorvete','200.241.146.213',973447246);
INSERT INTO t_anuncio VALUES ('AOYqtG1mPinuHav','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Semi-novo de 24000 Btus, marca Springer, e Xerox 5009 semi nova. \r\n\r\nTratar com Sueli: 327-6000',NULL,10,0.00,22,'Ar condicionado','200.241.146.213',973447400);
INSERT INTO t_anuncio VALUES ('AONBUZ4pSliDYrU','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Em 5 veses, aceito todos os cart�es.\r\n\r\nTratar: 991-0799',NULL,10,0.00,7,'Abadas (todos os blocos)','200.241.146.213',973447498);
INSERT INTO t_anuncio VALUES ('AOvjCP2vYjwZsVw','AEF14hCdGhu5q1u',5,0,'2000-11-05','2000-11-15','Expositor de frios, banca de m�rmore para a�ougue, moedor de carne, amaciador de carne, etiquetadora toledo 15Kg, fatiador de queijo, freezer com portas de vidro, etc...\r\n\r\nTratar: 231-4468',NULL,10,0.00,7,'Expositor carne','200.241.146.213',973447677);
INSERT INTO t_anuncio VALUES ('AOo3eH2F0ty1uXi','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Bem conservado.\r\n\r\nTratar: 328-3284/ 359-2106',NULL,10,0.00,22,'2 Ar condicionados de 18 000 btus','200.241.146.213',973447762);
INSERT INTO t_anuncio VALUES ('AOHuF0lG9CH2DeH','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Com cd Philips\r\n\r\nTratar: 223-7158',NULL,10,150.00,22,'Som Philips','200.241.146.213',973447847);
INSERT INTO t_anuncio VALUES ('AOymxKPanI9mzU7','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Com 2 bocas de 15\" e som 3 em 1 philips.\r\n\r\nTratar: 241-2618',NULL,10,0.00,22,'Caixote de som','200.241.146.213',973447939);
INSERT INTO t_anuncio VALUES ('AOgV6rMfAb8BWx0','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Mesas redondas.\r\n\r\ntratar:327-1756',NULL,10,0.00,7,'10 jogos de mesas para bar','200.241.146.213',973448019);
INSERT INTO t_anuncio VALUES ('AOfXS5y1C5iDeHc','AEF14hCdGhu5q1u',1,14,'2000-11-05','2000-11-15','Para 4 linhas e 16 ramais.\r\n\r\nTatar: 972-9342',NULL,10,0.00,4,'Central telef�nica SIEMENS','200.241.146.213',973448125);
INSERT INTO t_anuncio VALUES ('AOacDYbEZsF0tWp','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','8 cubas, 48 jogos de talheres, 2 mesas c/ 4 cadeiras.\r\n\r\nTratar: 9760010/ 981-4740',NULL,10,0.00,7,'Self service','200.241.146.213',973448235);
INSERT INTO t_anuncio VALUES ('AOJ1W1mPiLIbwZA','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Tratar: 9737041',NULL,10,0.00,22,'Ar condicionado 18000 Btus','200.241.146.213',973448303);
INSERT INTO t_anuncio VALUES ('AObBMYtOhKzUfIb','AEF14hCdGhu5q1u',1,20,'2000-11-05','2000-11-15','S�rie: 500.600.700.800 R$ 32,00\r\nCannon bc 02/ bc 20 R$32,00\r\n\r\nComprar:327-8485/ 9311-8479',NULL,10,0.00,19,'Cartuchos HP','200.241.146.213',973453462);
INSERT INTO t_anuncio VALUES ('AOLbPMRcpSHUfsN','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Semi-nova.\r\n\r\nTratar: 977-0832',NULL,10,270.00,22,'TV 20 pol','200.241.146.213',973448371);
INSERT INTO t_anuncio VALUES ('AOJMrwJcx0PaD6r','AEF14hCdGhu5q1u',1,0,'2000-11-05','2000-11-15','Toda revisada, em perfeito estado.\r\n\r\nTratar: 973-0026/ 221-1981',NULL,10,4500.00,22,'Central de Ar Condionado  150000 BTUS','200.241.146.213',973448557);
INSERT INTO t_anuncio VALUES ('AOmEzERcxKzU7AV','AEF14hCdGhu5q1u',5,24,'2000-11-05','2000-11-15','Tratar: 324-2040/ 998-0427',NULL,10,0.00,19,'Grava-se CDs','200.241.146.213',973453565);
INSERT INTO t_anuncio VALUES ('AOMxIVgBWpuPavQ','AEF14hCdGhu5q1u',1,21,'2000-11-05','2000-11-15','Em bom estado.\r\n\r\nTratar: 337-3353/ 268-1119',NULL,10,0.00,19,'586 c/ 32 Ram','200.241.146.213',973453680);
INSERT INTO t_anuncio VALUES ('AOD3Y3oRkNKdrUv','AEF14hCdGhu5q1u',5,24,'2000-11-05','2000-11-15','Manuten��o de micros, instala��o de programas, Internet Gr�tis, digita��o, cart�es, aulas particulares. Atendemos em domic�lio e empresas. Inclusive domingos e feriados.\r\n\r\nTratar: 354-6479/ 9371-2914',NULL,10,0.00,19,'Assist�ncia t�cnica 24H','200.241.146.213',973454225);
INSERT INTO t_anuncio VALUES ('AOgcnsxSlOvI3nI','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','�timo Ponto Comercial na Fernandes Lima para restaurante, farm�cia. Tr.231-8866',NULL,10,0.00,2,'Ponto Comercial','200.199.95.6',973536066);
INSERT INTO t_anuncio VALUES ('AOxBvsxSdyvQbwR','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','Aluga-se salas para escrit�rio,consult�rio. Tr. 223-2079',NULL,10,0.00,2,'SALAS','200.199.95.6',973536245);
INSERT INTO t_anuncio VALUES ('AOQEHMRWhurERcp','AEF14hCdGhu5q1u',1,13,'2000-11-06','2000-11-16','Salas comerciais prontas com vista para o mar, audit�rio, papelaria. Em 60x Tr.354-9405; 991-7110',NULL,10,0.00,2,'SALAS COMERCIAIS','200.199.95.6',973536588);
INSERT INTO t_anuncio VALUES ('AOQ7aDYrUnsN8B4','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','Alugo sala no Edf Breda, 5o. andar. Tr.9311-8849; 3261933. ',NULL,10,120.00,2,'SALA','200.199.95.6',973536830);
INSERT INTO t_anuncio VALUES ('AOL4fsNgRkx0tWp','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','Aluga-se loja,na R.Ponte de Miranda, atr�s da Assembl�ia, Tr.221-6291; 981-0067',NULL,10,0.00,2,'ALUGA-SE LOJA','200.199.95.6',973537076);
INSERT INTO t_anuncio VALUES ('AOuLOTez2nsN8B4','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','Aluga-se consult�rio m�dico e odontol�gico, equipado e mobiliado. R. Dias Cabral, Centro Tr. 221-6291; 981-0067.',NULL,10,0.00,2,'ALUGA-SE CONSULT�RIO','200.199.95.6',973537343);
INSERT INTO t_anuncio VALUES ('AOcgN0lG9uzUfAV','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','Alugo Ponto Comercial com dep�sito (casa) na R. Comendador Cala�a, Po�o. Tr.221-2388.  ',NULL,10,0.00,2,'PONTO COMERCIAL','200.199.95.6',973538021);
INSERT INTO t_anuncio VALUES ('AOPJEZcF8tqTeBW','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-07','Aluga-se salas com wc. 24 e 32m2. Bem localizadas. Tr. 972-1092.',NULL,1,0.00,2,'SALAS','200.199.95.6',973538470);
INSERT INTO t_anuncio VALUES ('AObZ2fkF0tiLYjE','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','Ponto para lava jato com ou sem maquin�rio e ponto para bar e lanchonete. Tr. 982-3670. ',NULL,10,0.00,2,'PONTO PARA LAVA JATO','200.199.95.6',973539125);
INSERT INTO t_anuncio VALUES ('AOh7ivA3wRO9uPa','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','Aluga-se 2 lojas, P�. Sta. Tereza, R.Santo Ant�nio de esquina. Servindo para farm�cia, mercadinho, etc. Tr. 221-6291; 981-0067. ',NULL,10,0.00,2,'AlUGA-SE LOJAS','200.199.95.6',973539388);
INSERT INTO t_anuncio VALUES ('AOOBoBOhC5UfA3o','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-07','Alugam-se �timas salas para com�rcio e escrit�rio em geral. Tr.357-3256; 996-0040; 997-2495. ',NULL,1,0.00,2,'Alugo Salas','200.199.95.6',973539693);
INSERT INTO t_anuncio VALUES ('AOwsvAVgJ4pK5yT','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Esqui em bom estado.\r\n\r\nTratar: 327-8232/ 996-9369',NULL,10,200.00,7,'Ski','200.241.146.213',973639050);
INSERT INTO t_anuncio VALUES ('AO482ZsVoROhKdG','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','Alugam-se �timas salas para com�rcio e escrit�rio em geral. Tr.357-3256; 996-0040; 997-2495. ',NULL,10,0.00,2,'SALAS PARA COM�RCIO','200.199.95.6',973539777);
INSERT INTO t_anuncio VALUES ('AOY1c9C5y16zUnI','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','Salas p/ R$ 150,00 para escrit�rio/consult�rio com amplo estacionamento. Acesso para 2 avenidas. Tr,982-3506.',NULL,10,0.00,2,'SALAS','200.199.95.6',973540064);
INSERT INTO t_anuncio VALUES ('AOMknsF0lGnI3oJ','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','Salas p/ R$ 150,00 para escrit�rio/consult�rio com amplo estacionamento. Acesso para 2 avenidas. Tr,982-3506.',NULL,10,150.00,2,'Salas p/ esc./Consult.','200.199.95.6',973540127);
INSERT INTO t_anuncio VALUES ('AO2CNKXiD63wJcx','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','Aluga-se/vende-se ponto comercial aonde funcionava o Divina Gula.Tr.355-2368,355-2216. ',NULL,10,0.00,2,'PONTO COMERCIAL','200.199.95.6',973540359);
INSERT INTO t_anuncio VALUES ('AOwQTmH2vYF8tO9','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','Alugo pousada na Jo�o Davino, 246 Mangabeiras e vendo todos os M�veis e Eletrodom�sticos. Tr.223-3628; 973-4523',NULL,10,0.00,2,'ALUGO POUSADA','200.199.95.6',973540709);
INSERT INTO t_anuncio VALUES ('AOGrCjwJ4hmzUnI','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','Aluga-se pr�dio com 1100m2 de �rea, com estacionamento no subsolo e central de ar. Tr.325-1575. ',NULL,10,0.00,2,'ALUGA-SE PR�DIO C/ 1100M2 DE �REA','200.199.95.6',973540961);
INSERT INTO t_anuncio VALUES ('AO1t7sxK5iDYbwR','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','R. Durval Guimar�es, Leonardo da Vinci, sala para escrit�rio com recep��o e wc, 30m2. Tr.977-5647; 355-8094.',NULL,10,0.00,2,'SALA','200.199.95.6',973541247);
INSERT INTO t_anuncio VALUES ('AODHuzMfA38lOhC','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','Aluga-se sala na Ponta Verde para massagem. Tr.973-5089; 337-2979 Nete.',NULL,10,0.00,2,'SALA PARA MASSAGENS','200.199.95.6',973541585);
INSERT INTO t_anuncio VALUES ('AOnFIV8B4FK5y1u','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','Alugo Ponto Comercial, de esquina, bom para Restaurante, Sorveteria, auto-pe�as, papelaria e material de constru��o. Tr.235-2767; 971-0218. ',NULL,10,0.00,2,'Super Ponto','200.199.95.6',973541900);
INSERT INTO t_anuncio VALUES ('AOt1WhK5y16z2vQ','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','Aluga-se excelente Ponto Comercial. Tr. 328-2145(fins de semana ou � noite).',NULL,10,0.00,2,'PONTO COMERCIAL','200.199.95.6',973542088);
INSERT INTO t_anuncio VALUES ('AOsgrwZkF8dqLeH','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','Papelaria em funcionamento, excelente oportunidade. Tr. 973.8176',NULL,10,0.00,2,'PAPELARIA  EM  FUNCIONAMENTO','200.199.95.6',973542319);
INSERT INTO t_anuncio VALUES ('AOJDyL6rMfcxSlG','AEF14hCdGhu5q1u',2,13,'2000-11-06','2000-11-16','Aluga-se pr�dio na Paju�ara com 3 pavimentos, 27 salas c/ wc, 1 audit�rio, 2 sal�es, 1 sala para reuni�o, centraltelef�nica, elevador, estacionamento e subesta��o de energia, Tr. 983-5377; 981-1017.',NULL,10,0.00,2,'ALUGA-SE PR�DIO','200.199.95.6',973542624);
INSERT INTO t_anuncio VALUES ('AOkfanA3wRWpKdy','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Tratar:328-4925',NULL,10,0.00,7,'Passaporte na serraria','200.241.146.213',973640119);
INSERT INTO t_anuncio VALUES ('AOquMZcF8JG1mPa','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Tratar: 996-4730',NULL,10,0.00,7,'Calend�rio, folhinhas e bon�s','200.241.146.213',973640177);
INSERT INTO t_anuncio VALUES ('AO2knIFgJchKdGh','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Arquivo, computador, telefone, impressora, mesa, cadeira, fog�o, geladeira, arm�rios, etc..\r\n\r\nTratar: 231-6639/ 327-7940',NULL,10,0.00,7,'M�veis de  escrit�rio','200.241.146.213',973640324);
INSERT INTO t_anuncio VALUES ('AOZUX2fAVgdqLez','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Direto da fran�a e Italia.\r\n\r\nTratar: 372-3253',NULL,10,0.00,7,'Produtos de beleza','200.241.146.213',973640406);
INSERT INTO t_anuncio VALUES ('AOKMXavYz2fIbE7','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Eles s�o biderecionais\r\n\r\nTratar: 9302-3167',NULL,10,0.00,7,'R�dios motorolas','200.241.146.213',973640498);
INSERT INTO t_anuncio VALUES ('AO6xANSdG96r2nQ','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','8300 Btus\r\n\r\nTratar: 973-8233',NULL,10,0.00,7,'Ar condicionado Elgim','200.241.146.213',973640581);
INSERT INTO t_anuncio VALUES ('AOzl8cpCPaZcxS5','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Aceitamos roupas sociais, masc. e fem. em consigna��o\r\n\r\nTratar: 223-7689',NULL,10,0.00,7,'Roupas em cosigna��o','200.241.146.213',973640713);
INSERT INTO t_anuncio VALUES ('AOK2dqTeH27sVgJ','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Tratar: 973-4047',NULL,10,0.00,7,'Seladora p/ saco pl�stico','200.241.146.213',973640792);
INSERT INTO t_anuncio VALUES ('AOgxIN8tO96rM7s','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Tratar: 973-4047',NULL,10,0.00,7,'Caloi Al�mino','200.241.146.213',973640875);
INSERT INTO t_anuncio VALUES ('AO2uxKPaDYVgBWh','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Tratar: 973-4047',NULL,10,0.00,7,'Micro-Sistem','200.241.146.213',973640999);
INSERT INTO t_anuncio VALUES ('AOMU5afIbERcF0t','AEmS3wR4F8dG9Kd',1,31,'2001-04-13','2001-04-18','Isso mesmo! Promo��o imperd�vel! V�lido at� sexta-feira dia 25 de maio! Corra e aproveite que temos poucas unidades!',NULL,5,289.00,19,'HD  20GB SAMSUNG por apenas R$ 289,00!!!','200.191.61.26',987176760);
INSERT INTO t_anuncio VALUES ('AOI0VKHwtpYV0XU','AEmS3wR4F8dG9Kd',1,32,'2001-05-20','2001-05-30','Isso mesmo! Pente de mem�ria DIMM PC100 de 32MB por apenas R$ 47,00!!! Promo��o v�lida at� sexta-feira dia 25 de maio. Ligue e garanta j� o seu!','jpg',10,47.00,19,'Mem�riA 32MB DIMM por apenas R$ 47,00!!!','200.227.200.116',990350995);
INSERT INTO t_anuncio VALUES ('AOt9chuPansF0lG','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Impec�vel, original e divido em 2x',NULL,10,450.00,7,'Ar condicionado 10.500 Btus','200.241.146.213',973641076);
INSERT INTO t_anuncio VALUES ('AOxWfsVgB49uPiT','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Tratar: 314-2507',NULL,10,0.00,7,'M�quina de solda','200.241.146.213',973641250);
INSERT INTO t_anuncio VALUES ('AOUQ96zUfIpK5qL','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Tecido importado, pre�o de revenda.\r\n\r\nTratar: 338-5063/ 338-4869',NULL,10,0.00,7,'Colchas Solt./ casal','200.241.146.213',973641362);
INSERT INTO t_anuncio VALUES ('AOuGZ3oJ4xuXaDY','AEF14hCdGhu5q1u',6,0,'2000-11-07','2000-11-17','Tratar: 994-1168',NULL,10,0.00,7,'Aqu�rio grande','200.241.146.213',973641478);
INSERT INTO t_anuncio VALUES ('AOBDyDYrM7cx0lO','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Em bom estado.\r\n\r\nTratar: 979-6346/ 337-2214',NULL,10,0.00,7,'Fax Toshiba','200.241.146.213',973641581);
INSERT INTO t_anuncio VALUES ('AO0NIN8tWhmHaD6','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Tratar: 324-4399',NULL,10,0.00,7,'Capota de fibra F-1000','200.241.146.213',973641702);
INSERT INTO t_anuncio VALUES ('AOJTyDQ30lMQ3gt','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Semi-industrial, frita batata tipo: chips e palha, e seladora reta.\r\n\r\n\r\nTratar: 359-6059',NULL,10,0.00,7,'M�quina p/ frituras de batata','200.241.146.213',973641936);
INSERT INTO t_anuncio VALUES ('AOVRU7cx0l2nIbw','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Esmaltec, 2 tampas de vidro\r\n\r\nTratar: 982-7894',NULL,10,0.00,7,'Freezer horiz. 380L','200.241.146.213',973642017);
INSERT INTO t_anuncio VALUES ('AOhrmrM7sNS5qT6','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Mod. 104\r\n\r\nTratar: 982-7894',NULL,10,0.00,7,'Balan�a Balmak','200.241.146.213',973642091);
INSERT INTO t_anuncio VALUES ('AOLjCz2vYzE7AbE','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','2 caixas c/ 2 alto falantes e 2 super tweeter, cada.\r\n\r\nTratar: 334-3120',NULL,10,0.00,7,'Amplificador CSR','200.241.146.213',973642231);
INSERT INTO t_anuncio VALUES ('AOfjCH2nQj8tO9u','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Todos os estilos, voz e teclado.\r\n\r\nTratar: 997-0200/ 336-4105',NULL,10,0.00,7,'Toco em festas','200.241.146.213',973642381);
INSERT INTO t_anuncio VALUES ('AOKMXcpKdGLeHaD','AEiJEJW9uHwZcpC',1,9,'2001-04-12','2001-04-22','CASA C/GARAG.3QTS SENDO 1 SU�TE SALA AMPLA, WC SOCIAL AREA DE SERV.�REA TERR.6X25',NULL,10,19000.00,2,'VENDO CASA NA RUA ROTARY-TAB.NOVO','200.199.56.189',987080945);
INSERT INTO t_anuncio VALUES ('AO6MOTezUvAVgJc','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','TRATAR: 976-1540',NULL,10,0.00,7,'Locadora c/ 500 fitas','200.241.146.213',973642552);
INSERT INTO t_anuncio VALUES ('AO5fiDQbEROhK5y','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Tratar: 325-7606',NULL,10,0.00,7,'Teclado YAMAHA PSR 190','200.241.146.213',973642883);
INSERT INTO t_anuncio VALUES ('AO51GDIN8lafsN0','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Tratar: 325-7606',NULL,10,0.00,25,'Teclado Yamaha PSR 190','200.241.146.213',973642947);
INSERT INTO t_anuncio VALUES ('AOa69mHaDCqLYrM','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Tratar: 325-7606',NULL,10,0.00,7,'Freezer Vertical Frigidare 270L','200.241.146.213',973643050);
INSERT INTO t_anuncio VALUES ('AOOe9XavQ30lyT6','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Tratar: 325-7606',NULL,10,0.00,7,'V�deo K-7 Philips','200.241.146.213',973643115);
INSERT INTO t_anuncio VALUES ('AOjfinIbwR4pSdG','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Tratar: 325-7606',NULL,10,0.00,7,'Ar cond. 10.000 BTus','200.241.146.213',973643160);
INSERT INTO t_anuncio VALUES ('AOyWZcpKdyDYr2n','AEF14hCdGhu5q1u',2,9,'2000-11-07','2000-11-17','Alugo �tima casa, com 6 quartos, piscina, em Paripueira, mobiliada, por temporada.',NULL,10,0.00,2,'CASA DE PRAIA','200.199.95.6',973643619);
INSERT INTO t_anuncio VALUES ('AOaOZGLY3gXavIN','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Mede 1,5 mts\r\n\r\nTratar: 231-6154/ 9371-0202',NULL,10,0.00,7,'Balc�o de Frios','200.241.146.213',973643658);
INSERT INTO t_anuncio VALUES ('AOQ0FCPUR4LY3gd','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Cobra Ataque\r\n\r\nTratar: 325-5994/ 972-9871',NULL,10,0.00,7,'Espingarda Submar.','200.241.146.213',973643984);
INSERT INTO t_anuncio VALUES ('AOFWZO9uPaJOhuX','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Novos na embalagem\r\n\r\nTratar: 231-5530/ 998-3700',NULL,10,0.00,7,'Alto falantes JBL 15 pol.','200.241.146.213',973644158);
INSERT INTO t_anuncio VALUES ('AOHvG1ePq1ePiTu','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Tratar: 325-2215',NULL,10,0.00,7,'TV 29 Pol. ( Sony)','200.241.146.213',973644335);
INSERT INTO t_anuncio VALUES ('AOAorotO9urEZkF','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Tratar: 325-2215',NULL,10,0.00,7,'Ar condicionado 7.500 Btus','200.241.146.213',973644509);
INSERT INTO t_anuncio VALUES ('AOtTW1erM7W9uXa','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Semi-novo\r\n\r\nTratar: 325-3138/ 981-4765',NULL,10,0.00,7,'Equipamento odontol�gico','200.241.146.213',973644637);
INSERT INTO t_anuncio VALUES ('AOTtMZkVoZWx0B4','AEF14hCdGhu5q1u',1,21,'2000-11-07','2000-11-17','Completo e impressora\r\n\r\nTratar: 235-2918/ 991-0586',NULL,10,920.00,19,'Pentium 120 mhz, 16 Ram','200.241.146.213',973644863);
INSERT INTO t_anuncio VALUES ('AOV7ifA3gBG1mPi','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Novo na caixa, 6 head\r\n\r\nTratar: 235-3130',NULL,10,350.00,7,'Video panasonic','200.241.146.213',973644960);
INSERT INTO t_anuncio VALUES ('AO9X85ivtyfAN8t','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Todos os tipos direto da f�brica\r\n\r\nTratar: 981-7185/ falar c/ Cl�udio',NULL,10,0.00,7,'Balan�a Filizola','200.241.146.213',973645153);
INSERT INTO t_anuncio VALUES ('AOrm1mH2vY3oRcF','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Em f�rmica e granito\r\n\r\nTratar: 976-7291',NULL,10,0.00,7,'Arm�rio Odontol�gico','200.241.146.213',973645497);
INSERT INTO t_anuncio VALUES ('AOvruzUnI3K5y1m','AEF14hCdGhu5q1u',2,13,'2000-11-08','2000-11-18','Farm�cia. Alugo sala comercial com prateleiras. Tr no local 324-1351; 2237389',NULL,10,0.00,2,'POSTO SANTA L�CIA','200.199.95.6',973710542);
INSERT INTO t_anuncio VALUES ('AOqa49mHU7O1ezM','AEvyBG1uH2J4pKX',5,0,'2001-05-09','2001-05-19','Preciso de Duplas ou Individual, Adultos ou Infantil. Iniciantes. Que realmente gostem de cantar. Para qualquer evento. Ligue: (0xx11) 4675-7581. Das 12 �s 20 hs.',NULL,10,0.00,25,'CANTORAS E CANTORES','200.211.160.40',989384760);
INSERT INTO t_anuncio VALUES ('AOVtwB4hK52fA3w','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Tratar: 974-9947/ 2218196',NULL,10,0.00,14,'Honda Cg 125cc','200.241.146.213',973647220);
INSERT INTO t_anuncio VALUES ('AOX1W1myLeboRcp','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Tratar: 974-9947/ 221-8196',NULL,10,0.00,14,'Titan KS','200.241.146.213',973647270);
INSERT INTO t_anuncio VALUES ('AOIwrEJkNgYjMfR','AEvyBG1uH2J4pKX',5,0,'2001-05-09','2001-05-19','Preciso de Duplas ou Individual, Adultos ou Infantil. Iniciantes. Que realmente gostem de cantar. Para qualquer evento. Ligue: (0xx11) 4675-7581. Das 12 �s 20 hs.',NULL,10,0.00,7,'CANTORAS E CANTORES','200.211.160.40',989384679);
INSERT INTO t_anuncio VALUES ('AO3ZinI3wZO9uXi','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','Pre�o a combinar\r\n\r\nTratar: 973-4047',NULL,10,0.00,14,'Honda Biz c/ 10 pagas','200.241.146.213',973647509);
INSERT INTO t_anuncio VALUES ('AOl8boJcx0XqDYr','AEF14hCdGhu5q1u',1,0,'2000-11-07','2000-11-17','10 Meses\r\n\r\nTratar: 235-1861',NULL,10,0.00,14,'XR 200R','200.241.146.213',973647704);
INSERT INTO t_anuncio VALUES ('AOtY16jER4LQ3oB','AEwm1mHaLerUnYr',5,0,'2001-05-08','2001-05-18','Empresa multinacional em fase de expans�o procura profissionais de Marketing e RH para desenvolver  e-business utilizando a Web.\r\nSe interessar contactar: (85) 9941 6717 \r\nhttp://change.to/anewlife\r\nHenrique Autran \r\n',NULL,10,0.00,7,'HBL International','200.217.143.155',989375454);
INSERT INTO t_anuncio VALUES ('AOYdgtO9CPwJWhK','AEwm1mHaLerUnYr',5,0,'2001-05-08','2001-05-18','Empresa multinacional em fase de expans�o procura profissionais de Marketing e RH para desenvolver  e-business utilizando a Web.\r\nSe interessar contactar: (85) 9941 6717 \r\nhttp://change.to/anewlife\r\nHenrique Autran \r\n',NULL,10,0.00,8,'HBL International','200.217.143.155',989375394);
INSERT INTO t_anuncio VALUES ('AOKOdivQV85anQ3','AEIc7sN8B49C5y1',3,0,'2001-05-11','2001-05-21','ENXUTA FUTURA MASTER II, SEMI NOVA R$ 350,00 OU TROCO POR ALGO DE MEU INTERESSE (MATERIAIS PARA CONSTRU��O)',NULL,10,0.00,22,'MAQUINA DE  LAVAR','200.199.95.127',989599930);
INSERT INTO t_anuncio VALUES ('AOymF0dG1CHaDeP','AEeVezMfIbgJ4F0',1,21,'2001-05-13','2001-05-23','Vendo Pentium 200mmx, 32mb mem�ria, Hd 1.2gb, Cd Rom 52x,Som,Fax Modem, Teclado, Mouse, Cx.Som, Monitor Color 14\".\r\n ',NULL,10,650.00,19,'VENDO PENTIUM 200MMX COMPLETO','200.199.58.52',989761302);
INSERT INTO t_anuncio VALUES ('AOA696jgJ4L5iDY','AE7dwJ4x0BG9u5y',1,23,'2001-05-06','2001-05-16','Vendo uma impressora Cannon mod. 250, semi-nova. Boa impress�o e bastante econ�mica. Interessados entrar em contato comigo. Obrigado.',NULL,10,120.00,19,'VENDO IMPRESSORA SEMI-NOVA','200.199.50.218',989118991);
INSERT INTO t_anuncio VALUES ('AOHDG1uXy1ePq3o','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Prof. Luiz Luna, Refor�o escolar. Ensino fundamental (5a. a 8a. s�rie). Todas as mat�rias.',NULL,10,0.00,10,'REFOR�O ESCOLAR     5@- 8@ (Luna)','200.199.95.6',973649132);
INSERT INTO t_anuncio VALUES ('AOs038dyLYN0dyT','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Carlos ensina refor�o: Matem�tica,F�sica (1o. e 2o. grau. N�o deixe para �ltima hora.\r\nFone 338-1092 ',NULL,10,0.00,10,'REFOR�O ESCOLAR                Prof. Cralos','200.199.95.6',973649483);
INSERT INTO t_anuncio VALUES ('AOtT416rUf4pKdG','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Refor�o escolar em domic�lio da 1a. a 5a. s�ries. Tr.977-0948',NULL,10,0.00,10,'REFOR�O ESCOLAR       1@ - 5@ S�r','200.199.95.6',973649661);
INSERT INTO t_anuncio VALUES ('AOjZ27kx0liDYjM','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Temos cursos t�cnicos em patologia cl�nica, radiologia, assistente de farm�cia e consult�rio odontol�gico. Matriculas abertas.\r\nFone 336-7731',NULL,10,0.00,10,'LABORAT�RIO ESCOLA','200.199.95.6',973649870);
INSERT INTO t_anuncio VALUES ('AOs0boJkNglOhKd','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Matem�tica e portugu�s para todos os n�veis. Professora formada. Magda. Fone 9304-3000',NULL,10,0.00,10,'MATEM�TICA E PORTUGU�S','200.199.95.6',973650129);
INSERT INTO t_anuncio VALUES ('AO4KFCH2fApCPan','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Espanhol/ingl�s. Matr�culas abertas. Professor especializado em Madrid. Tr. 221-5314,336-7456',NULL,10,0.00,10,'ESPANHOL E INGL�S','200.199.95.6',973650604);
INSERT INTO t_anuncio VALUES ('AOl0jgJcF8dy1uP','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Espanhol em tr�s meses. Professores nativos. Cursos intensivos, nomais, com certificado.\r\nTr.325-1478; 374-0087',NULL,10,0.00,10,'ESPANHOL  EM  TR�S MESES','200.199.95.6',973650833);
INSERT INTO t_anuncio VALUES ('AOitoJ4FgJcF8Bc','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-09','Aula particular - Matematica, F�sica e Qu�mica. 1o. e 2o. graus. Fones 235-4489; \r\n9302-3427',NULL,1,0.00,10,'AULA PARTICULAR','200.199.95.6',973651166);
INSERT INTO t_anuncio VALUES ('AOVBEAF0tWT6rM7','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Teclado, viol�o, guitarra e cavaquinho. R$ 40,00 por m�s, em domic�lio. Fone 997-0200;\r\n336-4105. ',NULL,10,0.00,10,'TECLADO, VIOL�O E GUITARRA','200.199.95.6',973651417);
INSERT INTO t_anuncio VALUES ('AOnF6raLm5aLu5y','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','(Vizinho ao Divina Gula) Casa de Massas',NULL,10,0.00,16,'Pozzuoli ( almo�o executivo R$6,90)','200.241.146.213',973672463);
INSERT INTO t_anuncio VALUES ('AOmozM7AVotWhK5','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Mo�as de boa apar�ncia.\r\n\r\nTratar: 3032-4040/ 326-8309',NULL,10,0.00,8,'Demonstradora de produtos (Selecionamos)','200.241.146.213',973672635);
INSERT INTO t_anuncio VALUES ('AOLHK5y1nINgBcF','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Capital e interior, seg.grau completo e boa caligrafia. (5 -vagas)\r\n\r\nTratar: 989-1015',NULL,10,0.00,8,'Vendedora Externa','200.241.146.213',973672783);
INSERT INTO t_anuncio VALUES ('AO9cnkFoJsx0t4x','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Que durma no emprego.\r\n\r\nTratar: 326-9787/ 3032-1258',NULL,10,0.00,8,'Cozinheira, bab�  (Precisa-se)','200.241.146.213',973672854);
INSERT INTO t_anuncio VALUES ('AOnwzMfIbMJ4x0t','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Precisa de contrabaixista p/ todos os ritmos\r\n\r\nTratar: 328-1064/ 979-5413',NULL,10,0.00,8,'Banda de bailes','200.241.146.213',973672935);
INSERT INTO t_anuncio VALUES ('AOyexmPaD6VgJ4x','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','C/ experi�ncia e refer�ncia\r\n\r\nTratar: 9303-3824/ 359-2371',NULL,10,0.00,8,'Precisa-se de cabeleireiro e manicure)','200.241.146.213',973673023);
INSERT INTO t_anuncio VALUES ('AOT5S4G9C5iD6za','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Precisa-se de costureira\r\n\r\nTratar: 327-0093',NULL,10,0.00,8,'Art vest','200.241.146.213',973673085);
INSERT INTO t_anuncio VALUES ('AOhtEQkNgJNpStW','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Planos de sa�de e odont�logicos\r\n\r\nTratar:326-8382',NULL,10,0.00,8,'Ortoclin  (precisa de vendedores)','200.241.146.213',973673171);
INSERT INTO t_anuncio VALUES ('AOSEj8cpCHotGLY','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Curr�culos p/ ladeirado Brito 122\r\n\r\nTratar: 326-8382',NULL,10,0.00,8,'Ortoclin ( precisa credenciar dentista)','200.241.146.213',973673261);
INSERT INTO t_anuncio VALUES ('AOQEzMnQrU7AbEf','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','N�o pagaram seus direitos? Dr.Roberto e Dr. Aristeu, resolvem.\r\n\r\nTRatar: 336-3411/ 223-7027',NULL,10,0.00,8,'Empregado!!! Voc� foi demitido??? (Advogados)','200.241.146.213',973673448);
INSERT INTO t_anuncio VALUES ('AOHuN05qTebwRkF','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Bab�s , arrumadeira, serv. gerais e etc...\r\n\r\nTratar: 221-0875',NULL,10,0.00,8,'Temos empregadas dom�sticas','200.241.146.213',973673529);
INSERT INTO t_anuncio VALUES ('AOlTOL6rEfcpSlG','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Bab�s , arrumadeira, serv. gerais e etc...\r\n\r\nTratar: 221-0875',NULL,10,0.00,8,'Temos empregadas dom�sticas','200.241.146.213',973673531);
INSERT INTO t_anuncio VALUES ('AOWKxCPaDYVgJ4x','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','C/ ref. e que durma no emprego\r\n\r\nTratar: 221-8008',NULL,10,0.00,8,'Necessito de bab�','200.241.146.213',973673615);
INSERT INTO t_anuncio VALUES ('AOC24x0dOiuPq2v','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','C/ ref. e que durma no emprego\r\n\r\nTratar: 221-8008',NULL,10,0.00,8,'Necessito de bab�','200.241.146.213',973673617);
INSERT INTO t_anuncio VALUES ('AOQRUfsVoRWhC5y','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','C/ �timas ref.\r\n\r\nTRatar: 351-5265',NULL,10,0.00,8,'Acompanhante de idosos','200.241.146.213',973673718);
INSERT INTO t_anuncio VALUES ('AOC9W9eH2vAVoR4','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','C/ �timas ref.\r\n\r\nTRatar: 351-5265',NULL,10,0.00,8,'Acompanhante de idosos','200.241.146.213',973673720);
INSERT INTO t_anuncio VALUES ('AOL2lyTmPinIbMn','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','C/ experi�ncia\r\n\r\nTratar: 328-3604',NULL,10,0.00,8,'Precisa-se de aux. de enfermagem','200.241.146.213',973673833);
INSERT INTO t_anuncio VALUES ('AOA7inIbwZWhK5y','AEF14hCdGhu5q1u',1,21,'2000-11-08','2000-11-18','Com grantia de 6 meses em 1+3 sem juros\r\n\r\nTratar: 357-5345/ 996-5847',NULL,10,0.00,19,'Computadores semi-novos','200.241.146.213',973674092);
INSERT INTO t_anuncio VALUES ('AO15SXivQbZcxK5','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Tratar: 241-9311',NULL,10,0.00,8,'Grava��o de CDs diversos','200.241.146.213',973674152);
INSERT INTO t_anuncio VALUES ('AOrZiDIjEZcx0t4','AEF14hCdGhu5q1u',5,23,'2000-11-08','2000-11-18','Tratar: 241-9311',NULL,10,0.00,19,'Impressora usada c/ garantia','200.241.146.213',973674205);
INSERT INTO t_anuncio VALUES ('AOeUc9mPiDAN8BW','AEF14hCdGhu5q1u',1,33,'2000-11-08','2000-11-18','Tratar: 241-9311',NULL,10,0.00,19,'Monitor usado com garantia','200.241.146.213',973674254);
INSERT INTO t_anuncio VALUES ('AOdZimHaD6bE7Ab','AEF14hCdGhu5q1u',1,32,'2000-11-08','2000-11-18','Tratar: 971-6609',NULL,10,0.00,19,'Pentes de mem�ria (simm. dimm)','200.241.146.213',973674331);
INSERT INTO t_anuncio VALUES ('AO1bmboJWpmjwR4','AEF14hCdGhu5q1u',1,31,'2000-11-08','2000-11-18','Tratar: 971-6603',NULL,10,0.00,19,'Hds usados com garantia','200.241.146.213',973674383);
INSERT INTO t_anuncio VALUES ('AOpGJ4pK5GL6z2v','AEF14hCdGhu5q1u',1,39,'2000-11-08','2000-11-18','Novos, �timos pre�os\r\n\r\nTratar: 328-3284/ 359-2106',NULL,10,0.00,19,'Leitor de c�d de barras','200.241.146.213',973674456);
INSERT INTO t_anuncio VALUES ('AOstwdiLQ3KPanI','AEF14hCdGhu5q1u',1,21,'2000-11-08','2000-11-18','Tratar: 983-1271/ 328-3440',NULL,10,0.00,19,'Pentium 100mhz 32 Ram','200.241.146.213',973674536);
INSERT INTO t_anuncio VALUES ('AOoi5avI3olGTez','AEF14hCdGhu5q1u',1,0,'2000-11-08','2000-11-18','Tratar: 983-1271',NULL,10,0.00,22,'Videok�','200.241.146.213',973674577);
INSERT INTO t_anuncio VALUES ('AOuilqLeHaZsVoJ','AEF14hCdGhu5q1u',1,21,'2000-11-08','2000-11-18','Kit multim�dia e muito mais...\r\n\r\nTratar: 998-5015\r\n',NULL,10,0.00,19,'Computador K-6 Completo','200.241.146.213',973674664);
INSERT INTO t_anuncio VALUES ('AOfjElG1ez7kF0l','AEF14hCdGhu5q1u',1,21,'2000-11-08','2000-11-18','Completo\r\n\r\nTratar: 353-3291/ 974-7832',NULL,10,800.00,19,'Pentium 200mhz 32 Ram','200.241.146.213',973674755);
INSERT INTO t_anuncio VALUES ('AO5SNSdGTmjwL6r','AEF14hCdGhu5q1u',1,21,'2000-11-08','2000-11-18','Tratar: 327-1144 Falar c/ Neto',NULL,10,0.00,19,'3 computadores pentium II 166mhz','200.241.146.213',973674827);
INSERT INTO t_anuncio VALUES ('AOwxAVgJuX2nYr2','AEF14hCdGhu5q1u',1,21,'2000-11-08','2000-11-18','Tratar: 327-6446/ 9371-6650',NULL,10,0.00,19,'Computador AMD Athlon 700mhz','200.241.146.213',973674999);
INSERT INTO t_anuncio VALUES ('AONd8dqDQb0drER','AEF14hCdGhu5q1u',1,23,'2000-11-08','2000-11-18','Impressora Epson\r\n\r\nTratar: 325-5772/ 963-5312',NULL,10,450.00,19,'Impressora Lx 300 (nova)','200.241.146.213',973675086);
INSERT INTO t_anuncio VALUES ('AOuTO1ezUn4hK5q','AEF14hCdGhu5q1u',1,21,'2000-11-08','2000-11-18','Hd 3Gb, Completo\r\n\r\nTratar: 325-4811/ 962-1807',NULL,10,0.00,19,'Pentium 166mhz','200.241.146.213',973675165);
INSERT INTO t_anuncio VALUES ('AO5h4boRkNS5G1u','AEF14hCdGhu5q1u',1,21,'2000-11-08','2000-11-18','Tratar: 327-3894\r\n\r\n',NULL,10,0.00,19,'2 computadores','200.241.146.213',973675235);
INSERT INTO t_anuncio VALUES ('AOdKV85qL6rM7sF','AEF14hCdGhu5q1u',1,23,'2000-11-08','2000-11-18','Tratar: 327-3894',NULL,10,0.00,19,'Impressora!!!!','200.241.146.213',973675281);
INSERT INTO t_anuncio VALUES ('AOCO7kqD6zE7A3w','AEF14hCdGhu5q1u',1,21,'2000-11-08','2000-11-18','Tratar: 324-6808/ 976-4559',NULL,10,750.00,19,'Pentium 166mhz (comp.)','200.241.146.213',973675350);
INSERT INTO t_anuncio VALUES ('AOY727sF0lGTmH2','AEF14hCdGhu5q1u',1,21,'2000-11-08','2000-11-18','Tratar: 324-6808/ 976-4559',NULL,10,750.00,19,'Pentium 166mhz (comp.)','200.241.146.213',973675351);
INSERT INTO t_anuncio VALUES ('AO3RhKPiTvjMfIb','AEF14hCdGhu5q1u',1,21,'2000-11-08','2000-11-18','Completo\r\n\r\nTratar: 9381-0878\r\n\r\n',NULL,10,550.00,19,'Pentium 166mhz','200.241.146.213',973675441);
INSERT INTO t_anuncio VALUES ('AO3mpuXiLeX2vQb','AEF14hCdGhu5q1u',1,21,'2000-11-08','2000-11-18','Completo\r\n\r\nTratar: 9381-0878\r\n\r\n',NULL,10,550.00,19,'Pentium 166mhz','200.241.146.213',973675442);
INSERT INTO t_anuncio VALUES ('AOb7insVgJ4pKdy','AEF14hCdGhu5q1u',1,21,'2000-11-08','2000-11-18','Completo supre novo\r\n\r\nTRATAR: 325-4247',NULL,10,0.00,19,'Pentium II 266mhz','200.241.146.213',973675528);
INSERT INTO t_anuncio VALUES ('AO624pC5y1YrUnQ','AEF14hCdGhu5q1u',1,22,'2000-11-08','2000-11-18','super novo, com 64 Ram placa DVD, Comp.\r\n\r\nTratar: 978-9146/ 327-7643',NULL,10,0.00,19,'Notebook Compaq K-6 2 500mhz','200.241.146.213',973675673);
INSERT INTO t_anuncio VALUES ('AOKEPU7cxKW9CXa','AEF14hCdGhu5q1u',1,24,'2000-11-08','2000-11-18','Instala��o, manuten��o, pe�as 24H\r\n\r\nTratar: 327-3246/ 9305-5486',NULL,10,0.00,19,'Conserto de micros','200.241.146.213',973675772);
INSERT INTO t_anuncio VALUES ('AOCwV8tG1ebwZkF','AEF14hCdGhu5q1u',1,0,'2000-11-08','2000-11-18','Ouro remido, fa�o qualquer neg�cio\r\n\r\nTRatar: 973-0026',NULL,10,0.00,11,'Vale das cascatas','200.241.146.213',973675929);
INSERT INTO t_anuncio VALUES ('AO5LWTerEZWhCXi','AEF14hCdGhu5q1u',1,0,'2000-11-08','2000-11-18','Fa�o qualquer neg�cio\r\n\r\nTratar: 973-0026',NULL,10,0.00,11,'Lindoya','200.241.146.213',973675978);
INSERT INTO t_anuncio VALUES ('AOQ0V8tO1mjE7Ib','AEF14hCdGhu5q1u',1,0,'2000-11-08','2000-11-18','Valor a acertar\r\n\r\nTratar:973-0026',NULL,10,0.00,11,'Moton�utica','200.241.146.213',973676053);
INSERT INTO t_anuncio VALUES ('AOGWuPav6zMfQr2','AEofqTmXqTYrUvY',1,0,'2001-05-05','2001-05-15','01 Ventilador Arno 40cm semi-novo. R$ 65,00\r\n01 Bomba d��gua WEG Mod. C660 1/3 cv. R$ 60,00',NULL,10,125.00,22,'Ventilador e bomba d��gua','200.199.56.247',989104538);
INSERT INTO t_anuncio VALUES ('AOqNsxKXankpK5i','AEofqTmXqTYrUvY',1,0,'2001-05-05','2001-05-06','Cadeira de balan�o, sof� 03 lugares, guarda roupa r�stico casal, guarda roupa solteiro, cama de solteiro, conjunto de sala c/ 06 cadeiras mais arca, faqueiro antigo, conjunto de lou�a em porcelana Real completo, 02 lustres de cristal.',NULL,1,0.00,27,'Vendo V�rios M�veis','200.199.56.247',989105422);
INSERT INTO t_anuncio VALUES ('AOYXCzERcpuz4hC','AEVEP2v6PqD6r2D',5,24,'2001-05-06','2001-05-16','Trabalho com Foto Digital Computadorizado, Foto para Home Page, �lbum Virtual e etc. C\\ alta defini��o. A C�mera Digital � a Sony DSC-D770 ,com zoom e capacidade de armazenar 100 Fotos por pente.','gif',10,0.00,19,'FOTO DIGITAL COMPUTADORIZADO','200.133.126.219',989177758);
INSERT INTO t_anuncio VALUES ('AOaknAF8BchKdOh','AExvG9mPaLI3wZs',1,0,'2001-05-05','2001-05-10','/CATERPILLAR D6D ANO 80 SERIE 76W 1610 - \r\n/MICHIGAN 55C ANO 88 SERIE 4238J 113BRC - \r\n/MICHIGAN 55A  ANO 80                     \r\n',NULL,5,0.00,17,'Maquinas e Tratores novos e usados','200.199.53.197',989092839);
INSERT INTO t_anuncio VALUES ('AO9rdiDYrMJ4xS5','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Aulas de Matem�tica e F�sica, 1o. e 2o. graus em domic�lio. Fone 327-9743; 973-7695.',NULL,10,0.00,10,'AULAS DE MATEM�TICA E F�SICA','200.199.95.6',973683376);
INSERT INTO t_anuncio VALUES ('AOvxspuH2fcxKXi','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','\'Refor�o Matem�tica, 1o. e 2o. graus, PSS, vestibular e concursos. Atende em domic�lio. Fone 962-9296. ',NULL,10,0.00,10,'REFOR�O ESCOLAR','200.199.95.6',973683559);
INSERT INTO t_anuncio VALUES ('AOOehmPavY3wZAV','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Aulas de viol�o. Contato com profa. Solange.\r\nFone 327.0093; 998-8911.',NULL,10,0.00,10,'AULAS DE VIOL�O','200.199.95.6',973683675);
INSERT INTO t_anuncio VALUES ('AOUAnsV8BWT6z2n','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Cia. do sabor oferececursos de tortas decoradas,salgados e docinhos para festas, panetones e p�es. Fone 2211496, 9736784.',NULL,10,0.00,10,'CIA. DO SABOR','200.199.95.6',973683870);
INSERT INTO t_anuncio VALUES ('AOkKNKPUfkhuzU7','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Ensino particular Mat. 1o. grau, em domic�lio Tr. 966-6876.',NULL,10,0.00,10,'ENSINO PARTICULAR','200.199.95.6',973683996);
INSERT INTO t_anuncio VALUES ('AOHJwJO9uXUnIbE','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Interc�mbio Est�gio  e trabalho no exterior. Tr.336-1160; 976-5404.',NULL,10,0.00,10,'INTERC�MBIO','200.199.95.6',973684118);
INSERT INTO t_anuncio VALUES ('AOIwpmzU6r8lG9m','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Curso pr�tico de contabilidade, setor fiscal,cont�bil, pessoal. Preparat�rio matem�tica, t�cnico dos Correios. Tr. 2210219; 971-2584.',NULL,10,0.00,10,'CURSO  DE CONTABILIDADE','200.199.95.6',973684308);
INSERT INTO t_anuncio VALUES ('AOA8EJ4hCX2fA3o','AEF14hCdGhu5q1u',5,0,'2000-11-08','2000-11-18','Curso de pintura em tecidos. Fone 235-4189; 9331-8820.',NULL,10,0.00,10,'CURSO DE PINTURA','200.199.95.6',973684431);
INSERT INTO t_anuncio VALUES ('AOhwHEZkx0XiD6r','AErwHMRkN8XiD6z',1,0,'2001-05-04','2001-05-14','Vendo Teclado Technics KN-1600. Excelente equipamento. Infinidade de timbres e estilos musicais. Sequenciador com 16 pistas. Recursos de edi��o de sons e ritmos. Acompanha caixa com disquetes de ritmos. Excelente estado. Pre�o de ocasi�o.F.(82)3370087.',NULL,10,0.00,25,'TECLADO TECHNICS KN-1600','200.241.146.213',988981002);
INSERT INTO t_anuncio VALUES ('AOzrmrM7AVZsNgK','AE8l8tWp0BOpSt4',6,32,'2001-05-04','2001-05-14','Compro pente de mem�ria EDO. Entre em contato pelo fone 976-9231',NULL,10,0.00,19,'Compro mem�ria EDO','200.199.51.49',988952556);
INSERT INTO t_anuncio VALUES ('AOMyJGL6jMBW9CP','AEAVIbEn6HMv6Ha',1,0,'2001-05-02','2001-05-12','series fechadas',NULL,10,450.00,7,'Vendo cole��o de cart�es telefonicos','200.199.51.20',988830617);
INSERT INTO t_anuncio VALUES ('AOmEyL6z2vA3wRk','AEiMWx0C5GTuXy9',3,9,'2001-05-02','2001-05-12','Boa localiza��o, bom para ponto comercial e para morar, 3 quartos, 2 banheiros, sala p/ 3 amb., copa-cozinha, 2 garagens, jardim, �rea de serv., toda na laje, 7x29m de esquina.',NULL,10,38000.00,2,'Boa casa na Ponta Grossa 3/4 e pre�o baixo','200.199.53.68',988828287);
INSERT INTO t_anuncio VALUES ('AOsX05qT6z2nQjM','AEDQLeHi1mzaLu5',1,0,'2001-05-01','2001-05-11','Vestido no estilo top/saia princesa em azul marinho com bordados na cor prata. Usado apenas uma vez. Pre�o atual de mercado: R$ 650,00 (Loja Della�s - Shopping Cidade)',NULL,10,250.00,7,'Vestido de Formatura','200.199.53.67',988765100);
INSERT INTO t_anuncio VALUES ('AOZKpejoBOLYbwR','AELO7kN8BchSlWp',5,0,'2001-05-01','2001-05-11','GOSTARIA DE TRABALHAR COM CONTABILIDADE FAZENDO SERVI�OS NO COMPUTADOR EM CASA MESMO, SE VC PRECISA DESTE TIPO DE SERVI�O ME PROCURE .\r\nJANISETE, FONE: 344-2498 /983-9285\r\nMACEI�-AL',NULL,10,0.00,6,'TRABALHO COM CONTABILIDADE','200.199.52.100',988759371);
INSERT INTO t_anuncio VALUES ('AOhAfA3gtOvQ3wR','AE7dwJ4x0BG9u5y',1,23,'2001-05-11','2001-05-21','CD\'S Prontos ou Personalizados. Personalize seu CD com os melhores e mais atualizados Softwares do mercado. Programas, Games, �udio, Cursos Multim�dia, etc. Solicite sua lista p/ e-mail. Contato: 241-0684. Paulo',NULL,10,10.00,19,'CD\'S Prontos ou Personalizados','200.199.50.78',989559444);
INSERT INTO t_anuncio VALUES ('AOMyBWpKlOTmPq1','AE7dwJ4x0BG9u5y',1,23,'2001-05-05','2001-05-15','Vendo uma Impressora Cannon, modelo 250, c/ cartucho novo, super conservada. Todos os drivers e manual. Na caixa. ',NULL,10,0.00,19,'Vende-se Impressora Cannon','200.199.56.241',989037137);
INSERT INTO t_anuncio VALUES ('AOMi5afIjMRkF8t','AE7dwJ4x0BG9u5y',1,25,'2001-05-01','2001-05-11','CD\'S prontos ou Personalizados. Centenas de T�tulos. Solicite sua lista por E-mail. infonet@bol.com.br Os �ltimos lan�amentos da m�sica nacional e internacional: Funk,Rock, Pop, Sertanejo, Ax�, Forr�, Baladas, Brega, Rom�nticas, etc. Solite cat�logo.',NULL,10,0.00,19,'CD\'s Prontos ou Personalizados','200.199.56.152',988687676);
INSERT INTO t_anuncio VALUES ('AOjOJynspubglqv','AE80FujwtGfjoBG',1,32,'2001-05-16','2001-05-26','Mem�rias DIMM de 64MB INSTALADAS!!!',NULL,10,75.00,19,'Mem�ria 64MB','200.191.20.17',989993324);
INSERT INTO t_anuncio VALUES ('AOh2P2nQbEBWhK5','AEF14hCdGhu5q1u',1,1,'2001-04-29','2001-05-09','Vendo P�lio ED, 1996, modelo 1997\r\nFone 235.2298; 974.6430\r\nE-mail alziracarmelino@uol.com.br',NULL,10,8500.00,1,'P�LIO ED','200.227.207.67',988593311);
INSERT INTO t_anuncio VALUES ('AOICVglG9uzUfIb','AEmS3wR4F8dG9Kd',5,0,'2001-04-29','2001-05-09','N�o perca tempo e dinheiro nas aprendendo coisas in�teis! Aprenda apenas o que voc� precisa! Na sua resid�ncia, com hora marcada.\r\n1 aula com 1 hora de dura��o custa R$ 10,00.',NULL,10,10.00,10,'Aulas de Inform�tica em domic�lio!','200.191.61.57',988541770);
INSERT INTO t_anuncio VALUES ('AOKbQVKXa7OTX27','AEmS3wR4F8dG9Kd',1,31,'2001-05-20','2001-05-30','Isso mesmo! HD de 10 GB por apenas R$ 269,00!\r\nE se quiser o windows instalado, paga somente R$ 20,00 a mais! Promo��o por tempo limitado!\r\nS� at� sexta-feira, dia 25 de maio! Aproveite!','gif',10,269.00,19,'HD DE 10GB por apenas R$ 269,00','200.191.62.221',990353463);
INSERT INTO t_anuncio VALUES ('AODcfA3wRsx8BkV','AEmS3wR4F8dG9Kd',5,0,'2001-04-29','2001-05-09','Isso mesmo! Modifique as m�sicas, tire uma parte que voc� n�o goste, mistura de faixas, efeitos especiais etc. Deixe suas m�sicas do jeito que voc� sempre quis!',NULL,10,0.00,24,'Deixe suas m�sicas do jeito que voc� sempre quis!','200.191.61.57',988538755);
INSERT INTO t_anuncio VALUES ('AOYKV0dyTebwJcx','AEQ2tWpStchSt4N',1,0,'2001-04-28','2001-05-08','Rel�gio incr�vel da citizen com fundo azul. Qualidade de novo!',NULL,10,300.00,7,'Rel�gio CITIZEN NAVISURF','200.199.95.157',988433440);
INSERT INTO t_anuncio VALUES ('AOic7cpKdGvQ2nI','AEQ2tWpStchSt4N',1,0,'2001-04-28','2001-05-08','Grava��o de CDs de JoGOs, ProGRamAs e muiTA MuSica de TodOS os EsTiLOs. InTEressados ENviem Um EmAil.',NULL,10,8.00,24,'CDs com qualidade','200.199.95.157',988433214);
INSERT INTO t_anuncio VALUES ('AOppchuPaDAVoJ4','AEQNYbwZA3gB4F8',5,24,'2001-04-27','2001-05-02',' Seu computador quebou! est� lento ! chame a Omega termos a solu��o em pouco tempo.\r\n  * Conserto e Manuten��o\r\n  * Instala��o de Sistema Operacional, Editor de texto, Planilha Eletr�nica , Gr�ficos (destaque p/ COREL10), e Sistema Comercial. \r\n   ',NULL,5,0.00,19,'SEU COMPUTADOR QUEBROU !  CHAME A  OMEGA','200.199.58.78',988366510);
INSERT INTO t_anuncio VALUES ('AOQ5CzM7kxuH2fA','AElN6zaLmXUv6Hi',5,0,'2001-04-27','2001-05-07','QUERO TRABALHAR PARA JUNTAR DINHEIRO, E FAZER CURSOS DE INFORM�TICA...\r\nFONE:344-2498/983-9285','gif',10,181.00,8,'QUERO TRABALHAR COM INFORM�TICA','200.199.58.175',988347515);
INSERT INTO t_anuncio VALUES ('AOQepuPiL6zUnQj','AEnmFSlWp05G9Kl',5,0,'2001-04-27','2001-05-07','J� FEZ SEU IMPOSTO DE RENDA? LIGUE PARA MANUEL TEOTONIO TUDO EM CONTABILIDADE.......\r\nTOT� CONTATO:641-2608/641-2769','jpg',10,0.00,15,'CONTABILIDADE EM GERAL','200.199.58.175',988346923);
INSERT INTO t_anuncio VALUES ('AOkm1YjwJ4T6jEZ','AEnmFSlWp05G9Kl',5,0,'2001-04-27','2001-05-07','ESCRITAS FISCAIS, SETOR PESSOAL, ABERTURA DE FIRMA, ENCERRAMENTO DE FIRMA, IMPOSTO DE RENDA, E TUDO QUE VOC� DESEJAR EM CONTABILIDADE.....CONTATO: 82 641-2530/641-2769 HUMBERTO','gif',10,0.00,15,'CONTABILIDADE(TOT�)','200.199.58.175',988346549);
INSERT INTO t_anuncio VALUES ('AOqNQ3oDIbgJ4F8','AEFYTeHaDejMfIb',5,0,'2001-04-27','2001-05-07','FABRICAMOS E REFORMAMOS M�VEIS EM GERAL,TEMOS DESENHISTA, OS MELHORES PRE�OS, QUALIDADE LIGUE PARA ANTERO 344-2498 OU 983-9285.. MACEI�-AL\r\n344-2498\r\n344-2498','jpg',10,0.00,15,'MARCENEIRO','200.199.58.175',988344228);
INSERT INTO t_anuncio VALUES ('AOFaly1C5GvYrUv','AEvyBG1uH2J4pKX',1,0,'2001-04-26','2001-05-06','Vendo MALA DIRETA, com cadastramento de pessoas f�sicas ou jur�dicas (endere�os ou emails).Ligue: (0xx11) 4675-7581. Das 12 �s 20 hs.',NULL,10,0.00,15,'MALA DIRETA','200.231.70.124',988339966);
INSERT INTO t_anuncio VALUES ('AOQHuHUfI30tYrM','AEloHMZsNg5yTmP',1,25,'2001-04-26','2001-05-06','Desenvolvo programas administrativos para todos os ramos de atividades',NULL,10,0.00,19,'PROGRAMAS ADMINISTRATIVOS','200.199.95.60',988327127);
INSERT INTO t_anuncio VALUES ('AOwjeH2D6HMfQjU','AE6QVY26aeinrvz',3,0,'2001-04-22','2001-05-02','Semi-nova, c/ 13 exerc�cios, ligue e  fa�a um bom neg�cio.',NULL,10,850.00,17,'M�quina de muscula��o ATHLETIC','200.241.146.213',987972937);
INSERT INTO t_anuncio VALUES ('AOdvyTmXy1ePq1C','AEF14hCdGhu5q1u',1,0,'2000-11-10','2000-11-20','M�veis de escrit�rio (arquivo, computador, mesa, cadeira, arm�rios, fog�o, geladeira).\r\nMotivo: fechamento de escrt�rio). Tr. 231-6639; 327-6940.',NULL,10,0.00,27,'M�VEIS DE ESCRIT�RIO','200.199.95.6',973852358);
INSERT INTO t_anuncio VALUES ('AOD9zERYbEtOhC5','AEvyBG1uH2J4pKX',5,0,'2001-04-22','2001-05-02','MALA DIRETA: Temos cadastro de emails de pessoas f�sicas e jur�dicas de todo Brasil. adquira j� esse cadastro (empresas), posteriormente envie seu curriculum  e aguarde o emprego chegar. Ligue agora para: (011) 4675-7581 Das 12 �s 20 hs.',NULL,10,0.00,8,'VAGA PARA VOC� TRABALHAR EST� MAIS F�CIL','200.211.154.44',987964823);
INSERT INTO t_anuncio VALUES ('AOrc7kNgB41u5y1','AE4GAVgB4puPiLe',1,0,'2001-04-24','2001-05-04','Vendo CD\'s gravados em shows ao vivo, a lista completa de CD\'s vc encontra em:  www.cdaovivo.hpg.com.br',NULL,10,8.00,24,'CD\'s de Shows','200.199.92.48',988085525);
INSERT INTO t_anuncio VALUES ('AOzWR4pKdyvQbwZ','AEiMWx0C5GTuXy9',5,0,'2001-04-26','2001-04-27','21 anos, experi�ncia na �rea, universit�rio Ufal, conhecimento em corel draw, basic, windows, word, excel, access, ms-dos e manuten��o em micros.',NULL,1,250.00,8,'Preciso Trabalhar, em inform�tica.','200.199.53.182',988264252);
INSERT INTO t_anuncio VALUES ('AOep41638dTY3gl','AEetqfc9ejRWT6b',5,14,'2001-05-15','2001-05-18','fazemos sevi�os de rede telefonicas e exten��es em hoteis e condominios e recidencias e empressas. contato 327-3385 ',NULL,3,0.00,4,'telefonia','200.199.58.134',989898670);
INSERT INTO t_anuncio VALUES ('AOLSV0dqLe3gBWp','AE7dwJ4x0BG9u5y',1,0,'2001-05-05','2001-05-15','Vendo uma agenda Cassio, de 32 Kb, supernova, na caixa, com todos os manuais. V�rios m�dulos e recursos. ',NULL,10,40.00,22,'Vendo uma Agenda Cassio','200.199.56.241',989037351);
INSERT INTO t_anuncio VALUES ('AODoHaDePivYzaL','AEwBMnIjUvIjUv6',1,1,'2001-05-05','2001-05-15','ar condicionado,vidro eletrico, trava eletrica,alarme,aceita-se contra-proposta,unico dono',NULL,10,7500.00,1,'Vendo Corsa 95','200.241.146.213',989073432);
INSERT INTO t_anuncio VALUES ('AOMMzwlqpm38lqD','AE7dwJ4x0BG9u5y',1,14,'2001-05-15','2001-05-25','MINE-BINA INTELBR�S, NOVO, LACRADO, COM V�RIAS FUN��ES E CAPACIDADE PARA AT� TR�S LINHAS DIGITAIS. PRE�O DE OCASI�O: R$ - 40,00! URGENTE!!!',NULL,10,40.00,4,'VENDE-SE BINA INTERLBRAS','200.199.56.147',989901146);
INSERT INTO t_anuncio VALUES ('AOnpk9erwBiet27','AE7dwJ4x0BG9u5y',1,0,'2001-05-15','2001-05-25','Vendo uma agenda Cassio, de 32 KB, com todas as fun��es espec�ficas desse tipo de agenda, mais recurso para anota��es pessoais e �rea Secreta de dados. Super nova, na caixa. Apenas R$ - 40,00. Interessados contatem-me pelo fone: 241-0684 ou email.',NULL,10,0.00,22,'Agenda Cassio 32 KB, pouqu�ssimo uso','200.199.56.147',989901613);
INSERT INTO t_anuncio VALUES ('AOzlvAVgJQbMfIb','AE7dwJ4x0BG9u5y',1,31,'2001-04-19','2001-04-29','Vendo um modem Motorola, 56kb V90, novo na caixa, c/ garantia. Instala�ao gratuita. R$-50,00.',NULL,10,50.00,19,'Modem Motorola','200.199.56.236',987715010);
INSERT INTO t_anuncio VALUES ('AOd4Zcx0lOL6rUn','AE7dwJ4x0BG9u5y',1,0,'2001-04-19','2001-04-29','Softwares(+100 t�tulos), �udio( fa�a sua colet�nea), Games, Tutoriais, Backups, etc. Apenas R$-10,00.','',10,10.00,24,'Grava-se CDs Diversos','200.199.56.236',987714053);
INSERT INTO t_anuncio VALUES ('AOcsDsN0lynIV8t','AEf61mPqTmz2nYz',1,10,'2001-04-19','2001-04-29','Vendo apartamento novo,tipo est�dio, beira-mar, nascente, S�o Jos� da Coroa Grande(Pe), mobiliado, piscina, sauna, sal�o de festas,garagem ,lavanderia,sala de gin�stica,pr�dio todo revestido em cer�mica,tratar com Ana Cl�udia, fones (82)338-3693/966-0599.',NULL,10,38200.00,2,'Apartamento S�o Jos� da Coroa Grande-Pe','200.241.148.66',987692206);
INSERT INTO t_anuncio VALUES ('AOrlEJ4e3wtWhK5','AEf61mPqTmz2nYz',1,1,'2001-04-19','2001-04-29','Vendo Escort/97,motor ZETEC 1.8,cor Prata, com ar-condicionado, dire��o hidr�ulica, trava el�trica, alarme, vidros c/ revestimento fum�.Tratar com Ana Cl�udia, fones 966-0599/338-3693.',NULL,10,12000.00,1,'Escort 97','200.241.148.66',987691432);
INSERT INTO t_anuncio VALUES ('AOa9AF0tWpuXqTm','AE8AL6jwZkhC5qT',1,1,'2001-04-18','2001-04-28','Fiat Uno Smart 2001, com 8 parcelas pagas de R$ 444,00\r\n',NULL,10,2500.00,1,'Fiat Uno Smart','200.211.135.38',987615146);
INSERT INTO t_anuncio VALUES ('AOCna7kFSd2fsN0','AE7dwJ4x0BG9u5y',1,0,'2001-04-27','2001-05-07','Games, Programas, �udio. Tudo Full e personalizado. Fa�a j� sua colet�nia com os melhores sucessos do momento! Qualquer CD, R$-10,00 apenas. Pe�a j� a lista de softwares pelo e-mail citado.',NULL,10,10.00,24,'Grava-se CD\'S','200.199.50.83',988346211);
INSERT INTO t_anuncio VALUES ('AONILeHiTus3EfI','AE7dwJ4x0BG9u5y',1,14,'2001-04-18','2001-04-28','VENDO UM IDENTIFICADOR DE CHAMADAS, COM CAPACIDADE PARA 3 LINHAS DIGITAIS E 54 MEMORIAS PERMANENTES. NOVO NA CAIXA. URGENTE!!! R$- 30,00',NULL,10,30.00,4,'Identificador de chamadas (Bina).','200.199.56.200',987564184);
INSERT INTO t_anuncio VALUES ('AOffqnQbwZ4xSlO','AECPKXiTmXaD6Hi',5,0,'2001-04-17','2001-04-27','Preciso trabalhar, tenho experiencia em contabilidade, digita��o.Tenho carro e celular pr�prio.',NULL,10,0.00,8,'Preciso Trabalhar','200.191.62.203',987524876);
INSERT INTO t_anuncio VALUES ('AOxzSPUnIb8B4pS','AE7dwJ4x0BG9u5y',1,0,'2001-04-17','2001-04-27',' Internet Explorer 5.5BR, Netscape 4.77, Alexa 2000, +500 fontes e 50 MP3. Apenas R$-10,00!!! Fa�a j� seu pedido!\r\n',NULL,10,10.00,24,'Super CD !!!','200.199.56.137',987479992);
INSERT INTO t_anuncio VALUES ('AOAZ2ZkF0liDQjE','AE8HCPiTudq1CdO',5,24,'2000-11-10','2000-11-20','Manutencao em Computadores, Impressoras.\r\nServicos de Cabeamentos, Instalacao e Confi-\r\nguracao de Rede do Windows (ponto-a-ponto).\r\nTreinamentos em Windows, Office, Internet.\r\n\r\n',NULL,10,35.00,19,'Manut. Computador/Impressora/Rede/Treinamentos','200.241.162.3',973875546);
INSERT INTO t_anuncio VALUES ('AOJ1c9C5y1ez2nQ','AEF14hCdGhu5q1u',1,10,'2000-11-10','2000-11-20','TR:9351-3616',NULL,10,0.00,2,'APARTAMENTO   2   QTS C/ARMARIOS','200.199.95.6',973882078);
INSERT INTO t_anuncio VALUES ('AOSU5qL6sFuXqTm','AE7dwJ4x0BG9u5y',1,25,'2001-04-17','2001-04-27',' Internet Explorer 5.5BR, Netscape 4.77, Alexa 2000, +500 fontes e 50 MP3. Apenas R$-10,00!!! Fa�a j� seu pedido!\r\n',NULL,10,10.00,19,'Super Colet�nea','200.199.56.137',987479862);
INSERT INTO t_anuncio VALUES ('AOnberMnQjoRsVg','AEF14hCdGhu5q1u',1,10,'2000-11-11','2000-11-21','2qts, Edf. Montreal',NULL,10,26000.00,2,'APTOS  NOVOS','200.199.95.6',973942669);
INSERT INTO t_anuncio VALUES ('AOTqJG9C5GDezav','AEF14hCdGhu5q1u',1,10,'2000-11-11','2000-11-21','VENDO ALUGO APTO NA P. VERDE TEL:982-4253/231-3268',NULL,10,0.00,2,'1,2 QTS','200.199.95.6',973943804);
INSERT INTO t_anuncio VALUES ('AOlFroJ4xSXqTmP','AEF14hCdGhu5q1u',1,10,'2000-11-11','2000-11-21','4QTS 3 WC\'S, SL. AMPLA,VAR\r\n\r\nTR:326-4679\r\n                                                                            ',NULL,10,40000.00,2,'EDF SOLAR  DO VALE','200.199.95.6',973944317);
INSERT INTO t_anuncio VALUES ('AOploJWx0lq1uXq','AEF14hCdGhu5q1u',1,10,'2000-11-11','2000-11-21','S/STES, VARANDA ,PISCINA\r\nREF 1840 231.8866 CRECI 906 J\r\n',NULL,10,0.00,2,'APTO  4QRTS','200.199.95.6',973944867);
INSERT INTO t_anuncio VALUES ('AOyBUfsVoRWhC5y','AEF14hCdGhu5q1u',1,10,'2000-11-11','2000-11-21','Apto. c/ 3q. 2 salas, piscina. Parcelo.',NULL,10,0.00,2,'APTO. COND. ARMANDO  LOBO','200.199.95.6',973945470);
INSERT INTO t_anuncio VALUES ('AOXnqfAVgBWhKdy','AEF14hCdGhu5q1u',1,10,'2000-11-11','2000-11-21','PLANO 100 C/3QRTS TR:357-5363',NULL,10,0.00,2,'NOBRE','200.199.95.6',973946910);
INSERT INTO t_anuncio VALUES ('AOrtM7s3EfsVo7I','AEvyBG1uH2J4pKX',5,0,'2001-04-16','2001-04-26','Ganhe Dinheiro com seu Micro trabalhando em sua casa. Trabalhe muito e ganhe bastante. Envie seus dados e pe�a informa��es gr�tis para: zipzip@uol.com.br ',NULL,10,0.00,25,'TRABALHE J�!!!','200.191.67.221',987396478);
INSERT INTO t_anuncio VALUES ('AOvVIN0vI3wRcF0','AEXGRs3w7I3EfQr',1,21,'2001-03-30','2001-04-09','Vendo 586 100 c/ 24 Mb de RAM, HD de 540 Mb, ideal para automa��o comercial ou uso pessoal. Monto e configuro com os programas de sua prefer�ncia.',NULL,10,500.00,19,'Computador Barato!','200.241.150.150',985969337);
INSERT INTO t_anuncio VALUES ('AOhbY3oRkNSlOpS','AEF14hCdGhu5q1u',1,0,'2000-11-11','2000-11-21','TR:3368046/9746430 E-MAIL:HENRIQUECARMELLINO@GLOBO.COM',NULL,10,30.00,7,'BARBIE  BONECA','200.199.95.6',973956466);
INSERT INTO t_anuncio VALUES ('AOpqR4F0lOLez2D','AEF14hCdGhu5q1u',6,0,'2000-11-11','2000-11-21','NOVO TR:336-8046/974-6430',NULL,10,18.00,7,'PUXA   BATATINHA','200.199.95.6',973956795);
INSERT INTO t_anuncio VALUES ('AOSadyTuXyLeHaL','AEF14hCdGhu5q1u',6,0,'2000-11-11','2000-11-21','TR:336-8046/974-6430',NULL,10,5.00,7,'CARRINHO DE  BRIQUEDO','200.199.95.6',973957127);
INSERT INTO t_anuncio VALUES ('AOfLO9uPaDA3w7s','AEF14hCdGhu5q1u',1,0,'2000-11-11','2000-11-21','TR:336-8046',NULL,10,240.00,7,'VITA DE VIDEUGAME DO MARIO64 FICIAL C/CARTUG','200.199.95.6',973957494);
INSERT INTO t_anuncio VALUES ('AO5vO1mHavI3wZs','AEF14hCdGhu5q1u',1,0,'2000-11-11','2000-11-21','TR:974-6430/336-8046',NULL,10,200.00,7,'DIRECTV','200.199.95.6',973958331);
INSERT INTO t_anuncio VALUES ('AOvNIyLeHinQjMf','AEF14hCdGhu5q1u',1,0,'2000-11-11','2000-11-21','TEL:336-8046',NULL,10,5.00,7,'BLIBIA   MANA','200.199.95.6',973958553);
INSERT INTO t_anuncio VALUES ('AOWILYbwZsx0lO1','AEF14hCdGhu5q1u',1,0,'2000-11-11','2000-11-21','Semi-nova, R-14\r\n\r\nTratar: 982-8813/ 973-0026',NULL,10,450.00,7,'Jogo de roda do tempra turbo stille','200.241.146.213',973963247);
INSERT INTO t_anuncio VALUES ('AOoOR4FgRsF8JkN','AEF14hCdGhu5q1u',3,0,'2000-11-11','2000-11-21','Semi-nova, R-14\r\n\r\nTratar: 982-8813/ 973-0026',NULL,10,450.00,7,'Jogo de roda da Mercedez','200.241.146.213',973963412);
INSERT INTO t_anuncio VALUES ('AOJyJG1mPinQjM7','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Alta, loira, seios grandes, cabelos longos, sexo total, local pr�prio.\r\n\r\nTratar: 9381-3152',NULL,10,0.00,20,'Kelly Travesti!!!','200.241.146.213',974026083);
INSERT INTO t_anuncio VALUES ('AO7bmrwZkNK5qL6','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Modelo fotogr�fica, c/ desejos e fantasias, ligue e n�o se engane. at, ele/casais\r\n\r\nTratar: 9304-3662',NULL,10,0.00,20,'Suyane','200.241.146.213',974026199);
INSERT INTO t_anuncio VALUES ('AOoOZcpSYVS5yTe','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Morena bronzeada, 1,70m, universit�ria, 20 anos, local pr�prio,motel ou hotel\r\n\r\nTratar: 9371-1989',NULL,10,0.00,20,'Viviane','200.241.146.213',974026307);
INSERT INTO t_anuncio VALUES ('AOma5iD6z27sVoR','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','19 anos, 1,70m, bonita seios exuberantes.\r\n\r\nAt. completo\r\n\r\nTratar: 962-4569',NULL,10,0.00,20,'Morena caliente','200.241.146.213',974026386);
INSERT INTO t_anuncio VALUES ('AOhsnkF8tW1mz2n','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Relax total, n�o perca atendo tamb�m com aparelhos.\r\n\r\nTratar: 9341-3280',NULL,10,0.00,20,'Massagens er�ticas','200.241.146.213',974026472);
INSERT INTO t_anuncio VALUES ('AODaX2FCXa7sN0l','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','loiro rec�m chegado, 1,75m, ex-modelo, completo, at.ele/ela, casais.\r\n\r\nTratar: 982-9248',NULL,10,0.00,20,'D�rio paulista','200.241.146.213',974026581);
INSERT INTO t_anuncio VALUES ('AOi14huXiLQjEZs','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','9303-0826, este � o fone do prazer, p/ homens de estilo, estou pronta para te satisfazer. C/ local\r\n\r\n\r\n',NULL,10,40.00,20,'Cris','200.241.146.213',974026715);
INSERT INTO t_anuncio VALUES ('AON5V8B4x8dy1mX','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','9303-0826, este � o fone do prazer, p/ homens de estilo, estou pronta para te satisfazer. C/ local\r\n\r\n\r\n',NULL,10,40.00,20,'Cris','200.241.146.213',974026715);
INSERT INTO t_anuncio VALUES ('AORvyLYjMfkF8tW','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Seios fartos, S/limete, ambos os sexos/ casais.\r\n\r\nTratar: 9371-0309',NULL,10,0.00,20,'Beatriz Popozuda','200.241.146.213',974026833);
INSERT INTO t_anuncio VALUES ('AOptkhKdy16rUfI','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Belas e selecionadasa garotas, loiras, morenas e mulatas.\r\n\r\nTratar: 966-7808',NULL,10,0.00,20,'Kelly e amigas','200.241.146.213',974026920);
INSERT INTO t_anuncio VALUES ('AOKDOTYjEZcxSly','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Coixas grossas, bumbum nota 10, liberal.\r\n\r\nTratar: 9304-8588',NULL,10,0.00,20,'Paula super gata','200.241.146.213',974027000);
INSERT INTO t_anuncio VALUES ('AOMsTeHaD63oRkV','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Loira linda de rosto e corpo, fogosa e liberal, at. ele/ casais\r\n\r\nTratar: 997-8424',NULL,10,0.00,20,'Bionda super gata','200.241.146.213',974027082);
INSERT INTO t_anuncio VALUES ('AOLwH2v6z2fAbEf','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Coixas grossas e belo bumbum, at. ele/ ela e casais.\r\n\r\nTratar: 963-5291',NULL,10,30.00,20,'Lilly bela e sensual','200.241.146.213',974027172);
INSERT INTO t_anuncio VALUES ('AOZ3uzUfA3K5qTe','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Coixas grossas e belo bumbum, at. ele/ ela e casais.\r\n\r\nTratar: 963-5291',NULL,10,30.00,20,'Lilly bela e sensual','200.241.146.213',974027173);
INSERT INTO t_anuncio VALUES ('AOAorEZsN8dGTmH','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Rec�m chegada de Recifa, bela, sexy e liberal. At, ele/casais\r\n\r\nTratar: 962-6939',NULL,10,0.00,20,'Juliana gata','200.241.146.213',974027274);
INSERT INTO t_anuncio VALUES ('AOZVH2nQbEJcF8B','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','As mais sensacionais. Sigilo total. Confira Book.\r\n\r\nTratar: 971-9228',NULL,10,0.00,20,'Disk modelos','200.241.146.213',974027348);
INSERT INTO t_anuncio VALUES ('AOBvOTezUfkF0lG','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','As mais sensacionais. Sigilo total. Confira Book.\r\n\r\nTratar: 971-9228',NULL,10,0.00,20,'Disk modelos','200.241.146.213',974027351);
INSERT INTO t_anuncio VALUES ('AOsmpCXivYN0lOh','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Rec�m chegada, branquinha, 19 aninhos, At. ele,ela e casais.\r\n\r\nTratar: 955-5364',NULL,10,0.00,20,'Paty paranaense','200.241.146.213',974027452);
INSERT INTO t_anuncio VALUES ('AOl9ANgCHa7A3wZ','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Moreno Claro, 1.76m, 72kg, p/ realizar suas fantasias sexuais, At. ele/ ela e casais.\r\n24h\r\nTratar: 995-1996',NULL,10,0.00,20,'Adex','200.241.146.213',974027622);
INSERT INTO t_anuncio VALUES ('AOmEPiDeHafQjMf','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Moreno claro, 1.78m, 75kg, Atendo em: motel, hotel, res.\r\n\r\nTratar: 9303-2826',NULL,10,0.00,20,'Andrade','200.241.146.213',974027723);
INSERT INTO t_anuncio VALUES ('AO4wyTmPq1eHaD6','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Com �timos atendimentos e servi�os 24h\r\n\r\nLigue: 962-0805',NULL,10,0.00,20,'Disk gatinhas','200.241.146.213',974027794);
INSERT INTO t_anuncio VALUES ('AOfalG9CdGTuXIb','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Bronzeada, pernas grossas, corpo perfeito, bumbum dourado.\r\n\r\nTratar: 9304-8865',NULL,10,0.00,20,'Iris loira','200.241.146.213',974027869);
INSERT INTO t_anuncio VALUES ('AOtgrMfIjMZsVoZ','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Modelo acompanhante para executivo, super fogosa!!\r\n\r\nTratar: 9309-1874',NULL,10,0.00,20,'Tatiana loira bronzeada','200.241.146.213',974027970);
INSERT INTO t_anuncio VALUES ('AOKUdivQbwJ4pK5','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Rosto angelical, pernas grossas, corpo perfeito, bumbum dourado.\r\n\r\nTratar: 9304-8865',NULL,10,30.00,20,'Thain� loira','200.241.146.213',974028072);
INSERT INTO t_anuncio VALUES ('AOIUXivYjMBWpSl','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Rosto angelical, pernas grossas, corpo perfeito, bumbum dourado.\r\n\r\nTratar: 9304-8865',NULL,10,30.00,20,'Thain� loira','200.241.146.213',974028073);
INSERT INTO t_anuncio VALUES ('AO5ZinAVgJO9mH2','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Sensual, 18 aninhos, 1.68m, 53kg, loirinha sexy. p/ele/ela/casais.\r\n\r\nTratar: 991-0408',NULL,10,0.00,20,'Luma nifetinha','200.241.146.213',974028189);
INSERT INTO t_anuncio VALUES ('AOknW9uXy9uX4x8','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Filha de alem�o, Super meiga, p/ homens de bom gosto.\r\n\r\nTratar: 9305-6493',NULL,10,0.00,20,'Caroline (loira)','200.241.146.213',974028270);
INSERT INTO t_anuncio VALUES ('AO9UdyLezUfA3gJ','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Maiores de 18 anos para trabalhar como acompanhante.\r\n\r\nTratar: 962-9708 Ariel',NULL,10,0.00,20,'Precisa-se de garotas','200.241.146.213',974028348);
INSERT INTO t_anuncio VALUES ('AOfjKPaLeP2vYrU','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Bumbum nota 10. Gata liberal, seios fartos, Chegue pr�\r\n\r\nTratar: 976-2067',NULL,10,0.00,20,'Ana Paula','200.241.146.213',974028445);
INSERT INTO t_anuncio VALUES ('AOGZ2hCPavsF0lG','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Com todo fogo da mulher baiana, 24h a sua espera!!\r\n\r\nTratar: 963-6183',NULL,10,0.00,20,'Suzana baiana','200.241.146.213',974028542);
INSERT INTO t_anuncio VALUES ('AOr6huH2nQVoB4x','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Morena clara, olhos verdes, lind�ssima classi-a.\r\n\r\nLigue: 967-0340',NULL,10,0.00,20,'Charmilly','200.241.146.213',974028615);
INSERT INTO t_anuncio VALUES ('AOG69mPiLejWhC5','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Opc�o que vvc desejava, morena boazuda, carinhosa, discreta. at. completo\r\n\r\nTratar: 9304-5043',NULL,10,0.00,20,'S� para Arapiraca','200.241.146.213',974028713);
INSERT INTO t_anuncio VALUES ('AODilG1mPinIbMf','AEF14hCdGhu5q1u',1,0,'2000-11-12','2000-11-22','4 Portas.\r\n\r\ntratar: 338-3355/ 9351-5051 c/ Clicia',NULL,10,0.00,14,'Renault Clio-RL','200.241.146.213',974029745);
INSERT INTO t_anuncio VALUES ('AOShkxK5y16rM7s','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','N�o contemplado\r\n\r\nTratar: 9381-7154/ 973-7154',NULL,10,0.00,20,'Strada 200 c/22 pagas','200.241.146.213',974029930);
INSERT INTO t_anuncio VALUES ('AOHs74huPa7kF0l','AEF14hCdGhu5q1u',1,0,'2000-11-12','2000-11-22','C/ motor MF 65, Painel el�trico, e inst. do motor.\r\n\r\nTratar: 981-1776',NULL,10,0.00,17,'Gerador 10Kva','200.241.146.213',974030126);
INSERT INTO t_anuncio VALUES ('AONlEB4pSdqL6zU','AEF14hCdGhu5q1u',1,0,'2000-11-12','2000-11-22','Agrale c/ partida el�trica, 6.500w\r\n\r\nTRATAR: 327-9360',NULL,10,0.00,17,'Grupo gerador','200.241.146.213',974030213);
INSERT INTO t_anuncio VALUES ('AOaCF0lO9KHaD6r','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','C/ faf� da manh�, roupa lavada,c/ ou s/ ar.\r\nExc. localiza��o.\r\n\r\nTratar: 325-2885',NULL,10,0.00,9,'Pousada (aceitamos mensalista)','200.241.146.213',974030327);
INSERT INTO t_anuncio VALUES ('AO6orEZkF05qLez','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Jacarecica!!! Perto da praia\r\n\r\nTratar: 355-5198',NULL,10,100.00,9,'Aluga-se quarto bem localizado','200.241.146.213',974030421);
INSERT INTO t_anuncio VALUES ('AOrP8tWpSty1u5y','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Jacarecica!!! Perto da praia\r\n\r\nTratar: 355-5198',NULL,10,100.00,9,'Aluga-se quarto bem localizado','200.241.146.213',974030422);
INSERT INTO t_anuncio VALUES ('AOtTjEZs3wt4x0B','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Para rapazes, em coletivo e individual.\r\n\r\nTratar: 977-1842/ 326-1143',NULL,10,0.00,9,'Temos vagas','200.241.146.213',974030522);
INSERT INTO t_anuncio VALUES ('AOhGJ4pKdOTmHaD','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Qualidade. Entrega em domic�lio, firmas, industria.\r\n\r\nTratar: 235-1524',NULL,10,0.00,3,'Quetinha/ marmitas/ 6 opc,','200.241.146.213',974031760);
INSERT INTO t_anuncio VALUES ('AOU3ezM7ANuPavQ','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Tratar: 9311-0531',NULL,10,0.00,3,'Aceita-se encomenda de Bob� e Caruru','200.241.146.213',974031816);
INSERT INTO t_anuncio VALUES ('AOSorEfIbEB4x0t','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Quentinhas,   caseira, op��o de 2 saladas, aceito chegue pr�\r\n\r\nTratar: 320-1756',NULL,10,0.00,3,'Manancial marmitas','200.241.146.213',974031904);
INSERT INTO t_anuncio VALUES ('AOiH0tQbw7kF8Jc','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Fornecemos alimenta��o em domicilio, p/ lojas, empresas, e res. Tudo 1@\r\n\r\nTratar: 995-1838/ 995-1458',NULL,10,2.50,3,'Andreana refei��es','200.241.146.213',974032025);
INSERT INTO t_anuncio VALUES ('AOE3YlqL6roJ4pK','AEF14hCdGhu5q1u',5,0,'2000-11-12','2000-11-22','Fornecemos alimenta��o em domicilio, p/ lojas, empresas, e res. Tudo 1@\r\n\r\nTratar: 995-1838/ 995-1458',NULL,10,2.50,3,'Andreana refei��es','200.241.146.213',974032037);
INSERT INTO t_anuncio VALUES ('AOIe2K5rpucqUoB','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','336-1800',NULL,10,0.00,7,'ADESIVOS','200.199.95.6',974590712);
INSERT INTO t_anuncio VALUES ('AO4sZ5z3x0sWy2n','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','338-2313',NULL,10,0.00,7,'Produtos p/ Pecu�ria e  Agricultura','200.199.95.6',974591126);
INSERT INTO t_anuncio VALUES ('AO7foCYkG9JXzVw','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','235-3230',NULL,10,0.00,7,'�GUA POT�VEL','200.199.95.6',974591232);
INSERT INTO t_anuncio VALUES ('AOFxrFKYAWR5rV9','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','Fone 320-1719',NULL,10,0.00,7,'�GUA DE COCO','200.199.95.6',974591314);
INSERT INTO t_anuncio VALUES ('AOJnwC6AbFZt5Ga','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','Fone 327-7182 ',NULL,10,0.00,7,'�GUA MINERAL','200.199.95.6',974591447);
INSERT INTO t_anuncio VALUES ('AOhxtyaE8Khvft4','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','231-5588',NULL,10,0.00,7,'�GUA SANIT�RIA','200.199.95.6',974591549);
INSERT INTO t_anuncio VALUES ('AOkAftdwKucOFp9','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','FONE 221-0651',NULL,10,0.00,7,'AGUARDENTE','200.199.95.6',974591651);
INSERT INTO t_anuncio VALUES ('AOeCEB5rNhQkOqM','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','327-1691',NULL,10,0.00,7,'ALARMES','200.199.95.6',974591776);
INSERT INTO t_anuncio VALUES ('AOHF5U8mkqRXjxD','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','FONE 223-3324',NULL,10,0.00,7,'ALFAIATE','200.199.95.6',974591887);
INSERT INTO t_anuncio VALUES ('AOjrI4yMftpDZkO','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','Atende a domic�lio Fone 973-1490',NULL,10,0.00,7,'DEPILADORA','200.199.95.6',974591976);
INSERT INTO t_anuncio VALUES ('AO7fwmIWivdrVpC','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','235-1009',NULL,10,0.00,16,'ALIMENTOS CONGELADOS','200.199.95.6',974592116);
INSERT INTO t_anuncio VALUES ('AOQmD7JdH2KeYkV','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','FONE 231-3403',NULL,10,0.00,16,'ALIMENTOS DIET�TICOS','200.199.95.6',974592246);
INSERT INTO t_anuncio VALUES ('AOck0erxTvWiU8C','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','Fone 325-1714',NULL,10,0.00,16,'Alimentos Prontos','200.199.95.6',974592327);
INSERT INTO t_anuncio VALUES ('AO1h5yaMo0PjVp1','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','FONE 324-1672',NULL,10,0.00,7,'ALUM�NIO','200.199.95.6',974592436);
INSERT INTO t_anuncio VALUES ('AOCKaZlzN1WUSXb','AEF14hCdGhu5q1u',2,0,'2000-11-18','2000-11-28','FONE 241-7447',NULL,10,0.00,7,'ANDAIMES','200.199.95.6',974592559);
INSERT INTO t_anuncio VALUES ('AOCYZlH3pCW2EZt','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','FONE 325-3193',NULL,10,0.00,5,'ANIMAIS - CRIA��O','200.199.95.6',974592727);
INSERT INTO t_anuncio VALUES ('AOXdDYA4qUZB5zV','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28',' CONSERTO FONE 336-8911',NULL,10,0.00,7,'ANTENAS','200.199.95.6',974592861);
INSERT INTO t_anuncio VALUES ('AO6pOUgmHbL7lyU','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','FONE 336-5648',NULL,10,0.00,7,'ANTENAS PARAB�LICAS','200.199.95.6',974593269);
INSERT INTO t_anuncio VALUES ('AO1x5iMgKe3hTnR','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','FONE 221-6956',NULL,10,0.00,7,'ANTICORROS�O','200.199.95.6',974593374);
INSERT INTO t_anuncio VALUES ('AOo0ywfJ5r1eIkG','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','FONE 336-3252',NULL,10,0.00,27,'ANTIQU�RIOS','200.199.95.6',974593507);
INSERT INTO t_anuncio VALUES ('AOem1vZlPiSueYr','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','CONSERTOS FONE 326-4672',NULL,10,0.00,7,'ELETR�NICA','200.199.95.6',974593626);
INSERT INTO t_anuncio VALUES ('AOSQguXHbNL6IkO','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','FONE 327-0438',NULL,10,0.00,7,'ELETOELETR�NICOS','200.199.95.6',974593750);
INSERT INTO t_anuncio VALUES ('AOZZpnsGMSjhnB2','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','FONE 336-1097',NULL,10,0.00,7,'APARELHOS AUDITIVOS','200.199.95.6',974593841);
INSERT INTO t_anuncio VALUES ('AOzzmIs4OynJB5H','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','FONE 886-1697',NULL,10,0.00,5,'APICULTURA','200.199.95.6',974593938);
INSERT INTO t_anuncio VALUES ('AOH5ftWiE0OTfB5','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','FONE 223-4515',NULL,10,0.00,5,'AQU�RIOS - PEIXES ORNAMENTAIS','200.199.95.6',974594049);
INSERT INTO t_anuncio VALUES ('AOnJhuIWao39vRW','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','FONE 241-1673',NULL,10,0.00,17,'AQUECEDOR SOLAR','200.199.95.6',974594135);
INSERT INTO t_anuncio VALUES ('AOcAEfZtXzw0KeQ','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','241-4616',NULL,10,0.00,7,'AR CONDICIONADO','200.199.95.6',974594232);
INSERT INTO t_anuncio VALUES ('AODReOagljDBO2o','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','FONE 221-7657',NULL,10,0.00,7,'ARAME - TELAS','200.199.95.6',974594358);
INSERT INTO t_anuncio VALUES ('AOjPvRXrUgQ4yTZ','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','FONE 324-3475',NULL,10,0.00,27,'ARM�RIOS DE COZINHA','200.199.95.6',974594468);
INSERT INTO t_anuncio VALUES ('AOmSaE7ZB5xSuYA','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','FONE 320-1979',NULL,10,0.00,27,'ARM�RIOS EMBUTIDOS','200.199.95.6',974594543);
INSERT INTO t_anuncio VALUES ('AOQQRPrN9CsOaEZ','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','FONE 223-5930',NULL,10,0.00,7,'ARMAS E MUNI��ES','200.199.95.6',974594624);
INSERT INTO t_anuncio VALUES ('AOagh7tP2gIWpT7','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','FONE 221-6017',NULL,10,0.00,7,'ARTESANATO','200.199.95.6',974594827);
INSERT INTO t_anuncio VALUES ('AOnEPr3Fh0QscpL','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','CONSERTO FONE 223-6496',NULL,10,0.00,17,'ASPIRADORES DE P�','200.199.95.6',974594920);
INSERT INTO t_anuncio VALUES ('AOYC2oKdbNmIk4G','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','FONE 326-7364',NULL,10,0.00,10,'AUTO-ESCOLA','200.199.95.6',974595067);
INSERT INTO t_anuncio VALUES ('AOMwbp1vRcUo0uP','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-19','FONE 336-4858',NULL,1,0.00,17,'AUTOMA��O COMERCIAL','200.199.95.6',974595204);
INSERT INTO t_anuncio VALUES ('AOYCigJ5rFfAcqU','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','FONE 221-6459',NULL,10,0.00,16,'AVES E OVOS','200.199.95.6',974595474);
INSERT INTO t_anuncio VALUES ('AOpVB4GiMgXzjVF','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','FONE 352-2033',NULL,10,0.00,7,'AVIA��O AGR�COLA','200.199.95.6',974595568);
INSERT INTO t_anuncio VALUES ('AOOs05b9fsMKYch','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','FONE 221-4831',NULL,10,0.00,7,'AVIAMENTOS','200.199.95.6',974595708);
INSERT INTO t_anuncio VALUES ('AO4qesWhD7H8mI4','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','FONE 221-5051',NULL,10,0.00,5,'AVICULTURA','200.199.95.6',974595980);
INSERT INTO t_anuncio VALUES ('AOJJS6ciwBV9vJO','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','FONE 352-2037',NULL,10,0.00,7,'AVI�ES - MANUTEN��O E PE�AS','200.199.95.6',974596100);
INSERT INTO t_anuncio VALUES ('AO2Wt5HrNxYsWy2','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','FONE 336-8762',NULL,10,0.00,7,'BALAN�AS','200.199.95.6',974596237);
INSERT INTO t_anuncio VALUES ('AOfn3Vh0uQGaLfZ','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','FONE 223-8094',NULL,10,0.00,7,'BALAN�AS -CONSERTO','200.199.95.6',974596349);
INSERT INTO t_anuncio VALUES ('AOqqkyTflz8uI4i','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','FONE 326-5998',NULL,10,0.00,16,'BALAS E BOMBONS','200.199.95.6',974596461);
INSERT INTO t_anuncio VALUES ('AO7J9nYAcWMn7Rt','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','FONE 355-2033',NULL,10,0.00,23,'BANCA DE JORNAIS E REVISTAS','200.199.95.6',974596579);
INSERT INTO t_anuncio VALUES ('AOYKGLnRtXoCYkO','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','FONE 241-7368',NULL,10,0.00,23,'BANCA DE JORNAIS E REVISTAS','200.199.95.6',974596663);
INSERT INTO t_anuncio VALUES ('AOG2sxLRXbCQcqw','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','FONE326-1792',NULL,10,0.00,27,'BANHEIROS','200.199.95.6',974596875);
INSERT INTO t_anuncio VALUES ('AOssZl5Pzq0uudX','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','FONE 326-2028',NULL,10,0.00,15,'BARBEIRO','200.199.95.6',974596983);
INSERT INTO t_anuncio VALUES ('AOWewBXjp1IO2oC','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','FONE 338-3045',NULL,10,0.00,15,'BARBEIRO','200.199.95.6',974597075);
INSERT INTO t_anuncio VALUES ('AO6YoC6rV9JPiES','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','FONE 221-3523',NULL,10,0.00,15,'ALFAIATE','200.199.95.6',974597195);
INSERT INTO t_anuncio VALUES ('AOPV4U8ezNfBPiw','AEF14hCdGhu5q1u',5,0,'2000-11-18','2000-11-28','FONE 336-3111',NULL,10,0.00,15,'ALFAIATE','200.199.95.6',974597278);
INSERT INTO t_anuncio VALUES ('AOz5DJdzUoYcNhL','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','FONE 327-8233',NULL,10,0.00,16,'BAR E CAF�','200.199.95.6',974597378);
INSERT INTO t_anuncio VALUES ('AOT1dzbUEoeIkVp','AEF14hCdGhu5q1u',1,0,'2000-11-18','2000-11-28','FONE 221-5361',NULL,10,0.00,16,'BEBIDAS','200.199.95.6',974597646);
INSERT INTO t_anuncio VALUES ('AOHPZ5agmQ2nRlP','AEF14hCdGhu5q1u',1,0,'2000-11-19','2000-11-29','FONE 326-4330',NULL,10,0.00,7,'BICICLETAS','200.199.95.6',974627319);
INSERT INTO t_anuncio VALUES ('AOpO6bxLZ5gmIbF','AEF14hCdGhu5q1u',5,0,'2000-11-19','2000-11-29','FONE 357-1040',NULL,10,0.00,7,'BIJOUTERIAS','200.199.95.6',974627444);
INSERT INTO t_anuncio VALUES ('AOuTi8uAVhJPrMg','AEF14hCdGhu5q1u',5,0,'2000-11-19','2000-11-29','LOCADORA FONE 354-2372 ',NULL,10,0.00,18,'BILHAR E SINUCA','200.199.95.6',974627589);
INSERT INTO t_anuncio VALUES ('AOmRTsaglz17kG2','AEF14hCdGhu5q1u',5,0,'2000-11-19','2000-11-29','FONE 336-8080',NULL,10,0.00,18,'BINGOS - PROMO��ES','200.199.95.6',974627678);
INSERT INTO t_anuncio VALUES ('AOvnV1fkGaCYjVp','AEF14hCdGhu5q1u',5,0,'2000-11-19','2000-11-29','FONE 327-3242',NULL,10,0.00,18,'BLOCOS CARNAVALESCOS','200.199.95.6',974627854);
INSERT INTO t_anuncio VALUES ('AOAY8uzV9v5qUo0','AEF14hCdGhu5q1u',5,0,'2000-11-19','2000-11-29','326-4765',NULL,10,0.00,18,'BOITE','200.199.95.6',974628001);
INSERT INTO t_anuncio VALUES ('AOjOQjx16sU8CPb','AEF14hCdGhu5q1u',5,0,'2000-11-19','2000-11-29','FONE 325-5743',NULL,10,0.00,18,'STRIP-TEASE, RELAX FOR MAN','200.199.95.6',974628108);
INSERT INTO t_anuncio VALUES ('AOoFPE8uI4vBXrN','AEF14hCdGhu5q1u',5,0,'2000-11-19','2000-11-29','FONE 325-3500',NULL,10,0.00,18,'BOLICHE','200.199.95.6',974628166);
INSERT INTO t_anuncio VALUES ('AOUcesGhLv5zUw8','AEF14hCdGhu5q1u',1,0,'2000-11-19','2000-11-29','FONE 336-6412',NULL,10,0.00,17,'BOMBAS','200.199.95.6',974628258);
INSERT INTO t_anuncio VALUES ('AOSQ0Q3hvB206ki','AEF14hCdGhu5q1u',5,0,'2000-11-19','2000-11-29','CONSERTO FONE 326-6336',NULL,10,0.00,17,'BOMBAS - CONSERTO','200.199.95.6',974628367);
INSERT INTO t_anuncio VALUES ('AOeeE0CeHbTnZkO','AEF14hCdGhu5q1u',1,0,'2000-11-19','2000-11-29','FONE 354-2366',NULL,10,0.00,17,'BOMBAS HIDR�ULICAS','200.199.95.6',974628457);
INSERT INTO t_anuncio VALUES ('AO8Sa7lrxDW2oCP','AEF14hCdGhu5q1u',1,0,'2000-11-19','2000-11-29','FONE 354-2244',NULL,10,0.00,17,'BOMBAS INJETORAS','200.199.95.6',974628533);
INSERT INTO t_anuncio VALUES ('AONVAy2oKXxTnR4','AEF14hCdGhu5q1u',5,0,'2000-11-19','2000-11-29','FONE 324-2245',NULL,10,0.00,7,'BON�S','200.199.95.6',974628634);
INSERT INTO t_anuncio VALUES ('AOTh20uIbFRWiES','AEF14hCdGhu5q1u',5,0,'2000-11-19','2000-11-29','FONE 221-9762',NULL,10,0.00,7,'BORDADOS','200.199.95.6',974628721);
INSERT INTO t_anuncio VALUES ('AOhF3SecqwX3hfs','AEF14hCdGhu5q1u',5,0,'2000-11-19','2000-11-29','FONE 326-2370',NULL,10,0.00,7,'BORRACHAS','200.199.95.6',974628802);
INSERT INTO t_anuncio VALUES ('AO3zRXzaMgQkNp1','AEF14hCdGhu5q1u',5,0,'2000-11-19','2000-11-29','FONE 338-4040',NULL,10,0.00,7,'BOTAS','200.199.95.6',974628881);
INSERT INTO t_anuncio VALUES ('AOvDa8ekpvHwuIO','AEF14hCdGhu5q1u',1,0,'2000-11-19','2000-11-29','FONE 338-3915',NULL,10,0.00,7,'BOX PARA BANHEIROS','200.199.95.6',974628977);
INSERT INTO t_anuncio VALUES ('AOKo3pDZtOwSC6z','AEF14hCdGhu5q1u',5,0,'2000-11-19','2000-11-29','FONE 326-5074',NULL,10,0.00,7,'BRINDES','200.199.95.6',974629185);
INSERT INTO t_anuncio VALUES ('AOSoa0HNL7joCYc','AEF14hCdGhu5q1u',5,0,'2000-11-19','2000-11-29','FONE 231-4614',NULL,10,0.00,18,'BRINQUEDOS - PARQUE','200.199.95.6',974629250);
INSERT INTO t_anuncio VALUES ('AOasYkyTZdpLY4i','AEF14hCdGhu5q1u',5,0,'2000-11-19','2000-11-29','FONE 326-5256',NULL,10,0.00,17,'BRITADORES','200.199.95.6',974629323);
INSERT INTO t_anuncio VALUES ('AO4yIF1ftzS6sG1','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','FONE 221-6496',NULL,10,0.00,16,'BUFFET','200.199.95.6',974724893);
INSERT INTO t_anuncio VALUES ('AOh3ByEKQ4vtzNT','AEF14hCdGhu5q1u',1,0,'2000-11-20','2000-11-30','GABCABOS S�O PAULO TEL FAX 11-3931-4131',NULL,10,0.00,7,'CABO DE A�O','200.199.95.6',974725135);
INSERT INTO t_anuncio VALUES ('AOTpHVpCY4oJ5r3','AEF14hCdGhu5q1u',1,0,'2000-11-20','2000-11-30','336-2226',NULL,10,0.00,7,'CA�A E PESCA','200.199.95.6',974726638);
INSERT INTO t_anuncio VALUES ('AO1FlHjUo0Q4Fx1','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-21','FONE: 350-1093',NULL,1,0.00,5,'ADESTRAMENTO DE C�ES','200.199.95.6',974727283);
INSERT INTO t_anuncio VALUES ('AOyG6Gndj1cageb','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','FONE: 241-9891',NULL,10,0.00,5,'BELEZA P/ C�ES','200.199.95.6',974727597);
INSERT INTO t_anuncio VALUES ('AOCKqwSXbx7tO2g','AEF14hCdGhu5q1u',1,0,'2000-11-20','2000-11-21','FONE: 336-1059',NULL,1,0.00,5,'PRODUTOS P/ C�ES','200.199.95.6',974727465);
INSERT INTO t_anuncio VALUES ('AOUiY4yT7lNgKeA','AEF14hCdGhu5q1u',1,0,'2000-11-20','2000-11-30','FONE 2216827',NULL,10,0.00,17,'CALCULADORAS','200.199.95.6',974727751);
INSERT INTO t_anuncio VALUES ('AOhLVTY4iobZP2g','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','FONE 336-7321',NULL,10,0.00,17,'CALCULADORAS - CONSERTO','200.199.95.6',974727825);
INSERT INTO t_anuncio VALUES ('AOJv3oSmA4D7Bdr','AEF14hCdGhu5q1u',1,0,'2000-11-20','2000-11-30','FONE 223-6690',NULL,10,0.00,17,'C�MARAS FRIGOR�FICAS','200.199.95.6',974727909);
INSERT INTO t_anuncio VALUES ('AOY8G1mPq26zbEf','AEvyBG1uH2J4pKX',5,0,'2001-04-16','2001-04-26','Ganhe Dinheiro com seu Micro trabalhando em sua casa. Trabalhe muito e ganhe bastante. Envie seus dados e pe�a informa��es gr�tis para: zipzip@uol.com.br ',NULL,10,0.00,8,'TRABALHE J�!!!','200.191.67.221',987396296);
INSERT INTO t_anuncio VALUES ('AOSgW2wRlHhvYkG','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','FONE 324-3182',NULL,10,0.00,15,'CONSERTO DE CAMINH�O','200.199.95.6',974728083);
INSERT INTO t_anuncio VALUES ('AO9bXVgmkqSPjpv','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','FONE 336-7321',NULL,10,0.00,15,'CONSERTO DE CALCULADORAS','200.199.95.6',974728152);
INSERT INTO t_anuncio VALUES ('AOA3d31ekqKQ3pD','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','FONE 241-5820',NULL,10,0.00,5,'CANIS','200.199.95.6',974728252);
INSERT INTO t_anuncio VALUES ('AOVj7tWiE8AF9nJ','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','FONE 223-4221',NULL,10,0.00,15,'CAPOTARIA','200.199.95.6',974736326);
INSERT INTO t_anuncio VALUES ('AOHlT6A4qMjp1uQ','AEF14hCdGhu5q1u',1,0,'2000-11-20','2000-11-30','FONE 344-2780',NULL,10,0.00,7,'CAPOTAS PARA VE�CULOS','200.199.95.6',974736470);
INSERT INTO t_anuncio VALUES ('AO66S6chDRjhCQc','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','FONE 359-2110 ',NULL,10,0.00,15,'CARGAS E ENCOMENDAS','200.199.95.6',974736585);
INSERT INTO t_anuncio VALUES ('AOFVkyESX3nlGUC','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','FONE 221 7493',NULL,10,0.00,7,'CARIMBOS','200.199.95.6',974736673);
INSERT INTO t_anuncio VALUES ('AO4G6NLZXbekyMJ','AEF14hCdGhu5q1u',1,0,'2000-11-20','2000-11-30','FONE 324-5102',NULL,10,0.00,16,'A�OUGUE','200.199.95.6',974736751);
INSERT INTO t_anuncio VALUES ('AOyY8YWTRP9Y4MK','AEF14hCdGhu5q1u',1,0,'2000-11-20','2000-11-30','FONE 326-4020',NULL,10,0.00,27,'CARPETES','200.199.95.6',974736830);
INSERT INTO t_anuncio VALUES ('AOLvMuQcqD5VhuA','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','FONE 241-1496',NULL,10,0.00,15,'LIMPEZA DE CARPETES','200.199.95.6',974736909);
INSERT INTO t_anuncio VALUES ('AOiEkF9v7BU0KeI','AEF14hCdGhu5q1u',1,0,'2000-11-20','2000-11-25','FONE 336-6420',NULL,5,0.00,16,'CASA DE CAF�','200.199.95.6',974737249);
INSERT INTO t_anuncio VALUES ('AORRpvQcqM5jF9v','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-25','FONE 372 1024',NULL,5,0.00,7,'LOTERIA','200.199.95.6',974737358);
INSERT INTO t_anuncio VALUES ('AO9hbKQWiD5rxL6','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-25','FONE 326-3940',NULL,5,0.00,18,'CASA NOTURNA','200.199.95.6',974737465);
INSERT INTO t_anuncio VALUES ('AOOm2wRdzVDYsOa','AEF14hCdGhu5q1u',1,0,'2000-11-20','2000-11-30','FONE 325 3117',NULL,10,0.00,7,'CASAS PR�-FABRICADAS','200.199.95.6',974737658);
INSERT INTO t_anuncio VALUES ('AOow4pD7lPMgC6s','AEF14hCdGhu5q1u',1,0,'2000-11-20','2000-11-30','FONE 326-1875',NULL,10,0.00,24,'LOJA DE CDs','200.199.95.6',974737866);
INSERT INTO t_anuncio VALUES ('AOhyHxDIOUuQ3hn','AEF14hCdGhu5q1u',1,0,'2000-11-20','2000-11-25','FONE 357-3021 ',NULL,5,0.00,24,'LOJA DE CDs','200.199.95.6',974737939);
INSERT INTO t_anuncio VALUES ('AOC0hD7JdzgCeA4','AEF14hCdGhu5q1u',6,0,'2000-11-20','2000-11-21','FONE 231-4940',NULL,1,0.00,24,'LOCADORA DE CDs','200.199.95.6',974738037);
INSERT INTO t_anuncio VALUES ('AOaq5rN9vYOaM8B','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-25','FONE 328-1431',NULL,5,0.00,24,'CDs LOCADORA','200.199.95.6',974738112);
INSERT INTO t_anuncio VALUES ('AOlJg6kqnJVTfki','AEF14hCdGhu5q1u',1,0,'2000-11-20','2000-11-30','FONE 327 7094',NULL,10,0.00,24,'CDs LOJA','200.199.95.6',974738222);
INSERT INTO t_anuncio VALUES ('AOOs06H3x1BOqMg','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','FONE 235-3407',NULL,10,0.00,7,'CESTAS  DE CAF� DA MANH�','200.199.95.6',974738383);
INSERT INTO t_anuncio VALUES ('AObH75awKQcKYbp','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','FONE 983 1494',NULL,10,0.00,7,'CHAVEIRO 24 HORAS','200.199.95.6',974738544);
INSERT INTO t_anuncio VALUES ('AOo0DtrN0m4iwR5','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','ATENDE 24H FONE 327-6691; 981-0523',NULL,10,0.00,7,'CHAVEIRO','200.199.95.6',974738858);
INSERT INTO t_anuncio VALUES ('AOEgywubhvW2oCQ','AEF14hCdGhu5q1u',1,0,'2000-11-20','2000-11-30','377-4054',NULL,10,0.00,16,'CHOCOLATES','200.199.95.6',974738980);
INSERT INTO t_anuncio VALUES ('AOuLiE8mXrLRtOi','AEF14hCdGhu5q1u',4,0,'2000-11-20','2000-11-25','FONE 235-2996',NULL,5,0.00,16,'CHOPERIAS','200.199.95.6',974739094);
INSERT INTO t_anuncio VALUES ('AOiyXjpvQcyMSX3','AEF14hCdGhu5q1u',1,0,'2000-11-20','2000-11-30','FONE 241-6699',NULL,10,0.00,16,'CAMAR�ES','200.199.95.6',974742784);
INSERT INTO t_anuncio VALUES ('AO66ndzNhSkGvBX','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-21','FONE  336-5200',NULL,1,0.00,18,'CINE ART CIDADE','200.199.95.6',974742958);
INSERT INTO t_anuncio VALUES ('AOr3caw0SeF9v7B','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','FONE 338-1014',NULL,10,0.00,18,'CINE SHOPPING FAROL','200.199.95.6',974743050);
INSERT INTO t_anuncio VALUES ('AO1hzxTeAW8ezV9','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-21','FONE 530-1767',NULL,1,0.00,7,'CLORO','200.199.95.6',974743312);
INSERT INTO t_anuncio VALUES ('AOHfN0Sexv5jF0m','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-21','338-1770',NULL,1,0.00,5,'CL�NICA VETERIN�RIA','200.199.95.6',974743409);
INSERT INTO t_anuncio VALUES ('AOSUN1nBP2Y4yDZ','AEF14hCdGhu5q1u',1,0,'2000-11-20','2000-11-21','FONE 325-4121/221-1991',NULL,1,0.00,27,'TUDO EM COLCH�ES','200.199.95.6',974743570);
INSERT INTO t_anuncio VALUES ('AOoacyTZlr0mAyE','AEF14hCdGhu5q1u',1,0,'2000-11-20','2000-11-21','FONE 522-1819',NULL,1,0.00,17,'EQUIPAMENTOS DE COMBUST�O','200.199.95.6',974743684);
INSERT INTO t_anuncio VALUES ('AOAQKIGDJP96syM','AEF14hCdGhu5q1u',1,0,'2000-11-20','2000-11-21','FONE 221-2785',NULL,1,0.00,17,'COMPRESSORES','200.199.95.6',974743885);
INSERT INTO t_anuncio VALUES ('AO8UkGUfBPpuQcy','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-21','326-1517',NULL,1,0.00,7,'COMUNICA��O VISUAL','200.199.95.6',974744114);
INSERT INTO t_anuncio VALUES ('AOx1MS6cqDXV9DI','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','Fone:336-2815',NULL,10,0.00,16,'A saborosa lanches','200.241.146.213',974763440);
INSERT INTO t_anuncio VALUES ('AOkIglzVhDOUoKY','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','Jangadeiros alagoanos, Paju�ara\r\n\r\nFone:327-9680\r\n\r\n',NULL,10,0.00,16,'A M lanches','200.241.146.213',974763556);
INSERT INTO t_anuncio VALUES ('AO00LtHbpSmQcyT','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','Av Alvaro otac�lio\r\n\r\nFone: 231-5146',NULL,10,0.00,16,'Beach Burguer','200.241.146.213',974763639);
INSERT INTO t_anuncio VALUES ('AOcIguYjNhZtOaw','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','R. Sta. Fernanda,\r\n\r\nFone: 231-4213',NULL,10,0.00,16,'Belisk�o lanches','200.241.146.213',974763723);
INSERT INTO t_anuncio VALUES ('AOLhzN16kG0lHVh','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','Jati�ca\r\n\r\nFone:327-5376',NULL,10,0.00,16,'Bibo\'s lanches','200.241.146.213',974763811);
INSERT INTO t_anuncio VALUES ('AOw8V1DfJ5E0uYV','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','Entregamos em domic�lio\r\n\r\nFone: 231-5013',NULL,10,0.00,16,'Bibo\'s lanches II','200.241.146.213',974763885);
INSERT INTO t_anuncio VALUES ('AOeAukpvBzKQ428','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','Av menino Marcelo, serraria\r\n\r\nFone: 328-2799',NULL,10,0.00,16,'Bon apetit','200.241.146.213',974764010);
INSERT INTO t_anuncio VALUES ('AOff1esOT7PbF8K','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','Av J. Davino\r\n\r\nFone:235-1166',NULL,10,0.00,16,'Caf� Brazil','200.241.146.213',974764118);
INSERT INTO t_anuncio VALUES ('AOEUW1Z5bhsqEKP','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','Sucos tropicais\r\n\r\nTratar: 326-1780/ 336-3040/ 221-3895',NULL,10,0.00,16,'Guaran� Express','200.241.146.213',974764320);
INSERT INTO t_anuncio VALUES ('AO8Si8CH3hBH2gu','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','Fone: 357-4004',NULL,10,0.00,16,'Disk Baita P�o','200.241.146.213',974764387);
INSERT INTO t_anuncio VALUES ('AOF1a0Y41RVT6ca','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','Fone: 336-6147',NULL,10,0.00,16,'Passaporte do Ga�cho','200.241.146.213',974764543);
INSERT INTO t_anuncio VALUES ('AOtfpDZsOiSdHVT','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','Fone: 327-8877',NULL,10,0.00,16,'Sarahs Esfiha','200.241.146.213',974764670);
INSERT INTO t_anuncio VALUES ('AONVXxuAGM639fl','AEF14hCdGhu5q1u',2,0,'2000-11-20','2000-11-30','Av Menino Marcelo, Serraria\r\n\r\nDisk:328-1431',NULL,10,0.00,24,'Cd  V�cio','200.241.146.213',974765432);
INSERT INTO t_anuncio VALUES ('AOAkBXz3wSAWOiL','AEF14hCdGhu5q1u',5,0,'2000-11-20','2000-11-30','Lav a seco, tecnologia italiana, \r\nlavamos e passamos em 1h, captamos e entregamos em domicilio.\r\n\r\nTratar: 325-7502',NULL,10,0.00,15,'Lavanderia Lav -Tudo','200.241.146.213',974764968);
INSERT INTO t_anuncio VALUES ('AOOkSeHb1eQ4qMf','AEF14hCdGhu5q1u',2,0,'2000-11-20','2000-11-30','Fone: 231-4940',NULL,10,0.00,24,'Jr Laser Locadora','200.241.146.213',974765488);
INSERT INTO t_anuncio VALUES ('AOykK6sN9DdPaES','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-12-01','Br atalaia, 319\r\n\r\nTratar: 221-0756',NULL,10,0.00,15,'Lavanderia espum�o','200.241.146.213',974796816);
INSERT INTO t_anuncio VALUES ('AOdddMKQOT5396W','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-12-01','Lavamos a seco, washouse\r\n\r\nTratar: 327-2291',NULL,10,0.00,15,'Lavanderia com self-service','200.241.146.213',974796911);
INSERT INTO t_anuncio VALUES ('AOYuanlzVhAWqMZ','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-12-01','Estofados, carpetes, bancos de aut. etc...\r\nTecn. patenteada. Atendemos em domicilio\r\n\r\nFone: 231-6982/ 974-7258',NULL,10,0.00,15,'Imperlav ( lavanderia)','200.241.146.213',974797061);
INSERT INTO t_anuncio VALUES ('AO4sKAN9vJE0mIW','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-12-01','Av julio marques luz, Jati�ca\r\n\r\nFone: 325-4466',NULL,10,0.00,15,'Lavanderia lig -lav delivery','200.241.146.213',974797146);
INSERT INTO t_anuncio VALUES ('AO1FlrFSekU05rF','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-12-01','R. sol, centro\r\n\r\nTratar: 221-1315',NULL,10,0.00,15,'El�i Leiloeiros','200.241.146.213',974797233);
INSERT INTO t_anuncio VALUES ('AOVHQ4qEJXbhuIW','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-12-01','Ladislau neto, centro\r\n\r\nTratar: 338-2500/ 223-5212',NULL,10,0.00,15,'Leil�es Freire','200.241.146.213',974797326);
INSERT INTO t_anuncio VALUES ('AOKgFvJdiwQOinB','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-12-01','Nossa livraria, Av da paz\r\n\r\nFone: 326-3695',NULL,10,0.00,23,'Livros Jur�dicos','200.241.146.213',974797464);
INSERT INTO t_anuncio VALUES ('AOR7oYbhf5pmAyE','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-12-01','R do Sol, centro\r\n\r\nFone: 336-7778',NULL,10,0.00,23,'Livros religiosos (Agape)','200.241.146.213',974797573);
INSERT INTO t_anuncio VALUES ('AOOkdbpfc26W1JP','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-12-01','Livraria nossa senhora dA ROSA M�stica, Av Jo�o Davino\r\n\r\nFone: 325-5691',NULL,10,0.00,23,'Livraria Religiosa','200.241.146.213',974797728);
INSERT INTO t_anuncio VALUES ('AO2GAhnXaocqDJ5','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-12-01','Livraria cultura Med, \r\n\r\nR. prof V. Campos\r\n\r\nFone: 326-7103',NULL,10,0.00,23,'Livros t�cnicos','200.241.146.213',974797836);
INSERT INTO t_anuncio VALUES ('AOCjsq2E7tjFp0m','AEF14hCdGhu5q1u',3,0,'2000-11-21','2000-12-01','Compra, venda e troca\r\n\r\nFone: 326-2944',NULL,10,0.00,23,'Livros usados ( Afarrabio)','200.241.146.213',974797922);
INSERT INTO t_anuncio VALUES ('AOcFX3pKYcgmHbN','AEF14hCdGhu5q1u',3,0,'2000-11-21','2000-12-01','Dr. Pontes de Miranda, 37\r\n\r\nFone: 336-7387',NULL,10,0.00,23,'Miranda Afarrabio','200.241.146.213',974797992);
INSERT INTO t_anuncio VALUES ('AOnNlrMSesWhD7t','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-12-01','Fone: 336-1884',NULL,10,0.00,7,'Casa das Mangueiras','200.241.146.213',974798103);
INSERT INTO t_anuncio VALUES ('AOlu9Z5bguqotz3','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-12-01','Fone: 325-6092',NULL,10,0.00,17,'Blend Caf� Expresso','200.241.146.213',974798228);
INSERT INTO t_anuncio VALUES ('AOoyXpvJOUekxLR','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-12-01','Dep J. Lages\r\n\r\nTratar: 231-7862',NULL,10,0.00,3,'Bom gosto refei��es','200.241.146.213',974798308);
INSERT INTO t_anuncio VALUES ('AOD1agCQbxZdrM8','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-12-01','Tratar: 241-1588',NULL,10,0.00,3,'Cabana Marmitas e self service','200.241.146.213',974798365);
INSERT INTO t_anuncio VALUES ('AOTbQcy2gBVpD6k','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-12-01','Refei��es naturais em domicilio\r\n\r\nTratar: 326-5193',NULL,10,0.00,3,'Nativa restaurante natural','200.241.146.213',974798442);
INSERT INTO t_anuncio VALUES ('AOfvNSmsGUdjxLQ','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-12-01','Tratar: 326-3035',NULL,10,0.00,3,'Quentinhas Ver�nicas','200.241.146.213',974798491);
INSERT INTO t_anuncio VALUES ('AODTvlHVoScaglj','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-12-01','Fone: 327-6760',NULL,10,0.00,20,'Belvedere Massagens','200.241.146.213',974798568);
INSERT INTO t_anuncio VALUES ('AOyqrpnByEsqvRP','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-12-01','Fone: 336-4999',NULL,10,0.00,20,'Cats ( centro alternativo para tratamento de sa�de','200.241.146.213',974798638);
INSERT INTO t_anuncio VALUES ('AO8UNvljoeyolrp','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-12-01','Tratar: 973-8545',NULL,10,0.00,20,'K�ssia massagens manicure e depila��o','200.241.146.213',974798689);
INSERT INTO t_anuncio VALUES ('AODfE0mQcyZlXr3','AEF14hCdGhu5q1u',1,0,'2000-11-21','2000-12-01','FONE 221-0069',NULL,10,0.00,16,'CASA DO TEMPERO','200.199.95.6',974848832);
INSERT INTO t_anuncio VALUES ('AOHPfP2oCIUZlP3','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-11-22','FONE 324-3848',NULL,1,0.00,7,'CONCRETO','200.199.95.6',974848928);
INSERT INTO t_anuncio VALUES ('AO223LR5jgIO27t','AEF14hCdGhu5q1u',1,0,'2000-11-21','2000-11-22','FONE 326-2509',NULL,1,0.00,7,'MODA PRAIA','200.199.95.6',974849135);
INSERT INTO t_anuncio VALUES ('AOkcm49flreciou','AEF14hCdGhu5q1u',1,0,'2000-11-21','2000-11-22','FONE 326-6275',NULL,1,0.00,7,'CONFEC��ES EM ATACADO','200.199.95.6',974849236);
INSERT INTO t_anuncio VALUES ('AOxF420uIVnBioC','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-11-22','FONE 326-2916',NULL,1,0.00,7,'C�PIAS XEROGR�FICAS','200.199.95.6',974849375);
INSERT INTO t_anuncio VALUES ('AOPPZPxmciYFvlr','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-11-22','FONE 327-6679',NULL,1,0.00,7,'ATELI� DOURADO','200.199.95.6',974849648);
INSERT INTO t_anuncio VALUES ('AOnDEuQxDRNSeky','AEF14hCdGhu5q1u',1,0,'2000-11-21','2000-11-22','FONE 235-2412',NULL,1,0.00,27,'CORTINAS CRIATIVA','200.199.95.6',974849792);
INSERT INTO t_anuncio VALUES ('AOykSmI3x1ly2oS','AEF14hCdGhu5q1u',1,0,'2000-11-21','2000-11-22','COURO FONE 223-7643',NULL,1,0.00,7,'PLASTICOURO','200.199.95.6',974849907);
INSERT INTO t_anuncio VALUES ('AOOcRdXz3p6scOq','AEF14hCdGhu5q1u',1,0,'2000-11-21','2000-11-22','FONE 241-8984',NULL,1,0.00,27,'COZINHAS - DECORA��O E INSTALA��O','200.199.95.6',974850062);
INSERT INTO t_anuncio VALUES ('AOQC2K53hfGM0ez','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-11-22','ESPA�O EDUCAR FONE 327-5285',NULL,1,0.00,7,'CRECHE ESCOLA','200.199.95.6',974850198);
INSERT INTO t_anuncio VALUES ('AO3pXFuAGUmrFTZ','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-11-22','FONE 326-2123',NULL,1,0.00,15,'A NIQUELADORA  -  CROMAGEM','200.199.95.6',974850348);
INSERT INTO t_anuncio VALUES ('AOmhOUgezxB5qMS','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-11-22','FONE 325-3609',NULL,1,0.00,27,'ART  DECORA��O','200.199.95.6',974850464);
INSERT INTO t_anuncio VALUES ('AOuCDdjpmkouIFL','AEF14hCdGhu5q1u',5,0,'2000-11-21','2000-11-22','MAC-INSET  FONE 320-1132',NULL,1,0.00,15,'DEDETIZA��O, DESRATIZA��O E OUTROS','200.199.95.6',974850704);
INSERT INTO t_anuncio VALUES ('AOSFyE7B5zhKeI4','AEF14hCdGhu5q1u',5,0,'2000-11-22','2000-11-23','Fone 221-1687 ',NULL,1,0.00,15,'Depila��o Personalizada','200.199.95.6',974888720);
INSERT INTO t_anuncio VALUES ('AOO5sGUZdbvJ4iw','AEF14hCdGhu5q1u',5,0,'2000-11-22','2000-11-23','Jos� Ara�jo Fone 241-0590',NULL,1,0.00,15,'DESENHISTA','200.199.95.6',974888866);
INSERT INTO t_anuncio VALUES ('AOLf2gC6kGfJlXP','AEF14hCdGhu5q1u',5,0,'2000-11-22','2000-11-23','Comese 231-7740',NULL,1,0.00,15,'DESENTUPIMENTO','200.199.95.6',974888977);
INSERT INTO t_anuncio VALUES ('AOzPdEm4Etp7XUS','AEF14hCdGhu5q1u',5,0,'2000-11-22','2000-11-23','FONE 983-3156',NULL,1,0.00,15,'DESPACHANTE DE IM�VEIS','200.199.95.6',974889268);
INSERT INTO t_anuncio VALUES ('AOQJukGLR5FvsGU','AEF14hCdGhu5q1u',5,0,'2000-11-22','2000-11-23','BRAND�O FONE 221-8397 ',NULL,1,0.00,15,'DESPACHANTE','200.199.95.6',974889422);
INSERT INTO t_anuncio VALUES ('AOHj5VKAGD531YW','AEF14hCdGhu5q1u',5,0,'2000-11-22','2000-11-23','ROBERVAL 221-9176',NULL,1,0.00,15,'DETETIVE','200.199.95.6',974889490);
INSERT INTO t_anuncio VALUES ('AO8wOLZX3hWU8er','AEF14hCdGhu5q1u',5,0,'2000-11-23','2000-11-24','FONE 327-1946',NULL,1,0.00,27,'DECORADOR','200.199.95.6',975005014);
INSERT INTO t_anuncio VALUES ('AOXXZ5bouA27lzV','AEF14hCdGhu5q1u',5,0,'2000-11-23','2000-11-24','Paju�ara Servi�os Fone 337-1293',NULL,1,0.00,15,'FAXINEIRA  E DOM�STICA','200.199.95.6',975005147);
INSERT INTO t_anuncio VALUES ('AOD1GM0YW1tzFKI','AEF14hCdGhu5q1u',5,0,'2000-11-23','2000-11-24','FONE 231-5636',NULL,1,0.00,15,'ELETRODOM�TICOS - CONSERTO','200.199.95.6',975005303);
INSERT INTO t_anuncio VALUES ('AO0YKIFvtrKQGMS','AEF14hCdGhu5q1u',1,0,'2000-11-23','2000-11-24','Ponto Certo 231-5636',NULL,1,0.00,22,'ELETRODOM�TICOS','200.199.95.6',975005419);
INSERT INTO t_anuncio VALUES ('AOb70YcqERrN1nk','AEF14hCdGhu5q1u',5,0,'2000-11-23','2000-11-24','Fone 221-0048',NULL,1,0.00,22,'Conserto de Eletrodom�sticos','200.199.95.6',975005538);
INSERT INTO t_anuncio VALUES ('AOyc7lzEgCqvRXj','AEF14hCdGhu5q1u',1,0,'2000-11-23','2000-11-24','Casa das Embalagens Fone 221-5975',NULL,1,0.00,7,'EMBALAGENS','200.199.95.6',975006547);
INSERT INTO t_anuncio VALUES ('AOAI8XbhL7qwKYk','AEF14hCdGhu5q1u',1,0,'2000-11-23','2000-11-24','Casa Mabel Fone 223-6462',NULL,1,0.00,7,'Embalagens','200.199.95.6',975006793);
INSERT INTO t_anuncio VALUES ('AOjh4iMgBz1mYs4','AEF14hCdGhu5q1u',5,0,'2000-11-23','2000-11-24','Encaderna��o e Recupera��o de Livros Fone 327-7898\r\n',NULL,1,0.00,15,'ENCADERNADOR','200.199.95.6',975008241);
INSERT INTO t_anuncio VALUES ('AOiMbhDJOU06jpD','AEF14hCdGhu5q1u',5,0,'2000-11-23','2000-11-24','Jos� Guedes Fone 324-4860',NULL,1,0.00,15,'ENCANADOR','200.199.95.6',975008370);
INSERT INTO t_anuncio VALUES ('AO6SioBP3hcqM0e','AEF14hCdGhu5q1u',5,0,'2000-11-23','2000-11-24','FONE 327-2266',NULL,1,0.00,15,'DISK ENTULHO','200.199.95.6',975008529);
INSERT INTO t_anuncio VALUES ('AOqGebpDJ4wCQW9','AEF14hCdGhu5q1u',1,0,'2000-11-23','2000-11-24','FONE 325-6993',NULL,1,0.00,7,'ENXOVAIS PARA BEB�','200.199.95.6',975008735);
INSERT INTO t_anuncio VALUES ('AOgEV1nR5aoCQ49','AEF14hCdGhu5q1u',1,0,'2000-11-23','2000-11-24','Rua do Sol, 117 Centro Fone 326-5701 ',NULL,1,0.00,7,'MATERIAL EL�TRICO E UTILIDADES DOM�STICAS','200.199.95.6',975008888);
INSERT INTO t_anuncio VALUES ('AO2OAivBPVmkyE0','AEF14hCdGhu5q1u',1,0,'2000-11-23','2000-11-24','FONE 221-0567',NULL,1,0.00,7,'EAQUIPAMENTOS ELETR�NICOS','200.199.95.6',975009016);
INSERT INTO t_anuncio VALUES ('AOPH75aSPjRXrwK','AEF14hCdGhu5q1u',1,0,'2000-11-23','2000-11-24','EQUIMAQ FONE 327-5571',NULL,1,0.00,17,'EQUIPAMENTOS INDUSTRIAIS','200.199.95.6',975009127);
INSERT INTO t_anuncio VALUES ('AOaoyvR5bpQ4iwJ','AEF14hCdGhu5q1u',1,0,'2000-11-23','2000-11-24','FONE 336-7674',NULL,1,0.00,7,'EQUIPAMENTOS ODONTOL�GICOS','200.199.95.6',975009255);
INSERT INTO t_anuncio VALUES ('AOU2ciglrp528eA','AEF14hCdGhu5q1u',1,0,'2000-11-23','2000-11-24','DIFER  FONE 336-6656',NULL,1,0.00,7,'EQUIPAMENTOS DE PROTE��O INDIVIDUAL','200.199.95.6',975009372);
INSERT INTO t_anuncio VALUES ('AO2akF1fJ5ESmAW','AEF14hCdGhu5q1u',5,0,'2000-11-24','2000-11-25','Ball Hal Fone 327-3535',NULL,1,0.00,10,'ESCOLA DE MERGULHO','200.199.95.6',975072381);
INSERT INTO t_anuncio VALUES ('AOJ7VgKm6A9LnJt','AEF14hCdGhu5q1u',5,0,'2000-11-24','2000-11-25','VILLA-LOBOS FONE 231-1237\r\nE-mail villalobos@sunnet.com.br',NULL,1,0.00,10,'ESCOLA  DE M�SICA','200.199.95.6',975072516);
INSERT INTO t_anuncio VALUES ('AOvxHMSY49P3pDI','AEF14hCdGhu5q1u',5,0,'2000-11-24','2000-11-25','Nado Livre Fone 223-3044',NULL,1,0.00,10,'ESCOLA DE NATA��O','200.199.95.6',975072593);
INSERT INTO t_anuncio VALUES ('AOUkY4iDR5FT6AO','AEF14hCdGhu5q1u',5,0,'2000-11-24','2000-11-25','A Bola Cheia Fone 221-4868',NULL,1,0.00,18,'ARTIGOS PARA ESPORTES','200.199.95.6',975072733);
INSERT INTO t_anuncio VALUES ('AOzHZdjE0mOiD7B','AEF14hCdGhu5q1u',1,0,'2000-11-24','2000-11-25','Sport Center 223-2130',NULL,1,0.00,18,'MATERIAL ESPORTIVO','200.199.95.6',975072805);
INSERT INTO t_anuncio VALUES ('AOkAwPbh7kUgKXj','AEF14hCdGhu5q1u',1,0,'2000-11-24','2000-11-25','Shopping do Atleta Fone 325-5517',NULL,1,0.00,18,'ROUPAS PARA ESPORTES','200.199.95.6',975072951);
INSERT INTO t_anuncio VALUES ('AOaaciLZtPoK6AO','AEF14hCdGhu5q1u',1,0,'2000-11-24','2000-11-25','AMAZONAS FONE 326-2370',NULL,1,0.00,7,'ESPUMAS - PL�TICOS - COURO','200.199.95.6',975073060);
INSERT INTO t_anuncio VALUES ('AOQuwBH3hvGMS6b','AEF14hCdGhu5q1u',5,0,'2000-11-24','2000-11-25','Cl�nica de est�tica FONE 327-1551 ',NULL,1,0.00,7,'ESTETICISTA','200.199.95.6',975073266);
INSERT INTO t_anuncio VALUES ('AOK0iguzN1JPaou','AEF14hCdGhu5q1u',5,0,'2000-11-24','2000-11-25','FONE 231-7494',NULL,1,0.00,7,'EST�TICA  CORPO E MENTE','200.199.95.6',975073374);
INSERT INTO t_anuncio VALUES ('AOC0W2vZtPxC6sO','AEF14hCdGhu5q1u',5,0,'2000-11-24','2000-11-25','FONE 351-6412',NULL,1,0.00,7,'EST�TICA CORPORAL','200.199.95.6',975073447);
INSERT INTO t_anuncio VALUES ('AOGy5FLRGMecyLB','AEF14hCdGhu5q1u',5,0,'2000-11-24','2000-11-25','J�nior Toledo 327-9468',NULL,1,0.00,15,'ESTILISTA  ALTA COSTURA','200.199.95.6',975073556);
INSERT INTO t_anuncio VALUES ('AOZnN0mQcyZdzVp','AEF14hCdGhu5q1u',1,0,'2000-11-24','2000-11-25','Tudo em estopas Fone 241-1385/241-1614',NULL,1,0.00,7,'ESTOPEX','200.199.95.6',975073702);
INSERT INTO t_anuncio VALUES ('AOrNXEKYcxZdrFK','AEF14hCdGhu5q1u',1,0,'2000-11-24','2000-11-25','Fone 322-3338',NULL,1,0.00,7,'Estopas','200.199.95.6',975073774);
INSERT INTO t_anuncio VALUES ('AOuuoXb9nlECQOv','AEF14hCdGhu5q1u',1,0,'2000-11-24','2000-11-25','FONE 328-1520',NULL,1,0.00,7,'ESTRUTURAS  MET�LICAS','200.199.95.6',975073919);
INSERT INTO t_anuncio VALUES ('AOzdvBPawSsGT7l','AEF14hCdGhu5q1u',1,0,'2000-11-24','2000-11-25','FONE 983-2251',NULL,1,0.00,7,'ETIQUETAS E SACOLAS','200.199.95.6',975074015);
INSERT INTO t_anuncio VALUES ('AOHtLI4iESP3pL7','AEF14hCdGhu5q1u',5,0,'2000-11-24','2000-11-25','KINGS FONE 223-6688',NULL,1,0.00,7,'EVENTOS -ORGANIZA��O E PROMOC�O','200.199.95.6',975074191);
INSERT INTO t_anuncio VALUES ('AOdepuH2nQVgBWh','AEIc7sN8B49C5y1',6,32,'2001-05-11','2001-05-21','COMPRO MEM�RIA SIM PENTES DE 16 OU DE 32MEGAS',NULL,10,0.00,19,'MEM�RIA SIM','200.199.95.127',989600243);
INSERT INTO t_anuncio VALUES ('AOvprxT6sOgCXrF','AEF14hCdGhu5q1u',1,0,'2000-11-24','2000-11-25','EXTIMAQ  FONE 221-6736',NULL,1,0.00,7,'EXTINTORES DE INC�NDIO','200.199.95.6',975083666);
INSERT INTO t_anuncio VALUES ('AO5PnsOaE8bhvRl','AEF14hCdGhu5q1u',5,0,'2000-11-24','2000-11-25','Assist�ncia T�cnica em Fax FONE 221-1430',NULL,1,0.00,15,'FAC-S�MILE - ASSIST�NCIA T�CNICA','200.199.95.6',975083825);
INSERT INTO t_anuncio VALUES ('AOMaPNhDR4SYsGT','AEF14hCdGhu5q1u',1,0,'2000-11-24','2000-11-25','FONE 320-1837',NULL,1,0.00,7,'DISK DROGARIA','200.199.95.6',975083935);
INSERT INTO t_anuncio VALUES ('AOzVGPFL7cECYcp','AEF14hCdGhu5q1u',1,0,'2000-11-24','2000-11-25','FONE 800-3636/ 0 800 82-3636',NULL,1,0.00,7,'MEDICAMENTOS EM DOMIC�LIO','200.199.95.6',975084177);
INSERT INTO t_anuncio VALUES ('AOTn1Z4iwCV1nBP','AEF14hCdGhu5q1u',1,0,'2000-11-24','2000-11-25','FONE 326-5546',NULL,1,0.00,7,'FARM�CIA DE MANIPULA��O','200.199.95.6',975084290);
INSERT INTO t_anuncio VALUES ('AOOIguY3pvJPU0I','AEF14hCdGhu5q1u',1,0,'2000-11-24','2000-11-25','FONE 221-7066',NULL,1,0.00,7,'NEGUINHO FERRO VELHO','200.199.95.6',975084407);
INSERT INTO t_anuncio VALUES ('AODnqDYjMfI3wZk','AEIc7sN8B49C5y1',1,1,'2001-05-11','2001-05-21','COMPLET�SSIMA DE TUDO BRANCA 4 PORTAS, AR CONDICIONADO, VIDRO EL�TRICO, TRAVAS EL�TRICAS,QUANDO ACIONA O ALARME OS VIDROS FECHAM AUTOMATICAMENTE,PELICULA FUM� IMPORTADA, ENCOSTO DE CABE�A NOS BANCOS TRASEIROS,M�DULO QUE AVISA P/ COLOCAR CINTO DE SEGURAN�A',NULL,10,9500.00,1,'ELBA 96 1.6 I.E.','200.199.95.127',989600148);
INSERT INTO t_anuncio VALUES ('AO5vinAV8tsxSlG','AE6QVY26aeinrvz',3,1,'2001-05-11','2001-05-21','Verde','jpg',10,24000.00,1,'Marea ELX / 99     (Completo)','200.241.146.213',989618437);
INSERT INTO t_anuncio VALUES ('AO2iQ4pDZlV0mIp','AEF14hCdGhu5q1u',5,0,'2000-11-24','2000-11-25','FONE 322-2332',NULL,1,0.00,7,'FIBRA DE VIDROS - FABRICA��O E SERVI�OS','200.199.95.6',975085341);
INSERT INTO t_anuncio VALUES ('AOHGZ4pKdGLv7iO','AE4yBWpKlGTmPqL',1,0,'2001-05-09','2001-05-19','Vende-se um Microsc�pio Oftalmol�gico D.F. Vasconcelos, ano 78, quase completo (s� est� faltando o pedal), com camara fotogr�fica Pratika.\r\nTratar 2310400',NULL,10,2000.00,7,'Microsc�pio Oftalmol�gico D.F. Vasconcelos','200.241.151.147',989420194);
INSERT INTO t_anuncio VALUES ('AOzjAagmANflzMS','AEF14hCdGhu5q1u',5,0,'2000-11-24','2000-11-25','FONE 231-4588',NULL,1,0.00,7,'FILMES - PRODUTORA','200.199.95.6',975085966);
INSERT INTO t_anuncio VALUES ('AO9rZP2oKQinR5r','AEF14hCdGhu5q1u',1,0,'2000-11-24','2000-11-25','FONE 325-3159',NULL,1,0.00,27,'FLORES  ARTIFICIAIS E DESIDRATADAS','200.199.95.6',975087594);
INSERT INTO t_anuncio VALUES ('AO1LNfWMm4Rzh74','AEF14hCdGhu5q1u',5,0,'2000-11-24','2000-11-25','MABEL FLORES FONE 338-3727',NULL,1,0.00,27,'FLORES - ORNAMENTA��O','200.199.95.6',975087778);
INSERT INTO t_anuncio VALUES ('AOrhzNSQ42lrFvB','AEF14hCdGhu5q1u',1,0,'2000-11-24','2000-11-25','FONE 350-2176',NULL,1,0.00,27,'FLORICULTURA - 24H','200.199.95.6',975087879);
INSERT INTO t_anuncio VALUES ('AO0oGMS5jpRWaom','AEF14hCdGhu5q1u',5,0,'2000-11-24','2000-11-25','CONSERTO - FONE 221-3328',NULL,1,0.00,15,'FORNO MICROONDAS','200.199.95.6',975087988);
INSERT INTO t_anuncio VALUES ('AOE2cpTnJdUgSmY','AEF14hCdGhu5q1u',5,0,'2000-11-24','2000-11-25','FONE 223-8244',NULL,1,0.00,15,'FOG�ES - CONSERTO','200.199.95.6',975088055);
INSERT INTO t_anuncio VALUES ('AOXJDJcGM8QkiwS','AEF14hCdGhu5q1u',5,0,'2000-11-24','2000-11-25','FONE 338-1722',NULL,1,0.00,15,'LIMPADORA  DE  FOSSAS','200.199.95.6',975088205);
INSERT INTO t_anuncio VALUES ('AOhN53TYWU6VhvB','AEF14hCdGhu5q1u',5,0,'2000-11-24','2000-11-25','TADEU FOTOS FONE 231-0542/327-1933',NULL,1,0.00,7,'FOTOGRAFIAS A�REAS','200.199.95.6',975088301);
INSERT INTO t_anuncio VALUES ('AOrzZWaguH9nJX2','AEF14hCdGhu5q1u',5,0,'2000-11-24','2000-11-25','FONE 327-4845',NULL,1,0.00,15,'FOTOGRAFIAS - RESTAURA��ES','200.199.95.6',975088388);
INSERT INTO t_anuncio VALUES ('AOtJ97lO2oINhvR','AEF14hCdGhu5q1u',5,0,'2000-11-24','2000-11-25','CIRURGI�O DOS SAPATOS FONE 221-5849',NULL,1,0.00,15,'SAPATOS - CONSERTO','200.199.95.6',975088550);
INSERT INTO t_anuncio VALUES ('AOtSFSdqTei7kF8','AE4yBWpKlGTmPqL',1,0,'2001-05-09','2001-05-19','Muitos Jogos de Playstation para voce. Na regi�o da Paju�ara, Jati�ca e Ponta verde eu entrego em casa. V� no seguinte endere�o:\r\nhttp://www.angelfire.com/scifi/loptus/psx.html\r\nPara ver a lista dos jogos.Fa�o 5 cds por 55 reais e 10 por 100 reais.\r\n',NULL,10,13.00,24,'Jogos de Playstation','200.241.151.147',989429553);
INSERT INTO t_anuncio VALUES ('AOGieAVpTfXiEgS','AEF14hCdGhu5q1u',5,0,'2000-11-24','2000-11-25','FONE 221-9027',NULL,1,0.00,27,'CASA DO GESSO','200.199.95.6',975088913);
INSERT INTO t_anuncio VALUES ('AO88N9nB4ieA4x9','AEF14hCdGhu5q1u',5,0,'2000-11-24','2000-11-25','TODO SERVI�O GR�FICO FONE 241-3602 ',NULL,1,0.00,7,'GR�FICA MASCARENHAS','200.199.95.6',975089056);
INSERT INTO t_anuncio VALUES ('AOWcJHVhvQiESer','AEF14hCdGhu5q1u',1,0,'2000-11-24','2000-11-25','MILENAR FONE 328-1815',NULL,1,0.00,7,'GRANITO E PEDRAS DECORATIVAS','200.199.95.6',975089159);
INSERT INTO t_anuncio VALUES ('AOUwGDRzxD4aouH','AEF14hCdGhu5q1u',1,0,'2000-11-24','2000-11-25','CACTUS FONE 326-6683',NULL,1,0.00,25,'INSTRUMENTOS MUSICAIS','200.199.95.6',975089268);
INSERT INTO t_anuncio VALUES ('AO2q4USXFLBywus','AEF14hCdGhu5q1u',1,0,'2000-11-24','2000-11-25','FONE 223-3677',NULL,1,0.00,25,'CASA DO M�SICO','200.199.95.6',975089370);
INSERT INTO t_anuncio VALUES ('AO8pOLAFKPvAFSX','AE80FujwtGfjoBG',5,0,'2001-05-16','2001-05-26','Conserte o seu computador pagando apenas R$35,00 (taxa de visita para as primeiras 02 horas; acr�scimo de R$10,00 para cada hora adicional). Servi�os com qualidade e garantia.',NULL,10,35.00,15,'Assit�ncia T�cnica para Computadores','200.191.20.17',990000706);
INSERT INTO t_anuncio VALUES ('AOgMO1nR5rmAOao','AEKctXj3NhQsWya',1,21,'2000-11-25','2000-12-05','K6 II 300Mhz, 64Mb de Ram,HD 4.3Gb, Fax 56K, Kit Multim�dia 44x, + Teclado e Mouse . (Sem Monitor) ',NULL,10,650.00,19,'-- K6 II 300Mhz --','200.199.50.177',975165649);
INSERT INTO t_anuncio VALUES ('AOfTbDIGMSY39f4','AEF14hCdGhu5q1u',1,39,'2000-11-25','2000-12-05','VENDO 486-Dx2, 50 HMZ, 8MB, PLACA DE FAX MODEM E ESTABILIZADOR FONE 327.6483/327.4416',NULL,10,0.00,19,'486-DX 2  S/ HD C/ MONITOR E IMPRESSORA','200.199.95.6',975201619);
INSERT INTO t_anuncio VALUES ('AO1J0QW2fJjxDYs','AEF14hCdGhu5q1u',1,0,'2000-11-25','2000-11-26','ATENDO EM DOMIC�LIO FONE 338-4780/979-0355',NULL,1,0.00,7,'RITA CONFEC��ES','200.199.95.6',975201757);
INSERT INTO t_anuncio VALUES ('AOG6omPjxTtaoKP','AEF14hCdGhu5q1u',2,10,'2000-11-25','2000-12-05','3QTS(SUITE), C/ARM�RIOS EMBUTIDOS, C/TEL\r\nFONE 327-6685/981-0845 ',NULL,10,560.00,2,'ALUGO APTO JAQUES LACAN','200.199.95.6',975202174);
INSERT INTO t_anuncio VALUES ('AOaGYN1DZXoCQ4i','AEF14hCdGhu5q1u',2,10,'2000-11-25','2000-11-30','EDF BRUMAS, PINTADO, C/ ARM�RIOS NOVOS. ',NULL,5,500.00,2,'ALUGO APTO PONTA VERDE','200.199.95.6',975202340);
INSERT INTO t_anuncio VALUES ('AOD9b16ca8z9nsq','AEF14hCdGhu5q1u',1,10,'2000-11-25','2000-12-05','QUITADO. FONE 241-5804/93410066',NULL,10,24000.00,2,'VENDO APTO NO RUI PALMEIRA','200.199.95.6',975202581);
INSERT INTO t_anuncio VALUES ('AOWWQNhDZlU0C6s','AEF14hCdGhu5q1u',1,0,'2000-11-25','2000-11-26','PRE�O A COMBINAR FONE 338-5182',NULL,1,0.00,7,'VENDO F�BRICA DE VINAGRE','200.199.95.6',975202767);
INSERT INTO t_anuncio VALUES ('AODTF0uIcyTfty2','AEF14hCdGhu5q1u',1,12,'2000-11-25','2000-11-26','54X80, DE ESQUINA FONE 337-2164',NULL,1,0.00,2,'TERRENO NA DEP. JOS� LAGES','200.199.95.6',975202898);
INSERT INTO t_anuncio VALUES ('AOFjJyMSzFZ5joC','AEF14hCdGhu5q1u',5,0,'2000-11-25','2000-11-26','PRECISO TRABALHAR. TENHO CARRO. FONE 979-6482 ',NULL,1,0.00,8,'MOTORISTA OU MOTOQUEIRO','200.199.95.6',975203075);
INSERT INTO t_anuncio VALUES ('AOjXCYsWivHVpX5','AEF14hCdGhu5q1u',1,0,'2000-11-25','2000-11-26','241-7905, 982-1539',NULL,1,0.00,24,'CDs VIRGEM P/ GRAVA��O','200.199.95.6',975203270);
INSERT INTO t_anuncio VALUES ('AOu8TBjpmWZ5bhv','AEF14hCdGhu5q1u',1,0,'2000-11-25','2000-12-05','VENDO FONE 327-6183',NULL,10,0.00,25,'TECLADO TECHNICS KN-1600','200.199.95.6',975203393);
INSERT INTO t_anuncio VALUES ('AOYu2CAxndhYkig','AEF14hCdGhu5q1u',1,0,'2000-11-25','2000-11-26','SEMI-INDUSTRIAL FONE 221-6259',NULL,1,0.00,17,'M�QUINA OVERLOK  E GALONEIRA','200.199.95.6',975203499);
INSERT INTO t_anuncio VALUES ('AO6KioCPV9BWqES','AEF14hCdGhu5q1u',1,0,'2000-11-25','2000-11-26','NOVO, 500 L,  FONE 371-8034/971-2232',NULL,1,0.00,22,'FREEZER HORIZONTAL','200.199.95.6',975203662);
INSERT INTO t_anuncio VALUES ('AOjUbhvRcGgCQjF','AEV7FTeYI42En7J',3,31,'2000-11-26','2000-12-06','placa de som sounblaster 16 + placa de v�deo trident com 1 mega + processador AMD 5x86 133MHZ com cooler + 24MB de Mem�ria Ram, sendo um par de 8MB e um par de 4MB.Tudo certinho, bem conservado',NULL,10,100.00,19,'Vendo ou troco estes componentes. Aceito sugest�es','200.199.52.195',975227038);
INSERT INTO t_anuncio VALUES ('AOLZxL6s4qKPrFT','AEiK9nB5zaC6F9D',5,0,'2000-11-27','2000-12-07','A Lins Consultoria realizar� no dia 12/12 no audit�rio do SEBRAE/AL esta excelente palestra para os empres�rios que desejam obter mais lucros. Ligue Gratuitamente para outras informa��es - 0800 82 1882','',10,0.00,10,'Palestra Empresa que faz dinheiro!!!','200.191.62.152',975326179);
INSERT INTO t_anuncio VALUES ('AOsYftP3pCkyEZd','AEiK9nB5zaC6F9D',1,0,'2000-11-27','2000-12-07','T�tulos:\r\n- Novo Tempo\r\n- O Sentido\r\n- Ac�stico e El�trico (duplo)',NULL,10,25.00,24,'3 discos da Banda Catedral semi-novos','200.191.62.152',975326622);
INSERT INTO t_anuncio VALUES ('AO3b5VS6c1zFDYc','AEykebTBzoAGEtr',1,0,'2000-11-27','2000-12-07','Vendo um amplificador SIGNUS SA-2, novo na caixa com NF e Garantia e Uma mesa de som STANER 8 canais.\r\nTR.:966-1018 OU 328-1470 ',NULL,10,0.00,25,'SOM INPEC�VEL !!!','200.241.146.14',975367942);
INSERT INTO t_anuncio VALUES ('AOtjy0I1tj6yomN','AE6QVY26aeinrvz',1,0,'2000-11-27','2000-12-07','Rox-pro, wilson, prince...',NULL,10,0.00,7,'Raquete de t�nnis (semi-novas)','200.241.146.213',975368727);
INSERT INTO t_anuncio VALUES ('AONnhtaKA9PxfWM','AEF14hCdGhu5q1u',1,0,'2000-11-27','2000-12-07','Serraria, Km 4\r\n\r\nTratar: 328-2065',NULL,10,0.00,20,'Chal� Sui�o','200.241.146.213',975369222);
INSERT INTO t_anuncio VALUES ('AOOUN7XFCsSk1Zz','AEF14hCdGhu5q1u',1,0,'2000-11-27','2000-12-07','Rod. AL101 norte, \r\n\r\nTratar: 355-5091',NULL,10,0.00,20,'Cquesabe Motel','200.241.146.213',975369340);
INSERT INTO t_anuncio VALUES ('AOuW4gPxfXKs20P','AEF14hCdGhu5q1u',5,0,'2000-11-27','2000-12-07','Rod. Al 101 Norte, \r\n\r\nTel. 355-5333',NULL,10,0.00,20,'Motel Opium','200.241.146.213',975369461);
INSERT INTO t_anuncio VALUES ('AOjDgcw5F7xYq0z','AEF14hCdGhu5q1u',5,0,'2000-11-27','2000-12-07','Fone: 355-5024',NULL,10,0.00,20,'Vip\'s Motel','200.241.146.213',975369513);
INSERT INTO t_anuncio VALUES ('AOy1WnAxCz85afk','AE80FujwtGfjoBG',1,0,'2001-05-16','2001-05-26','Pedal de multiefeito com 24 programas de at� 09 efeitos por vez: Compression, Distorcion, Chorus, Flanger, Delay, Phaser, Wah-wah, Noise Gate, Sims Amp, Step, Double, Hamonizer, Reverber, Acoustic e muito mais.','gif',10,250.00,25,'ZOOM 505','200.191.20.17',989998759);
INSERT INTO t_anuncio VALUES ('AOlzlhYy0sRrTs2','AEF14hCdGhu5q1u',5,0,'2000-11-27','2000-11-28','FONE 231-3634',NULL,1,0.00,8,'PRECISA-SE DE MANICURE E CABELEREIRA','200.199.95.6',975374802);
INSERT INTO t_anuncio VALUES ('AOWMySrhRr6OECr','AEF14hCdGhu5q1u',1,0,'2000-11-27','2000-11-28','MOTOROLA MG-300, COMPLETO FONE 983-4843',NULL,1,450.00,7,'R�DIO AMADOR','200.199.95.6',975374938);
INSERT INTO t_anuncio VALUES ('AOJzlNmc2Kx7XVK','AEF14hCdGhu5q1u',1,0,'2000-11-27','2000-11-28','VENDO FONE 325-3089',NULL,1,0.00,25,'PIANO ALEM�O','200.199.95.6',975375114);
INSERT INTO t_anuncio VALUES ('AOfFHh6WEmhZPFu','AEF14hCdGhu5q1u',1,0,'2000-11-27','2000-11-28','PRE�O A COMBINAR FONE 327-3243/9381-8432',NULL,1,0.00,17,'M�QUINA DE MUSCULA��O','200.199.95.6',975375273);
INSERT INTO t_anuncio VALUES ('AOWDDruG0kdhJaC','AEF14hCdGhu5q1u',1,0,'2000-11-27','2000-11-28','NINTENDO 64; SUP, NINTENDO, MEGA DRIVE E PLAY STATION FONE 355-5378/971-4791 ',NULL,1,0.00,18,'CARTUCHOS PARA V�DEO GAME','200.199.95.6',975375449);
INSERT INTO t_anuncio VALUES ('AOtjc2SQVTPFKQO','AEF14hCdGhu5q1u',1,0,'2000-11-27','2000-11-28',' FONE 235-4189; 974-7934',NULL,1,0.00,25,'TECLADO ROLAND E-96 E XP-60','200.199.95.6',975375657);
INSERT INTO t_anuncio VALUES ('AOh70cMCjhPgIi0','AEF14hCdGhu5q1u',3,0,'2000-11-27','2000-11-28','VENDO OU TROCO FONE 371-4202',NULL,1,0.00,7,'FILMADORAS JVC E PANASONIC','200.199.95.6',975375793);
INSERT INTO t_anuncio VALUES ('AOrVq0QNvlh6WUt','AEF14hCdGhu5q1u',1,0,'2000-11-27','2000-11-28','SIEMENS 4 LINHAS, 16 RAMAIS FONE 972-9342',NULL,1,0.00,4,'CENTRAL DE TELEFONE','200.199.95.6',975375949);
INSERT INTO t_anuncio VALUES ('AO1rCXqLmP2v6Hi','AE27afAVoBGTezU',1,28,'2001-04-15','2001-04-25','CPU de um 486 DX2 66Mhz, 16Mb de Ram, 850Mb de HD, com drive de 3,5, com Windows 95 e pacote office, em otimo estado de conservacao. Em caso de interesse envie um e-mail para: macealpe@zipmail.com.br',NULL,10,250.00,19,'486 DX2, 16Mb de  Ram,  HD de 850Mb','152.163.204.78',987308964);
INSERT INTO t_anuncio VALUES ('AOM2tOhKlW9C5Oh','AEr830lG9CzUnQj',5,0,'2001-04-15','2001-04-25','Filmagem de Eventos em geral, Document�rios e clips com equipamentos de ultima gera��o e profissionais altamente treinados.Edi��o de fitas.','',10,0.00,15,'Filmagem de Eventos','200.227.200.118',987307749);
INSERT INTO t_anuncio VALUES ('AOHfqDYjMnkvkF0','AEF14hCdGhu5q1u',1,1,'2001-03-27','2001-04-06','Vendo C�rdoba, branco 97, completo, semin-novo. Fone 235.2975',NULL,10,14500.00,1,'C�RDOBA 97','200.199.95.13',985698876);
INSERT INTO t_anuncio VALUES ('AOBj5oYyJblUucw','AEF14hCdGhu5q1u',5,0,'2000-11-28','2000-11-29','CENTENAS DE PROGRAMAS E JOGOS FONE 983-2261',NULL,1,0.00,24,'GRAVA-SE CDs','200.199.95.6',975377006);
INSERT INTO t_anuncio VALUES ('AOiKuOfXxe2KshZ','AEF14hCdGhu5q1u',1,35,'2000-11-28','2000-11-29','PENTIUM III/500. SUPER OFERTA! FONE 981-5137',NULL,1,0.00,19,'NOTEBOOK  SONY','200.199.95.6',975377159);
INSERT INTO t_anuncio VALUES ('AO3Bq1MfYzMn6Hq','AEP7gekqEJ397kq',1,1,'2001-03-29','2001-04-08','ESTADO DE ZERO KM, EQUIPADO. COR PRATA TEXAS\r\nEM PERFEITO ESTADO. VENDO, TROCO OU FINANCIO.',NULL,10,0.00,1,'SCORTE ZETEC 1.8 GLX 98','200.199.68.180',985891433);
INSERT INTO t_anuncio VALUES ('AOZ5swsUdVJ2C4o','AEu2pZPNSkolrF1',1,15,'2000-11-28','2000-12-08','Compre seu Concept completo, com carregador r�pido, bateria vibracall, adesivo no visor(aguia) e uma carca�a extra (6120). Com apenas 11 meses de comprado, na garantia ate dezembro.','jpg',10,250.00,4,'Gradiente Concept Baratinho!!!','200.199.52.114',975453743);
INSERT INTO t_anuncio VALUES ('AO1LF6Gwujfzoe4','AEu2pZPNSkolrF1',1,15,'2000-11-28','2000-12-08','Nokia 5120 com carregador r�pido e bateria vibracall, exelente estado 11 meses que foi comprado e baixiss�mo tempo de uso.','jpg',10,180.00,4,'Nokia 5120','200.199.52.114',975454033);
INSERT INTO t_anuncio VALUES ('AOAGXp7XMKWEJHF','AEemmxJbK4uLrL4',6,35,'2000-11-29','2000-11-30','Compro notebook usado em bom estado com CD e fax.','',1,1500.00,19,'Notebook','200.192.176.201',975494628);
INSERT INTO t_anuncio VALUES ('AO31aCcUBj7XMCk','AEluomsxLRboCYc',5,24,'2000-11-30','2000-12-10','projetos eletrico, hidraulicos, arquitetornicos, incendio, estrutural, levatamentos topogr�ficos e etc..\r\n',NULL,10,0.00,19,'Desenhos em AutoCAD 2000','200.199.64.54',975604588);
INSERT INTO t_anuncio VALUES ('AOUt7zo6OENf5Nu','AEfTaKINTZNKIO2',5,0,'2000-12-01','2000-12-11','T�cnico, digitador e vendedor na �rea de inform�tica.\r\nV�rios cursos de aperfei�oamento.\r\nTempo de est�gio.\r\nExperi�ncia com o pacote OFFICE.',NULL,10,0.00,8,'Funcion�rio dedicado querendo evoluir','200.241.148.66',975669291);
INSERT INTO t_anuncio VALUES ('AOmszLdNCkoYNvd','AEP7gekqEJ397kq',1,0,'2000-12-04','2000-12-05','carro scort zetec 98 top de linha, super conservado, interresados ligar para 966-4397',NULL,1,0.00,14,'scort zetec 98','200.199.68.180',975938790);
INSERT INTO t_anuncio VALUES ('AOOzmrEZkxuZkF8','AESHCP2nIb8tWpK',1,0,'2001-04-14','2001-04-24','Filhotes sadios nascidos a 02/04. Pai e m�e com bom pedigree. Tratar pelo fone: 338-4473 ou e-mail: evl@ev.eti.br',NULL,10,300.00,5,'Filhotes de Boxer com pedigree','200.241.146.147',987289232);
INSERT INTO t_anuncio VALUES ('AOMSDz1lEeUdNfP','AEItxBFJNRV048c',1,15,'2000-12-04','2000-12-14','Vendo celular CROMA Gradiente em �timo estado.','jpg',10,0.00,4,'Celular CROMA Gradiente - APROVEITE!','200.133.126.5',975954663);
INSERT INTO t_anuncio VALUES ('AOXFHSA20HfHp6W','AE2cmWLlb9OwecL',5,0,'2000-12-04','2000-12-14','Fa�a vistoria em seu im�vel antes de aluga-lo, garanto qualidade e compet�ncia no servi�o. quer uma maostra ??? ligue-me ou mande-me um email.',NULL,10,0.00,15,'Vistorie seu Im�vel.','200.199.68.145',975967827);
INSERT INTO t_anuncio VALUES ('AOQehCXy1CHaDeH','AEmS3wR4F8dG9Kd',1,31,'2001-04-13','2001-04-23','Vendo pe�as, placas e acess�rios de computadores, e com desconto na instala��o do produto. Pe�a detalhes comigo.',NULL,10,0.00,19,'Compre produtos de inform�tica mais baratos!','200.191.61.26',987176957);
INSERT INTO t_anuncio VALUES ('AO8KnjL5oY8zhJj','AEAiptVm4EAhRzp',3,0,'2000-12-07','2000-12-17','Vendo 2 filhotes de American St. (Pitbull), de excelente ninhagem e temperamento. Cores Dourado e Tigrado. Tratar com Silvio: 962-6040/231-4939\r\nOBS: Foto do Pai dos Filhotes.','gif',10,170.00,5,'Filhotes de Pitbull (Foto do Pai)','200.199.95.11',976187206);
INSERT INTO t_anuncio VALUES ('AOPHl306WU639fl','AEF14hCdGhu5q1u',5,0,'2000-12-10','2000-12-11','JOS� FRAGOSO FONE 359-2294\r\n',NULL,1,0.00,15,'ENCANADOR','200.199.87.71',976497903);
INSERT INTO t_anuncio VALUES ('AOlEqCcLXokE6VD','AEF14hCdGhu5q1u',5,0,'2000-12-10','2000-12-11','START FONE 336-5544',NULL,1,0.00,8,'AG�NCIA DE EMPREGOS','200.199.87.71',976498026);
INSERT INTO t_anuncio VALUES ('AOWay5x6ka6VDBr','AEF14hCdGhu5q1u',5,0,'2000-12-10','2000-12-11','FONE 336-5817',NULL,1,0.00,10,'ESCOLA DE DIC��O E ORAT�RIA','200.199.87.71',976498185);
INSERT INTO t_anuncio VALUES ('AOr9iCcDd3Rq8Qy','AEF14hCdGhu5q1u',5,0,'2000-12-10','2000-12-11','FONE 325-7502',NULL,1,0.00,15,'LAVANDERIA LAV-TUDO','200.199.87.71',976498507);
INSERT INTO t_anuncio VALUES ('AOmYBNfOEuiJj9R','AEF14hCdGhu5q1u',1,0,'2000-12-10','2000-12-11','FONE 338-4770',NULL,1,0.00,7,'PRODUTOS PARA AL�RGICOS','200.199.87.71',976498636);
INSERT INTO t_anuncio VALUES ('AOFd7qg6OwzhZyg','AEAYlVDti8c2RXV',5,0,'2000-12-13','2000-12-23','Seleciona-se garotas para fotos sensuais em p�gina na internet. Site de bom gosto. Enviar seus dados e uma foto ou marcar contato por e-mail.',NULL,10,0.00,8,'Procura-se garotas para fotos','200.199.67.131',976746651);
INSERT INTO t_anuncio VALUES ('AO1npZWU0YDl3Lt','AEAYlVDti8c2RXV',5,0,'2000-12-13','2000-12-23','Seleciona-se garotas para fotos sensuais em p�gina na internet. Site de bom gosto. Enviar seus dados e uma foto ou marcar contato por e-mail.',NULL,10,0.00,20,'Procura-se garotas para fotos','200.199.67.131',976746421);
INSERT INTO t_anuncio VALUES ('AO6DyD6z2vAV8tO','AEecnsNgJchSdG9',1,0,'2001-02-24','2001-03-06','Gravamos CDs de m�sica, e dados, temos muitos programas incluindo Show do Milh�o III\r\ne videoke, temos tambem Via Voice IBM',NULL,10,10.00,24,'SHOW DO MILH�O E VIDEOKE','200.199.56.11',982993472);
INSERT INTO t_anuncio VALUES ('AOhv1dNCcfNnXwe','AEoUODBzxCWa063',5,0,'2000-12-15','2000-12-25','Venha para o grande evento de Nova jerusalem em fazenda Nova.\r\n� Maior cidade teatro do mundo.\r\nPor apenas 75.00R$ \r\n',NULL,10,75.00,13,'paix�o de Cristo','200.241.162.3',976850987);
INSERT INTO t_anuncio VALUES ('AOvPzvG8kTrTlwA','AEAiptVm4EAhRzp',3,0,'2000-12-18','2000-12-28','Vendo lindo filhote de Pitbull. Excelente temperamento. Tr. 962-6040',NULL,10,0.00,5,'Filhotes de Pitbull','200.199.95.11',977156318);
INSERT INTO t_anuncio VALUES ('AOKUdiLeHi7A3w7','AEWC7P3xKeO2nZl',1,1,'2001-05-03','2001-05-13','COMPLETO AR, VIDRO, TRAVA O MAIS NOVO DA CIDADE',NULL,10,10200.00,1,'GOL BOLA 95','200.199.95.21',988894002);
INSERT INTO t_anuncio VALUES ('AOeX05aDYjgBW9u','AE6QVY26aeinrvz',3,1,'2001-05-11','2001-05-21','branco , c/ 30.000Km',NULL,10,10500.00,1,'Ford Ka / 99 (b�sico)','200.241.146.213',989618231);
INSERT INTO t_anuncio VALUES ('AOQf9ezMfIF0dG1','AE8l8tWp0BOpSt4',1,15,'2001-05-12','2001-05-22','Vendo duas baterias tarja azul (grossa) e um carregador do StarTac digital, pre�o a combinar.',NULL,10,0.00,4,'Baterias de StarTac digital','200.199.51.83',989721929);
INSERT INTO t_anuncio VALUES ('AOpZmy8PpREeGoX','AEyqb7z0Ai6VfPF',1,39,'2000-12-21','2000-12-31','Vendo joystick LEADERSHIP, 3 em 1. Serve como manche de avi�o, volante para carro e guidon de moto. Super novo. Aceito contra-proposta.\r\n',NULL,10,100.00,19,'Joystick LEADERSHIP','200.227.207.72',977415021);
INSERT INTO t_anuncio VALUES ('AOcvpRq8QydNDta','AErhMuWTRX964UK',5,0,'2000-12-28','2001-01-07','Visite o site Fla-Macei� on line! Tudo sobre o Flamengo: hist�ria, t�tulos, not�cias, curiosidades e muito mais! Al�m de uma se��o de turismo de Macei�.End.: www.flamaceio.hpg.com.br','gif',10,0.00,18,'www.flamaceio.hpg.com.br','200.199.56.101',977973099);
INSERT INTO t_anuncio VALUES ('AOsGO7XVLAgIh7X','AErhMuWTRX964UK',5,29,'2000-12-28','2001-01-07','Visite o site Fla-Macei� on line! Tudo sobre o Flamengo: hist�ria, t�tulos, not�cias, curiosidades e muito mais! Al�m de uma se��o de turismo de Macei�.End.: www.flamaceio.hpg.com.br','gif',10,0.00,19,'FLA-MACEI� on line','200.199.56.101',977974537);
INSERT INTO t_anuncio VALUES ('AOrTxsqge3Rzom4','AEZfSsa0e3Zdbge',3,15,'2001-01-08','2001-01-18','Vendo ou troco aparelho celular Nokia, modelo 6120.Telefone com uso de 2 anos e em boa conserva��o.',NULL,10,190.00,4,'Nokia 6120','200.199.58.93',978958070);
INSERT INTO t_anuncio VALUES ('AOldlFm42CFvl39','AEyQmpJjTA5hJOw',3,0,'2000-12-30','2001-01-09','nintendo 64 com 2 controles e 2 fitas nov�ssimo',NULL,10,250.00,7,'NINTENDO 64','200.241.161.132',978149706);
INSERT INTO t_anuncio VALUES ('AOjTFec2KzvBjge','AEaibDd30I0Ixvd',1,12,'2000-12-28','2001-01-07','vendo 02 terrenos sendo um medindo 19X150, e outro medindo 100X150, localizados entre a Praia do Franc�s e M.Deodoro',NULL,10,0.00,2,'VENDO �TIMOS TERRENOS PARA CH�CARAS','200.199.53.163',978050355);
INSERT INTO t_anuncio VALUES ('AOD9cxSlOhmPqTm','AEd3Q3wZkFSdGhK',5,0,'2001-01-21','2001-01-31','Sou um profissional com disponibilidade de mudan�a de estado. Formado em Adm.Empresas,Matem�tica,com p�s-gradua��o em Marketing. Esperi�ncia em vendas, Ger�ncia de Loja,Professor a 8 anos na �rea de matem�tica, f�sica, Mat.Fin. e Contabilidade.',NULL,10,0.00,8,'Procuro emprego','200.245.1.102',980121526);
INSERT INTO t_anuncio VALUES ('AOUknANgJc9uXq1','AEIqtN0lOheHaDY',1,20,'2001-01-18','2001-01-28','Placa-m�es, Mem�rias, HDs, Processadores, Modens, etc. Produtos c/ garantia. Pre�os de ocasi�o!!!',NULL,10,0.00,19,'Perif�ricos Novos e Usados','200.199.56.148',979866482);
INSERT INTO t_anuncio VALUES ('AONcZcxSlGDYjM7','AEIqtN0lOheHaDY',1,31,'2001-01-18','2001-01-28','Mem�rias (SIMM e DIMM), Placa-m�es, HDs, Processadores, Modens, etc. Tudo c/ garantia. Pre�os Promocionais!!!',NULL,10,0.00,19,'Perif�ricos Novos e Usados c/ garantia','200.199.56.148',979867505);
INSERT INTO t_anuncio VALUES ('AOcJgtO9CXUfA3o','AEIqtN0lOheHaDY',1,14,'2001-04-06','2001-04-16','Vendo um Identificador de Chamadas, novo na caixa, c/ 54 mem�rias permanentes e capacidade para conectar at� 3 linhas. R$-35,00. Urgente!!!',NULL,10,35.00,4,'Identificador de chamadas (Bina).','200.199.56.72',986529925);
INSERT INTO t_anuncio VALUES ('AObx3f4EeOtVv5w','AEslRbmOoQvPhRq',1,20,'2000-12-30','2001-01-09','Vendo processador CyrixMII PR-333MHz em exelente estado de conserva��o.\r\n',NULL,10,90.00,19,'Processador','200.199.58.85',978220658);
INSERT INTO t_anuncio VALUES ('AOZR0IyJzhdUKIy','AEoUODBzxCWa063',5,0,'2001-01-01','2001-01-11','Limpeza de caixa d\'�gua\r\nLimpeza de caixa de gordura\r\nDetetiza��o\r\npedreiro\r\nMarcineiro \r\nPintor\r\nDiarista\r\nVigia\r\nporteiro\r\nHidr�ulica \r\nEl�trica\r\nServi�al\r\nFrentista\r\nCalceteiro\r\nPiso de alta resist�ncia (dubeton e graniliti)\r\neServi�os Gerais \r\n\r\n',NULL,10,0.00,15,'Detetiza��o e Servi�os Gerais','200.199.58.142',978369770);
INSERT INTO t_anuncio VALUES ('AOdxieOL5FPoQqZ','AE7xHMSeANftH3g',5,0,'2001-01-01','2001-01-11','Limpeza de caixa D\'�gua,caixa de gordura,dedetiza��o,jardinagem,hidr�ulica,el�trica,marcineiro,pintor,pedreiro,porteiro,vigia,diarista,servi�al,desratiza��o,descupiniza��o,frentisa,calceteiro,piso em alta resist�ncia (Granilit ou Dubetom).',NULL,10,0.00,15,'LIMPOOL SERV servi�os gerais','200.199.58.142',978371362);
INSERT INTO t_anuncio VALUES ('AOU8q853TJwmkqo','AEN4s2Bj1RoQqg5',1,0,'2001-01-03','2001-01-13','CD DE MALA PIONEER PARA 12 CDS (MODELO FM)\r\nOBS. S� VENDO � VISTA',NULL,10,380.00,7,'CD DE MALA PIONEER','200.199.67.109',978546224);
INSERT INTO t_anuncio VALUES ('AO7XB30QyEzpnti','AEkun5VTY4SQFLB',5,0,'2001-01-04','2001-01-14','Projetos Arquitet�nicos, Ambienta��o,\r\ncompletos . Valorize cada metro quadrado do seu terreno ou im�vel, a sua casa tem que ter beleza, conforto e viabilidade. Portanto contrate profissionais! Visite o nosso site www.engenhoarquitetura.cjb.net','gif',10,0.00,27,'Projetos de Ambienta��o','200.199.58.170',978587278);
INSERT INTO t_anuncio VALUES ('AO0IB3LJqwkaZ53','AEkun5VTY4SQFLB',5,12,'2001-01-04','2001-01-14','Projetos Arquitet�nicos completos. Valorize cada metro quadrado do seu terreno ou im�vel, a sua casa tem que ter beleza, conforto e viabilidade. Portanto contrate profissionais! Visite o nosso site www.engenhoarquitetura.cjb.net','gif',10,0.00,2,'Projetos Arquitet�nicos','200.199.58.170',978588181);
INSERT INTO t_anuncio VALUES ('AOYkdpZ2CAZz97G','AEkun5VTY4SQFLB',5,0,'2001-01-04','2001-01-14','Projetos Arquitet�nicos completos. Valorize cada metro quadrado do seu terreno ou im�vel, a sua casa tem que ter beleza, conforto e viabilidade. Portanto contrate profissionais! Visite o nosso site www.engenhoarquitetura.cjb.net','gif',10,0.00,21,'Projetos Aquitet�nicos - Construa com Arte','200.199.58.170',978587481);
INSERT INTO t_anuncio VALUES ('AOWmuyfXFvyoeWD','AEj5s206jv28Y4a',1,21,'2001-01-05','2001-01-08','VENDO IMPRESSORA HP 840 (NOVA)\r\n',NULL,3,550.00,19,'IMPRESSORA HP 840','200.199.95.165',978698239);
INSERT INTO t_anuncio VALUES ('AOLHtxsMXxfPw6y','AEOmRr97OMIyfdb',1,1,'2001-01-05','2001-01-10','vidros verdes, dire��o hidra�lica, �nico dono, super conservado, carro do sert�o sem maresia, unica chance, aceito negociar.',NULL,5,15000.00,1,'Parati 1.8 96/96 gasolina','200.241.149.226',978700516);
INSERT INTO t_anuncio VALUES ('AOGZ1Ba0sij1ROE','AECUFDZdyUmAN9n',1,15,'2001-01-05','2001-01-15','Vendo aparelho Chroma,todo novo, garantia at� 11/08/2001',NULL,10,500.00,4,'NOKIA 8860 CHROMA','200.199.58.33',978707284);
INSERT INTO t_anuncio VALUES ('AOWvxZPEm4lFncU','AEkun5VTY4SQFLB',5,0,'2001-01-06','2001-01-16','Projetos Arquitet�nicos completos. Valorize cada metro quadrado do seu terreno ou im�vel, a sua casa tem que ter beleza, conforto e viabilidade. Portanto contrate profissionais! Visite o nosso site www.engenhoarquitetura.cjb.net','gif',10,0.00,6,'Projetos Arquitet�nicos','200.199.58.185',978800573);
INSERT INTO t_anuncio VALUES ('AOMAQpnXFeigYFD','AEaGj1BzwuGMJ5b',2,10,'2001-01-07','2001-01-17','Aluga-se apartamentos mobiliados por temporada 2 e 3 quartos na praia de Ponta Verde, na melhor localiza��o de Macei�.\r\nTratar: (0**82) 327-1574 / 979-0348\r\nPre�o a combinar.','jpg',10,0.00,2,'CONHE�A AS MARAVILHOSAS PRAIAS DE MACEI�','200.199.56.198',978873511);
INSERT INTO t_anuncio VALUES ('AO71bwZA3wJcF8R','AEGDWhKlWFKt4Fg',1,0,'2001-05-09','2001-05-19','-Painel eltr�nico com computador completo: rel�gio program�vel, tempo de exerc�cio, calorias, repeti��es e alarme metr�metro\r\n-Suporte para os p�s\r\n-Assento confort�vel \r\nEm perfeitas condi��es.',NULL,10,170.00,7,'Remo Seco de metal Sculpture BR-2200','200.241.161.33',989388131);
INSERT INTO t_anuncio VALUES ('AOZ3SBk3MvenYHq','AEee3EnmdcFEDCB',1,14,'2001-01-13','2001-01-23','Vendo linha telef�nica residencial bairro nobre de Macei�. �timo pre�o. Cristina. (21)9807-3951 (Rio) ou e-mail: marycris2000@aol.com',NULL,10,0.00,4,'Linha telefonica','205.188.197.47',979359910);
INSERT INTO t_anuncio VALUES ('AOfLOTmHaDIbG1u','AEK69C5qTmzUnYj',5,0,'2001-04-03','2001-04-13','Local para Acampamentos e Eventos Festivos, com: piscina, campo de futebol, dormit�rio, entre outros atrativos. Fa�a sua reserva j�! pelo e-mail: opcaocamp@bol.com.br ou pelo fone: (82) 9371-1582 / 327-0066','',10,0.00,15,'Op��oCamp','200.199.51.78',986273639);
INSERT INTO t_anuncio VALUES ('AOQnyDQjE7cF8B4','AE6QVY26aeinrvz',1,0,'2001-01-22','2001-02-01','Em perfeito estado, todo em f�rmica, ',NULL,10,350.00,7,'Chek out    p/ super mercado','200.241.146.213',980153823);
INSERT INTO t_anuncio VALUES ('AODruHaD6zE7A3w','AE6QVY26aeinrvz',1,0,'2001-01-22','2001-02-01','Em perfeito estado, �timo para oficinas, levantar qualquer tipo de peso\r\n\r\nPre�o a combinar...',NULL,10,0.00,17,'Guincho   3 Ton.','200.241.146.213',980153979);
INSERT INTO t_anuncio VALUES ('AO3mxKXiDYrM7sN','AEKpkF0BcNKlWx0',1,35,'2001-01-22','2001-02-01','Vendo notebook, IBM, 133, excelente estado , sem defeitos, com CD rom, maleta termica, especial, super novo, pronto para uso!',NULL,10,1400.00,19,'Notebook (oportunidade)','200.199.53.80',980199205);
INSERT INTO t_anuncio VALUES ('AO5BUYrMnRNoJtW','AEKpkF0BcNKlWx0',5,0,'2001-01-22','2001-02-01','procura-se urgente, cachorro poodle, que atende pelo nome de helgo, que se perdeu nas imedia��es da rua belo horizonte no farol. Recompensa-se!!',NULL,10,0.00,5,'PROCURA-SE POODLE BRANCO URGENTE','200.199.53.80',980200138);
INSERT INTO t_anuncio VALUES ('AOf5M7Ij2DQraTu','AEo5gBO9uPMfsVg',5,1,'2001-01-27','2001-02-06','N�o perca tempo a procura do seu ve�culo.Disk e levaremos ele at� voc�.Cidade de Macei�\r\nFones:336-0755 ou 981-0307',NULL,10,0.00,1,'DISK-VE�CULOS','200.227.200.102',980622941);
INSERT INTO t_anuncio VALUES ('AOiAT6zUnQx0tO9','AE6QVY26aeinrvz',1,0,'2001-01-26','2001-02-05','Ventiladores de 4 p�s R$ 49,90',NULL,10,49.90,7,'Ventilador de teto (promo��o)( ELETRO SILVA)','200.199.95.42',980512044);
INSERT INTO t_anuncio VALUES ('AOEz8Jk3Efs3EfR','AE6QVY26aeinrvz',5,0,'2001-01-26','2001-02-05',' ',NULL,10,49.90,7,'Lumin�ria de emerg�ncia','200.199.95.42',980512180);
INSERT INTO t_anuncio VALUES ('AO1BiD6Pq1eHi1K','AE68Hi1Cl4p8RAj',5,0,'2001-01-31','2001-02-10','Anuncie no Disk F�cil!\r\nMais de 10.000 pessoas ter�o acesso a seus produtos ou servi�os durante o ano todo!\r\nSeu investimento total � de apenas R$50,00 por ano.\r\nCat�logo totalmente colorido,distribu�do aos assinantes de jornal. Disk: 358-1210',NULL,10,0.00,6,'QUER VENDER ALGO POR TELEFONE?','200.199.56.188',980966979);
INSERT INTO t_anuncio VALUES ('AOpubEfQr2fQr2L','AE68Hi1Cl4p8RAj',5,0,'2001-01-31','2001-02-10','Anuncie no Disk F�cil!\r\nMais de 10.000 pessoas ter�o acesso a seus produtos ou servi�os durante o ano todo!\r\nSeu investimento total � de apenas R$50,00 por ano.\r\nCat�logo totalmente colorido,distribu�do aos assinantes de jornal. Disk: 358-1210',NULL,10,0.00,7,'QUER VENDER ALGO POR TELEFONE?','200.199.56.188',980967076);
INSERT INTO t_anuncio VALUES ('AOzTjMv7IjEfYHi','AEHWnQra1K5GxgZ',5,0,'2001-02-02','2001-02-12','digitador precisa de trabalho, jovem de 24 anos trabalhador, necessita de emprego com urgencia.  ',NULL,10,0.00,8,'Digitador Precisa Trabalha','200.199.53.212',981155597);
INSERT INTO t_anuncio VALUES ('AOtVu5Op0J4x8Rs','AEPl2D6PyhCtc3U',5,24,'2001-02-06','2001-02-16','Fa�o manuten��o em computadores, tanto software como hardware. Atendimento at� em fins de semana.',NULL,10,30.00,19,'Manuten��o de microcomputadores','200.241.169.204',981482856);
INSERT INTO t_anuncio VALUES ('AOQGveXGh0t4Fwf','AEPl2D6PyhCtc3U',6,28,'2001-02-06','2001-02-16','Compro processador Pentiun 233 mhz.',NULL,10,0.00,19,'Processador Pentiun 233 mhz','200.241.169.204',981482692);
INSERT INTO t_anuncio VALUES ('AOB3Clc3UD6PGp8','AEY4Tul4VE7YPyp',1,20,'2001-02-07','2001-02-17','Modem Us Robotics, 33600, ISA, Semi-novo, com Cd de instala��o. Modem super requisitado, por ser o melhor.',NULL,10,69.00,19,'Modem Us Robotics','200.199.56.33',981511551);
INSERT INTO t_anuncio VALUES ('AOAb0Js3MvYHq9S','AEY4Tul4VE7YPyp',1,32,'2001-02-07','2001-02-17','Vendo dois Pentes de mem�ria SIMM (72 vias), 16 MB cada, por 40 reais o pente. Um pechincha!!! Desafio a encontrarem pre�o menor!',NULL,10,80.00,19,'Mem�rias SIMM e DIMM','200.199.56.33',981511952);
INSERT INTO t_anuncio VALUES ('AOwA9KBkbU7Izq9','AEY4Tul4VE7YPyp',1,36,'2001-02-07','2001-02-17','Vendo uma Placa de Som Creative, compativel c/ sound blaster, semi-nova, com todos os drivers. A melhor placa de som do mercado.',NULL,10,50.00,19,'Placa de Som Creative','200.199.56.33',981514694);
INSERT INTO t_anuncio VALUES ('AOtEHM7A3wJkNgR','AE6QVY26aeinrvz',3,1,'2001-04-22','2001-05-02','c/ 30.000km, branco, semi-novo, �nico dono',NULL,10,11000.00,1,'Ford KA, ano-99','200.241.146.213',987973501);
INSERT INTO t_anuncio VALUES ('AOjtwB4hC5avYrU','AELQ9u5Op8l4FoR',1,1,'2001-02-23','2001-03-05','VENDE-SE UM FUSCA 83, VERDE MET�LICO, JG. DE RODAS, SISTEMA ELETR�NICO, MOTOR 1.6 GAS',NULL,10,2500.00,1,'VENDE-SE UM FUSCA - 83','200.199.95.128',982948540);
INSERT INTO t_anuncio VALUES ('AOJY9u5qTmrUnQj','AEecnsNgJchSdG9',5,39,'2001-02-23','2001-03-05','www.pitanga2000.hpg.com.br, site er�tico de Alagoas, sucesso em todo Brasil. 15 lugar no TOP 30. Visitem.',NULL,10,0.00,19,'Site Erotico Pitanga 2000','200.199.56.71',982899496);
INSERT INTO t_anuncio VALUES ('AOVsvI3gBWhCXiD','AEv58tGhCd2vYrM',1,9,'2001-03-03','2001-03-13','Vendo �tima casa no Conj. Eldorado/Feitosa, com 3 quartos/1 su�te, 2 salas, ampla cozinha, �rea de servi�o, reservat�rio com 6000L. TR 328-7205',NULL,10,0.00,2,'�TIMA OPORTUNIDADE VND/TRC','200.199.56.62',983593519);
INSERT INTO t_anuncio VALUES ('AOSEPUfAVolyL6z','AEv58tGhCd2vYrM',5,0,'2001-03-03','2001-03-13','Estudante do 4�ano de Engenharia Civil. Projetos em AUTOCAD, arquitet�nicos e el�tricos residenciais (baixa tens�o)com baixo custo. TR. 328.7205',NULL,10,0.00,15,'PROJETOS ARQUITET�NICOS E EL�TRICOS','200.199.56.62',983593811);
INSERT INTO t_anuncio VALUES ('AOeL4hC5qTYjMfA','AE8m1ez2vYVgJca',1,0,'2001-03-04','2001-03-09','VENDO EM EXCELENTE ESTADO, 01 CONDICIONADOR DE AR CONSUL 10.000 BTUS',NULL,5,350.00,7,'VENDO CONDICIONADOR DE AR - 10,000 BTUS','200.199.95.158',983676433);
INSERT INTO t_anuncio VALUES ('AO4vqD6z2vs3w7s','AEHP8tOpSty9u5y',5,0,'2001-03-05','2001-03-15','Fa�o copia de cd e backup de seus arquivos na sua casa ou empresa, a qualquer hora.',NULL,10,7.00,24,'Copias de Cd\'s','200.199.53.17',983813449);
INSERT INTO t_anuncio VALUES ('AOuNQbE7A38B4F8','AEHP8tOpSty9u5y',5,24,'2001-03-05','2001-03-15','Fa�a copia de seguran�a de seus arquivos em cd, na sua casa ou empresa a qualquer hora, � s� ligar.',NULL,10,10.00,19,'Backup em Cd','200.199.53.17',983813747);
INSERT INTO t_anuncio VALUES ('AO1rCPqTu5aLeHi','AEHlwJcx0lqLezU',5,0,'2001-03-05','2001-03-15','Aprenda a jogar esse brilhande jogo, aulas particulares, 1 dia por semana duas horas de aula, 25 R$ por m�s, local a combinar com o aluno(podendo ser no pr�pio domic�lio). Professores Glauco Barbosa tel - 981-1886, e Ricardo Lopes tel - 356-7255.',NULL,10,25.00,10,'Aulas de xadrez','200.133.126.55',983837833);
INSERT INTO t_anuncio VALUES ('AODbmzM7AVKdysF','AE0H8BcVoZAj2Dm',1,0,'2001-03-05','2001-03-15','Vendo Cd do Fifa 2001 , original, por R$ 50,00 reais.Tratar 977 0245',NULL,10,50.00,24,'Fifa 2001','200.241.161.223',983843818);
INSERT INTO t_anuncio VALUES ('AO0berUnQjoRcNg','AENYpK5y9CPqTu5',1,24,'2001-03-06','2001-03-16','50MB de espa�o em disco, contas de email ilimidadas, suporte � MS Frontpage 2000, cgi, php, perl, banco de dados mSQL e mySQL. Visite-nos www.hostsys.com.br',NULL,10,14.95,19,'Hospedagem de Web Sites por R$14,95','200.199.58.242',983849785);
INSERT INTO t_anuncio VALUES ('AOCozM7AVolO9C5','AEYsCXaDeHUnQjU',5,0,'2001-03-06','2001-03-16','Pousada em uma das mais praias do litoral sul alagoano','jpg',10,0.00,18,'Pousada','200.199.112.77',983889065);
INSERT INTO t_anuncio VALUES ('AOl1cFKdG9YjMnQ','AEYsCXaDeHUnQjU',5,0,'2001-03-06','2001-03-16','Pousada em uma das mais belas praias do litoral sul alagoano','jpg',10,0.00,18,'Pousada','200.199.112.77',983889126);
INSERT INTO t_anuncio VALUES ('AO5P6VJGDAhmjgl','AEyLW1mPiTYr2D6',1,0,'2001-05-15','2001-05-25','Vende-se transmissor sem fio 2 antenas, marca NADY para instrumentos musicais.',NULL,10,400.00,25,'VENDO TRANSMISSOR SEM FIO','200.241.164.67',989948505);
INSERT INTO t_anuncio VALUES ('AO2W7sVgRchKdGh','AEHMXafIbEBOhSd',1,13,'2001-03-07','2001-03-17','�tima sala, para escrit�rio, consult�rio, etc., pr�dio novo, 2 elevadores, gerador, garagem coberta, super parcelada. Tratar 972-1150 - Tales Cardoso',NULL,10,0.00,2,'Sala Comercial','161.148.195.142',983959853);
INSERT INTO t_anuncio VALUES ('AO6FQ3wZAboJcNg','AEHMXafIbEBOhSd',1,9,'2001-03-07','2001-03-17','Condom�nio fechado, sal�o de festas, piscina, casa c/ 130m de �rea �til, 3 qts s/ 01 suite, parcelada em at� 36meses. Tratar Tales Cardoso 972-1150',NULL,10,0.00,2,'Vista para lagoa','161.148.195.142',983960022);
INSERT INTO t_anuncio VALUES ('AOwberMfIj8B4F8','AERITm5Gp0lWFgR',5,0,'2001-03-08','2001-03-18','1981-(Discoteca/CSU/Jati�ca)Olhar e aperto de m�os intensos entre dois adolescentes.\r\n1982-(P.de �nibus cine S.Lu�s)pela janela evitei seu olhar.\r\n1984(Feirinha Paju�ara)vc correu, insistiu, mas n�o o reconheci. Passaram-se 17 anos e nunca esqueci voc�!',NULL,10,0.00,6,'AINDA SONHO COM VOC�','200.199.51.60',984024997);
INSERT INTO t_anuncio VALUES ('AOxJ27sNgBG1mHa','AEfntl4VULKBsrq',1,0,'2001-03-10','2001-03-20','Vendo um Bloqueador de Telefone\r\nNeide Fone 241-3901  ',NULL,10,0.00,7,'BLOQUEADOR DE TELEFONE','200.199.95.13',984227993);
INSERT INTO t_anuncio VALUES ('AO5DpmHM7spCXav','AEfntl4VULKBsrq',1,0,'2001-03-10','2001-03-20','Vendo uma Televis�o seminova.\r\nNeide Fone 241-3901',NULL,10,0.00,22,'TV  20','200.199.95.13',984228224);
INSERT INTO t_anuncio VALUES ('AOI8jE7sNglOhKd','AERq5inkpujwJW1',5,0,'2001-03-11','2001-03-21','MENSAGENS PARA TODAS AS OCASI�ES COM FUNDO MUSICAL PARA VOC� SE EMOCIONAR, OFERECEMOS A VOC� O QUE H� DE MELHOR EM MENSAGENS.\r\n* MENSAGENS FONADAS\r\n* BUQU� DE FLORES\r\n* CESTAS DE CHOCOLATES\r\n* CAIXAS DE CHOCOLATES E MUITO MAIS!\r\nLIGUE (82) 354.4169/4088',NULL,10,0.00,15,'MOMENTOS DO CORA��O TELEMENSAGENS','200.199.53.116',984350554);
INSERT INTO t_anuncio VALUES ('AOajmrM7sNK5qTm','AEfntl4VULKBsrq',2,10,'2001-03-15','2001-03-25','Alugo apartamento no Edf. Erich Fromm - Farol,\r\ncom arm�rios em todas as depend�ncias. Fone 336.8046; 974.6430 alzira_carmellino@bol.com.br ',NULL,10,500.00,2,'AP  NO ERICH FROMM -  ALUGUEL','200.199.95.13',984678530);
INSERT INTO t_anuncio VALUES ('AO0hW1mP2vsN0lG','AEwBEZcFgJOhKdy',5,0,'2001-03-17','2001-03-27','Ensino Windows, word, Excel, PowerPoint e Access.Contato:celio_ricardo@zipmail.com.br,\r\nF-241-3652 ou 9341-0452.Tratar com Ricardo.OBS: Tenho larga experi�ncia(Sou programador).',NULL,10,0.00,15,'Aula Particular de Inform�tica','200.241.159.156',984882817);
INSERT INTO t_anuncio VALUES ('AOkDyxK5qLI3wRk','AEwBEZcFgJOhKdy',5,0,'2001-03-17','2001-03-27','Digito trabalhos, fa�o digitaliza��o de fotos(Scanner),cart�es de visitas. Contato:celio_ricardo@zipmail.com.br, fones 241-3652 ou 9341-0452. Tratar com Ricardo.',NULL,10,0.00,15,'Digita��o de Trabalhos e Outros','200.241.159.156',984883171);
INSERT INTO t_anuncio VALUES ('AOLbCHaLmP2vYz2','AEQsLYjEZkFSdG1',5,0,'2001-03-19','2001-03-29','Manuten��o, instala��o e acessoria de compra de computadores e perif�ricos. Atende Macei� e interior.',NULL,10,0.00,15,'Assist�ncia T�cnica de Computadores','200.199.67.101',985047360);
INSERT INTO t_anuncio VALUES ('AOHCF8fsVgtOpKt','AEQsLYjEZkFSdG1',5,24,'2001-03-19','2001-03-29','  Manuten��o, instala��o e acessoria de compra de computadores e perif�ricos. Atende Macei� e interior.',NULL,10,0.00,19,'Assist�ncia T�cnica de Computadores','200.199.67.101',985047445);
INSERT INTO t_anuncio VALUES ('AOUxAF0lG1YrMnI','AEQsLYjEZkFSdG1',5,39,'2001-03-19','2001-03-29','Entre na internet, mas com estilo. Home Pages planejadas e com excelente design. Pre�o competitivo e servi�o de alta qualidade. Atende Brasil e exterior. Conhe�a o www.alchei.com.br, site produzido por n�s.',NULL,10,0.00,19,'Design e Constru��o de Home Pages','200.199.67.101',985047890);
INSERT INTO t_anuncio VALUES ('AOWPS5y1uXy1u5y','AEQsLYjEZkFSdG1',5,0,'2001-03-19','2001-03-29','Entre na internet, mas com estilo. Home Pages planejadas e com excelente design. Pre�o competitivo e servi�o de alta qualidade. Atende Brasil e exterior. Conhe�a o www.alchei.com.br, site produzido por n�s.',NULL,10,0.00,15,'Design e Constru��o de Home Pages','200.199.67.101',985048150);
INSERT INTO t_anuncio VALUES ('AOgOfsVgB41mHav','AEPzKXq1udiTmXq',3,0,'2001-03-20','2001-03-30','Piano Alem�o, em �timo estado de conserva��o, madeira marrom escuro, mecanismo de mudan�a de som para cravo e harpa, instrumento com fabrica��o de s�rie. Dispon�vel a negocia��o n�o s� para venda como para troca.    ',NULL,10,0.00,25,'Piano Alem�o em �timo estado de conserva��o.','200.199.68.168',985106573);
INSERT INTO t_anuncio VALUES ('AOTpsFS5qLIbwRk','AEfntl4VULKBsrq',1,9,'2001-03-24','2001-04-03','Vendo casa  no Po�o. �tima para cl�nica, com�rcio. Telefone 241.3901 Roberto ',NULL,10,0.00,2,'CASA   -    A.v. Comendador Cala�a','200.199.95.13',985457566);
INSERT INTO t_anuncio VALUES ('AOQU5anQjMJ4p0B','AE7dwJ4x0BG9u5y',1,0,'2001-03-26','2001-04-05','Games, Programas, �udio. Tudo Full e personalizado. Fa�a j� sua colet�nia com os melhores sucessos do momento! Qualquer CD, R$-10,00 apenas. ',NULL,10,10.00,24,'Grava-se CDs Diversos','200.191.20.19',985576854);
INSERT INTO t_anuncio VALUES ('AOkB8PEByDc952Z','AExvG9mPaLI3wZs',1,0,'2001-05-14','2001-05-15','P�-carregadeira MICHIGAN 75III ano 1981\r\nRetro-escavadeira CASE 580H ano 1987\r\nP�-carregadeira MICHIGAN 55C ano 1988\r\nP�-carregadeira MICHIGAN 55A ANO 1980',NULL,1,0.00,17,'M�quinas e Tratores','200.199.53.228',989892192);
INSERT INTO t_anuncio VALUES ('AO0hsN8tWhuPiL6','AEF14hCdGhu5q1u',1,1,'2001-03-27','2001-04-06',' Vendo Parat 99 1.6, cinza, c/ ar Fone223.3437; 9331-1758',NULL,10,0.00,1,'PARATI 99','200.199.95.13',985699568);
INSERT INTO t_anuncio VALUES ('AO9P8tOhK5avQr2','AEF14hCdGhu5q1u',3,1,'2001-03-27','2001-04-06','VENDO OU TROCO  fONE 9381-0733',NULL,10,0.00,1,'LOGUS CL 93','200.199.95.13',985699700);
INSERT INTO t_anuncio VALUES ('AOWALQjE7INgBcx','AELO7kN8BchSlWp',5,0,'2001-05-01','2001-05-11','PRECISO TRABALHAR COM CONTABILIDADE TENHO COMPUTADOR, SE VOC� PRECISA DE ALGU�M PARA FAZER ALGUM SERVI�O, GOSTARIA MUITO DE TRABALHAR PODE SER EM CASA OU NO ESCRIT�RIO, N�O PRECISA ASSINAR CARTEIRA.\r\nFONE:344-2498 / 983-9285\r\nJANISETE\r\n\r\n',NULL,10,0.00,8,'PRECISO TRABALHAR COM CONTABILIDADE...','200.199.52.100',988759135);
INSERT INTO t_anuncio VALUES ('AOy3erwRkxCXqDY','AELO7kN8BchSlWp',5,0,'2001-04-22','2001-05-02','fabricamos e consertamos todos os tipos de m�veis,com ou sem projeto,temos desenhista.os melhores pre�os,ligue e iremos at� sua resid�ncia. 082 344.2498/ 983.9285 antero.',NULL,10,0.00,15,'marceneiro','200.199.58.232',987963230);
INSERT INTO t_anuncio VALUES ('AOjU52vYrUZsVoZ','AEofqTmXqTYrUvY',1,9,'2001-03-31','2001-04-10','Terreno 12x30 m bom para cl�nica ou resid�ncia.\r\n02 salas / 03 quartos s/ 1 su�te / W.C. Social / Copa / Cozinha / dispensa / lavabo / DCE / �rea de servi�o / garagem para 3 carros / jardim.\r\nContato: (0**82) 221-6036 / 241-1445',NULL,10,85000.00,2,'Casa Pr�x. Shopping Cidade','200.241.162.3',986061325);
INSERT INTO t_anuncio VALUES ('AO4Q9lqTmHERkN8','AEJI1mHiLmz2vYj',5,0,'2001-04-02','2001-04-12','Sou estudante do 6� periodo de eng. el�trica(CESMAC), tenho curso de eletr�nica (senai) e de computa��o, participa��o em v�rios semin�rios de eng. el�trica e telecomuni��es, tenho facilidade no aprendizado e estou a disposi��o.',NULL,10,0.00,8,'Preciso de emprego','200.199.53.87',986188280);
INSERT INTO t_anuncio VALUES ('AOe0305ivIFS5qD','AEIqtN0lOheHaDY',1,0,'2001-04-06','2001-04-16','Fa�a sua colet�nea. Pop,rock,balada,funk, techno,sertaneja,forr�,ax�, etc. Games e Softwares.','',10,9.00,24,'CDS POR APENAS R$-9,00','200.199.56.72',986530147);
INSERT INTO t_anuncio VALUES ('AOtITeHaLmjM7Ib','AEPl2D6PyhCtc3U',1,0,'2001-04-09','2001-04-19','Vendo v�deo-cassete Gradiente semi novo; �timo estado de conserva��o e garantia de funcionamento completo.',NULL,10,150.00,22,'VideoCassete Gradiente Seminovo','200.241.146.221',986829702);
INSERT INTO t_anuncio VALUES ('AOUtnshuHMBOTer','AEmS3wR4F8dG9Kd',1,32,'2001-05-20','2001-05-30','Aproveite a promo��o! � s� at� sexta-feira dia 25 de maio! Mem�ria de 64 MB PC133 por somente R$ 65,00!!! T� de gra�a!',NULL,10,65.00,19,'Mem�ria DIMM 64MB PC133 por apenas R$ 65,00!!!','200.191.62.221',990355727);
INSERT INTO t_anuncio VALUES ('AO24RqnkheVKHEA','AEmS3wR4F8dG9Kd',5,24,'2001-05-20','2001-05-30','Marque presen�a na internet fazendo o site de sua empresa.com.br! Qualidade e efici�ncia s�o indispens�veis, por isso fa�a seu site com quem entende! Pre�o mediante or�amento sem compromisso.',NULL,10,0.00,19,'Cria��o de websites para empresas','200.191.62.221',990356036);
INSERT INTO t_anuncio VALUES ('AO6OtqvIV85afsx','AEmS3wR4F8dG9Kd',5,0,'2001-05-20','2001-05-21','Marque presen�a na internet fazendo o site de sua empresa.com.br! Qualidade e efici�ncia s�o indispens�veis, por isso fa�a seu site com quem entende! Pre�o mediante or�amento sem compromisso.',NULL,1,0.00,15,'Cria��o de websites para empresas','200.191.62.221',990356116);
INSERT INTO t_anuncio VALUES ('AOj4Ryvspu385a7','AEmS3wR4F8dG9Kd',1,36,'2001-05-20','2001-05-30','Aproveite! Fax-modem Motorola 56k por apenas R$ 65,00 instalado no seu micro!',NULL,10,65.00,19,'Fax-modem Motorola 56k por R$ 65,00 instalada!','200.191.62.221',990356363);
INSERT INTO t_anuncio VALUES ('AOGAf416bgGDANS','AE7RURN0dqfAVgl',5,0,'2001-05-21','2001-05-31','SEJA UM EMPREENDEDOR DE SUCESSO.OPORTUNIDADE DE NEG�CIO PR�PRIO.SEM HOR�RIO E SEM PATR�O.\r\nPOSSIBILIDADE DE GANHOS ILIMITADOS. SISTEMA INTERNACIONAL TRABALHE EM CASA.VENHA CONHECER. JUNTE-SE A N�S. http://www.5bi.net/ajm ',NULL,10,0.00,7,'SEJA UM EMPREENDEDOR DE SUCESSO','200.225.155.148',990425123);
INSERT INTO t_anuncio VALUES ('AOEaPMJyLQhebfc','AE7RURN0dqfAVgl',1,0,'2001-05-21','2001-05-31','PRODUTOS NATURAIS HERBALIFE. PROGRAMA B�SICO PARA CONTR�LE DE PESO. CONT�M:\r\nSHAKE-P� PARA PREPARO DE BEBIDAS PARA CONTROLE DE PESO(SABORES,CHOCOLATE,BAUNILHA,MORANGO E FRUTAS TROPICAIS). FIBER HERB (FIBRAS).\r\nhttp://www.sbelto.com/charme','jpg',10,103.00,7,'PROGRAMA PARA CONTR�LE DE PESO B�SICO','200.225.155.148',990425496);
INSERT INTO t_anuncio VALUES ('AOyAnc8d2ZGDAxC','AE7RURN0dqfAVgl',1,0,'2001-05-21','2001-05-31','PRODUTOS NATURAIS HERBALIFE.PROGRAMA AVAN�ADO PARA CONTROLE DE PESO. CONT�M:\r\nSHAKE + FIBER HERB + THERMOJETICS.\r\nDETALHES EM :http://www.sbelto.com/charme','jpg',10,187.00,7,'PROGRAMA PARA CONTR�LE DE PESO-AVAN�ADO','200.225.155.148',990425778);
INSERT INTO t_anuncio VALUES ('AOq4IpmjwBafkxC','AE7RURN0dqfAVgl',1,0,'2001-05-21','2001-05-31','PRODUTOS NATURAIS IMPORTADOS HERBALIFE.LINHA COMPLETA DE PRODUTOS DE BELEZA (DERMAJETICS).\r\nNUTRI��O (PROGRAMS DE CONTROLE DE PESO).OPORTUNIDADE DE NEG�CIOS COM MULTINACIONAL EM 51 PA�SES. PADR�O E ALTO ESTILO. http://sbelto.com/charme','jpg',10,0.00,7,'BELEZA NUTRI��O E ESTILO','200.225.155.148',990426180);
INSERT INTO t_anuncio VALUES ('AOszeNCzEJ2ZW9e','AEfREdqerwdqDIV',1,31,'2001-05-21','2001-05-31','Processador em perfeito estado de conserva��o.\r\nAceito Sugest�es!',NULL,10,100.00,19,'Vendo Processador Pentium 233MHZ','200.199.58.205',990441960);
INSERT INTO t_anuncio VALUES ('AOI6TIFZOLAFKP2','AEfREdqerwdqDIV',1,31,'2001-05-21','2001-05-31','Em bom estado',NULL,10,15.00,19,'Processador Cyrix 100MHZ','200.199.58.205',990442049);
INSERT INTO t_anuncio VALUES ('AORmheb8lq7chmz','AEfREdqerwdqDIV',1,31,'2001-05-21','2001-05-31','Bem conservado!',NULL,10,15.00,19,'Processador AMD 586 133MHZ','200.199.58.205',990442134);
INSERT INTO t_anuncio VALUES ('AOAeLIwByD4hmro','AEnh4hCP2ncpCXi',5,0,'2001-05-22','2001-06-01','O EVENTO ACONTECER� NO PERIODO DE 11/06/2001 A 15/06/2001 E SER� DIVIDIDO EM 09 M�DULOS. CADA M�DULO CUSTAR� R$:15,00. O PACOTE DE 04 M�DULOS CUSTA R$:45,00 E COM TODOS OS 09 M�DULOS CUSTA APENAS R$:65,00. MAIORES INFORMA��ES: (82) 241-8949',NULL,10,15.00,10,'CURSO PR�TICO DE SUCATA','200.199.53.190',990542854);

#
# Table structure for table 't_bairro'
#
CREATE TABLE t_bairro (
  cod_bairro tinyint(3) DEFAULT '0' NOT NULL auto_increment,
  desc_bairro varchar(30) DEFAULT '' NOT NULL,
  PRIMARY KEY (cod_bairro)
);

#
# Dumping data for table 't_bairro'
#

INSERT INTO t_bairro VALUES (1,'Farol');
INSERT INTO t_bairro VALUES (2,'Tabuleiro dos Martins');
INSERT INTO t_bairro VALUES (3,'Ponta Verde');
INSERT INTO t_bairro VALUES (4,'Jati�ca');
INSERT INTO t_bairro VALUES (5,'Benedito Bentes');
INSERT INTO t_bairro VALUES (6,'Po�o');
INSERT INTO t_bairro VALUES (7,'Trapiche');
INSERT INTO t_bairro VALUES (8,'Pontal');
INSERT INTO t_bairro VALUES (9,'Barro Duro');
INSERT INTO t_bairro VALUES (10,'Serraria');
INSERT INTO t_bairro VALUES (11,'Jacintinho');
INSERT INTO t_bairro VALUES (12,'Stela Mares');
INSERT INTO t_bairro VALUES (13,'Cruz das Almas');
INSERT INTO t_bairro VALUES (14,'Paju�ara');
INSERT INTO t_bairro VALUES (15,'Outro');
INSERT INTO t_bairro VALUES (16,'Centro');
INSERT INTO t_bairro VALUES (17,'Bebedouro');
INSERT INTO t_bairro VALUES (18,'Jaragu�');
INSERT INTO t_bairro VALUES (19,'Aldebaran');
INSERT INTO t_bairro VALUES (20,'Muril�polis');

#
# Table structure for table 't_categoria_anuncio'
#
CREATE TABLE t_categoria_anuncio (
  cod_categoria_anuncio tinyint(2) DEFAULT '0' NOT NULL auto_increment,
  desc_categoria_anuncio varchar(50) DEFAULT '' NOT NULL,
  PRIMARY KEY (cod_categoria_anuncio)
);

#
# Dumping data for table 't_categoria_anuncio'
#

INSERT INTO t_categoria_anuncio VALUES (1,'Ve�culos');
INSERT INTO t_categoria_anuncio VALUES (2,'Im�veis');
INSERT INTO t_categoria_anuncio VALUES (3,'Marmitas');
INSERT INTO t_categoria_anuncio VALUES (4,'Telefones');
INSERT INTO t_categoria_anuncio VALUES (5,'Animais');
INSERT INTO t_categoria_anuncio VALUES (6,'Avisos');
INSERT INTO t_categoria_anuncio VALUES (7,'Outros');
INSERT INTO t_categoria_anuncio VALUES (8,'Empregos');
INSERT INTO t_categoria_anuncio VALUES (9,'Pensionatos');
INSERT INTO t_categoria_anuncio VALUES (10,'Cursos');
INSERT INTO t_categoria_anuncio VALUES (11,'T�tulos');
INSERT INTO t_categoria_anuncio VALUES (12,'Utilidades P�blicas');
INSERT INTO t_categoria_anuncio VALUES (13,'Festas e Eventos');
INSERT INTO t_categoria_anuncio VALUES (14,'Cons�rcios');
INSERT INTO t_categoria_anuncio VALUES (15,'Servi�os');
INSERT INTO t_categoria_anuncio VALUES (16,'Comidas');
INSERT INTO t_categoria_anuncio VALUES (17,'M�quinas e Motores');
INSERT INTO t_categoria_anuncio VALUES (18,'Dicas e Lazer');
INSERT INTO t_categoria_anuncio VALUES (19,'Inform�tica');
INSERT INTO t_categoria_anuncio VALUES (20,'Massagens');
INSERT INTO t_categoria_anuncio VALUES (21,'Artes');
INSERT INTO t_categoria_anuncio VALUES (22,'Eletrodom�sticos');
INSERT INTO t_categoria_anuncio VALUES (23,'Livros');
INSERT INTO t_categoria_anuncio VALUES (24,'CDs');
INSERT INTO t_categoria_anuncio VALUES (25,'Inst. Musicais');
INSERT INTO t_categoria_anuncio VALUES (26,'J�ias');
INSERT INTO t_categoria_anuncio VALUES (27,'M�veis e Decora��es');

#
# Table structure for table 't_chegada_usuario'
#
CREATE TABLE t_chegada_usuario (
  cod_chegada tinyint(2) DEFAULT '0' NOT NULL auto_increment,
  desc_chegada varchar(50) DEFAULT '' NOT NULL,
  PRIMARY KEY (cod_chegada)
);

#
# Dumping data for table 't_chegada_usuario'
#

INSERT INTO t_chegada_usuario VALUES (1,'Publicidade na Internet');
INSERT INTO t_chegada_usuario VALUES (2,'Sites de busca');
INSERT INTO t_chegada_usuario VALUES (3,'Jornal ou revista');
INSERT INTO t_chegada_usuario VALUES (4,'R�dio ou TV');
INSERT INTO t_chegada_usuario VALUES (5,'Evento ou Outdoor');
INSERT INTO t_chegada_usuario VALUES (6,'Indica��o de um amigo');
INSERT INTO t_chegada_usuario VALUES (7,'Outro');

#
# Table structure for table 't_endereco'
#
CREATE TABLE t_endereco (
  cod_anunciante varchar(15) binary DEFAULT '0' NOT NULL,
  cod_estado tinyint(2) DEFAULT '0' NOT NULL,
  cod_tipo_endereco tinyint(1) DEFAULT '0' NOT NULL,
  cod_bairro tinyint(3) DEFAULT '0' NOT NULL,
  numero varchar(6) DEFAULT '0' NOT NULL,
  logradouro varchar(70) DEFAULT '' NOT NULL,
  localidade varchar(25) DEFAULT '' NOT NULL,
  cep varchar(9) DEFAULT '' NOT NULL,
  cod_pais tinyint(3) DEFAULT '0' NOT NULL,
  PRIMARY KEY (cod_anunciante),
  UNIQUE cod_anunciante (cod_anunciante)
);

#
# Dumping data for table 't_endereco'
#

INSERT INTO t_endereco VALUES ('AEY6aeimquyHMQU',2,1,1,'58','Gon�alves Dias','Macei�','57051-330',1);
INSERT INTO t_endereco VALUES ('AEKruPaLeHaD6Ha',2,1,0,'202','Comendador Palmeira','Macei�','57021-150',1);
INSERT INTO t_endereco VALUES ('AEItxBFJNRV048c',2,1,1,'58','Gon�alves Dias','Macei�','57051-330',1);
INSERT INTO t_endereco VALUES ('AE6QVY26aeinrvz',2,1,3,'232/70','Santa Fernanda','Macei�','57035-390',1);
INSERT INTO t_endereco VALUES ('AElI9C5GpgtcVwf',2,1,2,'613-29','44, Graciliano Ramos','Macei�','57000-000',1);
INSERT INTO t_endereco VALUES ('AEr7cNUT0Z6dkjq',2,1,15,'323','sdsddsds2','','233223-33',1);
INSERT INTO t_endereco VALUES ('AEF14hCdGhu5q1u',2,2,4,'1854','Julio Marquers Luz,','Macei�','57035',1);
INSERT INTO t_endereco VALUES ('AEENmlkAzGNUT07',2,1,18,'546','sdasdsadaasd','assaasas','11222111',1);
INSERT INTO t_endereco VALUES ('AEu2NTZlqw6cpTn',25,1,15,'61','Benjamin Constant 2000','Campinas','13010-142',1);
INSERT INTO t_endereco VALUES ('AEWC7P3xKeO2nZl',2,1,4,'232','Sta.fernanda, 232','Macei�','',1);
INSERT INTO t_endereco VALUES ('AEWMHU7sVgJ4xSd',2,1,3,'12','av sandorval arroxelas 456, ap 12','maceio','57030-785',1);
INSERT INTO t_endereco VALUES ('AEChYzqh8Zc4NEv',2,6,10,'304','.','Macei�','57046-350',1);
INSERT INTO t_endereco VALUES ('AEfntl4VULKBsrq',2,1,1,'113/20','Comendador Palmeira','Macei�','57021-150',1);
INSERT INTO t_endereco VALUES ('AEjjKl4VMDmdWNE',2,1,6,'03','Sto. eduardo q-27 edf. Santa Catarina','Macei�','57030-730',1);
INSERT INTO t_endereco VALUES ('AEy4fIj2LmHq2Lu',2,1,3,'732/40','Dep. Jose lages','Maceio','57035-330',1);
INSERT INTO t_endereco VALUES ('AE1putsjipwvCJQ',2,6,2,'','Cj. Acau�, Qd. B1 nr. 25','Macei�','57073-350',1);
INSERT INTO t_endereco VALUES ('AElJyhg7YXOFEvm',2,1,0,'','','','',1);
INSERT INTO t_endereco VALUES ('AEyqfYPGxwxofe5',2,1,1,'968','Ant�nio Proc�pio','Macei�','57057-460',1);
INSERT INTO t_endereco VALUES ('AE1fqDQjM7AVoK5',13,1,16,'30','Maria Toledo','Resplendor','35.230-00',1);
INSERT INTO t_endereco VALUES ('AEyTWhuXqLI3oR4',2,1,15,'234','flores','maceio','57056670',1);
INSERT INTO t_endereco VALUES ('AEpZ27AVoRG1u5q',2,2,1,'350','Fernandes Lima,','Macei�','57050',1);
INSERT INTO t_endereco VALUES ('AEcxkx0BcN0t4Fg',2,1,4,'202','Sta.Fernanda, 232 Ed Jahu','Macei�','57035-390',1);
INSERT INTO t_endereco VALUES ('AE8HCPiTudq1CdO',2,1,2,'49','18, Qd. G2, Conj. Graciliano Ramos','Maceio','57073340',1);
INSERT INTO t_endereco VALUES ('AELdvAOaw0rxL7t',19,2,15,'334/12','Princesa Isabel','Rio de Janeiro','22011-010',1);
INSERT INTO t_endereco VALUES ('AEKctXj3NhQsWya',2,1,1,'44','Ranildo Cavalcanti','Maceio','57052-200',1);
INSERT INTO t_endereco VALUES ('AEV7FTeYI42En7J',2,1,2,'150','Liberato Mitchell','Macei�','57083140',1);
INSERT INTO t_endereco VALUES ('AEiK9nB5zaC6F9D',2,1,11,'92','Francisco J. dos Santos','Macei�','57440041',1);
INSERT INTO t_endereco VALUES ('AEykebTBzoAGEtr',2,6,10,'104','Conj Jos� Ten�rio, Bloco 16','Macei�','57000000',1);
INSERT INTO t_endereco VALUES ('AEX5IUKHxDr8Y42',2,1,0,'','','','',1);
INSERT INTO t_endereco VALUES ('AEFx3LkUmhzhQGo',2,1,1,'113/12','Com Palmeira','Macei�','57021-150',1);
INSERT INTO t_endereco VALUES ('AEu2pZPNSkolrF1',2,1,2,'135','Aurora Leite Pessoa','Macei�','57072-360',1);
INSERT INTO t_endereco VALUES ('AEemmxJbK4uLrL4',13,1,15,'','','','',1);
INSERT INTO t_endereco VALUES ('AEBTxZygYyBrpf4',2,1,0,'','','','',1);
INSERT INTO t_endereco VALUES ('AEluomsxLRboCYc',2,5,19,'','','','',1);
INSERT INTO t_endereco VALUES ('AEfTaKINTZNKIO2',2,1,14,'112','�ngelo Martins','Macei�','57031-320',1);
INSERT INTO t_endereco VALUES ('AEFFjmy8z1PpIaK',2,1,0,'','','MACEIO','57015280',1);
INSERT INTO t_endereco VALUES ('AEP7gekqEJ397kq',2,1,0,'','B','maceio','57040220',1);
INSERT INTO t_endereco VALUES ('AE2cmWLlb9OwecL',2,1,11,'46-b','Santa Ana','Macei�','57040-280',1);
INSERT INTO t_endereco VALUES ('AEoMyB3vWoGKbLd',2,2,7,'1183','SIQUEIRA CAMPOS','MACEIO','57010001',1);
INSERT INTO t_endereco VALUES ('AEAiptVm4EAhRzp',2,3,16,'26','Democratas','Macei�','57015710',1);
INSERT INTO t_endereco VALUES ('AEp24wd31ZgYywl',2,6,4,'203','castelo branco qd 13 bl c','maceio','57036320',1);
INSERT INTO t_endereco VALUES ('AEfCo6NLJHKsiwt',2,2,1,'129','Rotary','Macei�','57050-480',1);
INSERT INTO t_endereco VALUES ('AEAYlVDti8c2RXV',2,2,1,'129','Rotary','Macei�','57050-480',1);
INSERT INTO t_endereco VALUES ('AElWQiZXVTOEmki',2,1,7,'291','Prof. Luiz Lavenere Machado','Macei�','57010-310',1);
INSERT INTO t_endereco VALUES ('AEoUODBzxCWa063',2,2,2,'57','Macei� Lot. Lagoa Azul','Macei�','57060-110',1);
INSERT INTO t_endereco VALUES ('AEYCvXpfGwkaZHx',2,6,6,'103','conj sto eduardo qd27, edf sta catarina','maceio','57030-000',1);
INSERT INTO t_endereco VALUES ('AEyqb7z0Ai6VfPF',2,2,4,'apto 1','joao davino 936 ed enseada bloco A','maceio','57037-000',1);
INSERT INTO t_endereco VALUES ('AEIJRVKsa0NDdNm',2,1,2,'ap.04','estudante jose diniz bina n.100','maceio','57080-000',1);
INSERT INTO t_endereco VALUES ('AE8ij9BzECyo53T',2,6,1,'bl 08/','Conj. Resid. Morada das �rvores','Macei�','57000-000',1);
INSERT INTO t_endereco VALUES ('AEofLXoe42r9JPw',2,1,0,'','','','',1);
INSERT INTO t_endereco VALUES ('AEN4s2Bj1RoQqg5',2,6,2,'Quadra','Condom�nio Aldebaran (Beta)','Macei�','57080-900',1);
INSERT INTO t_endereco VALUES ('AE071JywCsERHFD',2,1,1,'','','Macei�','',1);
INSERT INTO t_endereco VALUES ('AErhMuWTRX964UK',2,1,0,'','','Macei�','',1);
INSERT INTO t_endereco VALUES ('AEIm7XV1YWKIxDB',2,3,14,'','','Macei�','',1);
INSERT INTO t_endereco VALUES ('AEfv1k2KIyl3DAi',2,1,3,'','','','',1);
INSERT INTO t_endereco VALUES ('AEeSfPpeWws9ZPV',2,1,3,'','','','',1);
INSERT INTO t_endereco VALUES ('AEaibDd30I0Ixvd',2,1,15,'649','Agnelo Barbosa','Macei�','57011390',1);
INSERT INTO t_endereco VALUES ('AEyQmpJjTA5hJOw',2,1,0,'','','','',1);
INSERT INTO t_endereco VALUES ('AEslRbmOoQvPhRq',2,1,0,'132','Cincinato Pinto','Macei�','57020050',1);
INSERT INTO t_endereco VALUES ('AE7xHMSeANftH3g',2,1,2,'174','quadra c 2','Maceio','57070-330',1);
INSERT INTO t_endereco VALUES ('AEkun5VTY4SQFLB',2,1,15,'146','51','Macei�','57080-000',1);
INSERT INTO t_endereco VALUES ('AEj5s206jv28Y4a',2,2,2,'','DURVAL DE G�ES MONTERIO 3','MACEI�','57080000',1);
INSERT INTO t_endereco VALUES ('AEOmRr97OMIyfdb',2,6,10,'004','conj. jose tenorio bloco 110','maceio','57046350',1);
INSERT INTO t_endereco VALUES ('AECUFDZdyUmAN9n',2,6,5,'','Jo�o Sampaio II qd 3D lote 42','Macei�','57084420',1);
INSERT INTO t_endereco VALUES ('AEaGj1BzwuGMJ5b',2,1,0,'','','','',1);
INSERT INTO t_endereco VALUES ('AEZfSsa0e3Zdbge',2,3,14,'','','','',1);
INSERT INTO t_endereco VALUES ('AEee3EnmdcFEDCB',19,2,15,'501/20','Gal.San Martin - Leblon','Rio de Janeiro','22441-010',1);
INSERT INTO t_endereco VALUES ('AEIqtN0lOheHaDY',2,1,1,'28','el�i de lemos fran�a','macei�','57052-350',1);
INSERT INTO t_endereco VALUES ('AEOGtOTmP2YrEZA',2,6,14,'431','Conj. Paju�ara, Rua Pedro Am�rico, Q.\\','Macei�','57030-580',1);
INSERT INTO t_endereco VALUES ('AEd3Q3wZkFSdGhK',25,1,0,'41','Jos� Fontebasso','Jundia�','13.218-67',1);
INSERT INTO t_endereco VALUES ('AEKpkF0BcNKlWx0',2,1,9,'5','da Palma','Maceio','57000000',1);
INSERT INTO t_endereco VALUES ('AEo5gBO9uPMfsVg',2,1,1,'','','Macei�','',1);
INSERT INTO t_endereco VALUES ('AE68Hi1Cl4p8RAj',2,6,2,'08','Conj. Jardim Petr�polis II, Qd. H2','Macei�','57080-170',1);
INSERT INTO t_endereco VALUES ('AEHWnQra1K5GxgZ',2,1,15,'',' Bom Destino 75','Macei�','57014-660',1);
INSERT INTO t_endereco VALUES ('AE8g5ypg7Yri1SJ',2,1,2,'105','Desp. H�lio Miranda','Macei�','57072770',1);
INSERT INTO t_endereco VALUES ('AEPl2D6PyhCtc3U',2,1,0,'','','','',1);
INSERT INTO t_endereco VALUES ('AEY4Tul4VE7YPyp',2,1,1,'28','El�i de Lemos Fran�a','Macei�','57052-350',1);
INSERT INTO t_endereco VALUES ('AEecnsNgJchSdG9',2,6,10,'65','Loteamento Samba�ba, Lotes 6 e 7','Macei�','57047-050',1);
INSERT INTO t_endereco VALUES ('AELQ9u5Op8l4FoR',2,1,0,'','','','',1);
INSERT INTO t_endereco VALUES ('AEv58tGhCd2vYrM',2,1,15,'36','Rua Amazonas 36','Macei�','',1);
INSERT INTO t_endereco VALUES ('AE8m1ez2vYVgJca',2,1,6,'76','Milton Correia','Macei�','57000-000',1);
INSERT INTO t_endereco VALUES ('AEHP8tOpSty9u5y',2,1,11,'131�','Floresta','Mac�io','57040290',1);
INSERT INTO t_endereco VALUES ('AEHlwJcx0lqLezU',2,1,0,'','','','',1);
INSERT INTO t_endereco VALUES ('AE0H8BcVoZAj2Dm',2,2,18,'12','da Paz','MaCEio','',1);
INSERT INTO t_endereco VALUES ('AENYpK5y9CPqTu5',2,6,2,'9a/303','Res. Tabuleiro do Martins','Macei�','57073-460',1);
INSERT INTO t_endereco VALUES ('AEHgbgBO1urEZsN',2,1,2,'115A','A24, Qd 22, Benedito Bentes 1','Macei�','57084040',1);
INSERT INTO t_endereco VALUES ('AEYsvAVanQN8B4p',2,2,15,'Zona R','Mirante da Praia Lagoa do Pau','Coruripe','57230-000',1);
INSERT INTO t_endereco VALUES ('AEYsCXaDeHUnQjU',2,1,15,'Zona R','Mirante da Praia Lagoa do Pau','Coruripe','57230-000',1);
INSERT INTO t_endereco VALUES ('AEoZERkNoR4x0B4',2,1,15,'Zona R','Mirante da Praia Lagoa do Pau','Coruripe','57230-000',1);
INSERT INTO t_endereco VALUES ('AEyLW1mPiTYr2D6',2,1,15,'07','CONJ. VALE DO FEITOSA, Q. N','MACEI�','57042-270',1);
INSERT INTO t_endereco VALUES ('AEwd0dqLezE7kV8',2,1,15,'12','Dr raramma arl','maceio','5720000',1);
INSERT INTO t_endereco VALUES ('AEHMXafIbEBOhSd',2,1,1,'215','Gon�alves Dias','Macei�','57021-330',1);
INSERT INTO t_endereco VALUES ('AERITm5Gp0lWFgR',2,1,4,'','','Macei�','57035000',1);
INSERT INTO t_endereco VALUES ('AERq5inkpujwJW1',2,1,2,'66','CJ. G. RAMOS, QD: F-6, R: 58','MACEI�','57073-340',1);
INSERT INTO t_endereco VALUES ('AE2winI3oJG9uPi',2,2,0,'','desenbargador valente de lima','','',1);
INSERT INTO t_endereco VALUES ('AEwBEZcFgJOhKdy',2,1,1,'56','Jos� Malta de Alencar','Macei�','57052160',1);
INSERT INTO t_endereco VALUES ('AELJLQVgB49uPaD',2,6,10,'47-203','jose tenorio','Maceio','',1);
INSERT INTO t_endereco VALUES ('AEQsLYjEZkFSdG1',2,2,1,'','Rotary 129','Macei�','57050480',1);
INSERT INTO t_endereco VALUES ('AEPzKXq1udiTmXq',2,1,0,'','','','',1);
INSERT INTO t_endereco VALUES ('AEdjKXaDeP2vQrU',2,1,2,'10','10 quadra G','Maceio','57073020',1);
INSERT INTO t_endereco VALUES ('AEofqTmXqTYrUvY',2,1,1,'1012','Jorn. Augusto Vaz Filho','Macei�','57057-150',1);
INSERT INTO t_endereco VALUES ('AE7dwJ4x0BG9u5y',2,1,1,'','El�i de Lemos Fran�a, 28','Macei�','57052-350',1);
INSERT INTO t_endereco VALUES ('AEoJM7kVoZcF8Bc',2,1,16,'237','7 de setembro','','57020700',1);
INSERT INTO t_endereco VALUES ('AEkliTKBAziih87',2,1,3,'1217','Durval Guimaraes','Maceio','57000000',1);
INSERT INTO t_endereco VALUES ('AEpgPyp0ZAFwf6P',2,1,0,'','','','',1);
INSERT INTO t_endereco VALUES ('AEAqfQzypgJIzqh',2,1,0,'','','','',1);
INSERT INTO t_endereco VALUES ('AE2yfYPypgJAra1',2,1,1,'261','CEL. FRANCISCO SILVA','MACEIO','57050190',1);
INSERT INTO t_endereco VALUES ('AEXGRs3w7I3EfQr',2,1,2,'149','do Quadro','Maceio','57061-120',1);
INSERT INTO t_endereco VALUES ('AEnlotOhC51mPiL',2,1,10,'','NSS de F�tima <62','Macei�','57045-820',1);
INSERT INTO t_endereco VALUES ('AEJI1mHiLmz2vYj',2,1,1,'0087','Dr. Jos� de Castro Azevedo','Macei�','57050-240',1);
INSERT INTO t_endereco VALUES ('AEK69C5qTmzUnYj',2,1,6,'264','Dr. Jos� Milton Correia','Macei�','57030-690',1);
INSERT INTO t_endereco VALUES ('AEVEP2v6PqD6r2D',2,1,0,'','','','',1);
INSERT INTO t_endereco VALUES ('AEiJEJW9uHwZcpC',2,1,2,'1657','RUA ROTARY,','MACEI�','57082-030',1);
INSERT INTO t_endereco VALUES ('AEvyBG1uH2J4pKX',25,1,15,'250','Maria Joaquina de Abreu,','Ferraz de Vasconcelos','08543140',1);
INSERT INTO t_endereco VALUES ('AEmS3wR4F8dG9Kd',2,1,3,'46/304','R. Dep. Elizeu Teixeira','Macei�','57035-240',1);
INSERT INTO t_endereco VALUES ('AELO7kN8BchSlWp',2,1,5,'65','b57 qdb 56','macei�','57084040',1);
INSERT INTO t_endereco VALUES ('AESHCP2nIb8tWpK',2,1,0,'','','','',1);
INSERT INTO t_endereco VALUES ('AEr830lG9CzUnQj',2,2,6,'201','Cel Adauto Gomes Barbosa 750 - Res. Jati�ca 1 - Bl. 10','Macei�','57031-060',1);
INSERT INTO t_endereco VALUES ('AE27afAVoBGTezU',2,1,11,'130','Maria de Lourdes Moura Teixeira  Conj. Jose da Silva Peixoto','Maceio','57041-160',1);
INSERT INTO t_endereco VALUES ('AEbFI2nIbwtOhC5',2,1,9,'54','Maria Ramires Martins','Macei�','57042390',1);
INSERT INTO t_endereco VALUES ('AECPKXiTmXaD6Hi',2,1,3,'31','Jos� Julio Sauer','Maceio','57035540',1);
INSERT INTO t_endereco VALUES ('AE8AL6jwZkhC5qT',1,5,19,'','','','',2);
INSERT INTO t_endereco VALUES ('AEiEjwRkF8dy1uX',2,1,4,'','jutiuca','maceio','150',1);
INSERT INTO t_endereco VALUES ('AEV9cNgRsbo7Ira',2,1,3,'139/50','Senador Rui Palmeira','Macei�','5',1);
INSERT INTO t_endereco VALUES ('AEf61mPqTmz2nYz',2,3,1,'461/ca','Jos� da Silveira Camerino','Macei�','57057-420',1);
INSERT INTO t_endereco VALUES ('AEMzuzM7sNK5qDQ',2,1,3,'102','antonio cancacao','maceio','',1);
INSERT INTO t_endereco VALUES ('AEeVezMfIbgJ4F0',2,3,2,'08','Augusto Quintela Cavalcante','','57082-350',1);
INSERT INTO t_endereco VALUES ('AEQNYbwZA3gB4F8',2,1,2,'08','Augusto Quintela','Macei�','57082-350',1);
INSERT INTO t_endereco VALUES ('AE4GAVgB4puPiLe',2,5,19,'','','','',1);
INSERT INTO t_endereco VALUES ('AEiMWx0C5GTuXy9',2,1,16,'215','Dem�crito Gracindo','Macei�','57014-290',1);
INSERT INTO t_endereco VALUES ('AEloHMZsNg5yTmP',2,6,15,'101','Resd. Jacarecica Edf. Indai�, BL 01','Macei�','',1);
INSERT INTO t_endereco VALUES ('AEFYTeHaDejMfIb',2,1,5,'65','','maceio','57084040',1);
INSERT INTO t_endereco VALUES ('AE8l8tWp0BOpSt4',2,5,6,'','','','',1);
INSERT INTO t_endereco VALUES ('AEnmFSlWp05G9Kl',2,1,15,'269','DA INDEPENDENCIA','DELMIRO GOUVEIA-AL','57480000',1);
INSERT INTO t_endereco VALUES ('AElN6zaLmXUv6Hi',2,1,5,'65','B57 QD 56','MACEI�','57084000',1);
INSERT INTO t_endereco VALUES ('AEQ2tWpStchSt4N',1,5,19,'','','','',2);
INSERT INTO t_endereco VALUES ('AE5MG1u5yamQr2D',2,1,0,'','','','',1);
INSERT INTO t_endereco VALUES ('AEDQLeHi1mzaLu5',2,2,7,'1008','Siqueira Campos','MAcei�','57010-000',1);
INSERT INTO t_endereco VALUES ('AEAVIbEn6HMv6Ha',2,1,4,'194','Walfrido Rocha','Macei�','57036800',1);
INSERT INTO t_endereco VALUES ('AEvY9KdWxgl4FgR',2,1,1,'543','Pedro de Oliveira Rocha','Macei�','57057-560',1);
INSERT INTO t_endereco VALUES ('AERA16rUnIN8B4x',2,2,4,'1333','Luis Ramalho de Castro 1333 Jatiuca','Maceio','',1);
INSERT INTO t_endereco VALUES ('AErwHMRkN8XiD6z',2,1,4,'401','Santa Fernanda,232','Macei�','57035-390',1);
INSERT INTO t_endereco VALUES ('AEwBMnIjUvIjUv6',2,1,4,'232 70','Santa fernanda','Maceio','57035-390',1);
INSERT INTO t_endereco VALUES ('AEaS3gtWhCHUnIj',2,1,0,'','','','',1);
INSERT INTO t_endereco VALUES ('AExvG9mPaLI3wZs',2,1,0,'','','','',1);
INSERT INTO t_endereco VALUES ('AEM7U7cpKtivI3o',2,2,6,'1006','PRESIDENTE AGOSTINHO DA SILVA NEVES','MACEIO','57030240',1);
INSERT INTO t_endereco VALUES ('AEGDWhKlWFKt4Fg',2,1,15,'','','','',1);
INSERT INTO t_endereco VALUES ('AEwm1mHaLerUnYr',6,1,15,'','','','',1);
INSERT INTO t_endereco VALUES ('AE4yBWpKlGTmPqL',2,2,4,'704','�lvaro Otac�lio 3535','Macei�','57035-180',1);
INSERT INTO t_endereco VALUES ('AExEXqD6z2ZkVoR',2,1,11,'26','J quadra 9 -Conj Jose da Silva Peixoto','Macei�','57041120',1);
INSERT INTO t_endereco VALUES ('AEIc7sN8B49C5y1',2,1,13,'','marechal mascarenhas de moraes n.� 96','macei�','57038120',1);
INSERT INTO t_endereco VALUES ('AEIDWp0Jk3oZAj2',2,2,3,'2865/1','Robert Kennedy','Macei�','57035-160',1);
INSERT INTO t_endereco VALUES ('AEetqfc9ejRWT6b',2,1,14,'488','dr zeferino rodriques','macei�','57030-080',1);
INSERT INTO t_endereco VALUES ('AE80FujwtGfjoBG',2,6,2,'125','Conj. Osman Loureiro, Qd. B2','Macei�','57071330',1);
INSERT INTO t_endereco VALUES ('AEKbY305an49ejo',2,6,10,'10','Cond.Aldebaram Alfa  Qd. C  Lote 10','Maceio','57080-900',1);
INSERT INTO t_endereco VALUES ('AE7RURN0dqfAVgl',25,1,0,'91','CARLOS SARA','S�O PAULO','05551030',1);
INSERT INTO t_endereco VALUES ('AEfREdqerwdqDIV',2,1,2,'150','Liberato Mitchell','Macei�','57083-140',1);
INSERT INTO t_endereco VALUES ('AEoB8dinA3CHMRO',25,1,15,'297','Barbara Blumer','Sumar�','13170360',1);
INSERT INTO t_endereco VALUES ('AEnh4hCP2ncpCXi',2,3,1,'42','itatiaia','macei�','57051385',1);

#
# Table structure for table 't_estado'
#
CREATE TABLE t_estado (
  cod_estado tinyint(2) DEFAULT '0' NOT NULL auto_increment,
  uf_estado char(2) DEFAULT '' NOT NULL,
  desc_estado varchar(30) DEFAULT '' NOT NULL,
  PRIMARY KEY (cod_estado)
);

#
# Dumping data for table 't_estado'
#

INSERT INTO t_estado VALUES (1,'AC','Acre');
INSERT INTO t_estado VALUES (2,'AL','Alagoas');
INSERT INTO t_estado VALUES (3,'AP','Amap�');
INSERT INTO t_estado VALUES (4,'AM','Amazonas');
INSERT INTO t_estado VALUES (5,'BA','Bahia');
INSERT INTO t_estado VALUES (6,'CE','Cear�');
INSERT INTO t_estado VALUES (7,'DF','Distrito Federal');
INSERT INTO t_estado VALUES (8,'ES','Esp�rito Santo');
INSERT INTO t_estado VALUES (9,'GO','Goi�s');
INSERT INTO t_estado VALUES (10,'MA','Maranh�o');
INSERT INTO t_estado VALUES (11,'MT','Mato Grosso');
INSERT INTO t_estado VALUES (12,'MS','Mato Grosso do Sul');
INSERT INTO t_estado VALUES (13,'MG','Minas Gerais');
INSERT INTO t_estado VALUES (14,'PA','Par�');
INSERT INTO t_estado VALUES (15,'PB','Para�ba');
INSERT INTO t_estado VALUES (16,'PR','Paran�');
INSERT INTO t_estado VALUES (17,'PE','Pernambuco');
INSERT INTO t_estado VALUES (18,'PI','Piau�');
INSERT INTO t_estado VALUES (19,'RJ','Rio de Janeiro');
INSERT INTO t_estado VALUES (20,'RN','Rio Grande do Norte');
INSERT INTO t_estado VALUES (21,'RS','Rio Grande do Sul');
INSERT INTO t_estado VALUES (22,'RO','Rond�nia');
INSERT INTO t_estado VALUES (23,'RR','Roraima');
INSERT INTO t_estado VALUES (24,'SC','Santa Catarina');
INSERT INTO t_estado VALUES (25,'SP','S�o Paulo');
INSERT INTO t_estado VALUES (26,'SE','Sergipe');
INSERT INTO t_estado VALUES (27,'TO','Tocantins');

#
# Table structure for table 't_faixa_etaria'
#
CREATE TABLE t_faixa_etaria (
  cod_faixa_etaria tinyint(2) DEFAULT '0' NOT NULL,
  desc_faixa_etaria varchar(11) DEFAULT '' NOT NULL,
  PRIMARY KEY (cod_faixa_etaria)
);

#
# Dumping data for table 't_faixa_etaria'
#

INSERT INTO t_faixa_etaria VALUES (1,'At� 10 anos');
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
  cod_anuncio varchar(15) binary DEFAULT '0' NOT NULL,
  cod_bairro tinyint(3) DEFAULT '0' NOT NULL,
  PRIMARY KEY (cod_anuncio)
);

#
# Dumping data for table 't_imovel'
#

INSERT INTO t_imovel VALUES ('AOVRU7kN0txSlO9',2);
INSERT INTO t_imovel VALUES ('AOW0boJcNoB4FgR',15);
INSERT INTO t_imovel VALUES ('AOq1WhmPiLYrMfA',1);
INSERT INTO t_imovel VALUES ('AO9svQ3oJWTeH2v',9);
INSERT INTO t_imovel VALUES ('AOcfyDYz2DI3wZA',1);
INSERT INTO t_imovel VALUES ('AO2JMZkNgRWhKdG',1);
INSERT INTO t_imovel VALUES ('AOmFrEZs3EJcx0t',1);
INSERT INTO t_imovel VALUES ('AOKX0dqTmPUnQjM',1);
INSERT INTO t_imovel VALUES ('AOb0VgtWpS5P2vY',1);
INSERT INTO t_imovel VALUES ('AOJTN8BO9CzUfsV',1);
INSERT INTO t_imovel VALUES ('AOwhApKdy1QbwRk',1);
INSERT INTO t_imovel VALUES ('AO0fafA3wZ4xSlO',1);
INSERT INTO t_imovel VALUES ('AOzf2fsF0lavQbw',1);
INSERT INTO t_imovel VALUES ('AOwqlOhKdGnQjMf',1);
INSERT INTO t_imovel VALUES ('AOvVYboJcFBWhKd',1);
INSERT INTO t_imovel VALUES ('AO0WINgBWhmPavY',1);
INSERT INTO t_imovel VALUES ('AOf3lGLeH2ZkN8B',1);
INSERT INTO t_imovel VALUES ('AOoWfAV7B4x0t4x',1);
INSERT INTO t_imovel VALUES ('AO24vH2vQbgBWpS',16);
INSERT INTO t_imovel VALUES ('AO6oHE0dG9KdG9u',16);
INSERT INTO t_imovel VALUES ('AOcSV8BWpSPaDYr',4);
INSERT INTO t_imovel VALUES ('AOI7qnI3oRO9uXi',4);
INSERT INTO t_imovel VALUES ('AOtFAFSdG1I3oJ4',1);
INSERT INTO t_imovel VALUES ('AOuOBGT6jEZcxSd',1);
INSERT INTO t_imovel VALUES ('AOdFQV8tWhezUnQ',1);
INSERT INTO t_imovel VALUES ('AOgcnsxSlOvI3nI',1);
INSERT INTO t_imovel VALUES ('AOxBvsxSdyvQbwR',1);
INSERT INTO t_imovel VALUES ('AOQEHMRWhurERcp',16);
INSERT INTO t_imovel VALUES ('AOQ7aDYrUnsN8B4',16);
INSERT INTO t_imovel VALUES ('AOL4fsNgRkx0tWp',16);
INSERT INTO t_imovel VALUES ('AOuLOTez2nsN8B4',16);
INSERT INTO t_imovel VALUES ('AOcgN0lG9uzUfAV',6);
INSERT INTO t_imovel VALUES ('AOPJEZcF8tqTeBW',6);
INSERT INTO t_imovel VALUES ('AObZ2fkF0tiLYjE',15);
INSERT INTO t_imovel VALUES ('AOh7ivA3wRO9uPa',15);
INSERT INTO t_imovel VALUES ('AOOBoBOhC5UfA3o',4);
INSERT INTO t_imovel VALUES ('AO482ZsVoROhKdG',4);
INSERT INTO t_imovel VALUES ('AOY1c9C5y16zUnI',4);
INSERT INTO t_imovel VALUES ('AOMknsF0lGnI3oJ',4);
INSERT INTO t_imovel VALUES ('AO2CNKXiD63wJcx',4);
INSERT INTO t_imovel VALUES ('AOwQTmH2vYF8tO9',4);
INSERT INTO t_imovel VALUES ('AOGrCjwJ4hmzUnI',3);
INSERT INTO t_imovel VALUES ('AO1t7sxK5iDYbwR',3);
INSERT INTO t_imovel VALUES ('AODHuzMfA38lOhC',3);
INSERT INTO t_imovel VALUES ('AOnFIV8B4FK5y1u',15);
INSERT INTO t_imovel VALUES ('AOt1WhK5y16z2vQ',10);
INSERT INTO t_imovel VALUES ('AOsgrwZkF8dqLeH',10);
INSERT INTO t_imovel VALUES ('AOJDyL6rMfcxSlG',14);
INSERT INTO t_imovel VALUES ('AOyWZcpKdyDYr2n',15);
INSERT INTO t_imovel VALUES ('AOvruzUnI3K5y1m',2);
INSERT INTO t_imovel VALUES ('AOJ1c9C5y1ez2nQ',1);
INSERT INTO t_imovel VALUES ('AOnberMnQjoRsVg',1);
INSERT INTO t_imovel VALUES ('AOTqJG9C5GDezav',1);
INSERT INTO t_imovel VALUES ('AOlFroJ4xSXqTmP',1);
INSERT INTO t_imovel VALUES ('AOploJWx0lq1uXq',1);
INSERT INTO t_imovel VALUES ('AOyBUfsVoRWhC5y',1);
INSERT INTO t_imovel VALUES ('AOXnqfAVgBWhKdy',4);
INSERT INTO t_imovel VALUES ('AOG6omPjxTtaoKP',3);
INSERT INTO t_imovel VALUES ('AOaGYN1DZXoCQ4i',3);
INSERT INTO t_imovel VALUES ('AOD9b16ca8z9nsq',10);
INSERT INTO t_imovel VALUES ('AODTF0uIcyTfty2',3);
INSERT INTO t_imovel VALUES ('AOjTFec2KzvBjge',15);
INSERT INTO t_imovel VALUES ('AOo5RF6qSsfr1B2',15);
INSERT INTO t_imovel VALUES ('AO6FQ3wZAboJcNg',15);
INSERT INTO t_imovel VALUES ('AOVsvI3gBWhCXiD',15);
INSERT INTO t_imovel VALUES ('AO2W7sVgRchKdGh',16);
INSERT INTO t_imovel VALUES ('AOMAQpnXFeigYFD',3);
INSERT INTO t_imovel VALUES ('AO0IB3LJqwkaZ53',15);
INSERT INTO t_imovel VALUES ('AOKMXcpKdGLeHaD',2);
INSERT INTO t_imovel VALUES ('AOajmrM7sNK5qTm',1);
INSERT INTO t_imovel VALUES ('AOTpsFS5qLIbwRk',6);
INSERT INTO t_imovel VALUES ('AOjU52vYrUZsVoZ',1);
INSERT INTO t_imovel VALUES ('AOcsDsN0lynIV8t',15);
INSERT INTO t_imovel VALUES ('AOmEyL6z2vA3wRk',15);

#
# Table structure for table 't_marca_veiculo'
#
CREATE TABLE t_marca_veiculo (
  cod_marca_veiculo tinyint(2) DEFAULT '0' NOT NULL auto_increment,
  desc_marca_veiculo varchar(20) DEFAULT '' NOT NULL,
  PRIMARY KEY (cod_marca_veiculo)
);

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
  cod_pais smallint(3) DEFAULT '0' NOT NULL,
  desc_pais varchar(30) DEFAULT '' NOT NULL,
  PRIMARY KEY (cod_pais)
);

#
# Dumping data for table 't_pais'
#

INSERT INTO t_pais VALUES (1,'Brasil');
INSERT INTO t_pais VALUES (2,'Exterior');

#
# Table structure for table 't_profissao'
#
CREATE TABLE t_profissao (
  cod_profissao tinyint(2) DEFAULT '0' NOT NULL auto_increment,
  desc_profissao varchar(50) DEFAULT '' NOT NULL,
  PRIMARY KEY (cod_profissao)
);

#
# Dumping data for table 't_profissao'
#

INSERT INTO t_profissao VALUES (1,'executivo/gerente');
INSERT INTO t_profissao VALUES (2,'profissional liberal (m�dico, advogado, etc.)');
INSERT INTO t_profissao VALUES (3,'acad�mico/professor');
INSERT INTO t_profissao VALUES (4,'inform�tica/engenheiro');
INSERT INTO t_profissao VALUES (5,'t�cnico/engenheiro');
INSERT INTO t_profissao VALUES (6,'servi�o/atendimento ao cliente');
INSERT INTO t_profissao VALUES (7,'eclesi�stico/administrativo');
INSERT INTO t_profissao VALUES (8,'vendas/marketing');
INSERT INTO t_profissao VALUES (9,'comerciante/artes�o');
INSERT INTO t_profissao VALUES (10,'estudante/universit�rio');
INSERT INTO t_profissao VALUES (11,'estudante/secundarista');
INSERT INTO t_profissao VALUES (12,'aut�nomo/empres�rio');
INSERT INTO t_profissao VALUES (13,'desempregado, � procura de trabalho');
INSERT INTO t_profissao VALUES (14,'aposentado');
INSERT INTO t_profissao VALUES (15,'outra');

#
# Table structure for table 't_subcategoria_anuncio'
#
CREATE TABLE t_subcategoria_anuncio (
  cod_subcategoria_anuncio tinyint(3) DEFAULT '0' NOT NULL auto_increment,
  desc_subcategoria_anuncio varchar(50) DEFAULT '' NOT NULL,
  cod_categoria_anuncio tinyint(3) DEFAULT '0' NOT NULL,
  PRIMARY KEY (cod_subcategoria_anuncio)
);

#
# Dumping data for table 't_subcategoria_anuncio'
#

INSERT INTO t_subcategoria_anuncio VALUES (1,'Carro',1);
INSERT INTO t_subcategoria_anuncio VALUES (2,'Caminh�o',1);
INSERT INTO t_subcategoria_anuncio VALUES (3,'Trator',1);
INSERT INTO t_subcategoria_anuncio VALUES (4,'Moto',1);
INSERT INTO t_subcategoria_anuncio VALUES (5,'Especiais',1);
INSERT INTO t_subcategoria_anuncio VALUES (6,'Utilit�rios',1);
INSERT INTO t_subcategoria_anuncio VALUES (7,'N�utica',1);
INSERT INTO t_subcategoria_anuncio VALUES (9,'Casa',2);
INSERT INTO t_subcategoria_anuncio VALUES (10,'Apartamento',2);
INSERT INTO t_subcategoria_anuncio VALUES (11,'S�tio',2);
INSERT INTO t_subcategoria_anuncio VALUES (12,'Terrenos',2);
INSERT INTO t_subcategoria_anuncio VALUES (13,'Ponto Comercial',2);
INSERT INTO t_subcategoria_anuncio VALUES (14,'Residencial',4);
INSERT INTO t_subcategoria_anuncio VALUES (15,'Celular',4);
INSERT INTO t_subcategoria_anuncio VALUES (16,'Antiguidade',21);
INSERT INTO t_subcategoria_anuncio VALUES (27,'Quadros',21);
INSERT INTO t_subcategoria_anuncio VALUES (29,'Dom�nios',19);
INSERT INTO t_subcategoria_anuncio VALUES (28,'CPU�s',19);
INSERT INTO t_subcategoria_anuncio VALUES (20,'Acess�rios',19);
INSERT INTO t_subcategoria_anuncio VALUES (21,'Computador Desktop',19);
INSERT INTO t_subcategoria_anuncio VALUES (22,'Computador Port�til',19);
INSERT INTO t_subcategoria_anuncio VALUES (23,'Impressoras',19);
INSERT INTO t_subcategoria_anuncio VALUES (24,'Servi�os',19);
INSERT INTO t_subcategoria_anuncio VALUES (25,'Software',19);
INSERT INTO t_subcategoria_anuncio VALUES (26,'Fazenda',2);
INSERT INTO t_subcategoria_anuncio VALUES (30,'Driver',19);
INSERT INTO t_subcategoria_anuncio VALUES (31,'Hardware',19);
INSERT INTO t_subcategoria_anuncio VALUES (32,'Mem�ria',19);
INSERT INTO t_subcategoria_anuncio VALUES (33,'Monitor',19);
INSERT INTO t_subcategoria_anuncio VALUES (34,'MP3',19);
INSERT INTO t_subcategoria_anuncio VALUES (35,'Notebook',19);
INSERT INTO t_subcategoria_anuncio VALUES (36,'Placas',19);
INSERT INTO t_subcategoria_anuncio VALUES (37,'Scanner',19);
INSERT INTO t_subcategoria_anuncio VALUES (38,'Teclado',19);
INSERT INTO t_subcategoria_anuncio VALUES (39,'Outros',19);
INSERT INTO t_subcategoria_anuncio VALUES (0,'Sem Cateroria',7);

#
# Table structure for table 't_tipo_anuncio'
#
CREATE TABLE t_tipo_anuncio (
  cod_tipo_anuncio tinyint(1) DEFAULT '0' NOT NULL auto_increment,
  desc_tipo_anuncio varchar(15) DEFAULT '' NOT NULL,
  PRIMARY KEY (cod_tipo_anuncio)
);

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
  cod_tipo_endereco tinyint(1) DEFAULT '0' NOT NULL auto_increment,
  desc_tipo_endereco varchar(10) DEFAULT '0' NOT NULL,
  PRIMARY KEY (cod_tipo_endereco)
);

#
# Dumping data for table 't_tipo_endereco'
#

INSERT INTO t_tipo_endereco VALUES (1,'Rua');
INSERT INTO t_tipo_endereco VALUES (2,'Avenida');
INSERT INTO t_tipo_endereco VALUES (3,'Travessa');
INSERT INTO t_tipo_endereco VALUES (4,'Pra�a');
INSERT INTO t_tipo_endereco VALUES (5,'Alameda');
INSERT INTO t_tipo_endereco VALUES (6,'Outro');

#
# Table structure for table 't_tipo_usuario'
#
CREATE TABLE t_tipo_usuario (
  cod_tipo_usuario char(1) DEFAULT '0' NOT NULL,
  desc_tipo_usuario varchar(10) DEFAULT '' NOT NULL,
  PRIMARY KEY (cod_tipo_usuario)
);

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
  cod_anunciante varchar(15) binary DEFAULT '0' NOT NULL,
  login varchar(8) binary DEFAULT '' NOT NULL,
  password varchar(8) binary DEFAULT '' NOT NULL,
  cod_tipo_usuario char(1) DEFAULT '0' NOT NULL,
  cod_chegada tinyint(2) DEFAULT '0' NOT NULL,
  quando_chegou int(12) DEFAULT '0' NOT NULL,
  ultimo_acesso int(12) DEFAULT '0' NOT NULL,
  ultimo_ip varchar(15) DEFAULT '0' NOT NULL,
  PRIMARY KEY (cod_anunciante),
  UNIQUE cod_anunciante (cod_anunciante)
);

#
# Dumping data for table 't_usuario'
#

INSERT INTO t_usuario VALUES ('AEY6aeimquyHMQU','marcello','reefsurf','A',5,966513642,992951659,'200.241.146.196');
INSERT INTO t_usuario VALUES ('AEItxBFJNRV048c','leandro','5152','A',3,958931672,989099357,'200.133.126.203');
INSERT INTO t_usuario VALUES ('AE6QVY26aeinrvz','plinio','2416293','A',5,966602160,990008725,'200.241.146.213');
INSERT INTO t_usuario VALUES ('AEfntl4VULKBsrq','Alzira','1234','W',7,970142602,985457366,'200.199.95.13');
INSERT INTO t_usuario VALUES ('AEr7cNUT0Z6dkjq','broad','5152','W',3,969686617,969686617,'200.241.146.196');
INSERT INTO t_usuario VALUES ('AEF14hCdGhu5q1u','Classi-A','24107900','S',5,973082455,988593322,'200.227.207.67');
INSERT INTO t_usuario VALUES ('AE1fqDQjM7AVoK5','fsdamiao','110284','W',6,972363491,972363517,'200.251.128.53');
INSERT INTO t_usuario VALUES ('AEKruPaLeHaD6Ha','7007','654321','W',5,972213974,974682161,'200.199.95.6');
INSERT INTO t_usuario VALUES ('AEWC7P3xKeO2nZl','Olivio','840512','S',6,974574256,988894018,'200.199.95.21');
INSERT INTO t_usuario VALUES ('AEWMHU7sVgJ4xSd','tiagao19','9988797','W',6,972266571,972267077,'200.241.159.146');
INSERT INTO t_usuario VALUES ('AEChYzqh8Zc4NEv','elsilva','239801','W',6,969969343,969999957,'200.199.67.131');
INSERT INTO t_usuario VALUES ('AEjjKl4VMDmdWNE','brasil','7337','W',6,969973884,969974027,'200.199.67.114');
INSERT INTO t_usuario VALUES ('AEVEP2v6PqD6r2D','saulo','japao','W',7,986960909,989705251,'200.133.126.203');
INSERT INTO t_usuario VALUES ('AEy4fIj2LmHq2Lu','hsilvia','01102000','W',6,986834879,986834879,'200.241.145.129');
INSERT INTO t_usuario VALUES ('AE1putsjipwvCJQ','wrwilson','0202','W',2,970493707,970494795,'200.241.154.39');
INSERT INTO t_usuario VALUES ('AElJyhg7YXOFEvm','marcell2','5152','W',5,971205990,971205990,'200.199.92.37');
INSERT INTO t_usuario VALUES ('AEyqfYPGxwxofe5','rodrigo','965247','W',6,971962025,971962413,'200.199.67.86');
INSERT INTO t_usuario VALUES ('AEyTWhuXqLI3oR4','fulano','cicrano','W',5,986562548,986562623,'200.241.146.251');
INSERT INTO t_usuario VALUES ('AEpZ27AVoRG1u5q','eletro','24107900','W',5,973501325,973563645,'200.199.92.15');
INSERT INTO t_usuario VALUES ('AEcxkx0BcN0t4Fg','Igor','4040','W',6,973441646,983551278,'200.241.146.213');
INSERT INTO t_usuario VALUES ('AE8HCPiTudq1CdO','jfabio','721215','W',6,973873520,973875567,'200.241.162.3');
INSERT INTO t_usuario VALUES ('AEu2NTZlqw6cpTn','dexri','plex28ni','W',2,974972926,974972926,'200.183.56.174');
INSERT INTO t_usuario VALUES ('AELdvAOaw0rxL7t','glauce','lcarlos','W',3,975081121,975081249,'200.213.102.3');
INSERT INTO t_usuario VALUES ('AEKctXj3NhQsWya','bito','bito','W',6,975165248,975165660,'200.199.50.177');
INSERT INTO t_usuario VALUES ('AEV7FTeYI42En7J','Brian','daimon','W',4,975226426,975227231,'200.199.52.195');
INSERT INTO t_usuario VALUES ('AEiK9nB5zaC6F9D','Davi','daiane','W',4,975324561,975703897,'200.191.61.21');
INSERT INTO t_usuario VALUES ('AEykebTBzoAGEtr','Ken@51','vanessa','W',7,975367604,975368497,'200.241.146.14');
INSERT INTO t_usuario VALUES ('AEX5IUKHxDr8Y42','kcbc','coutinho','W',5,975418051,975418271,'200.241.146.215');
INSERT INTO t_usuario VALUES ('AEFx3LkUmhzhQGo','cankum','1594909','W',6,975440303,975440806,'200.241.172.3');
INSERT INTO t_usuario VALUES ('AEu2pZPNSkolrF1','Domarkes','andrews','W',4,975453355,975453882,'200.199.52.114');
INSERT INTO t_usuario VALUES ('AEemmxJbK4uLrL4','jose','teste','W',6,975494289,975494679,'200.192.176.201');
INSERT INTO t_usuario VALUES ('AEBTxZygYyBrpf4','felina','cicera','W',7,975601722,975601762,'200.199.64.54');
INSERT INTO t_usuario VALUES ('AEluomsxLRboCYc','maligna','meloap','W',6,975603819,975605105,'200.199.64.54');
INSERT INTO t_usuario VALUES ('AEfTaKINTZNKIO2','David','canso','W',7,975668807,975669439,'200.241.148.66');
INSERT INTO t_usuario VALUES ('AEFFjmy8z1PpIaK','ARCANJO','812700','W',4,975856433,977004566,'200.199.53.243');
INSERT INTO t_usuario VALUES ('AEP7gekqEJ397kq','admon','0480','W',5,975938471,985891235,'200.199.68.180');
INSERT INTO t_usuario VALUES ('AE2cmWLlb9OwecL','Alberto','murilo','W',4,975967168,975967846,'200.199.68.145');
INSERT INTO t_usuario VALUES ('AEoMyB3vWoGKbLd','PJRILUMI','000013','W',4,976027724,976027724,'200.199.53.90');
INSERT INTO t_usuario VALUES ('AEAiptVm4EAhRzp','silvio','juquinha','W',4,976186541,977156218,'200.199.95.11');
INSERT INTO t_usuario VALUES ('AEp24wd31ZgYywl','sandy','121212','W',5,976366442,985896005,'200.199.95.123');
INSERT INTO t_usuario VALUES ('AEfCo6NLJHKsiwt','sexysite','st3264','W',4,976744038,976744716,'200.199.67.131');
INSERT INTO t_usuario VALUES ('AEAYlVDti8c2RXV','caf�','st3264','W',4,976746082,977350015,'200.199.67.92');
INSERT INTO t_usuario VALUES ('AElWQiZXVTOEmki','saulinh0','alfa2s','W',4,976755229,976756195,'200.241.159.135');
INSERT INTO t_usuario VALUES ('AEoUODBzxCWa063','anderson','120978','W',6,976848924,978369806,'200.199.58.142');
INSERT INTO t_usuario VALUES ('AEYCvXpfGwkaZHx','teixnet','9629000','W',4,977241070,977241223,'200.241.159.178');
INSERT INTO t_usuario VALUES ('AEyqb7z0Ai6VfPF','well','jayce','W',5,977414746,989493319,'200.227.207.108');
INSERT INTO t_usuario VALUES ('AEIJRVKsa0NDdNm','gardel','1976','W',2,977431834,977431834,'200.17.116.207');
INSERT INTO t_usuario VALUES ('AE8ij9BzECyo53T','rsantana','1008','W',5,977538334,985468189,'200.191.20.121');
INSERT INTO t_usuario VALUES ('AEofLXoe42r9JPw','fabio','123654','W',4,977712519,977712804,'200.199.51.78');
INSERT INTO t_usuario VALUES ('AEN4s2Bj1RoQqg5','guinho','csa13cws','W',6,977835756,978546090,'200.199.67.109');
INSERT INTO t_usuario VALUES ('AE071JywCsERHFD','emerson','0900','W',4,977972338,977972422,'200.199.56.101');
INSERT INTO t_usuario VALUES ('AErhMuWTRX964UK','flamengo','0900','W',4,977972649,977974586,'200.199.56.101');
INSERT INTO t_usuario VALUES ('AEIm7XV1YWKIxDB','daphne','050380','W',4,977984551,978956145,'200.199.58.93');
INSERT INTO t_usuario VALUES ('AEfv1k2KIyl3DAi','fernanda','120378','W',6,977985666,978054920,'200.199.58.52');
INSERT INTO t_usuario VALUES ('AEeSfPpeWws9ZPV','maia','123456','W',5,977986164,978054873,'200.199.58.52');
INSERT INTO t_usuario VALUES ('AEaibDd30I0Ixvd','Fabiano','2377','W',4,978050084,981025953,'200.199.58.143');
INSERT INTO t_usuario VALUES ('AEyQmpJjTA5hJOw','dmm-rj','dmm1982','W',5,978148735,978198179,'172.133.183.163');
INSERT INTO t_usuario VALUES ('AEslRbmOoQvPhRq','rygar','ripster','W',6,978220463,978220667,'200.199.58.85');
INSERT INTO t_usuario VALUES ('AE7xHMSeANftH3g','carlos','28122','W',5,978370378,978371374,'200.199.58.142');
INSERT INTO t_usuario VALUES ('AEkun5VTY4SQFLB','engenho','sargo','W',4,978585590,978800613,'200.199.58.185');
INSERT INTO t_usuario VALUES ('AEj5s206jv28Y4a','NYRON','1212','W',6,978697941,978911782,'200.199.95.170');
INSERT INTO t_usuario VALUES ('AEOmRr97OMIyfdb','sauloram','sauloram','W',1,978700238,978700530,'200.241.149.226');
INSERT INTO t_usuario VALUES ('AECUFDZdyUmAN9n','an.melo','lipe2000','W',4,978705627,979587600,'200.199.58.47');
INSERT INTO t_usuario VALUES ('AEaGj1BzwuGMJ5b','rosane','85654','W',4,978872683,986060929,'200.241.162.3');
INSERT INTO t_usuario VALUES ('AEZfSsa0e3Zdbge','patricia','30343210','W',4,978957187,978958082,'200.199.58.93');
INSERT INTO t_usuario VALUES ('AEee3EnmdcFEDCB','marycris','181298','W',1,979359618,979359659,'205.188.197.47');
INSERT INTO t_usuario VALUES ('AEIqtN0lOheHaDY','maikow','secreto','W',4,979614173,986529943,'200.199.56.72');
INSERT INTO t_usuario VALUES ('AEOGtOTmP2YrEZA','emdjr','6430','W',6,979658454,986220005,'200.199.88.217');
INSERT INTO t_usuario VALUES ('AEd3Q3wZkFSdGhK','Rock','231161','W',7,980121043,980121692,'200.245.1.102');
INSERT INTO t_usuario VALUES ('AEKpkF0BcNKlWx0','LCandido','lcandido','W',6,980198852,980200144,'200.199.53.80');
INSERT INTO t_usuario VALUES ('AEo5gBO9uPMfsVg','celso','111111','W',6,980259912,980622966,'200.227.200.102');
INSERT INTO t_usuario VALUES ('AE68Hi1Cl4p8RAj','araquino','teia','W',5,980966521,980967026,'200.199.56.188');
INSERT INTO t_usuario VALUES ('AEHWnQra1K5GxgZ','Fox_Red','clerigo1','W',6,981155352,981155610,'200.199.53.212');
INSERT INTO t_usuario VALUES ('AE8g5ypg7Yri1SJ','vcarro','2865','W',6,981295877,981295877,'200.241.146.127');
INSERT INTO t_usuario VALUES ('AEPl2D6PyhCtc3U','jeferson','michelle','W',6,981482461,986829720,'200.241.146.221');
INSERT INTO t_usuario VALUES ('AEY4Tul4VE7YPyp','patrick','secreto','W',2,981510446,981514706,'200.199.56.33');
INSERT INTO t_usuario VALUES ('AEecnsNgJchSdG9','pitanga','0610','W',2,982899033,982993504,'200.199.56.11');
INSERT INTO t_usuario VALUES ('AELQ9u5Op8l4FoR','Misael','zxc123','W',6,982948280,982951475,'200.199.95.124');
INSERT INTO t_usuario VALUES ('AEv58tGhCd2vYrM','cris','2678','W',7,983593207,983593527,'200.199.56.62');
INSERT INTO t_usuario VALUES ('AE8m1ez2vYVgJca','Nyron','zxc123','W',6,983676092,983677617,'200.199.95.158');
INSERT INTO t_usuario VALUES ('AEHP8tOpSty9u5y','Pierre','mica','W',2,983812989,983813757,'200.199.53.17');
INSERT INTO t_usuario VALUES ('AEHlwJcx0lqLezU','jlls','jolusi','W',5,983837240,983837839,'200.133.126.55');
INSERT INTO t_usuario VALUES ('AE0H8BcVoZAj2Dm','delegado','1414','W',7,983843654,983918432,'200.241.161.99');
INSERT INTO t_usuario VALUES ('AENYpK5y9CPqTu5','msflores','495378','W',7,983849583,984518792,'200.227.207.24');
INSERT INTO t_usuario VALUES ('AEHgbgBO1urEZsN','esdras@','241278','W',1,983874881,983874881,'200.199.58.245');
INSERT INTO t_usuario VALUES ('AEYsvAVanQN8B4p','villagel','lagoapau','W',2,983878819,983878819,'200.199.112.136');
INSERT INTO t_usuario VALUES ('AEYsCXaDeHUnQjU','village','lagoapau','W',2,983888877,983889137,'200.199.112.77');
INSERT INTO t_usuario VALUES ('AEoZERkNoR4x0B4','village','lagoapau','W',2,983889419,983889419,'200.199.112.77');
INSERT INTO t_usuario VALUES ('AEyLW1mPiTYr2D6','LUCAS','BolBol','W',7,983892442,989948523,'200.241.164.67');
INSERT INTO t_usuario VALUES ('AEwd0dqLezE7kV8','dedeloli','jamo','W',5,983915444,983915444,'200.199.89.5');
INSERT INTO t_usuario VALUES ('AEHMXafIbEBOhSd','Tales','515712','W',1,983959002,983959866,'161.148.195.142');
INSERT INTO t_usuario VALUES ('AERITm5Gp0lWFgR','nalu','verdade','W',4,984024064,984025445,'200.199.51.60');
INSERT INTO t_usuario VALUES ('AERq5inkpujwJW1','moraes','mc1511','W',4,984349988,984350579,'200.199.53.116');
INSERT INTO t_usuario VALUES ('AE2winI3oJG9uPi','davi','93092071','W',2,984615343,984615343,'200.199.68.150');
INSERT INTO t_usuario VALUES ('AEwBEZcFgJOhKdy','C�lio','31077331','W',7,984879572,984882284,'200.241.159.156');
INSERT INTO t_usuario VALUES ('AELJLQVgB49uPaD','ericmart','12121033','W',7,984938639,986336712,'200.241.146.20');
INSERT INTO t_usuario VALUES ('AEQsLYjEZkFSdG1','Silvio','st3264','W',4,985047006,985048156,'200.199.67.101');
INSERT INTO t_usuario VALUES ('AEPzKXq1udiTmXq','Jath','12345a','W',6,985105480,985286138,'200.199.68.168');
INSERT INTO t_usuario VALUES ('AEdjKXaDeP2vQrU','skyze','691071','W',6,985298420,986246508,'200.254.143.8');
INSERT INTO t_usuario VALUES ('AEofqTmXqTYrUvY','diego_pa','100383','W',5,985304102,989105523,'200.199.56.247');
INSERT INTO t_usuario VALUES ('AE7dwJ4x0BG9u5y','jassen','secreto','W',1,985576391,989901255,'200.199.56.147');
INSERT INTO t_usuario VALUES ('AEoJM7kVoZcF8Bc','figura','figuras','W',1,985627747,985721778,'200.227.200.74');
INSERT INTO t_usuario VALUES ('AEkliTKBAziih87','graca_pa','julia','W',1,985786730,985786730,'200.241.162.3');
INSERT INTO t_usuario VALUES ('AEpgPyp0ZAFwf6P','COSTA','436634','W',7,985804971,985804971,'200.241.162.3');
INSERT INTO t_usuario VALUES ('AEAqfQzypgJIzqh','COSTA','436634','W',7,985805473,985805473,'200.241.162.3');
INSERT INTO t_usuario VALUES ('AE2yfYPypgJAra1','FERNANDO','124990','W',7,985899750,985899750,'200.241.146.14');
INSERT INTO t_usuario VALUES ('AEXGRs3w7I3EfQr','mqao','marcelo','W',1,985968954,985968990,'200.241.150.150');
INSERT INTO t_usuario VALUES ('AEnlotOhC51mPiL','sacajo','54321','W',1,985983049,985983049,'200.241.145.129');
INSERT INTO t_usuario VALUES ('AEJI1mHiLmz2vYj','JC Jr.','0123','W',7,986186121,986188360,'200.199.53.87');
INSERT INTO t_usuario VALUES ('AEK69C5qTmzUnYj','julywel','julywel','W',7,986272745,986273838,'200.199.51.78');
INSERT INTO t_usuario VALUES ('AEiJEJW9uHwZcpC','Cicero','1472','W',6,987080362,987080975,'200.199.56.189');
INSERT INTO t_usuario VALUES ('AEvyBG1uH2J4pKX','tucabian','123anton','W',2,987132733,989384712,'200.211.160.40');
INSERT INTO t_usuario VALUES ('AEmS3wR4F8dG9Kd','jotape2k','jplb77','W',3,987176118,990368398,'200.191.62.224');
INSERT INTO t_usuario VALUES ('AELO7kN8BchSlWp','antero','moveis','W',2,987276773,988759660,'200.199.52.100');
INSERT INTO t_usuario VALUES ('AESHCP2nIb8tWpK','evll','300664','W',7,987288835,987289524,'200.241.146.147');
INSERT INTO t_usuario VALUES ('AEr830lG9CzUnQj','Tayrone','tayr9409','W',6,987304057,987305087,'200.191.61.40');
INSERT INTO t_usuario VALUES ('AE27afAVoBGTezU','macealpe','traffic3','W',7,987307948,987309004,'152.163.204.78');
INSERT INTO t_usuario VALUES ('AEbFI2nIbwtOhC5','odair','blaclass','W',6,987438696,987438696,'200.241.146.207');
INSERT INTO t_usuario VALUES ('AECPKXiTmXaD6Hi','duda30','gostoso','W',5,987523895,988860308,'200.191.20.55');
INSERT INTO t_usuario VALUES ('AE8AL6jwZkhC5qT','sergio','0007','W',6,987614889,987615993,'200.211.135.38');
INSERT INTO t_usuario VALUES ('AEiEjwRkF8dy1uX','1 a 4','1234','W',7,987621942,987622035,'200.241.146.234');
INSERT INTO t_usuario VALUES ('AEV9cNgRsbo7Ira','olismith','catioly','W',7,987690715,987690715,'200.241.148.66');
INSERT INTO t_usuario VALUES ('AEf61mPqTmz2nYz','catioly','oliver','W',7,987690950,987692308,'200.241.148.66');
INSERT INTO t_usuario VALUES ('AEMzuzM7sNK5qDQ','netinho','netinho','W',2,987768449,987768519,'200.241.146.218');
INSERT INTO t_usuario VALUES ('AEeVezMfIbgJ4F0','guerra','1234','W',6,987958594,989761327,'200.199.58.52');
INSERT INTO t_usuario VALUES ('AEQNYbwZA3gB4F8','omega','0000','W',6,987961971,988624765,'200.199.58.218');
INSERT INTO t_usuario VALUES ('AE4GAVgB4puPiLe','cdaovivo','15951','W',3,988085292,988085723,'200.199.92.48');
INSERT INTO t_usuario VALUES ('AEiMWx0C5GTuXy9','mouzart','mouzartp','W',5,988263837,988828300,'200.199.53.68');
INSERT INTO t_usuario VALUES ('AEloHMZsNg5yTmP','clifton','623300','W',7,988326839,988929363,'200.199.51.37');
INSERT INTO t_usuario VALUES ('AEFYTeHaDejMfIb','ANTERO','TELE','W',1,988343474,988344282,'200.199.58.175');
INSERT INTO t_usuario VALUES ('AE8l8tWp0BOpSt4','ByRocha','delphi01','W',5,988343685,989721949,'200.199.51.83');
INSERT INTO t_usuario VALUES ('AEnmFSlWp05G9Kl','HUMBERTO','TOTO','W',2,988346205,988346614,'200.199.58.175');
INSERT INTO t_usuario VALUES ('AElN6zaLmXUv6Hi','DAVID','DAVID','W',2,988347280,988347662,'200.199.58.175');
INSERT INTO t_usuario VALUES ('AEQ2tWpStchSt4N','seucd','boninho','W',5,988432905,988433291,'200.199.95.157');
INSERT INTO t_usuario VALUES ('AE5MG1u5yamQr2D','chico','feliz','W',7,988600454,988600454,'200.199.53.161');
INSERT INTO t_usuario VALUES ('AEDQLeHi1mzaLu5','guega','marcelo','W',5,988764720,988765118,'200.199.53.67');
INSERT INTO t_usuario VALUES ('AEAVIbEn6HMv6Ha','sydney','maga10','W',7,988830192,988830662,'200.199.51.20');
INSERT INTO t_usuario VALUES ('AEvY9KdWxgl4FgR','Alysson','8102','W',5,988863952,988863952,'200.199.56.252');
INSERT INTO t_usuario VALUES ('AERA16rUnIN8B4x','massagem','153026','W',7,988907110,988907240,'205.188.193.172');
INSERT INTO t_usuario VALUES ('AErwHMRkN8XiD6z','jahuboy','86113801','W',7,988980613,988981020,'200.241.146.213');
INSERT INTO t_usuario VALUES ('AEwBMnIjUvIjUv6','Fred','fred','W',5,989073051,989073453,'200.241.146.213');
INSERT INTO t_usuario VALUES ('AEaS3gtWhCHUnIj','SAULO','7591','W',6,989091627,989092129,'200.199.53.197');
INSERT INTO t_usuario VALUES ('AExvG9mPaLI3wZs','ROMAQ','7591','W',6,989092373,989891595,'200.199.53.228');
INSERT INTO t_usuario VALUES ('AEM7U7cpKtivI3o','ADRIANO','0852','W',7,989275345,989275345,'200.199.68.18');
INSERT INTO t_usuario VALUES ('AEGDWhKlWFKt4Fg','Adolfo','120999','W',2,989304102,989523598,'200.241.173.10');
INSERT INTO t_usuario VALUES ('AEwm1mHaLerUnYr','autran','230175','W',2,989375270,989375400,'200.217.143.155');
INSERT INTO t_usuario VALUES ('AE4yBWpKlGTmPqL','Oliveira','loptus','W',2,989418457,989461645,'200.241.151.147');
INSERT INTO t_usuario VALUES ('AExEXqD6z2ZkVoR','edson','2001','W',7,989518757,989519102,'200.191.62.254');
INSERT INTO t_usuario VALUES ('AEIc7sN8B49C5y1','humberto','164589','W',7,989599612,989599696,'200.199.95.127');
INSERT INTO t_usuario VALUES ('AEIDWp0Jk3oZAj2','dsimons','102030','W',6,989738724,989804839,'208.50.80.71');
INSERT INTO t_usuario VALUES ('AEetqfc9ejRWT6b','juruna','1234','W',1,989897983,989898728,'200.199.58.134');
INSERT INTO t_usuario VALUES ('AE80FujwtGfjoBG','romgyver','cla21','W',5,989993075,990000713,'200.191.20.17');
INSERT INTO t_usuario VALUES ('AEKbY305an49ejo','Cristina','ana1611','W',5,990033398,990033493,'200.199.56.27');
INSERT INTO t_usuario VALUES ('AE7RURN0dqfAVgl','JORGE','badwolf','W',2,990424563,990426241,'200.225.155.148');
INSERT INTO t_usuario VALUES ('AEfREdqerwdqDIV','predacon','dogmeat','W',3,990441699,990442138,'200.199.58.205');
INSERT INTO t_usuario VALUES ('AEoB8dinA3CHMRO','Kampai','kampai','W',2,990500848,990500848,'200.226.143.50');
INSERT INTO t_usuario VALUES ('AEnh4hCP2ncpCXi','fguedes','1812','W',6,990542075,990542887,'200.199.53.190');

#
# Table structure for table 't_veiculo'
#
CREATE TABLE t_veiculo (
  cod_anuncio varchar(15) binary DEFAULT '0' NOT NULL,
  cod_marca_veiculo tinyint(2) DEFAULT '0' NOT NULL,
  PRIMARY KEY (cod_anuncio)
);

#
# Dumping data for table 't_veiculo'
#

INSERT INTO t_veiculo VALUES ('AOemqvzDQUY26cg',3);
INSERT INTO t_veiculo VALUES ('AO5vinAV8tsxSlG',2);
INSERT INTO t_veiculo VALUES ('AOeX05aDYjgBW9u',3);
INSERT INTO t_veiculo VALUES ('AODnqDYjMfI3wZk',2);
INSERT INTO t_veiculo VALUES ('AO3Bq1MfYzMn6Hq',3);
INSERT INTO t_veiculo VALUES ('AODoHaDePivYzaL',1);
INSERT INTO t_veiculo VALUES ('AOKUdiLeHi7A3w7',4);
INSERT INTO t_veiculo VALUES ('AOHfqDYjMnkvkF0',4);
INSERT INTO t_veiculo VALUES ('AOh2P2nQbEBWhK5',2);
INSERT INTO t_veiculo VALUES ('AOrlEJ4e3wtWhK5',3);
INSERT INTO t_veiculo VALUES ('AOa9AF0tWpuXqTm',2);
INSERT INTO t_veiculo VALUES ('AOLHtxsMXxfPw6y',4);
INSERT INTO t_veiculo VALUES ('AOf5M7Ij2DQraTu',0);
INSERT INTO t_veiculo VALUES ('AOtEHM7A3wJkNgR',3);
INSERT INTO t_veiculo VALUES ('AOjtwB4hC5avYrU',4);
INSERT INTO t_veiculo VALUES ('AO0hsN8tWhuPiL6',4);
INSERT INTO t_veiculo VALUES ('AO9P8tOhK5avQr2',4);

