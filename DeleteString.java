import java.util.ArrayList;

public class DeleteString {
	private ArrayList<FileData> fileList = new ArrayList<FileData>();

	public DeleteString(ArrayList<FileData> list) {
		fileList = list;
	}

	public DeleteString() {
		fileList.add(new FileData("C:\\Users\\Snorlax\\workspace\\FNM System\\hello.txt"));
		fileList.add(new FileData("C:\\Users\\Snorlax\\workspace\\FNM System\\txthi.txt"));
		fileList.add(new FileData("C:\\Users\\Snorlax\\workspace\\FNM System\\howareyou.txt"));
	}

	public void deleteAt(int begin, int end) {
		String sub1, sub2, fn;

		for (int i = 0; i < fileList.size(); i++) {
			fn = fileList.get(i).getChangedName();
			System.out.print(fn + " -> ");

			try {
				sub1 = fn.substring(0, begin);
				sub2 = fn.substring(end + 1, fn.length());

				fileList.get(i).setChangedName(sub1 + sub2);
				fileList.get(i).setUndoName(fn);
			} catch (StringIndexOutOfBoundsException e) {
				e.printStackTrace();
				fileList.get(i).setError();
			}

			System.out.println(fileList.get(i).getChangedName());
		}
	}

	// target 문자열이 여러 개가 있으면 맨 뒤에 있는걸 지움
	public void deleteTarget(String target, Boolean includeExt) {
		String sub1, sub2, fn, nefn;

		for (int i = 0; i < fileList.size(); i++) {
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
						fileList.get(i).setUndoName(fn);
					}
				}
				// 켜져 있다면
				else {
					// Target이 있으면
					if (fn.lastIndexOf(target) != -1) {
						sub1 = fn.substring(0, fn.lastIndexOf(target));
						sub2 = fn.substring(fn.lastIndexOf(target) + target.length(), fn.length());

						fileList.get(i).setChangedName(sub1 + sub2);
						fileList.get(i).setUndoName(fn);
					}
				}
			} catch (StringIndexOutOfBoundsException e) {
				e.printStackTrace();
				fileList.get(i).setError();
			}

			System.out.println(fileList.get(i).getChangedName());
		}
	}

	// target 문자열이 있다면 하나만 남기고 없다면 확장자 외에 모두 지워버린다.
	public void deleteExcept(String target) {
		String sub1, sub2, fn;

		for (int i = 0; i < fileList.size(); i++) {
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
				fileList.get(i).setUndoName(fn);
			} catch (StringIndexOutOfBoundsException e) {
				e.printStackTrace();
				fileList.get(i).setError();
			}

			System.out.println(fileList.get(i).getChangedName());
		}
	}
}
