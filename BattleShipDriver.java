package ting.nicholas;

import java.util.*;

public class BattleShipDriver {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		Board playerBoard = new Board(10, 10);
		playerBoard.setBoardName("playerBoard");
		Board oppPlayerPerspective = new Board(10, 10);
		oppPlayerPerspective.setBoardName("oppPlayerPerspective");
		Board opponentBoard = new Board(10, 10);
		opponentBoard.setBoardName("opponentBoard");

		while (!(playerBoard.getShipList().size() == 0)) {
			playerBoard.addShip(in);
		}
		opponentBoard.noUserAddShip();
		int choose = rand.nextInt(1);

		switch (choose) {
		case 0:
			System.out.println("Player goes first");
			while (playerBoard.getNumberSunk() != 5 || opponentBoard.getNumberSunk() != 5) {
				fire(in, oppPlayerPerspective, opponentBoard);
				checkSunk(opponentBoard, oppPlayerPerspective);
				playerBoard.display();
				oppPlayerPerspective.display();
				noUserFire(playerBoard);
				checkSunk(playerBoard, null);
				playerBoard.display();
				oppPlayerPerspective.display();
			}
			if(playerBoard.getNumberSunk() == 5){
				System.out.println("You lost!");
			}
			else{
				System.out.println("Congratulations, you won!");
			}
			break;
		case 1:
			System.out.println("AI goes first");
			while (playerBoard.getNumberSunk() != 5 || opponentBoard.getNumberSunk() != 5) {
				noUserFire(playerBoard);
				checkSunk(playerBoard, null);
				playerBoard.display();
				oppPlayerPerspective.display();
				fire(in, oppPlayerPerspective, opponentBoard);
				checkSunk(opponentBoard, oppPlayerPerspective);
				playerBoard.display();
				oppPlayerPerspective.display();
			}
			if(playerBoard.getNumberSunk() == 5){
				System.out.println("You lost!");
			}
			else{
				System.out.println("Congratulations, you won!");
			}
			break;

		}
	}

	public static void fire(Scanner in,  Board display, Board defender) {

		boolean successful = false;
		while (!successful) {
			try {
				System.out.println("Enter coordinates to fire upon (x,y):");
				String[] input = in.nextLine().split(",");
				int x = Integer.parseInt(input[0]);
				int y = Integer.parseInt(input[1]);
				Cell defendCell = defender.getCell(x, y);
				Cell displayCell = display.getCell(x, y);

				if (!defendCell.getVisited()) {
					defendCell.setVisited(true);
					if (defendCell.getFilled()) {
						defendCell.setHit(true);
						displayCell.setHit(true);
						System.out.println("You've hit an enemy ship!");
						Ship defendShip = defendCell.getOccupier();
						defendShip.setHitCount();
					} else {
						defendCell.setMiss(true);
						displayCell.setMiss(true);
						System.out.println("You've missed!");
					}
					successful = true;
				} else {
					System.out.println("That coordinate as already been fired upon!");
				}
			} catch (Exception e) {
				System.out.println("Those coordinates do not exist!");
			}
		}
	}

	public static void noUserFire(Board defender) {

		boolean successful = false;
		while (!successful) {
			try {
				Random rand = new Random();
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						if ((defender.getCell(i, j).getVisited())) {
							int k = rand.nextInt(3);
							switch (k) {
							case 0:

								Cell left = defender.getCell(i - 1, j);
								if (!left.getFilled()) {
									left.setVisited(true);
									if (left.getFilled()) {
										left.setHit(true);
										System.out.println("Your ship has been hit!");
										Ship defendShip = left.getOccupier();
										defendShip.setHitCount();
									} else {
										left.setMiss(true);
										System.out.println("Your fleet is unscathed!");
									}
								}
								break;

							case 1:

								Cell right = defender.getCell(i + 1, j);
								if (!right.getFilled()) {
									right.setVisited(true);
									if (right.getFilled()) {
										right.setHit(true);
										System.out.println("Your ship has been hit!");
										Ship defendShip = right.getOccupier();
										defendShip.setHitCount();
									} else {
										right.setMiss(true);
										System.out.println("Your fleet is unscathed!");
									}
								}

								break;

							case 2:

								Cell up = defender.getCell(i, j + 1);
								if (!up.getFilled()) {
									up.setVisited(true);
									if (up.getFilled()) {
										up.setHit(true);
										System.out.println("Your ship has been hit!");
										Ship defendShip = up.getOccupier();
										defendShip.setHitCount();
									} else {
										up.setMiss(true);
										System.out.println("Your fleet is unscathed!");
									}
								}

								break;

							case 3:

								Cell down = defender.getCell(i, j - 1);
								if (!down.getFilled()) {
									down.setVisited(true);
									if (down.getFilled()) {
										down.setHit(true);
										System.out.println("Your ship has been hit!");
										Ship defendShip = down.getOccupier();
										defendShip.setHitCount();

									} else {
										down.setMiss(true);
										System.out.println("Your fleet is unscathed!");
									}
								}

								break;
							}

						} else {
							int x = rand.nextInt(9);
							int y = rand.nextInt(9);
							Cell defendCell = defender.getCell(x, y);

							if (!defendCell.getVisited()) {
								defendCell.setVisited(true);
								if (defendCell.getFilled()) {
									defendCell.setHit(true);
									System.out.println("Your ship has been hit!");
									Ship defendShip = defendCell.getOccupier();
									defendShip.setHitCount();

								} else {
									defendCell.setMiss(true);
									System.out.println("Your fleet is unscathed!");
								}
							}
						}

					}
					successful = true;
				}
			} catch (Exception e) {
			}
		}
	}

	public static String checkSunk(Board subject, Board display) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Ship checkShip = subject.getCell(i, j).getOccupier();
				if (!(checkShip.getSunk())) {
					if (checkShip.getHitCount() == checkShip.getSize()) {
						checkShip.setSunk(true);
						switch (subject.getBoardName()) {
						case "playerBoard":
							System.out.printf("Your ", checkShip.toString(), " has been sunk!", "/n");
							for (int l = 0; i < 10; i++) {
								for (int m = 0; j < 10; j++) {
									if (subject.getCell(l, m).getOccupier() == checkShip) {
										subject.getCell(l, m).setSunk(true);
																																																																										}
								}
							}
						case "opponentBoard":
							System.out.printf("The enemy ", checkShip.toString(), " has been sunk!", "/n");
							for (int l = 0; i < 10; i++) {
								for (int m = 0; j < 10; j++) {
									if (subject.getCell(l, m).getOccupier() == checkShip) {
										subject.getCell(l, m).setSunk(true);
										display.getCell(l, m).setSunk(true);
									}
								}
							}
						}

					}
				}
			}
		}
		return null;
	}
}
