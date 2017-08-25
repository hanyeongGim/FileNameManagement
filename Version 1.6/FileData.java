
import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class FileData {
	private String changedName;
	private String undoName;
	private File f;
	private Boolean errorFlag;
	private Boolean selected;

	public FileData(String path) {
		f = new File(path);
		changedName = f.getName();
		undoName = changedName;
		setNoError();
		deselect();
	}

	public String getChangedName() {
		return changedName;
	}

	public void setChangedName(String s) {
		changedName = s;
	}

	public String getUndoName() {
		return undoName;
	}

	public void setUndoName(String s) {
		undoName = s;
	}

	public File getFile() {
		return f;
	}

	public void setFile(File fi) {
		f = fi;
	}

	public Boolean isError() {
		return errorFlag;
	}

	public void setError() {
		errorFlag = true;
	}

	public void setNoError() {
		errorFlag = false;
	}

	public Boolean isSelected() {
		return selected;
	}

	public void select() {
		selected = true;
	}

	public void deselect() {
		selected = false;
	}

	public void undo() {
		if (!errorFlag)
			changedName = undoName;
	}

	public void modify() {
		File newf = new File(getFile().getParentFile().getPath() + "\\" + changedName);

		if (!f.exists()) {
			System.out.println("������ �������� �ʽ��ϴ�.");
			return;
		} else if (newf.equals(getFile()))
			return;

		String ext1 = f.getName().substring(f.getName().lastIndexOf(".") + 1);
		String ext2 = changedName.substring(changedName.lastIndexOf(".") + 1);

		if (ext1.compareTo(ext2) != 0) {
			String s = JOptionPane.showInputDialog(
					"file : " + changedName + "\n���� ��/���� Ȯ���ڰ� �ٸ��� ������ �߻��� ���� �ֽ��ϴ�.\n���� �����Ͻðڽ��ϱ�?(Y / N)");
			// Scanner s = new Scanner(System.in);

			if (!s.equals("y")) {
				return;
			}
		}

		while (newf.exists()) {
			String subs = newf.getPath().substring(0, newf.getPath().lastIndexOf("."));

			newf = new File(subs + "(1)." + ext2);
		}

		f.renameTo(newf);
		
		try {
			Calendar c = Calendar.getInstance();
			String str = "[" + c.get(Calendar.YEAR) + "." + c.get(Calendar.MONTH) + "." + c.get(Calendar.DATE) + " "
					+ c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND) + "] "
					+ getFile().getPath() + " -> " + newf.getName() + "\r\n";

			FileWriter wr = new FileWriter("change.log", true);
			
			wr.write(str);
			
			wr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}