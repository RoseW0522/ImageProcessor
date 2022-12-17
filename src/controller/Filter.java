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
 * Command object that filters an IPicture.
 * Edits each pixel value of the IPicture based on the corresponding kernel,
 * which is a 2-D array of numbers associated with a given filter.
 */
public class Filter implements PictureCommand {

  /**
   * Represents filter types for filtering IPictures.
   * Legal options are blur or sharper, which make the IPicture more blurry or sharper respectively.
   */
  public enum FilterType {
    Blur, Sharpen
  }

  private final FilterType type;
  private final String picName;
  private final String newName;
  private double[][] kernel;

  /**
   * Construct a Filter command for an IPicture of the given FilterType of the given picName to
   * be saved as the given newName.
   * Legal options for FilterType are Blur or Sharpen.
   *
   * @param type type of filter to apply to the IPicture
   * @param picName String name of the IPicture to be filtered
   * @param newName String name to save the IPicture as
   */
  public Filter(FilterType type, String picName, String newName) {
    if (type == null || picName == null || newName == null) {
      throw new IllegalArgumentException("Filter type and picture names cannot be null.");
    }
    this.type = type;
    this.picName = picName;
    this.newName = newName;
    this.initKernel();
  }

  //initializes the kernel for this filter command based on the filter type
  private void initKernel() throws IllegalStateException {
    double[][] kernel;
    switch (type) {
      case Blur:
        kernel = new double[][]{{0.0625, 0.125, 0.0625},
            {0.0125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}};
        break;
      case Sharpen:
        kernel = new double[][]{{-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1.0, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}};
        break;
      default:
        throw new IllegalStateException("Illegal filter type.");
    }
    this.kernel = kernel;
  }

  @Override
  public void execute(IPictureModel model) throws IOException {
    IPicture picture = model.getPicture(this.picName);
    int height = picture.getHeight();
    int width = picture.getWidth();

    List<List<IPixel>> pixels = new ArrayList<>();

    for (int i = 0; i < height; i++) {
      List<IPixel> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        IPixel pixel = this.filterPixel(i, j, picture);
        row.add(pixel);
      }
      pixels.add(row);
    }
    model.add(this.newName, new Picture(pixels, this.newName));
  }

  //Applies the kernel to the pixel at the given position in the given IPicture, filtering it
  private IPixel filterPixel(int row, int col, IPicture picture) {
    ImageUtil util = new ImageUtil();
    int kernelSize = this.kernel.length;
    int delta = kernelSize / 2;
    int r = 0;
    int g = 0;
    int b = 0;
    for (int i = 0; i < kernelSize; i++) {

      for (int j = 0; j < kernelSize; j++) {
        try {
          IPixel pixel = picture.getPixelAt((row - delta) + i, (col - delta) + j);
          r = r + (int) Math.round(pixel.getR() * this.kernel[i][j]);
          g = g + (int) Math.round(pixel.getG() * this.kernel[i][j]);
          b = b + (int) Math.round(pixel.getB() * this.kernel[i][j]);
        } catch (IllegalArgumentException ignored) { }
        //do not change rgb values if the getPixel is out of bounds (throws exception)
      }
    }

    return new RGBPixel(util.clamp(r), util.clamp(g), util.clamp(b), 255);
  }


}
