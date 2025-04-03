// CMPT 315
// Fall, 2021
// Assignment 4
// Gregory Cal
// Instructor: Michael Janzen
//
// This

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

/**
 * This class will produce a raytraced sphere with a plane environment
 * 
 * @author Gregory Cal
 */
// Create an initial image in a frame
public class RayTrace extends JComponent {
	public BufferedImage image;

	// Construct the frame and make it exit when the x button is clicked
	//
	public static void main(String[] args) {
		JFrame f = new JFrame("Raytracing Engine");

		// Make the window closed when the x button is clicked
		// This makes a new instance that overrides the windowClosing function
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		RayTrace rayTrace = new RayTrace();

		f.add(rayTrace); // put the panel with the image in the frame
		f.pack(); // layout the frame
		f.setVisible(true); // show the frame
	}

	// Constructor
	// Create an image with an initial colouring to demonstrate how to write colours
	// to the image
	public RayTrace() {
		try {
			// Size of image is 500 by 500 pixels
			int width = 500;
			int height = 500;
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			double eyex = 0.0;// eyex
			double eyey = 0.0;// eyey
			double eyez = 0.0;// eyez

			double screenz = 1000.0;// screenz

			double xlight = -600;
			double ylight = 1000;
			double zlight = 0;
			// Red sphere
			double xRed = -100.0;// x-axis
			double yRed = 50.0; // y-axis
			double zRed = screenz - 80.0;// z-axis
			double rRed = 50.0; // radius
			// Blue sphere
			double xBlue = 0.0; // x-axis
			double yBlue = 0.0;// y-axis
			double zBlue = screenz + 50.0;// z-axis
			double rBlue = 70; // radius
			// Plane
			double planea = 0;
			double planeb = 1;
			double planec = 0;

			double diffusecoef = 0.7;
			double ambient = 0.3;
			/// List of all the objectss
			List<threeDObject> conveyerBelt = new ArrayList<threeDObject>();

			// Background Color
			Color bgColor = new Color(222, 191, 120);

			// Red sphere
			conveyerBelt.add(new Sphere(xRed, yRed, zRed, rRed));
			conveyerBelt.get(0).setColor(255, 0, 0, diffusecoef, ambient);

			// Blue sphere
			conveyerBelt.add(new Sphere(xBlue, yBlue, zBlue, rBlue));
			conveyerBelt.get(1).setColor(0, 0, 255, diffusecoef, ambient);
			///

			// Infinite plane
			conveyerBelt.add(new Plane(planea, planeb, planec));
			conveyerBelt.get(2).setColor(137, 207, 240, diffusecoef, ambient);

			// This double loop iterates through every pixel in the image
			for (int i = 0; i < image.getWidth(); i++) {

				for (int j = 0; j < image.getHeight(); j++) {

					ParametricLine paraline = new ParametricLine(eyex, eyey, eyez, i - width / 2, height / 2 - j,
							screenz);

					double primeTime = -1;
					threeDObject primeObject = null;

					for (int k = 0; k < conveyerBelt.size(); k++) {

						if (conveyerBelt.get(k).getT(paraline) >= 0) {

							if (primeTime == -1 || conveyerBelt.get(k).getT(paraline) < primeTime) {

								primeTime = conveyerBelt.get(k).getT(paraline);
								primeObject = conveyerBelt.get(k);
							}
						}
					}

					if (primeObject == null) {

						image.setRGB(i, j, bgColor.getRGB());
					} else {

						Color objectColor = primeObject.calculateColor(xlight, ylight, zlight,
								paraline.xfromt(primeTime), paraline.yfromt(primeTime), paraline.zfromt(primeTime),
								conveyerBelt);
						image.setRGB(i, j, objectColor.getRGB());
					}

				}
			}
			repaint();

		} catch (Exception e) // Generic Exception handler with information on what happened
		{
			System.out.println("There was a problem creating the image\n" + e);
		}
	}

	// Return the size this component should be - usually the size of the image,
	// or 100 x 100 if the image hasn't been loaded for some reason
	public Dimension getPreferredSize() {
		if (image == null)
			return new Dimension(100, 100);
		else
			return new Dimension(image.getWidth(null), image.getHeight(null));
	}

	// When redrawing just paint the image on the component
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}

	// Some functions for getting the alpha, red, green, or blue values from an
	// integer that
	// represents a colour
	public static int getAlpha(int pixelColour) {
		return (0xFF000000 & pixelColour) >>> 24;
	}

	public static int getRed(int pixelColour) {
		return (0x00FF0000 & pixelColour) >>> 16;
	}

	public static int getGreen(int pixelColour) {
		return (0x0000FF00 & pixelColour) >>> 8;
	}

	public static int getBlue(int pixelColour) {
		return (0x000000FF & pixelColour);
	}

	// Given the red, green, and blue values make the colour as an integer assuming
	// pixel is opaque
	public static int makeColour(int red, int green, int blue) {
		return (255 << 24 | red << 16 | green << 8 | blue);
	}

	// public double getA(double x0, double y0, double z0, double x1, double y1,
	// double z1, double xc, double yc, double zc, double r){
	// double A = Math.pow(x1, 2) - 2*(x0*x1) + Math.pow(x0, 2) + Math.pow(y1, 2) -
	// 2*(y0*y1) + Math.pow(y0, 2) + Math.pow(z1, 2) - 2*(z0*z1) + Math.pow(z0, 2);
	// return A;
	// }

	// public double getB(double x0, double y0, double z0, double x1, double y1,
	// double z1, double xc, double yc, double zc, double r){
	// double B = 2*(x0*x1) - 2*(x1*xc) - Math.pow(x0, 2) + 2*(x0*xc) + 2*(y0*y1) -
	// 2*(y1*yc) - 2*Math.pow(y0, 2) + 2*(y0*yc) + 2*(z0*z1) - 2*(z1*zc) -
	// 2*Math.pow(z0, 2) - 2*(z0*zc);
	// return B;
	// }

	// public double getC(double x0, double y0, double z0, double x1, double y1,
	// double z1, double xc, double yc, double zc, double r){
	// double C = Math.pow(x0, 2) - 2*(x0*xc) + Math.pow(xc, 2) + Math.pow(y0, 2) -
	// 2*(y0*y1) + Math.pow(yc, 2) + Math.pow(z0, 2) - 2*(z0*zc) +Math.pow(zc, 2) -
	// Math.pow(r, 2);
	// return C;
	// }

	// public double getT(double x0, double y0, double z0, double x1, double y1,
	// double z1, double xc, double yc, double zc, double r){
	// double A = getA(x0, y0, z0, x1, y1, z1, xc, yc, zc, r);
	// double B = getB(x0, y0, z0, x1, y1, z1, xc, yc, zc, r);
	// double C = getC(x0, y0, z0, x1, y1, z1, xc, yc, zc, r);
	// double discriminant = Math.pow(B, 2) - 4*(A*C);

	// if(discriminant < 0 ){
	// return -1;
	// }

	// double t1 = (-B+Math.sqrt(discriminant))/2*A;
	// double t2 = (-B-Math.sqrt(discriminant))/2*A;

	// if(t1 > 0 && t2 < 0){
	// return t1;
	// }
	// else if(t2 > 0 && t1< 0){
	// return t2;
	// }
	// else if(t1>t2){
	// return t2;
	// }
	// else return t1;
	// }
}
