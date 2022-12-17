package controller;

import model.IPictureModel;

/**
 * Command object that converts a Picture to greyscale.
 * Makes all r, g, b components have the same value based on
 * the given Greyscale type: ex. red-component, intensity-component, etc.
 */
public class Greyscale implements PictureCommand {

  /**
   * Represents greyscale types for converting Pictures to greyscale.
   * Can be based on red, green, blue, luma, intensity, or value component.
   */
  public enum Greytype {
    Red, Green, Blue, Value, Luma, Intensity
  }

  private final Greytype type;
  private final String pictureName;
  private final String saveName;

  /**
   * Constructs a greyscale command based on the given greyscale type.
   * Edits the Picture found with the given pictureName to the given greyscale type
   * and adds the picture to the model's Map as the given saveName.
   *
   * @param type Greyscale type for this Picture: ex. red-component, intensity-component
   * @param pictureName String name of the picture to be edited
   * @param saveName String name for this Picture to be stored as
   */
  public Greyscale(Greytype type, String pictureName, String saveName) {
    if (type == null || pictureName == null || saveName == null) {
      throw new IllegalArgumentException("Given greyscale type cannot be null!");
    }
    this.type = type;
    this.pictureName = pictureName;
    this.saveName = saveName;
  }

  @Override
  public void execute(IPictureModel model) {
    model.greyScale(this.pictureName, this.saveName, this.type);
  }
}
