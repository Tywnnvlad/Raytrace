import java.awt.Color;

/**
 * This class will create a Sphere threeDObject that includes a radius and the position of the sphere(x,y,z)
 */
public class Sphere extends threeDObject {

  private double x;
  private double y;
  private double z;
  private double radius;


  public Sphere(double x, double y, double z, double radius) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.radius = radius;
  }

  /**
   * 
   * @param line A parametric line where the ray is shot
   * @return will return a double value that is needed to get T
   */
  public double getA(ParametricLine line) {
    double A = Math.pow(line.getDestinationx(), 2) - 2 * (line.getOriginx() * line.getDestinationx())
        + Math.pow(line.getOriginx(), 2) +
        Math.pow(line.getDestinationy(), 2) - 2 * (line.getOriginy() * line.getDestinationy())
        + Math.pow(line.getOriginy(), 2) +
        Math.pow(line.getDestinationz(), 2) - 2 * (line.getOriginz() * line.getDestinationz())
        + Math.pow(line.getOriginz(), 2);
    return A;
  }

  /**
   * 
   * @param line A parametric line where the ray is shot
   * @return will return a double value that is needed to get T
   */
  public double getB(ParametricLine line) {
    double B = 2 * (line.getOriginx() * line.getDestinationx()) - 2 * (line.getDestinationx() * x)
        - 2 * Math.pow(line.getOriginx(), 2) + 2 * (line.getOriginx() * x) +
        2 * (line.getOriginy() * line.getDestinationy()) - 2 * (line.getDestinationy() * y)
        - 2 * Math.pow(line.getOriginy(), 2) + 2 * (line.getOriginy() * y) +
        2 * (line.getOriginz() * line.getDestinationz()) - 2 * (line.getDestinationz() * z)
        - 2 * Math.pow(line.getOriginz(), 2) + 2 * (line.getOriginz() * z);
    return B;
  }
  /**
   * 
   * @param line A parametric line where the ray is shot
   * @return will return a double value that is needed to get T
   */
  public double getC(ParametricLine line) {
    double C = Math.pow(line.getOriginx(), 2) - 2 * (line.getOriginx() * x) + Math.pow(x, 2) +
        Math.pow(line.getOriginy(), 2) - 2 * (line.getOriginy() * y) + Math.pow(y, 2) +
        Math.pow(line.getOriginz(), 2) - 2 * (line.getOriginz() * z) + Math.pow(z, 2) - Math.pow(radius, 2);
    return C;
  }
  

  /**
   * {@inheritdoc}
   */
  public double getT(ParametricLine line) {
  
    double A = getA(line);
    double B = getB(line);
    double C = getC(line);
    double discriminant = Math.pow(B, 2) - 4 * (A * C);

    if (discriminant < 0) {
      return -1;
    }

    double t1 = (-B + Math.sqrt(discriminant)) / (2 * A);
    double t2 = (-B - Math.sqrt(discriminant)) / (2 * A);

    if (t1 > 0 && t2 < 0) {
      // t=t1;
      return t1;
    } else if (t2 > 0 && t1 < 0) {
      // t=t2;
      return t2;
    } else if (t1 > t2) {
      // t=t2;
      return t2;
    } else {
      // t=t1;
      return t1;
    }
  }
  /**This method will calculate the the normalized normal of the sphere
   * @param x1 the x value of the vector
   * @param y1 the y value of the vector
   * @param z1 the z value of the vector
   * @return will return an array of double with the normalized vector [x1,y1,z1]
   */
  public double[] getNormal(double x1, double y1, double z1) {
    double[] normal = new double[3];
    double divisor = Math.sqrt(
        Math.pow((x1 - x), 2) +
            Math.pow((y1 - y), 2) +
            Math.pow((z1 - z), 2));
    normal[0] = (x1 - x) / divisor;
    normal[1] = (y1 - y) / divisor;
    normal[2] = (z1 - z) / divisor;

    return normal;
  }

}
