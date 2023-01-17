package dev.mvc.notice;

import java.util.ArrayList;

// Spring framework?? ? κ³΅ν? κΈ°λ₯
// application.propertiesλ₯? ?½?΄? DBMS ?°κ²?/?΄?  ?? κ΅¬ν
// try ~ catch ?? κ΅¬ν
// noticeVO κ°μ SQL? ?? ? ?¬ λ°? ?€?
// return ?? κ΅¬ν
public interface NoticeDAOInter {
  /**
   * ?±λ‘?
   * <xmp><insert id="create" parameterType="dev.mvc.notice.noticeVO"></xmp>
   * @param noticeVO
   * @return ?±λ‘ν ? μ½λ κ°μ
   */
  public int create(NoticeVO noticeVO);
  
  /**
   * ? μ²? λͺ©λ‘
   * <xmp><select id="list_all" resultType="dev.mvc.notice.noticeVO"></xmp>
   * @return ? μ½λ ? μ²? λͺ©λ‘
   */
  public ArrayList<NoticeVO> list_all();
  
  /**
   * μ‘°ν
   * <xmp><select id="read" resultType="dev.mvc.notice.noticeVO" parameterType="int"></xmp>
   * @param noticeno
   * @return
   */
  public NoticeVO read(int noticeno);
  
  /**
   * ?? 
   * <xmp><update id="update" parameterType="dev.mvc.notice.noticeVO"></xmp>
   * @param noticeVO
   * @return ?? ? ? μ½λ κ°??
   */
  public int update(NoticeVO noticeVO);
  
  /**
   * ?­? 
   * @param noticeno
   * @return ?­? ? ? μ½λ ?
   */
  public int delete(int noticeno);
  
}


