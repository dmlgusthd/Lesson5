<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>정보등록</title>
</head>
<body>
<h1 align="center">-=: STAFF정보 등록화면 :=-</h1>
<form action="<c:url value='/sign/SignUp'/>" method="post">
<table border="1" width="100%">
	<tr>
		<th>이름</th>
		<th><input type="text" name="st_name"></th>
		<th>주민번호</th>
		<th><input type="text" name="st_sn1" maxlength="6">-<input type="password" name="st_sn2" maxlength="7"></th>
		<th>종교</th>
		<th>
			<select name="religionno">
			<c:forEach var='b' items='${relist}'>
				<option value='${b.re_no}'>${b.re_name}</option>
			</c:forEach>
			</select>
		</th>
	</tr>
	<tr>
		<td>학력</td>
		<td>
		<c:forEach var='b' items='${sclist}'>		
			<input type="radio" name="schoolno" value='${b.sc_no}'>${b.sc_graduate}
		</c:forEach>
		</td>		
		<td>기술</td>
		<td colspan="3">
		<c:forEach var='b' items='${sklist}'>
			<input type="checkbox" name="skillno" value='${b.sk_no}'>${b.sk_name}
		</c:forEach>
		</td>		
	</tr>
	<tr>
		<td>졸업일</td>
		<td colspan="5">
			<input type="date" name="st_graduateday">
		</td>
	</tr>
	<tr>
		<td colspan="6" align="center">
				<input type="submit" value="등록"> | 
				<input type="reset" value="초기화">					
		</td>
	</tr>
</table>
</form>
</body>
</html>