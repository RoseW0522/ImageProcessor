package picture;

import java.util.HashMap;
import java.util.List;

/**
 * This interface includes the methods applied on Picture.
 * Also, it shows the state of Picture and manipulations on Picture.
 */
public interface IPicture {

  /**
   * Return the width of this Picture in pixels.
   *
   * @return the integer pixel width of this picture
   */
  int getWidth();

  /**
   * Return the height of this Picture in pixels.
   *
   * @return the integer pixel height of this picture
   */
  int getHeight();

  /**
   * Return the name of this Picture as a String.
   *
   * @return the String name of this Photo
   */
  String getName();

  /**
   * Return the IPixel at the given position in this Picture.
   *
   * @param row integer row of the pixel to be returned, starting at 0
   * @param col integer column of the pixel to be returned, starting at 0
   * @return the IPixel at this location
   * @throws IllegalArgumentException if the given position is out of this Picture's bounds
   */
  IPixel getPixelAt(int row, int col) throws IllegalArgumentException;

  /**
   * Returns a new IPicture that is this IPicture mirrored vertically.
   *
   * @return new IPicture that is the result of vertically mirroring this IPicture
   */
  IPicture mirrorVertical();

  /**
   * Returns a new IPicture that is this IPicture mirrored horizontally.
   *
   * @return new IPicture that is the result of horizontally mirroring this IPicture
   */
  IPicture mirrorHorizontal();

  /**
   * Returns a copy of this IPicture's Pixels as a List of List of IPixels.
   *
   * @return copy of this IPicture's pixels
   */
  List<List<IPixel>> getCopy();

  /**
   * Count the number of each value of red component in the picture.
   * @return a hash map contains each value as key and number as values
   */
  HashMap<Integer, Integer> redMap();

  /**
   * Count the number of each value of green component in the picture.
   * @return a hash map contains each value as key and number as values
   */
  HashMap<Integer, Integer> greenMap();

  /**
   * Count the number of each value of blue component in the picture.
   * @return a hash map contains each value as key and number as values
   */
  HashMap<Integer, Integer> blueMap();
}
