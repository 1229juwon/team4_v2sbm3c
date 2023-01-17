package dev.mvc.notice;

import java.util.ArrayList;

// ?°?΄?° μ²λ¦¬ κ΄?? ¨ ?κ³ λ¦¬μ¦? κ΅¬ν, ?¬μΉμ°?°, ? ?΄λ¬?
public interface NoticeProcInter {
  /**
   * ?±λ‘?
   * @param surveyVO
   * @return ?±λ‘ν ? μ½λ κ°μ
   */
  public int create(NoticeVO noticeVO);
  
  /**
   * ? μ²? λͺ©λ‘
   * @return ? μ½λ ? μ²? λͺ©λ‘
   */
  public ArrayList<NoticeVO> list_all();
  
  /**
   * μ‘°ν
   * @param surveyno
   * @return
   */
  public NoticeVO read(int noticeno);
  
  /**
   * ?? 
   * @param surveyVO
   * @return ?? ? ? μ½λ κ°??
   */
  public int update(NoticeVO noticeVO);
  
  /**
   * ?­? 
   * @param surveyno
   * @return ?­? ? ? μ½λ ?
   */
  public int delete(int noticeno);
  

}


