package dev.mvc.surveyitem;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.survey.SurveyProcInter;
import dev.mvc.survey.SurveyVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class SurveyitemCont {
  @Autowired
  @Qualifier("dev.mvc.survey.SurveyProc") 
  private SurveyProcInter surveyProc;
  
  @Autowired
  @Qualifier("dev.mvc.surveyitem.SurveyitemProc") 
  private SurveyitemProcInter surveyitemProc;
  
  public SurveyitemCont () {
    System.out.println("-> SurveyitemCont created.");
  }
  
  // 등록 폼
  // http://localhost:9093/surveyitem/create.do?surveyno=1
  @RequestMapping(value="/surveyitem/create.do", method = RequestMethod.GET)
  public ModelAndView create(int surveyno) {
//  public ModelAndView create(HttpServletRequest request,  int surveyno) {
    ModelAndView mav = new ModelAndView();

    SurveyVO surveyVO = this.surveyProc.read(surveyno);
    mav.addObject("surveyVO", surveyVO);
//    request.setAttribute("surveyVO", surveyVO);
    
    mav.setViewName("/surveyitem/create"); // /webapp/WEB-INF/views/surveyitem/create.jsp
    
    return mav;
  }
  
  /**
   * 등록 처리 http://localhost:9093/surveyitem/create.do
   * 
   * @return
   */
  @RequestMapping(value="/surveyitem/create.do", method = RequestMethod.POST)
  public ModelAndView create(SurveyitemVO surveyitemVO) {
    
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.surveyitemProc.create(surveyitemVO);
    
    if (cnt == 1) {
      mav.addObject("code", "create_success");
      // request.setAttribute("code", "create_success");
    } else {
      mav.addObject("code", "create_fail");
    }
    
    mav.addObject("cnt", cnt);
    
    if (cnt > 0) { // 정상 등록
      System.out.println("정상 등록");
      // mav.setViewName("redirect:/survey/list_all.do"); // 콘트롤러의 주소 요청, 자동 이동
      // mav.setViewName("/survey/list_all"); // /webapp/WEB-INF/views/survey/list_all.jsp X      
      mav.setViewName("/surveyitem/msg");
    } else { // 등록 실패
      System.out.println("등록 실패");
     // mav.setViewName("/survey/msg"); // /webapp/WEB-INF/views/survey/msg.jsp      
    }
    
    return mav;
  }
  
  /**
  * 모든 레코드 목록, http://localhost:9093/surveyitem/list_by_surveyno_search_paging.do?surveyno=1
  * @return
  */ 
  @RequestMapping(value="/surveyitem/list_by_surveyno.do", method=RequestMethod.GET)
  public ModelAndView list_by_surveyno(int surveyno) {
  ModelAndView mav = new ModelAndView();
 
  SurveyVO surveyVO = this.surveyProc.read(surveyno);
  mav.addObject("surveyVO", surveyVO);
 
  ArrayList<SurveyitemVO> list = this.surveyitemProc.list_by_surveyno(surveyno);
  mav.addObject("list", list);
  // request.setAttribute("list", list);
 
  // System.out.println("-> list size: " + list.size());
 
  mav.setViewName("/surveyitem/list_by_surveyno"); // /webapp/WEB-INF/views/surveyitem/list_by_surveyno.jsp
 
  return mav;
 }
  

}
