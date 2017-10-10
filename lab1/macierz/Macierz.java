package macierz;

import java.util.Scanner;
import java.io.*;


public class Macierz{
	
	private static int[][] macierz;
	
	public Macierz(int[][] macierz_){
		macierz=macierz_;

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
	
	public static int[][] add(int[][]tab){
		if(macierz.length!=tab.length || macierz[0].length != tab[0].length){
			System.out.print("Bledny wymiar macierzy ");
			return tab;
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
	
	public static int[][] sub(int[][]tab){
		if(macierz.length!=tab.length || macierz[0].length != tab[0].length){
			System.out.print("Bledny wymiar macierzy ");
			return tab;
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
	
	public static int[][] mul(int[][]tab){
		if(macierz.length!=tab[0].length || macierz[0].length != tab.length){
			System.out.print("Bledny wymiar macierzy ");
			return tab;
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
}
	
	