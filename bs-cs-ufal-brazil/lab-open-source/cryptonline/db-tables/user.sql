/* -------------------------------------------------------- 
  phpPgAdmin 2.4-1 DB Dump
  http://sourceforge.net/projects/phppgadmin/
  Host: localhost:5432
  Database  : "rsa_encription"
  2002-09-23 19:09:59
-------------------------------------------------------- */ 

/* -------------------------------------------------------- 
  Sequences 
-------------------------------------------------------- */ 
CREATE SEQUENCE "id_generator" start 1 increment 1 maxvalue 2147483647 minvalue 1 cache 1; 

/* -------------------------------------------------------- 
  Table structure for table "user" 
-------------------------------------------------------- */
CREATE TABLE "user" (
   "user_id" varchar NOT NULL,
   "first_name" varchar(20) NOT NULL,
   "last_name" varchar(100) NOT NULL,
   "email" varchar NOT NULL,
   "password" varchar (10) NOT NULL,
   "public_key_n" varchar NOT NULL,
   "public_key_e" varchar NOT NULL,
   "private_key_d" varchar NOT NULL,
   CONSTRAINT "user_pkey" PRIMARY KEY ("user_id")
);
CREATE  UNIQUE INDEX "email_user_ukey" ON "user" ("email");

