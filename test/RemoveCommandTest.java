import static org.junit.Assert.assertTrue;

import controller.command.RemoveCommand;
import java.util.Arrays;
import org.junit.Test;

/**
 * Tests class for the remove command. Ensures that removing layers from a model works as expected.
 */
public class RemoveCommandTest extends AbstractCommandTest {

  @Test(expected = IllegalArgumentException.class)
  public void nullConstructor() {
    new RemoveCommand(null);
  }

  @Test
  public void testGo() {
    new RemoveCommand(nonEmptyModel).perform(Arrays.asList("red1by1"));
    assertTrue(nonEmptyModel.getAllLayers().size() == 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNonExistant() {
    new RemoveCommand(nonEmptyModel).perform(Arrays.asList("hi"));
  }


}