
public class FNM {
	public static void main(String[] args) {
		InsertString is = new InsertString();
		DeleteString ds = new DeleteString();

		//is.insertAsRule(-1, false, true);
		//ds.deleteTarget("txt", false);
		//ds.deleteExcept("wow");
		UserForm uf = new UserForm();
		
		uf.addCommand("#INS 2 WOW#");
		uf.addCommand("#DEL 5 -1#");
		uf.doCommand();
	}
}
