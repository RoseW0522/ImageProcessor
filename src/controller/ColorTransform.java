package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.IPictureModel;
import picture.IPicture;
import picture.IPixel;
import picture.Picture;
import picture.RGBPixel;
import utils.ImageUtil;

/**
 * Command object that performs a color transform on an IPicture.
 * Edits each pixel to transform it in the corresponding way.
 */
public class ColorTransform implements PictureCommand {

  /**
   * Represents a color transform type for transforming IPictures.
   * Legal options are greyscale or sepia, which applies the corresponding color transform.
   */
  public enum TransformType {
    Greyscale, Sepia
  }

  private final TransformType type;
  private final String picName;
  private final String newName;
  private double[][] transform;

  /**
   * Construct a ColorTransform command for an IPicture of the given TransformType
   * of the given picName to be saved as the given newName.
   * Legal options for TransformType are Grayscale or Sepia.
   *
   * @param type    type of filter to apply to the IPicture
   * @param picName String name of the IPicture to be filtered
   * @param newName String name to save the IPicture as
   */
  public ColorTransform(TransformType type, String picName, String newName)
          throws IllegalArgumentException {
    if (type == null || picName == null || newName == null) {
      throw new IllegalArgumentException("Color transform type and picture names cannot be null.");
    }
    this.type = type;
    this.picName = picName;
    this.newName = newName;
    this.initTransform();
  }

  private void initTransform() throws IllegalArgumentException {
    double[][] transform;
    switch (this.type) {
      case Greyscale:
        transform = new double[][]{{0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}};
        break;
      case Sepia:
        transform = new double[][]{{0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}};
        break;
      default:
        throw new IllegalArgumentException("Invalid filter type.");
    }
    this.transform = transform;
  }

  @Override
  public void execute(IPictureModel model) throws IOException {
    IPicture picture = model.getPicture(picName);

    ImageUtil util = new ImageUtil();

    List<List<IPixel>> pixels = new ArrayList<>();
    int r;
    int g;
    int b;
    int newR;
    int newG;
    int newB;

    for (int i = 0; i < picture.getHeight(); i++) {
      List<IPixel> row = new ArrayList<>();
      for (int j = 0; j < picture.getWidth(); j++) {
        IPixel p = picture.getPixelAt(i, j);
        r = p.getR();
        g = p.getG();
        b = p.getB();
        newR = (int) ((r * this.transform[0][0])
                + (g * this.transform[0][1])
                + (b * this.transform[0][2]));
        newG = (int) ((r * this.transform[1][0])
                + (g * this.transform[1][1])
                + (b * this.transform[1][2]));
        newB = (int) ((r * this.transform[2][0])
                + (g * this.transform[2][1])
                + (b * this.transform[2][2]));
        row.add(new RGBPixel(util.clamp(newR), util.clamp(newG), util.clamp(newB), 255));
      }
      pixels.add(row);
    }

    model.add(newName, new Picture(pixels, newName));
  }
}
