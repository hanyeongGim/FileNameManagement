
import java.util.ArrayList;

public class StringHandle {
	private ArrayList<FileData> fileList;

	public StringHandle(ArrayList<FileData> list) {
		setList(list);
	}

	public StringHandle() {
		fileList = new ArrayList<FileData>();
		
		fileList.add(new FileData("D:\\Downloads\\FileData.java"));
		fileList.add(new FileData("D:\\Downloads\\MainGUI.java"));
		fileList.add(new FileData("D:\\Downloads\\OptionalGUI.java"));
		
		fileList.get(0).select();
		fileList.get(1).select();
		fileList.get(2).select();
	}

	public ArrayList<FileData> getList() {
		return fileList;
	}

	public void setList(ArrayList<FileData> list) {
		fileList = list;
	}
}
