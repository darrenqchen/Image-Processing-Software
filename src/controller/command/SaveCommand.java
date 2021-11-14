package controller.command;

import controller.exporter.LayerExporter;
import controller.exporter.PPMLayerExporter;
import java.util.Arrays;
import java.util.List;
import model.IModelLIME;
import model.layer.ILayer;

/**
 * Command for saving the top layer as an image to a file. Supports PNG, JPG, GIF, BMP, WBMP, and
 * PPM. The way to do this is that it takes the filename as the first word and exports it.
 */
public class SaveCommand extends AbstractCommand {

  /**
   * Creates a SaveCommand object that just takes in the model of the ImageProcessor.
   *
   * @param model the model for the ImageProcessor.
   */
  public SaveCommand(IModelLIME model) {
    super(model);
  }

  /**
   * Exports the layer to the given filePath.
   *
   * @param line the line we're given.
   * @throws IllegalArgumentException if the line is null or you can't export the file.
   */
  public void perform(List<String> line) {
    if (line == null) {
      throw new IllegalArgumentException("Line cannot null.");
    }
    String fileName = line.get(0);
    String ext = fileName.substring(fileName.indexOf(".") + 1);
    ILayer layer = this.model.getCurrentLayer();
    if (ext.equalsIgnoreCase("ppm")) {
      new PPMLayerExporter().export(layer.getPixels(), fileName);
    } else if (Arrays.asList("png", "jpg", "jpeg", "gif", "bmp", "wbmp")
        .contains(ext.toLowerCase())) {
      new LayerExporter().export(layer.getPixels(), fileName);
    } else {
      throw new IllegalArgumentException("Unsupported file type");
    }
  }
}
