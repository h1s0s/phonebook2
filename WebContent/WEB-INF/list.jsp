<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.javaex.vo.PersonVo" %>
<%
	List<PersonVo> personList = (List<PersonVo>)request.getAttribute("pList");
	//값을 넣을때 Object형식으로 넣기 때문에 꺼낼때 형변환을 해서 꺼내줘야함.
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>[phonebook2]</h1>
		
		<h2>전화번호 리스트</h2>
		
		<p>입력한 정보 내역입니다.</p>
		<%
		for(int i=0; i<personList.size(); i++) {
		%>
			<table border="1">
				<tr>
					<td>이름(name)</td>
					<td><%= personList.get(i).getName() %></td>
				</tr>
				<tr>
					<td>핸드폰(hp)</td>
					<td><%= personList.get(i).getHp() %></td>
				</tr>
				<tr>
					<td>회사(company)</td>
					<td><%= personList.get(i).getCompany() %></td>
				</tr>
				<tr>
					<td><a href="/phonebook2/pbc?action=updateForm&personid=<%= personList.get(i).getPersonId() %>">[수정]</a></td>
					<td><a href="/phonebook2/pbc?action=delete&personid=<%= personList.get(i).getPersonId() %>">[삭제]</a></td>
				</tr>
			</table>
			<br>
		<% 
		}
		%>
	</body>
</html>