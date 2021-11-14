package controller.command;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import model.layer.ILayer;
import model.layer.Layer;
import model.pixel.Pixel;
import org.junit.Test;

/**
 * Tests for the SelectCommand class methods.
 */
public class SelectCommandTest extends AbstractCommandTest {

  @Test(expected = IllegalArgumentException.class)
  public void nullConstructor() {
    new SelectCommand(null);
  }

  @Test
  public void testGo() {
    ILayer l = new Layer(new Pixel[][]{{new Pixel(2, 2, 2)}}, "second");
    nonEmptyModel.addLayer(l);
    new SelectCommand(nonEmptyModel).perform(Arrays.asList("second"));

    assertEquals(nonEmptyModel.getCurrentLayer(), l);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNonExistantLayer() {
    new SelectCommand(nonEmptyModel).perform(Arrays.asList("hi"));
  }

}