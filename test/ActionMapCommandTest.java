import controller.actioncommand.ActionCommand;
import controller.actioncommand.ActionMapCommand;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the ActionMapCommand methods.
 */
public class ActionMapCommandTest {

  private ActionCommand map;

  @Before
  public void init() {
    map = new ActionMapCommand();
  }

  @Test(expected = IllegalArgumentException.class)
  public void addFeatureNullFeature() {
    map.addFeature(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getCommandNullCommand() {
    map.getCommand(null);
  }
}