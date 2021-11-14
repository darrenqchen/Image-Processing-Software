package view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Creates a mock for the View to pretend to do things.
 */
public class MockView implements ILayerGUIView {

  Appendable out;

  /**
   * Creates a MockView object given an Appendable.
   *
   * @param out the Appendable.
   */
  public MockView(Appendable out) {
    if (out == null) {
      throw new IllegalArgumentException("Parameter cannot be null.");
    }
    this.out = out;
  }

  @Override
  public void colorChooser1() {
    try {
      this.out.append("color prompt 1");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void colorChooser2() {
    try {
      this.out.append("color prompt 2");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public Color getCheckerColor1() {
    return new Color(0, 0, 0);
  }

  @Override
  public Color getCheckerColor2() {
    return new Color(255, 255, 255);
  }

  @Override
  public double getDownscaleX() throws IllegalStateException {
    return 1;
  }

  @Override
  public double getDownscaleY() throws IllegalStateException {
    return 1;
  }

  @Override
  public int getCheckerSize() throws IllegalStateException {
    return 5;
  }

  @Override
  public int getCheckerRows() throws IllegalStateException {
    return 5;
  }

  @Override
  public int getCheckerColumns() throws IllegalStateException {
    return 5;
  }

  @Override
  public File getFileImage() throws IllegalStateException {
    return new File("test/controller/testFileImage.png");
  }

  @Override
  public File getFolderImage() throws IllegalStateException {
    return new File("test/controller/testFolderImage.png");
  }

  @Override
  public String getNewLayerName() {
    return "new Layer";
  }

  @Override
  public File getSaveFile() throws IllegalStateException {
    return new File("test/controller/testSaveFile.png");
  }

  @Override
  public File getSaveFolder() throws IllegalStateException {
    return new File("test/controller/testSaveFolder/");
  }

  @Override
  public int getSeeds() throws IllegalStateException {
    return 600;
  }

  @Override
  public void renderMessage(String message) {
    try {
      this.out.append(message);
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void rerender() {
    try {
      this.out.append("Rerendered");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void setActionListeners(ActionListener e) throws IllegalArgumentException {
    try {
      this.out.append("Setting action listeners");
    } catch (IOException f) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void setActionCommands() throws IllegalArgumentException {
    try {
      this.out.append("Setting action commands");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void setVisibility(boolean visibility) {
    try {
      this.out.append(visibility ? "Visible" : "Hidden");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }

  @Override
  public void setDefaultCloseOperation() {
    try {
      this.out.append("default close");
    } catch (IOException e) {
      throw new IllegalStateException("Can't append, check yourself before you wreck yourself");
    }
  }
}
