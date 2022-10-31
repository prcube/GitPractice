package controllers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import DAO.BoardDAO;
import DAO.CommentsDAO;
import DAO.FilesDAO;
import DTO.BoardDTO;
import DTO.CommentsDTO;
import DTO.FilesDTO;



@WebServlet("*.board")
public class BoardController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf8");
		String uri = request.getRequestURI();


		try{
			if(uri.equals("/list.board")) {
				
				
				
				response.sendRedirect("output.board");

			}else if(uri.equals("/insert.board")) {
				String id = (String) request.getSession().getAttribute("loginID");
				
				
				
				int maxSize = 1024*1024*10; // 업로드 파일 최대 사이즈
				String savePath = request.getServletContext().getRealPath("/files"); //webapp로 경로를 설정한 것
				File fileSavaPath = new File(savePath);
				if(!fileSavaPath.exists()) { //경로에 파일이 존재하지 않는다면
					fileSavaPath.mkdir();	//(make directory) 폴더를 만들어라.
				}
				MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF8", new DefaultFileRenamePolicy());
				

				
				
				
				
				String title = multi.getParameter("title");
				String contents = multi.getParameter("contents");
//				String writer = multi.getParameter("writer");
				SimpleDateFormat mFormat = new SimpleDateFormat("yyy-MM-dd HH:mm");
				String write_date = mFormat.format(System.currentTimeMillis());
//				int view_count = Integer.parseInt(multi.getParameter("view_count"));
//				System.out.println(id+" "+title+" "+contents+" "+write_date+" "+view_count);
				BoardDAO dao = BoardDAO.getInstance();
				
				
				int getseq = new BoardDAO().getInstance().getseq();
				BoardDTO dto2 = new BoardDTO(getseq,id,title,contents,write_date,0);
				
				
				
				int result = dao.insert(dto2);
				
				Enumeration<String> e = multi.getFileNames(); //ArrayList라고 생각
				//파일 첨부하기
				while(e.hasMoreElements()) { //result set의 next와 비슷한 개념이라고 생각하면댐 
					String name = e.nextElement();
					String oriName = multi.getOriginalFileName(name); //원본파일. file은 index에서 갖고 온거
					if(oriName == null) {continue;} // 3개중 2개만 첨부하고 비어있는 파일이 넘어오는 것을 무시하게 해줌
					String sysName = multi.getFilesystemName(name);
					FilesDAO.getInstance().insert(new FilesDTO(0,oriName,sysName,getseq));
				}
				
				
				
				response.sendRedirect("/output.board?cpage=1");

			}else if(uri.equals("/output.board")) {
				BoardDAO dao = BoardDAO.getInstance();
				int cpage = Integer.parseInt(request.getParameter("cpage"));
				
//				List<BoardDTO> list = dao.output();
				List<BoardDTO> list = BoardDAO.getInstance().selectByRange(cpage*10-9,cpage*10);
			
				String navi = BoardDAO.getInstance().getPagaNavi(cpage);	//navi 부분 갖고 오는거 
				request.setAttribute("navi", navi); //navi 부분 갖고 오는거 
				request.setAttribute("list", list);
				request.getRequestDispatcher("/board/boardlist.jsp").forward(request, response);

			}
			else if(uri.equals("/detail.board")) {
				BoardDAO dao = BoardDAO.getInstance();
				int seq = Integer.parseInt(request.getParameter("seq"));
				
				CommentsDAO dao2 = CommentsDAO.getInstance();
				List<CommentsDTO> list2 = dao2.output();
				List<FilesDTO> list = FilesDAO.getInstance().selectALL();
				
				
				String contents = request.getParameter("contents");
				BoardDAO.getInstance().addViewCount(seq);
				
				BoardDTO dto = dao.detail(seq);
				String id = (String) request.getSession().getAttribute("loginID");
				
				request.setAttribute("DTO", dto);
				request.setAttribute("loginID", id);
				request.setAttribute("list2", list2);
				request.setAttribute("list", list);
				request.getRequestDispatcher("/board/detail.jsp").forward(request, response);

			}else if(uri.equals("/deleteList.board")) {
				BoardDAO dao = BoardDAO.getInstance();
				int seq = Integer.parseInt(request.getParameter("seq"));
				dao.deleteList(seq);
				response.sendRedirect("/output.board");	
				
				
				//sql로 수정할 값을 넣는 부분
			}else if(uri.equals("/updateList.board")) {
				BoardDAO dao = BoardDAO.getInstance();
				int seq = Integer.parseInt(request.getParameter("seq"));
				String contents = request.getParameter("contents");
				
				int result = dao.update(seq, contents);
				System.out.println(seq + " : " + contents);
				
				request.getRequestDispatcher("/detail.board").forward(request, response);
				
				
				// detail에서 수정하러 가는 창 부분
			}else if(uri.equals("/update.board")) {
				BoardDAO dao = BoardDAO.getInstance();
				int seq = Integer.parseInt(request.getParameter("seq"));
//				String contents = request.getParameter("contents");
			
				
				System.out.println(seq);
				
				BoardDTO dto = dao.detail(seq);
				request.setAttribute("DTO", dto);
				String id = (String) request.getSession().getAttribute("loginID");
				request.setAttribute("loginID", id);
				request.getRequestDispatcher("/board/updateboard.jsp").forward(request, response);

			}
			
			
//			else if(uri.equals("/updateList.board")) {
//				request.setCharacterEncoding("utf-8");
//				BoardDAO dao = BoardDAO.getInstance();
//				
//				int seq = Integer.parseInt(request.getParameter("seq"));
//				String contents = request.getParameter("contents");
//				dao.update(seq, contents);
//				response.sendRedirect("updateList.board");
//			}
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
