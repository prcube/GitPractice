package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Temp {

	public static void main(String[] args) throws Exception {


		Class.forName("oracle.jdbc.driver.OracleDriver");

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "kh";
		String pw = "kh";
		String sql = "insert into board values(board_seq.nextval,?,?,?, sysdate, 0 )";

		Connection con = DriverManager.getConnection(url,id,pw);
		PreparedStatement pstat = con.prepareStatement(sql);

		for(int i =1; i<145;i++) {
			pstat.setString(1, "Test"+i);
			pstat.setString(2, "Title"+i);
			pstat.setString(3, "Contents"+i);
			pstat.execute();
			Thread.sleep((long)Math.random()*(700-200+1)+200);
			System.out.println(i + " 번째 데이터 : Test" + i);
		}
		con.commit();
		con.close();

	}

}
