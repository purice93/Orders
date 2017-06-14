package tiere;

import gehege.Gehege;

public interface ITier
{

	public String getName();
	public String getTyp();
	public String getArt();
	public Gehege getGehege();
	public void setGehege(Gehege gehege);
}
