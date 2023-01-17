package dev.mvc.notice;

/*
    noticeno                          NUMBER(8)    NOT NULL    PRIMARY KEY,
    adminno                             VARCHAR2(100)    NOT NULL,
    title                           VARCHAR2(1)    DEFAULT 'N'     NOT NULL,
    content
    rdate                             DATE     NOT NULL
 */

public class NoticeVO {
  /** ?„¤ë¬? ë²ˆí˜¸ */
  private int noticeno;
  
  private String adminno;
  
  private String title;
  
  private String content;
  
  public int getNoticeno() {
	return noticeno;
}

public void setNoticeno(int noticeno) {
	this.noticeno = noticeno;
}

public String getAdminno() {
	return adminno;
}

public void setAdminno(String adminno) {
	this.adminno = adminno;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

public String getRdate() {
	return rdate;
}

public void setRdate(String rdate) {
	this.rdate = rdate;
}

private String rdate = "";
}
