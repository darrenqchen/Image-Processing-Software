import static org.junit.Assert.assertEquals;

import controller.layerbuilder.PPMLayerBuilder;
import java.io.IOException;
import model.IModelLIME;
import model.MultiLayerLIME;
import model.layer.ILayer;
import org.junit.Before;
import org.junit.Test;
import view.ILayerProcessorView;
import view.LayerProcessorView;

/**
 * Test class for ImageProcessorView. Tests that public methods work and throw errors when
 * expected.
 */
public class ImageProcessorViewTest {

  private IModelLIME model;


  @Before
  public void init() {
    model = new MultiLayerLIME();
    ILayer sunset;
    sunset = new PPMLayerBuilder("res/Photos/sunset.ppm").build();
    sunset.changeName("sunset");
    model.addLayer(sunset);
  }

  @Test
  public void testRenderMessage() {
    Appendable out = new StringBuilder();

    ILayerProcessorView view = new LayerProcessorView(model, out);

    view.renderMessage("testing, 1 2, testing.");

    assertEquals("testing, 1 2, testing.\n", out.toString());

    view.renderMessage("Another message");

    assertEquals("testing, 1 2, testing.\nAnother message\n", out.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testRenderMessageFail() {
    Appendable out = new ReliablyUnreliableAppendable();
    ILayerProcessorView view = new LayerProcessorView(model, out);

    view.renderMessage("Doesn't matter");
  }

  @Test
  public void testRenderLayerInfo() {
    Appendable out = new StringBuilder();
    ILayerProcessorView view = new LayerProcessorView(model, out);

    view.renderLayerInfo();

    assertEquals("Layer 1: sunset | Visible\n", out.toString());

    model.hideLayer("sunset");

    view.renderLayerInfo();

    assertEquals("Layer 1: sunset | Visible\nLayer 1: sunset | Hidden\n", out.toString());

  }

  @Test(expected = IllegalStateException.class)
  public void testRenderLayerInfoFail() {
    Appendable out = new ReliablyUnreliableAppendable();
    ILayerProcessorView view = new LayerProcessorView(model, out);

    view.renderLayerInfo();
  }

  /**
   * Fake appendable to simulate an appendable failing. Used in these tests to trigger errors.
   */
  private class ReliablyUnreliableAppendable implements Appendable {

    @Override
    public Appendable append(CharSequence csq) throws IOException {
      throw new IOException("LOL");
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
      throw new IOException("LOL");
    }

    @Override
    public Appendable append(char c) throws IOException {
      throw new IOException("LOL");
    }
  }
}