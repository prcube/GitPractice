package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.MessagesDTO;
import dao.MessagesDAO;


@WebServlet("*.message")
public class MessagesController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);

			try {
		if(uri.equals("/input.message")) {
			String writer = request.getParameter("writer");
			String message = request.getParameter("message");

			MessagesDAO dao = MessagesDAO.getInstance();
			int result = dao.insert(writer, message);
			response.sendRedirect("index.html");

		}else if(uri.equals("/output.message")) {
			MessagesDAO dao = MessagesDAO.getInstance();
			List<MessagesDTO> list = dao.selectAll();

			request.setAttribute("list", list);
			request.getRequestDispatcher("outputView.jsp").forward(request, response);

		}else if(uri.equals("/delete.message")) {

			int seq = Integer.parseInt(request.getParameter("seq"));

			MessagesDAO dao = MessagesDAO.getInstance();
			int result = dao.delete(seq);
			response.sendRedirect("output.message");
			
		}else if(uri.equals("/update.message")) {
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			String writer = request.getParameter("writer");
			String message = request.getParameter("message");
			
			MessagesDAO dao = MessagesDAO.getInstance();
			int result = dao.update(new MessagesDTO(seq,writer,message));
			response.sendRedirect("output.message");

			
		}
			}catch(Exception e) {
				e.printStackTrace();
				response.sendRedirect("error.html");
			}



	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
