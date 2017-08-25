
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

public class UserForm extends StringHandle {
	private ArrayList<FileData> fileList = super.getList();
	private ArrayList<UsrCmd> cmdList = new ArrayList<UsrCmd>();

	public UserForm(ArrayList<FileData> list) {
		super(list);
	}

	public UserForm() {
		super();
	}

	// #을 구분자로 사용함.
	public void addCommand(String input) {
		try {
			String cmd = input.trim();

			StringTokenizer tok = new StringTokenizer(cmd);

			UsrCmd cur = new UsrCmd();

			cur.setCommand(tok.nextToken("#"));

			while (tok.hasMoreTokens()) {
				cur.addOption(tok.nextToken("#"));
			}

			cmdList.add(cur);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doCommand() {
		UsrCmd cmd;
		InsertString is = new InsertString(fileList);
		DeleteString ds = new DeleteString(fileList);

		for (int i = 0; i < cmdList.size(); i++) {
			cmd = cmdList.get(i);

			try {
				switch (cmd.getCommand()) {
				case "INS":
					is.insertAt(Integer.parseInt(cmd.getOption(0)), cmd.getOption(1));
					break;
				case "INSRULE":
					Boolean o1, o2;

					if (cmd.getOption(1).equals("N") || cmd.getOption(1).equals("n"))
						o1 = true;
					else
						o1 = false;

					if (cmd.getOption(2).equals("A") || cmd.getOption(2).equals("a"))
						o2 = true;
					else
						o2 = false;

					is.insertAsRule(Integer.parseInt(cmd.getOption(0)), o1, o2);
					break;
				case "DEL":
					ds.deleteAt(Integer.parseInt(cmd.getOption(0)), Integer.parseInt(cmd.getOption(1)));
					break;
				case "DELALL":
					ds.deleteAt(0, -1);
					break;
				case "DELSTR":
					ds.deleteTarget(cmd.getOption(0), false);
					break;
				case "DELEXPT":
					ds.deleteExcept(cmd.getOption(0));
					break;
				case "DATE":
					insertDate(Integer.parseInt(cmd.getOption(0)));
					break;
				default:
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 날짜 삽입, 인자는 1이면 앞, 아니면 뒤
	public void insertDate(int loc) {
		String sub1, fn;
		Calendar c = Calendar.getInstance();

		for (int i = 0; i < fileList.size(); i++) {
			fn = fileList.get(i).getChangedName();
			System.out.print(fn + " -> ");

			try {
				sub1 = Integer.toString(c.get(Calendar.YEAR));
				if (c.get(Calendar.MONTH) < 9)
					sub1 += "0";
				sub1 += Integer.toString(c.get(Calendar.MONTH) + 1);
				if (c.get(Calendar.DATE) < 10)
					sub1 += "0";
				sub1 += Integer.toString(c.get(Calendar.DATE));

				if (loc == 1)
					fileList.get(i).setChangedName(sub1 + fn);
				else
					fileList.get(i).setChangedName(fn + sub1);

				fileList.get(i).setUndoName(fn);
			} catch (StringIndexOutOfBoundsException e) {
				e.printStackTrace();
				fileList.get(i).setError();
			}

			System.out.println(fileList.get(i).getChangedName());
		}
	}
}
