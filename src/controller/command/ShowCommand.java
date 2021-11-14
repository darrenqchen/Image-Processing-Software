package controller.command;

import java.util.List;
import model.IModelLIME;

/**
 * Command for showing a layer in the model. Takes the name of the layer as an arg.
 */
public class ShowCommand extends AbstractCommand {

  /**
   * Creates a ShowCommand object that just takes in the model of the ImageProcessor.
   *
   * @param model the model for the ImageProcessor.
   */
  public ShowCommand(IModelLIME model) {
    super(model);
  }

  /**
   * Changes the given layer to visible.
   *
   * @param line the line we're given.
   * @throws IllegalArgumentException if the line is null or you can't show the layer.
   */
  public void perform(List<String> line) throws IllegalArgumentException {
    if (line == null) {
      throw new IllegalArgumentException("Line cannot null.");
    }
    this.model.showLayer(line.get(0));
  }
}
