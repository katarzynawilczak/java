package dictionary2;

public class BoardCell {
	
	private int x;
	private int y;
	private String content;
	
	BoardCell(int x_, int y_, String content_){
		x=x_;
		y=y_;
		content=content_;
	}
	
	public void (disable/enable)(Horiz/Vert)(Start/End/Inner){} 
	//umo�liwiaj�c� ustalenie czy kom�rka mo�e by� pocz�tkiem/ko�cem/�rodkiem 
	//dla nowego has�a poziomo, lub pionowo. Wymy�l struktur� danych
	//w kt�rej mo�na przechowywa� takie informacje
	
	public void setConntent(String content_) {
		content=content_;
	}
	
	public String getContent() {
		return content;
	}
	

}
