import static org.junit.Assert.assertArrayEquals;

import model.layer.ILayer;
import model.pixel.Pixel;
import model.transformation.SepiaColorChange;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the SepiaColorChange class methods.
 */
public class SepiaColorChangeTest extends ATransformationTest {

  @Before
  public void init() {
    super.init();
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorNullKernel() {
    new SepiaColorChange(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor1x2Kernel() {
    new SepiaColorChange(new double[][]{{1, 2}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor1x4Kernel() {
    new SepiaColorChange(new double[][]{{1, 2, 3, 4}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorRaggedArray() {
    new SepiaColorChange(new double[][]{
        {1, 2, 3},
        {1, 2},
        {1, 2, 3}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void applyNull() {
    new SepiaColorChange().apply(null);
  }

  @Test
  public void sepiaBlack2By2() {
    Pixel[][] image1After = new Pixel[2][2];
    image1After[0][0] = new Pixel(0, 0, 0);
    image1After[0][1] = new Pixel(0, 0, 0);
    image1After[1][0] = new Pixel(0, 0, 0);
    image1After[1][1] = new Pixel(0, 0, 0);
    ILayer change = new SepiaColorChange().apply(black2By2.getPixels());
    assertArrayEquals(image1After, change.getPixels());
  }

  @Test
  public void sepiaWhite2By2() {
    Pixel[][] image1After = new Pixel[2][2];
    image1After[0][0] = new Pixel(255, 255, 238);
    image1After[0][1] = new Pixel(255, 255, 238);
    image1After[1][0] = new Pixel(255, 255, 238);
    image1After[1][1] = new Pixel(255, 255, 238);
    ILayer change = new SepiaColorChange().apply(white2By2.getPixels());
    assertArrayEquals(image1After, change.getPixels());
  }

  @Test
  public void sepiaImage1() {
    Pixel[][] image1After = new Pixel[2][2];
    image1After[0][0] = new Pixel(255, 255, 238);
    image1After[0][1] = new Pixel(100, 88, 69);
    image1After[1][0] = new Pixel(196, 174, 136);
    image1After[1][1] = new Pixel(48, 42, 33);
    ILayer change = new SepiaColorChange().apply(image1.getPixels());
    assertArrayEquals(image1After, change.getPixels());
  }
}