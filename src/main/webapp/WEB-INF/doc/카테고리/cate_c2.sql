/* Table Name: ì¹´í…Œê³ ë¦¬ */
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
  START WITH 1              -- ?‹œ?‘ ë²ˆí˜¸
  INCREMENT BY 1          -- ì¦ê?ê°?
  MAXVALUE 9999999999 -- ìµœë?ê°?: 999999999 --> NUMBER(10) ???‘
  CACHE 2                       -- 2ë²ˆì? ë©”ëª¨ë¦¬ì—?„œë§? ê³„ì‚°
  NOCYCLE;                     -- ?‹¤?‹œ 1ë¶??„° ?ƒ?„±?˜?Š” ê²ƒì„ ë°©ì?
  
-- CREATE
INSERT INTO fcate(cateno, name, cnt, rdate, seqno, visible)
VALUES (fcate_seq.nextval, '³ª', 0, sysdate, 1, 'Y');

INSERT INTO fcate(cateno, name, cnt, rdate, seqno, visible)
VALUES (fcate_seq.nextval, '°¡', 0, sysdate, 2, 'Y');


commit;

-- SELECT ëª©ë¡
-- PK ì»¬ëŸ¼?? ìµœì´ˆ ?“±ë¡ì‹œ ê°’ì´ sequence?—?˜?•´ ê³ ì •?¨.
SELECT cateno, name, cnt, rdate, udate, seqno, visible
FROM fcate
ORDER BY cateno ASC;

-- SELECT ì¡°íšŒ
SELECT cateno, name, cnt, rdate, udate, seqno, visible
FROM fcate
WHERE cateno = 1;

-- UPDATE
-- ìµœì´ˆ ?“±ë¡? ?‚ ì§? ?œ ì§?, ?ˆ˜? • ?‚ ì§? ì¶”ê?
UPDATE cate
SET name='ê³ ì „', seqno=1, udate=sysdate
WHERE cateno=1;

commit;

-- DELETE
DELETE FROM cate
WHERE cateno=5;

commit;

-- ëª¨ë“  ? ˆì½”ë“œ ?‚­? œ
DELETE FROM cate;
commit;

-- COUNT(*)
SELECT COUNT(*) as cnt
FROM cate;

-- seqno ì¶œë ¥ ?ˆœ?„œ ê¸°ì? ëª©ë¡
SELECT cateno, name, cnt, rdate, udate, seqno
FROM fcate
ORDER BY seqno ASC;

-- ì¶œë ¥ ?ˆœ?„œ ?˜¬ë¦?(?ƒ?–¥, 10 ?“± -> 1 ?“±), seqno: 10 -> 1
UPDATE cate
SET seqno = seqno - 1
WHERE cateno = 1;

commit;

-- ì¶œë ¥ ?ˆœ?„œ ?‚´ë¦?(?•˜?–¥, 1 ?“± -> 10 ?“±), seqno: 1 -> 10
UPDATE cate
SET seqno = seqno + 1
WHERE cateno = 1;

commit;

-- ì¶œë ¥ ëª¨ë“œ?˜ ë³?ê²?
UPDATE cate
SET visible = 'Y'
WHERE cateno=1;

SELECT * FROM cate;

UPDATE cate
SET visible = 'N'
WHERE cateno=1;

SELECT * FROM cate;

commit;

-- seqno ì¶œë ¥ ?ˆœ?„œ ê¸°ì? ëª©ë¡
SELECT cateno, name, cnt, rdate, udate, seqno, visible
FROM cate
ORDER BY seqno ASC;

-- visible?´ 'Y'?¸ ì¹´í…Œê³ ë¦¬ ì¶œë ¥?•˜ê¸?
SELECT cateno, name, cnt, rdate, udate, seqno, visible
FROM cate
WHERE visible='Y'
ORDER BY seqno ASC;

-- ê¸??ˆ˜?˜ ì¦ê?
UPDATE cate
SET cnt = cnt + 1
WHERE cateno=1;

commit;

SELECT * FROM cate;

-- ê¸??ˆ˜?˜ ê°ì†Œ
UPDATE cate
SET cnt = cnt - 1
WHERE cateno=1;

commit;

SELECT * FROM cate;


