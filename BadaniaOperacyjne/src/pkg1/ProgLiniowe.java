package pkg1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.String;

public class ProgLiniowe {
	private static double[][] readFile(String plik) throws FileNotFoundException{
		double[][] tabPP;
		String[][] tabPPString;
		String[]row;
		String[]row2;
		String[]row3;
		int variablesCount=0;
		File file = new File(plik);
		Scanner scanner = new Scanner(file);
		
		String line = scanner.nextLine();//odczytanie pierwszego wiersza nierownosci
		String lineWithoutVariables = line.replaceAll("x+\\d", "");
		String lineWithoutSigns = lineWithoutVariables.replaceAll("[\\s\\+\\,\\=\\<\\>\\(\\)a-zA-Z]", "");
		row = lineWithoutSigns.split("\\*");
		variablesCount = row.length;
			
		String line2 = scanner.nextLine(); //odczytanie drugiego wiersza nierownosci
		String lineWithoutVariables2 = line2.replaceAll("x+\\d", "");
		String lineWithoutSigns2 = lineWithoutVariables2.replaceAll("[\\s\\+\\,\\=\\<\\>\\(\\)a-zA-Z]", "");
		row2 = lineWithoutSigns2.split("\\*");
			
		String line3 = scanner.nextLine(); //odczytanie funkcji celu
		String lineWithoutVariables3 = line3.replaceAll("x+\\d", "");
		String lineWithoutSigns3 = lineWithoutVariables3.replaceAll("[\\s\\+\\,\\=\\<\\>\\(\\)a-zA-Z]", "");
		String[] row3tmp = lineWithoutSigns3.split("\\*");	
		row3 = new String[row3tmp.length];
		for(int i=0; i<row3tmp.length-1;i++) {
			row3[i]=row3tmp[i];
		}
		row3[row3.length-1]="-1"; //symbolicznie minimalizacja
		tabPPString = new String[3][row.length];
		scanner.close();
		
		for(int i=0; i< row.length; i++)
			tabPPString[0][i]=row[i];
		for(int i=0; i< row.length; i++)
			tabPPString[1][i]=row2[i];
		for(int i=0; i< row.length; i++)
			tabPPString[2][i]=row3[i];
		tabPP = new double[3][variablesCount]; 
		for(int i=0; i<3; i++) {
			for(int j=0; j<variablesCount; j++) {
				tabPP[i][j]=Double.parseDouble(tabPPString[i][j]); //konwersja na double		
			}
		}
		return tabPP;
	}
		
	private static double[][] createPD(double[][] programP) {//stworzenie programu dualnego
		double[][] programD;
		programD = new double[programP[0].length][programP.length];
		for(int i=0; i<programD.length;i++) {
			for(int j=0; j<programD[0].length; j++) {
				if(i==programD.length-1 && j==programD[0].length-1)
					programD[i][j]=1; //symbolicznie maksymalizacja
				else
					programD[i][j]= programP[j][i];
			}
		}
		return programD;
	}
	
	private static double[][] findPoints(double[][] programD) { //znajdowanie punktow przeciec wszystkich prostych
		double[][] points;
		double[] singlePoint;
		int numberOfLines = programD.length+1; //dodatkowo osie OX i OY
		int maxNumberOfPoints = (numberOfLines*(numberOfLines-1))/2; 
		points = new double[maxNumberOfPoints][2];
		int k=0; //k<maxNumberOfPoints
		for(int i=0; i<programD.length-1; i++) { //przeciecia prostych z innymi prostymi
			for(int j=0; j<programD.length-1; j++) {
				if(i<j && k<maxNumberOfPoints) {
					singlePoint=findCrossing(programD[i][0], programD[i][1], programD[i][2], programD[j][0], programD[j][1], programD[j][2]);
					points[k][0]=singlePoint[0];
					points[k][1]=singlePoint[1];
					k++;
				}
			}		
		}
		double[] axeX = {0,1,0};
		double[] axeY = {1,0,0};
		for(int i=0; i<programD.length-1; i++) {//przeciecia prostych z osiami
			if(k<maxNumberOfPoints) {
				singlePoint=findCrossing(programD[i][0], programD[i][1], programD[i][2], axeX[0], axeX[1], axeX[2]);
				points[k][0]=singlePoint[0];
				points[k][1]=singlePoint[1];
				k++;
			}
		}	
		for(int i=0; i<programD.length-1; i++) {
			if(k<maxNumberOfPoints) {
				singlePoint=findCrossing(programD[i][0], programD[i][1], programD[i][2], axeY[0], axeY[1], axeY[2]);
				points[k][0]=singlePoint[0];
				points[k][1]=singlePoint[1];
				k++;
			}
		}
		for(int i=0; i<points.length;i++) {
			for(int j=0; j<points[0].length; j++) {
				if(points[i][j]==-0)
					points[i][j]=0;
			}
		}
		return points;
	}
	
	private static double[] findCrossing (double a1, double b1, double c1, double a2, double b2, double c2) {//znajduje przeciecie dwoch prostych
		double[]point;
		point = new double[2];
		double determinant = a1*b2-a2*b1;
		if(determinant==0) {
			point[0]=Double.MAX_VALUE; //jesli brak przeciecia to Double.MAX_VALUE
			point[1]=Double.MAX_VALUE;
		}
		else {
			double x = (b2*c1-b1*c2)/determinant;
			double y = (a1*c2-a2*c1)/determinant;
			point[0] = x;
			point[1] = y;
		}
		return point;
	}
	
	
	private static double[][] calculateArea(double[][] crossingPoints, double[][] programD) {
		double[][]tmp = new double[crossingPoints.length][2];//odrzucenie duplikatow
		int l=0;
		for(int i=0; i<tmp.length; i++) {
			tmp[i][0]=Double.MAX_VALUE;
			tmp[i][1]=Double.MAX_VALUE;
		}	
		for(int i=0; i<crossingPoints.length; i++) {
			boolean isDuplicate=false;
			for(int j=0; j<tmp.length; j++) {
				if(Math.round(crossingPoints[i][0]*100000)/100000.0==Math.round(tmp[j][0]*100000)/100000.0 && Math.round(crossingPoints[i][1]*100000)/100000.0==Math.round(tmp[j][1]*100000)/100000.0) {
					isDuplicate=true;
					break;
				}	
			}
			if(!isDuplicate) {
				tmp[l][0]=Math.round(crossingPoints[i][0]*100000)/100000.0;
				tmp[l][1]=Math.round(crossingPoints[i][1]*100000)/100000.0;
				l++;
			}
		}
		int numberOfMaxValue=0; //odrzucenie Double.MAX_VALUE
		for(int i=0; i<tmp.length; i++) {
			if(tmp[i][0]==Double.MAX_VALUE || tmp[i][1]==Double.MAX_VALUE) {
				numberOfMaxValue++;
			}
		}
		int rest = tmp.length-numberOfMaxValue;
		double[][] crossingPointsResult = new double[rest][2];
		int k=0; //k<crossingPointsNonMax.length
		for(int i=0; i<tmp.length; i++) {
			if(tmp[i][0]!=Double.MAX_VALUE && tmp[i][1]!=Double.MAX_VALUE) {
				crossingPointsResult[k]=tmp[i];
				k++;
			}
		}	
		double[][] area;
		int numberOfPoints=0;// ilosc punktow ograniczajacych obszar
		for(int i=0; i<crossingPointsResult.length; i++) {
			boolean isInArea=true;
			for(int j=0; j<programD.length-1;j++) {
				double[] coef = new double[2];
				coef[0]=programD[j][0];
				coef[1]=programD[j][1];
				if(calculateValueOfFunction(coef, crossingPointsResult[i])>programD[j][programD[0].length-1]) {
					isInArea=false;
				}
			}
			if(crossingPointsResult[i][0]>=0 && crossingPointsResult[i][1]>=0 && isInArea) {
				numberOfPoints++;
			}
		}
		if(numberOfPoints==0)
			return null;
		area= new double[numberOfPoints][2];
		int n=0; //n<numberOfPoints
		for(int i=0; i<crossingPointsResult.length; i++) {//uzupelniam wyliczonymi punktami area
			boolean isInArea=true;
			for(int j=0; j<programD.length-1;j++) {
				double[] coef = new double[2];
				coef[0]=programD[j][0];
				coef[1]=programD[j][1];
				if(calculateValueOfFunction(coef, crossingPointsResult[i])>programD[j][programD[0].length-1]) {
					isInArea=false;
				}
			}
			if(crossingPointsResult[i][0]>=0 && crossingPointsResult[i][1]>=0 && isInArea) {
				area[n][0]=crossingPointsResult[i][0];
				area[n][1]=crossingPointsResult[i][1];
				n++;
			}	
		}		
		return area;
	}
	
	private static double[]findMaxOfPD(double[][] area, double[][] programD) { //znajduje punkt dla ktorego funkcja celu osiagnie max
		double max;
		int indexOfPoint=0;
		double[] maxTab;
		int indexOfGoal = programD.length-1;//tablica wspolczynnikow f.celu programu dualnego
		double[] coefTab = new double[programD[0].length-1];
		for(int i=0; i<programD[0].length-1; i++) {
			coefTab[i]=programD[indexOfGoal][i];
		}
		max=calculateValueOfFunction(coefTab,area[0]);
		for(int i=0; i<area.length;i++) {
			if(calculateValueOfFunction(coefTab,area[i])>max) {
				max=calculateValueOfFunction(coefTab,area[i]);//znajduje indeks punktu dla ktorego max funkcja celu
				indexOfPoint=i;
			}
		}
		maxTab= new double[2];
		maxTab[0]=area[indexOfPoint][0];
		maxTab[1]=area[indexOfPoint][1];	
		return maxTab;
	}
	
	public static double calculateValueOfFunction(double[] coef, double point[]) {//mnozy wspolczynniki z podanymi punktami x1,x2..
		double result=0;
		for(int i=0; i<coef.length; i++) {
			result+=coef[i]*point[i];
		}
		return Math.round(result*1000)/1000.0;
	}
	
	private static double[] checkZeroVariables(double[] maxValPoint, double[][] programD) {//oblicza ktore zmienne nalezy wyzerowac
		double[]zeroVar = new double[programD.length-1];
		double[][]programDCoef = new double[programD.length-1][programD[0].length-1];
		for(int i=0; i<programD.length-1; i++) {
			for(int j=0; j<programD[0].length-1; j++) {
				programDCoef[i][j]=programD[i][j];
			}
		}
		for(int i=0; i<programDCoef.length; i++) {
			if(calculateValueOfFunction(programDCoef[i],maxValPoint)<programD[i][programD[0].length-1]){
				zeroVar[i]=0;//jesli nierownosc spe³niona ostro
			}
			else {
				zeroVar[i]=1;//jesli nierownosc spe³niona nieostro
			}
		}
	return zeroVar;
	}
	
	private static double[][] calculateResultVal(double[][] programP, double[] zeroVar) {//liczenie wynikowych zmiennych z rownan liniowych
		double[][] point = new double[programP.length-1][programP[0].length-1];
		double[][] pointToCalculate = new double[programP.length -1 ][programP[0].length]; //macierz z kolumna wyrazow wolnych
		for(int i = 0 ; i <pointToCalculate[0].length ; i++){//podstawienie zmiennych
			for(int x = 0; x < pointToCalculate.length; x++){
				if(i <  zeroVar.length){
					if(zeroVar[i] == 0)
						pointToCalculate[x][i] = 0;
					else 
						pointToCalculate[x][i] = programP[x][i];
				}
				else 
					pointToCalculate[x][i] = programP[x][i];
			}
		}
		pointToCalculate = reducedRowEchelonForm(pointToCalculate);//zredukowana macierz schodkowa
		int lastColIndex = pointToCalculate[0].length-1;
		//brak rozwi¹zañ:
		boolean allZeros=true;	
		for(int i = 0; i<lastColIndex; i++) {
			if(pointToCalculate[1][i]!=0)
				allZeros=false;
		}
		if(allZeros==true && pointToCalculate[1][lastColIndex]!=0) {
			return null;
		}
		//jedno rozwi¹zanie:
		boolean restZero=true;
		for(int i=0; i<pointToCalculate.length; i++) {
			for(int j=0; j<lastColIndex ; j++) {
				if(pointToCalculate[i][j] == 1) {
					for(int k=0; k<j ; k++) {
						if(pointToCalculate[i][k]!=0)
							restZero=false;
					}
					for(int l=j+1; l<lastColIndex ; l++) {
						if(pointToCalculate[i][l]!=0)
							restZero=false;
					}
				}
			}
		}
		if(restZero) {
			for(int i=0; i<pointToCalculate.length; i++) {
				for(int j=0; j<lastColIndex ; j++) {
					if(pointToCalculate[i][j]==1) {
						point[0][j]=Math.round(pointToCalculate[i][lastColIndex]*1000)/1000.0;
					}
				}
			}
			point[1][0]=Double.MAX_VALUE; //symbolicznie brak drugiego rozwi¹zania	
			return point;
		}
		
		//nieskoñczenie wiele rozwi¹zañ:
		if(allZeros==true && pointToCalculate[1][lastColIndex]==0) {
			for(int j=0; j<lastColIndex ; j++) {
				if(pointToCalculate[0][j]==1) {
					point[0][j]=pointToCalculate[0][lastColIndex];
				}
				if(pointToCalculate[0][j]!=1 && pointToCalculate[0][j]!=0) {
					point[1][j]=pointToCalculate[0][lastColIndex]/pointToCalculate[0][j];
				} 
			}
			return point;
		}
		//wysteppowanie jedynki i zer w rzedach
		boolean restZeroR1=true;
		boolean restZeroR2=true;
		for(int j=0; j<lastColIndex ; j++) {
			if(pointToCalculate[0][j] == 1) {
				for(int k=0; k<j ; k++) {
					if(pointToCalculate[0][k]!=0)
						restZeroR1=false;
				}
				for(int l=j+1; l<lastColIndex ; l++) {
					if(pointToCalculate[0][l]!=0)
						restZeroR1=false;
				}
			}
		}
		for(int j=0; j<lastColIndex ; j++) {
			if(pointToCalculate[1][j] == 1) {
				for(int k=0; k<j ; k++) {
					if(pointToCalculate[1][k]!=0)
						restZeroR2=false;
				}
				for(int l=j+1; l<lastColIndex ; l++) {
					if(pointToCalculate[1][l]!=0)
						restZeroR2=false;
				}
			}
		}		
		if(restZeroR1 || restZeroR2) {
			double[] point1 = new double[point[0].length];
			double[] point2 = new double[point[0].length];
			double[][] pointToCalculateTmp= new double[pointToCalculate.length][pointToCalculate[0].length];
			for(int i = 0; i<pointToCalculate.length; i++){
				for(int j = 0; j < pointToCalculate[0].length ; j++){
					pointToCalculateTmp[i][j]=pointToCalculate[i][j];
				}
			}
			for(int i = 0; i<pointToCalculate.length; i++){
				for(int j = 0; j < lastColIndex ; j++){
					if(pointToCalculate[i][j] == 1){
						pointToCalculateTmp[i][j] = pointToCalculate[i][lastColIndex];
						for(int j2 = 0; j2 < lastColIndex - 1; j2++){
							if(j2 != j)
								pointToCalculateTmp[i][j2] = 0;
						}
					}
				}
			}
			for(int j = 0; j < lastColIndex ; j++){
				if(pointToCalculateTmp[0][j]==0 && pointToCalculateTmp[1][j]==0)
					point1[j]=0;
				else if(pointToCalculateTmp[0][j]==0 && pointToCalculateTmp[1][j]!=0)
					point1[j]=pointToCalculateTmp[1][j];
				else if(pointToCalculateTmp[0][j]!=0 && pointToCalculateTmp[1][j]==0)
					point1[j]=pointToCalculateTmp[0][j];
			}
			//inny punkt
			for(int i = 0; i<pointToCalculate.length; i++){
				for(int j = 0; j < pointToCalculate[0].length ; j++){
					pointToCalculateTmp[i][j]=pointToCalculate[i][j];
				}
			}
			for(int j = 0; j < lastColIndex ; j++){
				if(pointToCalculate[0][j] == 1 && restZeroR1){
					pointToCalculateTmp[0][j] = pointToCalculate[0][lastColIndex];
					for(int j2 = 0; j2 < lastColIndex - 1; j2++){
						if(j2 != j)
							pointToCalculateTmp[0][j2] = 0;
					}	
				}
				else {
					if(pointToCalculate[0][j]!=1 && pointToCalculate[0][j]!=0) {
						pointToCalculateTmp[0][j]=pointToCalculate[0][lastColIndex]/pointToCalculate[0][j];
						for(int j2 = 0; j2 < lastColIndex - 1; j2++){
							if(j2 != j)
								pointToCalculateTmp[0][j2] = 0;
						}
					}
				}
			}
			for(int j = 0; j < lastColIndex ; j++){
				if(pointToCalculate[1][j] == 1 && restZeroR2){
					pointToCalculateTmp[1][j] = pointToCalculate[1][lastColIndex];
					for(int j2 = 0; j2 < lastColIndex - 1; j2++){
						if(j2 != j)
							pointToCalculateTmp[1][j2] = 0;
					}	
				}
				else{
					if(pointToCalculate[1][j]!=1 && pointToCalculate[1][j]!=0) {
						pointToCalculateTmp[1][j]=pointToCalculate[1][lastColIndex]/pointToCalculate[1][j];
						for(int j2 = 0; j2 < lastColIndex - 1; j2++){
							if(j2 != j)
								pointToCalculateTmp[1][j2] = 0;
						}
					}
				}
			}
			for(int j = 0; j < lastColIndex ; j++){
				if(pointToCalculateTmp[0][j]==0 && pointToCalculateTmp[1][j]==0)
					point2[j]=0;
				else if(pointToCalculateTmp[0][j]==0 && pointToCalculateTmp[1][j]!=0)
					point2[j]=pointToCalculateTmp[1][j];
				else if(pointToCalculateTmp[0][j]!=0 && pointToCalculateTmp[1][j]==0)
					point2[j]=pointToCalculateTmp[0][j];
			}
			
			point[0]=point1;
			point[1]=point2;
			}
		else {
			point[0][0]=Double.MAX_VALUE;
		}
		return point;
	}
	
	private static double [][] reducedRowEchelonForm(double[][] matrix){
		int lead = 0;
		int rowCount = matrix.length;
		if(rowCount == 0)
			return null;
		int columnCount = matrix[0].length;
		for (int r = 0; r < rowCount; r++) {
	        if (lead >= columnCount)
	            break; 
	        int i = r;
	        while (matrix[i][lead] == 0) {
	           i++;
	           if (i == rowCount) {
	               i = r;
	               lead++;
	               if (lead == columnCount)
	            	   return matrix;
	           }
	        }
	        double[] temp = matrix[r];
	        matrix[r] = matrix[i];
	        matrix[i] = temp;
	        double lv = matrix[r][lead];
	        for (int j = 0; j < columnCount; j++) {
	                matrix[r][j] = matrix[r][j] / lv;
	        } 
	        for (i = 0; i < rowCount; i++) {
	            if (i != r) {
	                lv = matrix[i][lead];
	                for (int j = 0; j < columnCount; j++) {
	                    matrix[i][j] -= lv * matrix[r][j];
	                }
	            }
	        }
	        lead++;
	    }
		return matrix;
	}
	
	private static double calculateGoalFunction(double[][] resultValues, double[][] programP) {//oblicz wartosc funkcji dla danego punktu
		double[] goalCoef = new double [programP[0].length-1]; //tylko wspolczynniki f.celu
		for(int i=0; i<goalCoef.length;i++) {
			goalCoef[i]=programP[programP.length-1][i];
		}
		double result = calculateValueOfFunction(goalCoef,resultValues[0]);
		return result;
	}

	
	public static void main(String[] args) throws FileNotFoundException{
		double[][] programP = readFile("plik1.txt");
		double[][] programD = createPD(programP);		
		double[][] crossingPoints = findPoints(programD);
		double[][] area = calculateArea(crossingPoints, programD);
		if(area==null) {
			System.out.println("Zadanie nie ma rozwi¹zania");
		}
		else {
			
			System.out.println("Lista punktów ograniczaj¹cych obszar programu dualnego: ");
			System.out.print("[");
			for(int i=0; i<area.length;i++) {
				System.out.print("(");
				for(int j=0; j<area[0].length; j++) {
					System.out.print(area[i][j]);
					if(j==0)
						System.out.print(",");
				}
				System.out.print(")");
				if(i!=area.length-1)
					System.out.print(", ");
			}
			System.out.println("]");
			double[] maxValPoint = findMaxOfPD(area,programD);
			double[] zeroVar = checkZeroVariables(maxValPoint,programD);
			double[][] resultValues = calculateResultVal(programP,zeroVar);
			if(resultValues==null) {
				System.out.println("Nie ma takiego punktu, który spe³nia³by podane warunki");
			}
			else if(resultValues[0][0]==Double.MAX_VALUE) {
				System.out.println("Nieskoñczona liczba punktów realizuj¹cych optimum, zale¿nych od wiêcej ni¿ dwóch zmiennych");
			}
			else if(resultValues[1][0]!=Double.MAX_VALUE) {
				System.out.println("Nieskoñczona liczba punktów realizuj¹cych optimum w przykladowych punktach: ");
				for(int i=0; i<resultValues.length;i++) {
					System.out.print("W"+(i+1)+": ");
					for(int j=0; j<resultValues[0].length; j++) {
						System.out.print("x"+(j+1)+"="+resultValues[i][j]+ " ");
					}
					System.out.println("");
				}
				double resultGoalFunction = calculateGoalFunction(resultValues, programP); 
				System.out.println("Minimalna wartoœæ funkcji celu: G(W)=" + resultGoalFunction);
			}
			else {
				System.out.println("Punkt realizuj¹cy optimum:" );
				System.out.print("W: ");
				for(int j=0; j<resultValues[0].length; j++) {
					System.out.print("x"+(j+1)+"="+resultValues[0][j]+ ", ");
				}
				System.out.println("");
				double resultGoalFunction = calculateGoalFunction(resultValues, programP); 
				System.out.println("Minimalna wartoœæ funkcji celu: G(W)=" + resultGoalFunction);
			}
		}
	}


	

	


	


	
}
