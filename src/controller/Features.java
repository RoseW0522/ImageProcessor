package controller;

import view.IPictureView;

/**
 * Represents the features of the Picture Editing program that can be performed by
 * interacting with components of the GUI.
 */
public interface Features {

  /**
   * Set the view for the controller. This begins the picture editing program.
   *
   * @param view IPictureView for the controller to display
   * @throws IllegalArgumentException if the given view is null
   */
  void setView(IPictureView view) throws IllegalArgumentException;

  /**
   * Exit the program. This properly exits down the program.
   */
  void exitProgram();

  /**
   * Performs a color transform of the given type on the currently displayed image.
   *
   * @param type Type of color transform to be performed (greyscale or sepia)
   */
  void colorTransform(ColorTransform.TransformType type);

  /**
   * Set the name of the current picture being edited to the given String.
   *
   * @param picName String name to name the picture
   */
  void setPictureName(String picName);

  /**
   * Loads a picture into the view and adds it to the model using a file browser.
   */
  void loadPicture();

  /**
   * Save the picture currently displayed in the view.
   * Opens a file browser for the user to select the save name and location
   */
  void savePicture();

  /**
   * Applies the given filter to the currently displayed picture in the view.
   *
   * @param type Type of filter to apply to the image (blur or sharpen)
   */
  void filter(Filter.FilterType type);

  /**
   * Displays the given component of each pixel, converting to a greyscale.
   *
   * @param type of component to visualize: red, green, blue, luma, intensity, or value
   */
  void viewComponent(Greyscale.Greytype type);

  /**
   * Flip the currently displayed picture in the given way.
   *
   * @param type Type of flip to be performed: horizontal, double, or both
   */
  void flipPicture(Flip.Fliptype type);

  /**
   * Brighten or darken the currently displayed image by the given value.
   * Adheres to clamping pixel values between 0 and 255.
   *
   * @param value integer value to brighten (or darken if negative) the picture by
   */
  void brighten(int value);
}
