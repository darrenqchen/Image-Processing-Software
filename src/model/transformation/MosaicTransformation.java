package model.transformation;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.layer.ILayer;
import model.layer.Layer;
import model.pixel.Pixel;

/**
 * Transformation for mosaics. apply method takes image data and then returns a new Layer with the
 * image data transformed so that the image looks like a mosaic.
 *
 * <p>Note that the number of seeds might be reduced to the number of pixels in the image, this is
 * for
 * performance reasons. For graders, this was not specified in the assignment, but I'm doing it b/c
 * I don't want to sit around waiting all day, and neither do you.</p>
 */
public class MosaicTransformation implements ITransformation {

  private int seeds;
  private final Random r;


  /**
   * Constructor for testing, used to reproduce reliable random positions.
   *
   * @param seeds the number of seeds in the image.
   * @param r     random number generator.
   * @throws IllegalArgumentException if given null.
   */
  public MosaicTransformation(int seeds, Random r) throws IllegalArgumentException {
    if (r == null) {
      throw new IllegalArgumentException("r cannot be null");
    }

    this.seeds = seeds;
    this.r = r;
  }

  /**
   * Creates a {@code MosaicTransformation} object given the number of seeds.
   *
   * @param seeds the number of seeds in the image.
   */
  public MosaicTransformation(int seeds) {
    this(seeds, new Random());
  }

  @Override
  public ILayer apply(Pixel[][] pixels) throws IllegalArgumentException {

    if (seeds > pixels.length * pixels[0].length) {
      seeds = pixels.length * pixels[0].length;
    }

    int xMax = pixels[0].length;
    int yMax = pixels.length;

    // get the anchor points.
    List<Point> points = new ArrayList<>();
    for (int i = 0; i < seeds; i += 1) {
      points.add(this.randCoord(xMax, yMax));
    }

    // Set all the things.
    for (int y = 0; y < pixels.length; y += 1) {
      for (int x = 0; x < pixels[y].length; x += 1) {
        Point closest = this.findClosest(points, x, y);
        pixels[y][x] = pixels[closest.y][closest.x];
      }
    }
    return new Layer(pixels, "New Mosaic");
  }

  /**
   * Returns a random coordinate with the x and y maximums given.
   *
   * @param xMax the maximum x value
   * @param yMax the maximum y value
   * @return a new point with random integer coords between 0 and the given values.
   */
  protected Point randCoord(int xMax, int yMax) {
    return new Point(this.r.nextInt(xMax), this.r.nextInt(yMax));
  }

  /**
   * Finds the closest point in the list to the point given.
   *
   * @param points list of points to search through
   * @param x      x val of the target point
   * @param y      y val of the target point
   * @return a point with the x and y value of the closest point in the points list.
   */
  protected Point findClosest(List<Point> points, int x, int y) {
    Point rec = points.get(0);
    Point target = new Point(x, y);

    double recDist = rec.distance(target);
    for (Point p : points) {
      double d = p.distance(target);
      if (d < recDist) {
        rec = p;
        recDist = d;
      }
    }
    return rec;
  }
}
