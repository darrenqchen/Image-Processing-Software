import static org.junit.Assert.assertEquals;

import model.layer.ILayer;
import model.layer.Layer;
import model.pixel.Pixel;
import model.transformation.BlurFilter;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for BlurFilter.
 */
public class BlurFilterTest extends ATransformationTest {

  @Before
  public void init() {
    super.init();
  }

  @Test
  public void white2by2() {
    ILayer blurredImage = white2By2.applyTransformation(new BlurFilter());

    Pixel p = new Pixel(143, 143, 143);
    Pixel[][] expectedPixels = {{p, p}, {p, p}};

    assertEquals(new Layer(expectedPixels), blurredImage);
  }

  @Test
  public void image1() {
    ILayer blurredImage = image1.applyTransformation(new BlurFilter());

    Pixel[][] expectedPixels = new Pixel[2][2];

    expectedPixels[0][0] = new Pixel(95, 95, 79);
    expectedPixels[0][1] = new Pixel(95, 47, 63);
    expectedPixels[1][0] = new Pixel(47, 95, 63);
    expectedPixels[1][1] = new Pixel(47, 47, 79);

    assertEquals(new Layer(expectedPixels), blurredImage);
  }


}