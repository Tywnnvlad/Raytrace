public abstract class threeDObject {

    public abstract double getT(ParametricLine line);

    public int redValue;
    public int greenValue;
    public int blueValue;

    public void setColor(int redValue, int greenValue, int blueValue){
        this.redValue = redValue;
        this.greenValue = greenValue;
        this.blueValue = blueValue;
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
    public void calculateColor(){

    }
}
