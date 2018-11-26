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
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.TextField;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.awt.Color;

public class HouseMain extends JFrame {

	private JPanel contentPane;
	private JSplitPane splitPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JSplitPane splitPane_1;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel label;
	private JComboBox cbsi;
	private JComboBox cbgum;
	private TextField tfaddr;
	private JLabel label_1;
	private JButton btnSearch;
	private JLabel label_2;
	private TextField tfsi;
	private JLabel lblNewLabel;
	private TextField tfgu;
	private JLabel label_3;
	private TextField tfdong;
	private JComboBox cbtype;
	private JLabel lblNewLabel_1;
	private JComboBox cb4;
	private JComboBox cbtype2;
	private JLabel label_4;
	private TextField tftype;
	private JLabel lblm;
	private JLabel lblNewLabel_2;
	private TextField tfprice;
	private JLabel label_5;
	private JLabel lblNewLabel_3;
	private TextField tfname;
	private JButton btnall;
	private JLabel label_6;
	private TextField tfphone;
	private JLabel label_7;
	private TextField tfwol;
	private JLabel label_8;
	private JLabel label_9;
	private TextField tfetc;
	private JButton btnup;
	private JButton btnre;
	private JLabel lblNewLabel_4;
	private TextField tfday;
	private String rdp;
	private String rdt;
	
	DecimalFormat df=new DecimalFormat("###,###");	

	ArrayList<HouseBean> arr;
	private JLabel label_10;
	private JComboBox cbhyung;
	HouseDBA dba=new HouseDBA();
	private TextField tfsangse;
	private JComboBox cbnow;
	private JButton btndel;
	private int row;
	private JButton btnsave;
	private JButton btnkan;
	private JComboBox cbjun;
	private JPanel panel_2;
	private JCheckBox chball;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HouseMain frame = new HouseMain();
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
	public HouseMain() {
		setTitle("부동산매물등록관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1180, 770);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getSplitPane(), BorderLayout.CENTER);

		btnall.doClick();
		tfwol.setEnabled(false);
	}

	private JSplitPane getSplitPane() {
		if (splitPane == null) {
			splitPane = new JSplitPane();
			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane.setRightComponent(getScrollPane());
			splitPane.setLeftComponent(getSplitPane_1());
			splitPane.setDividerLocation(210);
		}
		return splitPane;
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
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int i=table.getSelectedRow();
					
					tfday.setText(arr.get(i).getTfday());
					tfsi.setText(arr.get(i).getTfsi());
					tfgu.setText(arr.get(i).getTfgu());
					tfdong.setText(arr.get(i).getTfdong());
					tfaddr.setText(arr.get(i).getTfaddr());						
					tftype.setText(arr.get(i).getTftype()+"");
					cbhyung.setSelectedItem(arr.get(i).getCbhyung());
					cbtype.setSelectedItem(arr.get(i).getCbtype());
					cbtype2.setSelectedItem(arr.get(i).getCbtype2());
					tfprice.setText(arr.get(i).getTfprice()+"");
					tfwol.setText(arr.get(i).getTfwol());						
					tfname.setText(arr.get(i).getTfname());
					cbnow.setSelectedItem(arr.get(i).getCbnow());
					tfetc.setText(arr.get(i).getEtc());						
					tfphone.setText(arr.get(i).getTfphone());					
					
					row=arr.get(i).getHnum(); 
					
					btndel.setEnabled(true);
					btnre.setEnabled(true);
					
					
					
				}
			});
			String[] cols= {"매물번호","등록일","주소지(시/도)","시/구/군","동/읍/리","상세주소","평형(m2)","타입","희망가(만원)","월세(만원)","매물종류","종류","매도자이름","연락처","비고","현재"}; 
			DefaultTableModel dt= new DefaultTableModel(cols,0); 
			table.setModel(dt);			
			
		}
		return table;
	}

	private JSplitPane getSplitPane_1() {
		if (splitPane_1 == null) {
			splitPane_1 = new JSplitPane();
			splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane_1.setLeftComponent(getPanel());
			splitPane_1.setRightComponent(getPanel_1());
			splitPane_1.setDividerLocation(90);
		}
		return splitPane_1;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "\uB9E4\uBB3C\uAC80\uC0C9", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setLayout(null);
			panel.add(getLabel());
			panel.add(getCbsi());
			panel.add(getCbgum());
			//panel.add(getTfaddr());
			panel.add(getLabel_1());
			panel.add(getBtnSearch());
			panel.add(getCb4());
			panel.add(getTfsangse());
			panel.add(getPanel_2());
			panel.add(getChball());
			panel.add(getCbjun());
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "\uB9E4\uBB3C\uB4F1\uB85D/\uC218\uC815", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setLayout(null);
			panel_1.add(getLabel_2());
			panel_1.add(getTfsi());
			panel_1.add(getLblNewLabel());
			panel_1.add(getTfgu());
			panel_1.add(getLabel_3());
			panel_1.add(getTfdong());
			panel_1.add(getCbtype());
			panel_1.add(getLblNewLabel_1());
			panel_1.add(getTfaddr());
			panel_1.add(getCbtype2());
			panel_1.add(getLabel_4());
			panel_1.add(getTftype());
			panel_1.add(getLblm());
			panel_1.add(getLblNewLabel_2());
			panel_1.add(getTfprice());
			panel_1.add(getLabel_5());
			panel_1.add(getLblNewLabel_3());
			panel_1.add(getTfname());
			panel_1.add(getLabel_6());
			panel_1.add(getTfphone());
			panel_1.add(getLabel_7());
			panel_1.add(getTfwol());
			panel_1.add(getLabel_8());
			panel_1.add(getLabel_9());
			panel_1.add(getTfetc());
			panel_1.add(getBtnup());
			panel_1.add(getBtnre());
			panel_1.add(getLblNewLabel_4());
			panel_1.add(getTfday());
			panel_1.add(getLabel_10());
			panel_1.add(getCbhyung());
			panel_1.add(getCbnow());
			panel_1.add(getBtndel());
		}
		return panel_1;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("검색");
			label.setBounds(12, 42, 57, 23);
		}
		return label;
	}
	private JComboBox getCbsi() {/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////검색부분
		if (cbsi == null) {
			cbsi = new JComboBox();
			
			
			arr=dba.cbsi();
		
			
				cbsi.addItem("");
			for(int i=0;i<arr.size();i++) {        
	
				cbsi.addItem(arr.get(i).getTfsi());

			}		
			
cbsi.addMouseListener(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent arg0) {
					
				}
});

			cbsi.setBounds(139, 42, 132, 23);
			

		
		}
		return cbsi;
	}
	private JComboBox getCbgum() {
		if (cbgum == null) {
			cbgum = new JComboBox();
			cbgum.setModel(new DefaultComboBoxModel(new String[] {"", "아파트", "주택", "오피스텔", "원룸", "상가", "기타"}));
						
			
			cbgum.setBounds(283, 42, 110, 23);
		}
		return cbgum;
	}

	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("상 세 : ");
			label_1.setBounds(627, 42, 40, 23);
		}
		return label_1;
	}
	private JButton getBtnSearch() {///////////////////////////////////////////////////////////검색
		if (btnSearch == null) {
			btnSearch = new JButton("검색");
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String a=cbsi.getSelectedItem()+"";    //시
					String b=cbgum.getSelectedItem()+"";   //아파트, 원룸..
					String c=tfsangse.getText();                //상세입력 etc문구
					String d=cb4.getSelectedItem()+"";               //전세 월세 등
					int sel=cbjun.getSelectedIndex();
					
					
				arr=dba.search(a,b,d,c,sel);
					String[] cols= {"매물번호","등록일","주소지(시/도)","시/구/군","동/읍/리","상세주소","평형(m2)","타입","희망가(만원)","월세(만원)","매물종류","종류","매도자이름","연락처","비고","현재"}; 
					DefaultTableModel dt= new DefaultTableModel(cols,arr.size()); 
					table.setModel(dt);		
					
					for(int i=0;i<arr.size();i++) {        
						dt.setValueAt(arr.get(i).getHnum(),i,0);
						dt.setValueAt(arr.get(i).getTfday(),i,1);
						dt.setValueAt(arr.get(i).getTfsi(),i,2);
						dt.setValueAt(arr.get(i).getTfgu(),i,3);
						dt.setValueAt(arr.get(i).getTfdong(),i,4);
						dt.setValueAt(arr.get(i).getTfaddr(),i,5);
						dt.setValueAt(arr.get(i).getTftype(),i,6);
						dt.setValueAt(arr.get(i).getCbhyung(),i,7);	
						dt.setValueAt(df.format(arr.get(i).getTfprice()),i,8);
						dt.setValueAt(arr.get(i).getTfwol(),i,9);	
						dt.setValueAt(arr.get(i).getCbtype(),i,10);					
						dt.setValueAt(arr.get(i).getCbtype2(),i,11);						
						dt.setValueAt(arr.get(i).getTfname(),i,12);
						dt.setValueAt(arr.get(i).getTfphone(),i,13);
						dt.setValueAt(arr.get(i).getEtc(),i,14);
						dt.setValueAt(arr.get(i).getCbnow(),i,15);
						
					}
				}
			});
			btnSearch.setBounds(821, 42, 73, 23);
		}
		return btnSearch;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("주소지(시/도)");
			label_2.setBounds(264, 29, 88, 15);
		}
		return label_2;
	}
	private TextField getTfsi() {
		if (tfsi == null) {
			tfsi = new TextField();
			tfsi.setBounds(358, 21, 88, 23);
		}
		return tfsi;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("시/구/군");
			lblNewLabel.setBounds(463, 29, 57, 15);
		}
		return lblNewLabel;
	}
	private TextField getTfgu() {
		if (tfgu == null) {
			tfgu = new TextField();
			tfgu.setBounds(520, 21, 88, 23);
		}
		return tfgu;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("동/읍/리");
			label_3.setBounds(614, 29, 57, 15);
		}
		return label_3;
	}
	private TextField getTfdong() {
		if (tfdong == null) {
			tfdong = new TextField();
			tfdong.setBounds(675, 21, 88, 23);
		}
		return tfdong;
	}
	private JComboBox getCbtype() {
		if (cbtype == null) {
			cbtype = new JComboBox();
			cbtype.setModel(new DefaultComboBoxModel(new String[] {"매물종류", "아파트", "주택", "오피스텔", "원룸", "상가", "기타"}));

				cbtype.setBounds(358, 52, 110, 21);
		}
		return cbtype;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("상세주소");
			lblNewLabel_1.setBounds(778, 29, 57, 15);
		}
		return lblNewLabel_1;
	}
	private TextField getTfaddr() {
		if (tfaddr == null) {
			tfaddr = new TextField();
			tfaddr.setBounds(848, 21, 178, 23);
		}
		return tfaddr;
	}
	private JComboBox getCb4() {
		if (cb4 == null) {
			cb4 = new JComboBox();
			cb4.setModel(new DefaultComboBoxModel(new String[] {"", "전세", "월세", "매매", "기타"}));
			cb4.setBounds(405, 42, 99, 23);
		}
		return cb4;
	}
	private JComboBox getCbtype2() {
		if (cbtype2 == null) {
			cbtype2 = new JComboBox();
			cbtype2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
				
					if (cbtype2.getSelectedIndex()==2)
						tfwol.setEnabled(true);
					else 	tfwol.setEnabled(false);
				}
			});
			cbtype2.setModel(new DefaultComboBoxModel(new String[] {"선택", "전세", "월세", "매매", "기타"}));
			cbtype2.setBounds(479, 52, 100, 21);
		}
		return cbtype2;
	}
	
	ButtonGroup  gr = new ButtonGroup();

	
	
	
	
	
	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("넓이");
			label_4.setBounds(48, 56, 37, 15);
		}
		return label_4;
	}
	private TextField getTftype() {
		if (tftype == null) {
			tftype = new TextField();
			tftype.setBounds(88, 51, 119, 23);
		}
		return tftype;
	}
	private JLabel getLblm() {
		if (lblm == null) {
			lblm = new JLabel("(m2)");
			lblm.setBounds(213, 56, 57, 15);
		}
		return lblm;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("희망가");
			lblNewLabel_2.setBounds(591, 55, 47, 15);
		}
		return lblNewLabel_2;
	}
	private TextField getTfprice() {
		if (tfprice == null) {
			tfprice = new TextField();
			tfprice.setBounds(644, 50, 100, 23);
		}
		return tfprice;
	}
	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("(만원)");
			label_5.setBounds(760, 55, 57, 15);
		}
		return label_5;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("매도자이름");
			lblNewLabel_3.setBounds(48, 88, 71, 15);
		}
		return lblNewLabel_3;
	}
	private TextField getTfname() {
		if (tfname == null) {
			tfname = new TextField();
			tfname.setBounds(119, 80, 133, 23);
		}
		return tfname;
	}
	private JButton getBtnall() {   
		if (btnall == null) {
			btnall = new JButton("전체보기");
			btnall.setBounds(107, 6, 92, 23);
			btnall.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//int sel=cbjun.getSelectedIndex();
					arr=dba.Viewall();					
					
					String[] cols= {"매물번호","등록일","주소지(시/도)","시/구/군","동/읍/리","상세주소","평형(m2)","타입","희망가(만원)","월세(만원)","매물종류","종류","매도자이름","연락처","비고","현재"}; 
					DefaultTableModel dt= new DefaultTableModel(cols,arr.size()); 
					table.setModel(dt);		
					
					for(int i=0;i<arr.size();i++) {        //전체보기
						dt.setValueAt(arr.get(i).getHnum(),i,0);
						dt.setValueAt(arr.get(i).getTfday(),i,1);
						dt.setValueAt(arr.get(i).getTfsi(),i,2);
						dt.setValueAt(arr.get(i).getTfgu(),i,3);
						dt.setValueAt(arr.get(i).getTfdong(),i,4);
						dt.setValueAt(arr.get(i).getTfaddr(),i,5);
						dt.setValueAt(arr.get(i).getTftype(),i,6);
						dt.setValueAt(arr.get(i).getCbhyung(),i,7);	
						dt.setValueAt(df.format(arr.get(i).getTfprice()),i,8);
						dt.setValueAt(arr.get(i).getTfwol(),i,9);	
						dt.setValueAt(arr.get(i).getCbtype(),i,10);					
						dt.setValueAt(arr.get(i).getCbtype2(),i,11);						
						dt.setValueAt(arr.get(i).getTfname(),i,12);
						dt.setValueAt(arr.get(i).getTfphone(),i,13);
						dt.setValueAt(arr.get(i).getEtc(),i,14);
						dt.setValueAt(arr.get(i).getCbnow(),i,15);
						
					}
					}
			});
		}
		return btnall;
	}
	private JLabel getLabel_6() {
		if (label_6 == null) {
			label_6 = new JLabel("연락처");
			label_6.setBounds(257, 88, 47, 15);
		}
		return label_6;
	}
	private TextField getTfphone() {
		if (tfphone == null) {
			tfphone = new TextField();
			tfphone.setBounds(304, 80, 163, 23);
		}
		return tfphone;
	}
	private JLabel getLabel_7() {
		if (label_7 == null) {
			label_7 = new JLabel("월세");
			label_7.setBounds(831, 55, 32, 15);
		}
		return label_7;
	}
	private TextField getTfwol() {
		if (tfwol == null) {
			tfwol = new TextField();
	
			tfwol.setBounds(869, 52, 96, 23);
			

		}
		return tfwol;
	}
	private JLabel getLabel_8() {
		if (label_8 == null) {
			label_8 = new JLabel("(만원)");
			label_8.setBounds(981, 55, 57, 15);
		}
		return label_8;
	}
	private JLabel getLabel_9() {
		if (label_9 == null) {
			label_9 = new JLabel("비고");
			label_9.setBounds(490, 84, 32, 15);
		}
		return label_9;
	}
	private TextField getTfetc() {
		if (tfetc == null) {
			tfetc = new TextField();
			tfetc.setBounds(529, 80, 410, 23);
		}
		return tfetc;
	}
	private JButton getBtnup() {
		if (btnup == null) {
			btnup = new JButton("등록");
			btnup.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						if(tfday.getText()==null)
							JOptionPane.showMessageDialog(null,"매물종류를 입력하세요!!");
						else if(cbtype.getSelectedIndex()==0) 
							JOptionPane.showMessageDialog(null,"매물종류를 선택하세요!!");
						else if(cbtype2.getSelectedIndex()==0) 
								JOptionPane.showMessageDialog(null,"종류를 선택하세요!!");
						else {
					HouseBean h=new HouseBean();
					h.setTfday(tfday.getText());
					h.setTfphone(tfphone.getText());
					h.setTfdong(tfdong.getText());
					h.setEtc(tfetc.getText());
					h.setTfday(tfday.getText());
					h.setTfsi(tfsi.getText());
					h.setTfgu(tfgu.getText());
					h.setTfdong(tfdong.getText());
					h.setTfaddr(tfaddr.getText());
					h.setTftype(Integer.parseInt(tftype.getText()));
					h.setTfprice(Integer.parseInt(tfprice.getText()));
					h.setTfwol(tfwol.getText());
					h.setTfname(tfname.getText());
					h.setCbhyung(cbhyung.getSelectedItem()+"");
					h.setCbtype(cbtype.getSelectedItem()+"");
					h.setCbtype2(cbtype2.getSelectedItem()+"");
					h.setCbnow(cbnow.getSelectedItem()+"");	
					dba.HouseInsert(h);
					JOptionPane.showMessageDialog(null,"등록되었습니다.");
					tfday.setText("");tfgu.setText("");	tfetc.setText("");tfphone.setText("");
					tfsi.setText("");tfdong.setText("");tftype.setText("");tfname.setText("");
					tfwol.setText("");tfaddr.setText("");tfprice.setText("");
					cbtype.setSelectedIndex(0);cbhyung.setSelectedIndex(0);cbtype2.setSelectedIndex(0);
					cbnow.setSelectedIndex(0);cbsi.setSelectedIndex(0);

					btnall.doClick();
					
						}
					
					}catch(Exception e1){
						e1.printStackTrace();
				}
				}
				}
			
					


		
			);
			
			btnup.setBounds(1065, 25, 77, 23);
		}
		return btnup;
	}
	private JButton getBtnre() { //수정
		if (btnre == null) {
			btnre = new JButton("수정");
			btnre.setEnabled(false);
			btnre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						
						if(cbtype.getSelectedIndex()==0) 
							JOptionPane.showMessageDialog(null,"매물종류를 선택하세요!!");
						else if(cbtype2.getSelectedIndex()==0) 
								JOptionPane.showMessageDialog(null,"종류를 선택하세요!!");
						else {
					
					HouseBean h=new HouseBean(); 
					h.setTfday(tfday.getText());
					h.setTfphone(tfphone.getText());
					h.setTfdong(tfdong.getText());
					h.setEtc(tfetc.getText());
					h.setTfday(tfday.getText());
					h.setTfsi(tfsi.getText());
					h.setTfgu(tfgu.getText());
					h.setTfaddr(tfaddr.getText());
					h.setTftype(Integer.parseInt(tftype.getText()));
					h.setTfprice(Integer.parseInt(tfprice.getText()));
					h.setTfwol(tfwol.getText());
					h.setTfname(tfname.getText());
					h.setCbhyung(cbhyung.getSelectedItem()+"");
					h.setCbtype(cbtype.getSelectedItem()+"");
					h.setCbtype2(cbtype2.getSelectedItem()+"");
					h.setCbnow(cbnow.getSelectedItem()+"");	
					h.setNum(row);
					dba.HouseRE(h,row); 
					
					tfday.setText("");tfgu.setText("");	tfetc.setText("");tfphone.setText("");
					tfsi.setText("");tfdong.setText("");tftype.setText("");tfname.setText("");
					tfwol.setText("");tfaddr.setText("");tfprice.setText("");
					cbtype.setSelectedIndex(0);cbhyung.setSelectedIndex(0);cbtype2.setSelectedIndex(0);
					cbnow.setSelectedIndex(0);
					btnall.doClick();
						}
						
					}catch(Exception e1){
						e1.printStackTrace();
				}
					
				}
			});
			btnre.setBounds(1065, 51, 77, 23);
		}
		return btnre;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("등록일");
			lblNewLabel_4.setBounds(48, 29, 47, 15);
		}
		return lblNewLabel_4;
	}
	private TextField getTfday() {
		if (tfday == null) {
			tfday = new TextField();
			tfday.setBounds(99, 21, 159, 23);
		}
		return tfday;
	}
	private JLabel getLabel_10() {
		if (label_10 == null) {
			label_10 = new JLabel("형");
			label_10.setBounds(247, 56, 57, 15);
		}
		return label_10;
	}
	private JComboBox getCbhyung() {
		if (cbhyung == null) {
			cbhyung = new JComboBox();
			cbhyung.setModel(new DefaultComboBoxModel(new String[] {"해당없음", "A", "B", "C", "D", "E", "F", "기타"}));
			cbhyung.setBounds(264, 53, 88, 21);
		}
		return cbhyung;
	}
	private TextField getTfsangse() {
		if (tfsangse == null) {
			tfsangse = new TextField();
			tfsangse.setBounds(670, 42, 145, 23);
		}
		return tfsangse;
	}
	private JComboBox getCbnow() {
		if (cbnow == null) {
			cbnow = new JComboBox();
			cbnow.setModel(new DefaultComboBoxModel(new String[] {"거래희망", "가계약중", "계약완료", "보 류 중"}));
			cbnow.setBounds(955, 78, 100, 21);
		}
		return cbnow;
	}
	private JButton getBtndel() {
		if (btndel == null) {
			btndel = new JButton("삭제");
			btndel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
		
					dba.HouseDelete(row);
					btnall.doClick();
					
				}
			});
			btndel.setEnabled(false);
			btndel.setBounds(1065, 80, 77, 23);
		}
		return btndel;
	}
	private JButton getBtnsave() {
		if (btnsave == null) {
			btnsave = new JButton("저장");
			btnsave.setBounds(107, 39, 92, 23);
			btnsave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					dba.save();
				}
			});
		}
		return btnsave;
	}
	private JButton getBtnkan() {
		if (btnkan == null) {
			btnkan = new JButton("\uC815\uBCF4\uAD00\uB9AC");
			btnkan.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				HouseMem hm=new HouseMem();
				hm.setVisible(true);
				}
			});
			btnkan.setBounds(7, 39, 92, 23);
		}
		return btnkan;
	}
	private JComboBox getCbjun() {
		if (cbjun == null) {
			cbjun = new JComboBox();
			cbjun.setBounds(516, 43, 92, 21);
			cbjun.setModel(new DefaultComboBoxModel(new String[] {"매물번호순", "등록일   순", "도시이름", "구   이름", "동   이름", "평형 (큰)     ", "가격 (비싼)     ", "현재상태"}));
		}
		return cbjun;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_2.setBounds(927, 10, 211, 69);
			panel_2.setLayout(null);
			panel_2.add(getBtnall());
			panel_2.add(getBtnsave());
			panel_2.add(getBtnkan());
		}
		return panel_2;
	}
	private JCheckBox getChball() {
		if (chball == null) {
			chball = new JCheckBox("전체지역");
			chball.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					
					
					if(chball.isSelected()) {
					
					cbsi.setEnabled(false);
					cbsi.setSelectedItem("");
					}else {
						cbsi.setEnabled(true);
					}
				}
			});
		
			chball.setBounds(51, 42, 84, 23);
			}
		
			return chball;
		
		}
	}

