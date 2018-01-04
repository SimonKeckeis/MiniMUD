package at.fhv.itb2.miniMUD;

import java.util.*;

public class Field extends MapElement{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8912688028658396711L;
	private static final String _defaultDescpriptionShort = "A Field";
	private static final String _defaultDescpriptionLong = "The default description of a regular Field";
	private static final int MAXNEIGHBOURS = 4; //any other amount will not work correctly because of the Direction enumeration.

	
	private int _fieldID;
	private static int _sIDcount;
	private MapElement[] _neighbours;
	private String _descriptionShort;
	private String _descriptionLong;
	private Game _game;
	protected ArrayList<Player> _playersOnField; //priv
	protected ArrayList<Item> _itemsOnField; //priv
	private String _elementType;
	
	
	public Field(Game game){
		_fieldID = _sIDcount++;
		_descriptionShort = _defaultDescpriptionShort;
		_descriptionLong = _defaultDescpriptionLong;
		_neighbours = new MapElement[MAXNEIGHBOURS];
		_game = game;
		_playersOnField = new ArrayList<Player>(); 
		_itemsOnField = new ArrayList<Item>();

	}
	

	public Field(String descriptionShort, String descriptionLong, Game game){
		_fieldID = _sIDcount++;

		_descriptionShort = descriptionShort;
		_descriptionLong = descriptionLong;
		_neighbours = new MapElement[MAXNEIGHBOURS]; //0=n, 1=e, 2=s, 3=w
		_game = game;
		_playersOnField = new ArrayList<Player>(); 
		_itemsOnField = new ArrayList<Item>();
		
	}

	
	
	@Override
	public void enter(Player p, MapElement m, Direction dir) {
		p.getPos().leaveField(p);
		p.setPos((Field) m);														
		((Field) p.getPos()).addPlayer(p);
		
		System.out.println("Moved to " + m.getBasicElementData());
	}
	
	
	public void setNeighbour(Field f, Direction dir){
		_neighbours[dir.ordinal()] = f;
		f._neighbours[dir.getOppositeDirection().ordinal()] = this;
	}
	
	public void setNeighbourConnector(MapElement f, Direction dir){
		_neighbours[dir.ordinal()] = f;
	}
	
	@Override
	public MapElement getNeighbour(Direction dir){
		return _neighbours[dir.ordinal()];
	}
	
	public void leaveField(Player player){
		_playersOnField.remove(player);
	}
	
	public void addPlayer(Player player){
		_playersOnField.add(player);
	}
	
	public void addItem(Item item) {
		_itemsOnField.add(item);	
	}

	public ArrayList<Item> getItemList(){
		return _itemsOnField;
	}
	
	
	
	public String getElementData(){
		StringBuilder output = new StringBuilder();

		output.append("Field: " + _fieldID + ", " + _descriptionShort + "\n" + "Description: " + _descriptionLong + "\n\n");

		//neigbours
		output.append("Neighbours: \n");
		for (int i = 0; i < 4; i++) {
			switch(i) {
		        case 0: 
		        	output.append("North: ");
		        	break;
		        case 1: 
		        	output.append("East: ");
		        	break;
		        case 2: 
		        	output.append("South: ");
		        	break;
		        case 3: 
		        	output.append("West: ");
		        	break;
			}
			
			if(_neighbours[i] == null){
				output.append("None\n");
			}else{
				switch(_neighbours[i].getElementType()){
				case("Field"):
					output.append(_neighbours[i].getBasicElementData()+ "\n");
					break;
				case("Wall"):
					output.append(_neighbours[i].getBasicElementData()+ "\n");
					break;
				case("Door"):
					output.append(_neighbours[i].getBasicElementData()+ _neighbours[i].getNeighbour(Direction.values()[i]).getBasicElementData() + "\n");
					break;
				}
			}
		}
		
		//players
		output.append("\nPlayers on field: \n");
		
		if(_playersOnField.isEmpty()){
			output.append("None\n\n");
		}else{
			for (Player p : _playersOnField) {
				output.append("Name: " + p.getName() + "   |   ID: " + p.getPlayerID() + "\n\n");
			}
		}
		
		
		//items
		output.append("Items on field: \n");
		
		if(_itemsOnField.isEmpty()){
			output.append("None\n");
		}else{
			for (Item i : _itemsOnField) {
				output.append("Name: " + i.getName() + "   |   ID: " + i.getItemID() + "\n");
			}
		}
		
		
		output.append("------------------------------------------------------------------\n");
		
		return output.toString();
	}
	
	@Override
	public String getBasicElementData(){
		return "Field: " + _fieldID + ", " + _descriptionShort;
	}


	@Override
	public String getElementType() {
		return "Field";
	}
}
