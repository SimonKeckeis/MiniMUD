package at.fhv.itb2.miniMUD;


public abstract class Connector extends MapElement{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2343756865561677361L;
	protected MapElement[] _neighbours;
	private Game _game;

	
	public Connector(Game game){
		_neighbours = new Field[4];
		_game = game;
	}
	
	@Override
	public MapElement getNeighbour(Direction dir){
		return _neighbours[dir.ordinal()];
	}
	
	public void setNeighbour(Field f, Direction dir){
		_neighbours[dir.ordinal()] = f;
	}
	
	
	public void setBetween(Field f1, Field f2, Direction dirToNeighbour){
		f2.setNeighbourConnector(this, dirToNeighbour.getOppositeDirection());
		_neighbours[dirToNeighbour.ordinal()] = f2;
		f1.setNeighbourConnector(this, dirToNeighbour);
		_neighbours[dirToNeighbour.getOppositeDirection().ordinal()] = f1;

	}
	
	
	@Override
	public abstract void enter(Player p, MapElement m, Direction dir);
	
	@Override
	public String getElementData(){
		return null;
	}
	

}
