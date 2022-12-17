package model;

import java.io.IOException;

import controller.Greyscale;
import picture.IPicture;

/**
 * This interface includes the methods applied on Picture.
 *
 */
public interface IPictureModel extends IPictureModelState {

  /**
   * Loads the given file into the model, if found.
   * If not found, throws an IllegalArgumentException.
   *
   * @param fileName String name of the file to be loaded
   * @throws IllegalArgumentException if the given file name is not found
   */
  public void load(String fileName, String picName) throws IllegalArgumentException;

  /**
   * Saves the given Picture to this device as a .ppm file with the given file name.
   * @param fileName the given file name need to save the picName.
   * @param picName String name of the file to be saved.
   */
  void save(String fileName, String picName) throws IllegalArgumentException, IOException;

  /**
   * Flips the given picture horizontally.
   * @param picName String name of the file to be saved.
   * @param newName String name of the new picture to be added to the Map
   */
  void flipHorizontal(String picName, String newName);

  /**
   * Flips the given picture vertically.
   * @param picName String name of the file to be saved.
   * @param newName String name of the new picture to be added to the Map
   */
  void flipVertical(String picName, String newName);

  /**
   * Flips the given picture horizontally and vertically.
   * @param picName String name of the file to be saved
   * @param newName String name of the new picture to be added to the Map
   */
  void flipBoth(String picName, String newName);

  /**
   * Add the given IPicture of the given String name to the Map of Pictures in this model.
   *
   * @param name String name of the Picture to be added
   * @param picture IPicture to be added
   * @throws IllegalArgumentException if the given IPicture or String is null
   */
  void add(String name, IPicture picture) throws IllegalArgumentException;

  /**
   * Remove the IPicture of the given name from the Map of Pictures in this model.
   *
   * @param name String name of the Picture to be removed
   * @throws IllegalArgumentException if the given String name is not found
   */
  void remove(String name) throws IllegalArgumentException;

  /**
   * Adds a greyscale Picture of the picture that corresponds to the given name
   * of the given Greytype to the Map of IPictures with the given newName.
   * Throws IllegalArgumentException if the type is invalid or names are null.
   *
   * @param picName String name of the picture to be converted to greyscale
   * @param newName String name of the new picture to be added to the Map
   * @param type Greyscale type: ex. red-component, blue-component, etc
   * @throws IllegalArgumentException if any arguments are null or the type is not recognized
   */
  void greyScale(String picName, String newName, Greyscale.Greytype type)
          throws IllegalArgumentException;

  /**
   * Adds a copy of this Picture with each individual pixel value changed by the given delta.
   * If adding the delta to any value exceeds that pixel's cap or is less than 0,
   * sets the value to the cap or 0 respectively.
   *
   * @param picName String name of the picture to be edited
   * @param newName String name of the new picture to be added to the map
   * @param delta integer value to brighten the picture by (can be negative)
   * @throws IllegalArgumentException if either String is null or if the given picture is not found
   */
  void brighten(String picName, String newName, int delta) throws IllegalArgumentException;
}
