package at.fhv.itb2.miniMUD;

import java.io.Serializable;
import java.util.*;

public class Player implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8835342431680301836L;
	private int _playerID;
	private String _name;
	private Game _game;
	private Field _pos;
	private ArrayList<Item> _inventory;
	private static int _sPlayerIDcount;

	
	public Player(String name, Game game){
		_name = name;
		_game = game;
		_pos = game.getStart();
		_playerID = _sPlayerIDcount++;
		_game.getStart().addPlayer(this);
		_inventory = new ArrayList<>();
	}
	
	
	public String getName(){
		return _name;
	}
	
	public int getPlayerID(){
		return _playerID;
	}
	
	public Field getPos(){
		return _pos;
	}
	
	public void setPos(Field field){
		_pos = field;
	}
	
	public ArrayList<Item> getInventory() {
		return _inventory;
	}

	public void setInventory(ArrayList<Item> inventory) {
		_inventory = inventory;
	}
	
	public void pickUpItem(String itemName){
		for (Item item : _pos.getItemList()) {
			if(item.getName().equals(itemName)){
				_inventory.add(item);
				_pos.getItemList().remove(item);
				System.out.println("Picked up: " + item.getName());
				return;
			}
		}
		
		System.out.println("Item cannot be found on this field.");
		
	}
	
	public void useItem(Item item){
		if(_inventory.contains(item)){
			item.use(this);
		}else{
			System.out.println("This item is not in your inventory.");
		}
	}
	
	
	public void unlockDoor(Direction direction, int keyID){
		if(_pos.getNeighbour(direction) instanceof Door){
			Door door = (Door) _pos.getNeighbour(direction);
			for (Item item : _inventory) {
				if(item instanceof KeyItem){
					if(door.getLockID() == ((KeyItem) item).getKeyID()){
						door.unlockDoor();
						System.out.println("Door is now unlocked");
						return;
					}
				}
			}
			System.out.println("Invalid key.");
		}else{
			System.out.println("Keys can only be used on Doors.");
		}
	}
	
	public void move(Direction dir){
		if(_pos.getNeighbour(dir) == null){
			System.out.println("There is no field to move!");
		}else{
			_pos.getNeighbour(dir).enter(this, _pos.getNeighbour(dir), dir);
		}
	}
	
}
