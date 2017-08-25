
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JEditorPane;
import javax.swing.JTable;

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
	String[] colNames = { "�Է��� ��ɾ�" };
	DefaultTableModel ctmodel = new DefaultTableModel(colNames, 0);
	private JTable cmdTable;
	private ArrayList<FileData> fileList;

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

		if (classtype == "���ϴ� ��ġ�� ���ڿ� ����") {
			insertAtWindow();
		} else if (classtype == "��Ģ�� ���� ���ڿ� ����") {
			insertRuleWindow();
		} else if (classtype == "����� ����") {
			userFormWindow();
		} else if (classtype == "���ڿ� ���� ����") {
			helpIsnertWindow();
		} else if (classtype == "About FNM System") {
			helpAboutWindow();
		}
	}

	// ����� ��
	private void initialize() {

		setVisible(true);
		setResizable(false);
		getContentPane().add(optionPanel, BorderLayout.CENTER);
		optionPanel.setLayout(null);
		getContentPane().add(userFormPanel, BorderLayout.CENTER);
		userFormPanel.setLayout(null);
	}

	private void insertAtWindow() {
		setSize(600, 200);

		// ������ ���ڿ�
		Label label_insertAtString = new Label("������ ���ڿ�");
		label_insertAtString.setAlignment(Label.CENTER);
		label_insertAtString.setFont(new Font("���� ���", Font.PLAIN, 14));
		label_insertAtString.setBounds(10, 30, 100, 40);
		optionPanel.add(label_insertAtString);

		textField1 = new JTextField();
		textField1.setBounds(120, 30, 350, 40);
		optionPanel.add(textField1);
		textField1.setColumns(10);

		// ��ġ
		Label label_insertAtIndex = new Label("��ġ");
		label_insertAtIndex.setAlignment(Label.CENTER);
		label_insertAtIndex.setFont(new Font("���� ���", Font.PLAIN, 14));
		label_insertAtIndex.setBounds(10, 100, 100, 40);
		optionPanel.add(label_insertAtIndex);

		textField2 = new JTextField();
		textField2.setBounds(120, 100, 350, 40);
		optionPanel.add(textField2);
		textField2.setColumns(10);

		JButton buttonInsertAtConfirm = new JButton("Ȯ��");
		buttonInsertAtConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFstring = textField1.getText();
				System.out.printf(textFstring + "//");
				textFstring = textField2.getText();
				System.out.printf(textFstring + "//" + "insertAtConfirm");
			}
		});
		buttonInsertAtConfirm.setBounds(480, 65, 100, 40);
		optionPanel.add(buttonInsertAtConfirm);

	}

	private void insertRuleWindow() {
		setSize(600, 200);

		String str_numcheck[] = { "����", "����" };
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

		String str_ascendcheck[] = { "��������", "��������" };
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
		optionPanel.add(textField1);
		textField1.setColumns(10);

		JButton buttonInsertRuleConfirm = new JButton("Ȯ��");
		buttonInsertRuleConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFstring = textField1.getText();
				System.out.println(textFstring + "//" + "insertRuleConfirm");
			}
		});
		buttonInsertRuleConfirm.setBounds(330, 105, 200, 40);
		optionPanel.add(buttonInsertRuleConfirm);

	}

	private void userFormWindow() {
		setName("����� ����");

		setSize(430, 420);

		textField = new JTextField();
		textField.setBounds(50, 310, 220, 30);
		userFormPanel.add(textField);
		textField.setColumns(10);

		JLabel label = new JLabel("�Է�");
		label.setBounds(13, 317, 30, 15);
		userFormPanel.add(label);

		JButton btnNewButton = new JButton("�߰�");
		btnNewButton.setBounds(282, 309, 60, 30);
		btnNewButton.addActionListener(ufHandler);
		userFormPanel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("����");
		btnNewButton_1.setBounds(354, 309, 60, 30);
		btnNewButton_1.addActionListener(ufHandler);
		userFormPanel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("�ҷ�����");
		btnNewButton_2.setBounds(9, 350, 80, 30);
		btnNewButton_2.addActionListener(ufHandler);
		userFormPanel.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("����");
		btnNewButton_3.setBounds(101, 350, 80, 30);
		btnNewButton_3.addActionListener(ufHandler);
		userFormPanel.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("��ɾ�");
		btnNewButton_4.setBounds(193, 350, 80, 30);
		btnNewButton_4.addActionListener(ufHandler);
		userFormPanel.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("Ȯ��");
		btnNewButton_5.setBounds(312, 349, 100, 30);
		btnNewButton_5.addActionListener(ufHandler);
		userFormPanel.add(btnNewButton_5);

		userFormPanel.setLayout(new BorderLayout(0, 0));

		cmdTable = new JTable(ctmodel);
		cmdTable.setBounds(0, 0, 424, 300);
		cmdTable.setDragEnabled(false);

		cmdTable.getTableHeader().setReorderingAllowed(false);

		cmdTable.setRowHeight(25);
		cmdTable.getColumnModel().getColumn(0).setPreferredWidth(245);

		ufScrollPane = new JScrollPane(cmdTable);
		userFormPanel.add(ufScrollPane, BorderLayout.CENTER);
	}

	private class ufHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());

			switch (e.getActionCommand()) {
			case "�߰�":
				String[] arr = new String[1];
				arr[0] = textField.getText();
				ctmodel.addRow(arr);
				textField.setText("");
				break;
			case "����":
				ctmodel.removeRow(cmdTable.getSelectedRow());
				break;
			case "�ҷ�����":
				break;
			case "����":
				break;
			case "Ȯ��":
				UserForm uf = new UserForm(fileList);
				
				for (int i = 0; i < cmdTable.getRowCount(); i++) {
					uf.addCommand(cmdTable.getValueAt(i, 0).toString());
				}

				uf.doCommand();
				
				break;
			}
		}

	}

	// ���ڿ� �Է� ����
	private void helpIsnertWindow() {
		setSize(600, 600);
		Label label_helpInsert = new Label(frameName);
		label_helpInsert.setAlignment(Label.CENTER);
		label_helpInsert.setFont(new Font("���� ���", Font.PLAIN, 14));
		label_helpInsert.setSize(200, 50);
		label_helpInsert.setLocation((int) (getSize().width - label_helpInsert.getSize().width) / 2, 30);
		optionPanel.add(label_helpInsert);
	}

	// ���ڿ� ���� ����
	private void helpDeleteWindow() {
		setSize(600, 600);
		Label label_helpDelete = new Label(frameName);
		label_helpDelete.setAlignment(Label.CENTER);
		label_helpDelete.setFont(new Font("���� ���", Font.PLAIN, 14));
		label_helpDelete.setSize(200, 50);
		label_helpDelete.setLocation((int) (getSize().width - label_helpDelete.getSize().width) / 2, 30);
		optionPanel.add(label_helpDelete);
	}

	private void helpAboutWindow() {
		setSize(400, 400);
		Label label_helpAbout = new Label("About Us...");
		label_helpAbout.setAlignment(Label.CENTER);
		label_helpAbout.setFont(new Font("���� ���", Font.PLAIN, 14));
		label_helpAbout.setSize(200, 30);
		label_helpAbout.setBounds((int) (getSize().width - label_helpAbout.getSize().width) / 2, 30, 200, 30);
		optionPanel.add(label_helpAbout);

		JLabel label_ourinfo = new JLabel("<html>��ǻ���к� 2013105030 ���ѿ�(����)" + "<html><br />" + "��ǻ���к� 2013105027 ���ؿ�"
				+ "<html><br />" + "��ǻ���к� 2013105047 ������" + "<html><br />" + "��ǻ���к� 2013105063 ��â��" + "<html><br />"
				+ "��ǻ���к� 2013105106 ȫâ��" + "<html><br />" + "��ǻ���к� 2013105121 Ȳ��Ŀ" + "<html><br />" + "��Ÿ ����"
				+ "<html><br />" + "E-mail : yha1483@naver.com" + "<html><br />" + "       : qwertyazsxc@gmail.com");
		label_ourinfo.setFont(new Font("���� ���", Font.PLAIN, 14));
		label_ourinfo.setSize(300, 300);
		label_ourinfo.setBounds((int) (getSize().width - label_helpAbout.getSize().width) / 2, 70, 300, 250);
		optionPanel.add(label_ourinfo);

	}
}
