/**
 *  * This class will create a plane threeDObject that includes the (a,b,c) oefficient and the d(offset) of the plane
 */
public class Plane extends threeDObject{
    private double a;
    private double b;
    private double c;
    private double d;
    private double t;

    /**
     * The default constuctor
     * @param a a value for the plane
     * @param b b value for the plane
     * @param c c value for the plane
     */
    public Plane(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
    /**
     * {@inheritdoc}
     */
    public double getT(ParametricLine line){
        d = -100;

        if((a*(line.getDestinationx()-line.getOriginx())+b*(line.getDestinationy()-line.getOriginy())+c*(line.getDestinationz()-line.getOriginz()))==0){
            return -1;
        }
        t = (-(a*line.getOriginx()+b*line.getOriginy()+c*line.getOriginz())+d)/(a*(line.getDestinationx()-line.getOriginx())+b*(line.getDestinationy()-line.getOriginy())+c*(line.getDestinationz()-line.getOriginz()));
        // System.out.println();
        return t;
    }
    /**
     * {@inheritdoc}
     */
    public double[] getNormal(double x1, double y1, double z1){
        double[] normal = new double[3];

        double divisor = Math.sqrt(
            Math.pow(a, 2)+
            Math.pow(b, 2)+
            Math.pow(c, 2)
        );
        normal[0]=a/divisor;
        normal[1]=b/divisor;
        normal[2]=c/divisor;
        return normal;

    }


}
