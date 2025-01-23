package kr.co.iei;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResponseServlet
 */
@WebServlet("/response")
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResponseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("서버호출22");
		//클라이언트에게 응답할 문서타입을 지정 및 문자셋 지정
		response.setContentType("text/html;charset=utf-8");
		//사용자가 볼 HTML 문서를 작성하는 객체 생성
		PrintWriter out = response.getWriter();
		//HTML 문서 작성
		out.println("<!DOCTYPE html>");
		out.println("<html><head><title>응답페이지</title></head>");
		out.println("<body>");
		out.println("<h1>서블릿 응답페이지입니다.</h1>");
		out.println("<a href='/'>메인으로..</a>");
		out.println("<body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
