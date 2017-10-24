package pkg1;
import java.util.*;


public class Program{
	public double metoda(final double var){
		//var=2;//blad "final parameter var may not be assigned"
		return var;
	}
	
	public void metoda2(final LinkedList<Double> list){
		list.remove(1); //mozna usunac
		Double el = new Double(3);
		list.add(el); //mozna dodawac
		
		LinkedList<Double> list2 = new LinkedList<Double>();
		//list = list2; //blad "final parameter list may not be assigned"
	}

}
