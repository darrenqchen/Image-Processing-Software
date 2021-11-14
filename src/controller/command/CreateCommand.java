package controller.command;

import controller.layerbuilder.LayerBuilder;
import controller.layerbuilder.PPMLayerBuilder;
import java.util.Arrays;
import java.util.List;
import model.IModelLIME;
import model.layer.ILayer;

/**
 * Represents the command to create a layer. The way to do this is you pass a filepath then a name
 * for the layer (optional because otherwise it will get set to the filepath).
 */
public class CreateCommand extends AbstractCommand {

  /**
   * Creates a CreateCommand object given the model.
   *
   * @param model the model to apply the command to.
   */
  public CreateCommand(IModelLIME model) {
    super(model);
  }

  /**
   * Adds a file to the model if applicable.
   *
   * @param line the line we're given.
   * @throws IllegalArgumentException if it can't add the layer to the model.
   */
  public void perform(List<String> line) throws IllegalArgumentException {
    if (line == null) {
      throw new IllegalArgumentException("Line cannot null.");
    }
    String file = line.get(0);
    String fileType = file.substring(file.indexOf(".") + 1);
    ILayer layer;
    if (!Arrays.asList("GIF", "PNG", "JPG", "JPEG", "BMP", "WBMP", "PPM")
        .contains(fileType.toUpperCase())) {
      throw new IllegalArgumentException("Not supported file type.");
    } else if (fileType.equalsIgnoreCase("ppm")) {
      try {
        layer = new PPMLayerBuilder(file).build();
      } catch (IllegalStateException e) {
        throw new IllegalArgumentException("Cannot build layer.");
      }
    } else {
      //Arrays.asList("GIF", "PNG", "JPG", "JPEG", "BMP", "WBMP",
      // "PPM").contains(fileType.toUpperCase())
      try {
        layer = new LayerBuilder(file).build();
      } catch (IllegalStateException e) {
        throw new IllegalArgumentException("Cannot build layer.");
      }
    }
    if (line.size() >= 2) {
      layer.changeName(line.get(1));
    } else {
      layer.changeName(file);
    }
    this.model.addLayer(layer);
  }
}
