//package servlets;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import dao.MessagesDAO;
//
//@WebServlet("/InputServlet")
//public class InputServlet extends HttpServlet {
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		String writer = request.getParameter("writer");
//		String message = request.getParameter("message");
//
//		try {
//			MessagesDAO dao = MessagesDAO.getInstance();
//			int result = dao.insert(writer, message);//MessagesDAO 만들어 놓은 부분을 갖고 오는 것
//
////			PrintWriter pwr = response.getWriter(); // 클라이언트가 submit 하고 나오는 문구
//			response.sendRedirect("index.html"); //원래꺼원래꺼원래꺼
//			
////			request.setAttribute("list", list);
////			request.getRequestDispatcher("outputView.jsp").forward(request,response);
//		}
//		catch(Exception e) {
//			e.printStackTrace(); // 개발단계에서는 지우면 안댐!!!
//			response.sendRedirect("error.html"); // 문제가 생기면 브라우저에게 이동하라는 주소 값
//		}	
//	}
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		doGet(request, response);
//	}
//}
