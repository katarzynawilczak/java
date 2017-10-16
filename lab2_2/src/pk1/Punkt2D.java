package pk1;

public class Punkt2D {
	double x;
	double y;
	public Punkt2D(double x_, double y_) {
		x=x_;
		y=y_;
	}
	
	public double GetX() {
		return x;
	}
	
	public double GetY() {
		return y;
	}
	
	public void SetX(double x_) {
		x=x_;
	}
	
	public void SetY(double y_) {
		y=y_;
	}
	
	public double distance(Punkt2D punkt) {
		return Math.sqrt(Math.pow(GetX() - punkt.GetX(), 2) + Math.pow(GetY() - punkt.GetY(), 2));
	}
	
}
