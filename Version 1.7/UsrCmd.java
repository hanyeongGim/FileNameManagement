
public class UsrCmd{
	private String command;
	private String[] option = new String[5];
	public int optionNum;

	public UsrCmd() {

	}

	public UsrCmd(String command) {
		this.command = command;
		optionNum = 0;
	}

	public UsrCmd(String command, String option1) {
		this.command = command;
		option[0] = option1;
		optionNum = 1;
	}

	public UsrCmd(String command, String option1, String option2) {
		this.command = command;
		option[0] = option1;
		option[1] = option2;
		optionNum = 2;
	}

	public String getCommand() {
		return command;
	}

	public String getOption(int index) {
		if (index < optionNum)
			return option[index];
		else
			return "Out of bound";
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public void addOption(String option) {
		if (optionNum < 5) {
			this.option[optionNum++] = option;
		}
	}
}
