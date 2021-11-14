package model.pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Pixel.
 */
public class PixelTest {

  private Pixel whitePixel;
  private Pixel whitePixel2;
  private Pixel redPixel;
  private Pixel greenPixel;
  private Pixel bluePixel;
  private Pixel blackPixel;

  @Before
  public void init() {
    whitePixel = new Pixel(255, 255, 255);
    whitePixel2 = new Pixel(255, 255, 255);
    redPixel = new Pixel(255, 0, 0);
    greenPixel = new Pixel(0, 255, 0);
    bluePixel = new Pixel(0, 0, 255);
    blackPixel = new Pixel(0, 0, 0);
  }

  @Test
  public void getRed() {
    assertEquals(255, whitePixel.getRed());
    assertEquals(255, redPixel.getRed());
    assertEquals(0, greenPixel.getRed());
    assertEquals(0, bluePixel.getRed());
    assertEquals(0, blackPixel.getRed());
  }

  @Test
  public void getGreen() {
    assertEquals(255, whitePixel.getGreen());
    assertEquals(0, redPixel.getGreen());
    assertEquals(255, greenPixel.getGreen());
    assertEquals(0, bluePixel.getGreen());
    assertEquals(0, blackPixel.getGreen());
  }

  @Test
  public void getBlue() {
    assertEquals(255, whitePixel.getBlue());
    assertEquals(0, redPixel.getBlue());
    assertEquals(0, greenPixel.getBlue());
    assertEquals(255, bluePixel.getBlue());
    assertEquals(0, blackPixel.getBlue());
  }

  @Test
  public void testEquals() {
    assertEquals(whitePixel, whitePixel2);
    assertEquals(whitePixel, whitePixel2);
    assertNotEquals(whitePixel, redPixel);
    assertNotEquals(whitePixel, greenPixel);
    assertNotEquals(whitePixel, bluePixel);
    assertNotEquals(whitePixel, blackPixel);
  }

  @Test
  public void testHashCode() {
    assertEquals(283006, whitePixel.hashCode());
    assertEquals(274846, redPixel.hashCode());
    assertEquals(37696, greenPixel.hashCode());
    assertEquals(30046, bluePixel.hashCode());
    assertEquals(29791, blackPixel.hashCode());
  }
}