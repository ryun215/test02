package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import spms.vo.Member;

public class MemberDao {
	Connection connection;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int rowCount;
	public void setConnection(Connection connection){
		System.out.println("Ä¿³Ø¼Ç ¿¬°á");
		this.connection = connection;
		System.out.println("Ä¿³Ø¼Ç È®ÀÎ : " + this.connection);
		
		
	}
	
	//µô¸®Æ®
	public int delete(int no) throws Exception{
		try{
			
		}catch(Exception e){
			throw e;
		}finally{
			stmt = connection.createStatement();
			stmt.executeUpdate(
					"DELETE FROM MEMBERS WHERE MNO=" + 
					no);
			
		}
		return no;
		
	}
	
	
	
	
	//ÀÎ¼­Æ®
	public int insert(Member member) throws Exception{
		
		try{
		pstmt = connection.prepareStatement(
				"INSERT INTO MEMBERS(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)"
				+ " VALUES (?,?,?,NOW(),NOW())");
		pstmt.setString(1, member.getEmail());
		pstmt.setString(2, member.getPassword());
		pstmt.setString(3, member.getName());
		rowCount = pstmt.executeUpdate();
	
		}catch(Exception e){
			throw e;
		}finally{
			try {if (pstmt != null) pstmt.close();} catch(Exception e) {}
		}
		return rowCount;
	}
	
	
	//¼¿·ºÆ®
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
