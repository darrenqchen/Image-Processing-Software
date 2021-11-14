package controller.layerbuilder;

import model.layer.ILayer;

/**
 * Allows user to build an image from different file formats.
 */
public interface ILayerBuilder {

  /**
   * Builds an {@code IImage} based on how the implementing object is constructed.
   *
   * @return the image
   * @throws IllegalStateException if the image can't be built.
   */
  ILayer build() throws IllegalStateException;

}
