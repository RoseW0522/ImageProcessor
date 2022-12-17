package controller;

import java.io.IOException;

import model.IPictureModel;

/**
 * Represents the save Picture command.
 * Saves the Picture of the given name to this device as a PPM file.
 */
public class Save implements PictureCommand {

  private final String pictureName;
  private final String saveName;

  /**
   * Constructs a save command to save the Picture of the given name
   * as the given saveName.
   * Saves as a PPM file.
   *
   * @param pictureName String name of the Picture to be saved to this device
   * @param saveName String name for this Picture to be saved as
   * @throws IllegalArgumentException if any arguments are null
   */
  public Save(String saveName, String pictureName) throws IllegalArgumentException {
    if (pictureName == null || saveName == null) {
      throw new IllegalArgumentException("Names cannot be null!");
    }
    this.pictureName = pictureName;
    this.saveName = saveName;
  }

  @Override
  public void execute(IPictureModel model) throws IOException {
    model.save(this.saveName, this.pictureName);
  }
}
