package kr.co.iei;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import java.util.stream.*;
/**
 * Servlet implementation class SendData3Servlet
 */
@WebServlet("/sendData3")
public class SendData3Servlet extends HttpServlet {
	private String data = "";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendData3Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩
		request.setCharacterEncoding("utf-8");
//		String str = request.getParameter("str");
//		int num = Integer.parseInt(request.getParameter("num"));
//		String gender = request.getParameter("gender");
//		String[] hobby = request.getParameterValues("hobby");
//		String age = request.getParameter("age");
//		String comment = request.getParameter("comment");
//		String hiddenData = request.getParameter("hiddenData");
//		String readonly = request.getParameter("input1");
//		Arrays.stream(hobby).forEach(System.out::println);
//		System.out.println(str +"/"+ num +"/"+ gender +"/"+ hobby[0] +"/"+ hobby[1] +"/"+ age +"/"+ comment +"/"+ hiddenData +"/"+ readonly);
		
		Map<String, String[]> map = request.getParameterMap();
		map.entrySet().stream().forEach(i -> {
			data = "";
			Arrays.stream(i.getValue()).forEach(a -> data += a+"/");
			System.out.println(i.getKey() + ": " + data.substring(0,data.length()-1));
		});
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
