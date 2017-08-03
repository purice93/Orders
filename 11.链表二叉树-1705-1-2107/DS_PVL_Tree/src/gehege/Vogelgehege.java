package gehege;

public class Vogelgehege extends Gehege {
	private final static String art = "Vogel";

	public Vogelgehege(String name) {
		super(name, art);
	}

	public int compareTo(Vogelgehege o) {
		// TODO Auto-generated method stub
		return this.getName().compareTo(o.getName());
	}

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
