public class Plane extends threeDObject{
    private double a;
    private double b;
    private double c;
    private double d;
    private double t;

    private double ambient;//IA
    private double diffuse;//KD

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
    // public void printT(){
    //     // System.out.println("Plane: "+t);
    // }
}
