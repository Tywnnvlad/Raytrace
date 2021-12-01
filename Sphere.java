import java.awt.Color;
public class Sphere extends threeDObject{

    private double x;
    private double y;
    private double z;
    private double radius;
    private double t1;
    private double t2;

    
    private double ambient;//IA
    private double kd;//KD

    
    public Sphere(double x, double y, double z, double radius){
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = radius;
    }
   


    // public double getA(double line.getOriginx(), double line.getOriginy(), double line.getOriginz(), double line.getDestinationx(), double line.getDestinationy(), double line.getDestinationz(), double x, double y, double z, double radius){
    public double getA(ParametricLine line){
    double A =
    Math.pow(line.getDestinationx(), 2) - 2*(line.getOriginx()*line.getDestinationx()) + Math.pow(line.getOriginx(), 2) + 
    Math.pow(line.getDestinationy(), 2) - 2*(line.getOriginy()*line.getDestinationy()) + Math.pow(line.getOriginy(), 2) + 
    Math.pow(line.getDestinationz(), 2) - 2*(line.getOriginz()*line.getDestinationz()) + Math.pow(line.getOriginz(), 2);
		return A;
	}
    // public double getB(double line.getOriginx(), double line.getOriginy(), double line.getOriginz(), double line.getDestinationx(), double line.getDestinationy(), double line.getDestinationz(), double x, double y, double z, double radius){
    public double getB(ParametricLine line){
		double B = 
    2*(line.getOriginx()*line.getDestinationx()) - 2*(line.getDestinationx()*x) - 2*Math.pow(line.getOriginx(), 2) + 2*(line.getOriginx()*x) + 
    2*(line.getOriginy()*line.getDestinationy()) - 2*(line.getDestinationy()*y) - 2*Math.pow(line.getOriginy(), 2) + 2*(line.getOriginy()*y) + 
    2*(line.getOriginz()*line.getDestinationz()) - 2*(line.getDestinationz()*z) - 2*Math.pow(line.getOriginz(), 2) - 2*(line.getOriginz()*z);
		return B;
	}
    // public double getC(double line.getOriginx(), double line.getOriginy(), double line.getOriginz(), double line.getDestinationx(), double line.getDestinationy(), double line.getDestinationz(), double x, double y, double z, double radius){
    public double getC(ParametricLine line){
		double C = 
    Math.pow(line.getOriginx(), 2) - 2*(line.getOriginx()*x) + Math.pow(x, 2) + 
    Math.pow(line.getOriginy(), 2) - 2*(line.getOriginy()*y) + Math.pow(y, 2) + 
    Math.pow(line.getOriginz(), 2) - 2*(line.getOriginz()*z) + Math.pow(z, 2) - Math.pow(radius, 2);
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

		 t1 = (-B+Math.sqrt(discriminant))/(2*A);
		 t2 = (-B-Math.sqrt(discriminant))/(2*A);

		if(t1 > 0 && t2 < 0){
      // t=t1;
			return t1;
		}
		else if(t2 > 0 && t1< 0){
      // t=t2;
			return t2;
		}
		else if(t1>t2){
      // t=t2;
			return t2;
		}
		else{
      // t=t1;
      return t1;
    } 
	}
  public double[] getNormal(double x1, double y1, double z1){
    double[] normal = new double[3];
    double divisor = Math.sqrt(
      Math.pow((x1-x),2)+
      Math.pow((y1-y), 2)+
      Math.pow((z1-z), 2)
    );
    normal[0] = (x1-x)/divisor;
    normal[1] = (y1-y)/divisor;
    normal[2] = (z1-z)/divisor;

    // double x1 = line.xfromt(getT(line);
    // double y1 = line.yfromt(getT(line);
    // double z1 = line.zfromt(getT(line));
    // normal[0] = line.xfromt(getT(line))-x;
    // normal[1] = line.yfromt(getT(line))-y;
    // normal[3] = line.zfromt(getT(line))-z;
    // double divisor = Math.sqrt(normal[1]);
    return normal;
    
  }

  // public void printT(){
  //   System.out.println("T1:"+t1+" T2:"+t2);
  // }
}
