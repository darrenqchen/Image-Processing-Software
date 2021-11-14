package controller.command;

import java.util.List;
import model.IModelLIME;

/**
 * Represents a command that changed the layer to invisible. The way to use this is that it takes
 * the first word as the layer name and sets that layer to invisible.
 */
public class HideCommand extends AbstractCommand {

  /**
   * Creates a HideCommand object which takes in the model to hide the layer.
   *
   * @param model the model for the ImageProcessor.
   */
  public HideCommand(IModelLIME model) {
    super(model);
  }

  /**
   * Changes the specified layer to invisible.
   *
   * @param line the line we're given.
   * @throws IllegalArgumentException if the line is null or you can't show the layer.
   */
  public void perform(List<String> line) throws IllegalArgumentException {
    if (line == null) {
      throw new IllegalArgumentException("Line cannot null.");
    }
    this.model.hideLayer(line.get(0));
  }
}
