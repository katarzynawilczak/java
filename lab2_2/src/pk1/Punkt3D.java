package pk1;

public class Punkt3D extends Punkt2D{
	
	double z;
	
	Punkt3D(double x_, double y_, double z_){
		super(x_,y_);
		z=z_;
	}
	
	public void SetZ(double z_) {
		z=z_;
	}
	
	public double GetZ(){
		return z;
	}
	
	public double distance(Punkt3D punkt) {
		 return Math.sqrt(Math.pow(GetX() - punkt.GetX(), 2) + Math.pow(GetY() - punkt.GetY(), 2) + Math.pow(GetZ() - punkt.GetZ(), 2));
	}
	
	
	

}
