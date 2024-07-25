<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	h1{color:red;}
</style>
</head>
<body>
	
		<h1>스크립팅 원소</h1>
		
		<!-- html 주석 : 개발자도구탭(F12)에 노출됨 -->
		<%-- jsp 주석 : 노출되지않는다 --%>
	
		<%
			// 스크립틀릿 : 이 안에 일반적인 자바코드 작성(변수선언 및 초기화, 제어문 등)
			int sum = 0;
			for (int i = 1; i <= 100; i++) {
				sum += i;
			}
			
			System.out.println("덧셈 끝! 결과 : " + sum);
		%>
		
		<p>
			화면으로 출력하려면? <br>
			스크립틀릿 이용해서 출력가능 : <% out.println(sum); %> <br>
			표현식(출력식) 이용해서 출력가능 : <%= sum %> <%-- 저 '=' 이 out.println();와 같음. 세미콜론 찍지말것 --%>
		</p>
		
		<%
			String[] name = {"이미예", "이은지", "이주영", "김시연"};
		%>

		<h5>배열의 길이 : <%=name.length %></h5>	
		<h5>배열의 담긴 값 : <%=String.join("-", name) %></h5>	
		
		<h4>반복문을 통해 html 요소 반복적으로 화면에 출력가능</h4>
		
		<%-- forEach문 --%>
		<ul>
			<% for (String a :name) { %>
			<li><%=a %></li>
			<%} %>
		</ul>
		
		<%!
			public void test() {
			
		}
		
		
		%>
		
		<% test(); %>
		
</body>
</html>