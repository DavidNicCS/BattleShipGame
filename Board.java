package ting.nicholas;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Board{

	private Cell[][] board;
	private int rows;
	private int cols;
	private String name;
	private ArrayList<Ship> shipClass = new ArrayList<Ship>();

	public Board(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		shipClass = new ArrayList<Ship>();
		shipClass.add(new Ship("Aircraft Carrier", 5));
		shipClass.add(new Ship("Battleship", 4));
		shipClass.add(new Ship("Cruiser", 3));
		shipClass.add(new Ship("Destroyer", 2));
		shipClass.add(new Ship("Submarine", 3));
		name = "";
		board = new Cell[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				board[i][j] = new Cell();
			}
		}
	}

	public int getNumberSunk() {
		int sunk = 0;
		for (Ship a : shipClass) {
			if (a.getSunk()) {
				sunk += 1;
			}
		}
		return sunk;
	}

	public String getBoardName() {
		return name;
	}

	public void setBoardName(String value) {
		name = value;
	}

	public void addShip(Scanner in) { // After the method catches a Ship that does not fit, the start cell will throw an exception that renders the spot un-usable
		while (shipClass.size() != 0) {
			boolean successful = false;
			while (!successful) {
				try {
					Ship subjectBoat = shipClass.get(0);
					System.out.print("Place your ");
					System.out.println(subjectBoat);
					String[] location = getLocation(in);
					int x = Integer.parseInt(location[0]);
					int y = Integer.parseInt(location[1]);
					if(x > 9 || y > 9 || x < 0 || y < 0){
						throw new IllegalArgumentException();
					}
					subjectBoat.setStart(x, y);
					int length = subjectBoat.getSize();
					String orientation = getOrientation(in);
					int counter = 0;
					switch (orientation) {
					case "L":
						for (int i = 0; i < length; i++) {
							if (!board[x][y - i].getFilled()) {
								counter += 1;
								if (counter == length){
									for (int j = 0; j < length; j++) {
										board[x][y - j].setOccupier(subjectBoat);
										board[x][y - j].setFilled(true);
										successful = true;
									}
								}
								
							} else {
								throw new PreFilledException();
							}
						}
						shipClass.remove(0);
						break;
						
					case "R":
						for (int i = 0; i < length; i++) {
							if (!board[x][y + i].getFilled()) {
								counter += 1;
								if (counter == length){
									for (int j = 0; j < length; j++) {
										board[x][y + j].setOccupier(subjectBoat);
										board[x][y + j].setFilled(true);
										successful = true;
									}
								}
								
							}
							else {
								throw new PreFilledException();
							}
						}
						shipClass.remove(0);
						break;
						
					case "U":
						for (int i = 0; i < length; i++) {
							if (!board[x - i][y].getFilled()) {
								counter += 1;
								if (counter == length){
									for (int j = 0; j < length; j++) {
									board[x - j][y].setOccupier(subjectBoat);
									board[x - j][y].setFilled(true);
									successful = true;
								}
								}
								
							}  else {
								throw new PreFilledException();
							}
						}
						shipClass.remove(0);
						break;
						
					case "D":
						for (int i = 0; i < length; i++) {
							if (!board[x + i][y].getFilled()) {
								counter += 1;
								if (counter == length){
									for (int j = 0; j < length; j++) {
										board[x + j][y].setOccupier(subjectBoat);
										board[x + j][y].setFilled(true);
										successful = true;
									}
								}
								
							}  else {
								throw new PreFilledException();
							}
						}
						shipClass.remove(0);
						break;
					}
				}

				catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("The ship does not fit on the board!");
				}catch (IllegalArgumentException e) {
					System.out.println("The spot does not exist!"); 
				}
				catch (PreFilledException e){
					System.out.println("This spot is already occupied!");
				}
			}
			display();
		}
	}

	public void noUserAddShip() {
		Random rand = new Random();
		System.out.println("Entered noUserAddShip");
		String[] orients = { "L", "R", "U", "D" };
		while (shipClass.size() != 0) {
			int x = rand.nextInt(9);
			int y = rand.nextInt(9);
			boolean successful = false;
			while (!successful) {
				try {
					
					Ship subjectBoat = shipClass.get(0);
					subjectBoat.setStart(x, y);
					int length = subjectBoat.getSize();
					int k = rand.nextInt(3);
					String orientation = orients[k];
					int counter = 0;
					switch (orientation) {
					case "L":
						for (int i = 0; i < length; i++) {
							if (!board[x][y - i].getFilled()) {
								counter += 1;
								if (counter == length){
									for (int j = 0; j < length; j++) {
										board[x][y - j].setOccupier(subjectBoat);
										board[x][y - j].setFilled(true);
										successful = true;
									}
								}
								
							} 
						}
						shipClass.remove(0);
						break;
						
					case "R":
						for (int i = 0; i < length; i++) {
							if (!board[x][y + i].getFilled()) {
								counter += 1;
								if (counter == length){
									for (int j = 0; j < length; j++) {
										board[x][y + j].setOccupier(subjectBoat);
										board[x][y + j].setFilled(true);
										successful = true;
									}
								}
								
							}
							
						}
						shipClass.remove(0);
						break;
						
					case "U":
						for (int i = 0; i < length; i++) {
							if (!board[x - i][y].getFilled()) {
								counter += 1;
								if (counter == length){
									for (int j = 0; j < length; j++) {
									board[x - j][y].setOccupier(subjectBoat);
									board[x - j][y].setFilled(true);
									successful = true;
								}
								}
								
							} 
						}
						shipClass.remove(0);
						break;
						
					case "D":
						for (int i = 0; i < length; i++) {
							if (!board[x + i][y].getFilled()) {
								counter += 1;
								if (counter == length){
									for (int j = 0; j < length; j++) {
										board[x + j][y].setOccupier(subjectBoat);
										board[x + j][y].setFilled(true);
										successful = true;
									}
								}
								
							}  
						}
						shipClass.remove(0);
						break;
					}
				}

				catch (Exception e) {
				}
		}
		}
		display();
	}

	public String[] getLocation(Scanner in) {
		System.out.println("Please enter coordinates for your ship (row, column) row and column are between 0 and 9 inclusive.");
		String[] input = in.nextLine().split(",");
		return input;
	}

	public String getOrientation(Scanner in) {
		boolean successful = false;
		while (!successful) {
			System.out.println("Please enter orientation of the ship (L/R/U/D):");
			String orientation = in.nextLine();
			if (orientation.equals("L") || orientation.equals("R") || orientation.equals("U")
					|| orientation.equals("D")) {
				successful = true;
				return orientation;
			}
		}
		return null;
	}

	public Cell getCell(int x, int y) {
		return board[x][y];
	}

	public void display() {
		System.out.println(getBoardName());
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.printf("%s ", board[i][j]);
			}
			System.out.println();
		}

	}


}
