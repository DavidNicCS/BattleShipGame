package ting.nicholas;

public class Ship {
	private String shipName;
	private int shipSize;
	private int startX;
	private int startY;
	private int hitCount;
	private boolean sunk;
	
	public Ship(String shipName, int length){
		this.shipName = shipName;
		this.shipSize = length;
		startX = 0;
		startY = 0;
		sunk = false;
		hitCount = 0;
	}
	
	public int getSize(){
		return shipSize;
	}
	
	public String getShipName(){
		return shipName;
	}
	
	public boolean getSunk(){
		return sunk;
	}
	
	public void setSunk(boolean value){
		sunk = value;
	}
	
	public void setStart(int x, int y){
		startX = x;
		startY = y;
	}
	
	public void setHitCount(){
		hitCount += 1;
	}
	
	public int getX(){
		return startX;
	}
	
	public int getY(){
		return startY;
	}
	
	public int getHitCount(){
		return hitCount;
	}

	@Override
	public String toString(){
		return (getShipName() + " ");
	}

}
