package view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Represents the interface for the GUI view in the Image Processor program.
 */
public interface ILayerGUIView {

  /**
   * Shows the display to choose the first checker color.
   */
  void colorChooser1();

  /**
   * Shows the display to choose the second checker color.
   */
  void colorChooser2();

  /**
   * Gets the current color value of the first color picker and returns it.
   *
   * @return the java.awt.Color of the color picker.
   */
  Color getCheckerColor1();

  /**
   * Gets the current color value of the second color picker and returns it.
   *
   * @return the java.awt.Color of the color picker.
   */
  Color getCheckerColor2();

  /**
   * Gets the current value of the downscaleX text field as a double.
   *
   * @return the value as a double.
   * @throws IllegalStateException if the value is not a valid double.
   */
  double getDownscaleX() throws IllegalStateException;

  /**
   * Gets the current value of the downscaleY text field as a double.
   *
   * @return the value as a double.
   * @throws IllegalStateException if the value is not a valid double.
   */
  double getDownscaleY() throws IllegalStateException;

  /**
   * Gets the checker size.
   *
   * @return return the size of each checker square.
   * @throws IllegalStateException if it's not a number.
   */
  int getCheckerSize() throws IllegalStateException;

  /**
   * Gets the checker row.
   *
   * @return return the number of rows for the checkerboard.
   * @throws IllegalStateException if it's not a number.
   */
  int getCheckerRows() throws IllegalStateException;

  /**
   * Gets the checker columns.
   *
   * @return return the number of columns for the checkerboard.
   * @throws IllegalStateException if it's not a number.
   */
  int getCheckerColumns() throws IllegalStateException;

  /**
   * Gets the file for the image in jpg, ppm, or png.
   *
   * @throws IllegalStateException if you can't get the file.
   */
  File getFileImage() throws IllegalStateException;

  /**
   * Gets the folder for the images in jpg, ppm, or png.
   *
   * @throws IllegalStateException if you can't get the folder.
   */
  File getFolderImage() throws IllegalStateException;

  /**
   * Returns the text value of the new layer field.
   *
   * @return the value of the new layer field.
   */
  String getNewLayerName();

  /**
   * Gets the file of the place to save.
   *
   * @return the file.
   * @throws IllegalStateException if you can't get the file place.
   */
  File getSaveFile() throws IllegalStateException;

  /**
   * Gets the folder of the place to save.
   *
   * @return the folder directory.
   * @throws IllegalStateException if you can't get the folder place.
   */
  File getSaveFolder() throws IllegalStateException;

  /**
   * Gets the number of seeds in the mosaic text field.
   *
   * @return number of seeds to use.
   * @throws IllegalStateException if you can't get the seeds.
   */
  int getSeeds() throws IllegalStateException;

  /**
   * Renders the message on the screen.
   *
   * @param message the message to be rendered.
   */
  void renderMessage(String message);

  /**
   * Re-renders the view, to show newly updated material, and new components.
   */
  void rerender();

  /**
   * Sets all the buttons action listeners to the given Action listeners.
   *
   * @param e the Action Listener.
   */
  void setActionListeners(ActionListener e) throws IllegalArgumentException;

  /**
   * Sets all the buttons action listeners to the given Action listeners.
   */
  void setActionCommands() throws IllegalArgumentException;

  /**
   * Changes the visibility of the GUI.
   *
   * @param visibility whether to show or hide the GUI.
   */
  void setVisibility(boolean visibility);

  /**
   * Sets the GUI to close automatically on close.
   */
  void setDefaultCloseOperation();
}


