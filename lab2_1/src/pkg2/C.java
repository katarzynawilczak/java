package pkg2;

import pkg1.*;

public class C extends B{
	public C(int n, String na){
			super(n,na);	
		}
	
		/*void changeName(){
			name="Ela";			//pole o dostepie pakietowym z innego pakietu niewidziane w klasie pochodnej
		}*/
		
	public static void main(String[] argv){
		

		A ob1= new A(3, "Jan ");
		//ob1.changeName(); //niewidoczna metoda o dostepie pakietowym
		ob1.callChangeName(); //zmiana imienia metoda kl A (changeName wywo³ane metoda public)
		//ob1.decrement(); //niewidoczna metoda protected w A
		ob1.callDecrement(); //wywo³ana metoda z klasy A(decrement wywo³ane metoda public)

		B ob2 = new B(1, "Olek ");
		//ob2.increment(); //niewidoczna metoda z B private
		ob2.callChangeName(); //z klasy B
		ob2.callIncrement(); // wywo³ana metoda z klasy A private increment z A
		ob2.callDecrement(); // wywo³ana metoda z klasy A ale protected decrement z klasy B!!!

		C ob3= new C(4, "Zuzia ");
		//ob3.changeName();// brak nadpisanej metody
		ob3.callChangeName(); //metoda z B
	


	}	
		

}
