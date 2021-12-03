import java.awt.Color;
import java.util.List;

public abstract class threeDObject {

    public abstract double getT(ParametricLine line);

    public int redValue;
    public int greenValue;
    public int blueValue;
    public double diffusecoef;//kd
    public double ambient;
    // public double diffuseintensity;//id

    public void setColor(int redValue, int greenValue, int blueValue,double diffusecoef, double ambient){
        this.redValue = redValue;
        this.greenValue = greenValue;
        this.blueValue = blueValue;
        this.diffusecoef = diffusecoef;
        this.ambient = ambient;
        // this.diffuseintensity = diffuseintensity;
    }

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


        // double divisor = Math.sqrt(
        //     Math.pow((xlight-x1), 2)+
        //     Math.pow((ylight-y1), 2)+
        //     Math.pow((zlight-z1), 2)
        // );
        // light[0]=(xlight-x1)/divisor;
        // light[1]=(ylight-y1)/divisor;
        // light[2]=(zlight-z1)/divisor;

        double diffuseintensity = Math.max(0,dotProduction(normal, light));
        double diffuse = diffuseintensity*diffusecoef;

        
        for (int i=0; i<otherObjects.size(); i++) {
            if(otherObjects.get(i).getT(paraLine)>0 ){
                // System.out.println("WE SHOULD GET SHADOW!");
            // if(otherObjects.get(i).getT(paraLine)>0 && otherObjects.get(i).getT(paraLine)<1){
                thingInTheWay = otherObjects.get(i);
            }
        }

        if (thingInTheWay != null) {
            if (z1 > 1050){
            System.out.println("There is an object: "+thingInTheWay+" T at: "+thingInTheWay.getT(paraLine)+" xyz: "+paraLine.xfromt(thingInTheWay.getT(paraLine))+","+paraLine.yfromt(thingInTheWay.getT(paraLine))+","+paraLine.zfromt(thingInTheWay.getT(paraLine)));
            diffuse = 0;
            }
        }
        // } else {
        //     System.out.println("SHOULD GIVE AMBIENT ONLY");
        //     rgb = new Color((int)(redValue*(ambient)),(int)(greenValue*(ambient)),(int)(blueValue*(ambient)));
        //     System.out.println("Color: "+rgb.getRed()+", "+rgb.getGreen()+", "+rgb.getBlue());
        // }
        rgb = new Color((int)(redValue*(diffuse+ambient)),(int)(greenValue*(diffuse+ambient)),(int)(blueValue*(diffuse+ambient)));

        return rgb;
      }

    public double dotProduction(double[] normal,double[] light){
        double sum = 0;
        for(int i = 0; i < normal.length;i++){
            sum+= normal[i]*light[i];
        }
        return sum;
    }
    public int getredValue(){
        return redValue;
    }
    public int getgreenValue(){
        return greenValue;
    }
    public int getblueValue(){
        return blueValue;
    }
    public abstract double[] getNormal(double x1, double y1, double z1);
        
    
}
