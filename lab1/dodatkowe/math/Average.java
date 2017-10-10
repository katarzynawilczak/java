package math;
/**
 * Klasa licząca średnia ocen podanych jako parametr
 * 
 */
public class Average {
    /**
     * Metoda oblicza średnia ocen
     * @param grades lista ocen jednostkowych
     * @return średnia ocen podanych jako parametr
     */
    public double calculate(double[] grades){
        double sum = 0;
        for(int i = 0; i < grades.length; i++){
            sum += grades[i];
        }
        return sum/grades.length;
    }
}