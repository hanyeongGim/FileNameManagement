
import java.io.File;
import java.util.Scanner;

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
			System.out.println("파일이 존재하지 않습니다.");
			return;
		}

		try {
			String ext1 = f.getName().substring(f.getName().lastIndexOf(".") + 1);
			String ext2 = changedName.substring(changedName.lastIndexOf(".") + 1);

			if (ext1.compareTo(ext2) != 0) {
				System.out.println("변경 전/후의 확장자가 다르면 문제가 발생할 수도 있습니다.\n정말 진행하시겠습니까?");

				Scanner s = new Scanner(System.in);

				if (!s.next().equals("y")) {
					return;
				}
			}

			f.renameTo(newf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
