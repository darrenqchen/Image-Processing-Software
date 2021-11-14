package controller.layerbuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import model.layer.ILayer;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for LayerBuilder. Tests both png and jpg images. Uses the PPMImage
 */
public class LayerBuilderTest {

  private ILayer checkLayer;

  @Before
  public void init() {
    checkLayer = new PPMLayerBuilder("test/controller/layerbuilder/Checkerboard2By2.ppm").build();
  }

  @Test
  public void testPNGBuilder() {
    ILayer layer = new LayerBuilder("test/controller/layerbuilder/checker2x2.png").build();

    assertEquals(checkLayer, layer);
  }

  @Test
  public void testJPGBuilder() {
    ILayer layer = new LayerBuilder("test/controller/layerbuilder/checker2x2.jpg").build();

    //JPG images mess everything up bro. IDEK how to test this other than, these shouldn't be the
    //same b/c the JPG algorithm messes them up.
    assertNotEquals(checkLayer, layer);
  }

}