package utils;

import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import picture.IPicture;
import picture.IPixel;
import picture.Picture;
import picture.RGBPixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test the methods in ImageUtil class.
 */
public class ImageUtilTest {

  @Test
  public void bufferedImageToPicture() {

    try {
      new ImageUtil().bufferedImageToPicture(null, null);
      fail("BufferedImage and String must be non-null.");
    } catch (IllegalArgumentException e) {
      assertEquals("BufferedImage and String must be non-null.", e.getMessage());
    }

    try {
      new ImageUtil().bufferedImageToPicture(new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB),
              null);
      fail("BufferedImage and String must be non-null.");
    } catch (IllegalArgumentException e) {
      assertEquals("BufferedImage and String must be non-null.", e.getMessage());
    }

    try {
      new ImageUtil().bufferedImageToPicture(null, "Not null");
      fail("BufferedImage and String must be non-null.");
    } catch (IllegalArgumentException e) {
      assertEquals("BufferedImage and String must be non-null.", e.getMessage());
    }

    BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    image.setRGB(0, 0, (10 << 16) | (20 << 8) | 40);

    IPicture picture = new ImageUtil().bufferedImageToPicture(image, "New name");

    assertEquals("New name", picture.getName());
    assertEquals(new RGBPixel(10, 20, 40, 255), picture.getPixelAt(0, 0));
  }

  @Test
  public void testPictureToBufferedImage() {
    try {
      new ImageUtil().pictureToBufferedImage(null);
      fail("Given IPicture cannot be null.");

    } catch (IllegalArgumentException e) {
      assertEquals("Given IPicture cannot be null.", e.getMessage());
    }

    List<List<IPixel>> pixels = new ArrayList<>();
    IPixel p = new RGBPixel(50, 100, 180, 255);
    List<IPixel> row = new ArrayList<>();
    row.add(p);
    pixels.add(row);

    IPicture picture = new Picture(pixels, "Test");
    BufferedImage image = new ImageUtil().pictureToBufferedImage(picture);

    assertEquals(((50 << 16) | (100 << 8) | 180), image.getRGB(0, 0) & 0xffffff);
  }

  @Test
  public void testWriteFile() {
    IPixel pixel = new RGBPixel(150, 150, 150, 255);
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    row.add(pixel);
    row.add(pixel);
    pixels.add(row);
    pixels.add(row);

    IPicture picture = new Picture(pixels, "Test");
    try {
      new ImageUtil().writeFile(picture, "TestSave.png");
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }
}