import java.util.ArrayList;

public class UserForm extends InsertString {
	private ArrayList<FileData> fileList = new ArrayList<FileData>();

	public UserForm(ArrayList<FileData> list) {
		fileList = list;
	}

	public UserForm() {
		fileList.add(new FileData("C:\\Users\\Snorlax\\workspace\\FNM System\\hello.txt"));
		fileList.add(new FileData("C:\\Users\\Snorlax\\workspace\\FNM System\\txthi.txt"));
		fileList.add(new FileData("C:\\Users\\Snorlax\\workspace\\FNM System\\howareyou.txt"));
	}
	
	
}
