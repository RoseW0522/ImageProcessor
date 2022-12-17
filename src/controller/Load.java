package controller;

import model.IPictureModel;

/**
 * Represents the load Picture command. Loads the PPM file of the given name into the model.
 */
public class Load implements PictureCommand {

  private final String pictureName;
  private final String fileName;

  /**
   * Constructs a load command to load a PPM file of the given fileName into the model
   * with the given pictureName.
   *
   * @param pictureName String name of the Picture to be loaded into the model
   * @param fileName String name of the PPM file to be opened (DO NOT INCLUDE .ppm)
   * @throws IllegalArgumentException if any of the given arguments are null
   */
  public Load(String fileName, String pictureName) throws IllegalArgumentException {
    if (pictureName == null || fileName == null) {
      throw new IllegalArgumentException("Names cannot be null.");
    }

    this.pictureName = pictureName;
    this.fileName = fileName;
  }

  @Override
  public void execute(IPictureModel model) {
    model.load(this.fileName, this.pictureName);
  }
}
