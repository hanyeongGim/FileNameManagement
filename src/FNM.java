
public class FNM {
	public static void main(String[] args) {
		InsertString is = new InsertString();
		DeleteString ds = new DeleteString();
		UserForm uf = new UserForm();

		ds.deleteAt(8, 14);

		for (int i = 0; i < ds.getList().size(); i++) {
			System.out.println(ds.getList().get(i).getChangedName());
		}
	}
}
