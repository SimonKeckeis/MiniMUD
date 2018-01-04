package at.fhv.itb2.miniMUD;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class GameMain {

	public static void main(String[] args) {
		
		Game game = new Game();
		boolean exit = false;
		System.out.println("This is a game.");
		
		Game.generate(game);
		
		Scanner scan;
		String input;
		
		System.out.println("Enter your player name:");
		scan = new Scanner(System.in); 
		input = scan.nextLine();
		Player p = new Player(input, game);
		
		System.out.println("Show commands with the command: help");
		
		while(exit == false){
			System.out.println("\nEnter a command:");
			scan = new Scanner(System.in); 
			input = scan.nextLine();
			
			String[] commands = input.split("\\s+");
			
			switch (commands[0]) {
			case "help":
				System.out.println("\n-----------------------------\n");
				System.out.println("The following commands may be used:\n");
				System.out.println("info\n   Description: prints the current field information of the player.");
				System.out.println("move\n   [north, east, south, west]\n   Description: moves the player to the chosen direction.");
				System.out.println("pickup\n   [item name]\n   Description: player picks up the chosen item.");
				System.out.println("unlock\n   [north, east, south, west] [key ID]\n   Description: player unlocks the door in the chosen direction.");
				System.out.println("load\n   [item name]\n   Description: loads the last saved game file.");
				System.out.println("exit\n   To save the game\n   [yes, no]\n   Description: exit the game.");
				System.out.println("\n-----------------------------\n");
				break;
			case "info":
				System.out.println(p.getPos().getElementData());
				break;
			case "move":
				
				switch (commands[1]) {
				case "north":
					p.move(Direction.NORTH);
					break;
				case "east":
					p.move(Direction.EAST);
					break;
				case "south":
					p.move(Direction.SOUTH);
					break;
				case "west":
					p.move(Direction.WEST);
					break;
				default:
					System.out.println("Unknown direction.\n");
					break;
				}
				break;
			case "pickup":
				p.pickUpItem(commands[1]);
				break;
			case "unlock":
				if(commands.length >= 3){
					try{
						switch (commands[1]) {
						case "north":
							p.unlockDoor(Direction.NORTH, Integer.parseInt(commands[2]));
							break;
						case "east":
							p.unlockDoor(Direction.EAST, Integer.parseInt(commands[2]));
							break;
						case "south":
							p.unlockDoor(Direction.SOUTH, Integer.parseInt(commands[2]));
							break;
						case "west":
							p.unlockDoor(Direction.WEST, Integer.parseInt(commands[2]));
							break;
						default:
							System.out.println("Unknown direction.\n");
							break;
						}
					}catch (NumberFormatException e) {
						System.out.println("KeyID must be a number.");
					}
				}else{
					System.out.println("No KeyID entered!");
				}
				break;
			case "exit":
				System.out.println("\nDo you want to save your progress?\n");
				scan = new Scanner(System.in); 
				input = scan.nextLine();
				switch (input) {
				case "yes":
					
					File savedGameData = new File("savedGameData");
					if(savedGameData.exists()){
						System.out.println("Overriding files...");
					}else{
						savedGameData.mkdir();
					}
					
					try{
						ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("savedGameData\\savedGame.ser"));
						oos.writeObject(game);
						oos.close();
						oos = new ObjectOutputStream(new FileOutputStream("savedGameData\\savedPlayer.ser"));
						oos.writeObject(p);
						oos.close();
						
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					System.out.println("The game was saved successfully.");

					break;
				case "no":
					break;
				default:
					System.out.println("Invalid answer.");
					break;
				}
				
				System.exit(0);
				
				break;
			case "load":
				
				try {
					File savedGame = new File("savedGameData\\savedGame.ser");
					if(savedGame.exists()){
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedGame));
						game = (Game) ois.readObject();
						ois.close();
					}
					
					File savedPlayer = new File("savedGameData\\savedPlayer.ser");
					if(savedPlayer.exists()){
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedPlayer));
						p = (Player) ois.readObject();
						ois.close();
					}
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				System.out.println("Game was loaded successfully.");
				
				break;
			case "all":
				System.out.println(game.getGameMapData());
				break;
			default:
				System.out.println("Unknown command.");
				break;
			}
		}
	}
}
