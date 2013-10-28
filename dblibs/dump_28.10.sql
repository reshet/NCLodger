CREATE TABLE "Accomodation"
(
    ID_ACC INTEGER PRIMARY KEY NOT NULL,
    ID_HOTEL INTEGER NOT NULL,
    PRICE FLOAT(126) NOT NULL,
    COMMISSION FLOAT(126),
    QUANTITY INTEGER NOT NULL,
    TYPE VARCHAR2(50)
);
CREATE TABLE "Basket"
(
    ID_BASKET INTEGER PRIMARY KEY NOT NULL,
    ID_ACC INTEGER,
    ID_USER INTEGER NOT NULL,
    START_DATE DATE,
    END_DATE DATE,
    QUANTITY INTEGER
);
CREATE TABLE CONFIRM_EMAILS
(
    ID_USER INTEGER NOT NULL,
    CONFIRM_HASH VARCHAR2(128) NOT NULL,
    PRIMARY KEY ( ID_USER, CONFIRM_HASH )
);
CREATE TABLE "CheckItem"
(
    ID_ITEM INTEGER PRIMARY KEY NOT NULL,
    ID_ACC INTEGER NOT NULL,
    ID_ORDER INTEGER NOT NULL,
    START_DATE DATE NOT NULL,
    END_DATE INTEGER NOT NULL,
    QUANTITY INTEGER DEFAULT 1 NOT NULL,
    PRICE FLOAT(126) NOT NULL
);
CREATE TABLE "Discount_rule"
(
    ID_DC INTEGER PRIMARY KEY NOT NULL,
    "typeStatus" VARCHAR2(12) NOT NULL,
    DISCOUNT_VALUE FLOAT(126) NOT NULL
);
CREATE TABLE "Hotel"
(
    ID_HOTEL INTEGER PRIMARY KEY NOT NULL,
    NAME VARCHAR2(20) NOT NULL,
    LOC_LAT FLOAT(126),
    LOC_LNG FLOAT(126),
    CATEGORY INTEGER
);
CREATE TABLE "Order"
(
    ID_ORDER INTEGER PRIMARY KEY NOT NULL,
    ID_USER INTEGER NOT NULL,
    ID_PC INTEGER,
    DISCOUNT_VALUE FLOAT(126),
    "date" DATE NOT NULL,
    STATUS INTEGER
);
CREATE TABLE "Promocode"
(
    ID_PC INTEGER PRIMARY KEY NOT NULL,
    CODE VARCHAR2(20) NOT NULL,
    START_DATE DATE NOT NULL,
    END_DATE DATE NOT NULL,
    DISCOUNT FLOAT(126) NOT NULL,
    "isUsed" INTEGER NOT NULL
);
CREATE TABLE "Users"
(
    ID_USER INTEGER PRIMARY KEY NOT NULL,
    USERNAME VARCHAR2(20) NOT NULL,
    EMAIL VARCHAR2(50) NOT NULL,
    PSWD VARCHAR2(32) NOT NULL,
    USER_TYPE VARCHAR2(10) NOT NULL,
    IS_BLOCKED INTEGER NOT NULL,
    ID_DC INTEGER
);
ALTER TABLE "Accomodation" ADD FOREIGN KEY ( ID_HOTEL ) REFERENCES "Hotel" ( ID_HOTEL );
ALTER TABLE "Basket" ADD FOREIGN KEY ( ID_ACC ) REFERENCES "Accomodation" ( ID_ACC );
ALTER TABLE "Basket" ADD FOREIGN KEY ( ID_USER ) REFERENCES "Users" ( ID_USER );
ALTER TABLE CONFIRM_EMAILS ADD FOREIGN KEY ( ID_USER ) REFERENCES "Users" ( ID_USER );
ALTER TABLE "CheckItem" ADD FOREIGN KEY ( ID_ACC ) REFERENCES "Accomodation" ( ID_ACC );
ALTER TABLE "CheckItem" ADD FOREIGN KEY ( ID_ORDER ) REFERENCES "Order" ( ID_ORDER );
ALTER TABLE "Order" ADD FOREIGN KEY ( ID_PC ) REFERENCES "Promocode" ( ID_PC );
ALTER TABLE "Order" ADD FOREIGN KEY ( ID_USER ) REFERENCES "Users" ( ID_USER );
ALTER TABLE "Users" ADD FOREIGN KEY ( ID_DC ) REFERENCES "Discount_rule" ( ID_DC );
