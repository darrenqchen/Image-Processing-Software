package model.transformation.colorchange;


/**
 * Creates a Sepia transformation that changes the image into a reddish brown tone.
 */
public class SepiaColorChange extends AColorTransformation {

  /**
   * Creates a {@code SepiaColorChange} object with zero parameters.
   */
  public SepiaColorChange() {
    super(new double[][]{
        {0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}
    });
  }

  /**
   * Creates a {@code SepiaColorChange} object with with a given kernel.
   */
  public SepiaColorChange(double[][] kernel) {
    super(kernel);
  }
}
