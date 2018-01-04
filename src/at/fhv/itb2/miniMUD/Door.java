package at.fhv.itb2.miniMUD;


public class Door extends Connector{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3084303426216498812L;
	private boolean _isOpen;
	private int _lockID;
	private String _elementType;
	
	public Door(boolean isOpen, int lockID, Game game){
		super(game);
		_isOpen = isOpen;
		_lockID = lockID;
		_elementType = "Door";
	}

	
	
	

	
	public void unlockDoor(){
		_isOpen = true;
	}
	
	public boolean getIsOpen() {
		return _isOpen;
	}


	public int getLockID() {
		return _lockID;
	}
	
	@Override
	public void enter(Player p, MapElement m, Direction dir) {
		if(_isOpen == true){
			if((getNeighbour(dir) == null)){
				System.out.println("There is no field to move behind this door!\n");
			}else{
				_neighbours[dir.ordinal()].enter(p, m.getNeighbour(dir),dir);
			}
			
		}else{
			System.out.println("Door is locked. It can be opened with the correct key.\n");
		}
		
		
	}

	@Override
	public String getBasicElementData() {
		String output = "Door to ";
		
		return output;
	}




	@Override
	public String getElementType() {
		return "Door";
	}





}
