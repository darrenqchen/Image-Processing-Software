import model.layer.ILayer;
import model.layer.Layer;
import model.pixel.Pixel;
import org.junit.Before;

/**
 * Creates the base example variables inside for the classes that implement ITransformation.
 */
public abstract class ATransformationTest {

  protected Pixel whitePixel;
  protected Pixel redPixel;
  protected Pixel greenPixel;
  protected Pixel bluePixel;
  protected Pixel blackPixel;
  protected ILayer image1;
  protected ILayer black2By2;
  protected ILayer white2By2;
  protected ILayer black4By4;


  @Before
  public void init() {
    whitePixel = new Pixel(255, 255, 255);
    redPixel = new Pixel(255, 0, 0);
    greenPixel = new Pixel(0, 255, 0);
    bluePixel = new Pixel(0, 0, 255);
    blackPixel = new Pixel(0, 0, 0);
    image1 = new Layer(new Pixel[][]{
        {whitePixel, redPixel},
        {greenPixel, bluePixel}});
    // this (using the same reference) is ok because we aren't altering pixels
    black2By2 = new Layer(new Pixel[][]{
        {blackPixel, blackPixel},
        {blackPixel, blackPixel}});
    white2By2 = new Layer(new Pixel[][]{
        {whitePixel, whitePixel},
        {whitePixel, whitePixel}});
    black4By4 = new Layer(new Pixel[][]{
        {blackPixel, blackPixel, blackPixel, blackPixel},
        {blackPixel, blackPixel, blackPixel, blackPixel},
        {blackPixel, blackPixel, blackPixel, blackPixel},
        {blackPixel, blackPixel, blackPixel, blackPixel}});
  }
}
