package model.transformation;

import model.layer.ILayer;
import model.pixel.Pixel;

/**
 * Represents the base transformation to alter the image.
 */
public interface ITransformation {

  /**
   * Applies the filter kernel to the given image and returns the new altered image.
   *
   * @param pixels image data to apply the filter to.
   * @return the altered image.
   * @throws IllegalArgumentException if given null
   */
  ILayer apply(Pixel[][] pixels) throws IllegalArgumentException;
}
