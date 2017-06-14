package gehege;

public class Landsaeugetiergehege extends Gehege {
	private final static String art = "Landsaeugetier";

	public Landsaeugetiergehege(String name) {
		super(name, art);
	}

	public int compareTo(Landsaeugetiergehege o) {
		// TODO Auto-generated method stub
		return this.getName().compareTo(o.getName());
	}

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return 0;
	}

}