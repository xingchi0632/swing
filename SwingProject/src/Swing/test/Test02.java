package Swing.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import java.awt.TextArea;

public class Test02 extends JFrame {

	private JPanel contentPane;
	private JSplitPane splitPane;
	private JPanel panel;
	private JLabel lblName;
	private JTabbedPane tabbedPane;
	private JPanel tab1;
	private JPanel tab2;
	private JPanel tab3;
	private JButton button;
	private JTextField tf;
	private JScrollPane scrollPane;
	private TextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test02 frame = new Test02();
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
	public Test02() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getSplitPane(), BorderLayout.CENTER);
	}

	private JSplitPane getSplitPane() {
		if (splitPane == null) {
			splitPane = new JSplitPane();
			splitPane.setDividerSize(1);
			splitPane.setLeftComponent(getPanel());
			splitPane.setRightComponent(getTabbedPane());
			splitPane.setDividerLocation(250);
		}
		return splitPane;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(Color.MAGENTA, 4));
			panel.setLayout(null);
			panel.add(getLblName());
		}
		return panel;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("name");
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			lblName.setFont(new Font("궁서체", Font.BOLD, 40));
			lblName.setForeground(Color.CYAN);
			lblName.setBounds(12, 10, 134, 120);
		}
		return lblName;
	}
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.addTab("첫번째", null, getTab1(), null);
			tabbedPane.addTab("두번째", null, getTab2(), null);
			tabbedPane.addTab("세번째", null, getTab3(), null);
		}
		return tabbedPane;
	}
	private JPanel getTab1() {
		if (tab1 == null) {
			tab1 = new JPanel();
			tab1.setBorder(new LineBorder(Color.PINK, 3, true));
			tab1.setLayout(null);
			tab1.add(getButton());
		}
		return tab1;
	}
	private JPanel getTab2() {
		if (tab2 == null) {
			tab2 = new JPanel();
			tab2.setBackground(Color.GRAY);
			tab2.setBorder(new LineBorder(Color.YELLOW, 4, true));
			tab2.setLayout(null);
			tab2.add(getTf());
		}
		return tab2;
	}
	private JPanel getTab3() {
		if (tab3 == null) {
			tab3 = new JPanel();
			tab3.setBorder(new LineBorder(Color.GREEN, 4));
			tab3.setLayout(new BorderLayout(0, 0));
			tab3.add(getScrollPane(), BorderLayout.NORTH);
			tab3.add(getTextArea(), BorderLayout.CENTER);
		}
		return tab3;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("첫번째탭버튼");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					tabbedPane.setSelectedIndex(1);
					tf.setText("안녕");
				}
			});
			button.setForeground(Color.ORANGE);
			button.setFont(new Font("궁서", Font.PLAIN, 14));
			button.setBounds(48, 118, 142, 45);
		}
		return button;
	}
	private JTextField getTf() {
		if (tf == null) {
			tf = new JTextField();
			tf.setBackground(Color.ORANGE);
			tf.setEditable(false);
			tf.setBounds(66, 53, 116, 21);
			tf.setColumns(10);
		}
		return tf;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
		}
		return scrollPane;
	}
	private TextArea getTextArea() {
		if (textArea == null) {
			textArea = new TextArea();
		}
		return textArea;
	}
}
