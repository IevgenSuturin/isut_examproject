CREATE TABLE ITEM (id NUMBER(14) NOT NULL,
                   title VARCHAR2(20),
                   description VARCHAR2(50),
                   count NUMBER(10),
                   price NUMBER(10),
                   date_stor DATETIME,
                   PRIMARY KEY (id)
);
CREATE SEQUENCE item_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE OWNER (id NUMBER(14) NOT NULL,
                    fname VARCHAR2(20),
                    lname VARCHAR2(20),
                    cmpname VARCHAR2(40),
                    PRIMARY KEY (id)
);
CREATE SEQUENCE owner_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE TRANS_TYPES ( id NUMBER(14) NOT NULL,
                           name VARCHAR2(20),
                           desc VARCHAR2(40),
                           PRIMARY KEY (id)
);
CREATE SEQUENCE trans_type_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE TRANSACTIONS ( id NUMBER(14) NOT NULL,
                            item_id NUMBER(14),
                            owner_id NUMBER(14),
                            data_trans DATETIME,
                            count NUMBER(10),
                            amount NUMBER(10),
                            trtype_id NUMBER(14),
                            PRIMARY KEY (id),
                            CONSTRAINT FK_TRANSTYPE FOREIGN KEY (trtype_id) REFERENCES TRANS_TYPES(id)
);
CREATE SEQUENCE trans_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE CONTACT_DATA_TYPES ( id NUMBER(14) NOT NULL,
                                  name VARCHAR2(20),
                                  desc VARCHAR2(40),
                                  PRIMARY KEY (id)
);
CREATE SEQUENCE cdt_sequence START WITH 1 INCREMENT BY 1;


CREATE TABLE CONTACT_DATA(id NUMBER(14) NOT NULL,
                          owner_id NUMBER(14),
                          cdtype_id NUMBER(14),
                          value VARCHAR2(40),
                          PRIMARY KEY (id),
                          CONSTRAINT FK_OWNERCONTACT FOREIGN KEY (owner_id) REFERENCES OWNER(id),
                          CONSTRAINT FK_TYPECONTACT FOREIGN KEY (cdtype_id) REFERENCES CONTACT_DATA_TYPES(id)
);
CREATE SEQUENCE cd_sequence START WITH 1 INCREMENT BY 1;


CREATE TABLE ITEM_CAT_TYPES ( id NUMBER(14) NOT NULL,
                              name VARCHAR2(20),
                              decs VARCHAR2(40),
                              PRIMARY KEY (id)
);
CREATE SEQUENCE ict_sequence START WITH 1 INCREMENT BY 1;


CREATE TABLE ITEM_CATEGORIES ( id NUMBER(14) NOT NULL,
                               item_id NUMBER(14),
                               ictype_id NUMBER(14),
                               value VARCHAR2(40),
                               PRIMARY KEY (id),
                               CONSTRAINT FK_ITEMCATEG FOREIGN KEY (item_id) REFERENCES ITEM(id),
                               CONSTRAINT FK_TYPECATEG FOREIGN KEY (ictype_id) REFERENCES ITEM_CAT_TYPES(id)
);
CREATE SEQUENCE ic_sequence START WITH 1 INCREMENT BY 1;