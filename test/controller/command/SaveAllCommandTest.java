package controller.command;

import org.junit.Test;

/**
 * Tests for the SaveAllCommand class methods.
 */
public class SaveAllCommandTest extends AbstractCommandTest {

  @Test(expected = IllegalArgumentException.class)
  public void nullConstructor() {
    new SaveAllCommand(null);
  }

}