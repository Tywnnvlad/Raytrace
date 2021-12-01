public class ParametricLine {


    //EYE IS ORIGIN
    private double originx;
    private double originy;
    private double originz;

    //SCREEN IS DESTINATION
    private double destinationx;
    private double destinationy;
    private double destinationz;

    
    public ParametricLine(double originx, double originy, double originz, double destinationx, double destinationy, double destinationz){
        this.originx = originx ;
        this.originy = originy ;
        this.originx = originz ;
        this.destinationx = destinationx;
        this.destinationy = destinationy;
        this.destinationz = destinationz;
    }
    public double xfromt(double t){
        double x = originx+(destinationx-originx)*t;
        return x;
    }
    public double yfromt(double t){
        double y = originy+(destinationy-originy)*t;
        return y;
    }
    public double zfromt(double t){
        double z = originz+(destinationz-originz)*t;
        return z;
    }
    public double getOriginx(){
        return originx;
    }
    public double getOriginy(){
        return originy;
    }
    public double getOriginz(){
        return originz;
    }
    public double getDestinationx(){
        return destinationx;
    }
    public double getDestinationy(){
        return destinationy;
    }
    public double getDestinationz(){
        return destinationz;
    }
}
