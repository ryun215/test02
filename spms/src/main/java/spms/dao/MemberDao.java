package spms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import spms.vo.Member;

public class MemberDao {
	Connection connection;
	Statement stmt = null;
	ResultSet rs = null;
	
	public void setConnection(Connection connection){
		System.out.println("目池记 楷搬");
		this.connection = connection;
		System.out.println("目池记 犬牢 : " + this.connection);
		
		
	}
	
	public List<Member> selectList() throws Exception{
		ArrayList<Member> members = new ArrayList<Member>();
		try{
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT MNO,MNAME,EMAIL,CRE_DATE" + 
					" FROM MEMBERS" +
					" ORDER BY MNO ASC");
			
			
			while(rs.next()) {
				members.add(new Member()
							.setNo(rs.getInt("MNO"))
							.setName(rs.getString("MNAME"))
							.setEmail(rs.getString("EMAIL"))
							.setCreatedDate(rs.getDate("CRE_DATE"))	);
			}
		
			
		}catch(Exception e){
			throw e;
		}finally{
			try{if (rs!= null) rs.close();} catch(Exception e){}
			try{if (stmt!= null) stmt.close();} catch(Exception e){}
		}
		return members;
	}
}
