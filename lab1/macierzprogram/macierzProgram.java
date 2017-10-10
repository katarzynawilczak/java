package macierzprogram;

import macierz.*;

public class macierzProgram{
	
	public static void main(String[] argv){
		
		int[][] macierz1 = {{1,2,3},{1,2,3},{1,2,3}};
		Macierz mac = new Macierz(macierz1);
		
		System.out.print("Kliknij, jaka operacje chcesz wykonac: 1-dodac macierz, 2-odjac, 3-pomnozyc");
		int choice = Macierz.GetChoice();
		if(choice==1){
			int[][] macierz2 = Macierz.getMatrix();
			int[][] result = Macierz.add(macierz2);
			for(int i=0; i<result.length;i++){
				System.out.println("\n");
				for(int j=0;j<result[0].length; j++){
					System.out.print(result[i][j]);
					System.out.print(" ");
				}
				
			}
		}
		if(choice==2){
			int[][] macierz2 = Macierz.getMatrix();
			int[][] result = Macierz.sub(macierz2);
			for(int i=0; i<result.length;i++){
				System.out.println("\n");
				for(int j=0;j<result[0].length; j++){
					System.out.print(result[i][j]);
					System.out.print(" ");
				}
				
			}
		}
		if(choice==3){
			int[][] macierz2 = Macierz.getMatrix();
			int[][] result = Macierz.mul(macierz2);
			for(int i=0; i<result.length;i++){
				System.out.println("\n");
				for(int j=0;j<result[0].length; j++){
					System.out.print(result[i][j]);
					System.out.print(" ");
				}
				
			}
		}
	}
}