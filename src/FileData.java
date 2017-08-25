
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class FileData {
	private String changedName;
	private ArrayList<String> undoList;
	private File f;
	private Boolean selected;

	public FileData(String path) {
		f = new File(path);
		changedName = f.getName();
		undoList = new ArrayList<String>();
		deselect();
	}

	public String getChangedName() {
		return changedName;
	}

	public void setChangedName(String s) {
		changedName = s;
	}

	public ArrayList<String> getUndoList() {
		return undoList;
	}

	public void addUndoName(String s) {
		undoList.add(s);
	}

	public File getFile() {
		return f;
	}

	public void setFile(File fi) {
		f = fi;
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
		if (!undoList.isEmpty()) {
			changedName = undoList.get(undoList.size() - 1);

			undoList.remove(undoList.size() - 1);
		}
	}

	public void modify() {
		File newf = new File(getFile().getParentFile().getPath() + "\\" + changedName);

		if (!f.exists()) {
			System.out.println("파일이 존재하지 않습니다.");
			return;
		} else if (newf.equals(getFile()))
			return;

		String ext1 = f.getName().substring(f.getName().lastIndexOf(".") + 1);
		String ext2 = changedName.substring(changedName.lastIndexOf(".") + 1);

		if (ext1.compareTo(ext2) != 0) {
			String s = JOptionPane.showInputDialog(
					"file : " + changedName + "\n변경 전/후의 확장자가 다르면 문제가 발생할 수도 있습니다.\n정말 진행하시겠습니까?(Y / N)");
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
			String str = "[" + c.get(Calendar.YEAR) + "." + Integer.toString(c.get(Calendar.MONTH) + 1) + "."
					+ c.get(Calendar.DATE) + " ";
			if (c.get(Calendar.HOUR_OF_DAY) < 10)
				str += "0";
			str += c.get(Calendar.HOUR_OF_DAY) + ":";
			if (c.get(Calendar.MINUTE) < 10)
				str += "0";
			str += c.get(Calendar.MINUTE) + ":";
			if (c.get(Calendar.SECOND) < 10)
				str += "0";
			str += c.get(Calendar.SECOND) + "] " + getFile().getPath() + " -> " + newf.getName() + "\r\n";

			FileWriter wr = new FileWriter("change.log", true);

			wr.write(str);

			wr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}