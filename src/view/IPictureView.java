package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import controller.Features;

/**
 * This interface includes the methods to support rendering a message.
 */
public interface IPictureView {

  /**
   * Refresh the screen. This is used when an update is made and needs to be reflected
   * in the view.
   */
  void refresh();

  /**
   * Make the view visible. Called after it is constructed for smoothness.
   */
  void makeVisible();

  /**
   * Render a specific message to the provided data destination.
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  void renderMessage(String message) throws IOException;

  /**
   * Assigns features to the buttons in the GUI.
   *
   * @param features Controller object containing the program features to be assigned
   */
  void addFeatures(Features features);

  /**
   * Change the picture being displayed to the given BufferedImage.
   *
   * @param picture the BufferedImage to be displayed
   * @param picName String name of the picture being displayed
   * @throws IllegalArgumentException if either given argument is null
   */
  void switchPicture(BufferedImage picture, String picName) throws IllegalArgumentException;

  /**
   * Gets the File selected by the given file path. Can be retrieved from
   * either a file open or file save window.
   *
   * @param fileAction Determines which type of prompt to show the user
   *                   Save or Load based on the desired functionality
   * @return File selected
   */
  File getFilePath(PictureSwingView.FileAction fileAction);
}
