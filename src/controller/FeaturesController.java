package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.IPictureModel;
import model.PictureModel;
import utils.ImageUtil;
import view.IPictureView;
import view.PictureSwingView;

/**
 * Features controller for the PhotoEditing program with GUI view.
 * Contains commands called by action events within the GUI.
 */
public class FeaturesController implements Features {

  private IPictureModel model;
  private IPictureView view;
  private String pictureName;

  /**
   * Constructs a FeaturesController object with default empty model.
   * Does not assign the view (that is done with the setView method to start the program).
   */
  public FeaturesController() {
    this.model = new PictureModel();
  }

  /**
   * Constructs a FeaturesController object to act on the given model.
   * Does not assign the view (that is done with the setView method to start the program).
   *
   * @param model model for this controller to act on
   */
  public FeaturesController(IPictureModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model and view cannot be null.");
    }

    this.model = model;
  }

  @Override
  public void setView(IPictureView view) throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("View cannot be set to null.");
    }
    this.view = view;
    this.view.addFeatures(this);
  }

  @Override
  public void setPictureName(String picName) {
    if (picName == null) {
      throw new IllegalArgumentException("Name cannot be set to null.");
    }
    this.pictureName = picName;
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void colorTransform(ColorTransform.TransformType type) {
    String newName;
    if (type == ColorTransform.TransformType.Greyscale) {
      newName = "grey";
    } else if (type == ColorTransform.TransformType.Sepia) {
      newName = "sepia";
    } else {
      throw new IllegalArgumentException("Illegal Color Transform Type");
    }
    try {
      new ColorTransform(type, this.pictureName, newName).execute(this.model);
    } catch (IOException e) {
      throw new IllegalStateException("Could not convert the image to greyscale.");
    }
    this.pictureName = newName;

    ImageUtil util = new ImageUtil();

    this.view.switchPicture(util.pictureToBufferedImage(this.model.getPicture(newName)), newName);
    this.view.refresh();
  }

  @Override
  public void loadPicture() {
    File value = this.view.getFilePath(PictureSwingView.FileAction.Load);
    BufferedImage image;
    if (value == null) {
      return; //case if the file explorer is closed -> do nothing
    }
    try {
      image = ImageIO.read(value);
    } catch (IOException e) {
      this.renderMessage("Failed to read the given file.");
      return;
    }
    if (image != null) {
      String name = value.getName();
      this.pictureName = name;
      this.model.add(name, new ImageUtil().bufferedImageToPicture(image, name));
      this.view.switchPicture(image, name);
    }
  }

  @Override
  public void savePicture() {
    File value = this.view.getFilePath(PictureSwingView.FileAction.Save);
    if (value == null || this.pictureName == null) {
      return; //case if the file explorer is closed -> do nothing
    }
    ImageUtil util = new ImageUtil();
    String fileName = value.getName();
    try {
      util.writeFile(this.model.getPicture(this.pictureName), fileName);
    } catch (IOException e) {
      this.renderMessage("Failed to save this image.");
    }
  }

  @Override
  public void filter(Filter.FilterType type) {
    PictureCommand cmd;
    switch (type) {
      case Blur:
        cmd = new Filter(Filter.FilterType.Blur, this.pictureName, "blur");
        this.pictureName = "blur";
        break;
      case Sharpen:
        cmd = new Filter(Filter.FilterType.Sharpen, this.pictureName, "sharp");
        this.pictureName = "sharp";
        break;
      default:
        String error = "Invalid filter type received.";
        this.renderMessage(error);
        throw new IllegalStateException(error);
    }
    this.executeCommand(cmd);
  }

  @Override
  public void viewComponent(Greyscale.Greytype type) {
    PictureCommand command;
    switch (type) {
      case Red:
        command = new Greyscale(Greyscale.Greytype.Red, this.pictureName, "red");
        this.pictureName = "red";
        break;
      case Green:
        command = new Greyscale(Greyscale.Greytype.Green, this.pictureName, "green");
        this.pictureName = "green";
        break;
      case Blue:
        command = new Greyscale(Greyscale.Greytype.Blue, this.pictureName, "blue");
        this.pictureName = "blue";
        break;
      case Luma:
        command = new Greyscale(Greyscale.Greytype.Luma, this.pictureName, "luma");
        this.pictureName = "luma";
        break;
      case Intensity:
        command = new Greyscale(Greyscale.Greytype.Intensity, this.pictureName, "intensity");
        this.pictureName = "intensity";
        break;
      case Value:
        command = new Greyscale(Greyscale.Greytype.Value, this.pictureName, "value");
        this.pictureName = "value";
        break;
      default:
        String error = "Invalid component type received";
        this.renderMessage(error);
        throw new IllegalStateException(error);
    }
    this.executeCommand(command);
  }

  @Override
  public void flipPicture(Flip.Fliptype type) {
    PictureCommand cmd;
    switch (type) {
      case Vertical:
        cmd = new Flip(Flip.Fliptype.Vertical, this.pictureName, "verticalFlip");
        this.pictureName = "verticalFlip";
        break;
      case Horizontal:
        cmd = new Flip(Flip.Fliptype.Horizontal, this.pictureName, "horizontalFlip");
        this.pictureName = "horizontalFlip";
        break;
      case Both:
        cmd = new Flip(Flip.Fliptype.Both, this.pictureName, "doubleFlip");
        this.pictureName = "doubleFlip";
        break;
      default:
        String error = "Invalid flip type received";
        this.renderMessage(error);
        throw new IllegalArgumentException(error);
    }
    this.executeCommand(cmd);
  }

  @Override
  public void brighten(int value) {
    String oldName = this.pictureName;
    if (value == 0) {
      return;
    } else if (value < 0) {
      this.pictureName = "darker";
    } else {
      this.pictureName = "brighter";
    }
    this.executeCommand(new Brighten(value, oldName, this.pictureName));
  }

  //Executes the given PictureCommand command on the model. Handles potential IOException.
  private void executeCommand(PictureCommand command) {
    try {
      command.execute(this.model);
    } catch (IOException e) {
      this.renderMessage("Failed to display this component type");
      return;
    }

    ImageUtil util = new ImageUtil();

    this.view.switchPicture(util.pictureToBufferedImage(this.model.getPicture(this.pictureName)),
            this.pictureName);
    this.view.refresh();
  }

  //renders the given String message in the view as a pop-up. Handles the potential IOException.
  private void renderMessage(String message) {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Failed to render error message.");
    }
  }
}
