package pkg1;

public class DummyException extends Exception {
	
	DummyException(int [][]tab, int[][]macierz){
		
		System.out.println("Zle wymiary macierzy zostana zmodyfikowane");
		if(macierz.length<tab[0].length ) {
			int i=0;
			int j=macierz.length-1;
			
		/*	while(macierz.length<tab[0].length) {
				macierz[i][j]=1;
				while(macierz[0].length>i+1) {
					macierz[i][j]=1;
					i++;
				}
				j++;
			}
		}*/
		int [][]result= new int[macierz.length][tab[0].length];
		for(int i=0;i<macierz.length;i++){
			for(int j=0;j<tab[0].length;j++){
				for(int k=0; k<macierz[0].length;k++){
					result[i][j]=result[i][j]+(macierz[i][k]*tab[k][j]);
				}
			}
		}
		return result;
	} //?????????????????

}
