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
//import DTO.MessagesDTO;
//import dao.MessagesDAO;
//
//
//@WebServlet("/UpdateServlet")
//public class UpdateServlet extends HttpServlet {
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		int seq = Integer.parseInt(request.getParameter("seq"));
//		String writer = request.getParameter("writer");
//		String message = request.getParameter("message");
//
//		try {
//			// 1번방식
//			MessagesDAO dao = MessagesDAO.getInstance();
//			int result = dao.update(new MessagesDTO(seq,writer,message));//MessagesDAO 만들어 놓은 부분을 갖고 오는 것
//			response.sendRedirect("OutputServlet");
//
//			//			2번방식
//			//			MessagesDAO dao = new MessagesDAO();
//			//			int result = dao.update(seq,writer,message);//MessagesDAO 만들어 놓은 부분을 갖고 오는 것
//			//			response.sendRedirect("OutputServlet");
//
//
//		}
//		catch(Exception e) {
//			e.printStackTrace(); // 개발단계에서는 지우면 안댐!!!
//			response.sendRedirect("error.html"); // 문제가 생기면 브라우저에게 이동하라는 주소 값
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
