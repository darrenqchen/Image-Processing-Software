package model.transformation;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import controller.layerbuilder.CheckerboardLayerBuilder;
import model.layer.ILayer;
import model.layer.Layer;
import model.pixel.Pixel;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the DownscaleTransformation class methods.
 */
public class DownscaleTransformationTest extends ATransformationTest {

  ILayer twoByTwo;
  Pixel combinedPix;

  @Before
  public void init() {
    twoByTwo = new CheckerboardLayerBuilder(1, 2, 2, new Pixel(0, 0, 0), new Pixel(4, 4, 4))
        .build();
    combinedPix = new Pixel(2, 2, 2);
  }

  @Test
  public void testEvenScalars() {
    ILayer small = new DownscaleTransformation(.5, .5).apply(twoByTwo.getPixels());

    assertArrayEquals(new Pixel[][]{{combinedPix}}, small.getPixels());
  }

  @Test
  public void testScaleY() {
    ILayer small = new DownscaleTransformation(1, .5).apply(twoByTwo.getPixels());
    assertEquals(new Layer(new Pixel[][]{{combinedPix, combinedPix}}), small);
  }

  @Test
  public void testScaleX() {
    ILayer small = new DownscaleTransformation(.5, 1).apply(twoByTwo.getPixels());
    assertEquals(new Layer(new Pixel[][]{{combinedPix}, {combinedPix}}), small);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalX() {
    new DownscaleTransformation(2, .4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalX2() {
    new DownscaleTransformation(-.2, .4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalY() {
    new DownscaleTransformation(.4, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalY2() {
    new DownscaleTransformation(.3, -.5);
  }
}