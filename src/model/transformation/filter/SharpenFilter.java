package model.transformation.filter;

/**
 * Function object that will sharpen an image when passed to the image's applyTransformation
 * method.
 */
public class SharpenFilter extends AFilter {

  /**
   * Creates a {@code SharpenFilter} object with zero parameters which is the given kernel.
   */
  public SharpenFilter() {
    super(new double[][]{
        {-.125, -.125, -.125, -.125, -.125},
        {-.125, .25, .25, .25, -.125},
        {-.125, .25, 1, .25, -.125},
        {-.125, .25, .25, .25, -.125},
        {-.125, -.125, -.125, -.125, -.125}
    });
  }
}
