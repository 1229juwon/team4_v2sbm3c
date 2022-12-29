/* Table Name: μΉ΄νκ³ λ¦¬ */
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
  START WITH 1              -- ?? λ²νΈ
  INCREMENT BY 1          -- μ¦κ?κ°?
  MAXVALUE 9999999999 -- μ΅λ?κ°?: 999999999 --> NUMBER(10) ???
  CACHE 2                       -- 2λ²μ? λ©λͺ¨λ¦¬μ?λ§? κ³μ°
  NOCYCLE;                     -- ?€? 1λΆ??° ??±?? κ²μ λ°©μ?
  
-- CREATE
INSERT INTO fcate(cateno, name, cnt, rdate, seqno, visible)
VALUES (fcate_seq.nextval, '³ͺ', 0, sysdate, 1, 'Y');

INSERT INTO fcate(cateno, name, cnt, rdate, seqno, visible)
VALUES (fcate_seq.nextval, '°‘', 0, sysdate, 2, 'Y');


commit;

-- SELECT λͺ©λ‘
-- PK μ»¬λΌ?? μ΅μ΄ ?±λ‘μ κ°μ΄ sequence???΄ κ³ μ ?¨.
SELECT cateno, name, cnt, rdate, udate, seqno, visible
FROM fcate
ORDER BY cateno ASC;

-- SELECT μ‘°ν
SELECT cateno, name, cnt, rdate, udate, seqno, visible
FROM fcate
WHERE cateno = 1;

-- UPDATE
-- μ΅μ΄ ?±λ‘? ? μ§? ? μ§?, ??  ? μ§? μΆκ?
UPDATE cate
SET name='κ³ μ ', seqno=1, udate=sysdate
WHERE cateno=1;

commit;

-- DELETE
DELETE FROM cate
WHERE cateno=5;

commit;

-- λͺ¨λ  ? μ½λ ?­? 
DELETE FROM cate;
commit;

-- COUNT(*)
SELECT COUNT(*) as cnt
FROM cate;

-- seqno μΆλ ₯ ?? κΈ°μ? λͺ©λ‘
SELECT cateno, name, cnt, rdate, udate, seqno
FROM fcate
ORDER BY seqno ASC;

-- μΆλ ₯ ?? ?¬λ¦?(??₯, 10 ?± -> 1 ?±), seqno: 10 -> 1
UPDATE cate
SET seqno = seqno - 1
WHERE cateno = 1;

commit;

-- μΆλ ₯ ?? ?΄λ¦?(??₯, 1 ?± -> 10 ?±), seqno: 1 -> 10
UPDATE cate
SET seqno = seqno + 1
WHERE cateno = 1;

commit;

-- μΆλ ₯ λͺ¨λ? λ³?κ²?
UPDATE cate
SET visible = 'Y'
WHERE cateno=1;

SELECT * FROM cate;

UPDATE cate
SET visible = 'N'
WHERE cateno=1;

SELECT * FROM cate;

commit;

-- seqno μΆλ ₯ ?? κΈ°μ? λͺ©λ‘
SELECT cateno, name, cnt, rdate, udate, seqno, visible
FROM cate
ORDER BY seqno ASC;

-- visible?΄ 'Y'?Έ μΉ΄νκ³ λ¦¬ μΆλ ₯?κΈ?
SELECT cateno, name, cnt, rdate, udate, seqno, visible
FROM cate
WHERE visible='Y'
ORDER BY seqno ASC;

-- κΈ??? μ¦κ?
UPDATE cate
SET cnt = cnt + 1
WHERE cateno=1;

commit;

SELECT * FROM cate;

-- κΈ??? κ°μ
UPDATE cate
SET cnt = cnt - 1
WHERE cateno=1;

commit;

SELECT * FROM cate;


