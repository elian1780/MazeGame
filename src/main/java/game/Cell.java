package game;


class Cell {
	// Coordinates of the top-left corner of the grid cell 
	private int r , c ;
	

	private boolean[] walls = new boolean[4] ; 
	public boolean visited , isOnStack ; 
	Cell(int r,int c )
	{
		this.r = r ; 
		this.c = c ; 		
		// initially which all walls are present 
		for(int i = 0 ; i < 4 ; ++i ) {
			walls[i] = true ; 
		}
		visited = false ; 
		isOnStack = false ; 
	}
	
	public int getr() {return r ;}
	public int getc() {return c ;}
	// return true if the wall is present 
	public boolean isWall(int index) {
		return walls[index] ; 
	}
	// set the wall's visibility 
	public void setWall(int index,boolean f) {
		walls[index] = f ; 
	}
	public String toString() {
		return "(" + r + "," + c + ")" ;
	}
	
}
