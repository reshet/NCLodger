CREATE TABLE "Accomodation"
(
    ID_ACC NUMBER NOT NULL PRIMARY KEY,
    ID_HOTEL NUMBER NOT NULL,
    PRICE NUMBER NOT NULL,
    QUANTITY NUMBER,
    TYPE VARCHAR2(50)
);

CREATE TABLE CONFIRM_EMAILS
(
    ID_USER NUMBER NOT NULL PRIMARY KEY,
    CONFIRM_HASH VARCHAR2(128) NOT NULL,
    DATE_CONFIRM DATE NOT NULL
);
CREATE TABLE "Order"
(
    ID_ORDER NUMBER NOT NULL PRIMARY KEY,
    ID_USER NUMBER NOT NULL,
    ID_SM NUMBER NOT NULL,
    DATE_ORDER DATE NOT NULL,
    ID_ACC NUMBER NOT NULL,
    PRICE NUMBER NOT NULL,
    START_DATE DATE NOT NULL,
    END_DATE DATE NOT NULL,
    ID_PC NUMBER NOT NULL ,
    DISCOUNT NUMBER
);
CREATE TABLE "DiscountRule"
(
    ID_DC NUMBER NOT NULL PRIMARY KEY,
    NAME_DC VARCHAR2(12) NOT NULL,
    DISCOUNT_VALUE NUMBER NOT NULL
);
CREATE TABLE "Hotel"
(
    ID_HOTEL NUMBER NOT NULL PRIMARY KEY,
    NAME_H VARCHAR2(20) NOT NULL,
    LOC_LAT NUMBER,
    LOC_LNG NUMBER,
    CATEGORY NUMBER
    ID_SM NUMBER NOT NULL
);

CREATE TABLE "Promocode"
(
    ID_PC NUMBER NOT NULL PRIMARY KEY,
    CODE VARCHAR2(20) NOT NULL,
    START_DATE DATE NOT NULL,
    END_DATE DATE NOT NULL,
    DISCOUNT NUMBER NOT NULL,
    ISUSED SMALLINT
);
CREATE TABLE "Users"
(
    ID_USER NUMBER NOT NULL PRIMARY KEY,
    EMAIL VARCHAR2(50) NOT NULL,
    PSWD VARCHAR2(32) NOT NULL,
    USERNAME VARCHAR2(20) NOT NULL,
    ID_UT NUMBER NOT NULL,
    IS_BLOCKED NUMBER NOT NULL,
    ID_DC NUMBER,
    ID_CE NUMBER
);
CREATE TABLE UserType
{
    ID_UT NUMBER NOT NULL PRIMARY KEY,
    NAME_T VARCHAR2(10) NOT NULL,
}
;

CREATE TABLE SalesManager
{
    ID_SM NUMBER NOT NULL PRIMARY KEY,
    ID_USER NUMBER,
    COMMISSION NUMBER
}
