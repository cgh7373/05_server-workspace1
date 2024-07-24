package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestPostServlet
 */
@WebServlet("/test2.do")
public class RequestPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet 메소드 실행");
		
		// POST 방식 요청에는 데이터를 뽑기전에 인코딩 설정해줘야함
		// Header와 달리 Body 영역은 utf-8이 아님!
		
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name"); // "차은우" | ""
		String gender = request.getParameter("gender");// "M"|"F"|null 위에랑 다름
		int age = Integer.parseInt(request.getParameter("age")); // "20"->20 | "" => 오류!! (NumberformatException) : 빈문자열 숫자로 변환불가!
		String city = request.getParameter("city");
		double height = Double.parseDouble(request.getParameter("height"));
		
		// checkbox같은 여러개의 밸류값들을 뽑아내기
		String[] foods = request.getParameterValues("food"); // ["한식","일식"] | null
		
		System.out.println("name : " + name);
		System.out.println("gender : " + gender);
		System.out.println("age : " + age);
		System.out.println("city : " + city);
		System.out.println("height : " + height);
		
		System.out.println("foods : " + foods); // 아마 주소값 나올것임
//		System.out.println("foods[0] : " + foods[0]);
		
		if (foods == null) {
			System.out.println("foods : 없음");
		} else {
			System.out.println("foods : " + String.join(" / ", foods)); // 반복문없이, 각 객체를 /로(앞에거) 나눠서 보여준다???
		}
		
		// 뽑아낸 값들을 가지고 요청처리해야함 (DB와 상호작용 : 조회, 삽입, 수정, 삭제 등)
		// > Service 메소드 호출 > Dao 메소드 호출 > DB에 sql문 실행
		
		/*
		 * int result = new MemberService().insertMember(name,gender,age,city,height,foods);
		 * if (result > 0) {
		 * 		// 성공 => 성공페이지
		 * } else {
		 * 		// 실패 => 실패페이지
		 * }
		 */
		
		// 위의 요청 처리후 성공했다치고 사용자가 보게될 응답페이지(html) 만들어서 전송
		// 즉, Java 코드 내에 사용자게 보게될 html구문 작성해보기
		
		// 장점 : Java코드 내에 작성하기에 반복,조건문,유용한메소드 등을 활용할수있다
		// 단점 : 개불편함, 복잡함, 수정할때마다 서버 재기동해야됨
		
		// ** response 객체를 통해 사용자에게 html 전달
		
		// 1) 출력할내용은 문서형태의 html이고, 문자 인코딩셋은 utf-8이다
		response.setContentType("text/html; charset=utf-8");
		
		// 2) 내보낼 스트림 생성
		PrintWriter out = response.getWriter();
		
		// 3) 출력용 스트림을 통해 html구문 한줄씩 출력
		out.println("<html>");
		
		out.println("<head>");
		
		out.println("<style>");
		
		out.println("h2{color:red}");
		out.println("#name{color:orange}");
		out.println("#age{color:yellow}");
		out.println("#city{color:green}");
		out.println("#height{color:blue}");
		out.println("#gender{color:purple}");
		
		out.println("</style>");
		
		out.println("</head>");
		
		out.println("<body>");
		
		out.println("<h2>개인정보 응답 화면</h2>");
		
//		out.println("<span>" + name + "</span>님은");
		out.printf("<span id='name'>%s</span>님은, ", name);
		out.printf("<span id='age'>%d</span>살이며, ", age);
		out.printf("<span id='city'>%s</span>에 사는, ", city);
		out.printf("키는<span id='height'>%.1f</span>cm 이며, ", height);

		out.print("성별은 ");
		
		if (gender == null) {
			out.println("선택을 안함. <br>");
		} else {
			if (gender.equals("M")) {
				out.println("<span id='gender'>남자</span>입니다.");
			} else {
				out.println("<span id='gender'>여자</span>입니다.");
			}
		}
		
		out.print("좋아하는 음식은 ");
		
		if (foods == null) {
			out.println("없습니다.");
		} else {
			out.println("<ul>");
			
			for (int i = 0; i < foods.length; i++) {
				out.println("<li>"+ foods[i] + "</li>");
			}
			
			out.println("</ul>");
		}
		
		out.println("</body>");
			
		out.println("</html>");
		
		// 요청처리(DB의 sql문 실행) : > Service > Dao > sql문 실행
		
		// 요청처리가 다 되었다는 가정하에 응답페이지(html)
		
		// 1. 순수 Servlet 방식으로 작성하는 방법 : Java 코드 내에 html기술
		// 2. JSP(Java Server Page) 방식 : html 내에 Java 코드 기술가능
		
		// 응답 페이지 만드는 과정을 jsp에게 위임
		// 단, jsp에서 필요로 하는 데이터들을 전달해줘야함
		// 주섬주섬담기위한공간 == request의 attribute 영역 (키-밸류 세트)
		// request.setAttribute("키",밸류); : 키는 항상 스트링
		request.setAttribute("name", name);
		request.setAttribute("age", age);
		request.setAttribute("city", city);
		request.setAttribute("height", height);
		request.setAttribute("gender", gender);
		request.setAttribute("foods", foods);
		
		// 응답하고자 하는 뷰(jsp) 선택하면서 RequestDispatcher 객체 생성
		RequestDispatcher view = request.getRequestDispatcher("views/responsePage.jsp");
		
		view.forward(request, response);
		// 이거 너무중요.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost 메소드 실행");
		doGet(request, response);
	}

}
