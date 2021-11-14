package controller.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

/**
 * Tests for the HideCommand class methods.
 */
public class HideCommandTest extends AbstractCommandTest {

  @Test(expected = IllegalArgumentException.class)
  public void nullConstructor() {
    new HideCommand(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void goNullLine() {
    new HideCommand(emptyModel).perform(null);
  }

  @Test
  public void goHideTest() {
    assertTrue(nonEmptyModel.getAllLayers().get(0).isVisible());
    new HideCommand(nonEmptyModel).perform(new ArrayList<String>(Arrays.asList("red1by1")));
    assertFalse(nonEmptyModel.getAllLayers().get(0).isVisible());
  }

}