package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import MessageDTO.DTO;

public class MessagesDAO {

	private static MessagesDAO instance;

	synchronized public static MessagesDAO getInstance() throws Exception {

		if (instance == null) {
			instance = new MessagesDAO();
		}
		return instance;
	}

	private MessagesDAO(){}

	private Connection getConnetction() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

	public int insert(String name, String msg) throws Exception {
		String sql = "insert into message values(message_seq.nextval,?,?)";
		try (Connection con = this.getConnetction(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, name);
			pstat.setString(2, msg);
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.close();
			return result;
		}
	}

	public List<DTO> selectAll() throws Exception {
		String sql = "select * from message";
		try (Connection con = this.getConnetction();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {

			List<DTO> list = new ArrayList<>();
			while (rs.next()) {
				DTO dto = new DTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setName(rs.getString("writer"));
				dto.setMsg(rs.getString("message"));
				list.add(dto);
			}
			return list;
		}

	}

	public int delete(int delSeq) throws Exception {
		String sql = "delete from message where seq = ?";
		try (Connection con = this.getConnetction(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, delSeq);
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.commit();
			return result;
		}
	}

	public int update(DTO dto) throws Exception {
		String sql = "update message set writer=?, message=? where seq = ?";
		try (Connection con = this.getConnetction(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getMsg());
			pstat.setInt(3, dto.getSeq());
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.commit();
			return result;
		}
	}

}
