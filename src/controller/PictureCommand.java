package controller;

import java.io.IOException;

import model.IPictureModel;

/**
 * This class represents the manipulation on controller images by each commands.
 */
public interface PictureCommand {

  /**
   * Modified the given IPictureModel based on this PictureCommand.
   *
   * @param model model to execute this command on
   */
  void execute(IPictureModel model) throws IOException;
}
