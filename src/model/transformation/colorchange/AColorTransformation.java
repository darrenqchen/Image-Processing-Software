package model.transformation.colorchange;

import model.layer.ILayer;
import model.layer.Layer;
import model.pixel.Pixel;
import model.transformation.ITransformation;

/**
 * Represents the base for the image transformations like greyscale and sepia.
 */
public abstract class AColorTransformation implements ITransformation {

  protected final double[][] kernel;

  /**
   * Creates an {@code AColorTransformation} given a 2D double array.
   *
   * @param kernel the 2D array to apply the transformation.
   */
  public AColorTransformation(double[][] kernel) {
    if (kernel == null) {
      throw new IllegalArgumentException("Kernel cannot be null.");
    }
    // tests for a ragged array
    for (int i = 0; i < kernel.length; i += 1) {
      // the kernel has to be n x 3
      if (kernel[i].length != 3) {
        throw new IllegalArgumentException("Kernel has to be n x 3");
      }
    }

    // does a shallow copy because we don't change the kernel
    this.kernel = kernel.clone();
  }

  @Override
  public ILayer apply(Pixel[][] pixels) {
    if (pixels == null) {
      throw new IllegalArgumentException("Pixels cannot be null.");
    }
    // initializes the new Pixel array to the same length as the given pixel
    Pixel[][] newPixels = new Pixel[pixels.length][pixels[0].length];
    // loops through each pixel
    for (int i = 0; i < pixels.length; i += 1) {
      for (int j = 0; j < pixels[0].length; j += 1) {
        newPixels[i][j] = this.changePixelColor(pixels, i, j);
      }
    }
    return new Layer(newPixels);
  }

  /**
   * Changes the specific Pixel's color and returns a new one so the old one is not mutated.
   *
   * @param pixels 2D array of pixels that create the image.
   * @param i      row number.
   * @param j      column number.
   * @return the changed Pixel.
   */
  private Pixel changePixelColor(Pixel[][] pixels, int i, int j) {
    Pixel p = pixels[i][j];
    int red = 0;
    int green = 0;
    int blue = 0;
    // loops through the rows (we are assuming that right now there are only 3x3 matrices.
    // We can easily switch though
    red += this.kernel[0][0] * p.getRed()
        + this.kernel[0][1] * p.getGreen()
        + this.kernel[0][2] * p.getBlue();
    green += this.kernel[1][0] * p.getRed()
        + this.kernel[1][1] * p.getGreen()
        + this.kernel[1][2] * p.getBlue();
    blue += this.kernel[2][0] * p.getRed()
        + this.kernel[2][1] * p.getGreen()
        + this.kernel[2][2] * p.getBlue();
    return new Pixel(red, green, blue);
  }
}
