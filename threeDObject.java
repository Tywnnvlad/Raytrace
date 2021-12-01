import java.awt.Color;

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

    public Color calculateColor(double xlight, double ylight, double zlight, double x1, double y1, double z1){
        double[] normal = getNormal(x1, y1, z1);
        double[] light = new double[3];
        double divisor = Math.sqrt(
            Math.pow(xlight, 2)+
            Math.pow(ylight, 2)+
            Math.pow(zlight, 2)
        );
        light[0]=xlight/divisor;
        light[1]=ylight/divisor;
        light[2]=zlight/divisor;
        double diffuseintensity = Math.max(0,dotProduction(normal, light));
        double diffuse = diffuseintensity*diffusecoef;
        Color rgb = new Color((int)(redValue*(diffuse+ambient)),(int)(greenValue*(diffuse+ambient)),(int)(blueValue*(diffuse+ambient)));

    
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
