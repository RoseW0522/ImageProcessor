package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.Greyscale;
import picture.IPicture;
import picture.IPixel;
import picture.Picture;
import picture.RGBPixel;
import utils.ImageUtil;

/**
 * The class manipulates the picture and can save, load, flip picture.
 */
public class PictureModel implements IPictureModel {

  private final Map<String, IPicture> pictures;

  /**
   * Instantiate a model of Picture with name and picture image.
   */
  public PictureModel() {
    this.pictures = new HashMap<String, IPicture>();
  }

  @Override
  public void load(String fileName, String picName) throws IllegalArgumentException {
    if (pictures.containsKey(picName)) {
      pictures.remove(picName);
    }
    IPicture pic = new ImageUtil().readFile(fileName, picName);
    pictures.put(picName, pic);
  }

  @Override
  public void save(String fileName, String picName) throws IllegalArgumentException, IOException {
    if (!pictures.containsKey(picName)) {
      throw new IllegalArgumentException("Picture not found!");
    }

    IPicture pic = pictures.get(picName);

    if (pic.getHeight() == 0 || pic.getWidth() == 0) {
      throw new IllegalArgumentException("Cannot save an empty image.");
    }
    System.out.println("Saved new file: " + fileName);
    try {
      new ImageUtil().writeFile(pic, fileName);
    } catch (IOException e) {
      throw new IOException("Error writing this file.");
    }
    System.out.println("Successfully wrote to the file!");
  }


  @Override
  public int getWidth(String picName) {
    if (!pictures.containsKey(picName)) {
      throw new IllegalArgumentException("Picture not found!");
    }
    return pictures.get(picName).getWidth();
  }

  @Override
  public int getHeight(String picName) {
    if (!pictures.containsKey(picName)) {
      throw new IllegalArgumentException("Picture not found!");
    }
    return pictures.get(picName).getHeight();
  }

  @Override
  public IPixel getPixelAt(String picName, int row, int col) throws IllegalArgumentException {
    if (!pictures.containsKey(picName)) {
      throw new IllegalArgumentException("Picture not found!");
    }
    return pictures.get(picName).getPixelAt(row, col);
  }

  @Override
  public void flipHorizontal(String picName, String newName) {
    if (!pictures.containsKey(picName)) {
      throw new IllegalArgumentException("Picture not found!");
    }
    this.pictures.put(newName, pictures.get(picName).mirrorHorizontal());
  }

  @Override
  public void flipVertical(String picName, String newName) {
    if (!pictures.containsKey(picName)) {
      throw new IllegalArgumentException("Picture not found!");
    }
    this.pictures.put(newName, pictures.get(picName).mirrorVertical());
  }

  @Override
  public void flipBoth(String picName, String newName) {
    this.pictures.put(newName, pictures.get(picName).mirrorVertical().mirrorHorizontal());
  }

  @Override
  public void add(String name, IPicture picture) throws IllegalArgumentException {
    if (name == null || picture == null) {
      throw new IllegalArgumentException("Cannot add null values!");
    }
    this.pictures.put(name, picture);
  }

  @Override
  public void remove(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("Name of picture cannot be null!");
    }
    if (this.pictures.containsKey(name)) {
      this.pictures.remove(name);
    } else {
      throw new IllegalArgumentException("Picture of this name not found.");
    }
  }

  @Override
  public void greyScale(String picName, String newName, Greyscale.Greytype type)
          throws IllegalArgumentException {
    if (!pictures.containsKey(picName)) {
      throw new IllegalArgumentException("Picture not found!");
    }
    IPicture picture = this.pictures.get(picName);

    List<List<IPixel>> pixels = new ArrayList<>();

    for (int i = 0; i < picture.getHeight(); i++) {
      List<IPixel> row = new ArrayList<>();
      for (int j = 0; j < picture.getWidth(); j++) {
        IPixel p = picture.getPixelAt(i, j);
        int value;

        switch (type) {
          case Red:
            value = p.getR();
            break;
          case Green:
            value = p.getG();
            break;
          case Blue:
            value = p.getB();
            break;
          case Value:
            value = p.value();
            break;
          case Intensity:
            value = (int) Math.round(p.intensity());
            break;
          case Luma:
            value = (int) Math.round(p.luma());
            break;
          default:
            throw new IllegalArgumentException("Unknown greyscale type!");
        }

        row.add(new RGBPixel(value, value, value, p.getCap()));
      }
      pixels.add(row);
    }

    IPicture pic = new Picture(pixels, newName);
    this.pictures.put(newName, pic);
  }

  @Override
  public void brighten(String picName, String newName, int delta)
          throws IllegalArgumentException {
    if (!pictures.containsKey(picName)) {
      throw new IllegalArgumentException("Picture not found!");
    }
    IPicture picture = this.pictures.get(picName);

    List<List<IPixel>> pixels = new ArrayList<>();

    int red;
    int green;
    int blue;
    int cap;

    for (int i = 0; i < picture.getHeight(); i++) {
      List<IPixel> row = new ArrayList<>();
      for (int j = 0; j < picture.getWidth(); j++) {
        IPixel p = picture.getPixelAt(i, j);
        cap = p.getCap();

        red = this.changePixelValue(p.getR(), delta, cap);
        green = this.changePixelValue(p.getG(), delta, cap);
        blue = this.changePixelValue(p.getB(), delta, cap);

        row.add(new RGBPixel(red, green, blue, cap));
      }
      pixels.add(row);
    }
    this.pictures.put(newName, new Picture(pixels, newName));
  }

  /**
   * Edit the value of pixel by adding the value and delta.
   * @param value the selected value
   * @param delta the added value
   * @param cap the largest value to limit the range of the sum of value and delta
   * @return a cap if the sum of value and delta is over delta, the maximum of the sum of
   *         value add delta and zero
   */
  private int changePixelValue(int value, int delta, int cap) {
    if (value + delta > cap) {
      return cap;
    }
    else {
      return Math.max(value + delta, 0);
    }
  }

  @Override
  public IPicture getPicture(String picName) throws IllegalArgumentException {
    if (picName == null) {
      throw new IllegalArgumentException("Picture name cannot be null.");
    }
    if (this.pictures.containsKey(picName)) {
      return this.pictures.get(picName);
    }
    else {
      throw new IllegalArgumentException("Given picture not found.");
    }
  }
}
