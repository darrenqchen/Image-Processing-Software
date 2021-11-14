package model;

import java.util.List;
import model.layer.ILayer;

/**
 * The read only methods that the View of the program uses.
 */
public interface IViewModelLIME {

  /**
   * Returns the top layer to be exported.
   *
   * @throws IllegalStateException if there are no visible layers.
   */
  ILayer getCurrentLayer() throws IllegalStateException;

  /**
   * Returns all of the visible image layers to be saved by the controller.
   *
   * @return a list of the visible layers
   */
  List<ILayer> getAllLayers() throws IllegalStateException;

  /**
   * Gets the number of layers.
   *
   * @return the layer size.
   */
  int getLayerSize();
}
