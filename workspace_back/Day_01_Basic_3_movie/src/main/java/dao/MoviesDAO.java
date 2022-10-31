package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DTO.MoviesDTO;

public class MoviesDAO {

	private static MoviesDAO instance;

	synchronized public static MoviesDAO getInstance() throws Exception{
		if(instance ==null) {
			instance = new MoviesDAO();
		}
		return instance;
	}

	private MoviesDAO(){
	}
	
	
	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}//톰캣에서 자동으로 서버 접속 실행시키는 과정

//	private Connection getConnection() throws Exception {
//		String url = "jdbc:oracle:thin:@localhost:1521:xe";
//		String id = "kh";
//		String pw = "kh";
//		return DriverManager.getConnection(url, id, pw);
//	}

	public int insert(String title, String genre, String launch_date) throws Exception {
		String sql = "insert into movies values(movies_seq.nextval,?,?,?)";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, title);
			pstat.setString(2, genre);
			pstat.setString(3, launch_date);
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.close();
			return result;

		}
	}

	public List<MoviesDTO> selectAll() throws Exception{
		String sql = "select * from movies order by 1";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){

			List<MoviesDTO> list = new ArrayList<>();
			while(rs.next()) {
				MoviesDTO dto = new MoviesDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setgenre(rs.getString("genre"));
				dto.setlaunch_date(rs.getString("launch_date"));
				list.add(dto);
			}
			return list;
		}
	}

	public int delete(int seq) throws Exception{
		String sql = "delete from movies where seq = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){

			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.close();
			return result;
		}	
	}


	public int update(MoviesDTO dto) throws Exception{


		// 1번 방식
		String sql = "update movies set title=?, genre=? where seq=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){

			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getgenre());
			pstat.setInt(3, dto.getSeq());

			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.close();
			return result;
		}	
	}

	public List<MoviesDTO> search(String title) throws Exception{
		String sql = "select * from movies where title like ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, "%"+title+"%");
			try(ResultSet rs = pstat.executeQuery();){
				List<MoviesDTO> slist = new ArrayList<>();
				while(rs.next()) {
					MoviesDTO dto = new MoviesDTO();
					dto.setSeq(rs.getInt("seq"));
					dto.setTitle(rs.getString("title"));
					dto.setgenre(rs.getString("genre"));
					dto.setlaunch_date(rs.getString("launch_date"));
					slist.add(dto);

				}
				return slist;
			}
		}
	}
}
