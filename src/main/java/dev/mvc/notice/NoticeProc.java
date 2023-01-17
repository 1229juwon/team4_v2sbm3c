package dev.mvc.notice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("dev.mvc.notice.noticeProc")
public class NoticeProc implements NoticeProcInter {
  // noticeDAOInter interface ë§? ì¡´ìž¬?•˜ê³? êµ¬í˜„ class?Š” ì¡´ìž¬?•˜ì§? ?•Š?Œ.
  // interface?Š” ê°ì²´ë¥? ë§Œë“¤ ?ˆ˜ ?—†ê³? ?• ?‹¹ë§? ë°›ì„ ?ˆ˜ ?žˆ?Œ.
  
  @Autowired
  private NoticeDAOInter noticeDAO;
  
  public NoticeProc() {
    // System.out.println("-> noticeProc created.");
    // System.out.println("-> noticeProc: " + (noticeDAO == null));
  }
  
  @Override
  public int create(NoticeVO noticeVO) {
    int cnt = this.noticeDAO.create(noticeVO); // MyBATISê°? ì²˜ë¦¬?•œ ? ˆì½”ë“œ ê°??ˆ˜ê°? return?¨    
    // System.out.println("-> noticeProc create: " + (noticeDAO == null));
    return cnt;
  }
  
  @Override
  public ArrayList<NoticeVO> list_all() {
    ArrayList<NoticeVO> list = this.noticeDAO.list_all();
    
    return list;
  }
  
  @Override
  public NoticeVO read(int noticeno) {
    NoticeVO noticeVO = this.noticeDAO.read(noticeno);
    return noticeVO;
  }

  @Override
  public int update(NoticeVO noticeVO) {
    int cnt = this.noticeDAO.update(noticeVO);
    return cnt;
  }
  
  @Override
  public int delete(int noticeno) {
    int cnt = this.noticeDAO.delete(noticeno);
    return cnt;
  }
}

