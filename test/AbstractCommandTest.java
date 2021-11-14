import java.util.ArrayList;
import java.util.Collections;
import model.IModelLIME;
import model.MultiLayerLIME;
import model.layer.ILayer;
import model.layer.Layer;
import model.pixel.Pixel;
import org.junit.Before;

/**
 * Holds the variables for all the subclasses of AbstractCommandTest.
 */
public class AbstractCommandTest {

  protected Pixel redPixel;
  protected ILayer red1By1;
  protected IModelLIME emptyModel;
  protected IModelLIME nonEmptyModel;

  @Before
  public void init() {
    redPixel = new Pixel(255, 0, 0);
    red1By1 = new Layer(new Pixel[][]{
        {redPixel}}, "red1by1");
    emptyModel = new MultiLayerLIME();
    nonEmptyModel = new MultiLayerLIME(
        new ArrayList<ILayer>(Collections.singletonList(red1By1)));
  }
}