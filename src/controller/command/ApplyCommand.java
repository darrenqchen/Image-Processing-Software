package controller.command;

import java.util.Arrays;
import java.util.List;
import model.IModelLIME;

/**
 * Represents the command for applying a transformation to the specified Layer (Ex. blue, sharpen,
 * greyscale, sepia). The way to use this is that it takes the first word and applies the
 * transformation.
 */
public class ApplyCommand extends AbstractCommand {

  /**
   * Creates an ApplyCommand object to be able to transform the given layer.
   *
   * @param model the model for the ImageProcessor
   */
  public ApplyCommand(IModelLIME model) {
    super(model);
  }

  /**
   * Applies the transformation to the layer based on the given keyword.
   *
   * @param line the line we're given.
   * @throws IllegalArgumentException if you can't transform the layer.
   * @throws IllegalStateException    if the layer size is less than 1
   */
  public void perform(List<String> line) throws IllegalArgumentException {
    if (line == null) {
      throw new IllegalArgumentException("Line cannot be null");
    } else if (this.model.getLayerSize() <= 0) {
      throw new IllegalStateException("Layer size is less than 1.");
    }
    String transformation = line.get(0).toLowerCase();
    if (Arrays.asList("blur", "sharpen", "greyscale", "sepia").contains(transformation)) {
      this.model.transformCurrentLayer(transformation);
    } else {
      throw new IllegalArgumentException("Invalid Transformation.");
    }
  }
}
