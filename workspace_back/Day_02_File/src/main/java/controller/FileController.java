package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import FilesDAO.FilesDAO;
import dto.FilesDTO;


@WebServlet("*.file")
public class FileController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String uri = request.getRequestURI();
		try {
			if(uri.equals("/upload.file")) {
				int maxSize = 1024*1024*10; // 업로드 파일 최대 사이즈
				String savePath = request.getServletContext().getRealPath("/files"); //webapp로 경로를 설정한 것
				File fileSavaPath = new File(savePath);
				System.out.println(savePath);
				if(!fileSavaPath.exists()) { //경로에 파일이 존재하지 않는다면
					fileSavaPath.mkdir();	//(make directory) 폴더를 만들어라.
				}

				MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF8", new DefaultFileRenamePolicy());
				String oriName = multi.getOriginalFileName("file"); //원본파일. file은 index에서 갖고 온거
				String sysName = multi.getFilesystemName("file");

				FilesDTO dto = new FilesDTO(0,oriName,sysName,0);
				int result = FilesDAO.getInstance().insert(dto);
				response.sendRedirect("index.jsp");
			}
			else if(uri.equals("/list.file")) {
				List<FilesDTO> list = FilesDAO.getInstance().selectALL();
				request.setAttribute("list", list);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
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
