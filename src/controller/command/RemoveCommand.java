package controller.command;

import java.util.List;
import model.IModelLIME;

/**
 * Command to remove a layer from the model. The way to do this is that it takes the first word
 * which is the name of the layer to remove and removes it.
 */
public class RemoveCommand extends AbstractCommand {

  /**
   * Creates a RemoveCommand object that just takes in the model of the ImageProcessor.
   *
   * @param model the model for the ImageProcessor.
   */
  public RemoveCommand(IModelLIME model) {
    super(model);
  }

  /**
   * Simply passes the information along to the correct public facing method in the model. Any
   * errors that the model throws will be passed back to the caller.
   *
   * @param line the arguments, should only be one, the name of the layer (all others are ignored)
   * @throws IllegalArgumentException if line is null.
   */
  @Override
  public void perform(List<String> line) throws IllegalArgumentException {
    if (line == null) {
      throw new IllegalArgumentException("Line cannot null.");
    }
    String layerName = line.get(0);

    //IllegalArgumentException will propagate, this is intentional.
    this.model.removeLayer(layerName);
  }
}
