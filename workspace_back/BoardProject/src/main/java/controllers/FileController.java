package controllers;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import DAO.FilesDAO;
import DTO.FilesDTO;

@WebServlet("*.file")
public class FileController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();
		
		String filePath = request.getServletContext().getRealPath("files");
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
//			else if(uri.equals("/list.file")) {
//				List<FilesDTO> list = FilesDAO.getInstance().selectALL();
//				request.setAttribute("list", list);
//				request.getRequestDispatcher("/index.jsp").forward(request, response);
//			}
			else if(uri.equals("/download.file")) {
				String sysName = request.getParameter("sysname");
				String oriName = request.getParameter("oriname");

				System.out.println(filePath+"/"+sysName);//실제 파일의 경로
				File target = new File(filePath+"/"+sysName); //파일의 데이터 크기도 갖고 있음
				

				byte[] fileContents = new byte[(int) target.length()]; //int로 캐스팅
				oriName = new String(oriName.getBytes("utf8"), "ISO-8859-1"); // oriname 한글 안꺠지게해줌. 크롬은 기본 세팅되어있음
				

				response.reset(); //불필요한 코드긴 하지만 혹시나의 에러를 위해서 작성
				response.setHeader("Content-Disposition", "attachment;filename\""+oriName+"\"");

				try(ServletOutputStream sos = response.getOutputStream();
						FileInputStream fis = new FileInputStream(target);//파일이 램으로 들어오는 것
						DataInputStream dis = new DataInputStream(fis);){
					dis.readFully(fileContents); //모든 바이트들이 일루 드감	
					sos.write(fileContents);
					sos.flush();
				}
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
