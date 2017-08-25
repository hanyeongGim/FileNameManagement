
import java.util.ArrayList;

public class StringHandle {
	private ArrayList<FileData> fileList = new ArrayList<FileData>();

	public StringHandle(ArrayList<FileData> list) {
		setList(list);
	}

	public StringHandle() {
		fileList.add(new FileData("C:\\Users\\Snorlax\\workspace\\FNM System\\hello.txt"));
		fileList.add(new FileData("C:\\Users\\Snorlax\\workspace\\FNM System\\txthi.txt"));
		fileList.add(new FileData("C:\\Users\\Snorlax\\workspace\\FNM System\\howareyou.txt"));
	}

	public ArrayList<FileData> getList() {
		return fileList;
	}

	public void setList(ArrayList<FileData> list) {
		fileList = list;
	}
}
