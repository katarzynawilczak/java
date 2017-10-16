package pkg1;

public class B extends A{

	public B(int n, String na){
		super(n,na);
	}
	
	
	void changeName(){
		name="Ola "; //pole o dostepie pakietowym z tego samego pakietu widziane w klasie pochodnej
	}
	

	protected void decrement(){
		number-=3;
	}
	
	private void increment(){
		number+=2;
	}
	
	public static void main(String[] argv){
		
		B ob1 = new B(4,"Pola ");
		ob1.increment(); //powiêksza o 2 czyli metoda nadpisana w klasie B
		ob1.callIncrement(); //powieksza o 1 - metoda z klasy A private
		ob1.decrement(); //pomniejsza o 3 - metoda z B
		//ob1.changeName();
		ob1.Show();
		ob1.callChangeName(); //wywo³uje changeName z klasy B
		ob1.Show();
		ob1.callDecrement(); //pomniejsza o 3 - metoda z kl B protected
		
		A ob2 = new A(3, "Agata ");
		ob2.callIncrement(); // powieksza o 1
		ob2.decrement(); //zmienijsza o 1 - metoda z A
		ob2.Show();
		ob2.callDecrement();
		ob2.Show();
		
	}
	

}
