package controller.actioncommand;

import controller.Features;
import javax.swing.Action;

/**
 * The interface to be able to get certain commands from the action commands list without allowing
 * extra functions.
 */
public interface ActionCommand {

  /**
   * Adds the {@code Features} to the ActionCommand.
   *
   * @param f the Feature.
   */
  void addFeature(Features f);

  /**
   * Gets the Action to do each ActionEvent in the view.
   *
   * @param command the command to get.
   * @return the Action
   */
  Action getCommand(String command);

}
