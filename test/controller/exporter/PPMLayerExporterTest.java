package controller.exporter;

import static org.junit.Assert.assertEquals;

import controller.layerbuilder.CheckerboardLayerBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import model.layer.ILayer;
import model.pixel.Pixel;
import org.junit.Test;


/**
 * Test class for PPMImageExporter methods.
 */
public class PPMLayerExporterTest {

  @Test
  public void exportTest() {
    ILayer img = new CheckerboardLayerBuilder(1, 1, 1, new Pixel(255, 0, 0), new Pixel(0, 0, 0))
        .build();

    img.exportImage(new PPMLayerExporter(), "test/exportTest01");

    try {
      Scanner reader = new Scanner(new FileReader("test/exportTest01.ppm"));
      String str = "";
      while (reader.hasNextLine()) {
        str += reader.nextLine() + "\n";
      }
      assertEquals("P3\n1 1\n255\n255 0 0\t\n", str);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found");
    }
  }
}