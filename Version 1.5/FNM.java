
public class FNM {
	public static void main(String[] args) {
		InsertString is = new InsertString();
		DeleteString ds = new DeleteString();

		//is.insertAsRule(-1, false, true);
		//ds.deleteTarget("txt", false);
		//ds.deleteExcept("wow");
		UserForm uf = new UserForm();
		
		uf.addCommand("DELALL");
		uf.addCommand("DATE#1");
		uf.addCommand("INS#-1# 소프트웨어공학 #");
		uf.addCommand("INSRULE#-1#N#A");
		uf.doCommand();
	}
}
