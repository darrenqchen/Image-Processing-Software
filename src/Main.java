import controller.ControllerLIMEGUI;
import controller.ControllerLIMERegular;
import controller.IControllerLIME;
import java.io.FileNotFoundException;
import java.io.FileReader;
import model.IModelLIME;
import model.MultiLayerLIME;
import view.GUIView;

/**
 * The main class method.
 */
public class Main {

  /**
   * Runs the static main method in the jar file.
   *
   * @param args the arguments.
   */
  public static void main(String[] args) {

    if (args.length >= 2) {
      System.out.println("reading script file...");
      if (args[0].equals("-script")) {
        try {
          IModelLIME model = new MultiLayerLIME();
          new ControllerLIMERegular(new FileReader(args[1]), model).run();
        } catch (FileNotFoundException e) {
          System.out.println("No file found at: " + args[1]);
        }
      }
    } else {
      IModelLIME m = new MultiLayerLIME();
      GUIView view = new GUIView(m);
      IControllerLIME controller = new ControllerLIMEGUI(view, m);
      controller.run();
    }
  }


}
