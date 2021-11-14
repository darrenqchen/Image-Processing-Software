package view;

import static org.junit.Assert.assertEquals;

import controller.MockController;
import java.awt.Color;
import java.io.File;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the GUIView class methods.
 */
public class GUIViewTest {

  Appendable out;
  ILayerGUIView view;

  @Before
  public void init() {
    this.out = new StringBuilder();
    this.view = new MockView(this.out);
  }

  @Test
  public void colorChooser1Test() {
    this.view.colorChooser1();
    assertEquals("color prompt 1", this.out.toString());
  }

  @Test
  public void colorChooser2Test() {
    this.view.colorChooser2();
    assertEquals("color prompt 2", this.out.toString());
  }

  @Test
  public void getCheckerColor1Test() {
    this.view.getCheckerColor1();
    assertEquals(new Color(0, 0, 0), this.view.getCheckerColor1());
  }

  @Test
  public void getCheckerColor2Test() {
    this.view.getCheckerColor2();
    assertEquals(new Color( 255, 255, 255), this.view.getCheckerColor2());
  }

  @Test
  public void getDownscaleXTest() {
    assertEquals(1, this.view.getDownscaleX(), .01);
  }

  @Test
  public void getDownscaleYTest() {
    this.view.getCheckerColor2();
    assertEquals(1, this.view.getDownscaleY(), .01);
  }

  @Test
  public void getCheckerSizeTest() {
    this.view.getCheckerSize();
    assertEquals(5, this.view.getCheckerSize());
  }

  @Test
  public void getCheckerRowsTest() {
    this.view.getCheckerRows();
    assertEquals(5, this.view.getCheckerRows());
  }

  @Test
  public void getCheckerColumnsTest() {
    this.view.getCheckerColumns();
    assertEquals(5, this.view.getCheckerColumns());
  }

  @Test
  public void getFileImageTest() {
    this.view.getFileImage();
    assertEquals(new File("test/controller/testFileImage.png"), this.view.getFileImage());
  }

  @Test
  public void getFolderImageTest() {
    this.view.getFolderImage();
    assertEquals(new File("test/controller/testFolderImage.png"), this.view.getFolderImage());
  }

  @Test
  public void getNewLayerName() {
    assertEquals("new Layer", this.view.getNewLayerName());
  }

  @Test
  public void getSaveFileTest() {
    this.view.getSaveFile();
    assertEquals(new File("test/controller/testSaveFile.png"), this.view.getSaveFile());
  }

  @Test
  public void getSaveFolderTest() {
    this.view.getSaveFolder();
    assertEquals(new File("test/controller/testSaveFolder/"), this.view.getSaveFolder());
  }

  @Test
  public void getSeedsTest() {
    this.view.getSeeds();
    assertEquals(600, this.view.getSeeds());
  }

  @Test
  public void renderMessageTest() {
    this.view.renderMessage("Message");
    assertEquals("Message", this.out.toString());
  }

  @Test
  public void rerenderTest() {
    this.view.rerender();
    assertEquals("Rerendered", this.out.toString());
  }

  @Test
  public void setActionListenersTest() {
    this.view.setActionListeners(new MockController(new StringBuilder()));
    assertEquals("Setting action listeners", this.out.toString());
  }

  @Test
  public void setActionCommandsTest() {
    this.view.setActionCommands();
    assertEquals("Setting action commands", this.out.toString());
  }

  @Test
  public void setVisibilityTrue() {
    this.view.setVisibility(true);
    assertEquals("Visible", this.out.toString());
  }

  @Test
  public void setVisibilityFalse() {
    this.view.setVisibility(false);
    assertEquals("Hidden", this.out.toString());
  }

  @Test
  public void setDefaultCloseOperationTest() {
    this.view.setDefaultCloseOperation();
    assertEquals("default close", this.out.toString());
  }

}