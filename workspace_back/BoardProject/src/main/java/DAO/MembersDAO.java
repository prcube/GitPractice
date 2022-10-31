package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DTO.MembersDTO;



public class MembersDAO {

	private static MembersDAO instance = null;
	public static MembersDAO getInstance() {
		if(instance == null) {
			instance = new MembersDAO();
		}
		return instance;
	}



	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}


	public boolean isIdExist(String id) throws Exception{
		String sql = "select * from members where id=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, id);
			try(ResultSet rs = pstat.executeQuery();){
				return rs.next();
			}
		}
	}


	public int insert(String id, String pw, String name, String phone, String email, String zipcode, String address1,
			String address2, String signup_date) throws Exception {
		String sql = "insert into members values(?,?,?,?,?,?,?,?,?)";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, id);
			pstat.setString(2, pw);
			pstat.setString(3, name);
			pstat.setString(4, phone);
			pstat.setString(5, email);
			pstat.setString(6, zipcode);
			pstat.setString(7, address1);
			pstat.setString(8, address2);
			pstat.setString(9, signup_date);
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.close();
			return result;

		}
	}

	public boolean login(String id, String pw) throws Exception{
		String sql = "select * from members where id=? and pw=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, id);
			pstat.setString(2, pw);
			try(ResultSet rs = pstat.executeQuery();){
				return rs.next();
			}
		}
	}

	public int memberout(String id) throws Exception{
		String sql = "delete from members where id=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, id);
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.close();
			return result;
		}
	}
	
	public List<MembersDTO> mypage(String id) throws Exception{
		String sql = "Select name, phone, email, signup_date from members where id=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, id);
			ResultSet rs = pstat.executeQuery();
			List<MembersDTO> list = new ArrayList<>();
			while(rs.next()) {
				MembersDTO dto = new MembersDTO();
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setSignup_date(rs.getString("signup_date"));
				list.add(dto);
			}
			return list;
			
		}
		
	}
	
	public int update(MembersDTO dto) throws Exception{
		String sql = "update members set name=?, phone=?, email=? where id=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getPhone());
			pstat.setString(3, dto.getEmail());
			pstat.setString(4, dto.getId());
			
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.close();
			return result;
		}
		
	}


}
