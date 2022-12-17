package controller;

import model.IPictureModel;

/**
 * Command object that mirrors a Picture either vertically, horizontally, or both.
 * Type of flip is based on the given Fliptype.
 */
public class Flip implements PictureCommand {

  /**
   * Enum to represent the three types of image mirroring:
   * Vertical mirroring, Horizontal mirroring, or both.
   */
  public enum Fliptype {
    Vertical, Horizontal, Both
  }

  private final Fliptype type;
  private final String pictureName;
  private final String saveName;

  /**
   * Constructs a flip command based on the given fliptype type.
   * Edits the Picture found with the given pictureName to the new picture name.
   *
   * @param type        the type of flip
   * @param pictureName the given picture name
   * @param saveName    the new picture name need to save.
   */
  public Flip(Fliptype type, String pictureName, String saveName) {
    if (type == null || pictureName == null || saveName == null) {
      throw new IllegalArgumentException("Fliptype and picture names cannot be null!");
    }
    this.type = type;
    this.pictureName = pictureName;
    this.saveName = saveName;
  }

  @Override
  public void execute(IPictureModel model) {
    switch (this.type) {
      case Vertical:
        model.flipVertical(this.pictureName, this.saveName);
        break;
      case Horizontal:
        model.flipHorizontal(this.pictureName, this.saveName);
        break;
      case Both:
        model.flipBoth(this.pictureName, this.saveName);
        break;
      default:
        throw new IllegalArgumentException("Invalid Flip type!");
    }
  }
}
