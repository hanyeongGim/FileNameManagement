
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.MenuBar;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.TransferHandler.TransferSupport;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class MainGUI {
	private JFrame frame;
	private ArrayList<FileData> fileList = new ArrayList<FileData>();
	// JTable - file name list
	private JTable fileNameList;
	private JScrollPane jscrollPane;
	String columnNames[] = { " 변경 전 이름 ", " 변경 후 이름 " };
	private DefaultTableModel modelJTable = new DefaultTableModel(columnNames, 0) {
		private static final long serialVersionUID = 2336052481757166098L;

		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	// Model class
	InsertString is = new InsertString();

	// Optional Gui
	private OptionalGUI opgui;

	// Handler
	opGuiHandler opguihandler = new opGuiHandler();
	buttonHandler buttonhandler = new buttonHandler();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws UnsupportedLookAndFeelException
	 */
	public MainGUI() throws UnsupportedLookAndFeelException {

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
		frame.setBounds(((int) screenSize.getWidth() / 2) - 500, ((int) screenSize.getHeight() / 2) - 400, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 994, 30);
		frame.getContentPane().add(menuPanel);
		menuPanel.setLayout(new BorderLayout(0, 0));

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

		// 메뉴 바 간격 조정
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 4));
		menuPanel.add(menuBar);

		JMenu mnInsertString = new JMenu("문자열 삽입");
		mnInsertString.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		// mnInsertString.setVerticalAlignment(SwingConstants.CENTER);
		menuBar.add(mnInsertString);

		JMenuItem mn_InsertAtLocation = new JMenuItem("원하는 위치에 문자열 삽입");
		mn_InsertAtLocation.addActionListener(opguihandler);
		/*
		 * mn_InsertAtLocation.addActionListener(new ActionListener() {
		 * 
		 * // event 발생 public void actionPerformed(ActionEvent e) {
		 * 
		 * opgui = new OptionalGUI("mn_InsertAtLocation"); opgui.setLocation(
		 * frame.getX() + (frame.getWidth() - opgui.getWidth())/2, frame.getY()
		 * + (frame.getHeight() - opgui.getHeight())/2 );
		 * 
		 * 
		 * } });
		 */
		mn_InsertAtLocation.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		mnInsertString.add(mn_InsertAtLocation);

		JMenuItem mn_insertByRule = new JMenuItem("규칙에 맞춰 문자열 삽입");
		mn_insertByRule.addActionListener(opguihandler);
		mn_insertByRule.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		mnInsertString.add(mn_insertByRule);

		JMenu mnDeleteString = new JMenu("문자열 제거");
		mnDeleteString.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		menuBar.add(mnDeleteString);

		JMenuItem mn_DeleteAtLocation = new JMenuItem("원하는 위치의 문자열 제거");
		mn_DeleteAtLocation.addActionListener(opguihandler);
		mn_DeleteAtLocation.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		mnDeleteString.add(mn_DeleteAtLocation);

		JMenuItem mn_DeleteOnly = new JMenuItem("원하는 문자열만 제거");
		mn_DeleteOnly.addActionListener(opguihandler);
		mn_DeleteOnly.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		mnDeleteString.add(mn_DeleteOnly);

		JMenuItem mn_DeleteExcept = new JMenuItem("원하는 문자열 외 모두 제거");
		mn_DeleteExcept.addActionListener(opguihandler);
		mn_DeleteExcept.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		mnDeleteString.add(mn_DeleteExcept);

		JMenu mnUserForm = new JMenu("사용자 서식");
		mnUserForm.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		menuBar.add(mnUserForm);

		JMenuItem mn_userForm = new JMenuItem("사용자 서식");
		mn_userForm.addActionListener(opguihandler);
		mn_userForm.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		mnUserForm.add(mn_userForm);

		JMenu mnHelp = new JMenu("도움말");
		mnHelp.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		menuBar.add(mnHelp);

		JMenuItem mn_InsertHelp = new JMenuItem("문자열 삽입 도움말");
		mn_InsertHelp.addActionListener(opguihandler);

		/*
		 * mn_InsertHelp.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { opgui = new
		 * OptionalGUI("mn_InsertAtLocation"); opgui.setLocation( frame.getX() +
		 * (frame.getWidth() - opgui.getWidth())/2, frame.getY() +
		 * (frame.getHeight() - opgui.getHeight())/2 ); } });
		 */

		mn_InsertHelp.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		mnHelp.add(mn_InsertHelp);

		JMenuItem mn_DeleteHelp = new JMenuItem("문자열 제거 도움말");
		mn_DeleteHelp.addActionListener(opguihandler);
		mn_DeleteHelp.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		mnHelp.add(mn_DeleteHelp);

		JMenuItem mn_UseFormHelp = new JMenuItem("사용자 서식 도움말");
		mn_UseFormHelp.addActionListener(opguihandler);
		mn_UseFormHelp.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		mnHelp.add(mn_UseFormHelp);

		JMenuItem mn_About = new JMenuItem("About FNM System");
		mn_About.addActionListener(opguihandler);
		mn_About.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		mnHelp.add(mn_About);

		JPanel fileModifyPanel = new JPanel();
		fileModifyPanel.setBounds(0, menuPanel.getHeight(), frame.getWidth() - 6, frame.getHeight() / 25);
		frame.getContentPane().add(fileModifyPanel);
		fileModifyPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel fileModifyLeftPanel = new JPanel();
		fileModifyPanel.add(fileModifyLeftPanel);
		fileModifyLeftPanel.setLayout(null);

		JButton buttonFileUp = new JButton("선택 파일 ↑");
		buttonFileUp.addActionListener(buttonhandler);
		buttonFileUp.setBounds(12, 5, 97, 23);
		fileModifyLeftPanel.add(buttonFileUp);

		JButton buttonFileDown = new JButton("선택 파일 ↓");
		buttonFileDown.addActionListener(buttonhandler);
		buttonFileDown.setBounds(121, 5, 97, 23);
		fileModifyLeftPanel.add(buttonFileDown);

		JButton buttonRefresh = new JButton("새로 고침");
		buttonRefresh.addActionListener(buttonhandler);
		buttonRefresh.setBounds(230, 5, 97, 23);
		fileModifyLeftPanel.add(buttonRefresh);

		JPanel fileModifyRightPanel = new JPanel();
		fileModifyPanel.add(fileModifyRightPanel);
		fileModifyRightPanel.setLayout(new BorderLayout(0, 0));

		JPanel checkBoxPanel = new JPanel();
		fileModifyRightPanel.add(checkBoxPanel, BorderLayout.CENTER);
		checkBoxPanel.setLayout(new BorderLayout(10, 0));

		JCheckBox checkbox_grouping = new JCheckBox("이름 변경 시 폴더로 분류");
		checkBoxPanel.add(checkbox_grouping, BorderLayout.CENTER);

		checkbox_grouping.setHorizontalAlignment(SwingConstants.RIGHT);

		JButton buttonUndo = new JButton("되돌리기");
		buttonUndo.addActionListener(buttonhandler);
		checkBoxPanel.add(buttonUndo, BorderLayout.EAST);

		JPanel modifyButtonPanel = new JPanel();
		fileModifyRightPanel.add(modifyButtonPanel, BorderLayout.EAST);
		modifyButtonPanel.setLayout(new BorderLayout(0, 0));

		JButton buttonFileModify = new JButton("파일 변경");
		buttonFileModify.addActionListener(buttonhandler);
		buttonFileModify.setPreferredSize(new Dimension(fileModifyPanel.getWidth() / 5, fileModifyPanel.getHeight()));

		modifyButtonPanel.add(buttonFileModify);
		buttonFileModify.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel jTablePanel = new JPanel();
		jTablePanel.setBounds(0, menuPanel.getHeight() + fileModifyPanel.getHeight(), frame.getWidth() - 6,
				frame.getHeight() - menuPanel.getHeight() - fileModifyPanel.getHeight() - 30);
		jTablePanel.setLayout(new BorderLayout(0, 0));
		
		fileNameList = new JTable(modelJTable);
		fileNameList.getTableHeader().setReorderingAllowed(false); // Jtable의
																	// column이
																	// 변경되지
																	// 않도록..

		fileNameList.setSize(frame.getWidth(), frame.getHeight() - menuPanel.getHeight() - fileModifyPanel.getHeight());

		fileNameList.setRowHeight(25);
		fileNameList.getColumnModel().getColumn(0).setPreferredWidth(245);
		fileNameList.getColumnModel().getColumn(1).setPreferredWidth(246);

		fileNameList.setDragEnabled(true);
		fileNameList.setTransferHandler(new FileTransferHandler());

		jTablePanel.setTransferHandler(new FileTransferHandler());

		jscrollPane = new JScrollPane(fileNameList);
		jTablePanel.add(jscrollPane, BorderLayout.CENTER);

		frame.getContentPane().add(jTablePanel);
	}

	private void refreshTable() {
		DefaultTableModel model = (DefaultTableModel) fileNameList.getModel();

		for (int i = 0; i < fileNameList.getRowCount(); i++) {
			model.removeRow(i);
		}

		model.setRowCount(0);

		for (int i = 0; i < fileList.size(); i++) {
			String arr[] = new String[2];
			arr[0] = fileList.get(i).getFile().getName();
			arr[1] = fileList.get(i).getChangedName();
			model.addRow(arr);
		}

		System.out.println(model.getRowCount());
	}

	// optional gui를 띄우는 handler
	private class opGuiHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());
			opgui = new OptionalGUI(e.getActionCommand(), fileList);
			opgui.setLocation(frame.getX() + (frame.getWidth() - opgui.getWidth()) / 2,
					frame.getY() + (frame.getHeight() - opgui.getHeight()) / 2);
		}

	}

	private class buttonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());

		}

	}

	class FileTransferHandler extends TransferHandler {

		public FileTransferHandler() {
		}

		public int getSourceActions(JComponent c) {
			return COPY_OR_MOVE;
		}

		public boolean canImport(TransferSupport ts) {
			return ts.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
		}

		public boolean importData(TransferSupport ts) {
			try {
				@SuppressWarnings("rawtypes")
				List data = (List) ts.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
				if (data.size() < 1) {
					return false;
				}

				for (Object item : data) {
					File file = (File) item;

					Boolean flag = true;

					for (int i = 0; i < fileList.size(); i++) {
						if (file.equals(fileList.get(i).getFile()))
							flag = false;
					}

					if (flag)
						fileList.add(new FileData(file.getPath()));
				}

				refreshTable();

				return true;

			} catch (UnsupportedFlavorException e) {
				return false;
			} catch (IOException e) {
				return false;
			}
		}
	}
}// source의 끝
