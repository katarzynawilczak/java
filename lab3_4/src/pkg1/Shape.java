package pkg1;

import java.util.Random;
import java.util.LinkedList;


public abstract class Shape{
	  public String name;
	  /**
	  * Metoda rysujaca w konsoli dany kszta³t
	  */
	  public abstract void draw();
	  
	  public static void main(String []argv) {
		  Trojkat tr = new Trojkat();
		  tr.draw();
		  
		  Kolo ko = new Kolo();
		  ko.draw();
		  
		  Kwadrat kw = new Kwadrat();
		  kw.draw();
		  
		  LinkedList<Shape> figury = new LinkedList<Shape>();
		  figury.add(new Kolo());
		  figury.add(new Trojkat());
		  figury.add(new Kwadrat());
		  
		  Random rand = new Random();
		  int randomNumber = rand.nextInt(3);
		  
		  figury.get(randomNumber).draw();
		  
		  
		  
	  }
	}