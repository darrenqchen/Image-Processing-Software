import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import controller.exporter.LayerExporter;
import controller.layerbuilder.CheckerboardLayerBuilder;
import controller.layerbuilder.LayerBuilder;
import model.layer.ILayer;
import model.pixel.Pixel;
import org.junit.Test;

/**
 * Ensures that layers are exported properly. Uses the previously tested LayerBuilder class.
 */
public class LayerExporterTest {

  @Test
  public void exportPNGTest() {
    ILayer img = new CheckerboardLayerBuilder(1, 1, 1, new Pixel(255, 0, 0), new Pixel(0, 0, 0))
        .build();
    img.exportImage(new LayerExporter(), "test/exportPngTest01.png");

    ILayer importedImage = new LayerBuilder("test/exportPngTest01.png").build();

    assertEquals(img, importedImage);
  }

  @Test
  public void exportJPGTest() {
    ILayer img = new CheckerboardLayerBuilder(1, 1, 1, new Pixel(255, 0, 0), new Pixel(0, 0, 0))
        .build();
    img.exportImage(new LayerExporter(), "test/exportJPGTest01.jpg");

    ILayer importedImage = new LayerBuilder("test/exportJPGTest01.jpg").build();

    //Same thing here, this shouldn't be the same b/c of the way that JPG compresses images...
    assertNotEquals(img, importedImage);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullArg1() {
    new LayerExporter().export(null, "hi");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullArg2() {
    new LayerExporter().export(new Pixel[2][2], null);
  }
}