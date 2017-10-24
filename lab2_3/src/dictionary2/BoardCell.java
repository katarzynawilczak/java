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
	//umo¿liwiaj¹c¹ ustalenie czy komórka mo¿e byæ pocz¹tkiem/koñcem/œrodkiem 
	//dla nowego has³a poziomo, lub pionowo. Wymyœl strukturê danych
	//w której mo¿na przechowywaæ takie informacje
	
	public void setConntent(String content_) {
		content=content_;
	}
	
	public String getContent() {
		return content;
	}
	

}
