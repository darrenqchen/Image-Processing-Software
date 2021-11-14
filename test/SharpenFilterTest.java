import static org.junit.Assert.assertEquals;

import model.layer.ILayer;
import model.layer.Layer;
import model.pixel.Pixel;
import model.transformation.SharpenFilter;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for SharpenFilter.
 */
public class SharpenFilterTest extends ATransformationTest {

  @Before
  public void init() {
    super.init();
  }

  @Test
  public void black4By4() {
    ILayer sharperImage = black4By4.applyTransformation(new SharpenFilter());
    assertEquals(sharperImage, black4By4);
  }

  @Test
  public void image1() {
    ILayer sharpenedImage = image1.applyTransformation(new SharpenFilter());
    Pixel[][] expectedPixels = new Pixel[2][2];

    expectedPixels[0][0] = whitePixel;
    expectedPixels[0][1] = new Pixel(255, 127, 127);
    expectedPixels[1][0] = new Pixel(127, 255, 127);
    expectedPixels[1][1] = new Pixel(127, 127, 255);

    assertEquals(new Layer(expectedPixels), sharpenedImage);
  }

}