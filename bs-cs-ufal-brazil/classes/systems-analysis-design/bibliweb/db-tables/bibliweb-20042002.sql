
/* -------------------------------------------------------- 
  phpPgAdmin 2.4-1 DB Dump
  http://sourceforge.net/projects/phppgadmin/
  Host: localhost:5432
  Database  : "bibliweb"
  2002-04-21 00:04:02
-------------------------------------------------------- */ 

/* -------------------------------------------------------- 
  Sequences 
-------------------------------------------------------- */ 
CREATE SEQUENCE "ids" start 38 increment 1 maxvalue 2147483647 minvalue 1 cache 1; 
SELECT NEXTVAL('ids'); 

/* -------------------------------------------------------- 
  Table structure for table "academic_course" 
-------------------------------------------------------- */
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

/* -------------------------------------------------------- 
  Table structure for table "book" 
-------------------------------------------------------- */
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
   "edition" varchar(255) NOT NULL
);


/* -------------------------------------------------------- 
  Dumping data for table "book" 
-------------------------------------------------------- */ 
INSERT INTO "book" ("exemplar_id", "physical_place_id", "category_id", "language_id", "status_id", "title", "keywords", "acquisition_date", "total_quantity", "available_quantity", "isbn", "volume", "authors", "edition") VALUES('1019354883580', 14, 8, 28, 1, 'Fundamentals of Database Systems', 'Banco de dados|Datamining|Diagrama ER|XML', '2002-04-20', 3, 2, '85-9423-3322-3', 'Único', 'Elmasri & Navathe', '3ª Edition');

/* -------------------------------------------------------- 
  Table structure for table "category" 
-------------------------------------------------------- */
CREATE TABLE "category" (
   "category_id" int2 NOT NULL,
   "academic_course_id" int2 NOT NULL,
   "description" varchar(255) NOT NULL,
   CONSTRAINT "category_pkey" PRIMARY KEY ("category_id")
);


/* -------------------------------------------------------- 
  Dumping data for table "category" 
-------------------------------------------------------- */ 
INSERT INTO "category" ("category_id", "academic_course_id", "description") VALUES(8, 6, 'Banco de dados');
INSERT INTO "category" ("category_id", "academic_course_id", "description") VALUES(9, 6, 'Inteligência Artificial');

/* -------------------------------------------------------- 
  Table structure for table "dissertation" 
-------------------------------------------------------- */
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
   "abstract" varchar(255) NOT NULL
);


/* -------------------------------------------------------- 
  Dumping data for table "dissertation" 
-------------------------------------------------------- */ 

/* -------------------------------------------------------- 
  Table structure for table "exemplar" 
-------------------------------------------------------- */
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
   CONSTRAINT "exemplar_pkey" PRIMARY KEY ("exemplar_id")
);


/* -------------------------------------------------------- 
  Dumping data for table "exemplar" 
-------------------------------------------------------- */ 
INSERT INTO "exemplar" ("exemplar_id", "physical_place_id", "category_id", "language_id", "status_id", "title", "keywords", "acquisition_date", "total_quantity", "available_quantity") VALUES('1019354883580', 14, 8, 28, 1, 'Fundamentals of Database Systems', 'Banco de dados|Datamining|Diagrama ER|XML', '2002-04-20', 3, 2);

/* -------------------------------------------------------- 
  Table structure for table "grouping" 
-------------------------------------------------------- */
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
