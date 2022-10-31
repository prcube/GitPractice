//package servlets;
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import dao.MoviesDAO;
//
//
//@WebServlet("/DeleteServlet")
//public class DeleteServlet extends HttpServlet { 
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		int seq = Integer.parseInt(request.getParameter("seq"));
//
//		try {
//			MoviesDAO dao = MoviesDAO.getInstance();
//			int result = dao.delete(seq);
//			response.sendRedirect("OutputServlet");
//		}
//		catch(Exception e) {
//			e.printStackTrace(); // 개발단계에서는 지우면 안댐!!!
//			response.sendRedirect("moiveError.jsp"); // 문제가 생기면 브라우저에게 이동하라는 주소 값
//		}	
//		
//		
//		
//	}
//
//	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		doGet(request, response);
//	}
//
//}
