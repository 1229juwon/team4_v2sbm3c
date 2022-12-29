package dev.mvc.recommend;

/*
CREATE TABLE recommend(
    recommendno                           NUMBER(10)     NOT NULL    PRIMARY KEY,
    part                              VARCHAR2(1)    DEFAULT 'A' NOT NULL,
    rdate                             DATE     NOT NULL,
        frno                            NUMBER(10)       NOT NULL,
        memberno                       NUMBER(10)       NOT NULL,
  FOREIGN KEY (frno) REFERENCES frcontents (frno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);
 */
public class RecommendVO {
  /** 추천 번호 */
  private int recommendno;  
  /** 추천 종류 */
  private String part;
  /** 추천 날짜 */
  private String rdate;
  /** 맛집번호 */
  private int frno;  
  /** 회원 번호 */
  private int memberno;
  public int getRecommendno() {
    return recommendno;
  }
  public void setRecommendno(int recommendno) {
    this.recommendno = recommendno;
  }
  public String getPart() {
    return part;
  }
  public void setPart(String part) {
    this.part = part;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
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
  
  
  
}


