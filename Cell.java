package ting.nicholas;


	public class Cell {
		private boolean filled;
		private boolean visited;
		private boolean sunk;
		private boolean hit;
		private boolean miss;
		private Ship occupier;
		
		public Cell() {
			visited = false;
			filled = false;
			sunk = false;
			occupier = null;
			hit = false;
			miss = false;
		}
		
		public void setVisited(boolean value){
			visited = value;
		}
		
		public boolean getVisited(){
			return visited;
		}
		
		public void setFilled(boolean value){
			filled = value;
		}
		
		public boolean getFilled(){
			return filled;
		}
		
		
		public void setSunk(boolean value){
			sunk = value;
		}
		
		public boolean getSunk(){
			return sunk;
		}
		
		public void setOccupier(Ship value){
			occupier = value;
		}
		
		public Ship getOccupier(){
			return occupier;
		}
		
		public void setHit(boolean value){
			hit = value;
		}
		
		public void setMiss(boolean value){
			miss = value;
		}
		public boolean getMiss(){
			return miss;
		}
		public boolean getHit(){
			return hit;
		}
		
		public String toString(){
			if(sunk){
				return "!";
			}
			if(hit){
				return "*";
			}
			if(miss){
				return "#"; 
			}
			if(!filled){
				return "~";
			}
			//if(filled){
				//return "+";
			//}
			switch(occupier.getShipName()){
			case "Aircraft Carrier":
				return "A";
			case "Battleship":
				return "B";
			case "Cruiser":
				return "C";
			case "Destroyer":
				return "D";
			case "Submarine":
				return "S";
			
			}
			
			
			
			return null;
		}
		
	}

