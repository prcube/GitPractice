//package servlets;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import DTO.MoviesDTO;
//import dao.MoviesDAO;
//
//
//@WebServlet("/UpdateServlet")
//public class UpdateServlet extends HttpServlet {
//	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		int seq = Integer.parseInt(request.getParameter("seq"));
//		String title = request.getParameter("title");
//		String genre = request.getParameter("genre");
//		String launch_date = request.getParameter("launch_date");
//
//		try {
//			MoviesDAO dao = MoviesDAO.getInstance();
//			int result = dao.update(new MoviesDTO(seq,title,genre,launch_date));
//			response.sendRedirect("OutputServlet");
//
//		}
//		catch(Exception e) {
//			e.printStackTrace(); // 개발단계에서는 지우면 안댐!!!
//			response.sendRedirect("error.jsp"); // 문제가 생기면 브라우저에게 이동하라는 주소 값
//		}	
//
//		
//		
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}
