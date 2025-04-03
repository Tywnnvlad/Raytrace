/**
 * This class represents the direction from two points
 */
public class ParametricLine {


    //EYE IS ORIGIN
    private double originx;
    private double originy;
    private double originz;

    //SCREEN IS DESTINATION
    private double destinationx;
    private double destinationy;
    private double destinationz;

    /**
     * Default constructor of the class
     * @param originx x value of the origin
     * @param originy y value of the origin
     * @param originz z value of the origin
     * @param destinationx x value of the destnation
     * @param destinationy y value of the destination
     * @param destinationz z value of the destination
     */
    public ParametricLine(double originx, double originy, double originz, double destinationx, double destinationy, double destinationz){
        this.originx = originx ;
        this.originy = originy ;
        this.originz = originz ;
        this.destinationx = destinationx;
        this.destinationy = destinationy;
        this.destinationz = destinationz;
    }
    /**
     * A method to get the x value of the intersection point
     * @param t the T value of the intersection point
     * @return will return the x value of the intersection
     */
    public double xfromt(double t){
        double x = originx+((destinationx-originx)*t);
        return x;
    }
     /**
     * A method to get the y value of the intersection point
     * @param t the T value of the intersection point
     * @return will return the y value of the intersection
     */
    public double yfromt(double t){
        double y = originy+((destinationy-originy)*t);
        return y;
    }
        /**
     * A method to get the z value of the intersection point
     * @param t the T value of the intersection point
     * @return will return the z value of the intersection
     */
    public double zfromt(double t){
        double z = originz+((destinationz-originz)*t);
        return z;
    }
    /**
     * A getter method to get originx
     * @return return originx
     */
    public double getOriginx(){
        return originx;
    }
    /**
     * A getter method to get originy
     * @return return originy
     */
    public double getOriginy(){
        return originy;
    }
    /**
     * A getter method to get origin
     * @return return originz
     */
    public double getOriginz(){
        return originz;
    }
    /**
     * A getter method to get destinationx
     * @return return destinationx
     */
    public double getDestinationx(){
        return destinationx;
    }
    /**
     * A getter method to get destinationy
     * @return return destinationy
     */
    public double getDestinationy(){
        return destinationy;
    }
    /**
     * A getter method to get destinationz
     * @return return destinationz
     */
    public double getDestinationz(){
        return destinationz;
    }
}
