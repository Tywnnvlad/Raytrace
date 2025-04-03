import java.awt.Color;
import java.util.List;

/**
 * An abstract class of a threeDObject that represents a 3D object in a space
 * 
 */
public abstract class threeDObject {


    public int redValue;
    public int greenValue;
    public int blueValue;
    public double diffusecoef;//kd
    public double ambient;
    /**
     * This method will return a t value that will represent if there is an intersection from the source to the destination 
     * t>0 there is an intersection. t < 1 the intersection is beyond the source
     * @param line A parametric line where the ray is shot 
     * @return will return a double value that will represent if there is an intersection between the ray and the destination. 
     */
    public abstract double getT(ParametricLine line);

    /**
     * A method to set the color of the threeDObject
     * @param redValue int value that represents red color
     * @param greenValue int value that represents green color
     * @param blueValue int value that represents blue color
     * @param diffusecoef the diffuse coefficient 
     * @param ambient   the ambient setting for the object
     */
    public void setColor(int redValue, int greenValue, int blueValue,double diffusecoef, double ambient){
        this.redValue = redValue;
        this.greenValue = greenValue;
        this.blueValue = blueValue;
        this.diffusecoef = diffusecoef;
        this.ambient = ambient;
    }
    /**
     * This method will calculate the color value if it is has been directed by the light or blocked by an object
     * @param xlight xposition where the light is
     * @param ylight yposition where the light is
     * @param zlight zposition where the light is
     * @param x1     xvalue of the position where the intersection is
     * @param y1     yvalue of the position where the intersection is
     * @param z1     zvalue of the position where the intersection is
     * @param otherObjects the list of all objects in the space
     * @return return a color that represents the calculated value from the light and objects around the intersection point
     */
    public Color calculateColor(double xlight, double ylight, double zlight, double x1, double y1, double z1, List<threeDObject> otherObjects){
        double[] normal = getNormal(x1, y1, z1);
        double[] light = new double[3];
        Color rgb;
        threeDObject thingInTheWay = null;
        ParametricLine paraLine = new ParametricLine(x1, y1, z1, xlight, ylight, zlight);

        double divisor = Math.sqrt(
            Math.pow((xlight-x1), 2)+
            Math.pow((ylight-y1), 2)+
            Math.pow((zlight-z1), 2)
        );
        light[0]=(xlight-x1)/divisor;
        light[1]=(ylight-y1)/divisor;
        light[2]=(zlight-z1)/divisor;

        double diffuseintensity = Math.max(0,dotProduction(normal, light));
        double diffuse = diffuseintensity*diffusecoef;

        
        for (int i=0; i<otherObjects.size(); i++) {
            if(otherObjects.get(i).getT(paraLine)>0.001 ){
                // System.out.println("WE SHOULD GET SHADOW!");
            // if(otherObjects.get(i).getT(paraLine)>0 && otherObjects.get(i).getT(paraLine)<1){
                thingInTheWay = otherObjects.get(i);
            }
        }

        if (thingInTheWay != null) {
            // if (z1 > 1050){
            // System.out.println("There is an object: "+thingInTheWay+" T at: "+thingInTheWay.getT(paraLine)+" xyz: "+paraLine.xfromt(thingInTheWay.getT(paraLine))+","+paraLine.yfromt(thingInTheWay.getT(paraLine))+","+paraLine.zfromt(thingInTheWay.getT(paraLine)));
            diffuse = 0;
            
        }
        // } else {
        //     System.out.println("SHOULD GIVE AMBIENT ONLY");
        //     rgb = new Color((int)(redValue*(ambient)),(int)(greenValue*(ambient)),(int)(blueValue*(ambient)));
        //     System.out.println("Color: "+rgb.getRed()+", "+rgb.getGreen()+", "+rgb.getBlue());
        // }
        rgb = new Color((int)(redValue*(diffuse+ambient)),(int)(greenValue*(diffuse+ambient)),(int)(blueValue*(diffuse+ambient)));

        return rgb;
      }
    /**
     * Will perform a dot product of two vectors
     * @param normal first vector
     * @param light second vector
     * @return will return the normalized vector
     */
    public double dotProduction(double[] normal,double[] light){
        double sum = 0;
        for(int i = 0; i < normal.length;i++){
            sum+= normal[i]*light[i];
        }
        return sum;
    }
    /**
     * Method to return the red color value of the object
     * @return red color
     */
    public int getredValue(){
        return redValue;
    }
    /**
     * Method to return the green color value of the object
     * @return green color
     */
    public int getgreenValue(){
        return greenValue;
    }
    /**
     * Method to return the blue color value of the object
     * @return blue color
     */
    public int getblueValue(){
        return blueValue;
    }
    /**
     * A method that will return a normalized vector of an intersection
     * @param x1 xvalue of the intersection
     * @param y1 yvalue of the intersection
     * @param z1 zvalue of the intersection
     * @return   return an array of double that represents a normalized vector
     */
    public abstract double[] getNormal(double x1, double y1, double z1);
        
    
}
