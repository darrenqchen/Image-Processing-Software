import static org.junit.Assert.assertEquals;

import controller.layerbuilder.CheckerboardLayerBuilder;
import controller.layerbuilder.PPMLayerBuilder;
import model.layer.ILayer;
import model.pixel.Pixel;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for PPMImageBuilder.
 */
public class PPMLayerBuilderTest {

  private PPMLayerBuilder builder1;
  private PPMLayerBuilder nonExistentBuilder;
  private PPMLayerBuilder builder1P6;

  @Before
  public void init() {
    builder1 = new PPMLayerBuilder("test/Checkerboard2By2.ppm");
    nonExistentBuilder = new PPMLayerBuilder("test/nonExistent.ppm");
    builder1P6 = new PPMLayerBuilder("test/Checkerboard2By2P6.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullFilePath() {
    new PPMLayerBuilder(null);
  }

  @Test(expected = IllegalStateException.class)
  public void invalidFilePath() {
    nonExistentBuilder.build();
  }

  @Test(expected = IllegalStateException.class)
  public void isP6NotP3File() {
    builder1P6.build();
  }

  @Test
  public void build() {
    ILayer img = builder1.build();
    assertEquals(img, new CheckerboardLayerBuilder(1, 2, 2, new Pixel(255, 0, 0),
        new Pixel(0, 0, 0)).build());
  }
}