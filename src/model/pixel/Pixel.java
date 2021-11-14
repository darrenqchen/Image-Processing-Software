package model.pixel;

import java.awt.Color;
import java.util.Objects;

/**
 * Creates a Pixel class to create an image which consists of 3 color channels (Red, Green, Blue).
 * Is also an immutable class.
 */
public class Pixel implements IPixel {

  private final int red;
  private final int green;
  private final int blue;

  /**
   * Creates a {@code Pixel} object which clamps the object so we do not have to check for invalid
   * integers.
   *
   * @param red   red value.
   * @param green green value.
   * @param blue  blue value.
   */
  public Pixel(int red, int green, int blue) {
    this.red = this.clamp(red, MIN_COLOR_VAL, MAX_COLOR_VAL);
    this.green = this.clamp(green, MIN_COLOR_VAL, MAX_COLOR_VAL);
    this.blue = this.clamp(blue, MIN_COLOR_VAL, MAX_COLOR_VAL);

  }

  /**
   * Creates a {@code Pixel} object.
   *
   * @param c the java.awt.Color that we turn into our pixel
   */
  public Pixel(Color c) {
    this.red = c.getRed();
    this.green = c.getGreen();
    this.blue = c.getBlue();
  }

  /**
   * Clamps the first value to be between the min and max.
   *
   * @param value the value that gets clamped
   * @param min   minimum allowable value
   * @param max   maximum allowable value
   * @return and int between min and max inclusive
   */
  protected int clamp(int value, int min, int max) {
    if (value > max) {
      return max;
    } else if (value < min) {
      return min;
    }
    return value;
  }

  @Override
  public int getRed() {
    return this.red;
  }

  @Override
  public int getGreen() {
    return this.green;
  }

  @Override
  public int getBlue() {
    return this.blue;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    } else if (that instanceof Pixel) {
      Pixel p = (Pixel) that;
      return this.red == p.getRed()
          && this.green == p.getGreen()
          && this.blue == p.getBlue();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(red, green, blue);
  }

  @Override
  public String toString() {
    return this.red + ", " + this.green + ", " + this.blue;
  }
}
