package controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommentsDAO;
import DTO.CommentsDTO;

@WebServlet("*.comments")
public class CommentsController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf8");
		String uri = request.getRequestURI();


		try{
			if(uri.equals("/insert.comments")) {
				String writer = (String) request.getSession().getAttribute("loginID");
				String comments = request.getParameter("comments");
				int parentseq = Integer.parseInt(request.getParameter("seq"));
				
				SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String write_date = mFormat.format(System.currentTimeMillis());
				System.out.println(writer+" "+comments+" "+write_date+" "+parentseq);
				CommentsDAO dao = CommentsDAO.getInstance();
				dao.insert(writer, comments, write_date, parentseq);
				response.sendRedirect("/detail.board?seq="+parentseq);
			}
			
			else if(uri.equals("/output.comments")) {
				CommentsDAO dao = CommentsDAO.getInstance();
				int parentseq = Integer.parseInt(request.getParameter("seq"));
				List<CommentsDTO> list2 = dao.output();
				System.out.println(list2);
				request.setAttribute("list2", list2);
				request.getRequestDispatcher("/detail.board").forward(request, response);
			}
			
			else if(uri.equals("/update.comments")) {
				String contents = request.getParameter("comments");
				int parentseq = Integer.parseInt(request.getParameter("seq"));
				System.out.println(parentseq);
				CommentsDAO dao = CommentsDAO.getInstance();
				int result = dao.update(new CommentsDTO(contents));
				response.sendRedirect("/detail.board?seq="+parentseq);
			}
			
			else if(uri.equals("/delete.comments")) {
				
				CommentsDAO dao = CommentsDAO.getInstance();
				int parentseq = Integer.parseInt(request.getParameter("seq"));
				int seq = Integer.parseInt(request.getParameter("seq"));
				System.out.println(seq);
				dao.delete(seq);
				response.sendRedirect("/detail.board?seq="+parentseq);
				
			}

		}

		catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");;
		}














		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
