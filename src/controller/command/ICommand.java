package controller.command;

import java.util.List;

/**
 * Represents the different types of commands you can do in the controller like loading and saving
 * an image.
 */
public interface ICommand {

  /**
   * Does a certain type of command based on the subclass that uses it given the List of Strings.
   *
   * @throws IllegalArgumentException if the line command does not go well.
   * @throws IllegalStateException    if the layer size is less than 1
   */
  void perform(List<String> line) throws IllegalArgumentException;
}
