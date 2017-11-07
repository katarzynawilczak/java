package pkg1;

public class MatrixProgram{
	
	public static void main(String[] argv) throws Exception{
		
		//int[][] macierz1 = {{1,2,3},{1,2,3},{1,2,3}};
		//Matrix mac = new Matrix(macierz1);
		Matrix mac = new Matrix("C:\\Users\\KASIA\\eclipse-workspace\\lab5_1\\src\\pkg1\\myfile1");
		mac.print();
		System.out.println();
		System.out.print("Kliknij, jaka operacje chcesz wykonac: 1-dodac macierz, 2-odjac, 3-pomnozyc");
		int choice = Matrix.GetChoice();
		if(choice==1){
			try {
			int[][] macierz2 = Matrix.getMatrix();
			int[][] result = Matrix.add(macierz2);
			for(int i=0; i<result.length;i++){
				System.out.println("\n");
				for(int j=0;j<result[0].length; j++){
					System.out.print(result[i][j]);
					System.out.print(" ");
				}
				
			}
			}catch (MatrixDimensionsException e) {
			}catch (Exception e) {
			}
			
		}
		if(choice==2){
			try {
				int[][] macierz2 = Matrix.getMatrix();
				int[][] result = Matrix.sub(macierz2);
			for(int i=0; i<result.length;i++){
				System.out.println("\n");
				for(int j=0;j<result[0].length; j++){
					System.out.print(result[i][j]);
					System.out.print(" ");
				}
				
			}
			}catch (MatrixDimensionsException e) {
			}catch (Exception e) {
			}
		}
		if(choice==3){
			try {
				int[][] macierz2 = Matrix.getMatrix();
			int[][] result = Matrix.mul(macierz2);
			for(int i=0; i<result.length;i++){
				System.out.println("\n");
				for(int j=0;j<result[0].length; j++){
					System.out.print(result[i][j]);
					System.out.print(" ");
				}
				
			}
			}catch (MatrixDimensionsException e) {
			}catch (Exception e) {
			}
		}
	}
}