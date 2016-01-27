CREATE ROLE RUBAN_USER_ROLE NOT IDENTIFIED;
CREATE ROLE RUBAN_ADMIN_ROLE NOT IDENTIFIED;

CREATE USER TEST_ADMIN IDENTIFIED BY adminpass;
CREATE USER TEST_USER IDENTIFIED BY userpass;

GRANT create session, RUBAN_ADMIN_ROLE TO TEST_ADMIN;
GRANT create session, RUBAN_USER_ROLE TO TEST_USER;


--------------------------------------------------------
--  DDL for Table Users
--------------------------------------------------------

  CREATE TABLE "Users" 
   (	"User_id" NUMBER(*,0),
    "User_login" VARCHAR2(100 BYTE),
	"User_firstname" VARCHAR2(100 BYTE), 
	"User_lastname" VARCHAR2(100 BYTE), 
	"User_password" VARCHAR2(100 BYTE), 
	"User_email" VARCHAR2(100 BYTE), 
	"User_birthday" DATE, 
	"User_gender" NUMBER(*,0),  
	"User_country" VARCHAR2(100 BYTE),
	"User_regdate" DATE
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
  
  GRANT SELECT ON "Users" TO RUBAN_USER_ROLE;
  
  GRANT INSERT ON "Users" TO RUBAN_USER_ROLE;
  
  GRANT UPDATE ON "Users" TO RUBAN_USER_ROLE;
 
  GRANT INSERT ON "Users" TO RUBAN_ADMIN_ROLE;
 
  GRANT SELECT ON "Users" TO RUBAN_ADMIN_ROLE;
 
  GRANT UPDATE ON "Users" TO RUBAN_ADMIN_ROLE;
  
  
--------------------------------------------------------
--  DDL for Table Documents
--------------------------------------------------------

  CREATE TABLE "Documents" 
   (	"Document_id" NUMBER(*,0), 
	"Document_name" VARCHAR2(100 BYTE), 
	"Document_adddate" DATE,  
	"Theme_id" NUMBER(*,0) 
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
  
  GRANT SELECT ON "Documents" TO RUBAN_USER_ROLE;
  
  GRANT INSERT ON "Documents" TO RUBAN_USER_ROLE;
  
  GRANT UPDATE ON "Documents" TO RUBAN_USER_ROLE;
  
  GRANT DELETE ON "Documents" TO RUBAN_USER_ROLE;
 
  GRANT SELECT ON "Documents" TO RUBAN_ADMIN_ROLE;
 
  GRANT INSERT ON "Documents" TO RUBAN_ADMIN_ROLE;
  
  GRANT UPDATE ON "Documents" TO RUBAN_ADMIN_ROLE;
  
  GRANT DELETE ON "Documents" TO RUBAN_ADMIN_ROLE;
  
  
--------------------------------------------------------
--  DDL for Table Themes
--------------------------------------------------------

  CREATE TABLE "Themes" 
   (	"Theme_id" NUMBER(*,0), 
    "Theme_name" VARCHAR2(100 BYTE), 
	"User_id" NUMBER(*,0)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
  
  GRANT SELECT ON "Themes" TO RUBAN_USER_ROLE;
  
  GRANT INSERT ON "Themes" TO RUBAN_USER_ROLE;
  
  GRANT UPDATE ON "Themes" TO RUBAN_USER_ROLE;
  
  GRANT DELETE ON "Themes" TO RUBAN_USER_ROLE;
 
  GRANT SELECT ON "Themes" TO RUBAN_ADMIN_ROLE;
 
  GRANT INSERT ON "Themes" TO RUBAN_ADMIN_ROLE;
  
  GRANT UPDATE ON "Themes" TO RUBAN_ADMIN_ROLE;
  
  GRANT DELETE ON "Themes" TO RUBAN_ADMIN_ROLE;

  
--------------------------------------------------------
--  DDL for Table Lexems
--------------------------------------------------------

  CREATE TABLE "Lexems" 
   (	"Lexem_id" NUMBER(*,0), 
    "Lexem_name" VARCHAR2(100 BYTE), 
	"Theme_id" NUMBER(*,0)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
  
  GRANT SELECT ON "Lexems" TO RUBAN_USER_ROLE;
  
  GRANT INSERT ON "Lexems" TO RUBAN_USER_ROLE;
  
  GRANT UPDATE ON "Lexems" TO RUBAN_USER_ROLE;
  
  GRANT DELETE ON "Lexems" TO RUBAN_USER_ROLE;
 
  GRANT SELECT ON "Lexems" TO RUBAN_ADMIN_ROLE;
 
  GRANT INSERT ON "Lexems" TO RUBAN_ADMIN_ROLE;
  
  GRANT UPDATE ON "Lexems" TO RUBAN_ADMIN_ROLE;
  
  GRANT DELETE ON "Lexems" TO RUBAN_ADMIN_ROLE;

  
--------------------------------------------------------
--                    VIEWES
--------------------------------------------------------  

CREATE OR REPLACE FORCE VIEW "GetThemes" ("RANK", "Theme_name") AS 
  SELECT row_number() over (order by "Themes"."Theme_name") AS rank, "Themes"."Theme_name"
FROM "Themes" ORDER BY "Themes"."Theme_name";

CREATE OR REPLACE FORCE VIEW "GetDocumentsNone" ("RANK", "Document_name", "Theme_id") AS 
  SELECT row_number() over (order by "Documents"."Document_id") AS rank, "Documents"."Document_name", "Documents"."Theme_id"
FROM "Documents" ORDER BY "Documents"."Document_id";

CREATE OR REPLACE FORCE VIEW "GetDocumentsAlphabet" ("RANK", "Document_name", "Theme_id") AS 
  SELECT row_number() over (order by "Documents"."Document_name") AS rank, "Documents"."Document_name", "Documents"."Theme_id"
FROM "Documents" ORDER BY "Documents"."Document_name";

CREATE OR REPLACE FORCE VIEW "GetDocumentsDate" ("RANK", "Document_name", "Theme_id") AS 
  SELECT row_number() over (order by "Documents"."Document_adddate") AS rank, "Documents"."Document_name", "Documents"."Theme_id"
FROM "Documents" ORDER BY "Documents"."Document_adddate";

CREATE OR REPLACE FORCE VIEW "GetLexems" ("RANK", "Lexem_name", "Theme_id") AS 
  SELECT row_number() over (order by "Lexems"."Lexem_name") AS rank, "Lexems"."Lexem_name", "Lexems"."Theme_id"
FROM "Lexems" ORDER BY "Lexems"."Lexem_name";
 
--------------------------------------------------------
--                    SEQUENCES
--------------------------------------------------------  

CREATE SEQUENCE  "IncUserId"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "IncDocumentId"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ; 
CREATE SEQUENCE  "IncThemeId"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "IncLexemId"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
  
REM INSERTING into "Users"
SET DEFINE OFF;
REM INSERTING into "Documents"
SET DEFINE OFF;
REM INSERTING into "Themes"
SET DEFINE OFF;
REM INSERTING into "Lexems"
SET DEFINE OFF;


--------------------------------------------------------
--  DDL for Index PK_USERS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_USERS" ON "Users" ("User_id") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
  
  
--------------------------------------------------------
--  DDL for Index PK_DOCUMENTS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_DOCUMENTS" ON "Documents" ("Document_id") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
  
  
--------------------------------------------------------
--  DDL for Index PK_THEMES
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_THEMES" ON "Themes" ("Theme_id") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;


--------------------------------------------------------
--  DDL for Index PK_LEXEMS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_LEXEMS" ON "Lexems" ("Lexem_id") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
  
  
--------------------------------------------------------
--  DDL for Index User_in_theme_FK
--------------------------------------------------------

  CREATE INDEX "FK_USER_IN_THEME" ON "Themes" ("User_id") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;

  
--------------------------------------------------------
--  DDL for Index Theme_in_document_FK
--------------------------------------------------------

  CREATE INDEX "FK_THEME_IN_DOCUMENT" ON "Documents" ("Theme_id") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;

  
--------------------------------------------------------
--  DDL for Index Theme_in_lexem_FK
--------------------------------------------------------

  CREATE INDEX "FK_THEME_IN_LEXEM" ON "Lexems" ("Theme_id") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;



--------------------------------------------------------
--  Constraints for Table Users
--------------------------------------------------------

  ALTER TABLE "Users" ADD CONSTRAINT "PK_USERS" PRIMARY KEY ("User_id")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "Users" MODIFY ("User_id" NOT NULL ENABLE);
  
  ALTER TABLE "Users" MODIFY ("User_login" NOT NULL ENABLE);
 
  ALTER TABLE "Users" MODIFY ("User_firstname" NOT NULL ENABLE);
  
  ALTER TABLE "Users" MODIFY ("User_lastname" NOT NULL ENABLE);
  
  ALTER TABLE "Users" MODIFY ("User_password" NOT NULL ENABLE);
  
  ALTER TABLE "Users" MODIFY ("User_email" NOT NULL ENABLE);
  
  ALTER TABLE "Users" MODIFY ("User_birthday" NOT NULL ENABLE);
  
  ALTER TABLE "Users" MODIFY ("User_gender" NOT NULL ENABLE);
  
  ALTER TABLE "Users" MODIFY ("User_country" NOT NULL ENABLE);
  
  ALTER TABLE "Users" MODIFY ("User_regdate" NOT NULL ENABLE);
  
  
--------------------------------------------------------
--  Constraints for Table Documents
--------------------------------------------------------

  ALTER TABLE "Documents" ADD CONSTRAINT "PK_DOCUMENTS" PRIMARY KEY ("Document_id")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "Documents" MODIFY ("Document_id" NOT NULL ENABLE);
  
  ALTER TABLE "Documents" MODIFY ("Document_name" NOT NULL ENABLE);
   
  ALTER TABLE "Documents" MODIFY ("Document_adddate" NOT NULL ENABLE);   
  
  ALTER TABLE "Documents" MODIFY ("Theme_id" NOT NULL ENABLE);
 
 
--------------------------------------------------------
--  Constraints for Table Themes
--------------------------------------------------------

  ALTER TABLE "Themes" ADD CONSTRAINT "PK_THEMES" PRIMARY KEY ("Theme_id")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "Themes" MODIFY ("Theme_id" NOT NULL ENABLE);
  
  ALTER TABLE "Themes" MODIFY ("Theme_name" NOT NULL ENABLE);
   
  ALTER TABLE "Themes" MODIFY ("User_id" NOT NULL ENABLE);
  
  
--------------------------------------------------------
--  Constraints for Table Lexems
--------------------------------------------------------

  ALTER TABLE "Lexems" ADD CONSTRAINT "PK_LEXEMS" PRIMARY KEY ("Lexem_id")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "Lexems" MODIFY ("Lexem_id" NOT NULL ENABLE);
  
  ALTER TABLE "Lexems" MODIFY ("Lexem_name" NOT NULL ENABLE);
   
  ALTER TABLE "Lexems" MODIFY ("Theme_id" NOT NULL ENABLE);
  
 
--------------------------------------------------------
--  Ref Constraints for Table Documents
--------------------------------------------------------

  ALTER TABLE "Themes" ADD CONSTRAINT "FK_USER_IN_THEME" FOREIGN KEY ("User_id")
	  REFERENCES "Users" ("User_id") ENABLE;
  ALTER TABLE "Documents" ADD CONSTRAINT "FK_THEME_IN_DOCUMENT" FOREIGN KEY ("Theme_id")
	  REFERENCES "Themes" ("Theme_id") ENABLE;
  ALTER TABLE "Lexems" ADD CONSTRAINT "FK_THEME_IN_LEXEM" FOREIGN KEY ("Theme_id")
	  REFERENCES "Themes" ("Theme_id") ENABLE;