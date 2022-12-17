package picture;

/**
 * Represents a single RGB Pixel with a value of red, blue, and green.
 * Also has a cap, which no value of red, green, or blue can exceed.
 */
public class RGBPixel implements IPixel {
  private int r;
  private int g;
  private int b;
  private final int cap; //Highest value r, g, and b can hold

  /**
   * Represents the constructor of RGBPixel which contains the value of red, green, and blue.
   * @param r the value of color red
   * @param g the value of color green
   * @param b the value of color blue
   * @param cap the highest value of color to limit the maximum of r, g, b
   */
  public RGBPixel(int r, int g, int b, int cap) {
    if (r > cap || g > cap || b > cap) {
      throw new IllegalArgumentException("Color value exceeds the cap.");
    }
    else if (r < 0 || g < 0 || b < 0) {
      throw new IllegalArgumentException("Color value cannot be negative.");
    }

    this.r = r;
    this.g = g;
    this.b = b;
    this.cap = cap;
  }

  @Override
  public int value() {
    return (Math.max(this.r, (Math.max(this.g, this.b))));
  }

  @Override
  public double intensity() {
    return (this.r + this.b + this.g) / 3.0;
  }

  @Override
  public double luma() {
    return (0.2126 * this.r) + (0.7152 * this.g) + (0.0722 * this.b);
  }

  @Override
  public int getR() {
    return this.r;
  }

  @Override
  public int getG() {
    return this.g;
  }

  @Override
  public int getB() {
    return this.b;
  }

  @Override
  public int getCap() {
    return this.cap;
  }

  @Override
  public void editPixel(int r, int g, int b) {
    int newR = this.editValue(this.r, r);
    int newG = this.editValue(this.g, g);
    int newB = this.editValue(this.b, b);
    this.r = newR;
    this.g = newG;
    this.b = newB;
  }

  /**
   * Returns the value of a single r, g, or b value based on the current value and delta.
   * If the sum is higher than the cap, returns the cap.
   * If the sum is lower than 0, returns 0.
   * Else returns the sum.
   *
   * @param current current value
   * @param delta number to change the current value by (can be negative)
   * @return the new value
   */
  private int editValue(int current, int delta) {
    if (current + delta > this.cap) {
      return this.cap;
    }
    else {
      return Math.max(current + delta, 0);
    }
  }

  @Override
  public boolean equals(Object pixel) {
    if (pixel.getClass() == this.getClass()) {
      RGBPixel p = (RGBPixel) pixel;
      return (p.getR() == this.r && p.getG() == this.g
              && p.getB() == this.b && p.getCap() == this.cap);
    }
    else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return (this.b) + (256 * this.g) + (65536 * this.r);
  }
}
