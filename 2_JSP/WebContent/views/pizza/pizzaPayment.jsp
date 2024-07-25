<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name = (String)request.getAttribute("name");
	String phone = (String)request.getAttribute("phone");
	String address = (String)request.getAttribute("address");
	String message = (String)request.getAttribute("message");
	String pizza = (String)request.getAttribute("pizza");
	String[] topping = (String[])request.getAttribute("topping");
	String[] side = (String[])request.getAttribute("side");
	String payment = (String)request.getAttribute("payment");
	int price = (int)request.getAttribute("price");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

    <h1>피자 결제 페이지</h1>

    <h2>주문내역</h2>

    <h4>[ 주문자 정보 ]</h4>

    <ul>
        <li>성함 : <%=name %></li>

        <li>전화번호 : <%=phone %></li>

        <li>주소 : <%=address %></li>

		<%if (message.equals("")) { %>
            <li>요청사항 : 작성안함</li>
        <%} else { %>
            <li>요청사항 : <%=message %></li>
        <%} %>
    </ul>

    <br>

    <h4>[ 주문 정보 ]</h4>
    
    <ul>
        <li>피자 : <%=pizza %></li>

		<% if (topping == null) { %>
            <li>토핑 : 선택안함</li>
        <% } else { %>
            <li>토핑 : <%=String.join(", ", topping) %></li>
        <% } %>

		<% if (side == null) { %>
            <li>사이드 : 선택안함</li>
        <% } else { %>
            <li>사이드 : <%=String.join(", ", side) %></li>
        <% } %>    

		<% if (payment.equals("cash")) {%>
        <li>결제방식 : 현금결제</li>
        <% } else { %>
        <li>결제방식 : 카드결제</li>
        <% } %>
        
    </ul>

    <hr>

    <h3>위와 같이 주문하셨습니다.</h3>

    <h2>총가격 : <%=price %>원</h2>

</body>
</html>