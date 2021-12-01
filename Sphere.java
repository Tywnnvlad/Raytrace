public class Sphere extends threeDObject{

    private double x;
    private double y;
    private double z;
    private double radius;

    // public int redValue;
    // public int greenValue;
    // public int blueValue;

    
    private double ambient;//IA
    private double diffuse;//KD

    
    public Sphere(double x, double y, double z, double radius){
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = radius;

        ambient = 0.9;
        diffuse = 0.1;
    }
   
    // public void setEye(double line.getOriginx(), double line.getOriginy(), double line.getOriginz()){
    //     this.line.getOriginx() = line.getOriginx();
    //     this.line.getOriginy() = line.getOriginy();
    //     this.line.getOriginz() = line.getOriginz();
    // }
    // public void setScreen(double line.getDestinationx(), double line.getDestinationy(), double line.getDestinationz()){
    //     this.line.getDestinationx() = line.getDestinationx();
    //     this.line.getDestinationy() = line.getDestinationy();
    //     this.line.getDestinationz() = line.getDestinationz();
    // }

    // public void setxscreen(double line.getDestinationx()){
    //     this.line.getDestinationx() = line.getDestinationx();
    // }
    // public void setyscreen(double line.getDestinationy()){
    //     this.line.getDestinationy() = line.getDestinationy();
    // }
    // public void setzscreen(double line.getDestinationz()){
    //     this.line.getDestinationz() = line.getDestinationz();
    // }

    // public double getA(double line.getOriginx(), double line.getOriginy(), double line.getOriginz(), double line.getDestinationx(), double line.getDestinationy(), double line.getDestinationz(), double x, double y, double z, double radius){
    public double getA(ParametricLine line){
    double A = Math.pow(line.getDestinationx(), 2) - 2*(line.getOriginx()*line.getDestinationx()) + Math.pow(line.getOriginx(), 2) + Math.pow(line.getDestinationy(), 2) - 2*(line.getOriginy()*line.getDestinationy()) + Math.pow(line.getOriginy(), 2) + Math.pow(line.getDestinationz(), 2) - 2*(line.getOriginz()*line.getDestinationz()) + Math.pow(line.getOriginz(), 2);
		return A;
	}
    // public double getB(double line.getOriginx(), double line.getOriginy(), double line.getOriginz(), double line.getDestinationx(), double line.getDestinationy(), double line.getDestinationz(), double x, double y, double z, double radius){
    public double getB(ParametricLine line){
		double B = 2*(line.getOriginx()*line.getDestinationx()) - 2*(line.getDestinationx()*x) - Math.pow(line.getOriginx(), 2) + 2*(line.getOriginx()*x) + 2*(line.getOriginy()*line.getDestinationy()) - 2*(line.getDestinationy()*y) - 2*Math.pow(line.getOriginy(), 2) + 2*(line.getOriginy()*y) + 2*(line.getOriginz()*line.getDestinationz()) - 2*(line.getDestinationz()*z) - 2*Math.pow(line.getOriginz(), 2) - 2*(line.getOriginz()*z);
		return B;
	}
    // public double getC(double line.getOriginx(), double line.getOriginy(), double line.getOriginz(), double line.getDestinationx(), double line.getDestinationy(), double line.getDestinationz(), double x, double y, double z, double radius){
    public double getC(ParametricLine line){
		double C = Math.pow(line.getOriginx(), 2) - 2*(line.getOriginx()*x) + Math.pow(x, 2) + Math.pow(line.getOriginy(), 2) - 2*(line.getOriginy()*line.getDestinationy()) + Math.pow(y, 2) + Math.pow(line.getOriginz(), 2) - 2*(line.getOriginz()*z) +Math.pow(z, 2) - Math.pow(radius, 2);
		return C;	
	}
    public double getT(ParametricLine line){
    // public double getT(double line.getOriginx(), double line.getOriginy(), double line.getOriginz(), double line.getDestinationx(), double line.getDestinationy(), double line.getDestinationz(), double x, double y, double z, double radius){
		double A = getA(line);
		double B = getB(line);
		double C = getC(line);
		double discriminant = Math.pow(B, 2) - 4*(A*C);

		if(discriminant < 0 ){
			return -1;
		}

		double t1 = (-B+Math.sqrt(discriminant))/2*A;
		double t2 = (-B-Math.sqrt(discriminant))/2*A;

		if(t1 > 0 && t2 < 0){
			return t1;
		}
		else if(t2 > 0 && t1< 0){
			return t2;
		}
		else if(t1>t2){
			return t2;
		}
		else return t1;
	}
}
