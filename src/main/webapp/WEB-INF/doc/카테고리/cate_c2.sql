/* Table Name: 카테고리 */
/**********************************/

DROP TABLE cate CASCADE CONSTRAINTS;
DROP TABLE fcate;

CREATE TABLE fcate(
    cateno                            NUMBER(10)   NOT NULL    PRIMARY KEY,
    name                              VARCHAR2(30) NOT NULL,
    cnt                               NUMBER(7)  DEFAULT 0     NOT NULL,
    rdate                               DATE     NOT NULL,
    udate                             DATE     NULL,
        seqno                            NUMBER(10)   DEFAULT 0       NOT NULL,
        visible                            CHAR(1)      DEFAULT 'N'     NOT NULL -- Y, N
);

COMMENT ON TABLE fcate is '1';
COMMENT ON COLUMN fcate.cateno is '1';
COMMENT ON COLUMN fcate.name is '1';
COMMENT ON COLUMN fcate.cnt is '1';
COMMENT ON COLUMN fcate.rdate is '1';
COMMENT ON COLUMN fcate.udate is '1';
COMMENT ON COLUMN fcate.seqno is '1';
COMMENT ON COLUMN fcate.visible is '1';

-- SEQUENCE
DROP SEQUENCE fcate_seq;

CREATE SEQUENCE fcate_seq
  START WITH 1              -- ?��?�� 번호
  INCREMENT BY 1          -- 증�?�?
  MAXVALUE 9999999999 -- 최�?�?: 999999999 --> NUMBER(10) ???��
  CACHE 2                       -- 2번�? 메모리에?���? 계산
  NOCYCLE;                     -- ?��?�� 1�??�� ?��?��?��?�� 것을 방�?
  
-- CREATE
INSERT INTO fcate(cateno, name, cnt, rdate, seqno, visible)
VALUES (fcate_seq.nextval, '��', 0, sysdate, 1, 'Y');

INSERT INTO fcate(cateno, name, cnt, rdate, seqno, visible)
VALUES (fcate_seq.nextval, '��', 0, sysdate, 2, 'Y');


commit;

-- SELECT 목록
-- PK 컬럼?? 최초 ?��록시 값이 sequence?��?��?�� 고정?��.
SELECT cateno, name, cnt, rdate, udate, seqno, visible
FROM fcate
ORDER BY cateno ASC;

-- SELECT 조회
SELECT cateno, name, cnt, rdate, udate, seqno, visible
FROM fcate
WHERE cateno = 1;

-- UPDATE
-- 최초 ?���? ?���? ?���?, ?��?�� ?���? 추�?
UPDATE cate
SET name='고전', seqno=1, udate=sysdate
WHERE cateno=1;

commit;

-- DELETE
DELETE FROM cate
WHERE cateno=5;

commit;

-- 모든 ?��코드 ?��?��
DELETE FROM cate;
commit;

-- COUNT(*)
SELECT COUNT(*) as cnt
FROM cate;

-- seqno 출력 ?��?�� 기�? 목록
SELECT cateno, name, cnt, rdate, udate, seqno
FROM fcate
ORDER BY seqno ASC;

-- 출력 ?��?�� ?���?(?��?��, 10 ?�� -> 1 ?��), seqno: 10 -> 1
UPDATE cate
SET seqno = seqno - 1
WHERE cateno = 1;

commit;

-- 출력 ?��?�� ?���?(?��?��, 1 ?�� -> 10 ?��), seqno: 1 -> 10
UPDATE cate
SET seqno = seqno + 1
WHERE cateno = 1;

commit;

-- 출력 모드?�� �?�?
UPDATE cate
SET visible = 'Y'
WHERE cateno=1;

SELECT * FROM cate;

UPDATE cate
SET visible = 'N'
WHERE cateno=1;

SELECT * FROM cate;

commit;

-- seqno 출력 ?��?�� 기�? 목록
SELECT cateno, name, cnt, rdate, udate, seqno, visible
FROM cate
ORDER BY seqno ASC;

-- visible?�� 'Y'?�� 카테고리 출력?���?
SELECT cateno, name, cnt, rdate, udate, seqno, visible
FROM cate
WHERE visible='Y'
ORDER BY seqno ASC;

-- �??��?�� 증�?
UPDATE cate
SET cnt = cnt + 1
WHERE cateno=1;

commit;

SELECT * FROM cate;

-- �??��?�� 감소
UPDATE cate
SET cnt = cnt - 1
WHERE cateno=1;

commit;

SELECT * FROM cate;


