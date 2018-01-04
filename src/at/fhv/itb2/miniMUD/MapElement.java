package at.fhv.itb2.miniMUD;

import java.io.Serializable;

public abstract class MapElement implements Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 16974872253641263L;

	public abstract void enter(Player p, MapElement m, Direction dir);

	public abstract String getElementType();

	public abstract String getBasicElementData();

	public abstract MapElement getNeighbour(Direction dir);
	
	public abstract String getElementData();
}
