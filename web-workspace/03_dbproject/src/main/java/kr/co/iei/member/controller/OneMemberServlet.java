package kr.co.iei.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.iei.member.service.MemberService;
import kr.co.iei.member.vo.Member;

/**
 * Servlet implementation class OneMemberServlet
 */
@WebServlet("/oneMember")
public class OneMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OneMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String memberId = request.getParameter("memberId");
		MemberService memberService = new MemberService();
		Member m = memberService.selectOneMember(memberId);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if(m != null) {
			out.println("<!DOCTYPE html>\r\n"
					+ "<html lang=\"ko\">\r\n"
					+ "<head>\r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <title>아이디로 회원조회</title>\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "    <h1>아이디로 회원조회</h1>\r\n"
					+ "    <hr>\r\n"
					+ "    <table border=\"1\">\r\n"
					+ "        <tr>\r\n"
					+ "            <th>회원번호</th>\r\n"
					+ "			<th>아이디</th>\r\n"
					+ "			<th>비밀먼호</th>\r\n"
					+ "			<th>이름</th>\r\n"
					+ "			<th>전화번호</th>\r\n"
					+ "			<th>주소</th>\r\n"
					+ "			<th>회원등급</th>\r\n"
					+ "			<th>가입일</th>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>");
			
			
			
			
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
			
			
			out.println("</tr>\r\n"
					+ "    </table>\r\n"
					+ "    \r\n" + "<a href=\"/\">메인으로</a>"
					+ "</body>\r\n"
					+ "</html>");
		}else {
			out.println("<!DOCTYPE html>\r\n"
					+ "<html lang=\"ko\">\r\n"
					+ "<head>\r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <title>아이디로 회원조회</title>\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "    <h1>조회 실패</h1>\r\n"
					+ "    \r\n" + "<a href=\"/\">메인으로</a>"
					+ "</body>\r\n"
					+ "</html>");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
