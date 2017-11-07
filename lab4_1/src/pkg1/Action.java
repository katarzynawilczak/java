package pkg1;

import java.util.*;
import java.util.Random;
import java.util.LinkedList;

public class Action{


	public static void showElement(int n, LinkedList<Shape> list){
		System.out.println("Element number " + n + ": ");
		list.get(n-1).draw();
	}

	public static void addElement(Shape element, LinkedList<Shape> list){
		list.add(element);
	}

	public static void main(String[] args) {
		  
		  LinkedList<Shape> figury = new LinkedList<Shape>();
		  figury.add(new Kolo());
		  figury.add(new Trojkat());
		  figury.add(new Kwadrat());
		  
		  Random rand = new Random();
		  int randomNumber = rand.nextInt(3);
		  
		  System.out.println("Random figure: ");
		  figury.get(randomNumber).draw();
		  
		  LinkedList<Shape> figury1 = new LinkedList<Shape>();
  		  
		  Trojkat tr = new Trojkat();
		  figury1.add(tr);

		  Kolo ko = new Kolo();
		  figury1.add(ko);
		  
		  Kwadrat kw = new Kwadrat();
		  figury1.add(kw);
		  
		  showElement(1,figury1);
		  showElement(2,figury1);
		  showElement(3,figury1);
		
		  Kolo kolo1 = new Kolo();		  
		  addElement(kolo1, figury1);
	          System.out.println("Added element: ");
		  showElement(4,figury1);
		  
	  }


}
