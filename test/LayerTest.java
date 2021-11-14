import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import controller.exporter.LayerExporter;
import controller.exporter.PPMLayerExporter;
import controller.layerbuilder.PPMLayerBuilder;
import java.util.Objects;
import model.layer.ILayer;
import model.layer.Layer;
import model.pixel.Pixel;
import model.transformation.SepiaColorChange;
import model.transformation.BlurFilter;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the Image class methods.
 */
public class LayerTest {

  private Pixel p;
  private Pixel[][] pixelsRandom;
  private ILayer red1By1;
  private ILayer white1By1;
  private ILayer white2By2;

  @Before
  public void init() {
    Pixel redPixel;
    Pixel whitePixel;
    p = new Pixel(0, 0, 0);
    redPixel = new Pixel(255, 0, 0);
    whitePixel = new Pixel(255, 255, 255);
    pixelsRandom = new Pixel[][]{
        {new Pixel(2, 2, 2), new Pixel(3, 4, 124)},
        {new Pixel(124, 42, 14), new Pixel(34, 2, 34)}};
    red1By1 = new Layer(new Pixel[][]{
        {redPixel}});
    white1By1 = new Layer(new Pixel[][]{
        {whitePixel}});
    white2By2 = new Layer(new Pixel[][]{
        {whitePixel, whitePixel},
        {whitePixel, whitePixel}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorNullPixels() {
    new Layer(null, "Hi");
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorNullString() {
    new Layer(pixelsRandom, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorNullLayer() {
    new Layer(pixelsRandom, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void applyTransformationNull() {
    white2By2.applyTransformation(null);
  }

  @Test
  public void applyTransformationBlur() {
    assertArrayEquals(new Pixel[][]{{new Pixel(63, 63, 63)}},
        white1By1.applyTransformation(new BlurFilter()).getPixels());
  }

  @Test
  public void applyTransformationSepia() {
    Pixel p = white1By1.applyTransformation(new SepiaColorChange()).getPixels()[0][0];
    assertArrayEquals(new Pixel[][]{{new Pixel(255, 255, 238)}},
        white1By1.applyTransformation(new SepiaColorChange()).getPixels());
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeNameNullString() {
    white1By1.changeName(null);
  }

  @Test
  public void changeNameTest() {
    assertEquals("New Layer", white1By1.getName());
    white1By1.changeName("New Thing");
    assertEquals("New Thing", white1By1.getName());
  }

  @Test
  public void getNameNoChange() {
    assertEquals("New Layer", white1By1.getName());
    white1By1.changeName("New Thing");
    assertEquals("New Thing", white1By1.getName());
  }

  @Test
  public void getNameChange() {
    assertEquals("New Layer", white1By1.getName());
    white1By1.changeName("New Thing");
    assertEquals("New Thing", white1By1.getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void exportImageNullExporter() {
    white1By1.exportImage(null, "Hey");
  }

  @Test(expected = IllegalArgumentException.class)
  public void exportImageNullString() {
    white1By1.exportImage(new LayerExporter(), null);
  }

  @Test
  public void exportImageTest() {
    red1By1.exportImage(new PPMLayerExporter(), "test/exportTest02.ppm");
    assertArrayEquals(new PPMLayerBuilder("test/exportTest01.ppm").build().getPixels(),
        new PPMLayerBuilder("test/exportTest02.ppm").build().getPixels());
  }

  @Test
  public void getPixels() {
    assertArrayEquals(new Layer(pixelsRandom).getPixels(), pixelsRandom);
  }

  @Test
  public void hideTest() {
    assertTrue(white1By1.isVisible());
    white1By1.hide();
    assertFalse(white1By1.isVisible());
  }

  @Test
  public void showTest() {
    white1By1.hide();
    assertFalse(white1By1.isVisible());
    white1By1.show();
    assertTrue(white1By1.isVisible());
  }

  @Test
  public void isVisibleShownTest() {
    assertTrue(white1By1.isVisible());
  }

  @Test
  public void isVisibleHiddenTest() {
    white1By1.hide();
    assertFalse(white1By1.isVisible());
  }

  @Test
  public void testEquals() {
    assertEquals(new Layer(new Pixel[][]{}), new Layer(new Pixel[][]{}));
    assertNotEquals(new Layer(new Pixel[][]{}), new Layer(new Pixel[][]{{p}, {p}}));
  }

  @Test
  public void testHashCode() {
    assertEquals(Objects.hash(pixelsRandom), new Layer(pixelsRandom).hashCode());
  }
}