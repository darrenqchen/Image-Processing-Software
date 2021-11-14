import controller.command.CreateCommand;
import org.junit.Test;

/**
 * Tests for the CreateCommand class methods.
 */
public class CreateCommandTest extends AbstractCommandTest {

  @Test(expected = IllegalArgumentException.class)
  public void nullConstructor() {
    new CreateCommand(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void goNullLine() {
    new CreateCommand(emptyModel).perform(null);
  }
}