# MySQL dump 8.13
#
# Host: localhost    Database: classANews
#--------------------------------------------------------
# Server version	3.23.36-log

#
# Table structure for table 'Newsletter_Members'
#

CREATE TABLE Newsletter_Members (
  NewsletterMemberID bigint(20) NOT NULL auto_increment,
  email varchar(255) binary NOT NULL default '',
  receivedPublicity char(1) NOT NULL default 'N',
  PRIMARY KEY  (NewsletterMemberID)
) TYPE=MyISAM;

#
# Dumping data for table 'Newsletter_Members'
#

INSERT INTO Newsletter_Members VALUES (1,'marcellojunior@hotmail.com','N');
INSERT INTO Newsletter_Members VALUES (2,'leandro@cefet-al.br','N');
INSERT INTO Newsletter_Members VALUES (3,'henriquecarmellino@globo.com','N');
INSERT INTO Newsletter_Members VALUES (4,'plinio7@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (5,'alzira_carmellino@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (6,'broad@dirp.com','N');
INSERT INTO Newsletter_Members VALUES (7,'business@classi-a.com.br','N');
INSERT INTO Newsletter_Members VALUES (8,'alguem@algumlugar.br','N');
INSERT INTO Newsletter_Members VALUES (9,'fernandodamiao@uol.com.br','N');
INSERT INTO Newsletter_Members VALUES (10,'bob-16@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (11,'jfabio@mailbr.com.br','N');
INSERT INTO Newsletter_Members VALUES (12,'tiago27cm@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (13,'brunovida@globo.com','N');
INSERT INTO Newsletter_Members VALUES (14,'claudiobrasil@mailbr.com.br','N');
INSERT INTO Newsletter_Members VALUES (15,'wr.wilson@ig.com.br','N');
INSERT INTO Newsletter_Members VALUES (16,'marcello@dialnet.com.br','N');
INSERT INTO Newsletter_Members VALUES (17,'rodrigo@canalmaceio.com.br','N');
INSERT INTO Newsletter_Members VALUES (18,'elsilva@matrix.com.br','N');
INSERT INTO Newsletter_Members VALUES (19,'igor_vasconcelos@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (20,'andrearpeixoto@zipmail.com.br','N');
INSERT INTO Newsletter_Members VALUES (21,'glauceb@mattosfilho.com.br','N');
INSERT INTO Newsletter_Members VALUES (22,'rpeixoto@ofm.com.br','N');
INSERT INTO Newsletter_Members VALUES (23,'iamprotheus@zipmail.com.br','N');
INSERT INTO Newsletter_Members VALUES (24,'davipaulobb@ig.com.br','N');
INSERT INTO Newsletter_Members VALUES (25,'kjcosta@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (26,'kcoutinho@zipmail.com.br','N');
INSERT INTO Newsletter_Members VALUES (27,'cankum@mailbr.com.br','N');
INSERT INTO Newsletter_Members VALUES (28,'Domarques@ig.com.br','N');
INSERT INTO Newsletter_Members VALUES (29,'marcschubert@hotmail.com','N');
INSERT INTO Newsletter_Members VALUES (30,'anafelino@hotmail.com','N');
INSERT INTO Newsletter_Members VALUES (31,'meloap@zipmail.com.br','N');
INSERT INTO Newsletter_Members VALUES (32,'canso@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (33,'arcocontabil@ig.com.br','N');
INSERT INTO Newsletter_Members VALUES (34,'admon@ig.com.br','N');
INSERT INTO Newsletter_Members VALUES (35,'albertomcz@hotmail.com','N');
INSERT INTO Newsletter_Members VALUES (36,'pjrilumi@ig.com.br','N');
INSERT INTO Newsletter_Members VALUES (37,'silvioandre@email.com.br','N');
INSERT INTO Newsletter_Members VALUES (38,'mensfo@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (39,'silvioth@ositemail.com.br','N');
INSERT INTO Newsletter_Members VALUES (40,'almodel@zipmail.com.br','N');
INSERT INTO Newsletter_Members VALUES (41,'saulinh0@globo.com','N');
INSERT INTO Newsletter_Members VALUES (42,'anderalves@zipmail.com.br','N');
INSERT INTO Newsletter_Members VALUES (43,'contato@teixnet.com','N');
INSERT INTO Newsletter_Members VALUES (44,'wesl@uol.com.br','N');
INSERT INTO Newsletter_Members VALUES (45,'mariaeunice@uol.com.br','N');
INSERT INTO Newsletter_Members VALUES (46,'rdsantana@zipmail.com.br','N');
INSERT INTO Newsletter_Members VALUES (47,'fabiorpc@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (48,'wsimoes@matrix.com.br','N');
INSERT INTO Newsletter_Members VALUES (49,'emersonlucas@ig.com.br','N');
INSERT INTO Newsletter_Members VALUES (50,'fla-maceio@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (51,'dmvargas@ig.com.br','N');
INSERT INTO Newsletter_Members VALUES (52,'nandaramires@zipmail.com.br','N');
INSERT INTO Newsletter_Members VALUES (53,'costamaia@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (54,'fabyanoamorim@ig.com.br','N');
INSERT INTO Newsletter_Members VALUES (55,'diogo.maia@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (56,'jdos@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (57,'anderalves@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (58,'engenho.arquitetura@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (59,'nyron@ig.com.br','N');
INSERT INTO Newsletter_Members VALUES (60,'slrs@tci.ufal.br','N');
INSERT INTO Newsletter_Members VALUES (61,'an.melo@ig.com.br','N');
INSERT INTO Newsletter_Members VALUES (62,'arthur_paredes@yahoo.com.br','N');
INSERT INTO Newsletter_Members VALUES (63,'dvc5@zipmail.com.br','N');
INSERT INTO Newsletter_Members VALUES (64,'marycris2000@aol.com','N');
INSERT INTO Newsletter_Members VALUES (65,'maikow@ig.com.br','N');
INSERT INTO Newsletter_Members VALUES (66,'emdjr@zipmail.com.br','N');
INSERT INTO Newsletter_Members VALUES (67,'mauricio.fachini@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (68,'lcvadv@zipmail.com.br','N');
INSERT INTO Newsletter_Members VALUES (69,'som5@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (70,'araquino@globo.com','N');
INSERT INTO Newsletter_Members VALUES (71,'xandemaceio@hotmail.com.br','N');
INSERT INTO Newsletter_Members VALUES (72,'maurolemos@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (73,'jefersonmelo@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (74,'brad_jones@ig.com.br','N');
INSERT INTO Newsletter_Members VALUES (75,'jpitanga@ig.com.br','N');
INSERT INTO Newsletter_Members VALUES (76,'pisosal@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (77,'cristiano@maianobre.com.br','N');
INSERT INTO Newsletter_Members VALUES (78,'nyron@vircom.com.br','N');
INSERT INTO Newsletter_Members VALUES (79,'Janp@ig.com.br','N');
INSERT INTO Newsletter_Members VALUES (80,'jorgells@uol.com.br','N');
INSERT INTO Newsletter_Members VALUES (81,'thiagodavino@zipmail.com.br','N');
INSERT INTO Newsletter_Members VALUES (82,'mateus@hostsys.com.br','N');
INSERT INTO Newsletter_Members VALUES (83,'aninha@mariodias.com.br','N');
INSERT INTO Newsletter_Members VALUES (84,'esdrasonline@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (85,'villagelagoa@ieg.com.br','N');
INSERT INTO Newsletter_Members VALUES (86,'villagelagoa@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (87,'lucasparanhos@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (88,'dedeloliveira@hotmail.com','N');
INSERT INTO Newsletter_Members VALUES (89,'talescardoso@zipmail.com.br','N');
INSERT INTO Newsletter_Members VALUES (90,'20.buscar@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (91,'momentosdocoracao@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (92,'celio_ricardo@zipmail.com.br','N');
INSERT INTO Newsletter_Members VALUES (93,'allyson@fejal.com.br','N');
INSERT INTO Newsletter_Members VALUES (94,'sitecafe@sitecafe.com.br','N');
INSERT INTO Newsletter_Members VALUES (95,'jath@fppmedia.com','N');
INSERT INTO Newsletter_Members VALUES (96,'robertoskyze@hotmail.com','N');
INSERT INTO Newsletter_Members VALUES (97,'diego_paredes@yahoo.com','N');
INSERT INTO Newsletter_Members VALUES (98,'pscborges@uol.com.br','N');
INSERT INTO Newsletter_Members VALUES (99,'viagem@pele.net','N');
INSERT INTO Newsletter_Members VALUES (100,'graca_paredes@yahoo.com.br','N');
INSERT INTO Newsletter_Members VALUES (101,'marcozzi@sunnet.com.br','N');
INSERT INTO Newsletter_Members VALUES (102,'pinto.filho@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (103,'mqao@hotmail.com','N');
INSERT INTO Newsletter_Members VALUES (104,'sacajo@zipmail.com.br','N');
INSERT INTO Newsletter_Members VALUES (105,'jcfsj@zipmail.com.br','N');
INSERT INTO Newsletter_Members VALUES (106,'julywel@bol.com.br','N');
INSERT INTO Newsletter_Members VALUES (107,'joaquim@classi-a.com.br','Y');
INSERT INTO Newsletter_Members VALUES (108,'gomes@classia.com.br','Y');

#
# Table structure for table 'Newsletters'
#

CREATE TABLE Newsletters (
  NewsletterID int(11) NOT NULL auto_increment,
  Name varchar(80) default NULL,
  Description text,
  Vol int(11) default '1',
  Num int(11) default '1',
  PRIMARY KEY  (NewsletterID)
) TYPE=MyISAM;

#
# Dumping data for table 'Newsletters'
#


