package ting.nicholas;


	public class Cell {
		private boolean filled;
		private boolean visited;
		private boolean bombard;
		private boolean sunk;
		private Ship occupier;
		
		public Cell() {
			visited = false;
			filled = false;
			bombard = false;
			sunk = false;
			occupier = null;
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
		
		public void setBombard(boolean value){
			bombard = value;
		}
		
		public boolean getBombard(){
			return bombard;
		}
		
		public void setSunk(boolean value){
			sunk = value;
		}
		
		public void getSunk(){
			
		}
		
			
		public void setOccupier(Ship value){
			occupier = value;
		}
		
		public Ship getOccupier(){
			return occupier;
		}
	}

