package gehege;

public class Aquarium extends Gehege {
	private final static String art = "Wassertier";

	public Aquarium(String name) {
		super(name, art);
	}

	public int compareTo(Aquarium o) {
		// TODO Auto-generated method stub
		return this.getName().compareTo(o.getName());
	}

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return 0;
	}
}