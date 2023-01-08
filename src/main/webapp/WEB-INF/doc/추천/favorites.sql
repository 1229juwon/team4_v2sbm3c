/**********************************/
/* Table Name: 추천 */
/**********************************/
DROP TABLE favorites;

CREATE TABLE favorites(
        frno                            NUMBER(10)       NOT NULL,
        memberno                       NUMBER(10)       NOT NULL,
  FOREIGN KEY (frno) REFERENCES frcontents (frno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON COLUMN recommend.frno is '맛집번호';
COMMENT ON COLUMN recommend.memberno is '회원번호';


-- SEQUENCE
--DROP SEQUENCE favorites_seq;

--CREATE SEQUENCE favorites_seq
--  START WITH 1              -- 시작 번호
--  INCREMENT BY 1          -- 증가값
--  MAXVALUE 9999999999 -- 최대값: 999999999 --> NUMBER(10) 대응
--  CACHE 2                       -- 2번은 메모리에서만 계산
--  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
  
-- CREATE
INSERT INTO favorites(frno, memberno)
VALUES (9, 1);

INSERT INTO favorites(frno, memberno)
VALUES (9, 1);

commit;

-- SELECT 조회
SELECT frno, memberno
FROM favorites
WHERE frno = 9;


