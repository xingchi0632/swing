package Swing.SeunghaLee;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;



import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;


public class HouseMem extends JFrame {
	String idm;
	String pwm;
	DefaultTableModel dt;
	private JPanel contentPane;
	private JSplitPane splitPane;
	private JPanel panel;
	private JLabel label;
	private JLabel lblNewLabel;
	private JTextField tfmid;
	private JButton btnmlogin;
	String url,user,pwd;
	private JPasswordField tfmpw;
	private JSplitPane splitPane_1;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel_1;
	private JLabel lblNewLabel_1;
	private JTextField tf1;
	private JLabel lblNewLabel_2;
	private JPasswordField tf4;
	private JLabel label_1;
	private JPasswordField tf3;
	private JLabel label_2;
	private JTextField tf2;
	private JLabel label_3;
	private JTextField tf5;
	private JLabel label_4;
	private JTextField tf6;
	private JLabel label_5;
	private JTextField tf7;
	private JLabel label_6;
	private JTextField tf8;
	private JButton btnsu;
	private JButton btntal;
	
	HouseMain M;
	HouseBean h;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		


		
		
		EventQueue.invokeLater(new Runnable() {
			

			


			public void run() {
				try {
					HouseMem frame = new HouseMem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	

	
	
	public HouseMem() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 459, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getSplitPane(), BorderLayout.CENTER);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url="jdbc:oracle:thin:@localhost:1521:xe";
			user="scott";
			pwd="TIGER";
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	

	private JSplitPane getSplitPane() {
		if (splitPane == null) {
			splitPane = new JSplitPane();
			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane.setLeftComponent(getPanel());
			splitPane.setRightComponent(getSplitPane_1());
			splitPane.setDividerLocation(120);
		}
		return splitPane;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "\uC0AC\uC6A9\uC790\uC815\uBCF4\uAD00\uB9AC", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setLayout(null);
			panel.add(getLabel());
			panel.add(getLblNewLabel());
			panel.add(getTfmid());
			panel.add(getBtnmlogin());
			panel.add(getTfmpw());
		}
		return panel;
	}
	
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("아 이 디");
			label.setBounds(68, 41, 68, 15);
		}
		return label;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("비밀번호");
			lblNewLabel.setBounds(68, 74, 68, 15);
		}
		return lblNewLabel;
	}
	private JTextField getTfmid() {
		if (tfmid == null) {
			tfmid = new JTextField();
			tfmid.setBounds(148, 41, 116, 21);
			tfmid.setColumns(10);
		}
		return tfmid;
	}
	private JButton getBtnmlogin() {
		if (btnmlogin == null) {
			btnmlogin = new JButton("로그인");
			btnmlogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Connection con=null;
					PreparedStatement ps=null;
					Statement st=null;
					ResultSet rs=null;
					
					idm=tfmid.getText().toLowerCase().trim();
					
					//String pwm=new String(tfmpw.getPassword());
					pwm=String.valueOf(tfmpw.getPassword());
					
					try {
						con=DriverManager.getConnection(url, user, pwd);
						st=con.createStatement();			
						String sql="select * from (select * from loginmember where id='" +idm+ "')";
						rs=st.executeQuery(sql);	
						if(rs.next()==false ||(tfmid.getText().isEmpty())==true) {
							JOptionPane.showMessageDialog(null,"등록된 아이디를 입력하세요");
						}else {
							sql="select * from (select*from loginmember where id='"+idm+"')";
							rs=st.executeQuery(sql);	
							while(rs.next()==true) {
								if(rs.getString("pwd").equals(pwm)) {
									HouseMain m=null;
									if(m==null) {
										m=new HouseMain();
										//m.setVisible(true);
										
										//String sql2="select * from loginmember where id="+idm;
										String sql2="select * from loginmember where id='"+idm+"'";
										//System.out.println("select * from loginmember where id='"+idm+"'");
										//System.out.println("select * from loginmember where pw='"+pwm+"'");										
										st=con.createStatement();
										rs=st.executeQuery(sql2);
										while(rs.next()) {
											h=new HouseBean();
										
											
											h.setNum(rs.getInt("membernum"));
											//System.out.println(h.getNum());
											
											h.setId(rs.getString("id"));
											//System.out.println(h.getId());
											
											h.setPw(rs.getString("pwd"));
											//System.out.println(h.getPw());
											
											h.setName(rs.getString("name"));
											//System.out.println(h.getName());
											
											h.setBirth(rs.getString("birth"));
											//System.out.println(h.getBirth());	
											
											h.setPhone(rs.getString("phone"));
											//System.out.println(h.getPhone());
											
											h.setTfsname(rs.getString("storename"));
											//System.out.println(h.getTfsname());
												
											JOptionPane.showMessageDialog(null,h.getName()+"님 확인되었습니다.");
										String[] cols= {"회원번호","ID","PW","이름","생년월일","연락처","중개소이름"}; 
										DefaultTableModel dt= new DefaultTableModel(cols,1);   //1은 열개수를 말합니다
										table.setModel(dt);	
										
										
										dt.setValueAt(h.getNum(),0,0);
										
										dt.setValueAt(h.getId(),0,1);
										//System.out.println(h.getId());	
										
										dt.setValueAt("####",0,2);
										//System.out.println(h.getPw());	
										
										dt.setValueAt(h.getName(),0,3);
										//System.out.println(h.getName());	
										
										dt.setValueAt(h.getBirth(),0,4);
										//System.out.println(h.getBirth());	
										
										dt.setValueAt(h.getPhone(),0,5);
										//System.out.println(h.getPhone());	
										
										dt.setValueAt(h.getTfsname(),0,6);
										//System.out.println(h.getTfsname());	
										}


											
									}else {
										m=new HouseMain();
								}
								}else {
								JOptionPane.showMessageDialog(null,"비밀번호를 입력하세요");
								}
							}}	
							}catch(SQLException e1) {
						//e1.printStackTrace();
								JOptionPane.showMessageDialog(null,"로그인에 실패하였습니다");
							}
				}
			});
					
			btnmlogin.setBounds(278, 70, 97, 23);
		}
		return btnmlogin;
				
	}
	private JPasswordField getTfmpw() {
		if (tfmpw == null) {
			tfmpw = new JPasswordField();
			tfmpw.setBounds(148, 71, 116, 21);
		}
		return tfmpw;
	}
	private JSplitPane getSplitPane_1() {
		if (splitPane_1 == null) {
			splitPane_1 = new JSplitPane();
			splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane_1.setLeftComponent(getScrollPane());
			splitPane_1.setRightComponent(getPanel_1());
			splitPane_1.setDividerLocation(80);
		}
		return splitPane_1;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			
			
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			String[] cols= {"회원번호","ID","PW","이름","생년월일","연락처","중개소이름"}; 
			DefaultTableModel dt= new DefaultTableModel(cols,0);  
			table.setModel(dt);	
			
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					
					
					String pw2=new String(tfmpw.getPassword());
					
					
					tf1.setText(h.getNum()+"");
					tf2.setText(h.getId());
					tf5.setText(h.getName());
					tf3.setText(pw2);
					tf4.setText(pw2);	
					tf6.setText(h.getBirth());
					tf7.setText(h.getPhone());
					tf8.setText(h.getTfsname());
					

					tf3.setEnabled(true);
					tf4.setEnabled(true);
					tf5.setEnabled(true);
					tf6.setEnabled(true);
					tf7.setEnabled(true);
					tf8.setEnabled(true);
					btnsu.setEnabled(true);
					btntal.setEnabled(true);
					
					
				}
			});
			
			
			
			
		}
		return table;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "\uC218 \uC815", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setLayout(null);
			panel_1.add(getLblNewLabel_1());
			panel_1.add(getTf1());
			panel_1.add(getLblNewLabel_2());
			panel_1.add(getTf4());
			panel_1.add(getLabel_1());
			panel_1.add(getTf3());
			panel_1.add(getLabel_2());
			panel_1.add(getTf2());
			panel_1.add(getLabel_3());
			panel_1.add(getTf5());
			panel_1.add(getLabel_4());
			panel_1.add(getTf6());
			panel_1.add(getLabel_5());
			panel_1.add(getTf7());
			panel_1.add(getLabel_6());
			panel_1.add(getTf8());
			panel_1.add(getBtnsu());
			panel_1.add(getBtntal());
		}
		return panel_1;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("번 호");
			lblNewLabel_1.setBounds(12, 24, 57, 15);
		}
		return lblNewLabel_1;
	}
	private JTextField getTf1() {
		if (tf1 == null) {
			tf1 = new JTextField();
			tf1.setEnabled(false);
			tf1.setBounds(85, 21, 116, 21);
			tf1.setColumns(10);
		}
		return tf1;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("재 입 력");
			lblNewLabel_2.setBounds(12, 120, 57, 15);
		}
		return lblNewLabel_2;
	}
	private JPasswordField getTf4() {
		if (tf4 == null) {
			tf4 = new JPasswordField();
			tf4.setEnabled(false);
			tf4.setBounds(85, 117, 116, 21);
		}
		return tf4;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("비밀번호");
			label_1.setBounds(12, 91, 57, 15);
		}
		return label_1;
	}
	private JPasswordField getTf3() {
		if (tf3 == null) {
			tf3 = new JPasswordField();
			tf3.setEnabled(false);
			tf3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tf3.setText("");
					tf4.setText("");
				}
			});
			tf3.setBounds(85, 88, 116, 21);
		}
		return tf3;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("아 이 디");
			label_2.setBounds(12, 57, 57, 15);
		}
		return label_2;
	}
	private JTextField getTf2() {
		if (tf2 == null) {
			tf2 = new JTextField();
			tf2.setEnabled(false);
			tf2.setColumns(10);
			tf2.setBounds(85, 54, 116, 21);
		}
		return tf2;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("이  름");
			label_3.setBounds(213, 24, 57, 15);
		}
		return label_3;
	}
	private JTextField getTf5() {
		if (tf5 == null) {
			tf5 = new JTextField();
			tf5.setEnabled(false);
			tf5.setColumns(10);
			tf5.setBounds(286, 21, 116, 21);
		}
		return tf5;
	}
	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("생년월일");
			label_4.setBounds(213, 57, 57, 15);
		}
		return label_4;
	}
	private JTextField getTf6() {
		if (tf6 == null) {
			tf6 = new JTextField();
			tf6.setEnabled(false);
			tf6.setColumns(10);
			tf6.setBounds(286, 54, 116, 21);
		}
		return tf6;
	}
	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("연 락 처");
			label_5.setBounds(213, 87, 57, 15);
		}
		return label_5;
	}
	private JTextField getTf7() {
		if (tf7 == null) {
			tf7 = new JTextField();
			tf7.setEnabled(false);
			tf7.setColumns(10);
			tf7.setBounds(286, 84, 116, 21);
		}
		return tf7;
	}
	private JLabel getLabel_6() {
		if (label_6 == null) {
			label_6 = new JLabel("중개소명");
			label_6.setBounds(213, 120, 57, 15);
		}
		return label_6;
	}
	private JTextField getTf8() {
		if (tf8 == null) {
			tf8 = new JTextField();
			tf8.setEnabled(false);
			tf8.setColumns(10);
			tf8.setBounds(286, 117, 116, 21);
		}
		return tf8;
	}
	private JButton getBtnsu() {/////////////////////////////////////////////////////////////////////////수정
		if (btnsu == null) {
			btnsu = new JButton("수정");
			btnsu.setEnabled(false);
			btnsu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					
					
//						String pw1=String.valueOf(tf3.getPassword());
//						String pw2=String.valueOf(tf4.getPassword());
					
					
						String pw1=new String(tf3.getPassword());
						String pw2=new String(tf4.getPassword());						
						
						if (!pw1.equals(pw2)) {
							JOptionPane.showMessageDialog(null,"비밀번호가 일치하지 않습니다.");
						}else if (pw1.equals("")||pw2.equals("")) {
							JOptionPane.showMessageDialog(null,"비밀번호란이 공백입니다.");
						}else {
					
					
					Connection con=null;
					PreparedStatement ps=null;
					try {
						con=DriverManager.getConnection(url,user,pwd);
						String sql="update loginmember set Id=?, pwd=?, name=?, birth=?,phone=?,storename=?  where membernum=?";
						ps=con.prepareStatement(sql);
						ps.setString(1, tf2.getText().trim());
						ps.setString(2, String.valueOf(tf3.getPassword()));
						ps.setString(3, tf5.getText().trim());
						ps.setString(4, tf6.getText().trim());
						ps.setString(5, tf7.getText().trim());
						ps.setString(6, tf8.getText().trim());
						ps.setInt(7, Integer.parseInt(tf1.getText()));
						ps.executeUpdate();   
						JOptionPane.showMessageDialog(null,"수정이 완료 되었습니다.");
						setVisible(false);
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
			});
			btnsu.setBounds(91, 161, 110, 23);
		}
		return btnsu;
	}
	private JButton getBtntal() {
		if (btntal == null) {
			btntal = new JButton("\uD0C8\uD1F4");
			btntal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					int result=JOptionPane.showConfirmDialog(null, "탈퇴하시겠습니까?","Confirm",JOptionPane.YES_NO_OPTION);
					
					if(result==0) {
					
					
					Connection con=null;
					PreparedStatement ps=null;
					Statement st=null;
					
					int num2=Integer.parseInt(tf1.getText());
					
					try {
						con=DriverManager.getConnection(url,user,pwd);
						String sql="delete from loginmember where membernum="+num2;
						st=con.createStatement();
						st.executeQuery(sql);   
						JOptionPane.showMessageDialog(null,"탈퇴가 완료 되었습니다.이용해 주셔서 감사합니다.");
						
						setVisible(false);
						
						M=new HouseMain();
						M.setVisible(false);
						
						
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
					else {
						JOptionPane.showMessageDialog(null,"취소 되었습니다.");
					}
					
					
					
				}
			});
			btntal.setBounds(213, 161, 110, 23);
			btntal.setEnabled(false);
		}
		return btntal;
	}
}


