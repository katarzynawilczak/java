package pkg1;

public class Student extends Pracownik{
	Student(String p, double wB){
		super(p,wB);
	}
	public double wynagrodzenieNetto(){
		return wynagrodzenieBrutto*0.6;
		
	}
}
