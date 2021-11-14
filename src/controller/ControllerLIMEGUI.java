package controller;

import controller.actioncommand.ActionCommand;
import controller.actioncommand.ActionMapCommand;
import controller.command.CreateCommand;
import controller.command.CreateMultiCommand;
import controller.command.SaveAllCommand;
import controller.command.SaveCommand;
import controller.layerbuilder.CheckerboardLayerBuilder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.Action;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.IModelLIME;
import model.layer.ILayer;
import model.pixel.Pixel;
import view.ILayerGUIView;

/**
 * GUI controller for the LIME model. Exposes commands graphically using java swing. Handles action
 * listening for the GUI.
 */
public class ControllerLIMEGUI implements IControllerLIME, Features,
    ActionListener {

  private final ILayerGUIView view;
  private final IModelLIME model;

  ActionCommand commands;

  /**
   * Creates a {@code ControllerLimeGUI} object given a view and model.
   *
   * @param view  the view of the ImageProcessor program.
   * @param model the model of the ImageProcessor program.
   */
  public ControllerLIMEGUI(ILayerGUIView view, IModelLIME model) {
    if (view == null || model == null) {
      throw new IllegalArgumentException("Parameters cannot be null");
    }
    this.view = view;
    this.model = model;

    commands = new ActionMapCommand();
    commands.addFeature(this);
  }

  @Override
  public void run() {
    this.view.setActionListeners(this);
    this.view.setActionCommands();
    view.setDefaultCloseOperation();
    view.setVisibility(true);
    try {
      // Set System L&F
      UIManager.setLookAndFeel(
          UIManager.getSystemLookAndFeelClassName());
    } catch (UnsupportedLookAndFeelException | ClassNotFoundException e) {
      // handle exception
    } catch (InstantiationException e) {
      // handle exception
    } catch (IllegalAccessException e) {
      // handle exception
    }
  }

  @Override
  public void load() {
    try {
      File file = this.view.getFileImage();
      String absoluteFileName = file.toString();
      String shortFileName = absoluteFileName
          .substring(absoluteFileName.lastIndexOf(File.separator) + 1,
              absoluteFileName.lastIndexOf("."));
      new CreateCommand(this.model).perform(Arrays.asList(absoluteFileName, shortFileName));
      this.view.renderMessage("Loading File...");
    } catch (IllegalStateException e) {
      this.view.renderMessage("Error: " + e.getMessage());
    } catch (IllegalArgumentException e) {
      this.view.renderMessage("Error: " + e.getMessage());
    }
    this.view.rerender();
  }

  @Override
  public void loadAll() {
    try {
      File folder = this.view.getFolderImage();
      String absoluteFolderName = folder.getAbsolutePath();

      new CreateMultiCommand(this.model).perform(
          Collections.singletonList(absoluteFolderName));
      this.view.renderMessage("Loading Files...");
    } catch (IllegalArgumentException e) {
      this.view.renderMessage("Error: " + e.getMessage());
    }
    this.view.rerender();
  }

  @Override
  public void saveAll() {
    try {
      File folder = this.view.getSaveFolder();
      String absoluteFolderName = folder.getAbsolutePath();

      new SaveAllCommand(this.model).perform(
          Collections.singletonList(absoluteFolderName));
      this.view.renderMessage("Saved!");
    } catch (IllegalStateException e) {
      this.view.renderMessage("Error: " + e.getMessage());
    } catch (IllegalArgumentException e) {
      this.view.renderMessage("Error: " + e.getMessage());

    }
    this.view.rerender();
  }

  @Override
  public void saveCurrentLayer() {
    try {
      ILayer currentLayer = this.model.getCurrentLayer();
      File file = this.view.getSaveFile();
      String absoluteFileName = file.toString();
      new SaveCommand(this.model).perform(
          Collections.singletonList(absoluteFileName));
      this.view.renderMessage("Saved!");
    } catch (IllegalStateException e) {
      this.view.renderMessage("Error: " + e.getMessage());
    } catch (IllegalArgumentException e) {
      this.view.renderMessage("Error: " + e.getMessage());
    }
    this.view.rerender();
  }

  @Override
  public void blur() {
    try {
      this.model.transformCurrentLayer("blur");
    } catch (IllegalStateException e) {
      this.view.renderMessage(e.getMessage());
    }
    this.view.rerender();
  }

  @Override
  public void sharpen() {
    try {
      this.model.transformCurrentLayer("sharpen");
    } catch (IllegalStateException e) {
      this.view.renderMessage(e.getMessage());
    }
    this.view.rerender();
  }

  @Override
  public void greyscale() {
    try {
      this.model.transformCurrentLayer("greyscale");
    } catch (IllegalStateException e) {
      this.view.renderMessage(e.getMessage());
    }
    this.view.rerender();
  }

  @Override
  public void sepia() {
    try {
      this.model.transformCurrentLayer("sepia");
    } catch (IllegalStateException e) {
      this.view.renderMessage(e.getMessage());
    }
    this.view.rerender();
  }

  @Override
  public void downscale() {
    try {
      double xScale = this.view.getDownscaleX();
      double yScale = this.view.getDownscaleY();

      this.model.downScale(xScale, yScale);
    } catch (IllegalArgumentException e) {
      this.view.renderMessage("Error: " + e.getMessage());
    }
    this.view.rerender();
  }

  @Override
  public void mosaic() {
    try {
      this.model.transformCurrentLayer("mosaic " + this.view.getSeeds());
    } catch (IllegalStateException e) {
      this.view.renderMessage(e.getMessage());
    }
    this.view.rerender();
  }

  @Override
  public void buildChecker() {
    try {
      int checkerSize = view.getCheckerSize();
      int rows = view.getCheckerRows();
      int columns = view.getCheckerColumns();
      Pixel p1 = new Pixel(view.getCheckerColor1());
      Pixel p2 = new Pixel(view.getCheckerColor2());
      this.model
          .addLayer(new CheckerboardLayerBuilder(checkerSize, rows, columns, p1, p2).build());
    } catch (IllegalStateException e) {
      this.view.renderMessage(e.getMessage());
    } catch (IllegalArgumentException e) {
      this.view.renderMessage("Failed to create checkerboard");
    }

    this.view.rerender();
  }

  @Override
  public void renameCurrentLayer() {
    try {
      String newName = this.view.getNewLayerName();
      ILayer l = this.model.getCurrentLayer();
      l.changeName(newName);
      this.view.rerender();
    } catch (IllegalStateException e) {
      this.view.renderMessage("Error: " + e.getMessage());
    }
  }

  @Override
  public void selectLayer(String layerName) {
    this.model.selectLayer(layerName);
    this.view.rerender();
  }

  @Override
  public void removeLayer() {
    try {
      this.model.removeLayer(this.model.getCurrentLayer().getName());
      this.view.rerender();
    } catch (IllegalStateException e) {
      this.view.renderMessage("Error: " + e.getMessage());
    }
  }

  @Override
  public void toggleLayerVisibility() {
    try {
      ILayer l = this.model.getCurrentLayer();
      l.hide();
      this.view.rerender();
    } catch (IllegalStateException e) {
      this.view.renderMessage(e.getMessage());
    }
  }

  @Override
  public void colorChooser1() {
    this.view.colorChooser1();
  }

  @Override
  public void colorChooser2() {
    this.view.colorChooser2();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String[] args = e.getActionCommand().split(" ");
    if (args.length == 1) {
      Action a = commands.getCommand(e.getActionCommand());
      if (a != null) {
        a.actionPerformed(e);
      }
    } else if (args.length > 1) {
      if (args[0].equals("select")) {
        String c = e.getActionCommand();
        this.selectLayer(c.substring(c.indexOf(" ") + 1));
        this.view.rerender();
      }
    }
  }
}
