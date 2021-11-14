package controller.layerbuilder;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.layer.Layer;
import model.pixel.Pixel;

/**
 * Supports building the layer for GIF, PNG, JPG, JPEG, BMP, and WBMP file types. Not PPM.
 */
public class LayerBuilder implements ILayerBuilder {

  private final String filePath;

  /**
   * Creates a {@code LayerBuilder} given a file path as a String.
   *
   * @param filePath the file path for the file.
   * @throws IllegalArgumentException if the file path string is null.
   */
  public LayerBuilder(String filePath) throws IllegalArgumentException {
    if (filePath == null) {
      throw new IllegalArgumentException("File path cannot be null.");
    }
    this.filePath = filePath;
  }

  @Override
  public Layer build() throws IllegalStateException {
    try {
      // the line that reads the image file
      BufferedImage image = ImageIO.read(new File(this.filePath));
      int width = image.getWidth();
      int height = image.getHeight();
      Pixel[][] pixels = new Pixel[height][width];

      for (int i = 0; i < height; i += 1) {
        for (int j = 0; j < width; j += 1) {
          Color color = new Color(image.getRGB(j, i));
          pixels[i][j] = new Pixel(color.getRed(), color.getGreen(), color.getBlue());
        }
      }
      return new Layer(pixels);
    } catch (IOException e) {
      throw new IllegalStateException("File not found");
    }
  }
}
