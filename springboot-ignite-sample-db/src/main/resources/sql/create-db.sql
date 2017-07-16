-- ******************** --
-- H2 --
-- ******************** --
CREATE TABLE HOTEL (
  HOTEL_ID  INT  PRIMARY KEY,
  NAME      VARCHAR(50),
  EMAIL     VARCHAR(50),
  PHONE     VARCHAR(50),
  FAX       VARCHAR(50),
  RATE      VARCHAR(50),
  LOCAL_CURRENCY_CODE   VARCHAR(50),
  DESCRIPTION   VARCHAR(50)
);

-- ******************** --
-- HSQL --
-- ******************** --
--CREATE TABLE HOTEL
--(
--  HOTEL_ID              			NUMBER(11)              	NOT NULL,
--  NAME            		            VARCHAR2(255 CHAR)    	    NOT NULL,
--  EMAIL           		 	        VARCHAR2(255 CHAR) 		    NOT NULL,
--  PHONE           		            VARCHAR2(255 CHAR)      	NOT NULL,
--  FAX           		            VARCHAR2(255 CHAR)      	NOT NULL,
--  RATE           		            VARCHAR2(255 CHAR)      	NOT NULL,
--  LOCAL_CURRENCY_CODE       		VARCHAR2(30 CHAR)      	    NOT NULL,
--  DESCRIPTION           		    VARCHAR2(255 CHAR)      	NOT NULL,
--
--  CONSTRAINT HOTEL_PK PRIMARY KEY (HOTEL_ID)
--);