
/* -------------------------------------------------------- 
  phpPgAdmin 2.4-1 DB Dump
  http://sourceforge.net/projects/phppgadmin/
  Host: localhost:5432
  Database  : "bibliweb"
  2002-04-13 23:04:24
-------------------------------------------------------- */ 

/* -------------------------------------------------------- 
  Sequences 
-------------------------------------------------------- */ 
DROP SEQUENCE "ids";
CREATE SEQUENCE "ids" start 2 increment 1 maxvalue 2147483647 minvalue 1 cache 1; 
SELECT NEXTVAL('ids'); 

/* -------------------------------------------------------- 
  Table structure for table "academic_course" 
-------------------------------------------------------- */
CREATE TABLE "academic_course" (
   "academic_course_id" varchar(3) NOT NULL,
   "code" varchar(3) NOT NULL,
   "description" varchar(255) NOT NULL,
   CONSTRAINT "academic_course_pkey" PRIMARY KEY ("academic_course_id")
);
CREATE  UNIQUE INDEX "code_academic_course_ukey" ON "academic_course" ("code");
CREATE  UNIQUE INDEX "description_academic_course_uke" ON "academic_course" ("description");


/* -------------------------------------------------------- 
  Dumping data for table "academic_course" 
-------------------------------------------------------- */ 
INSERT INTO "academic_course" ("academic_course_id", "code", "description") VALUES('001', 'G55', 'Ciência da Computação');
INSERT INTO "academic_course" ("academic_course_id", "code", "description") VALUES('002', '09', 'Medicina');

/* -------------------------------------------------------- 
  Table structure for table "academic_user" 
-------------------------------------------------------- */

CREATE TABLE "academic_user" (
   "user_id" varchar(25) NOT NULL,
   "group_id" varchar(25) NOT NULL,
   "status_id" varchar(25) NOT NULL,
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
   "academic_course_id" varchar(3) NOT NULL
);
CREATE  UNIQUE INDEX "academic_user_registration_key" ON "academic_user" ("registration");


/* -------------------------------------------------------- 
  Dumping data for table "academic_user" 
-------------------------------------------------------- */ 
INSERT INTO "academic_user" ("user_id", "group_id", "status_id", "cpf", "name", "home_address", "work_address", "home_phone", "work_phone", "cell_phone", "email", "photo_extension", "username", "password", "last_access_IP", "last_access_Date", "registration", "academic_course_id") VALUES('1018751043340', '01', 'UO', '03059038457', 'Marcello Junior', 'Rua nla...', 'UFAL', '326-2884', '214-1402', '82-9997-1415', '', '', '', '', '', '', '1998G55D001T9', '001');

/* -------------------------------------------------------- 
  Table structure for table "book" 
-------------------------------------------------------- */

CREATE TABLE "book" (
   "exemplar_id" varchar(25) NOT NULL,
   "library_id" varchar(25) NOT NULL,
   "physical_place_id" varchar(25) NOT NULL,
   "category_id" varchar(25) NOT NULL,
   "language_id" varchar(25) NOT NULL,
   "status_id" varchar(25) NOT NULL,
   "title" varchar(255) NOT NULL,
   "edition" varchar(255) NOT NULL,
   "keywords" varchar(255),
   "acquisition_date" date,
   "quantity" int2 DEFAULT 1 NOT NULL,
   "isbn" varchar NOT NULL,
   "volume" varchar NOT NULL,
   "authors" varchar NOT NULL
);
CREATE  UNIQUE INDEX "isbn_book_ukey" ON "book" ("isbn");


/* -------------------------------------------------------- 
  Dumping data for table "book" 
-------------------------------------------------------- */ 
INSERT INTO "book" ("exemplar_id", "library_id", "physical_place_id", "category_id", "language_id", "status_id", "title", "edition", "keywords", "acquisition_date", "quantity", "isbn", "volume", "authors") VALUES('1018731944950', '0805317551', '0805317552', '0805317553', '0805317554', 'BA', 'Fundamentals of Database Systems', '3?Edition', 'Banco de dados|Datamining|Diagrama ER|XML', '2002-04-13', 1, '85-7393-116-7', '?ico', 'Elmasri & Navathe');

/* -------------------------------------------------------- 
  Table structure for table "exemplar" 
-------------------------------------------------------- */

CREATE TABLE "exemplar" (
   "exemplar_id" varchar(25) NOT NULL,
   "library_id" varchar(25) NOT NULL,
   "physical_place_id" varchar(25) NOT NULL,
   "category_id" varchar(25) NOT NULL,
   "language_id" varchar(25) NOT NULL,
   "status_id" varchar(25) NOT NULL,
   "title" varchar(255) NOT NULL,
   "edition" varchar(255) NOT NULL,
   "keywords" varchar(255),
   "acquisition_date" date,
   "quantity" int2 DEFAULT 1 NOT NULL,
   CONSTRAINT "exemplar_pkey" PRIMARY KEY ("exemplar_id")
);
CREATE  INDEX "exemplar_exemplar_id_key" ON "exemplar" ("exemplar_id");


/* -------------------------------------------------------- 
  Dumping data for table "exemplar" 
-------------------------------------------------------- */ 
INSERT INTO "exemplar" ("exemplar_id", "library_id", "physical_place_id", "category_id", "language_id", "status_id", "title", "edition", "keywords", "acquisition_date", "quantity") VALUES('1018731944950', '0805317551', '0805317552', '0805317553', '0805317554', 'BA', 'Fundamentals of Database Systems', '3?Edition', 'Banco de dados|Datamining|Diagrama ER|XML', '2002-04-13', 1);

/* -------------------------------------------------------- 
  Table structure for table "group" 
-------------------------------------------------------- */

CREATE TABLE "group" (
   "group_id" char(2) NOT NULL,
   "description" varchar(255) NOT NULL,
   CONSTRAINT "group_pkey" PRIMARY KEY ("group_id")
);


/* -------------------------------------------------------- 
  Dumping data for table "group" 
-------------------------------------------------------- */ 
INSERT INTO "group" ("group_id", "description") VALUES('01', 'Alunos');

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
   "status_id" varchar(2) NOT NULL,
   "description" varchar(255) NOT NULL,
   "group" varchar(20) NOT NULL,
   CONSTRAINT "status_pkey" PRIMARY KEY ("status_id")
);
CREATE  UNIQUE INDEX "status_status_id_key" ON "status" ("description", "status_id");


/* -------------------------------------------------------- 
  Dumping data for table "status" 
-------------------------------------------------------- */ 
INSERT INTO "status" ("status_id", "description", "group") VALUES('BA', 'Livro dispon?el', 'Exemplar');
INSERT INTO "status" ("status_id", "description", "group") VALUES('UA', 'Usuario Ativo', 'Usuario');

/* -------------------------------------------------------- 
  Table structure for table "user" 
-------------------------------------------------------- */

CREATE TABLE "user" (
   "user_id" varchar(25) NOT NULL,
   "group_id" varchar(25) NOT NULL,
   "status_id" varchar(25) NOT NULL,
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
INSERT INTO "user" ("user_id", "group_id", "status_id", "cpf", "name", "home_address", "work_address", "home_phone", "work_phone", "cell_phone", "email", "photo_extension", "username", "password", "last_access_IP", "last_access_Date") VALUES('1018751043340', '01', 'UO', '03059038457', 'Marcello Junior', 'Rua nla...', 'UFAL', '326-2884', '214-1402', '82-9997-1415', '', '', '', '', '', '');

/* No Views found */

/* No Functions found */

/* -------------------------------------------------------- 
  Triggers 
-------------------------------------------------------- */ 
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_62744" AFTER INSERT OR UPDATE ON "right" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins" ('&lt;unnamed&gt;', 'right', 'group', 'UNSPECIFIED', 'group_id', 'group_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_62746" AFTER DELETE ON "group" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del" ('&lt;unnamed&gt;', 'right', 'group', 'UNSPECIFIED', 'group_id', 'group_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_62748" AFTER UPDATE ON "group" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd" ('&lt;unnamed&gt;', 'right', 'group', 'UNSPECIFIED', 'group_id', 'group_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_63122" AFTER INSERT OR UPDATE ON "user" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins" ('&lt;unnamed&gt;', 'user', 'group', 'UNSPECIFIED', 'group_id', 'group_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_63124" AFTER DELETE ON "group" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del" ('&lt;unnamed&gt;', 'user', 'group', 'UNSPECIFIED', 'group_id', 'group_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_63126" AFTER UPDATE ON "group" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd" ('&lt;unnamed&gt;', 'user', 'group', 'UNSPECIFIED', 'group_id', 'group_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_63128" AFTER INSERT OR UPDATE ON "user" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins" ('&lt;unnamed&gt;', 'user', 'status', 'UNSPECIFIED', 'status_id', 'status_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_63130" AFTER DELETE ON "status" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del" ('&lt;unnamed&gt;', 'user', 'status', 'UNSPECIFIED', 'status_id', 'status_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_63132" AFTER UPDATE ON "status" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd" ('&lt;unnamed&gt;', 'user', 'status', 'UNSPECIFIED', 'status_id', 'status_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_63166" AFTER INSERT OR UPDATE ON "academic_user" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins" ('&lt;unnamed&gt;', 'academic_user', 'academic_course', 'UNSPECIFIED', 'academic_course_id', 'academic_course_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_63168" AFTER DELETE ON "academic_course" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del" ('&lt;unnamed&gt;', 'academic_user', 'academic_course', 'UNSPECIFIED', 'academic_course_id', 'academic_course_id');
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_63170" AFTER UPDATE ON "academic_course" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd" ('&lt;unnamed&gt;', 'academic_user', 'academic_course', 'UNSPECIFIED', 'academic_course_id', 'academic_course_id');
