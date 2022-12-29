<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


<script type="text/javascript">
  $(function() { // click 이벤트 핸들러 등록
    $('#authBtn').on('click', check_authKey); // 찾기 버튼 클릭시 입력창 나타나기
  });

  function check_authKey(){ 

	  let auth = $('#auth', frm).val(); // frm 폼에서 auth가 'auth'인 태그 검색
	  
	  if ($.trim(auth) == ${authKey}) { // auth과 인증번호가 일치하면
		  $('#frm').submit(); 
		}
	  else{

		  }

  }

</script> 

</head> 
 
<body>
---------------------------------<br>
<c:import url="/menu/top.do" />
---------------------------------<br>

  <DIV class='title_line'>인증번호 입력</DIV>

  <DIV class='content_body'> 
    <DIV style='width: 40%; margin: 0px auto;'>
      <FORM name='frm' id='frm' method='POST' action='./show_id.do'>
      
        
        <div class="form_input">
        <label>인증번호를 입력해주세요</label>    
          <input type='text' class="form-control" name='auth' id='auth' 
                    value='' required="required" 
                    style='width: 80%;' placeholder="인증번호" autofocus="autofocus">                    
        </div> 
        
        <div class="form_input">
          <button type="button" id="authBtn" class="btn btn-info">찾기</button>
        </div>
        
     	<div class="form_input" style="display:none">
     	<textarea name="checkn" id="checkn" rows="15"  style='width: 100%; border: #AAAAAA 1px solid;'>${checkn}</textarea>
     	</div>
      </FORM>
    </DIV>
  </DIV> <%-- <DIV class='content_body'> END --%>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>