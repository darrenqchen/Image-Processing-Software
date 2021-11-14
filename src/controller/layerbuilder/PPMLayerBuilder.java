package controller.layerbuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import model.layer.ILayer;
import model.layer.Layer;
import model.pixel.Pixel;

/**
 * Is an image builder that takes in a String filePath (P3 PPM file) and creates an image from it.
 */
public class PPMLayerBuilder implements ILayerBuilder {

  private final String filePath;

  /**
   * Creates a {@code PPMImageBuilder} object given a file path as a String.
   *
   * @param filePath the file path of the directory of the image.
   */
  public PPMLayerBuilder(String filePath) {
    if (filePath == null) {
      throw new IllegalArgumentException("File path cannot be null.");
    }
    this.filePath = filePath;
  }

  /**
   * Builds an Layer from a .ppm file.
   *
   * @return the Layer
   * @throws IllegalStateException if there is no file at the given file path
   */
  @Override
  public ILayer build() throws IllegalStateException {
    Scanner reader;

    try {
      reader = new Scanner(new FileInputStream(this.filePath));
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("No file at path: " + this.filePath);
    }

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (reader.hasNext()) {
      String s = reader.nextLine();
      if (s.length() > 0 && s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    reader = new Scanner(builder.toString());

    String token;

    token = reader.next();
    if (!token.equals("P3")) {
      throw new IllegalStateException("File passed is not a valid PPM file.");
    }
    int width = reader.nextInt();
    int height = reader.nextInt();
    int maxValue = reader.nextInt();

    Pixel[][] pixels = new Pixel[height][width];
    for (int i = 0; i < height; i += 1) {
      for (int j = 0; j < width; j += 1) {
        int r = reader.nextInt();
        int g = reader.nextInt();
        int b = reader.nextInt();
        pixels[i][j] = new Pixel(r, g, b);
      }
    }

    return new Layer(pixels);
  }
}
