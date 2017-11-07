package pkg1;

import java.util.*;
import java.util.LinkedList;
import java.util.Collections;

import java.io.*;


public class Kadry{
	private static LinkedList<Pracownik> listaPracownikow = new LinkedList<Pracownik>();

	public static void usunPracownika(Pracownik pracownik){
		listaPracownikow.remove(pracownik);	
	}
	
	public static void dodajPracownika(Pracownik pracownik){
		if(pracownik!=null) {
		listaPracownikow.add(pracownik);
		}
		else {
		System.out.print("Blad");
		};
	}
	
	public static void szukajPracownika(String p){
		boolean check_ = Pesel.check(p);
		if(check_==true){
			for(Pracownik i : listaPracownikow){
				if(i.pesel.equals(p)) {
				System.out.println("Pracownik znaleziony: "+ i.pesel +" " + i.wynagrodzenieBrutto);
				}
			}
		}
		else{
			System.out.print("Bledny pesel");
		}
	}
	
	
	public static double  pobierzNetto(Pracownik p) {
		return p.wynagrodzenieNetto();
	}
	
	public static void zmienBrutto(Pracownik p, double wart) {
		p.wynagrodzenieBrutto=wart;
	}
	public static void main(String []args){
		Pracownik prac = new PracownikEtatowy("97041605468", 2300.00);
		Pracownik stud = new Student("44051401359", 1400.00);
		Pracownik prac2 = new PracownikEtatowy("97141605468", 2600.00);
		Pracownik stud2 = new Student("44051501359", 2000.00);
		dodajPracownika(prac);
		dodajPracownika(stud);
		dodajPracownika(prac2);
		dodajPracownika(stud2);
		
		
		
		szukajPracownika("97041605468");
		

		double d = pobierzNetto(prac);
		System.out.println("Wynagrodzenie netto: " + d);
		
		double d2 = pobierzNetto(stud);
		System.out.println("Wynagrodzenie netto: " + d2);
		
		zmienBrutto(prac, 2700);
		System.out.println("Zmienione brutto: " + prac.pesel + " " + prac.wynagrodzenieBrutto);
		
		System.out.println("Pracownicy: ");
		for(Pracownik i : listaPracownikow) {
			System.out.println(i.pesel + " " + i.wynagrodzenieBrutto);
		}
		
		Collections.sort(listaPracownikow,new MyComp());
		
		System.out.println("Pracownicy posortowani: ");
		for(Pracownik i : listaPracownikow) {
			System.out.println(i.pesel + " " + i.wynagrodzenieBrutto);
		}
	}
	
	

}
