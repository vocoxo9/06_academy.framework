DROP TABLE ATTACHMENT;
DROP TABLE REPLY;
DROP TABLE BOARD;
DROP TABLE CATEGORY;
DROP TABLE NOTICE;
DROP TABLE MEMBER;

DROP SEQUENCE SEQ_UNO; -- ȸ����ȣ �߻���ų ������
DROP SEQUENCE SEQ_NNO; -- �������׹�ȣ �߻���ų ������
DROP SEQUENCE SEQ_BNO; -- �Խ��ǹ�ȣ �߻���ų ������
DROP SEQUENCE SEQ_RNO; -- ��۹�ȣ �߻���ų ������
DROP SEQUENCE SEQ_FNO; -- ÷�����Ϲ�ȣ �߻���ų ������

--------------------------------------------------------------------------
------------------------ MEMBER ���� ---------------------------
--------------------------------------------------------------------

CREATE TABLE MEMBER(
    USER_NO NUMBER PRIMARY KEY,
    USER_ID VARCHAR2(30) NOT NULL UNIQUE,
    USER_PWD VARCHAR2(100) NOT NULL,
    USER_NAME VARCHAR2(15) NOT NULL,
    EMAIL VARCHAR2(100),
    BIRTHDAY VARCHAR2(6),
    GENDER VARCHAR2(1) CHECK (GENDER IN ('M','F')),
    PHONE VARCHAR(13),
    ADDRESS VARCHAR2(100),
    ENROLL_DATE DATE DEFAULT SYSDATE,
    MODIFY_DATE DATE DEFAULT SYSDATE,
    STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N'))
);

CREATE SEQUENCE SEQ_UNO
NOCACHE;

INSERT INTO MEMBER
VALUES (SEQ_UNO.NEXTVAL, 'admin', '1234', '������', 'admin@kh.or.kr','800918','F',  '010-1111-2222','����� ���α� �ﰢ��', '2024-12-31', '2024-12-31', 'Y');

INSERT INTO MEMBER
VALUES (SEQ_UNO.NEXTVAL, 'user01', 'pass01', 'ȫ�浿', 'user01@kh.or.kr','900213','M',  '010-3333-4444','����� ���Ǳ� �Ÿ���', '2025-01-20', '2025-01-20', 'Y');

--------------------------------------------------------------------------
------------------------ BOARD ���� ---------------------------
--------------------------------------------------------------------

CREATE TABLE BOARD(
    BOARD_NO NUMBER PRIMARY KEY,
    BOARD_TITLE VARCHAR2(100) NOT NULL,
    BOARD_CONTENT VARCHAR2(4000) NOT NULL,
    BOARD_WRITER NUMBER,
    COUNT NUMBER DEFAULT 0,
    CREATE_DATE DATE DEFAULT SYSDATE,
    STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N')),
    FOREIGN KEY (BOARD_WRITER) REFERENCES MEMBER(USER_NO)
);

CREATE SEQUENCE SEQ_BNO
NOCACHE;

INSERT INTO BOARD
VALUES(SEQ_BNO.NEXTVAL, 'ù��° �Խ��� ���� ����', '�ȳ��ϼ���. 1 �Խ����Դϴ�.', 1, DEFAULT, '20241231', DEFAULT);

INSERT INTO BOARD
VALUES(SEQ_BNO.NEXTVAL, '�ι�° �Խ��� ���� ����', '�ȳ��ϼ���. 2 �Խ����Դϴ�.', 1, DEFAULT, '20241231', DEFAULT);

INSERT INTO BOARD
VALUES(SEQ_BNO.NEXTVAL, '���� �Խ��� �����Ұ�', '�ȳ��ϼ���. 3 �Խ����Դϴ�.', 1, DEFAULT, '20250106', DEFAULT);

INSERT INTO BOARD
VALUES(SEQ_BNO.NEXTVAL, '�ȳ� ���̹�Ƽ���� ó������?', '�ȳ��ϼ���. ù �Խ����Դϴ�.', 2, DEFAULT, '20250120', DEFAULT);

INSERT INTO BOARD
VALUES(SEQ_BNO.NEXTVAL, '��� ����', '�ݰ����ϴ�', 1, DEFAULT, '20250226', DEFAULT);

INSERT INTO BOARD
VALUES(SEQ_BNO.NEXTVAL, '����¡ ó��...�ؾ���...���õ����� ���L', '�ȳ��Ͻʴϱ�?', 1, DEFAULT, '20250228', DEFAULT);

INSERT INTO BOARD
VALUES(SEQ_BNO.NEXTVAL, '�Խ��� ����', '�ȳ��ϼ���. �Խ����Դϴ�.', 1, DEFAULT, '20250307', DEFAULT);

INSERT INTO BOARD
VALUES(SEQ_BNO.NEXTVAL, '���õ�����', '�ȳ��ϼ���. �����̿���', 2, DEFAULT, '20250314', DEFAULT);

INSERT INTO BOARD
VALUES(SEQ_BNO.NEXTVAL, '��� ȭ����!!', 'ȭ�����սô�', 1, DEFAULT, '20250317', DEFAULT);

INSERT INTO BOARD
VALUES(SEQ_BNO.NEXTVAL, '��������!!', '�ȳ�~ ��� ������~', 1, DEFAULT, '20250318', DEFAULT);

INSERT INTO BOARD
VALUES(SEQ_BNO.NEXTVAL, '�������� ���ǴٿԾ�', '�����ӿ�ũ ����?', 1, DEFAULT, '20250321', DEFAULT);

INSERT INTO BOARD
VALUES(SEQ_BNO.NEXTVAL, '�����ϰڽ��ϴ�', '�����غ����?', 2, DEFAULT, '20250321', DEFAULT);

INSERT INTO BOARD
VALUES(SEQ_BNO.NEXTVAL, '�������Խ����Դϴ�.', '�ȳ��ϼ��� �������Խ����Դϴ�.', 1, DEFAULT, '20250408', DEFAULT);

--------------------------------------------------------------------------
------------------------ REPLY ���� ---------------------------
--------------------------------------------------------------------

CREATE TABLE REPLY(
    REPLY_NO NUMBER PRIMARY KEY,
    REPLY_CONTENT VARCHAR2(400),
    REF_BNO NUMBER,
    REPLY_WRITER NUMBER,
    CREATE_DATE DATE DEFAULT SYSDATE,
    STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN ('Y','N')),
    FOREIGN KEY (REF_BNO) REFERENCES BOARD(BOARD_NO),
    FOREIGN KEY (REPLY_WRITER) REFERENCES MEMBER(USER_NO)
);

CREATE SEQUENCE SEQ_RNO
NOCACHE;

INSERT INTO REPLY
VALUES(SEQ_RNO.NEXTVAL, 'ù���� ����Դϴ�.', 1, 2, '20250120', DEFAULT);

INSERT INTO REPLY
VALUES(SEQ_RNO.NEXTVAL, 'ù���� ����Դϴ�.', 13, 2, '20250121', DEFAULT);

INSERT INTO REPLY
VALUES(SEQ_RNO.NEXTVAL, '�ι��� ����Դϴ�.', 13, 2, '20250122', DEFAULT);

INSERT INTO REPLY
VALUES(SEQ_RNO.NEXTVAL, '������ ����Դϴ�.', 13, 2, '20250123', DEFAULT);

COMMIT;
