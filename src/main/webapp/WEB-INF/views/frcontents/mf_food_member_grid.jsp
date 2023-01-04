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
 
<DIV class='title_line'>
  <A href="./list_all.do" class='title_link'>인공지능 기반 추천</A>
</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE> 

  <DIV class='menu_line'></DIV>
  
  <DIV id='panel' style='margin: 10px auto; text-align: center; width: 90%;'>
    <DIV style='margin: 0px auto; width: 100%;'>
      <c:forEach var="frcontentsVO" items="${list }">
        <c:set var="fr_name" value="${frcontentsVO.fr_name }" />
        <c:set var="cateno" value="${frcontentsVO.cateno }" />
        <c:set var="frno" value="${frcontentsVO.frno }" />
        <c:set var="thumb1" value="${frcontentsVO.thumb1 }" />
                
        <DIV style='margin: 0px auto; width: 19.5%; float: left; height: 350px;'>
          <a href="./read.do?frno=${frno}&cateno=${cateno}"><IMG src="/frcontents/storage/${thumb1 }" style="width: 95%; height: 200px;"></a>
          <br>
          ${fr_name } 
        </DIV>
      </c:forEach>
    </DIV>
  </DIV>
</DIV>
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>

