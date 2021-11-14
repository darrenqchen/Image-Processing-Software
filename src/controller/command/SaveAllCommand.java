package controller.command;

import controller.exporter.ILayerExporter;
import controller.exporter.LayerExporter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import model.IModelLIME;
import model.layer.ILayer;

/**
 * Command for saving a layered image. This will create a folder with the specified name, the folder
 * will contain each layer saved as a PNG image file, there will be an info.txt file that contains
 * the ordering and visibility information for the layers.
 */
public class SaveAllCommand extends AbstractCommand {

  /**
   * Creates a SaveAllCommand object that just takes in the model of the ImageProcessor.
   *
   * @param model the model for the ImageProcessor.
   */
  public SaveAllCommand(IModelLIME model) {
    super(model);
  }

  /**
   * Creates a folder which will contain all the layers with a txt file that contains that contains
   * the ordering and visibility for the layers.
   *
   * @param line the line we're given.
   * @throws IllegalArgumentException if the line is null or you can't export any of the files.
   */
  public void perform(List<String> line) throws IllegalArgumentException {
    if (line == null) {
      throw new IllegalArgumentException("Line cannot null.");
    }

    String folderName = line.get(0);
    ILayerExporter exporter = new LayerExporter();
    List<ILayer> layers = this.model.getAllLayers();

    File f = new File(folderName);
    if (f.exists()) {
      throw new IllegalArgumentException("File already exists, cannot override existing files");
    } else if (new File(folderName).mkdir()) {
      // it worked
    } else {
      throw new IllegalArgumentException("Could not create the correct directory.");
    }

    StringBuilder info = new StringBuilder();
    for (int i = 0; i < layers.size(); i += 1) {
      ILayer l = layers.get(i);
      String layerName = l.getName();
      boolean vis = l.isVisible();

      exporter.export(l.getPixels(), folderName + "/" + layerName + ".png");
      info.append(layerName + ".png:" + (vis ? "1" : "0") + "\n");
    }

    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(folderName + "/info.txt"));
      writer.write(info.toString());
      writer.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to write to file");
    }
  }
}
