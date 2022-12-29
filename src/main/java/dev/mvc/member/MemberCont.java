package dev.mvc.member;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.tool.MailTool;
 
@Controller
public class MemberCont {
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc = null;
  
  public MemberCont(){
  }
  	
  
  // http://localhost:9091/member/checkID.do?id=user1
  /**
  * ID �ߺ� üũ, JSON ���
  * @return
  */
  @ResponseBody
  @RequestMapping(value="/member/checkID.do", method=RequestMethod.GET ,
                           produces = "text/plain;charset=UTF-8" )
  public String checkID(String id) {
    int cnt = this.memberProc.checkID(id);
   
    JSONObject json = new JSONObject();
    json.put("cnt", cnt);
   
    return json.toString(); 
  }

  // http://localhost:9091/member/create.do
  /**
  * ��� ��
  * @return
  */
  @RequestMapping(value="/member/create.do", method=RequestMethod.GET )
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/create"); // /WEB-INF/views/member/create.jsp
   
    return mav; // forward
  }

  /**
   * ��� ó��
   * @param memberVO
   * @return
   */
  @RequestMapping(value="/member/create.do", method=RequestMethod.POST)
  public ModelAndView create(MemberVO memberVO){
    ModelAndView mav = new ModelAndView();
    
    System.out.println("id: " + memberVO.getId());
    System.out.println("address: " + memberVO.getAddress());
    
    memberVO.setGrade(15); // �⺻ ȸ�� ���� ��� 15 ����
    
    int cnt= memberProc.create(memberVO); // ȸ�� ���� ó��
    
    if (cnt == 1) {
      mav.addObject("code", "create_success");
      mav.addObject("mname", memberVO.getMname());  // ȫ�浿��(user4) ȸ�� ������ �����մϴ�.
      mav.addObject("id", memberVO.getId());
    } else {
      mav.addObject("code", "create_fail");
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
    
    mav.addObject("url", "/member/msg");  // /member/msg -> /member/msg.jsp
    
    mav.setViewName("redirect:/member/msg.do");

//    mav.addObject("code", "create_fail"); // ���� ���� test��
//    mav.addObject("cnt", 0);                 // ���� ���� test��
    
    return mav;
  }
  
  /**
   * ���ΰ�ħ ����, EL���� param���� ����
   * @return
   */
  @RequestMapping(value="/member/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav; // forward
  }

// ���� �ִ� ����
//  /**
//  * ��� ��� ����
//  * http://localhost:9091/member/list.do
//  * @param session
//  * @return
//  */
//  @RequestMapping(value="/member/list.do", method=RequestMethod.GET)
//  public ModelAndView list() {
//    ModelAndView mav = new ModelAndView();
//    
//    ArrayList<MemberVO> list = memberProc.list();
//    mav.addObject("list", list);
//
//    mav.setViewName("/member/list"); // /webapp/WEB-INF/views/member/list.jsp
//    
//    return mav;
//  }  

  // ���� ���͸�
  /**
  * ��� ��� ����
  * http://localhost:9091/member/list.do
  * @param session
  * @return
  */
  @RequestMapping(value="/member/list.do", method=RequestMethod.GET)
  public ModelAndView list(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if (this.adminProc.isAdmin(session)) {
      ArrayList<MemberVO> list = memberProc.list();
      mav.addObject("list", list);

      mav.setViewName("/member/list"); // /webapp/WEB-INF/views/member/list.jsp
      
    } else {
      mav.setViewName("/admin/login_need"); // /webapp/WEB-INF/views/admin/login_need.jsp
      
    }
    
    return mav;
  }  
  
//  /**
//   * ȸ�� ��ȸ
//   * http://localhost:9091/member/read.do?memberno=1
//   * @param memberno
//   * @return
//   */
//  @RequestMapping(value="/member/read.do", method=RequestMethod.GET)
//  public ModelAndView read(int memberno){
//    ModelAndView mav = new ModelAndView();
//    
//    MemberVO memberVO = this.memberProc.read(memberno);
//    mav.addObject("memberVO", memberVO);
//    mav.setViewName("/member/read"); // /member/read.jsp
//    
//    return mav; // forward
//  }
  
  /**
   * session ��ü�� �̿��� ȸ�� ��ȸ
   * http://localhost:9091/member/read.do
   * @param memberno
   * @return
   */
  @RequestMapping(value="/member/read.do", method=RequestMethod.GET)
  public ModelAndView read(HttpSession session){
    ModelAndView mav = new ModelAndView();
    
    if (this.memberProc.isMember(session)) {
      int memberno = (int)session.getAttribute("memberno");
      
      MemberVO memberVO = this.memberProc.read(memberno);
      mav.addObject("memberVO", memberVO);
      mav.setViewName("/member/read"); // /member/read.jsp
      
    } else {
      mav.setViewName("/member/login_need"); // /webapp/WEB-INF/views/member/login_need.jsp
      
    }
       
    return mav; // forward
  }
  

  /**
   * ȸ�� ���� ���� ó��
   * @param memberVO
   * @return
   */
  @RequestMapping(value="/member/update.do", method=RequestMethod.POST)
  public ModelAndView update(HttpSession session, MemberVO memberVO){
    
    int memberno = (int)session.getAttribute("memberno");
    memberVO.setMemberno(memberno);
    
    ModelAndView mav = new ModelAndView();
    
    // System.out.println("id: " + memberVO.getId());
    
    int cnt= memberProc.update(memberVO);
    
    if (cnt == 1) {
      mav.addObject("code", "update_success");
      mav.addObject("mname", memberVO.getMname());  // ȫ�浿��(user4) ȸ�� ������ �����߽��ϴ�.
      mav.addObject("id", memberVO.getId());
    } else {
      mav.addObject("code", "update_fail");
    }

    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
    mav.addObject("url", "/member/msg");  // /member/msg -> /member/msg.jsp
    
    mav.setViewName("redirect:/member/msg.do"); // ���� ��ħ ����
    
    return mav;
  }
  
  
  /**
   * ���̵� ã��
   * @param memberVO
   * @return
   */
  @RequestMapping(value="/member/find_id.do", method=RequestMethod.GET)
  public ModelAndView find_id(HttpSession session, MemberVO memberVO){    
	  ModelAndView mav = new ModelAndView();  
	  
	  ArrayList<MemberVO> list = memberProc.list();
	  mav.addObject("list", list);
	  mav.setViewName("/member/find_id"); 
	    
	  return mav;
  }
  
  /**
   * ��й�ȣ ã��
   * @param memberVO
   * @return
   */
  @RequestMapping(value="/member/find_pw.do", method=RequestMethod.GET)
  public ModelAndView find_pw(HttpSession session, MemberVO memberVO){
    
	  ModelAndView mav = new ModelAndView();
	    
	    if (true) {
	      ArrayList<MemberVO> list = memberProc.list();
	      mav.addObject("list", list);

	      mav.setViewName("/member/find_pw"); 
	      
	    } 
	    
	    return mav;
  }
  
  
  /**
   * ȸ�� ����
   * http://localhost:9091/member/delete.do?memberno=9
   * @param memberno
   * @return
   */
  @RequestMapping(value="/member/delete.do", method=RequestMethod.GET)
  public ModelAndView delete(int memberno){
    ModelAndView mav = new ModelAndView();
    
    MemberVO memberVO = this.memberProc.read(memberno); // ������ ���ڵ带 ����ڿ��� ����ϱ����� ����.
    mav.addObject("memberVO", memberVO);
    mav.setViewName("/member/delete"); // /member/delete.jsp
    
    return mav; // forward
  }
 
  /**
   * ȸ�� ���� ó��
   * @param memberVO
   * @return
   */
  @RequestMapping(value="/member/delete.do", method=RequestMethod.POST)
  public ModelAndView delete_proc(int memberno){
    ModelAndView mav = new ModelAndView();
    
    // System.out.println("id: " + memberVO.getId());
    MemberVO memberVO = this.memberProc.read(memberno); // ������ ������ ����ϱ����� ����.
    
    
    int cnt= memberProc.delete(memberno);

    if (cnt == 1) {
      mav.addObject("code", "delete_success");
      mav.addObject("mname", memberVO.getMname());  // ȫ�浿��(user4) ȸ�� ������ �����߽��ϴ�.
      mav.addObject("id", memberVO.getId());
    } else {
      mav.addObject("code", "delete_fail");
    }

    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
    mav.addObject("url", "/member/msg");  // /member/msg -> /member/msg.jsp
    
    mav.setViewName("redirect:/member/msg.do");
    
    return mav;
  }
  
  /**
   * �н����带 �����մϴ�.
   * @param memberno
   * @return
   */
  @RequestMapping(value="/member/passwd_update.do", method=RequestMethod.GET)
  public ModelAndView password_update(){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/passwd_update"); // password_update.jsp
    
    return mav;
  }
  
  /**
   * �н����� ���� ó��
   * @param memberno ȸ�� ��ȣ
   * @param current_password ���� �н�����
   * @param new_password ���ο� �н�����
   * @return
   */
  @RequestMapping(value="/member/password_update.do", method=RequestMethod.POST)
  public ModelAndView password_update(HttpSession session, String current_password, String new_password){
    int memberno = (int)session.getAttribute("memberno");
        
    ModelAndView mav = new ModelAndView();
    
    MemberVO memberVO = this.memberProc.read(memberno); // �н����带 �����Ϸ��� ȸ�� ������ ����
    mav.addObject("mname", memberVO.getMname());  
    mav.addObject("id", memberVO.getId());
    
    // ���� �н����� �˻�� ������
    HashMap<Object, Object> map = new HashMap<Object, Object>();
    map.put("memberno", memberno);
    map.put("password", current_password);
    
    int cnt = memberProc.password_check(map); // ���� �н����� �˻�
    int update_cnt = 0; // ����� �н����� ��
    
    if (cnt == 1) { // ���� �н����尡 ��ġ�ϴ� ���
      map.put("password", new_password); // ���ο� �н����带 ����
      update_cnt = memberProc.password_update(map); // �н����� ���� ó��
      
      if (update_cnt == 1) {
        mav.addObject("code", "password_update_success"); // �н����� ���� ����
      } else {
        cnt = 0;  // �н������ ��ġ������ ���������� ����.
        mav.addObject("code", "password_update_fail");       // �н����� ���� ����
      }
      
      mav.addObject("update_cnt", update_cnt);  // ����� �н������� ����    
    } else {
      mav.addObject("code", "password_fail"); // �н����尡 ��ġ���� �ʴ� ���
    }

    mav.addObject("cnt", cnt); // �н����� ��ġ ����
    mav.addObject("url", "/member/msg");  // /member/msg -> /member/msg.jsp
    
    mav.setViewName("redirect:/member/msg.do");
    
    return mav;
  }
  
//  /**
//   * �α��� ��
//   * @return
//   */
//  // http://localhost:9091/member/login.do 
//  @RequestMapping(value = "/member/login.do", method = RequestMethod.GET)
//  public ModelAndView login() {
//    ModelAndView mav = new ModelAndView();
//  
//    mav.setViewName("/member/login_form"); // /webapp/WEB-INF/views/member/login_form.jsp
//    return mav;
//  }
//
//  /**
//   * �α��� ó��
//   * @return
//   */
//  // http://localhost:9091/member/login.do 
//  @RequestMapping(value = "/member/login.do", 
//                             method = RequestMethod.POST)
//  public ModelAndView login_proc(HttpSession session,
//                                              String id, 
//                                              String password) {
//    ModelAndView mav = new ModelAndView();
//    
//    HashMap<String, Object> map = new HashMap<String, Object>();
//    map.put("id", id);
//    map.put("password", password);
//    
//    int count = memberProc.login(map); // id, password ��ġ ���� Ȯ��
//    if (count == 1) { // �α��� ����
//      // System.out.println(id + " �α��� ����");
//      MemberVO memberVO = memberProc.readById(id); // �α����� ȸ���� ���� ��ȸ
//      
//      // session ������ ȸ�� ���� ����
//      session.setAttribute("memberno", memberVO.getMemberno());
//      session.setAttribute("id", id);
//      session.setAttribute("mname", memberVO.getMname());
//      session.setAttribute("grade", memberVO.getGrade());
//      
//      mav.setViewName("redirect:/index.do"); // ���� �������� �̵�  
//    } else {
//      mav.addObject("url", "/member/login_fail_msg"); // login_fail_msg.jsp, redirect parameter ����
//     
//      mav.setViewName("redirect:/member/msg.do");  // ���ΰ�ħ ����, /member/login_fail_msg.jsp
//    }
//        
//    return mav;
//  }
  
//  /**
//   * �α��� ��
//   * @return
//   */
//  // http://localhost:9091/member/login.do 
//  @RequestMapping(value = "/member/login.do", 
//                             method = RequestMethod.GET)
//  public ModelAndView login_cookie(HttpServletRequest request) {
//    ModelAndView mav = new ModelAndView();
//    
//    Cookie[] cookies = request.getCookies();
//    Cookie cookie = null;
//  
//    String ck_id = ""; // id ����
//    String ck_id_save = ""; // id ���� ���θ� üũ
//    String ck_password = ""; // password ����
//    String ck_password_save = ""; // password ���� ���θ� üũ
//  
//    if (cookies != null) { // ��Ű�� �����Ѵٸ�
//      for (int i=0; i < cookies.length; i++){
//        cookie = cookies[i]; // ��Ű ��ü ����
//      
//        if (cookie.getName().equals("ck_id")){
//          ck_id = cookie.getValue(); 
//        }else if(cookie.getName().equals("ck_id_save")){
//          ck_id_save = cookie.getValue();  // Y, N
//        }else if (cookie.getName().equals("ck_password")){
//          ck_password = cookie.getValue();         // 1234
//        }else if(cookie.getName().equals("ck_password_save")){
//          ck_password_save = cookie.getValue();  // Y, N
//        }
//      }
//    }
//  
//    //    <input type='text' class="form-control" name='id' id='id' 
//    //            value='${ck_id }' required="required" 
//    //            style='width: 30%;' placeholder="���̵�" autofocus="autofocus">
//    mav.addObject("ck_id", ck_id);
//  
//    //    <input type='checkbox' name='id_save' value='Y' 
//    //            ${ck_id_save == 'Y' ? "checked='checked'" : "" }> ����
//    mav.addObject("ck_id_save", ck_id_save);
//  
//    mav.addObject("ck_password", ck_password);
//    mav.addObject("ck_password_save", ck_password_save);
//  
//    mav.setViewName("/member/login_form_ck"); // /member/login_form_ck.jsp
//    return mav;
//  }
  
  /**
   * �α��� �� + �α��� ������ �ڵ����� �ּ� �̵�
   * http://localhost:9091/member/login.do 
   * http://localhost:9091/member/login.do?return_url=/cart/list_by_memberno.do
   * @param return_url �α��� ������ �ڵ����� �̵��� �ּ�
   * @return
   */
  @RequestMapping(value = "/member/login.do", 
                             method = RequestMethod.GET)
  public ModelAndView login_cookie(HttpServletRequest request,
                                                  @RequestParam(value="return_url", defaultValue="") String return_url ) {
    ModelAndView mav = new ModelAndView();
    
    Cookie[] cookies = request.getCookies();
    Cookie cookie = null;
  
    String ck_id = ""; // id ����
    String ck_id_save = ""; // id ���� ���θ� üũ
    String ck_password = ""; // password ����
    String ck_password_save = ""; // password ���� ���θ� üũ
  
    if (cookies != null) { // ��Ű�� �����Ѵٸ�
      for (int i=0; i < cookies.length; i++){
        cookie = cookies[i]; // ��Ű ��ü ����
      
        if (cookie.getName().equals("ck_id")){
          ck_id = cookie.getValue(); 
        }else if(cookie.getName().equals("ck_id_save")){
          ck_id_save = cookie.getValue();  // Y, N
        }else if (cookie.getName().equals("ck_password")){
          ck_password = cookie.getValue();         // 1234
        }else if(cookie.getName().equals("ck_password_save")){
          ck_password_save = cookie.getValue();  // Y, N
        }
      }
    }
  
    //    <input type='text' class="form-control" name='id' id='id' 
    //            value='${ck_id }' required="required" 
    //            style='width: 30%;' placeholder="���̵�" autofocus="autofocus">
    mav.addObject("ck_id", ck_id);
  
    //    <input type='checkbox' name='id_save' value='Y' 
    //            ${ck_id_save == 'Y' ? "checked='checked'" : "" }> ����
    mav.addObject("ck_id_save", ck_id_save);
  
    mav.addObject("ck_password", ck_password);
    mav.addObject("ck_password_save", ck_password_save);
    
    mav.addObject("return_url", return_url); // �α��� ������ �ڵ����� �̵��� �ּ�
  
    mav.setViewName("/member/login_form_ck"); // /member/login_form_ck.jsp
    return mav;
  }
   
//  /**
//  * Cookie ��� �α��� ó��
//  * @param request Cookie�� �б����� �ʿ�
//  * @param response Cookie�� �������� �ʿ�
//  * @param session �α��� ������ �޸𸮿� ���
//  * @param id  ȸ�� ���̵�
//  * @param password ȸ�� �н�����
//  * @param id_save ȸ�� ���̵� Cookie�� ���� ����
//  * @param password_save �н����� Cookie�� ���� ����
//  * @return
//  */
//  // http://localhost:9091/member/login.do 
//  @RequestMapping(value = "/member/login.do", 
//                            method = RequestMethod.POST)
//  public ModelAndView login_cookie_proc(
//                            HttpServletRequest request,
//                            HttpServletResponse response,
//                            HttpSession session,
//                            String id,
//                            String password,
//                            @RequestParam(value="id_save", defaultValue="") String id_save,
//                            @RequestParam(value="password_save", defaultValue="") String password_save) {
//    ModelAndView mav = new ModelAndView();
//    HashMap<String, Object> map = new HashMap<String, Object>();
//    map.put("id", id);
//    map.put("password", password);
//   
//    int cnt = memberProc.login(map);
//    if (cnt == 1) { // �α��� ����
//      // System.out.println(id + " �α��� ����");
//      MemberVO memberVO = memberProc.readById(id);
//      session.setAttribute("memberno", memberVO.getMemberno()); // ������ �޸𸮿� ���
//      session.setAttribute("id", id);
//      session.setAttribute("mname", memberVO.getMname());
//      session.setAttribute("grade", memberVO.getGrade());
//   
//      // -------------------------------------------------------------------
//      // id ���� ��� ����
//      // -------------------------------------------------------------------
//      if (id_save.equals("Y")) { // id�� ������ ���, Checkbox�� üũ�� ���
//        Cookie ck_id = new Cookie("ck_id", id);
//        ck_id.setPath("/");  // root ������ ��Ű�� ��������� ��� ��ο��� ��� ���� ����
//        ck_id.setMaxAge(60 * 60 * 24 * 30); // 30 day, �ʴ���
//        response.addCookie(ck_id); // id ����
//      } else { // N, id�� �������� �ʴ� ���, Checkbox�� üũ ������ ���
//        Cookie ck_id = new Cookie("ck_id", "");
//        ck_id.setPath("/");
//        ck_id.setMaxAge(0);
//        response.addCookie(ck_id); // id ����
//      }
//      
//      // id�� �������� �����ϴ�  CheckBox üũ ����
//      Cookie ck_id_save = new Cookie("ck_id_save", id_save);
//      ck_id_save.setPath("/");
//      ck_id_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
//      response.addCookie(ck_id_save);
//      // -------------------------------------------------------------------
//  
//      // -------------------------------------------------------------------
//      // Password ���� ��� ����
//      // -------------------------------------------------------------------
//      if (password_save.equals("Y")) { // �н����� ������ ���
//        Cookie ck_password = new Cookie("ck_password", password);
//        ck_password.setPath("/");
//        ck_password.setMaxAge(60 * 60 * 24 * 30); // 30 day
//        response.addCookie(ck_password);
//      } else { // N, �н����带 �������� ���� ���
//        Cookie ck_password = new Cookie("ck_password", "");
//        ck_password.setPath("/");
//        ck_password.setMaxAge(0);
//        response.addCookie(ck_password);
//      }
//      // password�� �������� �����ϴ�  CheckBox üũ ����
//      Cookie ck_password_save = new Cookie("ck_password_save", password_save);
//      ck_password_save.setPath("/");
//      ck_password_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
//      response.addCookie(ck_password_save);
//      // -------------------------------------------------------------------
//   
//      mav.setViewName("redirect:/index.do");  
//    } else {
//      mav.addObject("url", "login_fail_msg");
//      mav.setViewName("redirect:/member/msg.do"); 
//    }
//       
//    return mav;
//  }

  /**
  * Cookie ��� �α��� ó��
  * @param request Cookie�� �б����� �ʿ�
  * @param response Cookie�� �������� �ʿ�
  * @param session �α��� ������ �޸𸮿� ���
  * @param id  ȸ�� ���̵�
  * @param password ȸ�� �н�����
  * @param id_save ȸ�� ���̵� Cookie�� ���� ����
  * @param password_save �н����� Cookie�� ���� ����
  * @return
  */
  // http://localhost:9091/member/login.do 
  @RequestMapping(value = "/member/login.do", 
                            method = RequestMethod.POST)
  public ModelAndView login_cookie_proc(
                            HttpServletRequest request,
                            HttpServletResponse response,
                            HttpSession session,
                            String id,
                            String password,
                            @RequestParam(value="id_save", defaultValue="") String id_save,
                            @RequestParam(value="password_save", defaultValue="") String password_save,
                            @RequestParam(value="return_url", defaultValue="") String return_url) {
    ModelAndView mav = new ModelAndView();
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("id", id);
    map.put("password", password);
   
    int cnt = memberProc.login(map);
    if (cnt == 1) { // �α��� ����
      // System.out.println(id + " �α��� ����");
      MemberVO memberVO = memberProc.readById(id);
      session.setAttribute("memberno", memberVO.getMemberno()); // ������ �޸𸮿� ���
      session.setAttribute("id", id);
      session.setAttribute("mname", memberVO.getMname());
      session.setAttribute("grade", memberVO.getGrade());
   
      // -------------------------------------------------------------------
      // id ���� ��� ����
      // -------------------------------------------------------------------
      if (id_save.equals("Y")) { // id�� ������ ���, Checkbox�� üũ�� ���
        Cookie ck_id = new Cookie("ck_id", id);
        ck_id.setPath("/");  // root ������ ��Ű�� ��������� ��� ��ο��� ��� ���� ����
        ck_id.setMaxAge(60 * 60 * 24 * 30); // 30 day, �ʴ���
        response.addCookie(ck_id); // id ����
      } else { // N, id�� �������� �ʴ� ���, Checkbox�� üũ ������ ���
        Cookie ck_id = new Cookie("ck_id", "");
        ck_id.setPath("/");
        ck_id.setMaxAge(0);
        response.addCookie(ck_id); // id ����
      }
      
      // id�� �������� �����ϴ�  CheckBox üũ ����
      Cookie ck_id_save = new Cookie("ck_id_save", id_save);
      ck_id_save.setPath("/");
      ck_id_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
      response.addCookie(ck_id_save);
      // -------------------------------------------------------------------
  
      // -------------------------------------------------------------------
      // Password ���� ��� ����
      // -------------------------------------------------------------------
      if (password_save.equals("Y")) { // �н����� ������ ���
        Cookie ck_password = new Cookie("ck_password", password);
        ck_password.setPath("/");
        ck_password.setMaxAge(60 * 60 * 24 * 30); // 30 day
        response.addCookie(ck_password);
      } else { // N, �н����带 �������� ���� ���
        Cookie ck_password = new Cookie("ck_password", "");
        ck_password.setPath("/");
        ck_password.setMaxAge(0);
        response.addCookie(ck_password);
      }
      // password�� �������� �����ϴ�  CheckBox üũ ����
      Cookie ck_password_save = new Cookie("ck_password_save", password_save);
      ck_password_save.setPath("/");
      ck_password_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
      response.addCookie(ck_password_save);
      // -------------------------------------------------------------------
   
      System.out.println("-> return_url: " + return_url);
      
      if (return_url.length() > 0) { // �� �α��� ������ �ڵ����� �̵��� �ּ�
        mav.setViewName("redirect:" + return_url);  
      } else {
        mav.setViewName("redirect:/index.do"); // ���� �������� �̵�
      }
      
    } else {
      mav.addObject("url", "login_fail_msg");
      mav.setViewName("redirect:/member/msg.do"); 
    }
       
    return mav;
  }
    
  /**
   * �α׾ƿ� ó��
   * @param session
   * @return
   */
  @RequestMapping(value="/member/logout.do", 
                             method=RequestMethod.GET)
  public ModelAndView logout(HttpSession session){
    ModelAndView mav = new ModelAndView();
    session.invalidate(); // ��� session ���� ����
    
    mav.setViewName("redirect:/index.do"); 
    
    return mav;
  }
  
  /**
   * Cookie + Ajax ��� �α��� ó��
   * @param request Cookie�� �б����� �ʿ�
   * @param response Cookie�� �������� �ʿ�
   * @param session �α��� ������ �޸𸮿� ���
   * @param id  ȸ�� ���̵�
   * @param password ȸ�� �н�����
   * @param id_save ȸ�� ���̵� Cookie�� ���� ����
   * @param password_save �н����� Cookie�� ���� ����
   * @return
   */
  // http://localhost:9091/member/login_ajax.do 
  @RequestMapping(value = "/member/login_ajax.do", 
                             method = RequestMethod.POST)
  @ResponseBody
  public String login_cookie_proc_ajax (
                             HttpServletRequest request,
                             HttpServletResponse response,
                             HttpSession session,
                             String id, String password,
                             @RequestParam(value="id_save", defaultValue="") String id_save,
                             @RequestParam(value="password_save", defaultValue="") String password_save) {

    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("id", id);
    map.put("password", password);
    
    int cnt = memberProc.login(map);
    if (cnt == 1) { // �α��� ����
      // System.out.println(id + " �α��� ����");
      MemberVO memberVO = memberProc.readById(id);
      session.setAttribute("memberno", memberVO.getMemberno()); // ������ �޸𸮿� ���
      session.setAttribute("id", id);
      session.setAttribute("mname", memberVO.getMname());
      session.setAttribute("grade", memberVO.getGrade());
      
      // -------------------------------------------------------------------
      // id ���� ��� ����
      // -------------------------------------------------------------------
      if (id_save.equals("Y")) { // id�� ������ ���, Checkbox�� üũ�� ���
        Cookie ck_id = new Cookie("ck_id", id);
        ck_id.setPath("/");  // root ������ ��Ű�� ��������� ��� ��ο��� ��� ���� ����
        ck_id.setMaxAge(60 * 60 * 24 * 30); // 30 day, �ʴ���
        response.addCookie(ck_id); // id ����
      } else { // N, id�� �������� �ʴ� ���, Checkbox�� üũ ������ ���
        Cookie ck_id = new Cookie("ck_id", "");
        ck_id.setPath("/");  // root ������ ��Ű�� ��������� ��� ��ο��� ��� ���� ����
        ck_id.setMaxAge(0);
        response.addCookie(ck_id); // id ����
      }
      // id�� �������� �����ϴ�  CheckBox üũ ����
      Cookie ck_id_save = new Cookie("ck_id_save", id_save);
      ck_id_save.setPath("/");  // root ������ ��Ű�� ��������� ��� ��ο��� ��� ���� ����
      ck_id_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
      response.addCookie(ck_id_save);
      // -------------------------------------------------------------------

      // -------------------------------------------------------------------
      // Password ���� ��� ����
      // -------------------------------------------------------------------
      if (password_save.equals("Y")) { // �н����� ������ ���
        Cookie ck_password = new Cookie("ck_password", password);
        ck_password.setPath("/");  // root ������ ��Ű�� ��������� ��� ��ο��� ��� ���� ����
        ck_password.setMaxAge(60 * 60 * 24 * 30); // 30 day
        response.addCookie(ck_password);
      } else { // N, �н����带 �������� ���� ���
        Cookie ck_password = new Cookie("ck_password", "");
        ck_password.setPath("/");  // root ������ ��Ű�� ��������� ��� ��ο��� ��� ���� ����
        ck_password.setMaxAge(0);
        response.addCookie(ck_password);
      }
      // password�� �������� �����ϴ�  CheckBox üũ ����
      Cookie ck_password_save = new Cookie("ck_password_save", password_save);
      ck_password_save.setPath("/");  // root ������ ��Ű�� ��������� ��� ��ο��� ��� ���� ����
      ck_password_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
      response.addCookie(ck_password_save);
      // -------------------------------------------------------------------
      
    }
     
    JSONObject json = new JSONObject();
    json.put("cnt", cnt);
   
    return json.toString(); 
  }
  
  /**
   * Ajax ��� ȸ�� ��ȸ
   * http://localhost:9091/member/read_ajax.do
   * {
   * "rname":"�մ���",
   * "raddress2":"��ö��",
   * "rzipcode":"12345",
   * "raddress1":"����� ���α�",
   * "rtel":"000-0000-0000"
   * }
   * @param memberno
   * @return
   */
  @RequestMapping(value="/member/read_ajax.do", method=RequestMethod.GET)
  @ResponseBody
  public String read_ajax(HttpSession session){
    int memberno = (int)session.getAttribute("memberno");
    
    MemberVO memberVO = this.memberProc.read(memberno);
    
    JSONObject json = new JSONObject();
    json.put("rname", memberVO.getMname());
    json.put("rtel", memberVO.getPhonenum());
    json.put("rzipcode", memberVO.getHomenum());
    json.put("raddress", memberVO.getAddress());
    json.put("rnickname", memberVO.getNickname());
    
    return json.toString();
  }
  
  
//http://localhost:9092/mail/send.do
  /**
   * ���� ����
   * @return
   */
	/*
	 * @RequestMapping(value = {"/member/send.do"}, method=RequestMethod.POST)
	 * public ModelAndView send(String receiver, String from, String title, String
	 * content, String name) {
	 * 
	 * ModelAndView mav = new ModelAndView();
	 * 
	 * mav.setViewName("/member/send"); // /WEB-INF/views/mail/send.jsp
	 * 
	 * 
	 * MailTool mailTool = new MailTool(); from = "1229juwon67@gmail.com"; title =
	 * "������ȣ ����Ű �Դϴ�."; String key = content;
	 * 
	 * 
	 * ArrayList<MemberVO> list = memberProc.list(); mav.addObject("list", list);
	 * mav.addObject("authKey", key); mav.addObject("checkn", name);
	 * 
	 * mailTool.send(receiver, from, title, content); // ���� ����
	 * 
	 * return mav; }
	 */
  
  
  
//http://localhost:9092/mail/send.do
// /**
// * ���� ����
// * @return
// */
// @RequestMapping(value="/member/send.do", method=RequestMethod.GET )
// public ModelAndView send() {
//   ModelAndView mav = new ModelAndView();
//   mav.setViewName("/member/send"); 
//  
//   return mav; // forward
// }

 /**
  * ���� ����
  * @return
  */
 @RequestMapping(value="/member/send.do", method=RequestMethod.POST)
 public ModelAndView send(String receiver, String from, String title, String content, String name){
   ModelAndView mav = new ModelAndView();
   
   MailTool mailTool = new MailTool();
   from = "1229juwon67@gmail.com";
   title = "������ȣ ����Ű �Դϴ�.";
   String key = content;
   
   
   ArrayList<MemberVO> list = memberProc.list();
   mav.addObject("list", list);
   mav.addObject("authKey", key);
   mav.addObject("checkn", name);
   
   mailTool.send(receiver, from, title, content); // ���� ����
   mav.setViewName("/member/send");
   return mav;
 }
  
  /**
   * ���̵� ��� ����
   * http://localhost:9091/member/show_id.do
   * @param session
   * @return
   */
   @RequestMapping(value="/member/show_id.do", method= RequestMethod.POST)
   public ModelAndView list(String checkn) {
     ModelAndView mav = new ModelAndView();
     

     MemberVO memberVO = memberProc.readByEmail(checkn);
     ArrayList<MemberVO> list = memberProc.list();
     mav.addObject("list", list);

     mav.addObject("find_id", memberVO.getId());
     mav.setViewName("/member/show_id");
     return mav;
   }  

  
}



