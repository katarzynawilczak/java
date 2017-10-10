import java.io.*;
 
public class Reader{
  public static void main(String[] argv){
	String text = null;
	try{
		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader bfr = new BufferedReader(rd);
 
	System.out.print("Wpisz jakis tekst: ");
	text = bfr.readLine();
  	}catch(IOException e){e.printStackTrace();}

  System.out.println("Wpisales: "+text);
	}
}



/* brak wyj¹tku */