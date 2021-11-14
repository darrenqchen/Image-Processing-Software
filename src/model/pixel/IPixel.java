package model.pixel;

/**
 * Represents a Pixel with RGB values.
 */
public interface IPixel {

  int MAX_COLOR_VAL = 255;
  int MIN_COLOR_VAL = 0;

  /**
   * Gets the int value of the red color.
   *
   * @return value of red.
   */
  int getRed();

  /**
   * Gets the int value of the green color.
   *
   * @return value of green.
   */
  int getGreen();

  /**
   * Gets the int value of the blue color.
   *
   * @return value of blue.
   */
  int getBlue();
}
