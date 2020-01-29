package game;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;


import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {
   		JFrame frame;
    public Game() {
        
		JFrame frame = new JFrame("JK MazeGame") ;
		Panel panel = new Panel(); 
		frame.setSize(width , height) ; 
		frame.setResizable(false) ; 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; 
		frame.setVisible(true) ; 
		frame.setLocationRelativeTo(null);
		frame.add(panel) ; 
   
                frame.addKeyListener(new KeyListener(){
             @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == 39) panel.move(2);
                    if (e.getKeyCode() == 37) panel.move(4);
                    if (e.getKeyCode() == 38) panel.move(1);
                    if (e.getKeyCode() == 40)  panel.move(3);
                    
                }

                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyReleased(KeyEvent e) {}
        });
    }
    
    
    
    private static int width = 790 , height = 880 ;  
	
}
class Panel extends JPanel implements ActionListener {
	private int width = 800, height = 800 ;
	public int rows , cols , w = 60 ; // number of rows,cols in a grid and width of a grid cell 
	private Cell Current , Next ; 
	private Timer timer ; // needed for the animation 
	private Stack<Cell> stack ;
	int red , green , blue ; 
	private ArrayList<Cell> grid = new ArrayList<Cell>() ; // grid represented as an Arraylist  
        boolean mazeGenerated=false;
        Image img = Toolkit.getDefaultToolkit().getImage("images/jk.resized.png"); 
        int direction = 0;
        int a=0,b=0;
        static Cell cell;
        int currentGrid=0;
        // JPanel constructor 
        public Panel() 
	{
		cols = (int)(width/w) ; 
		rows = (int)(height/w) ;
		for(int j = 0 ; j < cols ; ++j) {
			for(int i = 0 ; i < rows ; ++i) {
				grid.add(new Cell(i, j)) ; 
			}
		}
		red = 102 ; blue = 255 ; green = 178 ;
		stack = new Stack<Cell>() ; 
		timer = new Timer(5,this); 
		timer.start(); 
		Current = grid.get(0) ; 
		Current.visited = true ; 
		setBackground(Color.BLACK) ; 
	}
        


	// returns the index of cell (i,j) in arraylist 
	public int index(int i,int j) {
		if(i < 0 || j < 0 || i >= rows || j >= cols) return -1 ; 
		else
			return i + j * cols ; 
	}
	// return a random unvisited neighbor of the current cell 
	public Cell checkNeighbors(Cell a) {
		int index = -1 ; 
		Cell[] arr = new Cell[4] ; 
		int r = a.getr() , c = a.getc() ; 
		int top = (index(r,c-1) == -1) ? -1 : index(r,c-1) ; 
		int right = (index(r + 1,c) == -1) ? -1 : index(r + 1,c) ; 
		int bottom = (index(r,c+1) == -1) ? -1 : index(r,c+1) ; 
		int left = (index(r-1,c) == -1) ? -1 : index(r-1,c) ;
		if(top != -1 && grid.get(top).visited==false) {
			arr[++index] = grid.get(top) ; 
		}
		if(right != -1 && grid.get(right).visited==false) {
			arr[++index] = grid.get(right) ; 
		}
		if(bottom != -1 && grid.get(bottom).visited==false) {
			arr[++index] = grid.get(bottom) ; 
		}
		if(left != -1 && grid.get(left).visited==false) {
			arr[++index] = grid.get(left) ; 
		}
		if(index >= 0) {
			Random random = new Random();
			int t = random.nextInt(index + 1) ; 
			return arr[t] ;  
		}
		else return new Cell(-1, -1) ; 
	}
	@Override 
        
	public void paint(Graphics g)
	{
            
                     
		super.paint(g) ;
		g.setColor(new Color(255,255,255,100)) ;
            for (Iterator<Cell> it = grid.iterator(); it.hasNext();) {
                cell = it.next();
                int x = cell.getr() * w , y = cell.getc() * w ;
                // draw the walls of the cell
                if(cell.isWall(0)) {
                    g.drawLine(x, y, x + w, y ) ;
                }
                if(cell.isWall(1)) {
                    g.drawLine(x + w, y , x + w , y + w) ;
                }
                if(cell.isWall(2)) {
                    g.drawLine(x + w, y + w, x , y + w) ;
                }
                if(cell.isWall(3)) {
                    g.drawLine(x , y + w, x, y) ;
                }
                // visited and unvisited cells have different colors
                if(cell.visited) {
                    //g.setColor(new Color((int)(red * 255) , (int)(green * 255) , (int)(blue * 255) , 100)) ;
                    g.setColor(new Color(red,green,blue,100)) ;
                    g.fillRect(x, y, w, w) ;
                    g.setColor(new Color(255,255,255,100)) ;
                    //g.setColor(Color.white) ;
                }
                // distinguish the current cell from the rest
                if(cell.equals(Current)&&!mazeGenerated) {
                    g.setColor(new Color(253,120,168)) ;
                    g.fillRect(x, y, w, w) ;
                    g.setColor(new Color(255,255,255,100)) ;
                   
                }
                else if(cell.isOnStack) {
                    g.setColor(new Color(209,158,185,100)) ;
                    g.fillRect(x, y, w, w) ;
                    g.setColor(new Color(255,255,255,100)) ;
                   
                }
            }		
            
                
                 
                //set starting point and ending point color  color
                if(mazeGenerated){
                //set starting point and ending point color  color
               
               // g.setColor(Color.PINK);
               // g.fillRect(0, 0, w, w);
               // g.setColor(Color.gray);
              //  g.fillRect(720, 0, w, w);
                g.drawImage(img, a, b, this);
               
                }
                
	}
        
        
        
	// remove the wall between the current and next cell 
	public void removeWalls(Cell a,Cell b)
	{
		int dx = a.getr() - b.getr() ;
		int dy = a.getc() - b.getc() ;
		if(dx == 1) {
			a.setWall(3, false) ; 
			b.setWall(1, false) ; 
		}
		else if(dx == -1) {
			a.setWall(1, false) ; 
			b.setWall(3, false) ; 
		}
		else if(dy == -1) {
			a.setWall(2, false) ; 
			b.setWall(0, false) ; 
		}
		else if(dy == 1) {
			a.setWall(0, false) ; 
			b.setWall(2, false) ; 
		}
		
	}
	// Timer calls this method every frame 
	@Override
	public void actionPerformed(ActionEvent e) {
		// Standard DFS approach 
		Cell next = checkNeighbors(Current) ; 
		// found a valid neighbor 
		if(next.getr() != -1) {
			next.visited = true ;
			stack.add(Current) ; 
			Current.isOnStack = true ;
			removeWalls(Current,next) ; 
			Current = next ; 
		}
		// couldn't find anything , backtrack! 
		else if(stack.empty()==false) {
			Current = stack.pop() ; 
			Current.isOnStack = false ; 
		}
		mazeGenerated = stack.empty() ; 
		for(Cell cell : grid) {
			if(!cell.visited) {
				mazeGenerated = false ; 
				break ; 
			}
		}
		if(mazeGenerated) {
			Current = null ; 
		}
		
                repaint() ; 
                
	}

        void move(int i){
            Current = grid.get(currentGrid) ;                    
                        
            switch(i){
                case 1:     
                    if(!Current.isWall(0)) {
                        b-=60;
                        currentGrid-=13;
                    }
                break;
                        
                case 2: 
                    if(!Current.isWall(1)) {
                        a+=60; 
                        currentGrid+=1;
                    }
                break;
                   
                case 3: 
                    if(!Current.isWall(2)) {
                        b+=60; 
                        currentGrid+=13;
                    }
                break;  

                case 4: 
                      if(!Current.isWall(3)) {
                        a-=60; 
                        currentGrid-=1;
                    }
                break;
            }
            
          repaint();
        }

                
}
