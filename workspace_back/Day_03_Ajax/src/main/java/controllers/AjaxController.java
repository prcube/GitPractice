package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import DTO.BoardDTO;

@WebServlet("*.ajax")
public class AjaxController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String uri = request.getRequestURI();

		if(uri.equals("/exam01.ajax")) {
			System.out.println("Exam01 : 비동기 통신 도착");
		}else if(uri.equals("/exam02.ajax")) {
			String p1 = request.getParameter("key1");
			String p2 = request.getParameter("key2");
			System.out.println(p1 + " : " + p2);
		}else if(uri.equals("/exam03.ajax")) {
			System.out.println("exam03 동작");
			response.getWriter().append("Ajax Works!");
		}else if(uri.equals("/exam04.ajax")) {
			Gson g= new Gson();
			System.out.println("exam04 동작");
			String[] arr = new String[] {"Apple", "Orange", "Mango"};
			String jsonString = g.toJson(arr);
			response.getWriter().append(jsonString);

			//			response.getWriter().append("[\"Apple\",\"Orange\",\"Mango\"]");
		}else if(uri.equals("/exam05.ajax")) {

			Gson g= new Gson();
			BoardDTO dto = new BoardDTO(10,"Tester","TItle","Contents",null,0);
			String jsonString = g.toJson(dto);
			response.getWriter().append(jsonString);
			
		}else if(uri.equals("/exam06.ajax")) {

			List<BoardDTO> list = new ArrayList<>();
			list.add(new BoardDTO(10,"Tester1","TItle1","Contents1",null,0));
			list.add(new BoardDTO(11,"Tester2","TItle2","Contents2",null,0));
			list.add(new BoardDTO(12,"Tester3","TItle3","Contents3",null,0));
			
			Gson g = new Gson();
			String jsonString = g.toJson(list);
			response.getWriter().append(jsonString);
			
			
			//타입이 없는 데이터들을 직렬화 하기 위해서 exam07처럼 사용을 함.
		}else if(uri.equals("/exam07.ajax")) {
			
			JsonObject total = new JsonObject();
			
			
			JsonObject obj = new JsonObject();
			obj.addProperty("emp_id", "101");
			obj.addProperty("emp_name", "Jack");
			obj.addProperty("dept_title", "해외영업부");
			obj.addProperty("job_name", "대리");
			
			
			
			BoardDTO dto = new BoardDTO(10,"Tester1","TItle1","Contents1",null,0);
			
			total.addProperty("obj", obj.toString());
			total.addProperty("dto", new Gson().toJson(dto));
			
			
			response.setContentType("text/html;charset=utf8"); //한글 깨질 때 처리하는 것
			response.getWriter().append(total.toString());
			
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
