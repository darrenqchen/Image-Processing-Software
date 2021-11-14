import static org.junit.Assert.assertArrayEquals;

import java.util.Random;
import model.pixel.Pixel;
import model.transformation.MosaicTransformation;
import org.junit.Test;

/**
 * Tests for the MosaicTransformation class methods.
 */
public class MosaicTransformationTest extends ATransformationTest {

  @Test(expected = IllegalArgumentException.class)
  public void mosaicTransformationNullRandom() {
    new MosaicTransformation(100, null);
  }

  @Test
  public void apply1Seed() {
    Pixel[][] newPixels = new Pixel[][]{{redPixel, redPixel}, {redPixel, redPixel}};
    assertArrayEquals(newPixels,
        new MosaicTransformation(1, new Random(1)).apply(image1.getPixels()).getPixels());
  }

  @Test
  public void apply2Seeds() {
    Pixel[][] newPixels = new Pixel[][]{{whitePixel, redPixel}, {whitePixel, redPixel}};
    assertArrayEquals(newPixels,
        new MosaicTransformation(2, new Random(1)).apply(image1.getPixels()).getPixels());
  }

}