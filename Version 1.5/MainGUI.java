
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
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DragSource;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.TransferHandler.TransferSupport;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.activation.ActivationDataFlavor;
import javax.activation.DataHandler;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;

public class MainGUI implements Observer {
	private JFrame frame;
	private ArrayList<FileData> fileList = new ArrayList<FileData>();
	// JTable - file name list
	private JTable fileNameList;
	private JScrollPane jscrollPane;
	String columnNames[] = { " 변경 전 이름 ", " 변경 후 이름 " };

	public interface Reorderable {
		public void reorder(int from, int to);
	}

	@SuppressWarnings("serial")
	private class MyTableModel extends DefaultTableModel implements Reorderable {
		public MyTableModel(Object[] arg0, int arg1) {
			super(arg0, arg1);
		}

		public void reorder(int from, int to) {
			Object o = getDataVector().remove(from);
			getDataVector().add(to, o);
			fireTableDataChanged();
		}
	}

	private MyTableModel modelJTable = new MyTableModel(columnNames, 0) {
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

	public MainGUI() throws UnsupportedLookAndFeelException {

		UIManager.setLookAndFeel(new NimbusLookAndFeel());

		initialize();
	}

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
		menuBar.setFont(new Font("굴림", Font.PLAIN, 14));

		// 메뉴 바 간격 조정
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 4));
		menuPanel.add(menuBar);

		JMenu mnInsertString = new JMenu("문자열 삽입");
		mnInsertString.setFont(new Font("굴림", Font.PLAIN, 14));
		// mnInsertString.setVerticalAlignment(SwingConstants.CENTER);
		menuBar.add(mnInsertString);

		JMenuItem mn_InsertAtLocation = new JMenuItem("원하는 위치에 문자열 삽입");
		mn_InsertAtLocation.addActionListener(opguihandler);
		mn_InsertAtLocation.setFont(new Font("굴림", Font.PLAIN, 14));
		mnInsertString.add(mn_InsertAtLocation);

		JMenuItem mn_insertByRule = new JMenuItem("규칙에 맞춰 문자열 삽입");
		mn_insertByRule.addActionListener(opguihandler);
		mn_insertByRule.setFont(new Font("굴림", Font.PLAIN, 14));
		mnInsertString.add(mn_insertByRule);

		JMenu mnDeleteString = new JMenu("문자열 제거");
		mnDeleteString.setFont(new Font("굴림", Font.PLAIN, 14));
		menuBar.add(mnDeleteString);

		JMenuItem mn_DeleteAtLocation = new JMenuItem("원하는 위치의 문자열 제거");
		mn_DeleteAtLocation.addActionListener(opguihandler);
		mn_DeleteAtLocation.setFont(new Font("굴림", Font.PLAIN, 14));
		mnDeleteString.add(mn_DeleteAtLocation);

		JMenuItem mn_DeleteOnly = new JMenuItem("원하는 문자열만 제거");
		mn_DeleteOnly.addActionListener(opguihandler);
		mn_DeleteOnly.setFont(new Font("굴림", Font.PLAIN, 14));
		mnDeleteString.add(mn_DeleteOnly);

		JMenuItem mn_DeleteExcept = new JMenuItem("원하는 문자열 외 모두 제거");
		mn_DeleteExcept.addActionListener(opguihandler);
		mn_DeleteExcept.setFont(new Font("굴림", Font.PLAIN, 14));
		mnDeleteString.add(mn_DeleteExcept);

		JMenu mnUserForm = new JMenu("사용자 서식");
		mnUserForm.setFont(new Font("굴림", Font.PLAIN, 14));
		menuBar.add(mnUserForm);

		JMenuItem mn_userForm = new JMenuItem("사용자 서식");
		mn_userForm.addActionListener(opguihandler);
		mn_userForm.setFont(new Font("굴림", Font.PLAIN, 14));
		mnUserForm.add(mn_userForm);

		JMenu mnHelp = new JMenu("도움말");
		mnHelp.setFont(new Font("굴림", Font.PLAIN, 14));
		menuBar.add(mnHelp);

		JMenuItem mn_InsertHelp = new JMenuItem("문자열 삽입 도움말");
		mn_InsertHelp.addActionListener(opguihandler);

		mn_InsertHelp.setFont(new Font("굴림", Font.PLAIN, 14));
		mnHelp.add(mn_InsertHelp);

		JMenuItem mn_DeleteHelp = new JMenuItem("문자열 제거 도움말");
		mn_DeleteHelp.addActionListener(opguihandler);
		mn_DeleteHelp.setFont(new Font("굴림", Font.PLAIN, 14));
		mnHelp.add(mn_DeleteHelp);

		JMenuItem mn_UseFormHelp = new JMenuItem("사용자 서식 도움말");
		mn_UseFormHelp.addActionListener(opguihandler);
		mn_UseFormHelp.setFont(new Font("굴림", Font.PLAIN, 14));
		mnHelp.add(mn_UseFormHelp);

		JMenuItem mn_About = new JMenuItem("About FNM System");
		mn_About.addActionListener(opguihandler);
		mn_About.setFont(new Font("굴림", Font.PLAIN, 14));
		mnHelp.add(mn_About);

		JPanel fileModifyPanel = new JPanel();
		fileModifyPanel.setBounds(0, menuPanel.getHeight(), frame.getWidth() - 6, frame.getHeight() / 25);
		frame.getContentPane().add(fileModifyPanel);
		fileModifyPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel fileModifyLeftPanel = new JPanel();
		fileModifyPanel.add(fileModifyLeftPanel);
		fileModifyLeftPanel.setLayout(null);

		JButton buttonRefresh = new JButton("새로 고침");
		buttonRefresh.addActionListener(buttonhandler);
		buttonRefresh.setBounds(12, 0, 100, 32);
		fileModifyLeftPanel.add(buttonRefresh);

		JButton buttonUndo = new JButton("되돌리기");
		buttonUndo.setBounds(124, 0, 100, 32);
		fileModifyLeftPanel.add(buttonUndo);
		buttonUndo.addActionListener(buttonhandler);

		JPanel fileModifyRightPanel = new JPanel();
		fileModifyPanel.add(fileModifyRightPanel);
		fileModifyRightPanel.setLayout(new BorderLayout(0, 0));

		JPanel checkBoxPanel = new JPanel();
		fileModifyRightPanel.add(checkBoxPanel, BorderLayout.CENTER);
		checkBoxPanel.setLayout(null);

		JPanel modifyButtonPanel = new JPanel();
		fileModifyRightPanel.add(modifyButtonPanel, BorderLayout.EAST);
		modifyButtonPanel.setLayout(new BorderLayout(0, 0));

		JButton buttonFileModify = new JButton("파일 변경");
		buttonFileModify.addActionListener(buttonhandler);
		buttonFileModify.setPreferredSize(new Dimension(200, 32));

		modifyButtonPanel.add(buttonFileModify, BorderLayout.EAST);
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
		fileNameList.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				JComponent c = (JComponent) e.getComponent();
				Optional.ofNullable(c.getTransferHandler())
						.ifPresent(th -> th.exportAsDrag(c, e, TransferHandler.COPY));
			}
		});
		fileNameList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		fileNameList.setColumnSelectionAllowed(false);
		fileNameList.setRowSelectionAllowed(true);

		fileNameList.setAutoCreateRowSorter(true);
		fileNameList.getRowSorter().addRowSorterListener(new RowSorterListener() {
			@Override
			public void sorterChanged(RowSorterEvent e) {
				FileData temp = null;

				for (int i = 0; i < fileNameList.getRowCount(); i++) {
					int j;

					for (j = i; j < fileList.size(); j++) {
						if (fileList.get(j).getFile().getName().equals(fileNameList.getValueAt(i, 0))) {
							break;
						}
					}

					if (fileList.size() > 0) {
						temp = fileList.get(i);
						fileList.set(i, fileList.get(j));
						fileList.set(j, temp);
					}
				}
			}
		});

		frame.setTransferHandler(new FileTransferHandler());
		fileNameList.addMouseListener(new TableMouseListener());

		jscrollPane = new JScrollPane(fileNameList);
		jscrollPane.setToolTipText("드래그 & 드롭으로 파일을 추가하세요");
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
			String arr[] = new String[3];
			arr[0] = fileList.get(i).getFile().getName();
			arr[1] = fileList.get(i).getChangedName();
			model.addRow(arr);
		}

		System.out.println(model.getRowCount());
	}

	private void addObserver() {
		opgui.ufHandler.addObserver(this);
	}

	// optional gui를 띄우는 handler
	private class opGuiHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());

			for (int i = 0; i < fileList.size(); i++) {
				fileList.get(i).deselect();
			}
			// 선택된 줄이 없으면 모두 select
			if (fileNameList.getSelectedRow() == -1) {
				for (int i = 0; i < fileList.size(); i++) {
					fileList.get(i).select();
				}
			} else {
				for (int i : fileNameList.getSelectedRows()) {
					int j;

					for (j = 0; i < fileList.size(); j++) {
						if (fileList.get(j).getFile().getName().equals(fileNameList.getValueAt(i, 0))) {
							break;
						}
					}

					fileList.get(j).select();
				}
			}
			opgui = new OptionalGUI(e.getActionCommand(), fileList);
			opgui.setLocation(frame.getX() + (frame.getWidth() - opgui.getWidth()) / 2,
					frame.getY() + (frame.getHeight() - opgui.getHeight()) / 2);

			addObserver();
		}
	}

	private class buttonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());

			switch (e.getActionCommand()) {
			case "새로 고침":
				refreshTable();
				break;
			case "되돌리기":
				for (int i = 0; i < fileList.size(); i++) {
					fileList.get(i).setChangedName(fileList.get(i).getUndoName());
				}
				refreshTable();
				break;
			case "파일 변경":
				for (int i = 0; i < fileList.size(); i++) {
					fileList.get(i).modify();
				}

				while (fileList.size() > 0) {
					fileList.remove(0);
				}

				System.out.println("size: " + fileList.size());

				refreshTable();

				break;
			default:
				break;
			}
		}
	}

	public class TableMouseListener implements MouseListener {
		public TableMouseListener() {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getSource().equals(fileNameList)) {
				fileNameList.setDropMode(DropMode.INSERT_ROWS);
				setOrderHandler(fileNameList);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			setFileHandler(fileNameList);
		}

	}

	public void setOrderHandler(JTable table) {
		table.setTransferHandler(new TableRowTransferHandler());
	}

	public void setFileHandler(JTable table) {
		table.setTransferHandler(new FileTransferHandler());
	}

	@SuppressWarnings("serial")
	public class TableRowTransferHandler extends TransferHandler {
		private final DataFlavor localObjectFlavor = new ActivationDataFlavor(Integer.class,
				"application/x-java-Integer;class=java.lang.Integer", "Integer Row Index");
		private JTable table = null;

		public TableRowTransferHandler() {
			this.table = fileNameList;
		}

		@Override
		protected Transferable createTransferable(JComponent c) {
			assert (c == table);
			return new DataHandler(new Integer(table.getSelectedRow()), localObjectFlavor.getMimeType());
		}

		@Override
		public boolean canImport(TransferHandler.TransferSupport info) {
			boolean b = info.getComponent() == table && info.isDrop() && info.isDataFlavorSupported(localObjectFlavor);
			table.setCursor(b ? DragSource.DefaultMoveDrop : DragSource.DefaultMoveNoDrop);
			return b;
		}

		@Override
		public int getSourceActions(JComponent c) {
			table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			return TransferHandler.COPY_OR_MOVE;
		}

		@Override
		public boolean importData(TransferHandler.TransferSupport info) {
			JTable target = (JTable) info.getComponent();
			JTable.DropLocation dl = (JTable.DropLocation) info.getDropLocation();
			int index = dl.getRow();
			int max = table.getModel().getRowCount();
			if (index < 0 || index > max)
				index = max;
			target.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			try {
				Integer rowFrom = (Integer) info.getTransferable().getTransferData(localObjectFlavor);
				if (rowFrom < index)
					index--;
				if (rowFrom != -1 && rowFrom != index) {
					((Reorderable) table.getModel()).reorder(rowFrom, index);
					target.getSelectionModel().addSelectionInterval(index, index);

					FileData temp = fileList.get(rowFrom);
					fileList.remove(fileList.indexOf(fileList.get(rowFrom)));

					if (rowFrom < index)
						fileList.add(index - 1, temp);
					else
						fileList.add(index, temp);
					for (int i = 0; i < fileList.size(); i++) {
						System.out.println(fileList.get(i).getChangedName());
					}

					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		protected void exportDone(JComponent c, Transferable t, int act) {
			if ((act == TransferHandler.MOVE) || (act == TransferHandler.NONE)) {
				table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			table.setDropMode(DropMode.ON);
			setFileHandler(table);
		}
	}

	@SuppressWarnings("serial")
	class FileTransferHandler extends TransferHandler {
		private JTable table = null;

		public FileTransferHandler() {
			this.table = fileNameList;
		}

		public int getSourceActions(JComponent c) {
			table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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

				table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

				return true;

			} catch (UnsupportedFlavorException e) {
				return false;
			} catch (IOException e) {
				return false;
			}
		}
	}

	public void update(Observable o, Object arg) {
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
}// source의 끝
