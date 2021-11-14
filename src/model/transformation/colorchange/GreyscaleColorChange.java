package model.transformation.colorchange;


/**
 * Creates a Greyscale transformation that changes all the colors to a shade of grey.
 */
public class GreyscaleColorChange extends AColorTransformation {

  /**
   * Creates a {@code GreyscaleColorChange} object with zero parameters.
   */
  public GreyscaleColorChange() {
    super(new double[][]{
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}
    });
  }

  /**
   * Creates a {@code GreyscaleColorChange} object with with a given kernel.
   */
  public GreyscaleColorChange(double[][] kernel) {
    super(kernel);
  }
}
