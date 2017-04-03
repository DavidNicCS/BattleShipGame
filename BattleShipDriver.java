package ting.nicholas;

import java.util.*;

public class BattleShipDriver {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		Board playerBoard = new Board(10, 10);
		Board oppPlayerPerspective = new Board(10,10);
		Board opponentBoard = new Board(10, 10);
		
		while(!(playerBoard.getShipList().size() == 0)){
			playerBoard.addShip(in);
		}
		opponentBoard.noUserAddShip();
		int choose = rand.nextInt(1);
		
		switch(choose){
			case 0:
				 while(){
					 
				 }
			case 1:
				
		}
	}
		
	public void fire(Scanner in, /*Board attacker,*/ Board defender){
		
		boolean successful = false;
		while (!successful) {
			try {
				System.out.println("Enter coordinates to fire upon (x,y):");
				String[] input = in.nextLine().split(",");
				int x = Integer.parseInt(input[0]);
				int y = Integer.parseInt(input[1]);
				Cell defendCell = defender.getCell(x,y);
				
				if (!defendCell.getBombard()) {
					defendCell.setBombard(true);
					if (defendCell.getFilled()) {
						System.out.println("You've hit an enemy ship!");
						Ship defendShip = defendCell.getOccupier();
						defendShip.setHitCount();
						if (defendShip.getHitCount() == defendShip.getSize()){
							defendShip.setSunk(true);
							
						}
					}
					successful = true;
				}
				else {
					System.out.println("That coordinate as already been fired upon!");
				}
			}
			catch (Exception e) {
				System.out.println("Those coordinates do not exist!");
			}
		}
	}
	
	public void noUserFire(Scanner in, Board defender){
		
		boolean successful = false;
		while (!successful) {
			try {
				Random rand = new Random();
				int x = rand.nextInt(9);
				int y = rand.nextInt(9);
				Cell defendCell = defender.getCell(x,y);
				
				if (!defendCell.getBombard()) {
					defendCell.setBombard(true);
					if (defendCell.getFilled()) {
						System.out.println("Your ship has been hit!");
						Ship defendShip = defendCell.getOccupier();
						defendShip.setHitCount();
						if (defendShip.getHitCount() == defendShip.getSize()){
							defendShip.setSunk(true);
							
						}
					}
					successful = true;
				}
				else {
				}
			}
			catch (Exception e) {
			}
		}
	}
	
}
