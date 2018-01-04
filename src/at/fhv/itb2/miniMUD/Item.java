package at.fhv.itb2.miniMUD;

import java.io.Serializable;

public abstract class Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3896276509749163709L;
	private static int _sIDcount;
	private int _itemID;
	private String _name;
	private Game _game;
	private Field _pos;

	public Item(String name, Game game){
		_itemID = _sIDcount++;
		_name = name;
		_game = game;
		_pos = game.getStart();
		_pos.addItem(this);
	}

	public Item(String name, Field pos, Game game){
		_itemID = _sIDcount++;
		_name = name;
		_game = game;
		_pos = pos;
		_pos.addItem(this);
	}

	public abstract void use(Player player);
	
	public Field getPos(){
		return _pos;
	}
	
	public String getName(){
		return _name;
	}
	
	public int getItemID(){
		return _itemID;
	}
}
