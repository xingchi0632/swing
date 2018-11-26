package Swing.SeunghaLee;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.TextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;

import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class HouseJoin extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private TextField tfJid;
	private TextField tfJpw;
	private TextField tfJpw2;
	private TextField tfJname;
	private TextField tfJbirth;
	private TextField tfJphone;
	private JButton btnOkid;
	private JButton btnjoinin;
	HouseDBA dba=new HouseDBA();
	private JLabel lblNewLabel_6;
	private TextField tfsname;
	JTextField textfield = new JTextField();
	private JPasswordField Jpwd;
	private JPasswordField Jpwd2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HouseJoin frame = new HouseJoin();
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
	public HouseJoin() {
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 435, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "\uC0AC \uC6A9 \uC790 \uB4F1 \uB85D", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setLayout(null);
			panel.add(getLblNewLabel());
			panel.add(getLblNewLabel_1());
			panel.add(getLblNewLabel_2());
			panel.add(getLblNewLabel_3());
			panel.add(getLblNewLabel_4());
			panel.add(getLblNewLabel_5());
			panel.add(getTfJid());
			panel.add(getJpwd());
			panel.add(getJpwd2());
			panel.add(getTfJname());
			panel.add(getTfJbirth());
			panel.add(getTfJphone());
			panel.add(getBtnOkid());
			panel.add(getBtnjoinin());
			panel.add(getLblNewLabel_6());
			panel.add(getTfsname());
			panel.add(getJpwd());
			panel.add(getJpwd2());
		}
		return panel;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("\uC544\uC774\uB514");
			lblNewLabel.setBounds(33, 41, 57, 15);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
			lblNewLabel_1.setBounds(33, 80, 57, 15);
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("\uBE44\uBC00\uBC88\uD638\uC7AC\uC785\uB825");
			lblNewLabel_2.setBounds(31, 110, 99, 15);
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("\uC774\uB984");
			lblNewLabel_3.setBounds(33, 139, 57, 15);
		}
		return lblNewLabel_3;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
			lblNewLabel_4.setBounds(33, 171, 57, 15);
		}
		return lblNewLabel_4;
	}
	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("\uC804\uD654\uBC88\uD638");
			lblNewLabel_5.setBounds(33, 200, 57, 15);
		}
		return lblNewLabel_5;
	}
	private TextField getTfJid() {
		if (tfJid == null) {
			tfJid = new TextField();
		
			tfJid.setBounds(136, 41, 152, 23);
		}
		return tfJid;
	}

	private TextField getTfJname() {
		if (tfJname == null) {
			tfJname = new TextField();
			tfJname.setBounds(136, 131, 152, 23);
		}
		return tfJname;
	}
	private TextField getTfJbirth() {
		if (tfJbirth == null) {
			tfJbirth = new TextField();
			tfJbirth.setBounds(135, 163, 155, 23);
		}
		return tfJbirth;
	}
	private TextField getTfJphone() {
		if (tfJphone == null) {
			tfJphone = new TextField();
			tfJphone.setBounds(136, 192, 155, 23);
		}
		return tfJphone;
	}
	private JButton getBtnOkid() { /////////////////////아이디검사
		if (btnOkid == null) {
			btnOkid = new JButton("\uC911\uBCF5\uD655\uC778");
			btnOkid.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					HouseBean h=new HouseBean();
					h.setTfJid(tfJid.getText());
				
					dba.check(h);
					
				}
			});
			btnOkid.setBounds(291, 41, 97, 23);
		}
		return btnOkid;
	}
	private JButton getBtnjoinin() {
		if (btnjoinin == null) {
			btnjoinin = new JButton("사용자등록");

				btnjoinin.addActionListener(new ActionListener() {
				
					public void actionPerformed(ActionEvent e) {
						
						String cpw=String.valueOf(Jpwd.getPassword());                          //겟패스워드()의  텍스트값을 가져옴 
						String cpw2=String.valueOf(Jpwd2.getPassword());
						
						
				//	System.out.println(String.valueOf(Jpwd.getPassword()));

						try {		
							
							if(tfJid.getText().equals("")) 	{							
								JOptionPane.showMessageDialog(null,"아이디를 입력하세요");
							}else if(Jpwd.getPassword().equals("")){ 								
								JOptionPane.showMessageDialog(null,"비밀번호를 입력하세요");
							}else if(Jpwd2.getPassword().equals("")) 					{			
								JOptionPane.showMessageDialog(null,"비밀번호재입력란을 입력하세요");
							}else if(tfJname.getText().equals("")) { 								
								JOptionPane.showMessageDialog(null,"이름을 입력하세요");
							}else if(tfJbirth.getText().equals("")) {								
								JOptionPane.showMessageDialog(null," 생년월일을 입력하세요");
							}else if(tfJphone.getText().equals("")){						
								JOptionPane.showMessageDialog(null,"전화번호를 입력하세요"); 	
							}else if(tfsname.getText().equals("")) 		{						
							JOptionPane.showMessageDialog(null,"중개소명을 입력하세요");
							}else if(!cpw.equals(cpw2)){
								 JOptionPane.showMessageDialog(null,"비밀번호가 일치하지 않습니다.");
							}else {
								
							HouseBean h=new HouseBean();
							h.setBirth(tfJbirth.getText().trim());
							h.setId(tfJid.getText().trim());
							//h.setJpwd(cpw);
							//h.setJpwd(String.valueOf(Jpwd.getPassword()));
							//h.setJpwd(Jpwd.getPassword()+"");
							h.setName(tfJname.getText().trim());
							h.setPhone(tfJphone.getText().trim());
							h.setTfsname(tfsname.getText().trim());
							dba.Join(h,cpw);
							 }
								}catch(Exception e5) {
							e5.printStackTrace();
								}
					
					}
				});
			btnjoinin.setBounds(146, 254, 115, 23);
		}
		
		return btnjoinin;
	}
	private JLabel getLblNewLabel_6() {
		if (lblNewLabel_6 == null) {
			lblNewLabel_6 = new JLabel("중개소명");
			lblNewLabel_6.setBounds(33, 229, 57, 15);
		}
		return lblNewLabel_6;
	}
	private TextField getTfsname() {
		if (tfsname == null) {
			tfsname = new TextField();
			tfsname.setBounds(136, 221, 155, 23);
		}
		return tfsname;
	}
	private JPasswordField getJpwd() {
		if (Jpwd == null) {
			Jpwd = new JPasswordField();
			Jpwd.setBounds(136, 77, 152, 21);
		}
		return Jpwd;
	}
	private JPasswordField getJpwd2() {
		if (Jpwd2 == null) {
			Jpwd2 = new JPasswordField();
			Jpwd2.setBounds(135, 107, 153, 21);
		}
		return Jpwd2;
	}
}
