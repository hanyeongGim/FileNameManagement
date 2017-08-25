
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

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JList;

public class OptionalGUI extends JFrame {
	private JTextField textField1;
	private JTextField textField2;
	JPanel optionPanel = new JPanel();
	JPanel userFormPanel = new JPanel();
	private String frameName;

	boolean numcheck = true;
	boolean ascendcheck = true;
	String textFstring = "";
	private final JList list = new JList();
	private final JButton button = new JButton("");

	public OptionalGUI() {
		super("temp");

		initialize();
	}

	// insert string��
	public OptionalGUI(String classtype) {
		super(classtype);
		frameName = classtype;
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
		list.setBounds(0, 0, 394, 300);
		userFormPanel.add(list);
		button.setBounds(10, 310, 97, 23);
		
		userFormPanel.add(button);
		setSize(400, 600);
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
