
/* -------------------------------------------------------- 
  phpPgAdmin 2.4-1 DB Dump
  http://sourceforge.net/projects/phppgadmin/
  Host: localhost:5432
  Database  : "bibliweb"
  2002-05-04 17:05:09
-------------------------------------------------------- */ 

/* -------------------------------------------------------- 
  Sequences 
-------------------------------------------------------- */ 
DROP SEQUENCE "ids";
CREATE SEQUENCE "ids" start 44 increment 1 maxvalue 2147483647 minvalue 1 cache 1; 
SELECT NEXTVAL('ids'); 

/* -------------------------------------------------------- 
  Table structure for table "academic_course" 
-------------------------------------------------------- */
DROP TABLE "academic_course";
CREATE TABLE "academic_course" (
   "academic_course_id" int2 NOT NULL,
   "code" varchar(3) NOT NULL,
   "description" varchar(255) NOT NULL,
   CONSTRAINT "academic_course_pkey" PRIMARY KEY ("academic_course_id")
);
CREATE  UNIQUE INDEX "code_academic_course_ukey" ON "academic_course" ("code");


/* -------------------------------------------------------- 
  Dumping data for table "academic_course" 
-------------------------------------------------------- */ 
INSERT INTO "academic_course" ("academic_course_id", "code", "description") VALUES(32, 'G55', 'Ciência da Computação');
INSERT INTO "academic_course" ("academic_course_id", "code", "description") VALUES(33, 'G25', 'Medicina');

/* -------------------------------------------------------- 
  Table structure for table "academic_user" 
-------------------------------------------------------- */
DROP TABLE "academic_user";
CREATE TABLE "academic_user" (
   "user_id" varchar(25) NOT NULL,
   "group_id" int2 NOT NULL,
   "status_id" int2 NOT NULL,
   "cpf" varchar(11) NOT NULL,
   "name" varchar(255) NOT NULL,
   "home_address" varchar(255) NOT NULL,
   "work_address" varchar(255),
   "home_phone" varchar(12),
   "work_phone" varchar(12),
   "cell_phone" varchar(12),
   "email" varchar(255),
   "photo_extension" varchar(3),
   "username" varchar(15),
   "password" varchar(15),
   "last_access_IP" varchar(15),
   "last_access_Date" varchar(25),
   "registration" varchar(13) NOT NULL,
   "academic_course_id" int2 NOT NULL
);
CREATE  UNIQUE INDEX "academic_user_registration_key" ON "academic_user" ("registration");


/* -------------------------------------------------------- 
  Dumping data for table "academic_user" 
-------------------------------------------------------- */ 
INSERT INTO "academic_user" ("user_id", "group_id", "status_id", "cpf", "name", "home_address", "work_address", "home_phone", "work_phone", "cell_phone", "email", "photo_extension", "username", "password", "last_access_IP", "last_access_Date", "registration", "academic_course_id") VALUES('1019075364600', 29, 5, '02728379467', 'Edle Marcio', 'Rua Gustavo Paiva, 2220, Apto 102/02 Mangabeiras', '', '82-327-0516', '', '', '', '', '', '', '', '', '1999G55D014V6', 32);
INSERT INTO "academic_user" ("user_id", "group_id", "status_id", "cpf", "name", "home_address", "work_address", "home_phone", "work_phone", "cell_phone", "email", "photo_extension", "username", "password", "last_access_IP", "last_access_Date", "registration", "academic_course_id") VALUES('1019095261310', 36, 4, '03059038457', 'Marcello Alves de Sales Junior', 'Rua Gonçalves Dias, 58 Farol', 'Departamento de Tecnologia da Informação, UFAL', '82-326-2884', '82-214-1402', '82-9997-1415', 'marcellojunior@hotmail.com', 'jpg', '', '', '', '', '1998G55D001T9', 32);
INSERT INTO "academic_user" ("user_id", "group_id", "status_id", "cpf", "name", "home_address", "work_address", "home_phone", "work_phone", "cell_phone", "email", "photo_extension", "username", "password", "last_access_IP", "last_access_Date", "registration", "academic_course_id") VALUES('1019667750120', 36, 5, '00987705431', 'André Alécio', 'Rua Fulano de tal', 'UFAL - Proex', '', '', '82-9306-2160', 'andre_alecio@hotmail.com', '', '', '', '', '', '1999G55D005V6', 32);
INSERT INTO "academic_user" ("user_id", "group_id", "status_id", "cpf", "name", "home_address", "work_address", "home_phone", "work_phone", "cell_phone", "email", "photo_extension", "username", "password", "last_access_IP", "last_access_Date", "registration", "academic_course_id") VALUES('1019412970200', 36, 4, '02957198428', 'José Ricardo', 'Rua Victoria 19', 'NPD reitoria UFAL', '82-354-9466', '82-214-1075', '', 'jricardo_so@hotmail.com', 'jpg', '', '', '', '', '1999G55D028V2', 32);
INSERT INTO "academic_user" ("user_id", "group_id", "status_id", "cpf", "name", "home_address", "work_address", "home_phone", "work_phone", "cell_phone", "email", "photo_extension", "username", "password", "last_access_IP", "last_access_Date", "registration", "academic_course_id") VALUES('1019671559980', 37, 4, '00761107452', 'Arturo Hernandes Dominguez', 'Rua Fulano lalalal ', 'NPD reitoria UFAL', '9895-8545', '214-8954', '9978-5654', 'arturo@npd.ufal.br', 'jpg', '', '', '', '', '98956565457', 32);

/* -------------------------------------------------------- 
  Table structure for table "book" 
-------------------------------------------------------- */
DROP TABLE "book";
CREATE TABLE "book" (
   "exemplar_id" varchar(25) NOT NULL,
   "physical_place_id" int2 NOT NULL,
   "category_id" int2 NOT NULL,
   "language_id" int2 NOT NULL,
   "status_id" int2 NOT NULL,
   "title" varchar(255) NOT NULL,
   "keywords" varchar(255) NOT NULL,
   "acquisition_date" date NOT NULL,
   "total_quantity" int2 DEFAULT 1 NOT NULL,
   "available_quantity" int2 DEFAULT 1 NOT NULL,
   "isbn" varchar NOT NULL,
   "volume" varchar NOT NULL,
   "authors" varchar NOT NULL,
   "edition" varchar(255) NOT NULL,
   "exemplar_type_id" int2
);


/* -------------------------------------------------------- 
  Dumping data for table "book" 
-------------------------------------------------------- */ 
INSERT INTO "book" ("exemplar_id", "physical_place_id", "category_id", "language_id", "status_id", "title", "keywords", "acquisition_date", "total_quantity", "available_quantity", "isbn", "volume", "authors", "edition", "exemplar_type_id") VALUES('1019621139580', 14, 43, 28, 1, 'Fundamentals of Database Systems', 'Banco de dados|Datamining|Diagrama ER|XML', '2001-04-12', 1, 1, '8594233322', 'Único', 'Elmasri & Navathe', '3ª Edition', 39);
INSERT INTO "book" ("exemplar_id", "physical_place_id", "category_id", "language_id", "status_id", "title", "keywords", "acquisition_date", "total_quantity", "available_quantity", "isbn", "volume", "authors", "edition", "exemplar_type_id") VALUES('1019621993230', 15, 43, 1, 1, 'Symbolic Logic and Machanical Theorem Proving', 'Lógica,Taltologia,Premissa', '0015-11-21', 3, 3, '0121703509', '1', 'Chin-Liang Chang & Richard Char-Tung Lee', '1º', 39);
INSERT INTO "book" ("exemplar_id", "physical_place_id", "category_id", "language_id", "status_id", "title", "keywords", "acquisition_date", "total_quantity", "available_quantity", "isbn", "volume", "authors", "edition", "exemplar_type_id") VALUES('1019623155730', 14, 43, 1, 1, 'Modelos de documentos', 'OOP', '2003-11-25', 5, 5, '8570018416', '4', 'Rabaum', '3º', 39);
INSERT INTO "book" ("exemplar_id", "physical_place_id", "category_id", "language_id", "status_id", "title", "keywords", "acquisition_date", "total_quantity", "available_quantity", "isbn", "volume", "authors", "edition", "exemplar_type_id") VALUES('1019668106860', 14, 43, 1, 1, 'Date', 'SQL,XML,QBE', '2002-06-24', 4, 4, '0541124873', 'Único', 'C. J. Date', '1º', 39);
INSERT INTO "book" ("exemplar_id", "physical_place_id", "category_id", "language_id", "status_id", "title", "keywords", "acquisition_date", "total_quantity", "available_quantity", "isbn", "volume", "authors", "edition", "exemplar_type_id") VALUES('1019671840590', 14, 42, 28, 1, 'IA - Jogos', 'Euristica, A*', '2000-12-12', 15, 15, '1545451512', 'Único', 'Eu Mesmo', '1º', 39);

/* -------------------------------------------------------- 
  Table structure for table "category" 
-------------------------------------------------------- */
DROP TABLE "category";
CREATE TABLE "category" (
   "category_id" int2 NOT NULL,
   "academic_course_id" int2 NOT NULL,
   "description" varchar(255) NOT NULL,
   CONSTRAINT "category_pkey" PRIMARY KEY ("category_id")
);


/* -------------------------------------------------------- 
  Dumping data for table "category" 
-------------------------------------------------------- */ 
INSERT INTO "category" ("category_id", "academic_course_id", "description") VALUES(42, 32, 'Inteligência Artificial');
INSERT INTO "category" ("category_id", "academic_course_id", "description") VALUES(43, 32, 'Banco de Dados');

/* -------------------------------------------------------- 
  Table structure for table "dissertation" 
-------------------------------------------------------- */
DROP TABLE "dissertation";
CREATE TABLE "dissertation" (
   "exemplar_id" varchar(25) NOT NULL,
   "physical_place_id" int2 NOT NULL,
   "category_id" int2 NOT NULL,
   "language_id" int2 NOT NULL,
   "status_id" int2 NOT NULL,
   "title" varchar(255) NOT NULL,
   "keywords" varchar(255) NOT NULL,
   "acquisition_date" date NOT NULL,
   "total_quantity" int2 DEFAULT 1 NOT NULL,
   "available_quantity" int2 DEFAULT 1 NOT NULL,
   "kind" varchar NOT NULL,
   "author" varchar NOT NULL,
   "publish_date" date NOT NULL,
   "abstract" varchar(255) NOT NULL,
   "exemplar_type_id" int2
);


/* -------------------------------------------------------- 
  Dumping data for table "dissertation" 
-------------------------------------------------------- */ 

/* -------------------------------------------------------- 
  Table structure for table "exemplar" 
-------------------------------------------------------- */
DROP TABLE "exemplar";
CREATE TABLE "exemplar" (
   "exemplar_id" varchar(25) NOT NULL,
   "physical_place_id" int2 NOT NULL,
   "category_id" int2 NOT NULL,
   "language_id" int2 NOT NULL,
   "status_id" int2 NOT NULL,
   "title" varchar(255) NOT NULL,
   "keywords" varchar(255) NOT NULL,
   "acquisition_date" date NOT NULL,
   "total_quantity" int2 DEFAULT 1 NOT NULL,
   "available_quantity" int2 DEFAULT 1 NOT NULL,
   "exemplar_type_id" int2,
   CONSTRAINT "exemplar_pkey" PRIMARY KEY ("exemplar_id")
);


/* -------------------------------------------------------- 
  Dumping data for table "exemplar" 
-------------------------------------------------------- */ 
INSERT INTO "exemplar" ("exemplar_id", "physical_place_id", "category_id", "language_id", "status_id", "title", "keywords", "acquisition_date", "total_quantity", "available_quantity", "exemplar_type_id") VALUES('1019621139580', 14, 43, 28, 1, 'Fundamentals of Database Systems', 'Banco de dados|Datamining|Diagrama ER|XML', '2001-04-12', 1, 1, 39);
INSERT INTO "exemplar" ("exemplar_id", "physical_place_id", "category_id", "language_id", "status_id", "title", "keywords", "acquisition_date", "total_quantity", "available_quantity", "exemplar_type_id") VALUES('1019621993230', 15, 43, 1, 1, 'Symbolic Logic and Machanical Theorem Proving', 'Lógica,Taltologia,Premissa', '0015-11-21', 3, 3, 39);
INSERT INTO "exemplar" ("exemplar_id", "physical_place_id", "category_id", "language_id", "status_id", "title", "keywords", "acquisition_date", "total_quantity", "available_quantity", "exemplar_type_id") VALUES('1019623155730', 14, 43, 1, 1, 'Modelos de documentos', 'OOP', '2003-11-25', 5, 5, 39);
INSERT INTO "exemplar" ("exemplar_id", "physical_place_id", "category_id", "language_id", "status_id", "title", "keywords", "acquisition_date", "total_quantity", "available_quantity", "exemplar_type_id") VALUES('1019668106860', 14, 43, 1, 1, 'Date', 'SQL,XML,QBE', '2002-06-24', 4, 4, 39);
INSERT INTO "exemplar" ("exemplar_id", "physical_place_id", "category_id", "language_id", "status_id", "title", "keywords", "acquisition_date", "total_quantity", "available_quantity", "exemplar_type_id") VALUES('1019671840590', 14, 42, 28, 1, 'IA - Jogos', 'Euristica, A*', '2000-12-12', 15, 15, 39);

/* -------------------------------------------------------- 
  Table structure for table "exemplar_type" 
-------------------------------------------------------- */
DROP TABLE "exemplar_type";
CREATE TABLE "exemplar_type" (
   "exemplar_type_id" int2 NOT NULL,
   "code" varchar(2) NOT NULL,
   "description" varchar(255) NOT NULL,
   CONSTRAINT "exemplar_type_pkey" PRIMARY KEY ("exemplar_type_id")
);
CREATE  UNIQUE INDEX "code_exemplar_type_ukey" ON "exemplar_type" ("code");
CREATE  UNIQUE INDEX "description_exemplar_type_ukey" ON "exemplar_type" ("description");
CREATE  UNIQUE INDEX "exemplar_type_exemplar_type_key" ON "exemplar_type" ("code", "exemplar_type_id");


/* -------------------------------------------------------- 
  Dumping data for table "exemplar_type" 
-------------------------------------------------------- */ 
INSERT INTO "exemplar_type" ("exemplar_type_id", "code", "description") VALUES(39, 'B', 'Livro');
INSERT INTO "exemplar_type" ("exemplar_type_id", "code", "description") VALUES(42, 'D', 'Dissertação');

/* -------------------------------------------------------- 
  Table structure for table "grouping" 
-------------------------------------------------------- */
DROP TABLE "grouping";
CREATE TABLE "grouping" (
   "group_id" int2 NOT NULL,
   "code" varchar(2) NOT NULL,
   "description" varchar(255) NOT NULL,
   CONSTRAINT "group_pkey" PRIMARY KEY ("group_id")
);
CREATE  UNIQUE INDEX "code_grouping_ukey" ON "grouping" ("code");
CREATE  UNIQUE INDEX "description_grouping_ukey" ON "grouping" ("description");


/* -------------------------------------------------------- 
  Dumping data for table "grouping" 
-------------------------------------------------------- */ 
INSERT INTO "grouping" ("group_id", "code", "description") VALUES(36, 'S', 'Alunos');
INSERT INTO "grouping" ("group_id", "code", "description") VALUES(37, 'P', 'Professores');
INSERT INTO "grouping" ("group_id", "code", "description") VALUES(38, 'C', 'Balconistas');

/* -------------------------------------------------------- 
  Table structure for table "language" 
-------------------------------------------------------- */
DROP TABLE "language";
CREATE TABLE "language" (
   "language_id" int2 NOT NULL,
   "description" varchar(100) NOT NULL,
   CONSTRAINT "language_pkey" PRIMARY KEY ("language_id")
);


/* -------------------------------------------------------- 
  Dumping data for table "language" 
-------------------------------------------------------- */ 
INSERT INTO "language" ("language_id", "description") VALUES(1, 'Inglês');
INSERT INTO "language" ("language_id", "description") VALUES(28, 'Português');

/* -------------------------------------------------------- 
  Table structure for table "physical_place" 
-------------------------------------------------------- */
DROP TABLE "physical_place";
CREATE TABLE "physical_place" (
   "physical_place_id" int2 NOT NULL,
   "description" varchar(255) NOT NULL,
   CONSTRAINT "physical_place_pkey" PRIMARY KEY ("physical_place_id")
);


/* -------------------------------------------------------- 
  Dumping data for table "physical_place" 
-------------------------------------------------------- */ 
INSERT INTO "physical_place" ("physical_place_id", "description") VALUES(14, 'Estante L/A3');
INSERT INTO "physical_place" ("physical_place_id", "description") VALUES(15, 'Estante L/B4');

/* -------------------------------------------------------- 
  Table structure for table "right" 
-------------------------------------------------------- */
DROP TABLE "right";
CREATE TABLE "right" (
   "right_id" char(2) NOT NULL,
   "group_id" char(2) NOT NULL,
   "operation_id" char(2) NOT NULL,
   CONSTRAINT "right_pkey" PRIMARY KEY ("right_id")
);


/* -------------------------------------------------------- 
  Dumping data for table "right" 
-------------------------------------------------------- */ 

/* -------------------------------------------------------- 
  Table structure for table "status" 
-------------------------------------------------------- */
DROP TABLE "status";
CREATE TABLE "status" (
   "status_id" int2 NOT NULL,
   "description" varchar(255) NOT NULL,
   "group" varchar(255) NOT NULL,
   CONSTRAINT "status_pkey" PRIMARY KEY ("status_id")
);
CREATE  UNIQUE INDEX "status_status_id_key" ON "status" ("description", "status_id");


/* -------------------------------------------------------- 
  Dumping data for table "status" 
-------------------------------------------------------- */ 
INSERT INTO "status" ("status_id", "description", "group") VALUES(5, 'Usuário inatívo', 'Usuário');
INSERT INTO "status" ("status_id", "description", "group") VALUES(1, 'Livro Disponível', 'Exemplar');

/* -------------------------------------------------------- 
  Table structure for table "user" 
-------------------------------------------------------- */
DROP TABLE "user";
CREATE TABLE "user" (
   "user_id" varchar(25) NOT NULL,
   "group_id" int2 NOT NULL,
   "status_id" int2 NOT NULL,
   "cpf" varchar(11) NOT NULL,
   "name" varchar(255) NOT NULL,
   "home_address" varchar(255) NOT NULL,
   "work_address" varchar(255),
   "home_phone" varchar(12),
   "work_phone" varchar(12),
   "cell_phone" varchar(12),
   "email" varchar(255),
   "photo_extension" varchar(3),
   "username" varchar(15),
   "password" varchar(15),
   "last_access_IP" varchar(15),
   "last_access_Date" varchar(25),
   CONSTRAINT "user_pkey" PRIMARY KEY ("user_id")
);
CREATE  UNIQUE INDEX "user_cpf_key" ON "user" ("cpf", "username");


/* -------------------------------------------------------- 
  Dumping data for table "user" 
-------------------------------------------------------- */ 
INSERT INTO "user" ("user_id", "group_id", "status_id", "cpf", "name", "home_address", "work_address", "home_phone", "work_phone", "cell_phone", "email", "photo_extension", "username", "password", "last_access_IP", "last_access_Date") VALUES('1019075364600', 29, 5, '02728379467', 'Edle Marcio', 'Rua Gustavo Paiva, 2220, Apto 102/02 Mangabeiras', '', '82-327-0516', '', '', '', '', '', '', '', '');
INSERT INTO "user" ("user_id", "group_id", "status_id", "cpf", "name", "home_address", "work_address", "home_phone", "work_phone", "cell_phone", "email", "photo_extension", "username", "password", "last_access_IP", "last_access_Date") VALUES('1019095261310', 36, 4, '03059038457', 'Marcello Alves de Sales Junior', 'Rua Gonçalves Dias, 58 Farol', 'Departamento de Tecnologia da Informação, UFAL', '82-326-2884', '82-214-1402', '82-9997-1415', 'marcellojunior@hotmail.com', 'jpg', '', '', '', '');
INSERT INTO "user" ("user_id", "group_id", "status_id", "cpf", "name", "home_address", "work_address", "home_phone", "work_phone", "cell_phone", "email", "photo_extension", "username", "password", "last_access_IP", "last_access_Date") VALUES('1019667750120', 36, 5, '00987705431', 'André Alécio', 'Rua Fulano de tal', 'UFAL - Proex', '', '', '82-9306-2160', 'andre_alecio@hotmail.com', '', '', '', '', '');
INSERT INTO "user" ("user_id", "group_id", "status_id", "cpf", "name", "home_address", "work_address", "home_phone", "work_phone", "cell_phone", "email", "photo_extension", "username", "password", "last_access_IP", "last_access_Date") VALUES('1019412970200', 36, 4, '02957198428', 'José Ricardo', 'Rua Victoria 19', 'NPD reitoria UFAL', '82-354-9466', '82-214-1075', '', 'jricardo_so@hotmail.com', 'jpg', '', '', '', '');
INSERT INTO "user" ("user_id", "group_id", "status_id", "cpf", "name", "home_address", "work_address", "home_phone", "work_phone", "cell_phone", "email", "photo_extension", "username", "password", "last_access_IP", "last_access_Date") VALUES('1019671559980', 37, 4, '00761107452', 'Arturo Hernandes Dominguez', 'Rua Fulano lalalal ', 'NPD reitoria UFAL', '9895-8545', '214-8954', '9978-5654', 'arturo@npd.ufal.br', 'jpg', '', '', '', '');

/* No Views found */

/* No Functions found */

/* -------------------------------------------------------- 
  Triggers 
-------------------------------------------------------- */ 
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_75073" AFTER INSERT OR UPDATE ON "right" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins" ('&amp;lt;unnamed&amp;gt;', 'right', 'group', 'UNSPECIFIED', 'group_id', 'group_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_80431" AFTER INSERT OR UPDATE ON "user" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins" ('&lt;unnamed&gt;', 'user', 'status', 'UNSPECIFIED', 'status_id', 'status_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_80433" AFTER DELETE ON "status" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del" ('&lt;unnamed&gt;', 'user', 'status', 'UNSPECIFIED', 'status_id', 'status_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_80435" AFTER UPDATE ON "status" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd" ('&lt;unnamed&gt;', 'user', 'status', 'UNSPECIFIED', 'status_id', 'status_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_80484" AFTER INSERT OR UPDATE ON "academic_user" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins" ('&lt;unnamed&gt;', 'academic_user', 'academic_course', 'UNSPECIFIED', 'academic_course_id', 'academic_course_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_80486" AFTER DELETE ON "academic_course" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del" ('&lt;unnamed&gt;', 'academic_user', 'academic_course', 'UNSPECIFIED', 'academic_course_id', 'academic_course_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_80488" AFTER UPDATE ON "academic_course" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd" ('&lt;unnamed&gt;', 'academic_user', 'academic_course', 'UNSPECIFIED', 'academic_course_id', 'academic_course_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_82596" AFTER INSERT OR UPDATE ON "exemplar" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins" ('&lt;unnamed&gt;', 'exemplar', 'physical_place', 'UNSPECIFIED', 'physical_place_id', 'physical_place_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_82598" AFTER DELETE ON "physical_place" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del" ('&lt;unnamed&gt;', 'exemplar', 'physical_place', 'UNSPECIFIED', 'physical_place_id', 'physical_place_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_82600" AFTER UPDATE ON "physical_place" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd" ('&lt;unnamed&gt;', 'exemplar', 'physical_place', 'UNSPECIFIED', 'physical_place_id', 'physical_place_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_82602" AFTER INSERT OR UPDATE ON "exemplar" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins" ('&lt;unnamed&gt;', 'exemplar', 'category', 'UNSPECIFIED', 'category_id', 'category_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_82604" AFTER DELETE ON "category" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del" ('&lt;unnamed&gt;', 'exemplar', 'category', 'UNSPECIFIED', 'category_id', 'category_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_82606" AFTER UPDATE ON "category" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd" ('&lt;unnamed&gt;', 'exemplar', 'category', 'UNSPECIFIED', 'category_id', 'category_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_82608" AFTER INSERT OR UPDATE ON "exemplar" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins" ('&lt;unnamed&gt;', 'exemplar', 'language', 'UNSPECIFIED', 'language_id', 'language_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_82610" AFTER DELETE ON "language" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del" ('&lt;unnamed&gt;', 'exemplar', 'language', 'UNSPECIFIED', 'language_id', 'language_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_82612" AFTER UPDATE ON "language" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd" ('&lt;unnamed&gt;', 'exemplar', 'language', 'UNSPECIFIED', 'language_id', 'language_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_82614" AFTER INSERT OR UPDATE ON "exemplar" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins" ('&lt;unnamed&gt;', 'exemplar', 'status', 'UNSPECIFIED', 'status_id', 'status_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_82616" AFTER DELETE ON "status" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del" ('&lt;unnamed&gt;', 'exemplar', 'status', 'UNSPECIFIED', 'status_id', 'status_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_82618" AFTER UPDATE ON "status" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd" ('&lt;unnamed&gt;', 'exemplar', 'status', 'UNSPECIFIED', 'status_id', 'status_id');
