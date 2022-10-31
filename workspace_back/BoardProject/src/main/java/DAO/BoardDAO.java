package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DTO.BoardDTO;
import DTO.MembersDTO;

public class BoardDAO {

	private static BoardDAO instance = null;
	public static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}

	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

//	public int insert(int getseq, String writer, String title, String contents, String write_date, int view_count) throws Exception{
//		String sql = "insert into board values(?,?,?,?,?,?)";
//		try(Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);){
//			pstat.setInt(1, getseq);
//			pstat.setString(2, writer);
//			pstat.setString(3, title);
//			pstat.setString(4, contents);
//			pstat.setString(5, write_date);
//			pstat.setInt(6, view_count);
//			int result = pstat.executeUpdate();
//			con.setAutoCommit(false);
//			con.close();
//			return result;
//		}
//	}
	
	public int insert(BoardDTO dto) throws Exception{
		String sql = "insert into board values(?,?,?,?,?,0)";
		try(Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, dto.getSeq());
			pstat.setString(2, dto.getWriter());
			pstat.setString(3, dto.getTitle());
			pstat.setString(4, dto.getContents());
			pstat.setString(5, dto.getWrite_date());
//			pstat.setInt(6, dto.getView_count());
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.close();
			return result;
		}
	}
	
	public int getseq() throws Exception{
		String sql = "select board_seq.nextval from dual";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			try(ResultSet rs = pstat.executeQuery();){
				rs.next();
				return rs.getInt(1);
			}
		}
	}

	public List<BoardDTO> output()throws Exception{
		String sql = "select * from board order by seq desc";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){

			List<BoardDTO> list = new ArrayList<>();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setWrite_date(rs.getString("write_date"));
				dto.setView_count(rs.getInt("view_count"));
				list.add(dto);
			}
			return list;
		}
	}


	public BoardDTO detail(int seq) throws Exception {
		String sql = "select * from board where seq=?";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, seq);
			try (ResultSet rs = pstat.executeQuery();) {
				rs.next();
				BoardDTO dto = new BoardDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setContents(rs.getString("contents"));
				dto.setWrite_date(rs.getString("write_date"));
				dto.setView_count(rs.getInt("view_count"));
				return dto;
			}
		}
	}



	public int deleteList(int seq) throws Exception{
		String sql = "delete from board where seq = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){

			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.close();
			return result;
		}
	}

	public int update(int seq, String contents) throws Exception{
		String sql = "update board set contents=? where seq =?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){

			pstat.setString(1, contents);
			pstat.setInt(2, seq);

			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.close();
			return result;
		}

	}
	public int addViewCount(int seq) throws Exception{
		String sql = "update board set view_count = view_count + 1 where seq=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){

			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.close();
			return result;
		}

	}

	public int getRecordCount() throws Exception{
		String sql = "select count(*) from board";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery()){
			rs.next();
			return rs.getInt(1);
		}
	}

	public List<BoardDTO> selectByRange(int start, int end) throws Exception {
		String sql = "select * from (select board.*, row_number () over(order by seq desc) rn from board) where rn between ? and ?";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, start);
			pstat.setInt(2, end);
			try (ResultSet rs = pstat.executeQuery();) {
				List<BoardDTO> list = new ArrayList<>();
				while(rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setSeq(rs.getInt("seq"));
					dto.setTitle(rs.getString("title"));
					dto.setWriter(rs.getString("writer"));
					dto.setContents(rs.getString("contents"));
					dto.setWrite_date(rs.getString("write_date"));
					dto.setView_count(rs.getInt("view_count"));
					list.add(dto);
				}
				return list;
			}

		}
	}

	public String getPagaNavi(int currentPage) throws Exception{

		int recordTotalCount = this.getRecordCount(); // board 테이블에 총 144개 글이 있다고 가정
		int recordCountPerPage = 10; // 게시판당 10개씩 보여주는 것으로 설정
		int naviCountPerPage = 10; // 게시판 하단의 Page Navigator가 한번에 몇개씩 보여질지 설정

		int pageTotalCount = 0;
		if(recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		}else {		
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		// 게시글의 개수 / 한페이지당 보여줄 게시글 + 1 = 전체페이지의 개수

		//		int currentPage = 3;
		if(currentPage < 1) {
			currentPage =1;
		}else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		// 7 : 1~10
		// 15 : 11~20
		// 28 : 21~30

		int startNavi = (currentPage-1) / naviCountPerPage * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage -1 ;

		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		//		System.out.println("현재 페이지 : " + currentPage);
		//		System.out.println("네이게이터 시작 : " + startNavi);
		//		System.out.println("네이게이터 끝 : " + endNavi);

		boolean needPrev = true ;
		boolean needNext = true ;

		if(startNavi == 1) {needPrev = false;}
		if(endNavi == pageTotalCount) {needNext = false;}

		StringBuilder sb = new StringBuilder();

		if(needPrev) {
			sb.append("<a href='/output.board?cpage="+(startNavi-1)+"'><</a>");
		}
		for(int i = startNavi; i<= endNavi; i++) {
			sb.append("<a href='/output.board?cpage="+i+"'>" + i + " ");
		}
		if(needNext) {
			sb.append("<a href='/output.board?cpage="+(endNavi+1)+"'>></a>");
		}
		return sb.toString();
	}
}
