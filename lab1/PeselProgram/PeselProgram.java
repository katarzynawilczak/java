package PeselProgram;

import Pesel.*;

public class PeselProgram{
	
	public static void main(String argv[]){

		System.out.print("Wpisz pesel: ");
	
		String pesel = Pesel.getString();
    
		boolean check_ = Pesel.check(pesel);
		if(check_==true){
			System.out.print("Pesel prawidlowy");
		}
		else{
			System.out.print("Bledny pesel");
		}
   
	}
	
}
