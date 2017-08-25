
import java.util.ArrayList;

public class InsertString extends StringHandle {
	private ArrayList<FileData> fileList;

	public InsertString(ArrayList<FileData> list) {
		fileList = list;
	}

	public InsertString() {
		super();
		fileList = super.getList();
	}

	public void insertAt(int index, String s) {
		String sub1, sub2, fn;

		for (int i = 0; i < fileList.size(); i++) {
			if (!fileList.get(i).isSelected())
				continue;

			fn = fileList.get(i).getChangedName();
			System.out.print(fn + " -> ");

			try {
				if (index >= 0) {
					sub1 = fn.substring(0, index);
					sub2 = fn.substring(index);
				} else {
					sub1 = fn.substring(0, fn.lastIndexOf("."));
					sub2 = fn.substring(fn.lastIndexOf("."));
				}

				fileList.get(i).setChangedName(sub1 + s + sub2);
			} catch (StringIndexOutOfBoundsException e) {
				e.printStackTrace();
			}

			fileList.get(i).addUndoName(fn);
			System.out.println(fileList.get(i).getChangedName());
		}
	}

	public void insertAsRule(int index, Boolean isNum, Boolean isAscend) {
		String sub1, sub2, fn, s;
		int cnt = 0;

		// 오름차순
		if (isAscend) {
			for (int i = 0; i < fileList.size(); i++) {
				if (!fileList.get(i).isSelected())
					continue;

				fn = fileList.get(i).getChangedName();
				System.out.print(fn + " -> ");

				try {
					if (index >= 0) {
						sub1 = fn.substring(0, index);
						sub2 = fn.substring(index);
					} else {
						sub1 = fn.substring(0, fn.lastIndexOf("."));
						sub2 = fn.substring(fn.lastIndexOf("."));
					}

					// 숫자인 경우
					if (isNum) {
						s = Integer.toString(cnt + 1);
					}
					// 영문인 경우
					else {
						char c = 'a';
						c += cnt % 26;

						if (cnt < 26)
							s = Character.toString(c);
						else
							s = Character.toString(c) + Integer.toString(cnt / 26);
					}

					cnt++;

					fileList.get(i).setChangedName(sub1 + s + sub2);
				} catch (StringIndexOutOfBoundsException e) {
					e.printStackTrace();
				}
				
				fileList.get(i).addUndoName(fn);
				System.out.println(fileList.get(i).getChangedName());
			}
		}
		// 내림차순
		else {
			for (int i = fileList.size() - 1; i >= 0; i--) {
				if (!fileList.get(i).isSelected())
					continue;

				fn = fileList.get(i).getChangedName();
				System.out.print(fn + " -> ");

				try {
					if (index >= 0) {
						sub1 = fn.substring(0, index);
						sub2 = fn.substring(index);
					} else {
						sub1 = fn.substring(0, fn.lastIndexOf("."));
						sub2 = fn.substring(fn.lastIndexOf("."));
					}

					// 숫자인 경우
					if (isNum) {
						s = Integer.toString(cnt);
					}
					// 영문인 경우
					else {
						char c = 'a';
						c += cnt % 26;

						if (cnt < 26)
							s = Character.toString(c);
						else
							s = Character.toString(c) + Integer.toString(cnt / 26);
					}

					cnt++;

					fileList.get(i).setChangedName(sub1 + s + sub2);
				} catch (StringIndexOutOfBoundsException e) {
					e.printStackTrace();
				}

				fileList.get(i).addUndoName(fn);
				System.out.println(fileList.get(i).getChangedName());
			}
		}
	}
}
