package model;

import model.layer.ILayer;

/**
 * Represents the interface for the image processing model which is provided the basic functions
 * like applying.
 */
public interface IModelLIME extends IViewModelLIME {

  /**
   * Transforms the top visible layer of the model with the given transformation type.
   *
   * @param command the command that corresponds to the image transformation
   * @throws IllegalArgumentException when given null.
   * @throws IllegalStateException    if the list is empty.
   */
  void transformCurrentLayer(String command) throws IllegalArgumentException;

  /**
   * Downscales all of the layers in the model.
   *
   * @param scaleX the number to scale the x dimension by
   * @param scaleY the number to scale the y dimension by
   * @throws IllegalArgumentException if given numbers larger than 1, less than or equal to 0
   */
  void downScale(double scaleX, double scaleY) throws IllegalArgumentException;

  /**
   * Adds a layer to the bottom of the layer model. Ensures that the layer is the same size as the
   * existing layers.
   *
   * @param newLayer the new layer to be added
   * @throws IllegalArgumentException if given null or the layer size doesn't match the size of the
   *                                  existing layers.
   */
  void addLayer(ILayer newLayer) throws IllegalArgumentException;

  /**
   * Removes the specified layer.
   *
   * @param layerName the name of the layer to remove
   * @throws IllegalArgumentException if given null or there are no layers by the given name
   */
  void removeLayer(String layerName) throws IllegalArgumentException;

  /**
   * Selects the layer as the top most and makes it visible.
   *
   * @param layerName the name of the layer to select
   * @throws IllegalArgumentException if given null or there is no such layer by this name
   */
  void selectLayer(String layerName) throws IllegalArgumentException;

  /**
   * Shows the specified layer.
   *
   * @param layerName the name of the layer to show
   * @throws IllegalArgumentException if given null or there are no layers by the given name
   */
  void showLayer(String layerName) throws IllegalArgumentException;

  /**
   * Hides the specified layer.
   *
   * @param layerName the name of the layer to hide
   * @throws IllegalArgumentException if given null or there are no layers by the given name
   */
  void hideLayer(String layerName) throws IllegalArgumentException;

}
