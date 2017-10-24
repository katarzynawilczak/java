package dictionary2;

public class Board {
	
	private int width;
	private int height;
	private BoardCell[][] board;
	
	
	
	public int getWidth() {
		return board[0].length;
	}
	
	public int getHeight() {
		return board.length;
	}
	
	public BoardCell getCell(int x, int y) {
		return board[x][y];
	}
	
	public void setCell(int x, int y, BoardCell c) {
		board[x][y]=c;
	}
	
	public LinkedList<BoardCell> getStartCells(){
		
	} //zwracaj�ca wszystkei kom�rki, kt�re mog� by� pocz�tkiem dla nowego has�a
	createPattern(int fromx, int fromy, int tox, int toy) {
		
	}//tworz�c� wzorzec wyra�enia regularnego
}
