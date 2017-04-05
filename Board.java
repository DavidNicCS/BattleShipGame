package ting.nicholas;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Board {

	private Cell[][] board;
	private int rows;
	private int cols;
	private String name;
	private	 ArrayList<Ship> shipClass = new ArrayList<Ship>();

	public Board(int rows, int cols) {
		rows = this.rows;
		cols = this.cols;
		shipClass = new ArrayList<Ship>();
		shipClass.add(new Ship("Aircraft Carrier",5));
		shipClass.add(new Ship("Battleship", 4));
		shipClass.add(new Ship("Cruiser", 3));
		shipClass.add(new Ship("Destroyer", 2));
		shipClass.add(new Ship("Submarine", 3));
		name = null;
		board = new Cell[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {
				board[i][j] = new Cell();
			}
		}
	}
	public ArrayList<Ship> getShipList(){
		return shipClass;
	}
	
	public int getNumberSunk(){
		int sunk = 0;
		for (Ship a: shipClass){
			if(a.getSunk()){
				sunk += 1;
			}
		}
		return sunk;
	}
	
	public String getBoardName(){
		return name;
	}
	
	public void setBoardName(String value){
		name = value;
	}

	public void addShip(Scanner in) {
		boolean successful = false;
		while (!successful) {
			try {
				String [] location = getLocation(in);
				int x = Integer.parseInt(location[0]);
				int y = Integer.parseInt(location[1]);
				Ship subjectBoat = getBoat(in);
				subjectBoat.setStart(x, y);
				int length = subjectBoat.getSize();
				String orientation = getOrientation(in);

				switch (orientation) {
				case "L":
					for (int i = 0; i < length; i++) {
						if (!board[x - i][y].getFilled()) {
							board[x - i][y].setOccupier(subjectBoat);
							board[x - i][y].setFilled(true);
							successful = true;
						}
						else{
							throw new IllegalArgumentException ();
						}
					}
					break;
				case "R":
					for (int i = 0; i < length; i++) {
						if (!board[x + i][y].getFilled()) {
							board[x + i][y].setOccupier(subjectBoat);
							board[x + i][y].setFilled(true);
							successful = true;
						}
						else{
							throw new IllegalArgumentException ();
						}
					}
					break;
				case "U":
					for (int i = 0; i < length; i++) {
						if (!board[x][y - i].getFilled()) {
							board[x][y - i].setOccupier(subjectBoat);
							board[x][y - i].setFilled(true);
							successful = true;
						}
						else{
							throw new IllegalArgumentException ();
						}
					}
					break;
				case "D":
					for (int i = 0; i < length; i++) {
						if (!board[x][y + i].getFilled()) {
							board[x][y + i].setOccupier(subjectBoat);
							board[x][y + i].setFilled(true);
							successful = true;
						}
						else{
							throw new IllegalArgumentException ();
						}
					}
					break;
				}
			}

			catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("The ship does not fit on the board!");
			}
			catch (IllegalArgumentException e){
				System.out.println("The spot is already occupied!");
			}
		}
	}

	public void noUserAddShip(){
		Random rand = new Random();
		String [] orients = {"L","R","U","D"};
		for(int j = 0; j < 5; j++){
			int x = rand.nextInt(9);
			int y = rand.nextInt(9);
			boolean successful = false;
			while (!successful) {
				try {

					Ship subjectBoat = shipClass.get(j);
					subjectBoat.setStart(x, y);
					int length = subjectBoat.getSize();
					int k = rand.nextInt(3);
					String orientation = orients[k];

					switch (orientation) {
					case "L":
						for (int i = 0; i < length; i++) {
							if (!board[x - i][y].getFilled()) {
								board[x - i][y].setOccupier(subjectBoat);
								board[x - i][y].setFilled(true);
								successful = true;
							}
							else{
								throw new IllegalArgumentException ();
							}
						}
						break;
					case "R":
						for (int i = 0; i < length; i++) {
							if (!board[x + i][y].getFilled()) {
								board[x + i][y].setOccupier(subjectBoat);
								board[x + i][y].setFilled(true);
								successful = true;
							}
							else{
								throw new IllegalArgumentException ();
							}
						}
						break;
					case "U":
						for (int i = 0; i < length; i++) {
							if (!board[x][y - i].getFilled()) {
								board[x][y - i].setOccupier(subjectBoat);
								board[x][y - i].setFilled(true);
								successful = true;
							}
							else{
								throw new IllegalArgumentException ();
							}
						}
						break;
					case "D":
						for (int i = 0; i < length; i++) {
							if (!board[x][y + i].getFilled()) {
								board[x][y + i].setOccupier(subjectBoat);
								board[x][y + i].setFilled(true);
								successful = true;
							}
							else{
								throw new IllegalArgumentException ();
							}
						}
						break;
					}
				}

				catch (Exception e) {
				}
			}
		}
	}
	
	public String[] getLocation(Scanner in) {
		System.out.println("Please enter coordinates for your ship (x,y) x and y are between 0 and 9 inclusive.");
		String[] input = in.nextLine().split(",");
		return input;
	}

	public String getOrientation(Scanner in) {
		System.out.println("Please enter orientation of the ship (L/R/U/D):");
		String orientation = in.next();
		while (orientation != "L" || orientation != "R" || orientation != "U" || orientation != "D") {
			System.out.println("Please enter orientation of the ship (L/R/U/D):");
			orientation = in.next();
		}
		return orientation;
	}
	
	public Ship getBoat(Scanner in){
		/*ArrayList<Ship> shipClass = new ArrayList<Ship>();
		shipClass.add(new Ship("Aircraft Carrier",5));
		shipClass.add(new Ship("Battleship", 4));
		shipClass.add(new Ship("Cruiser", 3));
		shipClass.add(new Ship("Destroyer", 2));
		shipClass.add(new Ship("Submarine", 3));*/
		System.out.print("Please enter the class of ship: ");
		for(Ship a: shipClass){
			System.out.printf(a.toString());
		}
		System.out.print("\n");
		boolean successful = false;
		while (!successful) {
			String ship = in.next();
			for (Ship a : shipClass) {
				if (a.getClass().equals(ship)) {
					shipClass.remove(a);
					successful = true;
					return a;
				} else {
					System.out.printf("Please enter the class of ship: ", shipClass.toString(), "\n");
					System.out.println("This code is case-sensitive");
				}
			}
		}
		return null;
	}

	public Cell getCell(int x, int y){
		return board[x][y];
	}
	
	public void display(){
		System.out.println("BOARD");
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.printf("%s ", board[i][j]);
			}
			System.out.println();
		}
		
	}
	
	/*public String getShip(Scanner in) {
		System.out.println(
				"Please enter the class of ship (Aircraft Carrier, Battleship, Cruiser, Destroyer, Submarine):");
		String ship = in.next();
		while (ship != "Aircraft Carrier" || ship != "Battleship" || ship != "Cruiser" || ship != "Destroyer"
				|| ship != "Submarine") {
			System.out.println(
					"Please enter the class of ship (Aircraft Carrier, Battleship, Cruiser, Destroyer, Submarine):");
			ship = in.next();
		}
		return ship;
	}

	public int getShipLength(Scanner in) {
		int length = 0;
		String shipClass = getShip(in);
		switch (shipClass) {
		case "Aircraft Carrier":
			length = 5;
			break;
		case "Battleship":
			length = 4;
			break;
		case "Cruiser":
			length = 3;
			break;
		case "Destroyer":
			length = 2;
			break;
		case "Submarine":
			length = 3;
		}
		return length;
	}*/
	

}

