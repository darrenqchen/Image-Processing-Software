package controller;

/**
 * An interface that outlines all of the features of our program, ie. all the things that we can do
 * to images.
 */
public interface Features {

  /**
   * Loads an image as a layer in the program.
   */
  void load();

  /**
   * Loads an image as a layer in the program.
   */
  void loadAll();

  /**
   * Saves the current top layer as an image.
   */
  void saveCurrentLayer();

  /**
   * Saves all of the layers in a folder, layer order, visibility and image data are preserved.
   */
  void saveAll();

  /**
   * Blurs the current layer.
   */
  void blur();

  /**
   * Sharpens the current layer.
   */
  void sharpen();

  /**
   * Greyscale's the current layer.
   */
  void greyscale();

  /**
   * Sepia's the current layer.
   */
  void sepia();

  /**
   * Downscales the top layer.
   */
  void downscale();

  /**
   * Mosaics the current layer.
   */
  void mosaic();

  /**
   * Builds the checkerboard based on the data given in the GUI.
   */
  void buildChecker();

  /**
   * Changes the name of the current layer to the new name.
   */
  void renameCurrentLayer();

  /**
   * Selects the specified layer.
   *
   * @param layerName the name of the layer to select.
   */
  void selectLayer(String layerName);

  /**
   * Removes the top layer.
   */
  void removeLayer();

  /**
   * Toggles the visibility of the selected layer.
   */
  void toggleLayerVisibility();

  /**
   * Does the choosing of the color for the first color.
   */
  void colorChooser1();

  /**
   * Does the choosing of the color for the second color.
   */
  void colorChooser2();


}
