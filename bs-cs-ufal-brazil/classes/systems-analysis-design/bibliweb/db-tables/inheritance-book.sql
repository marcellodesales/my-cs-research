CREATE DATABASE bibliweb WITH ENCODING = 'LATIN1';

CREATE SEQUENCE "ids" start 1 increment 1 maxvalue 2147483647 minvalue 1 cache 1;

################     Exemplares da Biblioteca    %%%%%%%%%%%%%%%%%%%%

CREATE TABLE "exemplar" (
   "exemplar_id" varchar(25) NOT NULL,
   "exemplar_type_id" int2 REFERENCES "exemplar_type"("exemplar_type_id") NOT NULL,
   "category_id" int2 REFERENCES "category"("category_id") NOT NULL,
   "language_id" int2 REFERENCES "language"("language_id") NOT NULL,
   "title" varchar(255) NOT NULL,
   "keywords" varchar(255) NOT NULL,
   "publishing_company" varchar(255) NOT NULL,
   CONSTRAINT "exemplar_pkey" PRIMARY KEY ("exemplar_id")
);
CREATE  INDEX "exemplar_exemplar_id_key" ON "exemplar" ("exemplar_id");

CREATE TABLE "exemplar_copy" (
   "exemplar_copy_id" varchar(25) NOT NULL,
   "exemplar_id" varchar(25) NOT NULL,
   "physical_place_id" int2 REFERENCES "physical_place"("physical_place_id") NOT NULL,
   "status_id" int2 REFERENCES "status"("status_id") NOT NULL,
   "acquisition_date" date NOT NULL,
   CONSTRAINT "exemplar_copy_pkey" PRIMARY KEY ("exemplar_copy_id")
);
CREATE  INDEX "exemplar_exemplar_copy_id_key" ON "exemplar_copy" ("exemplar_id");


CREATE TABLE "book" (
   "isbn" varchar NOT NULL,
   "volume" varchar NOT NULL,
   "authors" varchar NOT NULL,
   "edition" varchar(255) NOT NULL
) INHERITS("exemplar");

CREATE TABLE "dissertation" (
   "kind" varchar NOT NULL,
   "author" varchar NOT NULL,
   "publish_date" date NOT NULL,
   "abstract" varchar(255) NOT NULL
) INHERITS("exemplar");


############################     Usuários da Biblioteca   %%%%%%%%%%%%%%%%%%%%%%%%%%%%

CREATE TABLE "user" (
"user_id" varchar (25) NOT NULL, 
"group_id" int2 REFERENCES "grouping"("group_id") NOT NULL, 
"status_id" int2 REFERENCES "status"("status_id") NOT NULL, 
"cpf" varchar (11) NOT NULL, 
"name" varchar (255) NOT NULL, 
"home_address" varchar (255) NOT NULL, 
"work_address" varchar (255) , 
"home_phone" varchar (12) , 
"work_phone" varchar (12) , 
"cell_phone" varchar (12) , 
"email" varchar (255) , 
"photo_extension" varchar (3) , 
"username" varchar (15) , 
"password" varchar (15) , 
"last_access_IP" varchar (15) , 
"last_access_Date" varchar (25) ,
PRIMARY KEY ("user_id"), UNIQUE ("cpf", "username"));
CREATE INDEX "user_user_id_key" ON "user"("user_id");


CREATE TABLE "academic_user" (
"registration" varchar (13) NOT NULL, 
"academic_course_id" int2 REFERENCES "academic_course"("academic_course_id") NOT NULL ,UNIQUE ("registration")) 
INHERITS("user");

CREATE TABLE "clerk_user" (
"code" varchar (13) NOT NULL, UNIQUE ("code")) 
INHERITS("user");


%%%%%%%%%%%%%%%%%%%% Setores Biblioteca %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

CREATE TABLE "academic_course" (
   "academic_course_id" int2 NOT NULL,
   "code" varchar(3) NOT NULL UNIQUE,
   "description" varchar(255) NOT NULL,
   CONSTRAINT "academic_course_pkey" PRIMARY KEY ("academic_course_id")
);


CREATE TABLE "grouping" (
   "group_id" int2 NOT NULL,
   "code" varchar(2) NOT NULL,
   "description" varchar(255) NOT NULL,
   CONSTRAINT "group_pkey" PRIMARY KEY ("group_id")
);
CREATE  UNIQUE INDEX "description_grouping_ukey" ON "grouping" ("description");
CREATE  UNIQUE INDEX "code_grouping_ukey" ON "grouping" ("code");

CREATE TABLE "exemplar_type" (
"exemplar_type_id" int2 NOT NULL, 
"code" varchar(2) NOT NULL,
"description" varchar (255) NOT NULL ,
PRIMARY KEY ("exemplar_type_id"), UNIQUE ("exemplar_type_id", "code"));
CREATE UNIQUE INDEX "description_exemplar_type_ukey" ON "exemplar_type"("description")  
CREATE UNIQUE INDEX "code_exemplar_type_ukey" ON "exemplar_type"("code")

CREATE TABLE "right" (
"right_id" int2 NOT NULL, 
"group_id" int2 REFERENCES "group"("group_id") NOT NULL, 
"operation_id" int2 NOT NULL ,
PRIMARY KEY ("right_id"), UNIQUE ("right_id"));

CREATE TABLE "status" (
"status_id" int2 NOT NULL, 
"description" varchar (255) NOT NULL, 
"group" varchar (255) NOT NULL ,
PRIMARY KEY ("status_id"), UNIQUE ("status_id", "description"));

CREATE TABLE "language" (
"language_id" int2 NOT NULL, 
"description" varchar (100) NOT NULL ,
PRIMARY KEY ("language_id"), UNIQUE ("language_id"));

CREATE TABLE "category" (
"category_id" int2 NOT NULL, 
"academic_course_id" int2 REFERENCES "academic_course"("academic_course_id") NOT NULL, 
"description" varchar (255) NOT NULL ,
PRIMARY KEY ("category_id"), UNIQUE ("category_id"));
 
CREATE TABLE "physical_place" (
"physical_place_id" int2 NOT NULL, 
"description" varchar (255) NOT NULL ,
PRIMARY KEY ("physical_place_id"), UNIQUE ("physical_place_id"));


############### Associações ###################
CREATE TABLE "lend" (
"lend_id" varchar (25) NOT NULL, 
"lend_date" date NOT NULL, 
"devolution_date" date NOT NULL, 
"copy_id" varchar (25) NOT NULL, 
"renter_id" varchar (25) NOT NULL, 
"clerk_id" varchar (25) NOT NULL, 
"renewal_quant" int2 NOT NULL, 
"was_returned" bool NOT NULL ,
PRIMARY KEY ("lend_id"));

CREATE TABLE "fine" (
"fine_id" varchar NOT NULL, 
"rent_id" varchar NOT NULL, 
"renter_id" varchar NOT NULL, 
"value" float4 NOT NULL, 
"was_paid" bool DEFAULT 'f' NOT NULL ,
PRIMARY KEY ("fine_id"));


INSERT INTO "lend" ("lend_id", "lend_date", "devolution_date", "exemplar_id", "renter_id", "clerk_id", "renewal_quant", "was_returned") VALUES ('12121212', '2002-08-28', '2002-08-28', '1019621139580', '1019095261310', '1019671559980', '0', 'f')