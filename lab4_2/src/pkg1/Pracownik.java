package pkg1;

import java.io.*;

public abstract class Pracownik{
	String pesel;
	double wynagrodzenieBrutto; 
	
	Pracownik(String p, double wynBrutto){
		pesel=p;
		wynagrodzenieBrutto=wynBrutto;
	}
	
	public abstract double wynagrodzenieNetto();
}
