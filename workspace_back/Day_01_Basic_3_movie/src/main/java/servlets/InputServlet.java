//package servlets;
//
//import java.io.IOException;
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import dao.MoviesDAO;
//
//
//@WebServlet("/InputServlet")
//public class InputServlet extends HttpServlet {
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		String title = request.getParameter("title");
//		String genre = request.getParameter("genre");
////		Timestamp launch_date = request.getParameter("launch_date");
//		SimpleDateFormat mFormat = new SimpleDateFormat("yyy-MM-dd HH:mm");
////		SimpleDateFormat mFormat = new DateTimeformat.forPattern("MMMM, yyyy");
//		String launch_date = mFormat.format(System.currentTimeMillis());
//		
//
//		try {
//			MoviesDAO dao = MoviesDAO.getInstance();
//			int result = dao.insert(title, genre, launch_date);
//
//
//			response.sendRedirect("index.jsp"); 
//		}
//		catch(Exception e) {
//			e.printStackTrace(); // 개발단계에서는 지우면 안댐!!!
//			response.sendRedirect("moiveError.jsp"); // 문제가 생기면 브라우저에게 이동하라는 주소 값
//		}	
//		
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
