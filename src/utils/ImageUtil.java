package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import picture.IPicture;
import picture.IPixel;
import picture.RGBPixel;
import picture.Picture;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   * @param picName String name to assign to the IPicture output
   */
  private IPicture readPPM(String filename, String picName) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      throw new IllegalArgumentException("No file found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    //System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    //System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    //System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    List<List<IPixel>> picture = new ArrayList<>();

    for (int i = 0; i < height; i++) {

      List<IPixel> row = new ArrayList<>();

      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        //System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);

        row.add(new RGBPixel(r, g, b, maxValue)); //add pixel to the row
      }
      picture.add(row); //add row to the list of rows, aka the picture
    }

    return new Picture(picture, picName);
  }

  /**
   * Write the contents of the given Picture as a PPM file with the given name.
   *
   * @param picture  Picture to write to a PPM file
   * @param fileName String name of the PPM file to write the contents of this Picture to
   */
  private void writePPM(IPicture picture, String fileName)
          throws IllegalArgumentException, IOException {
    try {
      //write the PPM file header
      FileWriter file = new FileWriter(fileName);
      file.write("P3\n" + picture.getWidth() + " " + picture.getHeight() + "\n255\n");

      for (int row = 0; row < picture.getHeight(); row++) {
        for (int col = 0; col < picture.getWidth(); col++) {
          IPixel pixel = picture.getPixelAt(row, col);
          file.write(pixel.getR() + "\n");
          file.write(pixel.getG() + "\n");
          file.write(pixel.getB() + "\n");
        }
      }

      file.close();
    } catch (IOException e) {
      throw new IOException("Error writing this PPM file.");
    }
  }

  /**
   * Converts the given BufferedImage to an IPicture with the given String as a name.
   * Used for loading a BufferedImage from a JPEG/PNG/etc. into the photo editing program.
   *
   * @param image BufferedImage to be converted
   * @param name  String name for the IPicture to have
   * @return IPicture representing the same image as the given BufferedImage
   * @throws IllegalArgumentException if either argument is null
   */
  public IPicture bufferedImageToPicture(BufferedImage image, String name)
          throws IllegalArgumentException {
    if (image == null || name == null) {
      throw new IllegalArgumentException("BufferedImage and String must be non-null.");
    }
    List<List<IPixel>> pixels = new ArrayList<>();
    int width = image.getWidth(); //how wide this BufferedImage is
    int height = image.getHeight(); //how tall this Buffered Image is

    for (int i = 0; i < height; i++) {
      List<IPixel> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        int rgb = image.getRGB(j, i);
        int red = (rgb & 0xff0000) >> 16;
        int green = (rgb & 0xff00) >> 8;
        int blue = rgb & 0xff;

        IPixel p = new RGBPixel(red, green, blue, 255);
        row.add(p);
      }
      pixels.add(row);
    }

    return new Picture(pixels, name);
  }

  /**
   * Converts the given IPicture into a BufferedImage, which can be saved as a PNG, JPEG, etc.
   *
   * @param picture IPicture to be converted into a BufferedImage
   * @return the BufferedImage representing the same picture as the given IPicture
   * @throws IllegalArgumentException if the given IPicture is null
   */
  public BufferedImage pictureToBufferedImage(IPicture picture) throws IllegalArgumentException {
    if (picture == null) {
      throw new IllegalArgumentException("Given IPicture cannot be null.");
    }
    int width = picture.getWidth();
    int height = picture.getHeight();
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        IPixel pixel = picture.getPixelAt(i, j);
        int rgbValue = ((pixel.getR() << 16) | (pixel.getG() << 8) | pixel.getB());

        image.setRGB(j, i, rgbValue);
      }
    }
    return image;
  }

  /**
   * Saves this given IPicture as a file of the given String fileName,
   * and of that file type if valid. Valid file types are JPEG, PNG, PPM, and BMP.
   * If a file of this name already exists in the location, overwrites that file.
   *
   * @param picture  IPicture to be saved to this machine
   * @param fileName String name to save this file as
   * @throws IOException              if there is an error writing and saving this file
   * @throws IllegalArgumentException if either input is null
   */
  public void writeFile(IPicture picture, String fileName)
          throws IOException, IllegalArgumentException {
    if (picture == null || fileName == null) {
      throw new IllegalArgumentException("IPicture and file name cannot be null.");
    }


    String fileType = "";
    if (fileName.contains(".ppm") || fileName.contains(".PPM")) {
      this.writePPM(picture, fileName); //calls the writePPM function to save a PPM file
    }
    else if (fileName.contains(".png") || fileName.contains(".PNG")) {
      fileType = "png";
    }
    else if (fileName.contains(".JPEG") || fileName.contains(".jpeg")
            || fileName.contains(".JPG") || fileName.contains(".jpg")) {
      fileType = "jpg";
    }
    else if (fileName.contains(".bmp") || fileName.contains(".BMP")) {
      fileType = "bmp";
    }
    else {
      throw new IllegalArgumentException("Invalid file type.");
    }

    BufferedImage image = this.pictureToBufferedImage(picture);
    File file = new File(fileName);

    try {

      ImageIO.write(image, fileType, file);

    } catch (IOException e) {
      throw new IOException("Error saving this file");
    }
  }

  /**
   * Reads the file of the given fileName and converts it to an IPicture with the given picName.
   * If a file of the given name is not found, throws an IllegalArgumentException.
   *
   * @param fileName name of the file to be converted to an IPicture
   * @param picName String name to assign to the IPicture
   * @return an IPicture with the data of the given file
   * @throws IllegalArgumentException if the given file is not found
   */
  public IPicture readFile(String fileName, String picName) throws IllegalArgumentException {
    File file = new File(fileName);
    BufferedImage image;
    try {
      image = ImageIO.read(file);
    } catch (IOException e) {
      throw new IllegalArgumentException("Given file not found.");
    }

    if (fileName.contains(".ppm") || fileName.contains(".PPM")) {
      return this.readPPM(fileName, picName);
    }

    return this.bufferedImageToPicture(image, picName);
  }

  /**
   * Clamps the int rgb value of a pixel to be between 0 and 255.
   * If the value is less than 0, returns 0.
   * If the value is greater than 255, returns 255.
   *
   * @param rgb integer rgb component to be clamped
   * @return integer value to be return for the rgb component of a pixel
   */
  public int clamp(int rgb) {
    if (rgb > 255) {
      return 255;
    } else {
      return Math.max(rgb, 0);
    }
  }
}

