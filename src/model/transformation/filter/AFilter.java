package model.transformation.filter;

import model.layer.ILayer;
import model.layer.Layer;
import model.pixel.Pixel;
import model.transformation.ITransformation;

/**
 * Represents the base for the filter transformations like blurring and sharpening.
 */
public abstract class AFilter implements ITransformation {

  protected final double[][] kernel;

  /**
   * Constructs an AFilter.
   *
   * @param kernel the filter data. Must be square, and of odd length.
   * @throws IllegalArgumentException if the kernel is null, or doesn't meet the specified
   *                                  requirements.
   */
  public AFilter(double[][] kernel) {
    if (kernel == null) {
      throw new IllegalArgumentException("Kernel cannot be empty.");
    }
    for (int i = 0; i < kernel.length; i += 1) {
      if (kernel.length != kernel[i].length) {
        throw new IllegalArgumentException("Kernel has to be square.");
      }
    }
    if (kernel.length % 2 == 0 || kernel[0].length % 2 == 0) {
      throw new IllegalArgumentException("Kernel has to be an odd length.");
    }

    this.kernel = kernel.clone();
  }

  @Override
  public ILayer apply(Pixel[][] pixels) throws IllegalArgumentException {
    if (pixels == null) {
      throw new IllegalArgumentException("pixels cannot be null");
    }
    int mid = this.kernel.length / 2;
    Pixel[][] ret = new Pixel[pixels.length][pixels[0].length];
    // loops through the pixels
    for (int i = 0; i < pixels.length; i += 1) {
      for (int j = 0; j < pixels[i].length; j += 1) {
        double totalR = 0;
        double totalG = 0;
        double totalB = 0;
        // loops through the kernel
        for (int k = 0; k < this.kernel.length; k += 1) {
          for (int l = 0; l < this.kernel[0].length; l += 1) {
            Pixel p = this.valueAt(pixels, i - mid + k, j - mid + l);
            double kernelVal = this.kernel[k][l];
            totalR += kernelVal * p.getRed();
            totalG += kernelVal * p.getGreen();
            totalB += kernelVal * p.getBlue();
          }
        }

        ret[i][j] = new Pixel((int) totalR, (int) totalG, (int) totalB);

      }
    }

    return new Layer(ret);
  }

  /**
   * Gets the value at the index of the pixel array, or 0 if index is out of bounds.
   *
   * @param pixels the 2D array we search in
   * @param i      the row index
   * @param j      the column index
   * @return The value at the index, or 0 if index out of bounds.
   */
  protected Pixel valueAt(Pixel[][] pixels, int i, int j) {
    if (i < 0 || j < 0 || i >= pixels.length || j >= pixels[0].length) {
      return new Pixel(0, 0, 0);
    }
    return pixels[i][j];
  }
}
