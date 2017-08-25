
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class OptionalGUI extends JFrame {
	private JTextField textField1;
	private JTextField textField2;
	JPanel optionPanel = new JPanel();
	JPanel userFormPanel = new JPanel();
	private String frameName;
	private JScrollPane ufScrollPane;
	boolean numcheck = true;
	boolean ascendcheck = true;
	String textFstring = "";
	private JTextField textField;
	String[] colNames = { "입력한 명령어" };
	DefaultTableModel ctmodel = new DefaultTableModel(colNames, 0);
	String[] colNames2 = { "명령어 양식", "설명" };
	DefaultTableModel ctmodel2 = new DefaultTableModel(colNames2, 0);
	private JTable cmdTable;
	private ArrayList<FileData> fileList;
	private boolean fneCheck = false;

	// Optional GUI 기능별 handler
	insertAtHandler insAtHandler = new insertAtHandler();
	insertRuleHandler insRuleHandler = new insertRuleHandler();
	deleteAtHandler delAtHandler = new deleteAtHandler();
	deleteTargetHandler delTargetHandler = new deleteTargetHandler();
	deleteExceptHandler delExceptHandler = new deleteExceptHandler();
	ufHandler ufHandler = new ufHandler();

	public OptionalGUI() {
		super("temp");

		initialize();
	}

	public OptionalGUI(String classtype, ArrayList<FileData> inputList) {
		super(classtype);
		frameName = classtype;
		this.fileList = inputList;
		initialize();

		if (classtype == "원하는 위치에 문자열 삽입") {
			insertAtWindow();
		} else if (classtype == "규칙에 맞춰 문자열 삽입") {
			insertRuleWindow();
		} else if (classtype == "원하는 위치의 문자열 제거") {
			deleteAt();
		} else if (classtype == "원하는 문자열만 제거") {
			deleteTarget();
		} else if (classtype == "원하는 문자열 외 모두 제거") {
			deleteExcept();
		} else if (classtype == "사용자 서식") {
			userFormWindow();
		} else if (classtype == "명령어 목록") {
			userFormCommand();
		} else if (classtype == "About FNM System") {
			helpAboutWindow();
		}
	}

	// 공통된 곳
	private void initialize() {
		setVisible(true);
		setResizable(false);
		getContentPane().add(optionPanel, BorderLayout.CENTER);
		optionPanel.setLayout(null);
	}

	private void insertAtWindow() {
		setSize(600, 200);
		// 삽입할 문자열
		Label label_insertAtString = new Label("삽입할 문자열");
		label_insertAtString.setAlignment(Label.CENTER);
		label_insertAtString.setFont(new Font("굴림", Font.PLAIN, 14));
		label_insertAtString.setBounds(10, 30, 100, 40);
		optionPanel.add(label_insertAtString);

		textField1 = new JTextField();
		textField1.setBounds(120, 30, 350, 40);
		textField1.setToolTipText("삽입하고자 하는 문자열을 입력하세요");
		optionPanel.add(textField1);
		textField1.setColumns(10);

		// 위치
		Label label_insertAtIndex = new Label("위치");
		label_insertAtIndex.setAlignment(Label.CENTER);
		label_insertAtIndex.setFont(new Font("굴림", Font.PLAIN, 14));
		label_insertAtIndex.setBounds(10, 100, 100, 40);
		optionPanel.add(label_insertAtIndex);

		textField2 = new JTextField();
		textField2.setBounds(120, 100, 350, 40);
		textField2.setToolTipText("<html> 숫자를 써주세요 <html><br /> 음수 입력 시 확장자 바로 앞으로 간주합니다");
		optionPanel.add(textField2);
		textField2.setColumns(10);

		JButton buttonInsertAtConfirm = new JButton("확인");
		buttonInsertAtConfirm.addActionListener(insAtHandler);
		buttonInsertAtConfirm.setBounds(480, 65, 100, 40);
		optionPanel.add(buttonInsertAtConfirm);
	}

	private void insertRuleWindow() {
		setSize(600, 200);
		String str_numcheck[] = { "숫자", "영문" };
		JComboBox combo_numcheck = new JComboBox(str_numcheck);
		combo_numcheck.setMaximumRowCount(2);
		combo_numcheck.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				// TODO Auto-generated method stub
				if (event.getStateChange() == ItemEvent.SELECTED) {
					if (combo_numcheck.getSelectedIndex() == 0) {
						numcheck = true;

					} else {
						numcheck = false;
					}
					System.out.println("numcheck = " + numcheck);
				}
			}
		}

		);
		combo_numcheck.setBounds(70, 25, 200, 40);

		optionPanel.add(combo_numcheck);

		String str_ascendcheck[] = { "오름차순", "내림차순" };
		JComboBox combo_ascendcheck = new JComboBox(str_ascendcheck);
		combo_ascendcheck.setMaximumRowCount(2);
		combo_ascendcheck.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				// TODO Auto-generated method stub
				if (event.getStateChange() == ItemEvent.SELECTED) {
					if (combo_ascendcheck.getSelectedIndex() == 0) {
						ascendcheck = true;

					} else {
						ascendcheck = false;
					}
					System.out.println("ascendcheck = " + ascendcheck);
				}
			}
		}

		);
		combo_ascendcheck.setBounds(330, 25, 200, 40);
		optionPanel.add(combo_ascendcheck);

		textField1 = new JTextField();
		textField1.setBounds(70, 105, 200, 40);
		textField1.setToolTipText("<html>규칙이 들어갈 위치를 써주세요 <html><br /> 음수 입력 시 확장자 바로 앞으로 간주합니다");
		optionPanel.add(textField1);
		textField1.setColumns(10);

		JButton buttonInsertRuleConfirm = new JButton("확인");
		buttonInsertRuleConfirm.addActionListener(insRuleHandler);
		buttonInsertRuleConfirm.setBounds(330, 105, 200, 40);
		optionPanel.add(buttonInsertRuleConfirm);
	}

	private void deleteAt() {
		// TODO Auto-generated method stub
		setSize(600, 200);

		// 범위
		Label label_deleteAtRange = new Label("범위");
		label_deleteAtRange.setAlignment(Label.CENTER);
		label_deleteAtRange.setFont(new Font("굴림", Font.PLAIN, 14));
		label_deleteAtRange.setBounds(20, 50, 80, 60);
		optionPanel.add(label_deleteAtRange);

		textField1 = new JTextField();
		textField1.setBounds(120, 50, 150, 60);
		textField1.setToolTipText("<html> 숫자를 써주세요 (begin) <html><br /> begin은 end보다 작은 양수입니다");
		optionPanel.add(textField1);
		textField1.setColumns(10);

		// 위치
		Label label_deleteAtstream = new Label("~");
		label_deleteAtstream.setAlignment(Label.CENTER);
		label_deleteAtstream.setFont(new Font("굴림", Font.PLAIN, 14));
		label_deleteAtstream.setBounds(290, 50, 20, 60);
		optionPanel.add(label_deleteAtstream);

		textField2 = new JTextField();
		textField2.setBounds(330, 50, 150, 60);
		textField2.setToolTipText("<html> 숫자를 써주세요 (end) <html><br /> end는 begin보다 큰 양수입니다");
		optionPanel.add(textField2);
		textField2.setColumns(10);

		JButton buttonDeleteAtConfirm = new JButton("확인");
		buttonDeleteAtConfirm.addActionListener(delAtHandler);
		buttonDeleteAtConfirm.setBounds(500, 50, 80, 60);
		optionPanel.add(buttonDeleteAtConfirm);
	}

	private void deleteTarget() {
		// TODO Auto-generated method stub
		setSize(600, 200);

		// 제거할 문자열
		Label label_DeleteTargetString = new Label("제거할 문자열");
		label_DeleteTargetString.setAlignment(Label.CENTER);
		label_DeleteTargetString.setFont(new Font("굴림", Font.PLAIN, 14));
		label_DeleteTargetString.setBounds(30, 50, 100, 60);
		optionPanel.add(label_DeleteTargetString);

		textField1 = new JTextField();
		textField1.setBounds(160, 50, 300, 60);
		textField1.setToolTipText("<html>제거할 문자열을 입력해 주세요 <html><br /> 중복 시 가장 뒷쪽의 문자열 1개만 지웁니다");
		optionPanel.add(textField1);
		textField1.setColumns(10);

		// 확장자 체크박스
		JCheckBox filenameExtensionCheckBox = new JCheckBox("확장자를 포함하여 제거");
		filenameExtensionCheckBox.setSize(300, 50);
		filenameExtensionCheckBox.setToolTipText("체크 시 확장자를 포함한 전체 파일 이름을 대상으로 합니다");
		filenameExtensionCheckBox.setBounds((getWidth() - filenameExtensionCheckBox.getWidth()) / 2, 120, 300, 50);
		filenameExtensionCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {

				// 확장자 포함 제거 옵션이 켜져있다면
				if (filenameExtensionCheckBox.isSelected()) {
					fneCheck = true;
				}
				// 확장자 포함 제거 옵션이 꺼져있다면
				else {
					fneCheck = false;
				}
				System.out.println("FileNameExtension Include : " + fneCheck);
			}
		});
		optionPanel.add(filenameExtensionCheckBox);

		JButton buttonDeleteTargetButton = new JButton("확인");
		buttonDeleteTargetButton.addActionListener(delTargetHandler);
		buttonDeleteTargetButton.setBounds(490, 50, 80, 60);
		optionPanel.add(buttonDeleteTargetButton);
	}

	private void deleteExcept() {
		// TODO Auto-generated method stub
		setSize(600, 200);

		// 제외할 문자열
		Label label_DeleteEeceptString = new Label("제외할 문자열");
		label_DeleteEeceptString.setAlignment(Label.CENTER);
		label_DeleteEeceptString.setFont(new Font("굴림", Font.PLAIN, 14));
		label_DeleteEeceptString.setBounds(30, 50, 100, 60);
		optionPanel.add(label_DeleteEeceptString);

		textField1 = new JTextField();
		textField1.setBounds(160, 50, 300, 60);
		textField1.setToolTipText("<html>남길 문자열을 입력하세요");
		optionPanel.add(textField1);
		textField1.setColumns(10);

		JButton buttonDeleteExceptButton = new JButton("확인");
		buttonDeleteExceptButton.addActionListener(delExceptHandler);
		buttonDeleteExceptButton.setBounds(490, 50, 80, 60);
		optionPanel.add(buttonDeleteExceptButton);
	}

	private void userFormWindow() {
		getContentPane().add(userFormPanel, BorderLayout.CENTER);
		userFormPanel.setLayout(null);

		setName("사용자 서식");

		setSize(430, 420);
		userFormPanel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(50, 310, 220, 30);
		userFormPanel.add(textField);
		textField.setColumns(10);
		textField.addKeyListener(new ufKeyListener());
		textField.requestFocus();

		JLabel label = new JLabel("입력");
		label.setBounds(13, 317, 30, 15);
		userFormPanel.add(label);

		JButton btnNewButton = new JButton("추가");
		btnNewButton.setBounds(282, 309, 60, 30);
		btnNewButton.addActionListener(ufHandler);
		userFormPanel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("삭제");
		btnNewButton_1.setBounds(354, 309, 60, 30);
		btnNewButton_1.addActionListener(ufHandler);
		userFormPanel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("불러오기");
		btnNewButton_2.setBounds(9, 350, 80, 30);
		btnNewButton_2.addActionListener(ufHandler);
		userFormPanel.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("저장");
		btnNewButton_3.setBounds(101, 350, 80, 30);
		btnNewButton_3.addActionListener(ufHandler);
		userFormPanel.add(btnNewButton_3);

		JButton btnNewButton_5 = new JButton("확인");
		btnNewButton_5.setBounds(312, 349, 100, 30);
		btnNewButton_5.addActionListener(ufHandler);
		userFormPanel.add(btnNewButton_5);

		cmdTable = new JTable(ctmodel);
		cmdTable.setBounds(0, 0, 424, 300);
		cmdTable.setDragEnabled(false);
		cmdTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cmdTable.getTableHeader().setReorderingAllowed(false);

		cmdTable.setRowHeight(30);
		cmdTable.getColumnModel().getColumn(0).setPreferredWidth(245);

		ufScrollPane = new JScrollPane(cmdTable);
		ufScrollPane.setLocation(0, 0);
		ufScrollPane.setSize(424, 300);
		userFormPanel.add(ufScrollPane);
	}

	private void userFormCommand() {
		getContentPane().add(userFormPanel, BorderLayout.CENTER);
		userFormPanel.setLayout(null);

		setName("명령어 목록");

		setSize(630, 300);
		userFormPanel.setLayout(null);

		cmdTable = new JTable(ctmodel2);
		cmdTable.setBounds(0, 0, 424, 300);
		cmdTable.setDragEnabled(false);
		cmdTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cmdTable.getTableHeader().setReorderingAllowed(false);

		String[][] arr = new String[10][2];

		arr[0][0] = "INS#index#String";
		arr[0][1] = "index 위치에 String을 끼워넣는다.";

		arr[1][0] = "INSRULE#index#N or A#A or D";
		arr[1][1] = "index 위치에 해당하는 규칙대로 삽입한다. (숫자 or 영문, 오름차순 or 내림차순)";

		arr[2][0] = "DEL#start#end";
		arr[2][1] = "start~end index의 문자들을 지운다. end를 생략하면 한 문자만 지운다.";

		arr[3][0] = "DELALL#Option";
		arr[3][1] = "옵션이 없다면 확장자를 제외한 모든 문자를, 있다면 확장자를 포함해서 지운다.";

		arr[4][0] = "DELSTR#TargetString#Option";
		arr[4][1] = "옵션이 있다면 확장자를 포함, 없다면 포함하지 않고 Target 문자열을 지운다.";

		arr[5][0] = "DELEXPT#TargetString";
		arr[5][1] = "TargetString만 제외하고 지운다.";

		arr[6][0] = "DATE#1 or 0";
		arr[6][1] = "날짜를 yyyymmdd 형태로 삽입한다. (맨 앞 or 확장자 앞)";

		ctmodel2.addRow(arr[0]);
		ctmodel2.addRow(arr[1]);
		ctmodel2.addRow(arr[2]);
		ctmodel2.addRow(arr[3]);
		ctmodel2.addRow(arr[4]);
		ctmodel2.addRow(arr[5]);
		ctmodel2.addRow(arr[6]);

		cmdTable.setRowHeight(30);
		cmdTable.getColumnModel().getColumn(0).setPreferredWidth(180);
		cmdTable.getColumnModel().getColumn(1).setPreferredWidth(420);

		ufScrollPane = new JScrollPane(cmdTable);
		ufScrollPane.setLocation(0, 0);
		ufScrollPane.setSize(624, 241);
		userFormPanel.add(ufScrollPane);

		JLabel label = new JLabel("※ -1은 확장자 바로 앞의 index입니다.");
		label.setBounds(10, 240, 313, 31);
		userFormPanel.add(label);
	}

	// isertAtHandler using Observer Pattern
	public class insertAtHandler extends Observable implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("insertAt - " + e.getActionCommand());

			// 확인 버튼 클릭 시
			if (e.getActionCommand() == "확인") {
				InsertString is = new InsertString(fileList);
				is.insertAt(Integer.parseInt(textField2.getText()), textField1.getText());

				setChanged();
				notifyObservers();
				dispose();
			}
		}
	}

	// isertRuleHandler using Observer Pattern
	public class insertRuleHandler extends Observable implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("insertRule - " + e.getActionCommand());

			// 확인 버튼 클릭 시
			if (e.getActionCommand() == "확인") {
				InsertString is = new InsertString(fileList);
				is.insertAsRule(Integer.parseInt(textField1.getText()), numcheck, ascendcheck);

				setChanged();
				notifyObservers();
				dispose();
			}
		}
	}

	// isertRuleHandler using Observer Pattern
	public class deleteAtHandler extends Observable implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("delAt - " + e.getActionCommand());

			// 확인 버튼 클릭 시
			if (e.getActionCommand() == "확인") {
				DeleteString ds = new DeleteString(fileList);
				ds.deleteAt(Integer.parseInt(textField1.getText()), Integer.parseInt(textField2.getText()));

				setChanged();
				notifyObservers();
				dispose();
			}
		}
	}

	public class deleteTargetHandler extends Observable implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("delTarget - " + e.getActionCommand());

			// 확인 버튼 클릭 시
			if (e.getActionCommand() == "확인") {
				DeleteString ds = new DeleteString(fileList);
				ds.deleteTarget(textField1.getText(), fneCheck);

				setChanged();
				notifyObservers();
				dispose();
			}
		}
	}

	public class deleteExceptHandler extends Observable implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("delExcept - " + e.getActionCommand());

			// 확인 버튼 클릭 시
			if (e.getActionCommand() == "확인") {
				DeleteString ds = new DeleteString(fileList);
				ds.deleteExcept(textField1.getText());

				setChanged();
				notifyObservers();
				dispose();
			}
		}
	}

	public void saveFreeset() {
		File savefile;
		String savepathname;
		JFileChooser chooser = new JFileChooser();

		chooser.setCurrentDirectory(new File("C:\\"));
		chooser.setFileSelectionMode(chooser.DIRECTORIES_ONLY);

		while (true) {
			int re = chooser.showSaveDialog(null);

			if (re == JFileChooser.APPROVE_OPTION) {
				savefile = chooser.getSelectedFile();

				Calendar c = Calendar.getInstance();

				savepathname = savefile.getAbsolutePath() + "\\" + "FNM_";
				savepathname += Integer.toString(c.get(Calendar.YEAR));
				if (c.get(Calendar.MONTH) < 9)
					savepathname += "0";
				savepathname += Integer.toString(c.get(Calendar.MONTH) + 1);
				if (c.get(Calendar.DATE) < 10)
					savepathname += "0";
				savepathname += Integer.toString(c.get(Calendar.DATE));
				if (c.get(Calendar.HOUR_OF_DAY) < 10)
					savepathname += "0";
				savepathname += c.get(Calendar.HOUR_OF_DAY);
				if (c.get(Calendar.MINUTE) < 10)
					savepathname += "0";
				savepathname += c.get(Calendar.MINUTE);
				if (c.get(Calendar.SECOND) < 10)
					savepathname += "0";
				savepathname += c.get(Calendar.SECOND) + ".freeset";
				break;
			} else {
				JOptionPane.showMessageDialog(null, "경로를 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			}
		}

		try {
			OutputStream out = new FileOutputStream(savepathname);
			ObjectOutputStream oos = new ObjectOutputStream(out);

			ArrayList<String> sarr = new ArrayList<String>();

			for (int i = 0; i < cmdTable.getRowCount(); i++) {
				sarr.add(cmdTable.getValueAt(i, 0).toString());
			}

			oos.writeObject(sarr);

			oos.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadFreeset() {
		JFileChooser jfc = new JFileChooser();

		if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			for (int i = 0; i < ctmodel.getRowCount(); i++) {
				ctmodel.removeRow(i);
			}

			try {
				InputStream in = new FileInputStream(jfc.getSelectedFile().toString()); // 입력파일을
																						// 받음
				ObjectInputStream ois = new ObjectInputStream(in); // 입력파일에 있는
																	// 오브젝트를 받음
				ArrayList<String> sarr = new ArrayList<String>();
				sarr = (ArrayList<String>) ois.readObject();

				for (int i = 0; i < sarr.size(); i++) {
					String[] arr1 = new String[1];
					arr1[0] = sarr.get(i);
					ctmodel.addRow(arr1);
				}

				in.close();
				ois.close();
			} catch (Exception e) { // 입력파일이 없을때
				e.printStackTrace();
			}
		}
	}

	public class ufHandler extends Observable implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());

			switch (e.getActionCommand()) {
			case "추가":
				if (textField.getText().isEmpty())
					break;

				String[] arr = new String[1];
				arr[0] = textField.getText();
				ctmodel.addRow(arr);
				textField.setText("");
				break;
			case "삭제":
				if (cmdTable.getSelectedRow() != -1)
					ctmodel.removeRow(cmdTable.getSelectedRow());
				break;
			case "불러오기":
				loadFreeset();
				break;
			case "저장":
				if (cmdTable.getRowCount() == 0)
					JOptionPane.showMessageDialog(null, "명령어 목록이 비어있습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				else
					saveFreeset();
				break;
			case "확인":
				UserForm uf = new UserForm(fileList);

				for (int i = 0; i < cmdTable.getRowCount(); i++) {
					uf.addCommand(cmdTable.getValueAt(i, 0).toString());
				}

				uf.doCommand();

				setChanged();

				notifyObservers();

				dispose();

				break;
			default:
				break;
			}
		}
	}

	public class ufKeyListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			// 엔터를 쳤을 때
			if (e.getKeyCode() == 10 && !textField.getText().isEmpty()) {
				String[] arr = new String[1];
				arr[0] = textField.getText();
				ctmodel.addRow(arr);
				textField.setText("");
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}
	}

	private void helpAboutWindow() {
		setSize(400, 400);
		Label label_helpAbout = new Label("About Us...");
		label_helpAbout.setAlignment(Label.CENTER);

		label_helpAbout.setFont(new Font("굴림", Font.BOLD, 20));
		label_helpAbout.setSize(200, 30);
		label_helpAbout.setBounds(100, 44, 200, 30);
		optionPanel.add(label_helpAbout);

		JLabel label_ourinfo = new JLabel("<html>컴퓨터학부 2013105030 김한영" + "<html><br />" + "컴퓨터학부 2013105027 김준우"
				+ "<html><br />" + "컴퓨터학부 2013105047 박정현" + "<html><br />" + "컴퓨터학부 2013105063 유창재" + "<html><br />"
				+ "컴퓨터학부 2013105106 홍창민" + "<html><br />" + "컴퓨터학부 2013105121 황진커" + "<html><br />" + "<html><br />"
				+ "E-mail : " + "<html><br />" + "yha1483@naver.com" + "<html><br />" + "qwertyazsxc@gmail.com");
		label_ourinfo.setFont(new Font("굴림", Font.PLAIN, 15));
		label_ourinfo.setSize(300, 300);
		label_ourinfo.setVerticalAlignment(SwingConstants.CENTER);
		label_ourinfo.setBounds(90, 80, 210, 250);
		optionPanel.add(label_ourinfo);
	}
}