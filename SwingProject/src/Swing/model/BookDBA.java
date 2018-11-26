package Swing.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookDBA {
	String url,user,pwd;
	
	public BookDBA() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url="jdbc:oracle:thin:@localhost:1521:xe";
			user="scott";
			pwd="TIGER";
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void bookInsert(BookBean b) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DriverManager.getConnection(url,user,pwd);
			String sql="insert into Book values(BOOK_SEQ.nextval,?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, b.getTitle());
			ps.setString(2, b.getWriter());
			ps.setString(3, b.getIndate());
			ps.setString(4, b.getOutdate());
			ps.setString(5, b.getGubun());
			ps.setInt(6, b.getPrice());
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
	public ArrayList<BookBean> bookView() { 
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<BookBean> arr=new ArrayList<>();
		try {
		con=DriverManager.getConnection(url, user, pwd);
		String sql="select * from Book order by num";
		st=con.createStatement();
		rs=st.executeQuery(sql);
		while(rs.next()) {
			BookBean b=new BookBean();
			b.setNum(rs.getInt("num"));
			b.setTitle(rs.getString("title"));
			b.setWriter(rs.getString("writer"));
			b.setIndate(rs.getString("indate"));
			b.setOutdate(rs.getString("outdate"));
			b.setGubun(rs.getString("gubun"));			
			b.setPrice(rs.getInt("price"));			
			arr.add(b);
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
	
	public void bookDelete(int row) {
		Connection con=null;
		Statement st=null;
		try {
			con=DriverManager.getConnection(url,user,pwd);
			String sql="delete from book where num="+row;
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
	
	public void bookUpdate(BookBean b,int row) {
		Connection con=null;
		Statement st=null;
		PreparedStatement ps=null;
		try {
			con=DriverManager.getConnection(url, user, pwd);
			String sql="update book set title=?,writer=?,indate=?,outdate=?,gubun=?,price=? where num=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, b.getTitle());
			ps.setString(2, b.getWriter());
			ps.setString(3, b.getIndate());
			ps.setString(4, b.getOutdate());
			ps.setString(5, b.getGubun());	
			ps.setInt(6, b.getPrice());			
			ps.setInt(7, row);	
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
}
