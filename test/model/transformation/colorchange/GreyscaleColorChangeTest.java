package model.transformation.colorchange;

import static org.junit.Assert.assertArrayEquals;

import model.layer.ILayer;
import model.pixel.Pixel;
import model.transformation.ATransformationTest;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the GreyscaleColorChange class methods.
 */
public class GreyscaleColorChangeTest extends ATransformationTest {

  @Before
  public void init() {
    super.init();
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorNullKernel() {
    new GreyscaleColorChange(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor1x2Kernel() {
    new GreyscaleColorChange(new double[][]{{1, 2}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor1x4Kernel() {
    new GreyscaleColorChange(new double[][]{{1, 2, 3, 4}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorRaggedArray() {
    new GreyscaleColorChange(new double[][]{
        {1, 2, 3},
        {1, 2},
        {1, 2, 3}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void applyNull() {
    new GreyscaleColorChange().apply(null);
  }

  @Test
  public void greyscaleBlack2By2() {
    Pixel[][] image1After = new Pixel[2][2];
    image1After[0][0] = new Pixel(0, 0, 0);
    image1After[0][1] = new Pixel(0, 0, 0);
    image1After[1][0] = new Pixel(0, 0, 0);
    image1After[1][1] = new Pixel(0, 0, 0);
    ILayer change = new GreyscaleColorChange().apply(black2By2.getPixels());
    assertArrayEquals(image1After, change.getPixels());
  }

  @Test
  public void greyscaleWhite2By2() {
    Pixel[][] image1After = new Pixel[2][2];
    image1After[0][0] = new Pixel(254, 254, 254);
    image1After[0][1] = new Pixel(254, 254, 254);
    image1After[1][0] = new Pixel(254, 254, 254);
    image1After[1][1] = new Pixel(254, 254, 254);
    ILayer change = new GreyscaleColorChange().apply(white2By2.getPixels());
    assertArrayEquals(image1After, change.getPixels());
  }

  @Test
  public void greyscaleImage1() {
    Pixel[][] image1After = new Pixel[2][2];
    image1After[0][0] = new Pixel(254, 254, 254);
    image1After[0][1] = new Pixel(54, 54, 54);
    image1After[1][0] = new Pixel(182, 182, 182);
    image1After[1][1] = new Pixel(18, 18, 18);
    ILayer change = new GreyscaleColorChange().apply(image1.getPixels());
    assertArrayEquals(image1After, change.getPixels());
  }
}