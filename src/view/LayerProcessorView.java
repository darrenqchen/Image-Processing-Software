package view;

import java.io.IOException;
import java.util.List;
import model.IModelLIME;
import model.layer.ILayer;

/**
 * Creates a view model of the Image Processor to display the info of the layers after a command is
 * initiatied.
 */
public class LayerProcessorView implements ILayerProcessorView {

  private final IModelLIME model;
  private final Appendable out;

  /**
   * Creates an ImageProcessorView.
   *
   * @param model the ImageProcessor model
   * @param out   the output stream to append text to
   * @throws IllegalArgumentException if given null.
   */
  public LayerProcessorView(IModelLIME model, Appendable out)
      throws IllegalArgumentException {
    if (model == null || out == null) {
      throw new IllegalArgumentException("Cannot pass null args.");
    }
    this.model = model;
    this.out = out;
  }

  /**
   * Appends the message to out.
   *
   * @param message the message that should be rendered/shown
   * @throws IllegalStateException if we cannot write to out.
   */
  @Override
  public void renderMessage(String message) throws IllegalStateException {
    try {
      this.out.append(message + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Could not write to out.");
    }
  }

  /**
   * Appends the board information to out.
   *
   * @throws IllegalStateException if we cannot append to out.
   */
  @Override
  public void renderLayerInfo() throws IllegalStateException {
    StringBuilder s = new StringBuilder();

    List<ILayer> layers = this.model.getAllLayers();
    for (int i = 0; i < layers.size(); i += 1) {
      ILayer l = layers.get(i);
      s.append(
          "Layer " + (i + 1) + ": " + l.getName() + (l.isVisible() ? " | Visible" : " | Hidden")
              + "\n");
    }

    try {
      this.out.append(s.toString());
    } catch (IOException e) {
      throw new IllegalStateException("Could not write to out.");
    }
  }
}
