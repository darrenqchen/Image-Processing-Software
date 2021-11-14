package model.transformation;

import model.layer.ILayer;
import model.layer.Layer;
import model.pixel.Pixel;

/**
 * Downscale image opperation. Apply method downscales an image by the factors provided in the
 * constructor.
 */
public class DownscaleTransformation implements ITransformation {

  private final double xScalar;
  private final double yScaler;

  /**
   * Constructs a DownscaleTransformation function object, ensures that the scalars are in (0, 1].
   *
   * @param x x scalar
   * @param y y scalar
   * @throws IllegalArgumentException if either scalar is invalid.
   */
  public DownscaleTransformation(double x, double y) throws IllegalArgumentException {
    if (x > 1 || y > 1 || x <= 0 || y <= 0) {
      throw new IllegalArgumentException("Scalars must be in (0, 1].");
    }
    this.xScalar = x;
    this.yScaler = y;
  }

  @Override
  public ILayer apply(Pixel[][] pixels) throws IllegalArgumentException {
    int newDimX = (int) (pixels[0].length * this.xScalar);
    int newDimY = (int) (pixels.length * this.yScaler);

    Pixel[][] newPixels = new Pixel[newDimY][newDimX];

    for (int i = 0; i < newPixels.length; i += 1) {
      for (int j = 0; j < newPixels[i].length; j += 1) {
        double oldI = Math.min(pixels.length - 1, (i + .00001) / this.yScaler);
        double oldJ = Math.min(pixels[0].length - 1, (j + .00001) / this.xScalar);

        Pixel p1 = pixels[(int) Math.floor(oldI)][(int) Math.floor(oldJ)];
        Pixel p2 = pixels[(int) Math.floor(oldI)][(int) Math.ceil(oldJ)];
        Pixel p3 = pixels[(int) Math.ceil(oldI)][(int) Math.floor(oldJ)];
        Pixel p4 = pixels[(int) Math.ceil(oldI)][(int) Math.ceil(oldJ)];

        newPixels[i][j] = averagePixels(p1, p2, p3, p4);
      }
    }

    return new Layer(newPixels);
  }

  /**
   * Averages the pixels RGB values and returns a pixel with those average values.
   *
   * @param pixels the pixels to average.
   * @return the pixel with the average RGB values.
   * @throws IllegalArgumentException if given null
   */
  protected Pixel averagePixels(Pixel... pixels) throws IllegalArgumentException {
    if (pixels == null) {
      throw new IllegalArgumentException("cannot pass null");
    }

    int[] totals = {0, 0, 0};

    for (Pixel p : pixels) {
      if (p == null) {
        throw new IllegalArgumentException("Cannot pass null");
      }
      totals[0] += p.getRed();
      totals[1] += p.getGreen();
      totals[2] += p.getBlue();
    }

    return new Pixel(totals[0] / pixels.length, totals[1] / pixels.length,
        totals[2] / pixels.length);
  }
}
