package pkg1;


public class PracownikEtatowy extends Pracownik{
	
	PracownikEtatowy(String p,double wB){
		super(p,wB);
	}

	public double wynagrodzenieNetto(){
		return wynagrodzenieBrutto*0.8;
	}

}
