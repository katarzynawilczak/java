package lpierwsze;
import javaIn2.*;

public class LiczbyPierwsze{
	
	public static void main(String[] argv){
		
			System.out.print("Wyszukaj liczby pierwsze mniejsze od: ");
			int num = JIn.GetInt();
			
			for(int i=2; i<num; i++){
				if(JIn.check(i)==true){
					System.out.print("Liczba pierwsza: " + i + "\n");
				}
			}
	}
}