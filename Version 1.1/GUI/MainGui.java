package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Toolkit;
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

import Models.InsertString;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.BoxLayout;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainGui
{
	private JFrame frame;
	
	//JTable - file name list
	private JTable fileNameList;
	private JScrollPane jscrollPane;
	String columnNames[] = {" ���� �� ����Ʈ ", " ���� �� ����Ʈ "};
	private DefaultTableModel modelJTable = new DefaultTableModel(columnNames, 0)
	{
		private static final long serialVersionUID = 2336052481757166098L;
		public boolean isCellEditable(int row, int column)
		{
			return false;
		}
	};
	
	//Model class
	InsertString is = new InsertString();
	
	//Optional Gui
	private OptionalGUI opgui;
	
	//Handler
	opGuiHandler opguihandler = new opGuiHandler();
	buttonHandler buttonhandler = new buttonHandler();
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
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		
		frame = new JFrame("File Name Modify System");
		frame.setBounds( ((int) screenSize.getWidth()/2)-500, ((int) screenSize.getHeight()/2)-400, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, frame.getWidth()-6, frame.getHeight()/20);
		frame.getContentPane().add(menuPanel);
		menuPanel.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("���� ���", Font.PLAIN, 14));
		
		//�޴� �� ���� ����
		menuBar.setLayout( new FlowLayout(FlowLayout.LEFT, 10, 0));
		menuPanel.add(menuBar);
		
		JMenu mnInsertString = new JMenu("���ڿ� ����");
		mnInsertString.setFont(new Font("���� ���", Font.PLAIN, 14));
		//mnInsertString.setVerticalAlignment(SwingConstants.CENTER);
		menuBar.add(mnInsertString);
		
		JMenuItem mn_InsertAtLocation = new JMenuItem("���ϴ� ��ġ�� ���ڿ� ����");
		mn_InsertAtLocation.addActionListener( opguihandler );
		/*
		mn_InsertAtLocation.addActionListener(new ActionListener() {
			
			// event �߻�
			public void actionPerformed(ActionEvent e)
			{
				
				opgui = new OptionalGUI("mn_InsertAtLocation");
				opgui.setLocation( frame.getX() + (frame.getWidth() - opgui.getWidth())/2,
				frame.getY() + (frame.getHeight() - opgui.getHeight())/2 );
				
			
			}
		});
		*/
		mn_InsertAtLocation.setFont(new Font("���� ���", Font.PLAIN, 14));
		mnInsertString.add(mn_InsertAtLocation);
		
		JMenuItem mn_insertByRule = new JMenuItem("��Ģ�� ���� ���ڿ� ����");
		mn_insertByRule.addActionListener(opguihandler);
		mn_insertByRule.setFont(new Font("���� ���", Font.PLAIN, 14));
		mnInsertString.add(mn_insertByRule);
		
		JMenu mnDeleteString = new JMenu("���ڿ� ����");
		mnDeleteString.setFont(new Font("���� ���", Font.PLAIN, 14));
		menuBar.add(mnDeleteString);
		
		JMenuItem mn_DeleteAtLocation = new JMenuItem("���ϴ� ��ġ�� ���ڿ� ����");
		mn_DeleteAtLocation.addActionListener(opguihandler);
		mn_DeleteAtLocation.setFont(new Font("���� ���", Font.PLAIN, 14));
		mnDeleteString.add(mn_DeleteAtLocation);
		
		JMenuItem mn_DeleteOnly = new JMenuItem("���ϴ� ���ڿ��� ����");
		mn_DeleteOnly.addActionListener(opguihandler);
		mn_DeleteOnly.setFont(new Font("���� ���", Font.PLAIN, 14));
		mnDeleteString.add(mn_DeleteOnly);
		
		JMenuItem mn_DeleteExcept = new JMenuItem("���ϴ� ���ڿ� �� ��� ����");
		mn_DeleteExcept.addActionListener(opguihandler);
		mn_DeleteExcept.setFont(new Font("���� ���", Font.PLAIN, 14));
		mnDeleteString.add(mn_DeleteExcept);
		
		JMenu mnUserForm = new JMenu("����� ����");
		mnUserForm.setFont(new Font("���� ���", Font.PLAIN, 14));
		menuBar.add(mnUserForm);
		
		JMenuItem mn_userForm = new JMenuItem("����� ����");
		mn_userForm.addActionListener(opguihandler);
		mn_userForm.setFont(new Font("���� ���", Font.PLAIN, 14));
		mnUserForm.add(mn_userForm);
		
		JMenu mnHelp = new JMenu("����");
		mnHelp.setFont(new Font("���� ���", Font.PLAIN, 14));
		menuBar.add(mnHelp);
		
		JMenuItem mn_InsertHelp = new JMenuItem("���ڿ� ���� ����");
		mn_InsertHelp.addActionListener( opguihandler );
		
		/*
		mn_InsertHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opgui = new OptionalGUI("mn_InsertAtLocation");
				opgui.setLocation( frame.getX() + (frame.getWidth() - opgui.getWidth())/2,
				frame.getY() + (frame.getHeight() - opgui.getHeight())/2 );
			}
		});
		*/
		
		
		mn_InsertHelp.setFont(new Font("���� ���", Font.PLAIN, 14));
		mnHelp.add(mn_InsertHelp);
		
		JMenuItem mn_DeleteHelp = new JMenuItem("���ڿ� ���� ����");
		mn_DeleteHelp.addActionListener(opguihandler);
		mn_DeleteHelp.setFont(new Font("���� ���", Font.PLAIN, 14));
		mnHelp.add(mn_DeleteHelp);
		
		JMenuItem mn_UseFormHelp = new JMenuItem("����� ���� ����");
		mn_UseFormHelp.addActionListener(opguihandler);
		mn_UseFormHelp.setFont(new Font("���� ���", Font.PLAIN, 14));
		mnHelp.add(mn_UseFormHelp);
		
		JMenuItem mn_About = new JMenuItem("About FNM System");
		mn_About.addActionListener(opguihandler);
		mn_About.setFont(new Font("���� ���", Font.PLAIN, 14));
		mnHelp.add(mn_About);
		
		JPanel fileModifyPanel = new JPanel();
		fileModifyPanel.setBounds(0, menuPanel.getHeight(), frame.getWidth()-6, frame.getHeight()/25);
		frame.getContentPane().add(fileModifyPanel);
		fileModifyPanel.setLayout(new GridLayout(0, 2, 0, 0));
								
								JPanel fileModifyLeftPanel = new JPanel();
								fileModifyPanel.add(fileModifyLeftPanel);
								fileModifyLeftPanel.setLayout(null);
								
								JButton buttonFileUp = new JButton("���� ���� ��");
								buttonFileUp.addActionListener(buttonhandler);
								buttonFileUp.setBounds(12, 5, 97, 23);
								fileModifyLeftPanel.add(buttonFileUp);
								
								JButton buttonFileDown = new JButton("���� ���� ��");
								buttonFileDown.addActionListener (buttonhandler);
								buttonFileDown.setBounds(121, 5, 97, 23);
								fileModifyLeftPanel.add(buttonFileDown);
								
								JButton buttonRefresh = new JButton("���� ��ħ");
								buttonRefresh.addActionListener(buttonhandler);
								buttonRefresh.setBounds(230, 5, 97, 23);
								fileModifyLeftPanel.add(buttonRefresh);
								
								JPanel fileModifyRightPanel = new JPanel();
								fileModifyPanel.add(fileModifyRightPanel);
								fileModifyRightPanel.setLayout(new BorderLayout(0, 0));
								
								JPanel checkBoxPanel = new JPanel();
								fileModifyRightPanel.add(checkBoxPanel, BorderLayout.CENTER);
								checkBoxPanel.setLayout(new BorderLayout(10, 0));
								
								JCheckBox checkbox_grouping = new JCheckBox("�̸� ���� �� ������ �з�");
								checkBoxPanel.add(checkbox_grouping, BorderLayout.CENTER);
								
										checkbox_grouping.setHorizontalAlignment(SwingConstants.RIGHT);
										
										JButton buttonUndo = new JButton("�ǵ�����");
										buttonUndo.addActionListener(buttonhandler);
										checkBoxPanel.add(buttonUndo, BorderLayout.EAST);
										
										JPanel modifyButtonPanel = new JPanel();
										fileModifyRightPanel.add(modifyButtonPanel, BorderLayout.EAST);
										modifyButtonPanel.setLayout(new BorderLayout(0, 0));
										
										JButton buttonFileModify = new JButton("���� ����");
										buttonFileModify.addActionListener(buttonhandler);
										buttonFileModify.setPreferredSize( new Dimension( fileModifyPanel.getWidth()/5, fileModifyPanel.getHeight()) );
										
												modifyButtonPanel.add(buttonFileModify);
												buttonFileModify.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		
		
		
		JPanel jTablePanel = new JPanel();
		jTablePanel.setBounds(0, menuPanel.getHeight() + fileModifyPanel.getHeight(), frame.getWidth()-6, frame.getHeight() - menuPanel.getHeight() - fileModifyPanel.getHeight() -30 );
		jTablePanel.setLayout(new BorderLayout(0, 0));
				
		fileNameList = new JTable(modelJTable);
		fileNameList.getTableHeader().setReorderingAllowed(false);		//Jtable�� column�� ������� �ʵ���..
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
		//���� ������ �������
	/*	for (int a = 0; a < modelJTable.getRowCount(); a++)
		{
			//Table��, �޴� �̸�, ��� �ð��� ��� ��ġ�� ��� �߰�
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
		
		//ó�� ���Ե� ���
		Object[] row = new Object[2];

		row[0] = before;
		row[1] = after;
		//JTable�� �� �� �߰�
		modelJTable.addRow(row);

	}
	
	//optional gui�� ���� handler
	private class opGuiHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println(e.getActionCommand());
			opgui = new OptionalGUI(e.getActionCommand());
			opgui.setLocation( frame.getX() + (frame.getWidth() - opgui.getWidth())/2,
			frame.getY() + (frame.getHeight() - opgui.getHeight())/2 );
		}
		
	}
	
	private class buttonHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println(e.getActionCommand());
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
}//source�� ��
