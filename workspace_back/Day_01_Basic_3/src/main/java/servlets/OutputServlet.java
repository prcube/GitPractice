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


@WebServlet("/OutputServlet")
public class OutputServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		response.getWriter().append("Served at: ").append(request.getContextPath());

		try {
			MessagesDAO dao = MessagesDAO.getInstance();
			List<MessagesDTO> list = dao.selectAll();
			
			// <-- 여기서 부터는 클라이언트가 보게 될 화면을 구성하는 view 작업
			request.setAttribute("val1", 10);
			request.setAttribute("val2", "Hello");
			request.setAttribute("val3", 3.14);
			String[] arr = new String[] {"Apple","Orange"};
			request.setAttribute("array", arr);
			MessagesDTO dto = new MessagesDTO(5000,"Test","Hello");
			request.setAttribute("dto", dto);
			request.setAttribute("list", list);
			request.getRequestDispatcher("outputView.jsp").forward(request, response);
		
			// forward는 하나의 request
			// redirect

		}catch(Exception e) {
			e.printStackTrace(); // 개발단계에서는 지우면 안댐!!!
			response.sendRedirect("error.html"); // 문제가 생기면 브라우저에게 이동하라는 주소 값
		}	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
