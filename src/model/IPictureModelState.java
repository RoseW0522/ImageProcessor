package model;

import picture.IPicture;
import picture.IPixel;

/**
 * This interface includes the methods to show the state of picture image.
 */
public interface IPictureModelState {

  /**
   * Return the width of the current photo in pixels.
   *
   * @return the width as an integer
   */
  int getWidth(String fileName);

  /**
   * Return the height of the current photo in pixels.
   *
   * @return the height as an integer
   */
  int getHeight(String fileName);

  /**
   * Return the pixel at the given position in the current photo.
   *
   * @param row the row of the pixel, starting at 0
   * @param col the column of the pixel, starting at 0
   * @return the Pixel at the given position
   * @throws IllegalArgumentException if the given position is outside the photo bounds
   */
  IPixel getPixelAt(String fileName, int row, int col) throws IllegalArgumentException;

  /**
   * Returns the IPicture of the given name if it exists in this map of IPictures.
   *
   * @param picName String name of the IPicture to be returned
   * @return the IPicture of the given name if it exists in this map of IPictures
   * @throws IllegalArgumentException if the given string is null or the IPicture is not found
   */
  IPicture getPicture(String picName) throws IllegalArgumentException;
}
