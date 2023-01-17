package dev.mvc.notice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoticeCont {
  @Autowired
  @Qualifier("dev.mvc.notice.NoticeProc") 
  private NoticeProcInter surveyProc;
  
  public NoticeCont() {
    System.out.println("-> SurveyCont created.");
  }
  
  // ?���? ?��
  // http://localhost:9093/survey/create.do
  @RequestMapping(value="/survey/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("-> create()");
    
    ModelAndView mav = new ModelAndView();
//    JSP View path
//    spring.mvc.view.prefix=/WEB-INF/views/
//    spring.mvc.view.suffix=.jsp
    mav.setViewName("/survey/create"); // /webapp/WEB-INF/views/survey/create.jsp
    
    return mav;
  }
  
  // ?���? 처리
  // <FORM name='frm' method='POST' action='./create.do'>
  // http://localhost:9093/survey/create.do
  @RequestMapping(value="/survey/create.do", method = RequestMethod.POST)
  public ModelAndView create(NoticeVO surveyVO) {
    
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.surveyProc.create(surveyVO);
    
    if (cnt == 1) {
      mav.addObject("code", "create_success");
      // request.setAttribute("code", "create_success");
    } else {
      mav.addObject("code", "create_fail");
    }
    
    mav.addObject("cnt", cnt);
    
    if (cnt > 0) { // ?��?�� ?���?
      System.out.println("?��?�� ?���?");
      mav.setViewName("redirect:/survey/list_all.do"); // 콘트롤러?�� 주소 ?���?, ?��?�� ?��?��
      // mav.setViewName("/survey/list_all"); // /webapp/WEB-INF/views/survey/list_all.jsp X
    } else { // ?���? ?��?��
      System.out.println("?���? ?��?��");
//      mav.setViewName("/survey/msg"); // /webapp/WEB-INF/views/survey/msg.jsp      
    }
    
    return mav;
  }
  
  /**
   * 모든 ?��코드 목록, http://localhost:9093/survey/list_all.do
   * @return
   */
  @RequestMapping(value="/survey/list_all.do", method=RequestMethod.GET)
  public ModelAndView list_all() {
    ModelAndView mav = new ModelAndView();
    
    ArrayList<NoticeVO> list = this.surveyProc.list_all();
    mav.addObject("list", list);
    // request.setAttribute("list", list);
    
    // System.out.println("-> list size: " + list.size());
    
    mav.setViewName("/survey/list_all"); // /webapp/WEB-INF/views/survey/list_all.jsp
    
    return mav;
  }

  /**
   * ?��?��?��, http://localhost:9093/survey/read_update.do?surveyno=1
   * @return
   */
  @RequestMapping(value="/survey/read_update.do", method=RequestMethod.GET)
  public ModelAndView read_update(int surveyno) {
    // System.out.println("-> surveyno: " + surveyno);
    
    ModelAndView mav = new ModelAndView();
    
    ArrayList<NoticeVO> list = this.surveyProc.list_all();
    mav.addObject("list", list);
    
    NoticeVO surveyVO = this.surveyProc.read(surveyno);
    mav.addObject("surveyVO", surveyVO);
    
    mav.setViewName("/survey/read_update"); // /webapp/WEB-INF/views/survey/read_update.jsp
    
    return mav;
  }
  
  // ?��?�� 처리
  // <FORM name='frm' method='POST' action='./read_update.do'>
  // http://localhost:9093/survey/read_update.do
  @RequestMapping(value="/survey/read_update.do", method = RequestMethod.POST)
  public ModelAndView read_update(NoticeVO surveyVO) {

    
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.surveyProc.update(surveyVO);
    
    if (cnt == 0) {
      mav.addObject("code", "update_fail");
    }
    
    mav.addObject("cnt", cnt);
    
    if (cnt > 0) { // ?��?�� ?���?
      // System.out.println("?��?�� ?��?��");
      mav.setViewName("redirect:/survey/list_all.do"); // 콘트롤러?�� 주소 ?���?, ?��?�� ?��?��
//      // mav.setViewName("/survey/list_all"); // /webapp/WEB-INF/views/survey/list_all.jsp X
    } else { // ?���? ?��?��
      // System.out.println("?��?�� ?��?��");
      mav.setViewName("/survey/msg"); // /webapp/WEB-INF/views/survey/msg.jsp      
    }
    
    return mav;
  }
  
  /**
   * ?��?��?��, http://localhost:9093/survey/read_delete.do?surveyno=1
   * @return
   */
  @RequestMapping(value="/survey/read_delete.do", method=RequestMethod.GET)
  public ModelAndView read_delete(int surveyno) {
    // System.out.println("-> surveyno: " + surveyno);
    
    ModelAndView mav = new ModelAndView();
    
    ArrayList<NoticeVO> list = this.surveyProc.list_all();
    mav.addObject("list", list);
    
    NoticeVO surveyVO = this.surveyProc.read(surveyno);
    mav.addObject("surveyVO", surveyVO);
    
    mav.setViewName("/survey/read_delete"); // /webapp/WEB-INF/views/survey/read_delete.jsp
    
    return mav;
  }
  
  // ?��?�� 처리
  // <FORM name='frm' method='POST' action='./read_delete.do'>
  // http://localhost:9093/survey/read_delete.do
  @RequestMapping(value="/survey/read_delete.do", method = RequestMethod.POST)
  public ModelAndView delete(int surveyno) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.surveyProc.delete(surveyno);
    
    if (cnt == 0) {
      mav.addObject("code", "delete_fail");
    }
    
    mav.addObject("cnt", cnt);
    
    if (cnt > 0) { // ?��?�� ?��?��
      mav.setViewName("redirect:/survey/list_all.do"); // 콘트롤러?�� 주소 ?���?, ?��?�� ?��?��
      // mav.setViewName("/survey/list_all"); // /webapp/WEB-INF/views/survey/list_all.jsp X
    } else { // ?���? ?��?��
      mav.setViewName("/survey/msg"); // /webapp/WEB-INF/views/survey/msg.jsp      
    }
    
    return mav;
  }



  
  }







