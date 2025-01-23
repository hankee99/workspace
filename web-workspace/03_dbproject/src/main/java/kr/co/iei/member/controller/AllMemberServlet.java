package kr.co.iei.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.iei.common.JDBCTemplate;
import kr.co.iei.member.service.MemberService;
import kr.co.iei.member.vo.Member;

/**
 * Servlet implementation class AllMemberServlet
 */
@WebServlet("/allMember")
public class AllMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		//서블릿 호출 시 전달한 데이터가 없으니 생략
		//3.비즈니스 로직(DB에서 전체회원 조회)
		MemberService memberService = new MemberService();
		ArrayList<Member> list = memberService.selectAllMember();
		
		//4.결과처리
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n"
				+ "<html lang=\"ko\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>전체회원조회</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <h1>전체회원조회</h1>\r\n"
				+ "    <hr>\r\n"
				+ "    <table border=\"1\">\r\n"
				+ "        <tr>\r\n"
				+ "            <th>회원번호</th>\r\n"
				+ "            <th>아이디</th>\r\n"
				+ "            <th>비밀먼호</th>\r\n"
				+ "            <th>이름</th>\r\n"
				+ "            <th>전화번호</th>\r\n"
				+ "            <th>주소</th>\r\n"
				+ "            <th>회원등급</th>\r\n"
				+ "            <th>가입일</th>\r\n"
				+ "        </tr>");
		
		for(Member m : list) {
			out.println("<tr>");
			
			out.println("<td>" +m.getMemberNo() +"</td>");
			out.println("<td>" +m.getMemberId() +"</td>");
			out.println("<td>" +m.getMemberPw() +"</td>");
			out.println("<td>" +m.getMemberName() +"</td>");
			out.println("<td>" +m.getMemberPhone() +"</td>");
			out.println("<td>" +m.getMemberAddr() +"</td>");
			if(m.getMemberLevel() == 1) out.println("<td>관리자</td>");
			if(m.getMemberLevel() == 2) out.println("<td>정회원</td>");
			if(m.getMemberLevel() == 3) out.println("<td>준회원</td>");
			out.println("<td>" +m.getEnrollDate() +"</td>");
			out.println("</tr>");
		}
		
		out.println("</body>\r\n"
				+ "</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
