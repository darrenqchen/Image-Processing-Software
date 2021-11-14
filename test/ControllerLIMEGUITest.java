import static org.junit.Assert.assertEquals;

import controller.Features;
import controller.IControllerLIME;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the ControllerLimeGUITest class methods.
 */
public class ControllerLIMEGUITest {

  Appendable out;
  IControllerLIME controller;
  Features controllerFeatures;
  ActionListener controllerListener;


  @Before
  public void init() {
    this.out = new StringBuilder();
    this.controller = new MockController(out);
    this.controllerFeatures = (Features) this.controller;
    this.controllerListener = (ActionListener) this.controller;
  }

  @Test
  public void loadTest() {
    this.controllerFeatures.load();
    assertEquals("Loading", this.out.toString());
  }

  @Test
  public void loadAllTest() {
    this.controllerFeatures.loadAll();
    assertEquals("Loading All", this.out.toString());
  }

  @Test
  public void saveCurrentLayerTest() {
    this.controllerFeatures.saveCurrentLayer();
    assertEquals("Saving current layer", this.out.toString());
  }

  @Test
  public void saveAllTest() {
    this.controllerFeatures.saveAll();
    assertEquals("Saving all", this.out.toString());
  }

  @Test
  public void blurTest() {
    this.controllerFeatures.blur();
    assertEquals("Blurring", this.out.toString());
  }

  @Test
  public void sharpenTest() {
    this.controllerFeatures.sharpen();
    assertEquals("Sharpening", this.out.toString());
  }

  @Test
  public void greyscaleTest() {
    this.controllerFeatures.greyscale();
    assertEquals("Greyscaling", this.out.toString());
  }

  @Test
  public void sepiaTest() {
    this.controllerFeatures.sepia();
    assertEquals("Sepia-ing", this.out.toString());
  }

  @Test
  public void downscaleTest() {
    this.controllerFeatures.downscale();
    assertEquals("Downscaling", this.out.toString());
  }

  @Test
  public void mosaicTest() {
    this.controllerFeatures.mosaic();
    assertEquals("Mosaic-ing", this.out.toString());
  }

  @Test
  public void buildCheckerTest() {
    this.controllerFeatures.buildChecker();
    assertEquals("Building the checker", this.out.toString());
  }

  @Test
  public void renameCurrentLayerTest() {
    this.controllerFeatures.renameCurrentLayer();
    assertEquals("Renaming current layer", this.out.toString());
  }

  @Test
  public void selectLayerTest() {
    this.controllerFeatures.selectLayer("Something");
    assertEquals("Selecting the layer", this.out.toString());
  }

  @Test
  public void removeLayerTest() {
    this.controllerFeatures.removeLayer();
    assertEquals("Removing the layer", this.out.toString());
  }

  @Test
  public void testToggleLayerVisibility() {
    this.controllerFeatures.toggleLayerVisibility();
    assertEquals("Toggling layer visibility", this.out.toString());
  }

  @Test
  public void testColorChooser1() {
    this.controllerFeatures.colorChooser1();
    assertEquals("Choosing color 1", this.out.toString());
  }

  @Test
  public void testColorChooser2() {
    this.controllerFeatures.colorChooser2();
    assertEquals("Choosing color 2", this.out.toString());
  }

  @Test
  public void actionPerformedTest() {
    this.controllerListener.actionPerformed(new ActionEvent("String", 1, "String"));
    assertEquals("Action eventing", this.out.toString());
  }
}