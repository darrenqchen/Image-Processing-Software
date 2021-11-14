import static org.junit.Assert.assertArrayEquals;

import controller.layerbuilder.CheckerboardLayerBuilder;
import model.pixel.Pixel;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the CheckerboardImageBuilder class methods.
 */
public class CheckerboardLayerBuilderTest {

  private Pixel redPixel;
  private Pixel blackPixel;

  @Before
  public void init() {
    CheckerboardLayerBuilder builder1;
    builder1 = new CheckerboardLayerBuilder(1, 2, 2,
        new Pixel(255, 0, 0), new Pixel(0, 0, 0));
    redPixel = new Pixel(255, 0, 0);
    blackPixel = new Pixel(0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorCheckerSizeLessThan1() {
    new CheckerboardLayerBuilder(0, 2, 2,
        new Pixel(255, 0, 0), new Pixel(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorRowsLessThan1() {
    new CheckerboardLayerBuilder(1, 0, 2,
        new Pixel(255, 0, 0), new Pixel(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorColumnsLessThan1() {
    new CheckerboardLayerBuilder(1, 2, 0,
        new Pixel(255, 0, 0), new Pixel(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorNullColor1() {
    new CheckerboardLayerBuilder(1, 2, 2,
        null, new Pixel(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorNullColor2() {
    new CheckerboardLayerBuilder(1, 2, 2,
        new Pixel(255, 0, 0), null);
  }

  @Test
  public void build1Row2Columns() {
    assertArrayEquals(
        new Pixel[][]{{redPixel, blackPixel}},
        new CheckerboardLayerBuilder(1, 1, 2,
            new Pixel(255, 0, 0), new Pixel(0, 0, 0)).build().getPixels());
  }

  @Test
  public void build2Row1Columns() {
    assertArrayEquals(
        new Pixel[][]{{redPixel}, {blackPixel}},
        new CheckerboardLayerBuilder(1, 2, 1,
            new Pixel(255, 0, 0), new Pixel(0, 0, 0)).build().getPixels());
  }

  @Test
  public void build2Row2Columns() {
    assertArrayEquals(
        new Pixel[][]{{redPixel, blackPixel}, {blackPixel, redPixel}},
        new CheckerboardLayerBuilder(1, 2, 2,
            new Pixel(255, 0, 0), new Pixel(0, 0, 0)).build().getPixels());
  }
}