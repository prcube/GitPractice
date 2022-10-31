package controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MembersDAO;
import DTO.MembersDTO;

@WebServlet("*.mem")
public class MembersController extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf8");
		System.out.println("Members Controller 동작");

		String uri = request.getRequestURI();
		System.out.println(uri);
		//중복 ID 확인



		try{
			if(uri.equals("/idcheck.mem")) {
				String id = request.getParameter("id");
				MembersDAO dao = MembersDAO.getInstance();
				boolean result = dao.isIdExist(id);
				response.getWriter().append(String.valueOf(result));
				
//				request.setAttribute("result", result);
//				request.setAttribute("id", id);
//				request.getRequestDispatcher("member/duplCheckView.jsp").forward(request, response);


			}else if(uri.equals("/insert.mem")){
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				String name = request.getParameter("name");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String zipcode = request.getParameter("zipcode");
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");
				SimpleDateFormat mFormat = new SimpleDateFormat("yyy-MM-dd HH:mm");
				String signup_date = mFormat.format(System.currentTimeMillis());

				MembersDAO dao = MembersDAO.getInstance();
				int result = dao.insert(id, pw, name, phone, email, zipcode, address1, address2, signup_date);
				response.sendRedirect("/index.jsp");
				
			}else if(uri.equals("/login.mem")) {
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				MembersDAO dao = MembersDAO.getInstance();
				boolean idresult = dao.login(id,pw);
				if (idresult) {
					System.out.println("로그인성공");
					request.getSession().setAttribute("loginID",id); // 키값: 로그인에 성공했다는 정보를 남기는 것, 밸류값
					response.sendRedirect("/");
				}
				else {
					response.sendRedirect("error.html");
					System.out.println("로그인실패");
				}
				
			}else if(uri.equals("/logout.mem")) {
				request.getSession().invalidate(); //보편적으로 1줄이면 로그아웃은 충분.
				response.sendRedirect("/");
				
			}else if(uri.equals("/memberout.mem")) {
				String memberout = (String) request.getSession().getAttribute("loginID");
				
				MembersDAO dao = MembersDAO.getInstance();
				int result = dao.memberout(memberout);
				request.getSession().invalidate();
				response.sendRedirect("/index.jsp");
				
			}
			else if(uri.equals("/mypage.mem")) {
				String mypage = (String) request.getSession().getAttribute("loginID");
				MembersDAO dao = MembersDAO.getInstance();
				List<MembersDTO> list = dao.mypage(mypage);
				request.setAttribute("list", list);
				request.getRequestDispatcher("/member/mypage.jsp").forward(request, response);
				
			}
			else if(uri.equals("/update.mem")) {
				String id = (String) request.getSession().getAttribute("loginID");
				String name = request.getParameter("name");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				
				MembersDAO dao = MembersDAO.getInstance();
				int result = dao.update(new MembersDTO(id, name, phone, email));
				response.sendRedirect("/mypage.mem");
				
				System.out.println(name + " : " + phone + " : " + email + " : ");
			}
			
			
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");;
		}







	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
