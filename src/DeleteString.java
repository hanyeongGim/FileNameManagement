
import java.util.ArrayList;

public class DeleteString extends StringHandle {
	private ArrayList<FileData> fileList;

	public DeleteString(ArrayList<FileData> list) {
		fileList = list;
	}

	public DeleteString() {
		super();
		fileList = super.getList();
	}

	// end가 음수이면 확장자 앞까지로
	public void deleteAt(int begin, int end) {
		String sub1, sub2, fn;

		if (begin > end && end >= 0)
			return;

		if (begin < 0)
			return;

		for (int i = 0; i < fileList.size(); i++) {
			if (!fileList.get(i).isSelected())
				continue;

			fn = fileList.get(i).getChangedName();
			System.out.print(fn + " -> ");

			try {
				sub1 = fn.substring(0, begin);

				if (end < 0)
					sub2 = fn.substring(fn.lastIndexOf("."), fn.length());
				else if (end >= fn.length())
					sub2 = "";
				else
					sub2 = fn.substring(end + 1, fn.length());

				fileList.get(i).setChangedName(sub1 + sub2);
			} catch (StringIndexOutOfBoundsException e) {
				e.printStackTrace();
			}

			fileList.get(i).addUndoName(fn);
			System.out.println(fileList.get(i).getChangedName());
		}
	}

	// 인자가 하나면 해당 index만 지움
	public void deleteAt(int index) {
		String sub1, sub2, fn;

		if (index < 0)
			return;

		for (int i = 0; i < fileList.size(); i++) {
			if (!fileList.get(i).isSelected())
				continue;

			if (index >= fileList.get(i).getChangedName().length())
				continue;

			fn = fileList.get(i).getChangedName();
			System.out.print(fn + " -> ");

			try {
				sub1 = fn.substring(0, index);
				sub2 = fn.substring(index + 1, fn.length());

				fileList.get(i).setChangedName(sub1 + sub2);
			} catch (StringIndexOutOfBoundsException e) {
				e.printStackTrace();
			}

			fileList.get(i).addUndoName(fn);
			System.out.println(fileList.get(i).getChangedName());
		}
	}

	// target 문자열이 여러 개가 있으면 맨 뒤에 있는걸 지움
	public void deleteTarget(String target, Boolean includeExt) {
		String sub1, sub2, fn, nefn;

		for (int i = 0; i < fileList.size(); i++) {
			if (!fileList.get(i).isSelected())
				continue;

			fn = fileList.get(i).getChangedName();
			System.out.print(fn + " -> ");

			try {
				// 확장자 포함 옵션이 꺼져 있다면
				if (!includeExt) {
					nefn = fn.substring(0, fn.lastIndexOf("."));

					// Target이 있으면
					if (nefn.lastIndexOf(target) != -1) {
						sub1 = nefn.substring(0, nefn.lastIndexOf(target));
						sub2 = nefn.substring(nefn.lastIndexOf(target) + target.length(), nefn.length());

						fileList.get(i).setChangedName(sub1 + sub2 + fn.substring(fn.lastIndexOf("."), fn.length()));
					}
				}
				// 켜져 있다면
				else {
					// Target이 있으면
					if (fn.lastIndexOf(target) != -1) {
						sub1 = fn.substring(0, fn.lastIndexOf(target));
						sub2 = fn.substring(fn.lastIndexOf(target) + target.length(), fn.length());

						fileList.get(i).setChangedName(sub1 + sub2);
					}
				}
			} catch (StringIndexOutOfBoundsException e) {
				e.printStackTrace();
			}
			
			fileList.get(i).addUndoName(fn);
			System.out.println(fileList.get(i).getChangedName());
		}
	}

	// target 문자열이 있다면 하나만 남기고 없다면 확장자 외에 모두 지워버린다.
	public void deleteExcept(String target) {
		String sub1, sub2, fn;

		for (int i = 0; i < fileList.size(); i++) {
			if (!fileList.get(i).isSelected())
				continue;

			fn = fileList.get(i).getChangedName();
			System.out.print(fn + " -> ");

			try {
				// target 문자열이 존재
				if (fn.lastIndexOf(target) != -1)
					sub1 = target;
				// 없으면 다 지운다
				else
					sub1 = "";

				sub2 = fn.substring(fn.lastIndexOf("."), fn.length());

				fileList.get(i).setChangedName(sub1 + sub2);
			} catch (StringIndexOutOfBoundsException e) {
				e.printStackTrace();
			}

			fileList.get(i).addUndoName(fn);
			System.out.println(fileList.get(i).getChangedName());
		}
	}
}
