package controller.exporter;

import model.pixel.Pixel;

/**
 * Represents the interface to create the specified file based on the pixels given in.
 */
public interface ILayerExporter {

  /**
   * Creates a File with the given image in pixels (ex. ppm).
   *
   * @throws IllegalArgumentException when given an invalid Pixels array
   * @throws IllegalStateException    when unable to open file for writing.
   */
  void export(Pixel[][] pixels, String fileName)
      throws IllegalArgumentException, IllegalStateException;
}
