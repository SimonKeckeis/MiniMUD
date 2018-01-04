package at.fhv.itb2.miniMUD;

import java.io.*;
import java.util.ArrayList;



public class Game implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 30423724624627338L;
	
	private ArrayList<MapElement> _gameMap;
	private ArrayList<Player> _playerList;
	private Field _start = new Field(this); //has ID 0
	
	
	public Game(){
		_gameMap = new ArrayList<MapElement>();
		_playerList = new ArrayList<Player>();
		_gameMap.add(_start);
	}
	
	
	public Field getStart(){
		return _start;
	}
	
	public void setStart(Field f){
		_start = f;
	}
	
	public void addPlayer(Player player) {
		_playerList.add(player);
	}
	
	public void addElement(MapElement m){
		_gameMap.add(m);
	}
	
	public String getGameMapData(){
		StringBuilder output = new StringBuilder();
		
		for (MapElement element : _gameMap) {
				output.append(element.getElementData());
		}
		
		return output.toString();
	}
	
	
	public static void generate(Game game){
		Wall w = new Wall(game);
		Field f1 = new Field(game);
		Field f2 = new Field(game);
		Field f3 = new Field(game);
		Field f4 = new Field(game);
		Field f5 = new Field(game);
		Field f6 = new Field(game);
		Field f7 = new Field(game);
		Field f8 = new Field(game);
		Field f9 = new Field(game);
		Field f10 = new Field(game);
		Field f11 = new Field(game);
		game.addElement(f11);
		game.addElement(f10);
		game.addElement(f9);
		game.addElement(f8);
		game.addElement(f7);
		game.addElement(f6);
		game.addElement(f5);
		game.addElement(f4);
		game.addElement(f3);
		game.addElement(f2);
		game.addElement(f1);

		
		Door d = new Door(false, 0, game);
		Door d1 = new Door(true, 0, game);
		
		
		game.getStart().setNeighbour(f1, Direction.EAST);
		game.getStart().setNeighbour(f3, Direction.SOUTH);
		f1.setNeighbour(f3, Direction.EAST);
		d.setBetween(f1, f2, Direction.EAST);
		f1.setNeighbour(f4, Direction.SOUTH);
		f4.setNeighbour(f5, Direction.SOUTH);
		f5.setNeighbour(f6, Direction.SOUTH);
		f6.setNeighbour(f7, Direction.EAST);
		f7.setNeighbour(f8, Direction.EAST);
		d1.setBetween(f7, f8, Direction.EAST);
		f8.setNeighbour(f9, Direction.EAST);
		f8.setNeighbour(f10, Direction.SOUTH);
		f10.setNeighbour(f11, Direction.EAST);
		f9.setNeighbour(f11, Direction.SOUTH);
		
		f2.setNeighbourConnector(w, Direction.EAST);
		f2.setNeighbourConnector(w, Direction.NORTH);
		f2.setNeighbourConnector(w, Direction.SOUTH);
		
		f8.setNeighbourConnector(w, Direction.NORTH);
		f9.setNeighbourConnector(w, Direction.EAST);
		f9.setNeighbourConnector(w, Direction.NORTH);
		f10.setNeighbourConnector(w, Direction.WEST);
		f10.setNeighbourConnector(w, Direction.SOUTH);
		f11.setNeighbourConnector(w, Direction.SOUTH);
		f11.setNeighbourConnector(w, Direction.EAST);
		
		KeyItem k = new KeyItem("k", f1, 0, game);
	}

	
	public void saveGame() throws IOException{

		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("savedGame.ser"))
				){
			oos.writeObject(this);
		}
	}
	
	public static Game loadSavedGame() throws FileNotFoundException, IOException, ClassNotFoundException{
		File f = new File("savedGame.ser");
		if(f.exists()){
			try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))){
				Game g = new Game();
				return g = (Game) ois.readObject();
			}
		}else{
			throw new FileNotFoundException();
		}
		
	}
	
}
