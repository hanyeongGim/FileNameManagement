
import java.util.ArrayList;

public class DeleteString extends StringHandle {
	private ArrayList<FileData> fileList = super.getList();

	public DeleteString(ArrayList<FileData> list) {
		super(list);
	}

	public DeleteString() {
		super();
	}

	// end�� �����̸� Ȯ���� �ձ�����
	public void deleteAt(int begin, int end) {
		String sub1, sub2, fn;

		if (begin > end && end >= 0)
			return;

		for (int i = 0; i < fileList.size(); i++) {
			fn = fileList.get(i).getChangedName();
			System.out.print(fn + " -> ");

			try {
				sub1 = fn.substring(0, begin);

				if (end < 0)
					sub2 = fn.substring(fn.lastIndexOf("."), fn.length());
				else
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

	// target ���ڿ��� ���� ���� ������ �� �ڿ� �ִ°� ����
	public void deleteTarget(String target, Boolean includeExt) {
		String sub1, sub2, fn, nefn;

		for (int i = 0; i < fileList.size(); i++) {
			fn = fileList.get(i).getChangedName();
			System.out.print(fn + " -> ");

			try {
				// Ȯ���� ���� �ɼ��� ���� �ִٸ�
				if (!includeExt) {
					nefn = fn.substring(0, fn.lastIndexOf("."));

					// Target�� ������
					if (nefn.lastIndexOf(target) != -1) {
						sub1 = nefn.substring(0, nefn.lastIndexOf(target));
						sub2 = nefn.substring(nefn.lastIndexOf(target) + target.length(), nefn.length());

						fileList.get(i).setChangedName(sub1 + sub2 + fn.substring(fn.lastIndexOf("."), fn.length()));
						fileList.get(i).setUndoName(fn);
					}
				}
				// ���� �ִٸ�
				else {
					// Target�� ������
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

	// target ���ڿ��� �ִٸ� �ϳ��� ����� ���ٸ� Ȯ���� �ܿ� ��� ����������.
	public void deleteExcept(String target) {
		String sub1, sub2, fn;

		for (int i = 0; i < fileList.size(); i++) {
			fn = fileList.get(i).getChangedName();
			System.out.print(fn + " -> ");

			try {
				// target ���ڿ��� ����
				if (fn.lastIndexOf(target) != -1)
					sub1 = target;
				// ������ �� �����
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
