package loginprogram;
import java.io.*;
import login.*;

public class LoginProgram {
    public static void main(String[] argv){
 
     Login log = new Login ("ala", "makota");

	  try{
	
	InputStreamReader rd = new InputStreamReader(System.in);
	
	BufferedReader bfr = new BufferedReader(rd);

    
    // TODO: prosba o wpisanie hasła i loginu
	System.out.print("Wpisz login, a nastepnie haslo: ");
	
	String login = bfr.readLine();
    
    String haslo = bfr.readLine();

    
    /*TODO: sprawdzenie czy podane hasło i login zgadzaja sie z tymi
   
     przechowywanymi w obiekcie log Jeśli tak, to ma zostać

     wyswietlone "OK", jesli nie - prosze wyswietlić informacje o błedzie
        */
	boolean check_ = Login.check(login,haslo);
	if(check_==true){
		System.out.print("OK");
	}
	else{
		System.out.print("Bledny login lub haslo");
	}

  }catch(IOException e){e.printStackTrace();}

   
 }

}
