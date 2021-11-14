import static org.junit.Assert.assertEquals;

import controller.command.ApplyCommand;
import java.util.ArrayList;
import java.util.Arrays;
import model.pixel.Pixel;
import org.junit.Test;

/**
 * Tests for the ApplyCommand class methods.
 */
public class ApplyCommandTest extends AbstractCommandTest {

  @Test(expected = IllegalArgumentException.class)
  public void nullConstructor() {
    new ApplyCommand(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void goNullLine() {
    new ApplyCommand(emptyModel).perform(null);
  }

  @Test
  public void goBlur() {
    assertEquals(new Pixel(255, 0, 0), redPixel);
    new ApplyCommand(nonEmptyModel).perform(new ArrayList<String>(Arrays.asList("blur")));
    assertEquals(new Pixel(255, 0, 0), redPixel);
  }

  @Test
  public void goSharpen() {
    assertEquals(new Pixel(255, 0, 0), redPixel);
    new ApplyCommand(nonEmptyModel).perform(new ArrayList<String>(Arrays.asList("sharpen")));
    assertEquals(new Pixel(255, 0, 0), redPixel);
  }

  @Test
  public void goGreyscale() {
    assertEquals(new Pixel(255, 0, 0), redPixel);
    new ApplyCommand(nonEmptyModel).perform(new ArrayList<String>(Arrays.asList("greyscale")));
    assertEquals(new Pixel(255, 0, 0), redPixel);
  }

  @Test
  public void goSepia() {
    assertEquals(new Pixel(255, 0, 0), redPixel);
    new ApplyCommand(nonEmptyModel).perform(new ArrayList<String>(Arrays.asList("sepia")));
    assertEquals(new Pixel(255, 0, 0), redPixel);
  }
}