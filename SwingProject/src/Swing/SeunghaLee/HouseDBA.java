package Swing.SeunghaLee;

//import java.nio.channels.SelectionKey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;




public class HouseDBA {
	
String url,user,pwd;

HouseIndex I;


	public HouseDBA() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url="jdbc:oracle:thin:@localhost:1521:xe";
			user="scott";
			pwd="TIGER";
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}	
	
	public void check(HouseBean h) {           ///////////////////////////////////회원가입 아이디 중복확인
		Connection con=null;
		//PreparedStatement ps=null;
		Statement st=null;
		ResultSet rs=null;
		
		String id33=h.getTfJid();
		if(id33.equals("")) {
			JOptionPane.showMessageDialog(null,"아이디를 입력하세요");
		}else {
		
		try {
			con=DriverManager.getConnection(url, user, pwd);
			st=con.createStatement();			
			String sql="select * from (select * from loginmember where id='"+id33+"')";
			rs=st.executeQuery(sql);	
			if(rs.next()==false ||(id33.isEmpty())==true) {
				JOptionPane.showMessageDialog(null,"'"+id33+"'"+"는 사용이 가능합니다");
			}else {
				JOptionPane.showMessageDialog(null,"이미 등록된 아이디입니다.다시입력해주세요.");

				
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
		}
	}

	public void Join(HouseBean h,String cpw) { /////////////////////////////////////////////////////회원가입 버튼
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			con=DriverManager.getConnection(url,user,pwd);
			String sql="insert into loginmember values(LOGINMEMBER_SEQ.nextval,?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, h.getId());
			ps.setString(2, cpw);
			ps.setString(3, h.getName());
			ps.setString(4, h.getBirth());
			ps.setString(5, h.getPhone());
			ps.setString(6, h.getTfsname());			
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null,"사용자가 등록되었습니다.");
		
		
			HouseIndex I=new HouseIndex();
			I.setVisible(false);
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,h.getId()+"는 이미 등록된 아이디입니다.다른 아이디를 입력하세요.");
		}finally {
			try {
				if(ps!=null)ps.close();
				if(con!=null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	}	
	public void login(HouseBean h,String Inpwd) {	////////////////////////////////////////로그인 버튼
		Connection con=null;
		//PreparedStatement ps=null;
		Statement st=null;
		ResultSet rs=null;
		
		
		//System.out.println(Inpwd);

		
		try {
			con=DriverManager.getConnection(url, user, pwd);
			st=con.createStatement();			
			String sql="select * from (select * from loginmember where id='"+h.getId()+"')";
			rs=st.executeQuery(sql);	
			
			if(rs.next()==false ||(h.getId().isEmpty())==true) {
				JOptionPane.showMessageDialog(null,"등록된 아이디를 입력하세요");
			}else {
				String sql3="select * from (select * from loginmember where id='"+h.getId()+"')";
				rs=st.executeQuery(sql3);	

				while(rs.next()==true) {
					if(rs.getString("pwd").equals(Inpwd)) {
						HouseMain m=null;
					
						if(m==null) {
								
							JOptionPane.showMessageDialog(null,h.getId()+"님 로그인되었습니다.");
							
							I=new HouseIndex();
//							I.setVisible(false);
							m=new HouseMain();
							m.setVisible(true);
							
					
						}else {
							m=new HouseMain();
						}
					}else {
						JOptionPane.showMessageDialog(null,"비밀번호를 정확히 입력하세요");
					}
				}
			}
		}catch(SQLException e) {
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null,"비밀번호를 정확히 입력하세요");
		}
		
	}	
	
	
	


	public ArrayList<HouseBean> Viewall() {/////////////////////////////////////////////////////////////////////////////전체보기 버튼
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<HouseBean> arr=new ArrayList<>();

		
	try {
	con=DriverManager.getConnection(url, user, pwd);
	String sql="select * from House";
	st=con.createStatement();
	rs=st.executeQuery(sql);
	while(rs.next()) {
		HouseBean h=new HouseBean();
		h.setHnum(rs.getInt("num"));
		h.setTfday(rs.getString("day"));
		h.setTfsi(rs.getString("si"));
		h.setTfgu(rs.getString("gu"));
		h.setTfdong(rs.getString("dong"));
		h.setTfaddr(rs.getString("addr"));			
		h.setTftype(rs.getInt("type"));		
		h.setCbhyung(rs.getString("type2"));
		h.setTfprice(rs.getInt("price"));
		h.setTfwol(rs.getString("wol"));
		h.setCbtype(rs.getString("builtype"));
		h.setCbtype2(rs.getString("buil"));			
		h.setTfname(rs.getString("Name"));				
		h.setTfphone(rs.getString("phone"));			
		h.setEtc(rs.getString("etc"));
		h.setCbnow(rs.getString("now"));
		arr.add(h);
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
public ArrayList<HouseBean> cbsi(){                          ///////////////////////////////////////////////////// (검색) 시선택 콤보박스
	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	ArrayList<HouseBean> arr=new ArrayList<>();
try {
con=DriverManager.getConnection(url, user, pwd);
String sql="select si from house group by si order by si";
st=con.createStatement();
rs=st.executeQuery(sql);
while(rs.next()) {
	HouseBean h=new HouseBean();
	h.setTfsi(rs.getString("si"));
	arr.add(h);
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
	


public ArrayList<HouseBean> search(String a,String b,String d,String c,int sel) {/////////////////////////////////////////검색
	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	ArrayList<HouseBean> arr=new ArrayList<>();

	
	String gs="";
	switch(sel){
	case 0:gs="num";break;
	case 1:gs="day";break;
	case 2:gs="si";break;
	case 3:gs="gu";break;
	case 4:gs="dong";break;
	case 5:gs="type desc";break;		
	case 6:gs="price desc";break;
	case 7:gs="now";break;		
	}	

try {
con=DriverManager.getConnection(url, user, pwd);
String sql="select *from (select * from house where si like '%"+a+"%' and builtype like '%"+b+"%' and buil like '%"+d+"%') a where a.num like '%"+c+"%' or a.day like '%"+c+"%' or a.si like  '%"+c+"%' or a.gu like '%"+c+"%' or a.dong like '%"+c+"%' or a.addr like '%"+c+"%'  or a.price like '%"+c+"%' or a.wol like '%"+c+"%'  or a.builtype like '%"+c+"%' or a.buil like '%"+c+"%' or a.name like '%"+c+"%'  or a.phone like '%"+c+"%' or a.etc like'%"+c+"%' or a.type2 like '%"+c+"%' or a.now like '%"+c+"%' order by " + gs;



st=con.createStatement();
rs=st.executeQuery(sql);
while(rs.next()) {
	HouseBean h=new HouseBean();
	h.setHnum(rs.getInt("num"));
	h.setTfday(rs.getString("day"));
	h.setTfsi(rs.getString("si"));
	h.setTfgu(rs.getString("gu"));
	h.setTfdong(rs.getString("dong"));
	h.setTfaddr(rs.getString("addr"));			
	h.setTftype(rs.getInt("type"));		
	h.setCbhyung(rs.getString("type2"));
	h.setTfprice(rs.getInt("price"));
	h.setTfwol(rs.getString("wol"));
	h.setCbtype(rs.getString("builtype"));
	h.setCbtype2(rs.getString("buil"));			
	h.setTfname(rs.getString("Name"));				
	h.setTfphone(rs.getString("phone"));			
	h.setEtc(rs.getString("etc"));
	h.setCbnow(rs.getString("now"));
	arr.add(h);
}
} catch (SQLException e1) {
e1.printStackTrace();
}finally {
try {
	if(rs!=null)rs.close();
	if(st!=null)st.close();
	if(con!=null)con.close();
}catch(SQLException e2) {
	e2.printStackTrace();
}
}
	
	return arr;
	
}


public void save() {                                 ////////////////////////////////////////저장
	Connection con=null;
	PreparedStatement ps=null;
	try {
		con=DriverManager.getConnection(url,user,pwd);
		String sql="commit";
		ps=con.prepareStatement(sql);
		ps.executeUpdate();
		JOptionPane.showMessageDialog(null,"저장되었습니다.");
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null,"저장에 실패하였습니다!!!");
	}finally {
		try {
			if(ps!=null)ps.close();
			if(con!=null)con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

public void HouseInsert(HouseBean h) {              ////////////////////////////////////////////////////////////////등록
	Connection con=null;
	PreparedStatement ps=null;
	try {
		con=DriverManager.getConnection(url,user,pwd);
		String sql="insert into house values(HOUSE_SEQ.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		ps=con.prepareStatement(sql);
		ps.setString(1, h.getTfday());
		ps.setString(2, h.getTfsi());
		ps.setString(3, h.getTfgu());
		ps.setString(4, h.getTfdong());
		ps.setString(5, h.getTfaddr());
		ps.setInt(6, h.getTftype());
		ps.setInt(7, h.getTfprice());
		ps.setString(8, h.getTfwol());		
		ps.setString(9, h.getCbtype()+"");
		ps.setString(10, h.getCbtype2()+"");
		ps.setString(11, h.getTfname());
		ps.setString(12, h.getTfphone());
		ps.setString(13, h.getEtc());		
		ps.setString(14, h.getCbhyung()+"");	
		ps.setString(15, h.getCbnow()+"");
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


public void HouseDelete(int row) { ////////////////////////////////////////////////////////////////////////////////삭제
	Connection con=null;
	Statement st=null;
	
	int result=JOptionPane.showConfirmDialog(null, "삭하시겠습니까?","Confirm",JOptionPane.YES_NO_OPTION);
	
	if(result==0) {
	
	
			try {
				con=DriverManager.getConnection(url,user,pwd);
				String sql="delete from house where num="+row;
				st=con.createStatement();
				st.executeQuery(sql);
				JOptionPane.showMessageDialog(null,"삭제되었습니다.");
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
			}else {
				JOptionPane.showMessageDialog(null,"취소 되었습니다.");
			}
			

}
public void HouseRE(HouseBean h,int row) {  ////////////////////////////////////////////////////////// 수정버튼
	Connection con=null;
	//Statement st=null;
	PreparedStatement ps=null;
	try {
		con=DriverManager.getConnection(url, user, pwd);
		String sql="update house set day=?,si=?,gu=?,dong=?,addr=?,type=?,price=?,wol=?, builtype=?,buil=?,name=?,phone=?,etc=?,type2=?,now=? where num=?";
		ps=con.prepareStatement(sql);
		
		ps.setString(1, h.getTfday());  //1
		//System.out.println(h.getTfday());		
		
		ps.setString(2, h.getTfsi()); //2
		//System.out.println(h.getTfsi());		
		
		ps.setString(3, h.getTfgu()); //3
		//System.out.println(h.getTfgu());
		
		ps.setString(4, h.getTfdong()); //4
		//System.out.println(h.getTfdong());
		
		ps.setString(5, h.getTfaddr());	 //5
		//System.out.println(h.getTfaddr());
		
		ps.setInt(6, h.getTftype()); //6
		//System.out.println(h.getTftype());
		

		
		ps.setInt(7, h.getTfprice()); //10
		//System.out.println(h.getTfprice());

		ps.setString(8, h.getTfwol()); //11
		//System.out.println(h.getTfwol());


		ps.setString(9, h.getCbtype()); //8
		//System.out.println(h.getCbtype());

		ps.setString(10, h.getCbtype2());  //9
		//System.out.println(h.getCbtype2());	
		
		ps.setString(11, h.getTfname()); //12
		//System.out.println(h.getTfname());
		
		ps.setString(12, h.getTfphone()); //13
		//System.out.println(h.getTfphone());
		
		ps.setString(13, h.getEtc()); //14
		//System.out.println(h.getEtc());
		

		
		ps.setString(14, h.getCbhyung());	//7
		//System.out.println(h.getCbhyung());
		
		ps.setString(15, h.getCbnow());		 //15
		//System.out.println(h.getCbnow());
		
		ps.setInt(16,row);  //16
		//System.out.println(row);
		
		ps.executeUpdate();
		JOptionPane.showMessageDialog(null,"수정되었습니다.");
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

		
	

