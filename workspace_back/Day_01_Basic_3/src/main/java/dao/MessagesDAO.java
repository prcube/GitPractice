package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DTO.MessagesDTO;

public class MessagesDAO {

	private static MessagesDAO instance;

	synchronized public static MessagesDAO getInstance() throws Exception{
		if(instance == null) {
			instance = new MessagesDAO();
		}
		return instance;
	}


	private MessagesDAO(){
		
	}



	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
//		String url = "jdbc:oracle:thin:@localhost:1521:xe";
//		String id = "kh";
//		String pw = "kh";
//		return DriverManager.getConnection(url, id, pw);
	}

	public int insert(String name, String msg) throws Exception {
		String sql = "insert into messages values(messages_seq.nextval,?,?)";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, name);
			pstat.setString(2, msg);
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.close();
			return result;
		}
	}

	public List<MessagesDTO> selectAll() throws Exception{
		String sql = "select * from messages order by 1";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){

			List<MessagesDTO> list = new ArrayList<>();
			while(rs.next()) {
				MessagesDTO dto = new MessagesDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setWriter(rs.getString("writer"));
				dto.setMessages(rs.getString("message"));
				list.add(dto);
			}
			return list;
		}
	}

	public int delete(int seq) throws Exception{
		String sql = "delete from messages where seq = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){

			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.close();
			return result;
		}	
	}


	public int update(MessagesDTO dto) throws Exception{


		// 1번 방식
		String sql = "update messages set writer=?, message=? where seq=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){

			pstat.setString(1, dto.getWriter());
			pstat.setString(2, dto.getMessages());
			pstat.setInt(3, dto.getSeq());

			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.close();
			return result;
		}	
	}

	//		2번방식
	//		public int update(int seq, String writer, String message) throws Exception{
	//			
	//			String sql = "update messages set writer=?, message=? where seq=?";
	//			try(Connection con = this.getConnection();
	//					PreparedStatement pstat = con.prepareStatement(sql);){
	//			
	//				pstat.setString(1, writer);
	//				pstat.setString(2, message);
	//				pstat.setInt(3, seq);
	//				
	//				int result = pstat.executeUpdate();
	//				con.setAutoCommit(false);
	//				con.close();
	//				return result;
	//			}	
	//		}
}