DROP TABLE MEMBER;
DROP TABLE MEMBER CASCADE CONSTRAINTS;

CREATE TABLE MEMBER (
  memberno NUMBER(10) NOT NULL, 
  id         VARCHAR(20)   NOT NULL UNIQUE, 
  password   VARCHAR(20)   NOT NULL, 
  mname      VARCHAR(10)   NOT NULL, 
  phonenum   VARCHAR(15)   NOT NULL,
  homenum    VARCHAR(15)        NULL, 
  address    VARCHAR(80)       NULL, 
  nickname   VARCHAR(50)       NULL, 
  mdate      DATE             NOT NULL, 
  grade    NUMBER(2)     NOT NULL, 
  PRIMARY KEY (memberno)           
);

COMMENT ON TABLE MEMBER is 'ȸ��';
COMMENT ON COLUMN MEMBER.MEMBERNO is 'ȸ����ȣ';
COMMENT ON COLUMN MEMBER.ID is '���̵�';
COMMENT ON COLUMN MEMBER.PASSWORD is '��й�ȣ';
COMMENT ON COLUMN MEMBER.MNAME is '����';
COMMENT ON COLUMN MEMBER.PHONENUM is '��ȭ��ȣ';
COMMENT ON COLUMN MEMBER.HOMENUM is '�����ȣ';
COMMENT ON COLUMN MEMBER.ADDRESS is '�ּ�';
COMMENT ON COLUMN MEMBER.NICKNAME is '�г���';
COMMENT ON COLUMN MEMBER.MDATE is '������';
COMMENT ON COLUMN MEMBER.GRADE is '���';

DROP SEQUENCE MEMBER_seq;

CREATE SEQUENCE MEMBER_seq
  START WITH 1              -- ���� ��ȣ
  INCREMENT BY 1          -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(7) ����
  CACHE 2                       -- 2���� �޸𸮿����� ���
  NOCYCLE;                     -- �ٽ� 1���� �����Ǵ� ���� ����
 
 
1. ���
 
1) id �ߺ� Ȯ��(null �Ͻ� count �ȵ�)
SELECT COUNT(id)
FROM MEMBER
WHERE id='user1';

SELECT COUNT(id) as cnt
FROM MEMBER
WHERE id='user1';

-- ȸ�� ����
INSERT INTO member(memberno, id, password, mname, phonenum, homenum,address, nickname, mdate, grade)
VALUES (member_seq.nextval, 'user1', '1234', 'juwon', '000-0000-0000', '00-0000-0000',
             '�����', '�ֿ�', sysdate, 1);

COMMIT;

 
2. ���
- �˻��� ���� �ʴ� ���, ��ü ��� ���
 
SELECT memberno, id, password, mname, phonenum, homenum, address, nickname, mdate, grade
FROM member
ORDER BY grade ASC, id ASC;
     
     
3. ��ȸ
 
SELECT memberno, id, password, mname, phonenum, homenum, address, nickname, mdate, grade
FROM member
WHERE memberno = 1;

SELECT memberno, id, password, mname, phonenum, homenum, address, nickname, mdate, grade
FROM member
WHERE id = 'user1';
 
    
4. ����
UPDATE member 

SET memberno = 2, id = '', password='', mname='', phonenum = 000-0000-0000, homenum= 00-0000-0000, address='', nickname='', mdate=0000-00-00, grade=0
WHERE memberno=1;


UPDATE member SET password='123123' 
WHERE memberno=2;

commit;
 
5. ����
1) ��� ����
DELETE FROM member;
 
2) Ư�� ȸ�� ����
DELETE FROM MEMBER
WHERE memberno=1;
commit;
 