package model.layer;

import controller.exporter.ILayerExporter;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Objects;
import model.pixel.Pixel;
import model.transformation.ITransformation;

/**
 * Creates an image class consisting of a 2D array of pixels.
 */
public class Layer implements ILayer {

  private final Pixel[][] pixels;
  private boolean isVisible;
  private String name;

  /**
   * Creates an {@code Layer} given a 2D array of pixels to represent the image and name for the
   * layer.
   *
   * @param pixels 2D array of pixels that creates the image.
   * @param name   layer name.
   */
  public Layer(Pixel[][] pixels, String name) {
    if (pixels == null || name == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    this.pixels = pixels.clone();
    this.isVisible = true;
    this.name = name;
  }

  /**
   * Creates a {@code Layer} given a 2D array of pixels to represent the image and automatically
   * names it "New Layer".
   *
   * @param pixels 2D array of pixels that creates the image.
   */
  public Layer(Pixel[][] pixels) {
    this(pixels, "New Layer");
  }

  /**
   * Copy constructor for Layer.
   *
   * @param layer layer to create a copy of
   * @throws IllegalArgumentException if given null
   */
  public Layer(ILayer layer) throws IllegalArgumentException {
    if (layer == null) {
      throw new IllegalArgumentException("layer cannot be null");
    }
    this.pixels = layer.getPixels();
    this.name = layer.getName();
    this.isVisible = layer.isVisible();
  }

  @Override
  public ILayer applyTransformation(ITransformation transformation)
      throws IllegalArgumentException {
    if (transformation == null) {
      throw new IllegalArgumentException("Transformation cannot be null");
    }
    return transformation.apply(this.pixels);
  }

  @Override
  public void changeName(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null.");
    }
    this.name = name;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void exportImage(ILayerExporter exporter, String fileName)
      throws IllegalArgumentException {
    if (exporter == null || fileName == null) {
      throw new IllegalArgumentException("Parameters cannot be null.");
    }
    exporter.export(this.pixels, fileName);
  }

  @Override
  public Pixel[][] getPixels() {
    return this.pixels.clone();
  }

  @Override
  public void hide() {
    this.isVisible = false;
  }

  @Override
  public void show() {
    this.isVisible = true;
  }

  @Override
  public boolean isVisible() {
    return this.isVisible;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    } else if (that instanceof Layer) {
      Layer img = (Layer) that;
      return Arrays.deepEquals(this.pixels, img.pixels);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.pixels);
  }

  @Override
  public BufferedImage toBufferedImage() {
    BufferedImage bi = new BufferedImage(this.pixels[0].length, this.pixels.length,
        BufferedImage.TYPE_INT_ARGB);
    for (int row = 0; row < this.pixels.length; row += 1) {
      for (int col = 0; col < this.pixels[row].length; col += 1) {
        Pixel p = this.pixels[row][col];
        Color c = new Color(p.getRed(), p.getGreen(), p.getBlue());
        bi.setRGB(col, row, c.getRGB());
      }
    }
    return bi;
  }

}
