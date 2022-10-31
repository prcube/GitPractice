package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.MoviesDTO;
import dao.MoviesDAO;


@WebServlet("*.movies")
public class MoviesController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);


		try {
			if(uri.equals("/input.movies")) {
				String title = request.getParameter("title");
				String genre = request.getParameter("genre");
				//			Timestamp launch_date = request.getParameter("launch_date");
				SimpleDateFormat mFormat = new SimpleDateFormat("yyy-MM-dd HH:mm");
				//			SimpleDateFormat mFormat = new DateTimeformat.forPattern("MMMM, yyyy");
				String launch_date = mFormat.format(System.currentTimeMillis());

				MoviesDAO dao = MoviesDAO.getInstance();
				int result = dao.insert(title, genre, launch_date);

				response.sendRedirect("index.jsp"); 

			}else if(uri.equals("/output.movies")) {

				MoviesDAO dao = MoviesDAO.getInstance();
				List<MoviesDTO> list = dao.selectAll();

				request.setAttribute("list", list);
				request.getRequestDispatcher("outputMovie.jsp").forward(request, response);

			}else if(uri.equals("/delete.movies")) {
				int seq = Integer.parseInt(request.getParameter("seq"));

				MoviesDAO dao = MoviesDAO.getInstance();
				int result = dao.delete(seq);
				response.sendRedirect("output.movies");
				
			}else if(uri.equals("/search.movies")) {
				String title = request.getParameter("sid");

				MoviesDAO dao = MoviesDAO.getInstance();
				List<MoviesDTO> slist = dao.search(title);
				System.out.println(slist.size());
				request.setAttribute("slist", slist);
				request.getRequestDispatcher("search.jsp").forward(request, response);

			}else if(uri.equals("/update.movies")){
				int seq = Integer.parseInt(request.getParameter("seq"));
				String title = request.getParameter("title");
				String genre = request.getParameter("genre");
				String launch_date = request.getParameter("launch_date");

				MoviesDAO dao = MoviesDAO.getInstance();
				int result = dao.update(new MoviesDTO(seq,title,genre,launch_date));
				response.sendRedirect("output.movies");
			}
		}catch(Exception e) {
			e.printStackTrace(); // 개발단계에서는 지우면 안댐!!!
			response.sendRedirect("error.jsp"); // 문제가 생기면 브라우저에게 이동하라는 주소 값
		}







	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
