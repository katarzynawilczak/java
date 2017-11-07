package pkg1;

import java.util.Comparator;

public class MyComp implements Comparator<Pracownik>{
	public int compare(Pracownik pr1, Pracownik pr2) {
		if(pr1.wynagrodzenieBrutto>pr2.wynagrodzenieBrutto) {
			return 1; 
		}
		if(pr1.wynagrodzenieBrutto<pr2.wynagrodzenieBrutto) {
			return -1; 
		}
		else return 0;
	}
}
