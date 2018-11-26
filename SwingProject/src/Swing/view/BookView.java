package Swing.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Swing.model.BookBean;
import Swing.model.BookDBA;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookView extends JFrame {

	private JPanel contentPane;
	private JSplitPane splitPane;
	private JSplitPane splitPane_1;
	private JSplitPane splitPane_2;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JPanel panel_1;
	private JButton btnInsert;
	private TextField tfTitle;
	private TextField tfWriter;
	private TextField tfIndate;
	private TextField tfOutdate;
	private TextField tfGubun;
	private TextField tfPrice;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JSplitPane splitPane_3;
	private JPanel panel_2;
	private JButton btnView;
	private JScrollPane scrollPane_1;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextArea taView;

	BookDBA dba=new BookDBA();
	ArrayList<BookBean> arr;  //taView에출력해주기 위해 전역으로 변경 
	private int row;
	private JButton btnUpdate;
	private JButton btnDelete;
	DecimalFormat df=new DecimalFormat("###,###"); //1,000이렇게 3가지마다 ,찍히게
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookView frame = new BookView();
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
	public BookView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 694, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getSplitPane(), BorderLayout.CENTER);
//		String[] cols= {"TITLE","WRITER","OUTDATE","PRICE"};
//		DefaultTableModel dt= new DefaultTableModel(cols,0);
//		table.setModel(dt);   //생성자에 굳이 안넣어도 테이블 만들때 넣어져도 됨
	}

	private JSplitPane getSplitPane() {
		if (splitPane == null) {
			splitPane = new JSplitPane();
			splitPane.setLeftComponent(getSplitPane_1());
			splitPane.setRightComponent(getSplitPane_3());
			splitPane.setDividerLocation(350);
		}
		return splitPane;
	}
	private JSplitPane getSplitPane_1() {
		if (splitPane_1 == null) {
			splitPane_1 = new JSplitPane();
			splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane_1.setRightComponent(getSplitPane_2());
			splitPane_1.setLeftComponent(getScrollPane_1_1());
			splitPane_1.setDividerLocation(130);
		}
		return splitPane_1;
	}
	private JSplitPane getSplitPane_2() {
		if (splitPane_2 == null) {
			splitPane_2 = new JSplitPane();
			splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane_2.setLeftComponent(getPanel());
			splitPane_2.setRightComponent(getPanel_1());
			splitPane_2.setDividerLocation(200);
		}
		return splitPane_2;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getLblNewLabel());
			panel.add(getTfTitle());
			panel.add(getTfWriter());
			panel.add(getTfIndate());
			panel.add(getTfOutdate());
			panel.add(getTfGubun());
			panel.add(getTfPrice());
			panel.add(getLblNewLabel_1());
			panel.add(getLblNewLabel_2());
			panel.add(getLblNewLabel_3());
			panel.add(getLblNewLabel_4());
			panel.add(getLblNewLabel_5());
		}
		return panel;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("제목");
			lblNewLabel.setBounds(12, 10, 57, 15);
		}
		return lblNewLabel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.add(getBtnInsert());
		}
		return panel_1;
	}
	private JButton getBtnInsert() {
		if (btnInsert == null) {
			btnInsert = new JButton("입력하기");
			btnInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BookBean b=new BookBean(); //게터 세터 파일이름 
					b.setTitle(tfTitle.getText());
					b.setWriter(tfWriter.getText());
					b.setIndate(tfIndate.getText());
					b.setOutdate(tfOutdate.getText());
					b.setGubun(tfGubun.getText());
					b.setPrice(Integer.parseInt(tfPrice.getText()));
					dba.bookInsert(b);
					btnView.doClick();
				}
			});
			btnInsert.setBounds(0, 0, 345, 69);
		}
		return btnInsert;
	}
	private TextField getTfTitle() {
		if (tfTitle == null) {
			tfTitle = new TextField();
			tfTitle.setBounds(109, 10, 175, 23);
		}
		return tfTitle;
	}
	private TextField getTfWriter() {
		if (tfWriter == null) {
			tfWriter = new TextField();
			tfWriter.setBounds(109, 39, 175, 23);
		}
		return tfWriter;
	}
	private TextField getTfIndate() {
		if (tfIndate == null) {
			tfIndate = new TextField();
			tfIndate.setBounds(109, 68, 175, 23);
		}
		return tfIndate;
	}
	private TextField getTfOutdate() {
		if (tfOutdate == null) {
			tfOutdate = new TextField();
			tfOutdate.setBounds(109, 97, 175, 23);
		}
		return tfOutdate;
	}
	private TextField getTfGubun() {
		if (tfGubun == null) {
			tfGubun = new TextField();
			tfGubun.setBounds(109, 126, 175, 23);
		}
		return tfGubun;
	}
	private TextField getTfPrice() {
		if (tfPrice == null) {
			tfPrice = new TextField();
			tfPrice.setBounds(109, 155, 175, 23);
		}
		return tfPrice;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("저자");
			lblNewLabel_1.setBounds(12, 47, 57, 15);
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("입고날짜");
			lblNewLabel_2.setBounds(12, 76, 57, 15);
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("출고날짜");
			lblNewLabel_3.setBounds(12, 105, 57, 15);
		}
		return lblNewLabel_3;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("책분류");
			lblNewLabel_4.setBounds(12, 134, 57, 15);
		}
		return lblNewLabel_4;
	}
	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("가격");
			lblNewLabel_5.setBounds(12, 163, 57, 15);
		}
		return lblNewLabel_5;
	}
	private JSplitPane getSplitPane_3() {
		if (splitPane_3 == null) {
			splitPane_3 = new JSplitPane();
			splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane_3.setRightComponent(getPanel_2());
			splitPane_3.setLeftComponent(getScrollPane());
			splitPane_3.setDividerLocation(340);
		}
		return splitPane_3;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(null);
			panel_2.add(getBtnView());
			panel_2.add(getBtnUpdate());
			panel_2.add(getBtnDelete());
		}
		return panel_2;
	}
	private JButton getBtnView() {
		if (btnView == null) {
			btnView = new JButton("전체보기");
			btnView.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				
					arr=dba.bookView();   //taView에 출력하기위해서 전역으로 바꿈 
					

					
					String[] cols= {"TITLE","WRITER","OUTDATE","PRICE"};
					DefaultTableModel dt=new  DefaultTableModel(cols,arr.size());
					table.setModel(dt);
					for(int i=0;i<arr.size();i++) {
						dt.setValueAt(arr.get(i).getTitle(),i,0);
						dt.setValueAt(arr.get(i).getWriter(),i,1);
						dt.setValueAt(arr.get(i).getOutdate(),i,2);
						dt.setValueAt(df.format(arr.get(i).getPrice()),i,3);

					}
				}
			});
			btnView.setBounds(0, 0, 97, 65);
		}
		return btnView;
	}
	private JScrollPane getScrollPane_1_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();

			scrollPane_1.setViewportView(getTable());
		}
		return scrollPane_1;
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int i=table.getSelectedRow();
					
					taView.append("번   호: " +arr.get(i).getNum()+"\n");
					taView.append("제   목: " +arr.get(i).getTitle()+"\n");
					taView.append("저   자: " +arr.get(i).getWriter()+"\n");
					taView.append("입고일: " +arr.get(i).getIndate()+"\n");
					taView.append("출고일: " +arr.get(i).getOutdate()+"\n");
					taView.append("구   분: " +arr.get(i).getGubun()+"\n");
					taView.append("가   격: " +df.format(arr.get(i).getPrice())+"\n\n");					
					
	
					tfTitle.setText(arr.get(i).getTitle());
					tfWriter.setText(arr.get(i).getWriter());
					tfIndate.setText(arr.get(i).getIndate());
					tfOutdate.setText(arr.get(i).getOutdate());
					tfGubun.setText(arr.get(i).getGubun());						
					tfPrice.setText(df.format(arr.get(i).getPrice())+"");
					row=arr.get(i).getNum();      //(전역변수지정해놓은)row란이름으로 num을 저장하겠다
				}
		});
			String[] cols= {"TITLE","WRITER","OUTDATE","PRICE"};
			DefaultTableModel dt= new DefaultTableModel(cols,0);
			table.setModel(dt);   //생성자에 굳이 안넣어도 테이블 만들때 넣어져도 됨	
		}
		return table;
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
	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("수정");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BookBean b=new BookBean(); 
					b.setTitle(tfTitle.getText());
					b.setWriter(tfWriter.getText());
					b.setIndate(tfIndate.getText());
					b.setOutdate(tfOutdate.getText());
					b.setGubun(tfGubun.getText());
					b.setPrice(Integer.parseInt(tfPrice.getText().replace(",","")));
					dba.bookUpdate(b,row); //위에 table에서 row 저장한값을 가지고 있음
					btnView.doClick();
					taView.setText("");
				}
			});
			btnUpdate.setBounds(104, 0, 97, 65);
		}
		return btnUpdate;
	}
	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("삭제");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dba.bookDelete(row); //row가 번호를 가지고 있다
					btnView.doClick();
					taView.setText("");
				}
			});
			btnDelete.setBounds(213, 0, 97, 65);
		}
		return btnDelete;
	}
}
