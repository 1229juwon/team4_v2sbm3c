DROP TABLE review CASCADE CONSTRAINTS;
DROP TABLE review;

CREATE TABLE review(
        reviewno                            NUMBER(10)         NOT NULL         PRIMARY KEY,
        frno                              NUMBER(10)     NOT NULL , -- FK
        memberno                                NUMBER(10)         NOT NULL , -- FK
        RATING                                 NUMBER(2)         NOT NULL,
        review_content                         VARCHAR2(500)                  NOT NULL,
  FOREIGN KEY (frno) REFERENCES frcontents (frno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE review is '����';
COMMENT ON COLUMN review.reviewno is '���� ��ȣ';
COMMENT ON COLUMN review.frno is '���� ��ȣ';
COMMENT ON COLUMN review.memberno is 'ȸ�� ��ȣ';
COMMENT ON COLUMN review.RATING is '����';
COMMENT ON COLUMN review.review_content is '���� ����';


DROP SEQUENCE review_seq;

CREATE SEQUENCE review_seq
  START WITH 1                -- ���� ��ȣ
  INCREMENT BY 1            -- ������
  MAXVALUE 9999999999  -- �ִ밪: 9999999999 --> NUMBER(10) ����
  CACHE 2                        -- 2���� �޸𸮿����� ���
  NOCYCLE;                      -- �ٽ� 1���� �����Ǵ� ���� ����

-- ��� ȭ�� ���� 1: Ŀ�´�Ƽ(��������, �Խ���, �ڷ��, ������,  Q/A...)�� ���
INSERT INTO review(reviewno, frno, memberno, RATING, review_content)
VALUES(review_seq.nextval, 5, 1, 5, '���־��');

INSERT INTO review(reviewno, frno, memberno, RATING, review_content)
VALUES(review_seq.nextval, 5, 1, 1, '���ο���');

-- ���� ���� ��ȸ
SELECT reviewno, frno, memberno, RATING, review_content
FROM review
WHERE frno=5

-- ���� ���� ��ȸ(���� ������)
SELECT reviewno, frno, memberno, RATING, review_content
FROM review
WHERE frno=5
ORDER BY RATING ASC;

-- ���� ���� ��ȸ(���� ������)
SELECT reviewno, frno, memberno, RATING, review_content
FROM review
WHERE frno=5
ORDER BY RATING DESC;

-- ��� ���ڵ� ����
DELETE FROM review;
commit;

-- ���� ���� ����
DELETE FROM review
WHERE frno = 5;
commit;

-- ���� ����(�ϳ���)
DELETE FROM review
WHERE reviewno = 3;
commit;




