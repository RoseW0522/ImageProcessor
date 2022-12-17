package picture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a Picture comprised of RGBPixels.
 */
public class Picture implements IPicture {

  private List<List<IPixel>> pixels;
  private String name;

  /**
   * Constructs a Picture.Picture from the given PPM file name.
   * Stores the pixel information of the given file as Pixels.
   *
   * @param pixels   all Pixels in this picture stored as a List of List of IPixels
   * @param fileName String name of the picture
   * @throws IllegalArgumentException if either given input is null
   */
  public Picture(List<List<IPixel>> pixels, String fileName) throws IllegalArgumentException {
    if (pixels == null || fileName == null) {
      throw new IllegalArgumentException("Pixels or filename cannot be null.");
    }
    this.pixels = pixels;
    this.name = fileName;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getWidth() {
    if (this.pixels.size() == 0) {
      return 0;
    } else {
      return this.pixels.get(0).size();
    }
  }

  @Override
  public int getHeight() {
    return this.pixels.size();
  }

  @Override
  public IPixel getPixelAt(int row, int col) throws IllegalArgumentException {
    if (this.pixels.size() <= row || row < 0 || col < 0) {
      throw new IllegalArgumentException("Invalid pixel position.");
    } else if (this.pixels.get(row).size() <= col) {
      throw new IllegalArgumentException("Invalid pixel position.");
    } else {
      List<IPixel> rowOfPixels = this.pixels.get(row);
      return rowOfPixels.get(col);
    }
  }

  @Override
  public IPicture mirrorVertical() {
    List<List<IPixel>> pixels = this.getCopy();

    //Collections.copy(pixels, this.pixels);
    Collections.reverse(pixels);

    return new Picture(pixels, this.name);
  }

  @Override
  public IPicture mirrorHorizontal() {
    List<List<IPixel>> pixels = this.getCopy();

    for (int i = 0; i < this.pixels.size(); i++) {
      List<IPixel> row = pixels.get(i);
      Collections.reverse(row);
    }


    return new Picture(pixels, this.name);
  }

  @Override
  public List<List<IPixel>> getCopy() {
    List<List<IPixel>> copy = new ArrayList<List<IPixel>>();
    for (int i = 0; i < this.getHeight(); i++) {
      List<IPixel> row = new ArrayList<>();
      for (int j = 0; j < this.getWidth(); j++) {
        IPixel pixel = pixels.get(i).get(j);
        IPixel nextPixel = new RGBPixel(pixel.getR(), pixel.getG(), pixel.getG(), pixel.getCap());
        row.add(nextPixel);
      }
      copy.add(row);
    }
    return copy;
  }

  @Override
  public HashMap<Integer, Integer> redMap() {
    HashMap<Integer, Integer> red = new HashMap<>();
    for (int k = 0; k < 256; k++) {
      red.put(k, 0);
    }
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        red.put(getPixelAt(i, j).getR(), red.get(getPixelAt(i, j).getR()) + 1);
      }
    }
    return red;
  }

  @Override
  public HashMap<Integer, Integer> greenMap() {
    HashMap<Integer, Integer> green = new HashMap<>();
    for (int k = 0; k < 256; k++) {
      green.put(k, 0);
    }
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        green.put(getPixelAt(i, j).getG(), green.get(getPixelAt(i, j).getG()) + 1);
      }
    }
    return green;
  }

  @Override
  public HashMap<Integer, Integer> blueMap() {
    HashMap<Integer, Integer> blue = new HashMap<>();
    for (int k = 0; k < 256; k++) {
      blue.put(k, 0);
    }
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        blue.put(getPixelAt(i, j).getB(), blue.get(getPixelAt(i, j).getB()) + 1);
      }
    }
    return blue;
  }
}

