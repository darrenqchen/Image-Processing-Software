import controller.Features;
import controller.IControllerLIME;
import controller.actioncommand.ActionCommand;
import controller.actioncommand.ActionMapCommand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Creates a mock for the Controller to pretend to do things.
 */
public class MockController implements IControllerLIME, Features, ActionListener {

  Appendable out;
  ActionCommand commands;

  /**
   * Creates a MockController object given an Appendable.
   *
   * @param out the Appendable.
   */
  public MockController(Appendable out) {
    if (out == null) {
      throw new IllegalArgumentException("Parameters cannot be null.");
    }
    this.out = out;
    this.commands = new ActionMapCommand();
    this.commands.addFeature(this);
  }

  @Override
  public void run() {
    try {
      this.out.append("Running");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void load() {
    try {
      this.out.append("Loading");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void loadAll() {
    try {
      this.out.append("Loading All");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void saveCurrentLayer() {
    try {
      this.out.append("Saving current layer");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void saveAll() {
    try {
      this.out.append("Saving all");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void blur() {
    try {
      this.out.append("Blurring");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void sharpen() {
    try {
      this.out.append("Sharpening");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void greyscale() {
    try {
      this.out.append("Greyscaling");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void sepia() {
    try {
      this.out.append("Sepia-ing");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void downscale() {
    try {
      this.out.append("Downscaling");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void mosaic() {
    try {
      this.out.append("Mosaic-ing");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void buildChecker() {
    try {
      this.out.append("Building the checker");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void renameCurrentLayer() {
    try {
      this.out.append("Renaming current layer");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void selectLayer(String layerName) {
    try {
      this.out.append("Selecting the layer");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void removeLayer() {
    try {
      this.out.append("Removing the layer");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void toggleLayerVisibility() {
    try {
      this.out.append("Toggling layer visibility");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void colorChooser1() {
    try {
      this.out.append("Choosing color 1");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void colorChooser2() {
    try {
      this.out.append("Choosing color 2");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      this.out.append("Action eventing");
    } catch (IOException f) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }
}
