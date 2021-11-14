import controller.command.CreateMultiCommand;
import org.junit.Test;

/**
 * Tests for the CreateMultiCommand class methods.
 */
public class CreateMultiCommandTest extends AbstractCommandTest {

  @Test(expected = IllegalArgumentException.class)
  public void nullConstructor() {
    new CreateMultiCommand(null);
  }

}