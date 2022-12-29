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

COMMENT ON TABLE review is '리뷰';
COMMENT ON COLUMN review.reviewno is '리뷰 번호';
COMMENT ON COLUMN review.frno is '맛집 번호';
COMMENT ON COLUMN review.memberno is '회원 번호';
COMMENT ON COLUMN review.RATING is '평점';
COMMENT ON COLUMN review.review_content is '리뷰 내용';


DROP SEQUENCE review_seq;

CREATE SEQUENCE review_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지

-- 등록 화면 유형 1: 커뮤니티(공지사항, 게시판, 자료실, 갤러리,  Q/A...)글 등록
INSERT INTO review(reviewno, frno, memberno, RATING, review_content)
VALUES(review_seq.nextval, 5, 1, 5, '맛있어요');

INSERT INTO review(reviewno, frno, memberno, RATING, review_content)
VALUES(review_seq.nextval, 5, 1, 1, '별로에요');

-- 맛집 리뷰 조회
SELECT reviewno, frno, memberno, RATING, review_content
FROM review
WHERE frno=5

-- 맛집 리뷰 조회(평점 낮은순)
SELECT reviewno, frno, memberno, RATING, review_content
FROM review
WHERE frno=5
ORDER BY RATING ASC;

-- 맛집 리뷰 조회(평점 높은순)
SELECT reviewno, frno, memberno, RATING, review_content
FROM review
WHERE frno=5
ORDER BY RATING DESC;

-- 모든 레코드 삭제
DELETE FROM review;
commit;

-- 맛집 리뷰 삭제
DELETE FROM review
WHERE frno = 5;
commit;

-- 리뷰 삭제(하나만)
DELETE FROM review
WHERE reviewno = 3;
commit;




