package pkg1;
import java.io.*;

public class Pesel{
	
	public static String getString(){
		String pesel = null;
	  try{
		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader bfr = new BufferedReader(rd);
 
		pesel = bfr.readLine();
	  }catch(IOException e){e.printStackTrace();}
      return pesel;	  
	}
	
	public static boolean check(String pesel_){
		if(pesel_.length()!=11) return false;
		int[] tab={0,0,0,0,0,0,0,0,0,0,0};
		for (int i = 0;i < 11; i++) {
			char c = pesel_.charAt(i);
			int d = Character.getNumericValue(c);
			tab[i]=d;
		}
		int sum=9*tab[0]+7*tab[1]+3*tab[2]+1*tab[3]+9*tab[4]+7*tab[5]+3*tab[6]+1*tab[7]+9*tab[8]+7*tab[9];
		if(sum%10==tab[10]) return true;
		else return false;
	}
}
