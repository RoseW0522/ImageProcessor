import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.Features;
import controller.FeaturesController;
import controller.PictureController;
import controller.PictureControllerImpl;
import model.IPictureModel;
import model.PictureModel;
import view.PictureSwingView;

/**
 * Main class to initiate the picture editor program.
 */
public class Editor {

  /**
   * Main method for running the editPhoto method for a PictureControllerImpl.
   * If no arguments are passed, opens the GUI view. If arguments are passed,
   * handling is outlined in the README.
   *
   * @param args String arguments to run this program
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      Features cont = new FeaturesController();
      cont.setView(new PictureSwingView());
    }
    else {
      IPictureModel model = new PictureModel();
      PictureController controller;
      if (args[0].equals("-file")) {
        try {
          InputStreamReader rd = new InputStreamReader(new FileInputStream(args[1]));
          controller = new PictureControllerImpl(model, rd);
        } catch (IOException e) {
          throw new IllegalStateException("Could not read given file.");
        }
      } else if (args[0].equals("-text")) {
        controller = new PictureControllerImpl(model, new InputStreamReader(System.in));
      } else {
        controller = new PictureControllerImpl(model, new InputStreamReader(System.in));
      }
      try {
        controller.editPhoto();
      } catch (IOException e) {
        throw new IllegalStateException("IOException encountered.");
      }
    }
  }
}
