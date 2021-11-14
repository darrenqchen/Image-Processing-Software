package model.layer;

import controller.exporter.ILayerExporter;
import java.awt.image.BufferedImage;
import model.pixel.Pixel;
import model.transformation.ITransformation;

/**
 * Represents an image layer. Ensures that there are appropriate methods for transforming and
 * exporting the images, as well as layer naming functionality.
 */
public interface ILayer {

  /**
   * Applies a transformation to this layer by calling the apply method of the ITransformation and
   * passing the correct image data.
   *
   * @param transformation the transformation that's getting applied.
   * @return a new, transformed, image.
   * @thrown IllegalArgumentException if given null.
   */
  ILayer applyTransformation(ITransformation transformation) throws IllegalArgumentException;

  /**
   * Exports the image based on the image exporter that is passed. Different exporters export to
   * different file formats.
   *
   * @param exporter the function object that exports the image.
   * @param fileName name of the file that it gets exported to.
   * @throws IllegalArgumentException if given null.
   */
  void exportImage(ILayerExporter exporter, String fileName) throws IllegalArgumentException;

  /**
   * Sets the layer name.
   *
   * @param name the new name.
   * @throw IllegalArgumentException if given null.
   */
  void changeName(String name) throws IllegalArgumentException;

  /**
   * Gets the layer name.
   *
   * @return the layer name.
   */
  String getName();

  /**
   * Returns a deep copy of the pixels that create this image.
   *
   * @return array list of Pixels.
   */
  Pixel[][] getPixels();

  /**
   * Hides the layer.
   */
  void hide();

  /**
   * Returns the visibility state of this layer.
   *
   * @return visibility of this layer
   */
  boolean isVisible();

  /**
   * Makes the layer visible.
   */
  void show();

  /**
   * Stores this image information as a buffered image and returns it.
   */
  BufferedImage toBufferedImage();
}
