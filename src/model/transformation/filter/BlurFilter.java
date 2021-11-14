package model.transformation.filter;

/**
 * Function object that blurs an Image, and returns it.
 */
public class BlurFilter extends AFilter {

  /**
   * Creates a {@code BlurFilter} object with zero parameters which is the given kernel.
   */
  public BlurFilter() {
    super(new double[][]{
        {0.0625, 0.125, 0.0625},
        {0.125, 0.25, 0.125},
        {0.0625, 0.125, 0.0625}});
  }

}
