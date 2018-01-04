package at.fhv.itb2.miniMUD;



public class KeyItem extends Item{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5924908068406051694L;
	private int _keyID;
	
	public KeyItem(String name, int keyID, Game game){
		super(name, game);
		_keyID = keyID;
	}

	public KeyItem(String name, Field pos, int keyID, Game game) {
		super(name, pos, game);
		_keyID = keyID;
	}
	
	public int getKeyID(){
		return _keyID;
	}


	@Override
	public void use(Player player) {
		System.out.println("This key may be used in combinition with the correct door.");
	}

}
