package controller;

import java.io.IOException;

/**
 * This interface include the method to handle the user input and calls
 * the Picture Command objects to edit Pictures.
 */
public interface PictureController {

  /**
   * Manipulates the picture through load, save, flip, greyScale.
   * And q can be input for quit.
   */
  void editPhoto() throws IOException;
}
