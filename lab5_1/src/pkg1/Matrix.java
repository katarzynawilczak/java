package pkg1;
import java.util.Scanner;
import java.io.*;

public class Matrix{
	
	protected static int[][] macierz;
	
	public static int[][] returnMatrix() {
		return macierz;
	}
	
	public Matrix(int[][] macierz_){
		macierz=macierz_;
	}
	
	public Matrix(String filename)throws IOException{
		
		File file1=new File(filename);
		Scanner in = new Scanner(file1);
		//Scanner in2 = new Scanner(file1);
		
		if(file1==null) {throw new FileNotFoundException();}
		try {
		int rows=3;
		int cols=3;
		/*while(in.hasNextLine()) {
			rows++;
			Scanner colIn = new Scanner(in.nextLine());
			while(colIn.hasNextInt()) {
				cols++;
			}
			colIn.close();
		}*/
		int[][]mat= new int[rows][cols];
		for(int i = 0; i < rows; ++i){
		    for(int j = 0; j < cols; ++j){
		        if(in.hasNextInt()){
		            mat[i][j] = in.nextInt();
		        }
		    }
		}
		macierz=mat;
		}catch(Exception e){
		}finally {
			in.close();
			//in2.close();
		}
		
	}
	
	public static int GetChoice(){
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		return num;
	}

	
	public static int[][] getMatrix(){
		
		System.out.print("Podaj liczbe wierszy i kolumn: ");
		Scanner in = new Scanner(System.in);
		int rows = in.nextInt();
		int cols = in.nextInt();
		int[][] matrix = new int[rows][cols];
		System.out.print("Podaj macierz cyfra po cyfrze: ");
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < cols; j++){
				int val = in.nextInt(); 
				matrix[i][j] = val;
			}
		} 
		return matrix;
	
    }
	
	
	public static int[][] add(int[][]tab) throws MatrixDimensionsException{
		if(macierz.length!=tab.length || macierz[0].length != tab[0].length){
			throw new MatrixDimensionsException();
		}
		else{
			for(int i=0;i<macierz.length;i++){
				for(int j=0;j<macierz[0].length;j++){
					macierz[i][j]=macierz[i][j]+tab[i][j];
				}
			}
			return macierz;
		}
		
		
	}
	
	public static int[][] sub(int[][]tab) throws MatrixDimensionsException{
		if(macierz.length!=tab.length || macierz[0].length != tab[0].length){
			throw new MatrixDimensionsException();
		}
		else{
			for(int i=0;i<macierz.length;i++){
				for(int j=0;j<macierz[0].length;j++){
					macierz[i][j]=macierz[i][j]-tab[i][j];
				}
			}
		}
		
		return macierz;
	}
	
	public static int[][] mul(int[][]tab) throws MatrixDimensionsException{
		if(macierz.length!=tab[0].length || macierz[0].length != tab.length){
			throw new MatrixDimensionsException();
		}
		else{
			int [][]result= new int[macierz.length][tab[0].length];
			for(int i=0;i<macierz.length;i++){
				for(int j=0;j<tab[0].length;j++){
					for(int k=0; k<macierz[0].length;k++){
						result[i][j]=result[i][j]+(macierz[i][k]*tab[k][j]);
					}
				}
			}
			return result;
		}	
	}
	public void print() {
		for(int i=0; i<macierz.length;i++){
			System.out.println("\n");
			for(int j=0;j<macierz[0].length; j++){
				System.out.print(macierz[i][j]);
				System.out.print(" ");
			}
		}
	}
}
	
	