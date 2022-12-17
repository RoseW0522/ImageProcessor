package controller;

import java.io.IOException;
import java.util.Objects;

import model.IPictureModel;
import picture.IPicture;
import picture.IPixel;

/**
 * Mock model to test that the PictureControllerImpl class passes the correct input
 * to the IPictureModel.
 */
public class ConfirmInputsPictureModel implements IPictureModel {

  final Appendable log;

  /**
   * Constructs a model containing only an appendable log to log the input from the Controller.
   *
   * @param log Appendable to write output to, confirming controller passes correct arguments
   */
  public ConfirmInputsPictureModel(Appendable log) throws IllegalArgumentException {
    Objects.requireNonNull(log);
    this.log = log;
  }

  @Override
  public void load(String fileName, String picName) throws IllegalArgumentException {
    try {
      log.append("Load command called. Given inputs: fileName: " + fileName
              + " picName: " + picName);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to write to the appendable.");
    }
  }

  @Override
  public void save(String fileName, String picName) throws IllegalArgumentException {
    try {
      log.append("Save command called. Given inputs: fileName: " + fileName
              + " picName: " + picName);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to write to the appendable.");
    }
  }

  @Override
  public void flipHorizontal(String picName, String newName) {
    try {
      log.append("Flip horizontal command called. Given inputs: fileName: " + picName
              + " picName: " + newName);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to write to the appendable.");
    }
  }

  @Override
  public void flipVertical(String picName, String newName) {
    try {
      log.append("Flip vertical command called. Given inputs: fileName: " + picName
              + " picName: " + newName);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to write to the appendable.");
    }
  }

  @Override
  public void flipBoth(String picName, String newName) {
    try {
      log.append("Flip both command called. Given inputs: fileName: " + picName
              + " picName: " + newName);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to write to the appendable.");
    }
  }

  @Override
  public void add(String name, IPicture picture) throws IllegalArgumentException {
    try {
      log.append("Add Picture command called. Given inputs: fileName: " + name);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to write to the appendable.");
    }
  }

  @Override
  public void remove(String name) throws IllegalArgumentException {
    try {
      log.append("Remove Picture command called. Given inputs: fileName: " + name);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to write to the appendable.");
    }
  }

  @Override
  public void greyScale(String picName, String newName, Greyscale.Greytype type)
          throws IllegalArgumentException {
    try {
      log.append("Greyscale command called. Given inputs: fileName: " + picName
              + ", " + newName + ", " + type.toString());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to write to the appendable.");
    }
  }

  @Override
  public void brighten(String picName, String newName, int delta) throws IllegalArgumentException {
    try {
      log.append("Brighten command called. Given inputs: fileName: " + picName
              + ", " + newName + ", " + delta);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to write to the appendable.");
    }
  }

  @Override
  public int getWidth(String fileName) {
    return 0;
  }

  @Override
  public int getHeight(String fileName) {
    return 0;
  }

  @Override
  public IPixel getPixelAt(String fileName, int row, int col) throws IllegalArgumentException {
    return null;
  }

  @Override
  public IPicture getPicture(String picName) throws IllegalArgumentException {
    return null;
  }
}
