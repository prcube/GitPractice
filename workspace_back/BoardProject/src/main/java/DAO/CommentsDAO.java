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
import DTO.CommentsDTO;

public class CommentsDAO {

	private static CommentsDAO instance = null;
	public static CommentsDAO getInstance() {
		if(instance == null) {
			instance = new CommentsDAO();
		}
		return instance;
	}

	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

	public int insert(String writer, String contents, String write_date, int seq) throws Exception{
		String sql = "insert into comments values(comments_seq.nextval,?,?,?,?)";
		try(Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);){

			pstat.setString(1, writer);
			pstat.setString(2, contents);
			pstat.setString(3, write_date);
			pstat.setInt(4, seq);

			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.close();
			return result;
		}
	}

	public List<CommentsDTO> output()throws Exception{
		String sql = "select * from comments order by seq";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){

			List<CommentsDTO> list2 = new ArrayList<>();
			while(rs.next()) {
				CommentsDTO dto = new CommentsDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setWriter(rs.getString("writer"));
				dto.setContents(rs.getString("contents"));
				dto.setWrite_date(rs.getString("write_date"));
//				dto.setParentseq(rs.getInt("parentseq"));
				list2.add(dto);
			}
			return list2;
		}
	}
	
	public int update(CommentsDTO dto) throws Exception{
		String sql = "update comments set contents=? where seq=?";
		try(Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getContents());
			pstat.setInt(2, dto.getSeq());
			
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.close();
			return result;
		}
		
	}
	
	public int delete(int seq) throws Exception{
		String sql = "delete from comments where seq = ?";
		try(Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.close();
			return result;
		}
	}







}
