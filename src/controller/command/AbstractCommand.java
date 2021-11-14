package controller.command;

import model.IModelLIME;

/**
 * Represents the abstract class for all the commands being used in the controller that holds the
 * model. Has to be in the specific order. For example, if the first word input is wrong for the
 * subclass, it will not work.
 */
public abstract class AbstractCommand implements ICommand {

  protected IModelLIME model;

  /**
   * Creates an Abstract Command given the model.
   *
   * @param model the model of the ImageProcessor program
   * @throws IllegalArgumentException if the String array is null.
   */
  public AbstractCommand(IModelLIME model)
      throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Parameters cannot be null.");
    }
    this.model = model;
  }
}
