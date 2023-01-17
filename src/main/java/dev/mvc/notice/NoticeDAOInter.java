package dev.mvc.notice;

import java.util.ArrayList;

// Spring framework?—?„œ ? œê³µí•˜?Š” ê¸°ëŠ¥
// application.propertiesë¥? ?½?–´?„œ DBMS ?—°ê²?/?•´? œ ??™ êµ¬í˜„
// try ~ catch ??™ êµ¬í˜„
// noticeVO ê°’ì„ SQL?— ??™ ? „?‹¬ ë°? ?‹¤?–‰
// return ??™ êµ¬í˜„
public interface NoticeDAOInter {
  /**
   * ?“±ë¡?
   * <xmp><insert id="create" parameterType="dev.mvc.notice.noticeVO"></xmp>
   * @param noticeVO
   * @return ?“±ë¡í•œ ? ˆì½”ë“œ ê°œìˆ˜
   */
  public int create(NoticeVO noticeVO);
  
  /**
   * ? „ì²? ëª©ë¡
   * <xmp><select id="list_all" resultType="dev.mvc.notice.noticeVO"></xmp>
   * @return ? ˆì½”ë“œ ? „ì²? ëª©ë¡
   */
  public ArrayList<NoticeVO> list_all();
  
  /**
   * ì¡°íšŒ
   * <xmp><select id="read" resultType="dev.mvc.notice.noticeVO" parameterType="int"></xmp>
   * @param noticeno
   * @return
   */
  public NoticeVO read(int noticeno);
  
  /**
   * ?ˆ˜? •
   * <xmp><update id="update" parameterType="dev.mvc.notice.noticeVO"></xmp>
   * @param noticeVO
   * @return ?ˆ˜? •?œ ? ˆì½”ë“œ ê°??ˆ˜
   */
  public int update(NoticeVO noticeVO);
  
  /**
   * ?‚­? œ
   * @param noticeno
   * @return ?‚­? œ?œ ? ˆì½”ë“œ ?ˆ˜
   */
  public int delete(int noticeno);
  
}


