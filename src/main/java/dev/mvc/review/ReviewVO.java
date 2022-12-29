package dev.mvc.review;

/*
CREATE TABLE review(
        reviewno                            NUMBER(10)         NOT NULL         PRIMARY KEY,
        frno                              NUMBER(10)     NOT NULL , -- FK
        memberno                                NUMBER(10)         NOT NULL , -- FK
        RATING                                 NUMBER(2)         NOT NULL,
        review_content                         VARCHAR2(500)                  NOT NULL,
  FOREIGN KEY (frno) REFERENCES frcontents (frno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);
 */
public class ReviewVO {
  /** 리뷰 번호 */
  private int reviewno;  
  /** 맛집 번호 */
  private int frno; 
  /** 회원 번호 */
  private int memberno; 
  /** 평점 */
  private int RATING; 
  /** 리뷰 내용 */
  private String review_content;
  
  public int getReviewno() {
    return reviewno;
  }
  public void setReviewno(int reviewno) {
    this.reviewno = reviewno;
  }
  public int getFrno() {
    return frno;
  }
  public void setFrno(int frno) {
    this.frno = frno;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public int getRATING() {
    return RATING;
  }
  public void setRATING(int rATING) {
    RATING = rATING;
  }
  public String getReview_content() {
    return review_content;
  }
  public void setReview_content(String review_content) {
    this.review_content = review_content;
  }
  
  
  
}


