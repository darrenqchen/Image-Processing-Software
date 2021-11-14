import controller.command.SaveCommand;
import org.junit.Test;

/**
 * Tests for the SaveCommand class methods.
 */
public class SaveCommandTest extends AbstractCommandTest {

  @Test(expected = IllegalArgumentException.class)
  public void nullConstructor() {
    new SaveCommand(null);
  }

}