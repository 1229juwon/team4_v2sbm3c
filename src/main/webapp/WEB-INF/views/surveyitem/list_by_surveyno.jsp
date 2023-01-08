<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    
</head> 
 
<body>
<c:import url="/menu/top.do" />
 <form name='topic' action='./list_by_surveyno_proc.do' method='POST'>
   <input type='hidden' name='surveyno' value='${param.surveyno }' >
 
<DIV class='title_line'>
  <A href="./list_by_surveyno.do?surveyno=${surveyVO.surveyno }" class='title_link'>${surveyVO.topic }</A>
</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="./create.do?surveyno=${surveyVO.surveyno }">등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_surveyno_grid1.do?surveyno=${surveyVO.surveyno }">갤러리형</A>
  </ASIDE> 

  <DIV class='menu_line'></DIV>
  
  <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <col style="width: 10%;"></col>
      <col style="width: 60%;"></col>
      <col style="width: 30%;"></col>
    </colgroup>
    <tbody>
      <c:forEach var="surveyitemVO" items="${list }">
        <c:set var="surveyno" value="${surveyitemVO.surveyno }" />
        <c:set var="surveyitemno" value="${surveyitemVO.surveyitemno }" />
        
        <tr style="height: 132px;"> 
          <td style='vertical-align: middle; text-align: center;'>
            <c:choose>
              <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}"> <%-- 이미지인지 검사 --%>
                <%-- /static/surveyitem/storage/ --%>
                <a href="./read.do?surveyitemno=${surveyitemno}&now_page=${param.now_page }"><IMG src="/surveyitem/storage/${thumb1 }" style="width: 120px; height: 80px;"></a> 
              </c:when>
            </c:choose>
          </td>  
          <td style='vertical-align: middle;'> 

     
            <label style="cursor: pointer;">
            <input type="radio" name="${surveyitemVO.surveyno}" value="${surveyitemVO.surveyno}" > ${surveyitemVO.item}
            </label>
          </td> 
         

          <td style='vertical-align: middle; text-align: center;'>
            <A href="/surveyitem/map.do?surveyno=${surveyno }&surveyitemno=${surveyitemno}" title="지도"><IMG src="/surveyitem/images/map.png" class="icon"></A>
            <A href="/surveyitem/youtube.do?surveyno=${surveyno }&surveyitemno=${surveyitemno}" title="Youtube"><IMG src="/surveyitem/images/youtube.png" class="icon"></A>
            수정/삭제
          </td> 
       </tr>
      </c:forEach>
     
            
    </tbody>
  </table>
</DIV>

       <div class="content_body_bottom">
       <button type="submit" class="btn btn-primary">참여</button>
       </div>
 </form>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>

