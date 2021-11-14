package controller.exporter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.pixel.Pixel;

/**
 * Supports exporting to files for GIF, PNG, JPEG, BMP, and WBMP file types. Not PPM.
 */
public class LayerExporter implements ILayerExporter {

  @Override
  public void export(Pixel[][] pixels, String fileName)
      throws IllegalArgumentException, IllegalStateException {

    if (pixels == null || fileName == null) {
      throw new IllegalArgumentException("Cannot pass null arguments");
    }

    File outputFile = new File(fileName);
    String extension = fileName.substring(fileName.indexOf(".") + 1);
    BufferedImage img = new BufferedImage(pixels[0].length, pixels.length,
        BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < pixels.length; i += 1) {
      for (int j = 0; j < pixels[0].length; j += 1) {
        Pixel p = pixels[i][j];
        int r = p.getRed();
        int g = p.getGreen();
        int b = p.getBlue();
        int color = (r << 16) | (g << 8) | b;
        img.setRGB(j, i, color);
      }
    }

    try {
      ImageIO.write(img, extension, outputFile);
    } catch (IOException e) {
      throw new IllegalStateException("Failed to write file.");
    }
  }
}
