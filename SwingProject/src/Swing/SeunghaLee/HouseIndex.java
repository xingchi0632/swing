package Swing.SeunghaLee;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.TextField;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class HouseIndex extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private TextField tfId;
	private JLabel lblNewLabel_2;
	private JButton btnLogin;
	private JButton btnJoin;
	HouseIndex I;
	HouseDBA dba=new HouseDBA();
	HouseJoin j;
	ArrayList<HouseBean> arr; 
	private TextField tfpw;
	private JPasswordField tfInpw;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HouseIndex frame = new HouseIndex();
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
	public HouseIndex() {
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("환영합니다");
		setBounds(100, 100, 308, 227);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getLblNewLabel());
			panel.add(getLblNewLabel_1());
			panel.add(getTfId());
			panel.add(getLblNewLabel_2());
			panel.add(getBtnLogin());
			panel.add(getBtnJoin());
			panel.add(getTfInpw());
			
		}
		return panel;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("\uC544  \uC774  \uB514");
			lblNewLabel.setBounds(35, 59, 57, 15);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
			lblNewLabel_1.setBounds(35, 104, 57, 15);
		}
		return lblNewLabel_1;
	}
	private TextField getTfId() {
		if (tfId == null) {
			tfId = new TextField();
			tfId.setBounds(99, 59, 121, 23);
			
		}
		return tfId;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("\uBD80\uB3D9\uC0B0 \uB9E4\uBB3C\uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8");
			lblNewLabel_2.setFont(new Font("占쏙옙占쏙옙", Font.BOLD, 18));
			lblNewLabel_2.setBounds(23, 10, 235, 39);
		}
		return lblNewLabel_2;
	}
	private JButton getBtnLogin() {///////////////////////////////////////////////////////////////////////////////////로그인버튼
		if (btnLogin == null) {
			btnLogin = new JButton("\uB85C\uADF8\uC778");
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					String Inpwd=new String(tfInpw.getPassword());
					HouseBean h=new HouseBean();
					h.setId(tfId.getText().toLowerCase().trim());
					dba.login(h,Inpwd);
					dispose();
					
					

					
					
					
				}
			});
			btnLogin.setBounds(171, 146, 81, 23);
		}
		return btnLogin;
	}
	private JButton getBtnJoin() {
		if (btnJoin == null) {
			btnJoin = new JButton("\uC0AC\uC6A9\uC790\uB4F1\uB85D");
			btnJoin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					
					
					j=new HouseJoin();
					j.setVisible(true);
					
					I=new HouseIndex();
					I.setVisible(false);
				}
			});
			btnJoin.setBounds(35, 146, 114, 23);
		}
		return btnJoin;
	}
	private JPasswordField getTfInpw() {
		if (tfInpw == null) {
			tfInpw = new JPasswordField();
			tfInpw.setBounds(99, 101, 121, 21);
		}
		return tfInpw;
	}
}
