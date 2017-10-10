package javaIn2;
import java.util.Scanner;

public class JIn{
	
	public static int GetInt(){
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		return num;
	}
	
	public static boolean check(int p){
		for(int i=2;i<p; i++){
			if(p%i==0){
				return false;
			}
		}
		return true;
	}
	
	
}