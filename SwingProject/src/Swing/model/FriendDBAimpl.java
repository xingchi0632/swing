package Swing.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FriendDBAimpl implements FriendDBA {
	String url,user,pwd;
				
	public FriendDBAimpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url="jdbc:oracle:thin:@localhost:1521:xe";
			user="scott";
			pwd="TIGER";
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//친구추가
	public void friendInsert(Friend f) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DriverManager.getConnection(url,user,pwd);
			String sql="insert into friend values(FRIEND_SEQ.nextval,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, f.getName());
			ps.setString(2, f.getBirth());
			ps.setString(3, f.getPhone());
			ps.setString(4, f.getAddr());
			ps.executeUpdate();   //명령어를 실행
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)ps.close();
				if(con!=null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//친구전체보기
	public ArrayList<Friend> friendView() {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<Friend> arr=new ArrayList<>();
		try {
			con=DriverManager.getConnection(url,user,pwd);
			String sql="select * from friend";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				Friend f=new Friend();
				f.setNum(rs.getInt("num"));
				f.setName(rs.getString("name"));
				f.setBirth(rs.getString("birth"));
				f.setPhone(rs.getString("phone"));
				f.setAddr(rs.getString("addr"));
				arr.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(st!=null)st.close();
				if(con!=null)con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}


	//친구 검색
	public ArrayList<Friend> friendSearch(String str,String txt) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<Friend> arr=new ArrayList<>();
		try {
		con=DriverManager.getConnection(url, user, pwd);
		String sql="select * from friend where "+str+" like '%"+txt+"%'";
		st=con.createStatement();
		rs=st.executeQuery(sql);
		while(rs.next()) {
			Friend f=new Friend();
			f.setNum(rs.getInt("num"));
			f.setName(rs.getString("name"));
			f.setBirth(rs.getString("birth"));
			f.setPhone(rs.getString("phone"));
			f.setAddr(rs.getString("addr"));
			arr.add(f);
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(st!=null) st.close();
				if(con!=null) con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}

	public void friendDelete(int num) {
		Connection con=null;
		Statement st=null;
		try {
			con=DriverManager.getConnection(url,user,pwd);
			String sql="delete from friend where num="+num;
			st=con.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
				e.printStackTrace();
		}finally {
			try {
				if(st!=null)st.close();
				if(con!=null)con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	public void btnUpdate(Friend f) {
		Connection con=null;
		Statement st=null;
		PreparedStatement ps=null;
		try {
			con=DriverManager.getConnection(url, user, pwd);
			String sql="update friend set name=?,birth=?,phone=?,addr=? where num=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, f.getName());
			ps.setString(2, f.getBirth());
			ps.setString(3, f.getPhone());
			ps.setString(4, f.getAddr());
			ps.setInt(5, f.getNum());	
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)ps.close();
				if(con!=null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
	}
	
	public Friend friendSelect(int num) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		Friend f=null;
		
		try {
		con=DriverManager.getConnection(url, user, pwd);
		String sql="select * from friend where num="+num;
		st=con.createStatement();
		rs=st.executeQuery(sql);
		while(rs.next()) {
			f=new Friend();
			f.setNum(rs.getInt("num"));
			f.setName(rs.getString("name"));
			f.setBirth(rs.getString("birth"));
			f.setPhone(rs.getString("phone"));
			f.setAddr(rs.getString("addr"));
			
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(st!=null) st.close();
				if(con!=null) con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return f ;
	}
}



