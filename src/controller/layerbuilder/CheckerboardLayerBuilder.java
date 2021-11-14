package controller.layerbuilder;

import model.layer.ILayer;
import model.layer.Layer;
import model.pixel.Pixel;

/**
 * An image builder that specifically is meant to build a checkerboard only.
 */
public class CheckerboardLayerBuilder implements ILayerBuilder {

  private final int checkerSize;
  private final int rows;
  private final int columns;
  private final Pixel color1;
  private final Pixel color2;

  /**
   * Creates a {@code CheckerboardImageBuilder} given these parameters.
   *
   * @param checkerSize size of each box in a (nxn pixels).
   * @param rows        amount of rows in the checkerboard.
   * @param columns     amount of column in the checkerboard.
   * @param color1      the first color of the checkerboard.
   * @param color2      the second color of the checkerboard.
   * @throws IllegalArgumentException if the integers are below 0 or if a color is null.
   */
  public CheckerboardLayerBuilder(int checkerSize, int rows, int columns, Pixel color1,
      Pixel color2) throws IllegalArgumentException {
    if (checkerSize < 1 || rows < 1 || columns < 1) {
      throw new IllegalArgumentException(
          "Checker size, width, and height need to be greater than 1.");
    } else if (color1 == null || color2 == null) {
      throw new IllegalArgumentException("Pixels cannot be null.");
    }
    this.checkerSize = checkerSize;
    this.rows = rows;
    this.columns = columns;
    this.color1 = color1;
    this.color2 = color2;
  }

  @Override
  public ILayer build() {
    Pixel[][] newPixels = new Pixel[this.checkerSize * this.rows][this.checkerSize
        * this.columns];

    for (int i = 0; i < newPixels.length; i += 1) {
      for (int j = 0; j < newPixels[0].length; j += 1) {
        newPixels[i][j] =
            (i / this.checkerSize + j / this.checkerSize) % 2 == 0 ? this.color1 : this.color2;
      }
    }
    return new Layer(newPixels);
  }
}
