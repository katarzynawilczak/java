package pkg1;
import java.util.*;
import java.io.*;


public class DummyMatrix extends Matrix {
	DummyMatrix(int[][] matrix_){
		super(matrix_);
	}
	DummyMatrix(String filename)throws IOException{
		super(filename);
	}
	
	
	public static int[][] dummyMul(int[][]tab) throws  DummyException{
		if(macierz.length!=tab[0].length || macierz[0].length != tab.length){
			throw new  DummyException(tab,macierz);
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
