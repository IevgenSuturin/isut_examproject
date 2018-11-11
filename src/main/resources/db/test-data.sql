INSERT INTO ITEM (id, title, description, count, price, date_stor)
VALUES ( item_sequence.nextval, 'test item', 'test item description', 10, 1000, GETDATE() );

INSERT INTO ITEM_CAT_TYPES (id, name, decs)
VALUES ( ict_sequence.nextval, 'height', 'height of item' );
INSERT INTO ITEM_CAT_TYPES (id, name, decs)
VALUES ( ict_sequence.nextval, 'width', 'width of item' );
INSERT INTO ITEM_CATEGORIES (id, item_id, ictype_id, value)
VALUES ( ic_sequence.nextval, 1, 1, '10 sm');
INSERT INTO ITEM_CATEGORIES (id, item_id, ictype_id, value)
VALUES ( ic_sequence.nextval, 1, 2, '20 sm');

INSERT INTO OWNER (id, fname, lname, cmpname)
VALUES ( owner_sequence.nextval, 'FIRST NAME1', 'LAST NAME1', 'COMPANY NAME1' );
INSERT INTO OWNER (id, fname, lname, cmpname)
VALUES ( owner_sequence.nextval, 'FIRST NAME2', 'LAST NAME2', 'COMPANY NAME2' );
INSERT INTO CONTACT_DATA_TYPES (id, name, desc)
VALUES ( cdt_sequence.nextval, 'phone', 'phone number' );
INSERT INTO CONTACT_DATA_TYPES(id, name, desc)
VALUES ( cdt_sequence.nextval, 'E-mail', 'E-mail' );
INSERT INTO CONTACT_DATA (id, owner_id, cdtype_id, value)
VALUES ( cd_sequence.nextval, 1, 1, '111 111 111 111');
INSERT INTO CONTACT_DATA (id, owner_id, cdtype_id, value)
VALUES ( cd_sequence.nextval, 1, 2, 'email1@email.com');
INSERT INTO CONTACT_DATA (id, owner_id, cdtype_id, value)
VALUES ( cd_sequence.nextval, 2, 1, '222 222 222 222');
INSERT INTO CONTACT_DATA (id, owner_id, cdtype_id, value)
VALUES ( cd_sequence.nextval, 2, 2, 'email22@email.com');

INSERT INTO TRANS_TYPES (id, name, desc)
VALUES ( trans_type_sequence.nextval, 'store', 'description of store transaction' );
INSERT INTO TRANS_TYPES (id, name, desc)
VALUES ( trans_type_sequence.nextval, 'withdrew', 'description of withdrew transaction' );