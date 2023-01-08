/**********************************/
/* Table Name: 추천 */
/**********************************/
DROP TABLE recommend;

CREATE TABLE recommend(
		recommendno                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		part                          		VARCHAR2(1)		 DEFAULT 'A' NOT NULL,
		rdate                         		DATE		 NOT NULL,
        frno                            NUMBER(10)       NOT NULL,
        memberno                       NUMBER(10)       NOT NULL,
  FOREIGN KEY (frno) REFERENCES frcontents (frno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE recommend is '추천';
COMMENT ON COLUMN recommend.recommendno is '추천 번호';
COMMENT ON COLUMN recommend.part is '추천 종류';
COMMENT ON COLUMN recommend.rdate is '추천 날짜';
COMMENT ON COLUMN recommend.frno is '맛집번호';
COMMENT ON COLUMN recommend.memberno is '회원번호';


-- SEQUENCE
DROP SEQUENCE recommend_seq;

CREATE SEQUENCE recommend_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 999999999 --> NUMBER(10) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
  
-- CREATE
INSERT INTO recommend(recommendno, part, rdate, frno, memberno)
VALUES (recommend_seq.nextval, 'B', sysdate, 9, 1);

INSERT INTO recommend(recommendno, part, rdate, frno, memberno)
VALUES (recommend_seq.nextval, 'A', sysdate, 9, 1);

commit;

-- SELECT 조회
SELECT recommendno, part, rdate, frno, memberno
FROM recommend
WHERE frno = 9;



