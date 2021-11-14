package model.transformation.filter;

import org.junit.Test;

/**
 * Test class for AFilter constructor.
 */
public class AFilterConstructorTest {

  @Test(expected = IllegalArgumentException.class)
  public void TestNull() {
    new FilterTester(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNonSquare() {
    new FilterTester(new double[][]{{1, 1}, {1, 2}, {1, 2}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEvenNumElems() {
    new FilterTester(new double[][]{{1, 1}, {1, 1}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRagged() {
    new FilterTester(new double[][]{{1, 2, 4}, {1, 2, 3, 4, 3}, {1, 23, 4}});
  }

  /**
   * Mock class for testing the AFilter constructor.
   */
  class FilterTester extends AFilter {

    /**
     * Constructs a FilterTester.
     *
     * @param kernel the kernel that's passed to super for checking
     */
    public FilterTester(double[][] kernel) {
      super(kernel);
    }
  }
}
