package picture;

/**
 * This interface includes the methods applied on the pixel.
 * It calculates different value base on the pixel.
 */
public interface IPixel {

  /**
   * Returns the value of this pixel, which is the greatest value
   * of its individual r, g, and b values.
   *
   * @return the value of this pixel: greatest of r, g, b
   */
  int value();

  /**
   * Returns the intensity of this pixel, which is the average of
   * its three r, g, b components.
   *
   * @return the intensity of this pixel: average of r, g, b
   */
  double intensity();

  /**
   * Returns the luma of this pixel, found via the formula:
   * 0.2126r + 0.7152g + 0.0722b.
   *
   * @return the calculated luma of this pixel
   */
  double luma();

  /**
   * Returns the amount of red in this Pixel, or the r value.
   *
   * @return the r value of this Pixel, how much red is in its color
   */
  int getR();

  /**
   * Returns the amount of green in this Pixel, or the g value.
   *
   * @return the g value of this Pixel, how much green is in its color
   */
  int getG();

  /**
   * Returns the amount of blue in this Pixel, or the b value.
   *
   * @return the b value of this Pixel, how much blue is in its color
   */
  int getB();

  /**
   * Returns the maximum value this Pixel can hold for one color, or its cap value.
   *
   * @return the cap value of this Pixel, the highest value it can hold
   */
  int getCap();

  /**
   * Changes the r, g, b values of this pixel by the given amounts.
   * These values cannot go below 0 or above cap value.
   *
   * @param r integer amount to change r value by (can be negative)
   * @param g integer amount to change g value by (can be negative)
   * @param b integer amount to change b value by (can be negative)
   */
  void editPixel(int r, int g, int b);
}
