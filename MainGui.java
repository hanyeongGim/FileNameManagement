import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;

import org.eclipse.ui.internal.handlers.WidgetMethodHandler;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.BoxLayout;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import java.awt.Font;

public class MainGui {

	private JFrame frame;
	private JTable fileNameList;
	
	private JScrollPane jscrollPane;
	
	String columnNames[] = {" 변경 전 리스트 ", " 변경 후 리스트 "};
	private DefaultTableModel modelJTable = new DefaultTableModel(columnNames, 0)
	{
		private static final long serialVersionUID = 2336052481757166098L;
		public boolean isCellEditable(int row, int column)
		{
			return false;
		}
	};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui window = new MainGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws UnsupportedLookAndFeelException 
	 */
	public MainGui() throws UnsupportedLookAndFeelException {
		
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("File Name Modify System");
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, frame.getWidth()-6, frame.getHeight()/20);
		frame.getContentPane().add(menuPanel);
		menuPanel.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		
		//메뉴 바 간격 조정
		menuBar.setLayout( new FlowLayout(FlowLayout.LEFT, 10, 0));
		menuPanel.add(menuBar);
		
		JMenu mnInsertString = new JMenu("문자열 삽입");
		mnInsertString.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		//mnInsertString.setVerticalAlignment(SwingConstants.CENTER);
		menuBar.add(mnInsertString);
		
		JMenuItem mn_InsertAtLocation = new JMenuItem("원하는 위치에 문자열 삽입");
		mn_InsertAtLocation.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		mnInsertString.add(mn_InsertAtLocation);
		
		JMenuItem mn_insertByRule = new JMenuItem("규칙에 맞춰 문자열 삽입");
		mn_insertByRule.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		mnInsertString.add(mn_insertByRule);
		
		JMenu mnDeleteString = new JMenu("문자열 제거");
		mnDeleteString.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		menuBar.add(mnDeleteString);
		
		JMenuItem mn_DeleteAtLocation = new JMenuItem("원하는 위치의 문자열 제거");
		mn_DeleteAtLocation.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		mnDeleteString.add(mn_DeleteAtLocation);
		
		JMenuItem mn_DeleteOnly = new JMenuItem("원하는 문자열만 제거");
		mn_DeleteOnly.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		mnDeleteString.add(mn_DeleteOnly);
		
		JMenuItem mn_DeleteExcept = new JMenuItem("원하는 문자열 외 모두 제거");
		mn_DeleteExcept.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		mnDeleteString.add(mn_DeleteExcept);
		
		JMenu mnUserForm = new JMenu("\uC0AC\uC6A9\uC790 \uC11C\uC2DD");
		mnUserForm.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		menuBar.add(mnUserForm);
		
		JMenuItem mn_userForm = new JMenuItem("\uC0AC\uC6A9\uC790 \uC11C\uC2DD");
		mn_userForm.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		mnUserForm.add(mn_userForm);
		
		JMenu mnHelp = new JMenu("\uB3C4\uC6C0\uB9D0");
		mnHelp.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		menuBar.add(mnHelp);
		
		JMenuItem mn_InsertHelp = new JMenuItem("\uBB38\uC790\uC5F4 \uC0BD\uC785 \uB3C4\uC6C0\uB9D0");
		mn_InsertHelp.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		mnHelp.add(mn_InsertHelp);
		
		JMenuItem mn_DeleteHelp = new JMenuItem("\uBB38\uC790\uC5F4 \uC81C\uAC70 \uB3C4\uC6C0\uB9D0");
		mn_DeleteHelp.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		mnHelp.add(mn_DeleteHelp);
		
		JMenuItem mn_UseFormHelp = new JMenuItem("\uC0AC\uC6A9\uC790 \uC11C\uC2DD \uB3C4\uC6C0\uB9D0");
		mn_UseFormHelp.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		mnHelp.add(mn_UseFormHelp);
		
		JMenuItem mn_About = new JMenuItem("About FNM System");
		mn_About.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		mnHelp.add(mn_About);
		
		JPanel fileModifyPanel = new JPanel();
		fileModifyPanel.setBounds(0, menuPanel.getHeight(), frame.getWidth()-6, frame.getHeight()/25);
		frame.getContentPane().add(fileModifyPanel);
		fileModifyPanel.setLayout(new GridLayout(0, 2, 0, 0));
								
								JPanel fileModifyLeftPanel = new JPanel();
								fileModifyPanel.add(fileModifyLeftPanel);
								fileModifyLeftPanel.setLayout(null);
								
								JButton buttonFileUp = new JButton("\uC120\uD0DD \uD30C\uC77C\u2191");
								buttonFileUp.setBounds(12, 5, 97, 23);
								fileModifyLeftPanel.add(buttonFileUp);
								
								JButton buttonFileDown = new JButton("\uC120\uD0DD \uD30C\uC77C\u2193");
								buttonFileDown.setBounds(121, 5, 97, 23);
								fileModifyLeftPanel.add(buttonFileDown);
								
								JPanel fileModifyRightPanel = new JPanel();
								fileModifyPanel.add(fileModifyRightPanel);
								fileModifyRightPanel.setLayout(new BorderLayout(0, 0));
								
								JPanel checkBoxPanel = new JPanel();
								fileModifyRightPanel.add(checkBoxPanel, BorderLayout.CENTER);
								checkBoxPanel.setLayout(new BorderLayout(10, 0));
								
								JCheckBox checkbox_grouping = new JCheckBox("이름 변경 시 폴더로 분류");
								checkBoxPanel.add(checkbox_grouping, BorderLayout.CENTER);
								
										checkbox_grouping.setHorizontalAlignment(SwingConstants.RIGHT);
										
										JButton buttonUndo = new JButton("\uB418\uB3CC\uB9AC\uAE30");
										checkBoxPanel.add(buttonUndo, BorderLayout.EAST);
										
										JPanel modifyButtonPanel = new JPanel();
										fileModifyRightPanel.add(modifyButtonPanel, BorderLayout.EAST);
										modifyButtonPanel.setLayout(new BorderLayout(0, 0));
										
										JButton buttonFileModify = new JButton("파일 변경");
										buttonFileModify.setPreferredSize( new Dimension( fileModifyPanel.getWidth()/5, fileModifyPanel.getHeight()) );
										
												modifyButtonPanel.add(buttonFileModify);
												buttonFileModify.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		
		
		
		JPanel jTablePanel = new JPanel();
		jTablePanel.setBounds(0, menuPanel.getHeight() + fileModifyPanel.getHeight(), frame.getWidth()-6, frame.getHeight() - menuPanel.getHeight() - fileModifyPanel.getHeight() -30 );
		jTablePanel.setLayout(new BorderLayout(0, 0));
				
		fileNameList = new JTable(modelJTable);
		jTablePanel.add(fileNameList);
		fileNameList.setSize(frame.getWidth(), frame.getHeight() - menuPanel.getHeight() - fileModifyPanel.getHeight());
		
		
		
		fileNameList.setRowHeight(25);
		fileNameList.getColumnModel().getColumn(0).setPreferredWidth(245);
		fileNameList.getColumnModel().getColumn(1).setPreferredWidth(246);	
		
		jscrollPane = new JScrollPane(fileNameList);
		jTablePanel.add(jscrollPane, BorderLayout.CENTER);
		
		
		addRow("aa.txt", "Ben Brode");
		addRow("cc.txt", "Dustin Browder");
		addRow("aa.txt", "Ben Brode");
		addRow("cc.txt", "Dustin Browder");
		addRow("aa.txt", "Ben Brode");
		addRow("cc.txt", "Dustin Browder");
		addRow("aa.txt", "Ben Brode");
		addRow("cc.txt", "Dustin Browder");
		addRow("aa.txt", "Ben Brode");
		addRow("cc.txt", "Dustin Browder");
		addRow("aa.txt", "Ben Brode");
		addRow("cc.txt", "Dustin Browder");
		addRow("aa.txt", "Ben Brode");
		addRow("cc.txt", "Dustin Browder");
		addRow("aa.txt", "Ben Brode");
		addRow("cc.txt", "Dustin Browder");
		addRow("aa.txt", "Ben Brode");
		addRow("cc.txt", "Dustin Browder");
		addRow("aa.txt", "Ben Brode");
		addRow("cc.txt", "Dustin Browder");
		addRow("aa.txt", "Ben Brode");
		addRow("cc.txt", "Dustin Browder");
		addRow("aa.txt", "Ben Brode");
		addRow("cc.txt", "Dustin Browder");
		addRow("aa.txt", "Ben Brode");
		addRow("cc.txt", "Dustin Browder");
		
		frame.getContentPane().add(jTablePanel);
		
		
	}
	
	private void addRow(String before, String after)
	{
		//같은 내용이 있을경우
	/*	for (int a = 0; a < modelJTable.getRowCount(); a++)
		{
			//Table명, 메뉴 이름, 계산 시간이 모두 일치할 경우 추가
			if ( ( ((String) modelJTable.getValueAt(a, 0)).compareTo(String.format("Table%d", num+1)) ==0 )&&( modelJTable.getValueAt(a, 1) == name )&&( modelJTable.getValueAt(a, 6) == time ))
			{
				amount = (int) modelJTable.getValueAt(a, 2);
				total = (int) modelJTable.getValueAt(a, 4); 

				amount++;
				total += price;
				disprice = (int) (total - (0.2)*total*nowTable.getCondition());
				
				modelJTable.setValueAt(amount, a, 2);
				modelJTable.setValueAt(total, a, 4);
				modelJTable.setValueAt(disprice, a, 5);
				return;

			}
		}
	 	*/
		
		//처음 기입될 경우
		Object[] row = new Object[2];

		row[0] = before;
		row[1] = after;
		//JTable에 새 줄 추가
		modelJTable.addRow(row);

	}
}
