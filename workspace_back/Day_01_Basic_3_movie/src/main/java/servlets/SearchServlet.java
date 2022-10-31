//package servlets;
//
//import java.io.IOException;
//import java.util.List;
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
//@WebServlet("/SearchServlet")
//public class SearchServlet extends HttpServlet {
//	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String title = request.getParameter("sid");
//		try {
//			MoviesDAO dao = MoviesDAO.getInstance();
//			List<MoviesDTO> slist = dao.search(title);
//			System.out.println(slist.size());
//			request.setAttribute("slist", slist);
//			request.getRequestDispatcher("search.jsp").forward(request, response);
//
//
//
//		}catch(Exception e) {
//			e.printStackTrace(); // 개발단계에서는 지우면 안댐!!!
//			response.sendRedirect("movieError.jsp"); // 문제가 생기면 브라우저에게 이동하라는 주소 값
//		}	
//		
//		
//		
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
