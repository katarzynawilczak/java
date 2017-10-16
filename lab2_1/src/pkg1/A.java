package pkg1;

import java.io.*;


public class A{
	protected int number;
	String name;

	public A(int n, String na){
		number=n;
		name=na;	
	}
	
	public void Show() {
		System.out.print(name+ "->"+number+ " ");
	}
	
	public void callDecrement(){
	decrement();
	System.out.print(number+ " ");
			
	}
	
	public void callChangeName(){
	changeName();
	System.out.print(name);
	}
	
	public void callIncrement(){
	increment();
	System.out.print(number + " ");
	}
	
	private void increment(){
		number+=1;	
	}
	
	protected void decrement(){
		number-=1;
	}
	
	void changeName(){
		name="Ala ";	
	}

}
