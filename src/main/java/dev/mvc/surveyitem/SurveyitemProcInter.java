package dev.mvc.surveyitem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface SurveyitemProcInter {
  /**
   * 등록
   * @param surveyitemVO
   * @return
   */
  public int create(SurveyitemVO surveyitemVO);
  
  /**
   * 전체 목록
   * <xmp><select id="list_by_surveyno" resultType="dev.mvc.surveyitem.SurveyitemVO" parameterType="int"></xmp>
   * @return 레코드 전체 목록
   */
  public ArrayList<SurveyitemVO> list_by_surveyno(int surveyno);
}
