import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import model.IModelLIME;
import model.MultiLayerLIME;
import model.layer.ILayer;
import model.layer.Layer;
import model.pixel.Pixel;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the MultiLayerImageProcessor class methods.
 */
public class MultiLayerLIMETest {

  private Pixel redPixel;
  private ILayer red1By1;
  private IModelLIME emptyModel;
  private IModelLIME nonEmptyModel;

  @Before
  public void init() {
    redPixel = new Pixel(255, 0, 0);
    red1By1 = new Layer(new Pixel[][]{
        {redPixel}});
    emptyModel = new MultiLayerLIME();
    nonEmptyModel = new MultiLayerLIME(
        new ArrayList<ILayer>(Collections.singletonList(red1By1)));
    red1By1.changeName("red1By1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullConstructorParameter() {
    new MultiLayerLIME(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void transformCurrentLayerNullString() {
    emptyModel.transformCurrentLayer(null);
  }

  @Test(expected = IllegalStateException.class)
  public void transformCurrentLayerEmptyList() {
    emptyModel.transformCurrentLayer("blur");
  }

  @Test
  public void transformCurrentLayerBlur() {
    assertEquals(new Pixel(255, 0, 0), redPixel);
    nonEmptyModel.transformCurrentLayer("blur");
    assertEquals(new Pixel(255, 0, 0), redPixel);
  }

  @Test
  public void transformCurrentLayerSharpen() {
    assertEquals(new Pixel(255, 0, 0), redPixel);
    nonEmptyModel.transformCurrentLayer("sharpen");
    assertEquals(new Pixel(255, 0, 0), redPixel);
  }

  @Test
  public void transformCurrentLayerGreyscale() {
    assertEquals(new Pixel(255, 0, 0), redPixel);
    nonEmptyModel.transformCurrentLayer("greyscale");
    assertEquals(new Pixel(255, 0, 0), redPixel);
  }

  @Test
  public void transformCurrentLayerSepia() {
    assertEquals(new Pixel(255, 0, 0), redPixel);
    nonEmptyModel.transformCurrentLayer("sepia");
    assertEquals(new Pixel(255, 0, 0), redPixel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addLayerNull() {
    emptyModel.addLayer(null);
  }

  @Test
  public void addLayer() {
    assertEquals(0, emptyModel.getLayerSize());
    emptyModel.addLayer(red1By1);
    assertEquals(1, emptyModel.getLayerSize());
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeLayerNull() {
    nonEmptyModel.removeLayer(null);
  }

  @Test
  public void removeLayerTest() {
    assertEquals(1, nonEmptyModel.getLayerSize());
    nonEmptyModel.removeLayer("red1By1");
    assertEquals(0, nonEmptyModel.getLayerSize());
  }

  @Test(expected = IllegalStateException.class)
  public void getCurrentLayerEmptyList() {
    emptyModel.getCurrentLayer();
  }

  @Test
  public void getCurrentLayerNonEmptyList() {
    assertEquals(red1By1, nonEmptyModel.getCurrentLayer());
  }

  @Test
  public void getAllLayersEmptyList() {
    assertEquals(new ArrayList<ILayer>(), emptyModel.getAllLayers());
  }

  @Test
  public void getAllLayersNonEmptyList() {
    assertEquals(new ArrayList<ILayer>(Collections.singletonList(red1By1)),
        nonEmptyModel.getAllLayers());
  }

  @Test
  public void getLayerSizeEmpty() {
    assertEquals(0, emptyModel.getLayerSize());
  }

  @Test
  public void getLayerSizeNonEmpty() {
    assertEquals(1, nonEmptyModel.getLayerSize());
  }

  @Test(expected = IllegalArgumentException.class)
  public void showLayerNull() {
    nonEmptyModel.showLayer(null);
  }

  @Test
  public void showLayerTest() {
    nonEmptyModel.hideLayer("red1By1");
    assertFalse(red1By1.isVisible());
    nonEmptyModel.showLayer("red1By1");
    assertTrue(red1By1.isVisible());
  }

  @Test(expected = IllegalArgumentException.class)
  public void hideLayerNull() {
    nonEmptyModel.removeLayer(null);
  }

  @Test
  public void hideLayerTest() {
    assertTrue(red1By1.isVisible());
    nonEmptyModel.hideLayer("red1By1");
    assertFalse(red1By1.isVisible());
  }
}