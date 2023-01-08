/**********************************/
/* Table Name: ��õ */
/**********************************/
DROP TABLE favorites;

CREATE TABLE favorites(
        frno                            NUMBER(10)       NOT NULL,
        memberno                       NUMBER(10)       NOT NULL,
  FOREIGN KEY (frno) REFERENCES frcontents (frno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON COLUMN recommend.frno is '������ȣ';
COMMENT ON COLUMN recommend.memberno is 'ȸ����ȣ';


-- SEQUENCE
--DROP SEQUENCE favorites_seq;

--CREATE SEQUENCE favorites_seq
--  START WITH 1              -- ���� ��ȣ
--  INCREMENT BY 1          -- ������
--  MAXVALUE 9999999999 -- �ִ밪: 999999999 --> NUMBER(10) ����
--  CACHE 2                       -- 2���� �޸𸮿����� ���
--  NOCYCLE;                     -- �ٽ� 1���� �����Ǵ� ���� ����
  
-- CREATE
INSERT INTO favorites(frno, memberno)
VALUES (9, 1);

INSERT INTO favorites(frno, memberno)
VALUES (9, 1);

commit;

-- SELECT ��ȸ
SELECT frno, memberno
FROM favorites
WHERE frno = 9;


