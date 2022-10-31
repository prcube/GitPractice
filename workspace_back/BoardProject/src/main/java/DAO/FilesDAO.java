package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DTO.FilesDTO;

public class FilesDAO {
	
	private static FilesDAO instance = null;
	public static FilesDAO getInstance() {
		if(instance == null) {
			instance = new FilesDAO();
		}
		return instance;
	}

	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	
	public int insert(FilesDTO dto)throws Exception{
		String sql = "insert into files values(files_seq.nextval,?,?,?)";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getOriName());
			pstat.setString(2, dto.getSysName());
			pstat.setInt(3, dto.getParent_seq());
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.close();
			return result;
			
		}
	}
	
	public List<FilesDTO> selectALL() throws Exception{
		String sql = "select * from files";
//		String sql = "select * from files where parent_seq=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery()){
//			pstat.setInt(1, seq);
			
			
			
			List<FilesDTO> list = new ArrayList<>();
			while(rs.next()) {
				FilesDTO dto = new FilesDTO();
				dto.setOriName(rs.getString("OriName"));
				dto.setParent_seq(rs.getInt("Parent_seq"));
				dto.setSysName(rs.getString("SysName"));
				dto.setSeq(rs.getInt("Seq"));
				list.add(dto);
			}
			return list;
			
		}
	}
	
	
	
	
	
	
	
	

}
