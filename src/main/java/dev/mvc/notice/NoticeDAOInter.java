package dev.mvc.notice;

import java.util.ArrayList;

// Spring framework?��?�� ?��공하?�� 기능
// application.properties�? ?��?��?�� DBMS ?���?/?��?�� ?��?�� 구현
// try ~ catch ?��?�� 구현
// noticeVO 값을 SQL?�� ?��?�� ?��?�� �? ?��?��
// return ?��?�� 구현
public interface NoticeDAOInter {
  /**
   * ?���?
   * <xmp><insert id="create" parameterType="dev.mvc.notice.noticeVO"></xmp>
   * @param noticeVO
   * @return ?��록한 ?��코드 개수
   */
  public int create(NoticeVO noticeVO);
  
  /**
   * ?���? 목록
   * <xmp><select id="list_all" resultType="dev.mvc.notice.noticeVO"></xmp>
   * @return ?��코드 ?���? 목록
   */
  public ArrayList<NoticeVO> list_all();
  
  /**
   * 조회
   * <xmp><select id="read" resultType="dev.mvc.notice.noticeVO" parameterType="int"></xmp>
   * @param noticeno
   * @return
   */
  public NoticeVO read(int noticeno);
  
  /**
   * ?��?��
   * <xmp><update id="update" parameterType="dev.mvc.notice.noticeVO"></xmp>
   * @param noticeVO
   * @return ?��?��?�� ?��코드 �??��
   */
  public int update(NoticeVO noticeVO);
  
  /**
   * ?��?��
   * @param noticeno
   * @return ?��?��?�� ?��코드 ?��
   */
  public int delete(int noticeno);
  
}


