package dev.mvc.notice;

import java.util.ArrayList;

// ?°?´?„° ì²˜ë¦¬ ê´?? ¨ ?•Œê³ ë¦¬ì¦? êµ¬í˜„, ?‚¬ì¹™ì—°?‚°, ? œ?–´ë¬?
public interface NoticeProcInter {
  /**
   * ?“±ë¡?
   * @param surveyVO
   * @return ?“±ë¡í•œ ? ˆì½”ë“œ ê°œìˆ˜
   */
  public int create(NoticeVO noticeVO);
  
  /**
   * ? „ì²? ëª©ë¡
   * @return ? ˆì½”ë“œ ? „ì²? ëª©ë¡
   */
  public ArrayList<NoticeVO> list_all();
  
  /**
   * ì¡°íšŒ
   * @param surveyno
   * @return
   */
  public NoticeVO read(int noticeno);
  
  /**
   * ?ˆ˜? •
   * @param surveyVO
   * @return ?ˆ˜? •?œ ? ˆì½”ë“œ ê°??ˆ˜
   */
  public int update(NoticeVO noticeVO);
  
  /**
   * ?‚­? œ
   * @param surveyno
   * @return ?‚­? œ?œ ? ˆì½”ë“œ ?ˆ˜
   */
  public int delete(int noticeno);
  

}


