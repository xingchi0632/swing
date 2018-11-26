package Swing.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.TextField;
import javax.swing.JButton;
import java.awt.Panel;
import javax.swing.border.TitledBorder;

import Swing.model.Friend;
import Swing.model.FriendDBAimpl;

import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.ScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class FriendView extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextArea taView;
	private JPanel panel_1;
	private JComboBox comSel;
	private TextField tfSearch;
	private JButton btnSearch,btnUpdate,btnDelete;
	
	FriendDBAimpl dba=new FriendDBAimpl();
	private JTextField tfNum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FriendView frame = new FriendView();
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
	public FriendView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 309);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(5, 5, 476, 266);
		contentPane.add(splitPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "\uCE5C\uAD6C\uB4F1\uB85D", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		splitPane.setLeftComponent(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("이 름");
		lblNewLabel.setBounds(25, 31, 57, 15);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("생 일");
		label.setBounds(25, 72, 57, 15);
		panel.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("전화번호");
		lblNewLabel_1.setBounds(25, 111, 57, 15);
		panel.add(lblNewLabel_1);
		
		TextField tfName = new TextField();
		tfName.setBounds(88, 31, 133, 23);
		panel.add(tfName);
		
		TextField tfBirth = new TextField();
		tfBirth.setBounds(88, 64, 133, 23);
		panel.add(tfBirth);
		
		TextField tfPhone = new TextField();
		tfPhone.setBounds(88, 103, 133, 23);
		panel.add(tfPhone);
		
		JLabel label_1 = new JLabel("주 소");
		label_1.setBounds(25, 144, 57, 15);
		panel.add(label_1);
		
		TextField tfAddr = new TextField();
		tfAddr.setBounds(88, 144, 133, 23);
		panel.add(tfAddr);
		
		JButton btnView = new JButton("전체보기");
		btnView.setBounds(12, 173, 97, 23);
		panel.add(btnView);
		
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taView.setText("");
				ArrayList<Friend> arr=dba.friendView();
				for(Friend f:arr) {
					taView.append("번호 : "+f.getNum()+"\n");
					taView.append("이름 : "+f.getName()+"\n");
					taView.append("생일 : "+f.getBirth()+"\n");
					taView.append("전화번호 : "+f.getPhone()+"\n");
					taView.append("주소 : "+f.getAddr()+"\n");
					taView.append("\n");
				}
			}
		});
		
		
		JButton btnInsert = new JButton("추가");
	
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=tfName.getText();
				String birth=tfBirth.getText();
				String phone=tfPhone.getText();
				String addr=tfAddr.getText();
				Friend f=new Friend();
				f.setName(name);  //위에꺼안쓰고 f.setName(tfName.getText()); 써도 됨
				f.setBirth(birth);
				f.setPhone(phone);
				f.setAddr(addr);
				dba.friendInsert(f);
			}
		});
		btnInsert.setBounds(117, 173, 104, 23);
		panel.add(btnInsert);
		
		tfNum = new JTextField();
		tfNum.setBounds(12, 206, 97, 21);
		panel.add(tfNum);
		tfNum.setColumns(10);
		
		JButton btnSelect = new JButton("상세보기");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				int num=Integer.parseInt(tfNum.getText());
				Friend f = dba.friendSelect(num);
				if(f==null) {
					taView.setText("친구없음");
					return;
				}
					tfName.setText(f.getName());
					tfBirth.setText(f.getBirth());
					tfPhone.setText(f.getPhone());
					tfAddr.setText(f.getAddr());
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);
				}catch(NumberFormatException n) {
					JOptionPane.showMessageDialog(null,"숫자를 입력하세요");
				}catch(NullPointerException e2) {
					JOptionPane.showMessageDialog(null,"찾는 친구가 없습니다.");
				}
			}
		});

		btnSelect.setBounds(117, 205, 104, 23);
		panel.add(btnSelect);
		
		 btnUpdate = new JButton("수정");
		btnUpdate.setBounds(12, 231, 97, 23);
		panel.add(btnUpdate);
		btnUpdate.setEnabled(false);
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num=Integer.parseInt(tfNum.getText());
				String name=tfName.getText();
				String birth=tfBirth.getText();
				String phone=tfPhone.getText();
				String addr=tfAddr.getText();
				Friend f=new Friend();
				f.setNum(num);   //f.setNum(Integer.parseInt(tfNum.getText())); 위아래 하나로 써두됨 ,
				f.setName(name);
				f.setBirth(birth);
				f.setPhone(phone);
				f.setAddr(addr);
				dba.btnUpdate(f);
			}
		});
		
		
		 btnDelete = new JButton("삭제");
		btnDelete.setEnabled(false);
		btnDelete.setBounds(117, 231, 104, 23);
		panel.add(btnDelete);
		
		btnDelete.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		int result=JOptionPane.showConfirmDialog(null,"삭제할까요?","Confirm",JOptionPane.YES_NO_OPTION);
			if(result==JOptionPane.YES_OPTION) {
				int num=Integer.parseInt(tfNum.getText());
				dba.friendDelete(num);
				}else {
					tfNum.setText("취소");
				}
			}
		});
		
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "\uC804\uCCB4\uBCF4\uAE30", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_1);
		splitPane_1.setLeftComponent(getScrollPane());
		splitPane_1.setRightComponent(getPanel_1());
		splitPane_1.setDividerLocation(200);
		splitPane.setDividerLocation(240);
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTaView());
		}
		return scrollPane;
	}
	private JTextArea getTaView() {
		if (taView == null) {
			taView = new JTextArea();
		}
		return taView;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.add(getComSel());
			panel_1.add(getTfSearch());
			panel_1.add(getBtnSearch());
		}
		return panel_1;
	}
	private JComboBox getComSel() {
		if (comSel == null) {
			comSel = new JComboBox();
			comSel.setBounds(0, 10, 56, 21);
			comSel.setModel(new DefaultComboBoxModel(new String[] {"선택하세요", "이름", "주소"}));
		}
		return comSel;
	}
	private TextField getTfSearch() {
		if (tfSearch == null) {
			tfSearch = new TextField();
			tfSearch.setBounds(62, 10, 75, 21);
		}
		return tfSearch;
	}
	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("검색");
		
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					taView.setText("");
					int num=comSel.getSelectedIndex();
					String str="";
					if(num==1) {
						str="name";
					}else if(num==2) {
						str="addr";
					}else {
						tfSearch.setText("선택오류");
						return;
					}
					ArrayList<Friend> arr=dba.friendSearch(str,tfSearch.getText());
					for(Friend f:arr) {
						taView.append("번호 : "+f.getNum()+"\n");
						taView.append("이름 : "+f.getName()+"\n");
						taView.append("생일 : "+f.getBirth()+"\n");
						taView.append("전화번호 : "+f.getPhone()+"\n");
						taView.append("주소 : "+f.getAddr()+"\n");
						taView.append("\n");
					}
				}
			});
			btnSearch.setBounds(143, 9, 64, 22);
		}
		return btnSearch;
	}
}
