package controller.actioncommand;

import controller.Features;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;

/**
 * Creates an ActionMap for the all the different events that will happen when an event occurs.
 */
public class ActionMapCommand extends ActionMap implements ActionCommand {

  private final List<Features> featuresList;

  /**
   * Creates the {@code ActionMap} map where all the different actions are added already.
   */
  public ActionMapCommand() {
    featuresList = new ArrayList<>();

    this.put("loadButton", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featuresList) {
          f.load();
        }
      }
    });

    this.put("loadAllButton", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featuresList) {
          f.loadAll();
        }
      }
    });

    this.put("saveAllButton", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featuresList) {
          f.saveAll();
        }
      }
    });

    this.put("saveCurrentLayerButton", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featuresList) {
          f.saveCurrentLayer();
        }
      }
    });

    this.put("blurButton", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featuresList) {
          f.blur();
        }
      }
    });

    this.put("sharpenButton", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featuresList) {
          f.sharpen();
        }
      }
    });

    this.put("greyscaleButton", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featuresList) {
          f.greyscale();
        }
      }
    });

    this.put("sepiaButton", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featuresList) {
          f.sepia();
        }
      }
    });

    this.put("mosaicButton", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featuresList) {
          f.mosaic();
        }
      }
    });

    this.put("downscaleButton", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featuresList) {
          f.downscale();
        }
      }
    });

    this.put("buildCheckerPattern", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featuresList) {
          f.buildChecker();
        }
      }
    });

    this.put("toggleVis", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featuresList) {
          f.toggleLayerVisibility();
        }
      }
    });

    this.put("remove", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featuresList) {
          f.removeLayer();
        }
      }
    });

    this.put("rename", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featuresList) {
          f.renameCurrentLayer();
        }
      }
    });

    this.put("colorChooser1", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featuresList) {
          f.colorChooser1();
        }
      }
    });

    this.put("colorChooser2", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featuresList) {
          f.colorChooser2();
        }
      }
    });
  }

  @Override
  public void addFeature(Features f) {
    if (f == null) {
      throw new IllegalArgumentException("Feature cannot be null.");
    }
    this.featuresList.add(f);
  }

  @Override
  public Action getCommand(String command) {
    if (command == null) {
      throw new IllegalArgumentException("Command cannot be null.");
    }
    return this.get(command);
  }
}
