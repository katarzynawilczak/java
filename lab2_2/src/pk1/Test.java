package pk1;

import java.util.LinkedList;
import java.util.ListIterator;
import java.io.*;
import java.util.Scanner;

public class Test {
	
	LinkedList<Punkt3D> punkty = new LinkedList<Punkt3D>();
	
	public void menu() {
		
		System.out.println("1. Wczytaj punkt 3D");
		System.out.println("2. Wyœwietl wszystkie punkty");
		System.out.println("3. Oblicz odleg³oœæ");
		System.out.print("4. Zakoñcz");
		
		Scanner in = new Scanner(System.in);
		int choice = in.nextInt();
		
		if(choice==1) {
			System.out.print("Podaj wspó³rzêdne punktu");
			Scanner in1 = new Scanner(System.in);
			int w1 = in1.nextInt();
			int w2 = in1.nextInt();
			int w3 = in1.nextInt();
			
			Punkt3D punkt1=new Punkt3D(w1,w2,w3);
			
			punkty.add(punkt1);
			
			menu();
			
		}
		
		if(choice==2) {
			System.out.println("Punkty: ");
			for(Punkt3D pkt : punkty) {
				System.out.println(pkt.GetX()+" "+pkt.GetY()+" "+pkt.GetZ());
			}
			menu();
		}
		
		if(choice==3) {
			System.out.print("Podaj wspó³rzêdne punktu 1");
			Scanner in2 = new Scanner(System.in);
			int w1 = in2.nextInt();
			int w2 = in2.nextInt();
			int w3 = in2.nextInt();
			
			Punkt3D punkt1=new Punkt3D(w1,w2,w3);
			
			System.out.print("Podaj wspó³rzêdne punktu 2");
			Scanner in3 = new Scanner(System.in);
			int w4 = in3.nextInt();
			int w5 = in3.nextInt();
			int w6 = in3.nextInt();
			
			Punkt3D punkt2=new Punkt3D(w4,w5,w6);
			
			System.out.println("Dystans: "+ punkt1.distance(punkt2));
			menu();
			
		}
		
		if(choice==4) {
			System.exit(0);
		}
		
	}
	
	public static void main(String[] argv) {
		Test t = new Test();
		t.menu();
			
	}

}
