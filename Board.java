public class Board 
	{
	    private int xLength;
	    private int yLength;
	    private int board;
	    public Board(int x, int y)
	        {
	            this.xLength = x;
	            this.yLength = y;
	        }
	    public makeBoard()
	        {
	            this.board = new int[6][5];
	        }
	    public Point getBoardSize()
	        {
	            Point size = new Point(this.xLength,this.yLength)
	            return size
	        }
	    public static void main(String[] args) 
	        {
	            
	        }
	}