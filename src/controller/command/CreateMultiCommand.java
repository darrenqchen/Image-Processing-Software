package controller.command;

import controller.layerbuilder.LayerBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import model.IModelLIME;
import model.layer.ILayer;

/**
 * Represents a CreateMulti command that loads all the layers in the file folder. The way to use
 * this is you call "createMulti folderName" and it'll load all the layers
 */
public class CreateMultiCommand extends AbstractCommand {

  /**
   * Creates a {@code CreateMultiCommand} object that takes in the model.
   *
   * @param model the model of the Image Processor program.
   */
  public CreateMultiCommand(IModelLIME model) {
    super(model);
  }

  /**
   * Adds all the files in the folder path to the model if applicable.
   *
   * @param line the line we're given.
   * @throws IllegalArgumentException if it can't add the layer to the model.
   */
  public void perform(List<String> line) throws IllegalArgumentException {
    if (line == null) {
      throw new IllegalArgumentException("Line cannot be null");
    }
    String filePath = line.get(0);

    if (!new File(filePath).exists()) {
      throw new IllegalArgumentException("Invalid file path");
    }

    try {
      Scanner reader = new Scanner(new FileInputStream(filePath + "/info.txt"));
      while (reader.hasNextLine()) {
        String[] nextLine = reader.nextLine().split(":");
        ILayer l = new LayerBuilder(filePath + "/" + nextLine[0]).build();
        l.changeName(nextLine[0].substring(0, nextLine[0].indexOf(".")));
        if (Integer.parseInt(nextLine[1]) == 0) {
          l.hide();
        }
        this.model.addLayer(l);
      }
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Invalid multi layer file format, couldn't find info.txt");
    }
  }
}
