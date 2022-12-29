package dev.mvc.surveyitem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.surveyitem.SurveyitemVO;
import dev.mvc.tool.Tool;

@Component("dev.mvc.surveyitem.SurveyitemProc")
  public class SurveyitemProc implements SurveyitemProcInter {
    @Autowired
    private SurveyitemDAOInter surveyitemDAO;

    @Override
    public int create(SurveyitemVO surveyitemVO) {
      int cnt=this.surveyitemDAO.create(surveyitemVO);
      return cnt;
    }
    
    @Override
    public ArrayList<SurveyitemVO> list_by_surveyno(int surveyno) {
      ArrayList<SurveyitemVO> list = this.surveyitemDAO.list_by_surveyno(surveyno);
      
      for (int i=0; i<list.size(); i++) {
        SurveyitemVO surveyitemVO = list.get(i);
        
//        String title = surveyitemVO.getTitle();
//        String content = surveyitemVO.getContent();
        
//        title = Tool.convertChar(title);
//        content = Tool.convertChar(content);
        
//        surveyitemVO.setTitle(title);
//        surveyitemVO.setContent(content);
      }
      
      return list;
    }

}