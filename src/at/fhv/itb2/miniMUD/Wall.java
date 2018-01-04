package at.fhv.itb2.miniMUD;

public class Wall extends Connector{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8414635552805230500L;

	public Wall(Game game) {
		super(game);
	}

	public void setWall(Field f, Direction dir){
		f.setNeighbourConnector(this, dir);
	}
	
	public void setWall(Field n, Field e, Field s, Field w){
		n.setNeighbourConnector(this, Direction.NORTH);
		e.setNeighbourConnector(this, Direction.EAST);
		s.setNeighbourConnector(this, Direction.SOUTH);
		w.setNeighbourConnector(this, Direction.WEST);
	}
	
	@Override
	public void enter(Player p, MapElement m, Direction dir) {
		System.out.println("Cannot enter  a wall.");
		
	}

	@Override
	public String getElementType() {
		return "Wall";
	}

	@Override
	public String getBasicElementData() {
		return "Wall";
	}

}
