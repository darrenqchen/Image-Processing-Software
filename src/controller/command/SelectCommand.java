package controller.command;

import java.util.List;
import model.IModelLIME;

/**
 * Command to select a layer in the model. The way to use this is that it takes the layername and
 * sets it to visible and pushes it to the front.
 */
public class SelectCommand extends AbstractCommand {

  /**
   * Creates a SelectCommand object that just takes in the model of the ImageProcessor.
   *
   * @param model the model for the ImageProcessor.
   */
  public SelectCommand(IModelLIME model) {
    super(model);
  }

  /**
   * Changes the given layer to visible and pushes it to the front.
   *
   * @param line the line we're given.
   * @throws IllegalArgumentException if the line is null or you can't show the layer.
   */
  public void perform(List<String> line) throws IllegalArgumentException {
    if (line == null) {
      throw new IllegalArgumentException("Line cannot null.");
    }
    this.model.selectLayer(line.get(0));
  }
}
