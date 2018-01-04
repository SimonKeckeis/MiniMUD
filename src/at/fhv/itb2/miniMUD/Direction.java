package at.fhv.itb2.miniMUD;


public enum Direction {
	NORTH, EAST, SOUTH, WEST;
	
	public Direction getOppositeDirection(){
        switch(this) {
            case NORTH: return SOUTH;
            case SOUTH: return NORTH;
            case EAST:  return WEST;
            case WEST:  return EAST;
        }
        return null;
    }
}
