package dictionary;

public class CwEntry extends Entry {
	private int x;
	private int y;
	private Direction d;
	
	public enum Direction{
		HORIZ,VERT
	}
	
	CwEntry(String word_, String clue_, int x_, int y_, Direction d_){
		super(word_,clue_);
		x=x_;
		y=y_;
		d=d_;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Direction getDir() {
		return d;
	}
}
