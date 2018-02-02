import java.awt.Point;
public class Board 
	{
	    private int xLength;
	    private int yLength;
	    private int[][] board;
	    public Board(int x, int y)
	        {
	            this.xLength = x;
	            this.yLength = y;
	        }
	    public void makeBoard()
	        {
	            this.board = new int[this.yLength][this.xLength];
	        }
	    public Point getBoardSize()
	        {
	            Point size = new Point(this.xLength,this.yLength);
	            return size;
	        }
	}