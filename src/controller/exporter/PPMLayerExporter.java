package controller.exporter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import model.pixel.Pixel;

/**
 * Is an image exporter that exports the image as a P3 PPM file.
 */
public class PPMLayerExporter implements ILayerExporter {

  @Override
  public void export(Pixel[][] pixels, String fileName)
      throws IllegalArgumentException, IllegalStateException {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
      writer.write("P3\n" + pixels[0].length + " " + pixels.length + "\r\n255\r\n");
      for (Pixel[] row : pixels) {
        for (Pixel p : row) {
          writer.write(p.getRed() + " " + p.getGreen() + " " + p.getBlue() + "\t");
        }
        writer.write("\r\n");
      }

      writer.close();
    } catch (IOException e) {
      throw new IllegalStateException("Unable to open file for writing.");
    }

  }
}
