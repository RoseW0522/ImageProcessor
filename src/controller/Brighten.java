package controller;

import model.IPictureModel;

/**
 * Command object that Brightens a Picture by raising or lowering all r, g, b values
 * of its pixels by the given delta.
 * Pixels cannot exceed the cap value or be less than 0.
 */
public class Brighten implements PictureCommand {

  private final int delta;
  private final String picName;
  private final String newName;

  /**
   * Instantiate the picture by making picture brighter or darker by delta.
   * @param delta the changing value in all pixel in picture
   * @param picName String name of the PPM file to be opened (DO NOT INCLUDE .ppm)
   * @param newName the filename need to save into picName
   */
  public Brighten(int delta, String picName, String newName) {
    if (picName == null || newName == null) {
      throw new IllegalArgumentException("Picture names cannot be null!");
    }
    this.delta = delta;
    this.picName = picName;
    this.newName = newName;
  }

  @Override
  public void execute(IPictureModel model) {
    model.brighten(this.picName, this.newName, this.delta);
  }
}
