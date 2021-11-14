package controller.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

/**
 * Tests for the ShowCommand class methods.
 */
public class ShowCommandTest extends AbstractCommandTest {

  @Test(expected = IllegalArgumentException.class)
  public void nullConstructor() {
    new ShowCommand(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void goNullLine() {
    new ShowCommand(emptyModel).perform(null);
  }

  @Test
  public void goShowTest() {
    nonEmptyModel.hideLayer("red1by1");
    assertFalse(nonEmptyModel.getAllLayers().get(0).isVisible());
    new ShowCommand(nonEmptyModel).perform(new ArrayList<String>(Arrays.asList("red1by1")));
    assertTrue(nonEmptyModel.getAllLayers().get(0).isVisible());
  }

}