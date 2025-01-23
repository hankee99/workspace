package kr.co.iei;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendData1Servlet
 */
@WebServlet("/sendData1")
public class SendData1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendData1Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("서버호출 완333");
		//요청시 보낸 데이터가 한글이 포함돼있으면 인코딩작업 수행
		//->한글 인코딩은 무조건 수행
		request.setCharacterEncoding("utf-8");
		//요청 정보중 전달된 데이터를 추출
		String data1 = request.getParameter("name");
		String data2 = request.getParameter("test");
		System.out.println("data1: "+data1 + "\ndata2: "+data2);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
