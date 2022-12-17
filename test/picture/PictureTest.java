package picture;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the Picture class.
 */
public class PictureTest {

  @Test
  public void constructorTest() {
    try {
      new Picture(null, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Pixels or filename cannot be null.", e.getMessage());
    }

    try {
      new Picture(new ArrayList<>(), null);
    } catch (IllegalArgumentException e) {
      assertEquals("Pixels or filename cannot be null.", e.getMessage());
    }

    try {
      new Picture(null, "Not null");
    } catch (IllegalArgumentException e) {
      assertEquals("Pixels or filename cannot be null.", e.getMessage());
    }
  }

  @Test
  public void getName() {
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    row.add(new RGBPixel(0, 0, 0, 255));
    row.add(new RGBPixel(100, 100, 100, 255));
    pixels.add(row);
    IPicture pic = new Picture(pixels, "model");

    assertEquals("model", pic.getName());
  }

  @Test
  public void getWidth() {
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    row.add(new RGBPixel(0, 0, 0, 255));
    row.add(new RGBPixel(100, 100, 100, 255));
    pixels.add(row);
    IPicture pic = new Picture(pixels, "model");

    List<List<IPixel>> noPixels = new ArrayList<>();
    IPicture emptyPic = new Picture(noPixels, "Empty");

    assertEquals(2, pic.getWidth());
    assertEquals(0, emptyPic.getWidth());
  }

  @Test
  public void getHeight() {
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    row.add(new RGBPixel(0, 0, 0, 255));
    row.add(new RGBPixel(100, 100, 100, 255));
    pixels.add(row);
    IPicture pic = new Picture(pixels, "model");

    List<List<IPixel>> noPixels = new ArrayList<>();
    IPicture emptyPic = new Picture(noPixels, "Empty");

    assertEquals(1, pic.getHeight());
    assertEquals(0, emptyPic.getHeight());
  }

  @Test
  public void getPixelAt() {
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    IPixel pixel1 = new RGBPixel(0, 0, 0, 255);
    IPixel pixel2 = new RGBPixel(100, 100, 100, 255);
    row.add(pixel1);
    row.add(pixel2);
    pixels.add(row);
    IPicture pic = new Picture(pixels, "model");

    try {
      pic.getPixelAt(1, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid pixel position.", e.getMessage());
    }

    try {
      pic.getPixelAt(-1, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid pixel position.", e.getMessage());
    }

    try {
      pic.getPixelAt(-4, -6);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid pixel position.", e.getMessage());
    }

    try {
      pic.getPixelAt(0, -1);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid pixel position.", e.getMessage());
    }

    assertEquals(pixel1, pic.getPixelAt(0, 0));
    assertEquals(pixel1, pic.getPixelAt(0, 1));
  }

  @Test
  public void getCopy() {
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    IPixel pixel1 = new RGBPixel(0, 0, 0, 255);
    IPixel pixel2 = new RGBPixel(100, 100, 100, 255);
    row.add(pixel1);
    row.add(pixel2);
    pixels.add(row);
    IPicture pic = new Picture(pixels, "model");

    List<List<IPixel>> copy = pic.getCopy();

    assertEquals(copy.get(0).get(0), pic.getPixelAt(0, 0));
    assertEquals(copy.get(0).get(1), pic.getPixelAt(0, 1));
  }

  @Test
  public void mirrorVertical() {
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    List<IPixel> row2 = new ArrayList<>();
    IPixel pixel1 = new RGBPixel(0, 0, 0, 255);
    IPixel pixel2 = new RGBPixel(100, 100, 100, 255);
    row.add(pixel1);
    row2.add(pixel2);
    pixels.add(row);
    pixels.add(row2);
    IPicture pic = new Picture(pixels, "model");

    IPicture pic2 = pic.mirrorVertical();

    assertEquals(new RGBPixel(0, 0, 0, 255), pic2.getPixelAt(1, 0));
    assertEquals(new RGBPixel(100, 100, 100, 255), pic2.getPixelAt(0, 0));
  }

  @Test
  public void mirrorHorizontal() {
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    IPixel pixel1 = new RGBPixel(0, 0, 0, 255);
    IPixel pixel2 = new RGBPixel(100, 100, 100, 255);
    row.add(pixel1);
    row.add(pixel2);
    pixels.add(row);
    IPicture pic = new Picture(pixels, "model");

    IPicture pic2 = pic.mirrorHorizontal();

    assertEquals(new RGBPixel(0, 0, 0, 255), pic2.getPixelAt(0, 1));
    assertEquals(new RGBPixel(100, 100, 100, 255), pic2.getPixelAt(0, 0));
  }
}