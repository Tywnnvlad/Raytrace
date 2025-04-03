# Raytracing Engine

This project is a basic raytracing engine implemented in Java. It demonstrates fundamental raytracing concepts, including:

*   **Object Representation:** Spheres and planes as 3D objects.
*   **Ray-Object Intersection:** Calculating intersections between rays and objects.
*   **Lighting:** Implementing a simple diffuse lighting model with ambient light.
*   **Shadows:** Determining if an object is in shadow.
*   **Color Calculation:** Computing the color of a pixel based on lighting and object properties.

## Project Structure

The project is organized into the following Java classes:

*   **`RayTrace.java`:** The main class that sets up the scene, performs raytracing, and displays the resulting image.
*   **`threeDObject.java`:** An abstract class representing a generic 3D object. It defines common properties and methods for all 3D objects, such as color, diffuse coefficient, ambient light, intersection calculation, and normal vector calculation.
*   **`Sphere.java`:** A concrete class representing a sphere, inheriting from `threeDObject`. It implements sphere-specific intersection and normal vector calculations.
*   **`Plane.java`:** A concrete class representing a plane, inheriting from `threeDObject`. It implements plane-specific intersection and normal vector calculations.
*   **`ParametricLine.java`:** A utility class representing a parametric line, used to define rays. It provides methods for calculating points along the line.

## How it Works

1.  **Scene Setup:**
    *   The `RayTrace` class initializes the scene, including:
        *   The eye (camera) position.
        *   The position of a light source.
        *   A list of 3D objects (`conveyerBelt`) in the scene (spheres and a plane).
        *   Object properties like color, diffuse coefficient, and ambient light.
        *   The background color.
    *   The screen is represented by a grid of pixels.

2.  **Ray Generation:**
    *   For each pixel on the screen, a ray is generated from the eye position through the pixel into the scene. This is done using the `ParametricLine` class.

3.  **Intersection Calculation:**
    *   For each ray, the code iterates through the objects in the scene and checks for intersections using the `getT()` method of each `threeDObject`.
    *   The `getT()` method returns a `t` value, which represents the distance along the ray where the intersection occurs.
    *   If `t > 0`, an intersection exists.
    *   The closest intersection (smallest positive `t` value) is considered the visible object for that pixel.

4.  **Color Calculation:**
    *   If an intersection is found, the `calculateColor()` method of the intersected object is called.
    *   `calculateColor()` determines the color of the pixel based on:
        *   **Diffuse Lighting:** The angle between the light direction and the surface normal at the intersection point.
        *   **Ambient Lighting:** A constant level of light that illuminates all objects.
        *   **Shadows:** A shadow ray is cast from the intersection point to the light source. If this ray intersects another object before reaching the light, the pixel is in shadow.
    *   The `getNormal()` method of the object is used to get the surface normal at the intersection point.
    *   The `dotProduction()` method is used to calculate the dot product between the normal and light vectors.

5.  **Pixel Coloring:**
    *   The calculated color is then set as the RGB value for the corresponding pixel in the image.

6.  **Image Display:**
    *   After processing all pixels, the `RayTrace` class displays the generated image in a window.

## Classes

### `RayTrace`

*   **`main(String[] args)`:** Entry point of the application. Creates the main window and the `RayTrace` object.
*   **`RayTrace()`:** Constructor. Initializes the scene, performs raytracing, and generates the image.
*   **`getPreferredSize()`:** Returns the preferred size of the component (the image size).
*   **`paint(Graphics g)`:** Draws the image on the component.
*   **`getAlpha(int pixelColour)`:** Extracts the alpha value from a pixel color.
*   **`getRed(int pixelColour)`:** Extracts the red value from a pixel color.
*   **`getGreen(int pixelColour)`:** Extracts the green value from a pixel color.
*   **`getBlue(int pixelColour)`:** Extracts the blue value from a pixel color.
*   **`makeColour(int red, int green, int blue)`:** Creates a color integer from red, green, and blue values.

### `threeDObject`

*   **`redValue`, `greenValue`, `blueValue`:** Color components of the object.
*   **`diffusecoef`:** Diffuse reflection coefficient.
*   **`ambient`:** Ambient light coefficient.
*   **`getT(ParametricLine line)`:** Abstract method to calculate the intersection `t` value for a given ray.
*   **`setColor(int redValue, int greenValue, int blueValue, double diffusecoef, double ambient)`:** Sets the color and lighting properties of the object.
*   **`calculateColor(double xlight, double ylight, double zlight, double x1, double y1, double z1, List<threeDObject> otherObjects)`:** Calculates the color of the object at an intersection point.
*   **`dotProduction(double[] normal, double[] light)`:** Calculates the dot product of two vectors.
*   **`getredValue()`, `getgreenValue()`, `getblueValue()`:** Getter methods for color components.
*   **`getNormal(double x1, double y1, double z1)`:** Abstract method to calculate the surface normal at an intersection point.

### `Sphere`

*   **`x`, `y`, `z`:** Center coordinates of the sphere.
*   **`radius`:** Radius of the sphere.
*   **`Sphere(double x, double y, double z, double radius)`:** Constructor.
*   **`getA(ParametricLine line)`, `getB(ParametricLine line)`, `getC(ParametricLine line)`:** Helper methods for calculating intersection coefficients.
*   **`getT(ParametricLine line)`:** Calculates the intersection `t` value for a ray and a sphere.
*   **`getNormal(double x1, double y1, double z1)`:** Calculates the surface normal at an intersection point on the sphere.

### `Plane`

*   **`a`, `b`, `c`:** Coefficients of the plane equation.
*   **`d`:** Offset of the plane.
*   **`t`:** intersection value.
*   **`Plane(double a, double b, double c)`:** Constructor.
*   **`getT(ParametricLine line)`:** Calculates the intersection `t` value for a ray and a plane.
*   **`getNormal(double x1, double y1, double z1)`:** Calculates the surface normal of the plane.

### `ParametricLine`

*   **`originx`, `originy`, `originz`:** Coordinates of the ray origin.
*   **`destinationx`, `destinationy`, `destinationz`:** Coordinates of a point on the ray.
*   **`ParametricLine(double originx, double originy, double originz, double destinationx, double destinationy, double destinationz)`:** Constructor.
*   **`xfromt(double t)`, `yfromt(double t)`, `zfromt(double t)`:** Calculates the coordinates of a point on the ray given a `t` value.
*   **`getOriginx()`, `getOriginy()`, `getOriginz()`, `getDestinationx()`, `getDestinationy()`, `getDestinationz()`:** Getter methods for ray properties.

## How to Run

1.  **Compile:**
    ```bash
    javac src/*.java
    ```
2.  **Run:**
    ```bash
    java src.RayTrace
    ```

## Limitations

*   **Basic Lighting:** Only diffuse and ambient lighting are implemented.
*   **Single Light Source:** The scene has only one light source.
*   **Limited Object Types:** Only spheres and planes are supported.
*   **No Reflections/Refractions:** The engine does not handle reflections or refractions.
*   **No Anti-Aliasing:** The image may have jagged edges.
* **No Texture:** The objects are only solid colors.

## Future Improvements

*   **Multiple Light Sources:** Add support for multiple light sources.
*   **Specular Lighting:** Implement specular highlights.
*   **Reflections and Refractions:** Add support for reflective and refractive surfaces.
*   **More Object Types:** Add support for other 3D object types (e.g., triangles, cubes).
*   **Anti-Aliasing:** Implement anti-aliasing techniques to smooth edges.
* **Texture Mapping:** Add texture mapping to the objects.
* **Optimization:** Optimize the code for better performance.
* **User Interface:** Create a user interface to change the scene.

## Author

Gregory Cal
