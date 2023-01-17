package dev.mvc.notice;

import java.util.ArrayList;

// ?��?��?�� 처리 �??�� ?��고리�? 구현, ?��칙연?��, ?��?���?
public interface NoticeProcInter {
  /**
   * ?���?
   * @param surveyVO
   * @return ?��록한 ?��코드 개수
   */
  public int create(NoticeVO noticeVO);
  
  /**
   * ?���? 목록
   * @return ?��코드 ?���? 목록
   */
  public ArrayList<NoticeVO> list_all();
  
  /**
   * 조회
   * @param surveyno
   * @return
   */
  public NoticeVO read(int noticeno);
  
  /**
   * ?��?��
   * @param surveyVO
   * @return ?��?��?�� ?��코드 �??��
   */
  public int update(NoticeVO noticeVO);
  
  /**
   * ?��?��
   * @param surveyno
   * @return ?��?��?�� ?��코드 ?��
   */
  public int delete(int noticeno);
  

}


