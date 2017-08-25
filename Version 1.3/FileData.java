
import java.io.File;
import java.util.Scanner;

public class FileData {
	private String changedName;
	private String undoName;
	private File f;
	private Boolean errorFlag;

	public FileData(String path) {
		f = new File(path);
		changedName = f.getName();
		undoName = changedName;
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

	public void undo() {
		if (!errorFlag)
			changedName = undoName;
	}

	public void modify() {
		File newf = new File(changedName);

		if (!f.exists()) {
			System.out.println("������ �������� �ʽ��ϴ�.");
			return;
		}

		try {
			String ext1 = f.getName().substring(f.getName().lastIndexOf(".") + 1);
			String ext2 = changedName.substring(changedName.lastIndexOf(".") + 1);

			if (ext1.compareTo(ext2) != 0) {
				System.out.println("���� ��/���� Ȯ���ڰ� �ٸ��� ������ �߻��� ���� �ֽ��ϴ�.\n���� �����Ͻðڽ��ϱ�?");

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
