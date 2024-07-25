package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PizzaServlet
 */
@WebServlet("/confirmPizza.do")
public class PizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PizzaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) 전달값 중에 한글이 있을경우 인코딩 처리 (post일때만)
//		request.setCharacterEncoding("utf-8");
		
		// 2) 요청시 전달값 뽑기 및 데이터 가공처리(파싱같은거) => 변수 및 객체에 기록
		// request.getParameter("키값"); : "밸류값"(String)
		// request.getParameterValues("키값"); : "밸류값"(String[])
		// => 만일 키값이 존재하지 않을경우 null 반환
		
		// 요청시 전달값들을 다 뽑아서 변수에 기록(변수명은 임의로) + 콘솔 출력
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String message = request.getParameter("message");
		String pizza = request.getParameter("pizza");
		String[] topping = request.getParameterValues("topping");
		String[] side = request.getParameterValues("side");
		String payment = request.getParameter("payment");
		
		System.out.println("이름 : " + userName);
		System.out.println("전화번호 : " + phone);
		System.out.println("주소 : " + address);
		if (message.equals("")) {
			System.out.println("요청사항 없음");
		} else {
		System.out.println("요청사항 : " + message);
		}
		System.out.println("피자종류 : " + pizza);
		if (topping==null) {
			System.out.println("토핑없음");
		} else {
			System.out.println("토핑 : " + String.join(", ", topping));
		}
		if (side==null) {
			System.out.println("사이드없음");
		} else {
		System.out.println("사이드 : " + String.join(", ", side));
		}
		System.out.println("결제방식 : " + payment);
		
		// 3) 요청처리 (db에 sql문 실행하러 > Service > Dao)
		int price = 0;
		
		switch(pizza) {
		case "콤비네이션피자" : price += 5000; break;
		case "치즈피자" : price += 6000; break;
		case "포테이토피자" :
		case "고구마피자" : price += 7000; break;
		case "불고기피자" : price += 8000; break;
		}
		
		if(topping != null) {
			for(int i = 0; i < topping.length; i++) {
				switch(topping[i]) {
				case "고구마무스" :
				case "콘크림무스" : price += 1500; break;
				case "파인애플토핑" :
				case "치즈토핑" : price += 2000; break;
				case "치즈바이트" : 
				case "치즈크러스트" : price += 3000; break;
				}
			}
		}
		
		if(side != null) {
			for(String a : side) {
				switch(a) {
				case "콜라" :
				case "사이다" : price += 2000; break;
				case "핫소스" :
				case "갈릭소스" : price += 300; break;
				case "피클" :
				case "파마산치즈" : price += 500; break;
				}
			}
		}
		
		// 4) 요청 처리 후 사용자가 보게될 응답페이지(결제페이지) 만들기
		//    응답페이지(jsp) 선택해서 포워딩
		//    단, 응답페이지에서 필요한 데이터는 여기서 담아서 갖다줘야함
		//    > request의 attribute 영역에 담기
		//    어떤 데이터 담아야할지 모르겠다면? 선 jsp 후 담기
		
		request.setAttribute("name", userName);
		request.setAttribute("phone", phone);
		request.setAttribute("address", address);
		request.setAttribute("message", message);
		request.setAttribute("pizza", pizza);
		request.setAttribute("topping", topping);
		request.setAttribute("side", side);
		request.setAttribute("payment", payment);
		request.setAttribute("price", price);
		
		// 응답할 view(jsp) 선택
		RequestDispatcher view = request.getRequestDispatcher("views/pizza/pizzaPayment.jsp");
		
		// 선택된 뷰 jsp로 보내기
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
