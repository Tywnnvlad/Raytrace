public class Plane extends threeDObject{
    private double a;
    private double b;
    private double c;
    private double d;
    private double t;

    public Plane(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getT(ParametricLine line){
        d = -100;

        if((a*(line.getDestinationx()-line.getOriginx())+b*(line.getDestinationy()-line.getOriginy())+c*(line.getDestinationz()-line.getOriginz()))==0){
            return -1;
        }
        t = (-(a*line.getOriginx()+b*line.getOriginy()+c*line.getOriginz())+d)/(a*(line.getDestinationx()-line.getOriginx())+b*(line.getDestinationy()-line.getOriginy())+c*(line.getDestinationz()-line.getOriginz()));
        // System.out.println();
        return t;
    }
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

    // public void printT(){
    //     // System.out.println("Plane: "+t);
    // }
}
