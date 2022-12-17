package model;

import picture.IPicture;
import picture.Picture;
import picture.IPixel;
import picture.RGBPixel;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test class for the PictureModel class.
 * Tests that the mutable methods for this class work as intended.
 */
public class PictureModelTest {

  @Test
  public void save() {
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    row.add(new RGBPixel(0, 0, 0, 255));
    pixels.add(row);
    IPicture pic = new Picture(pixels, "model");
    PictureModel model = new PictureModel();
    model.add("pic", pic);

    try {
      model.save("test.ppm", "pic");
    } catch (IOException e) {
      fail("Failed to save this image.");
    }

    assertTrue(new File("test.ppm").exists()); //test that the file is created

    FileReader reader;
    try {
      reader = new FileReader("test.ppm");
      try {
        assertEquals('P', reader.read());
        assertEquals('3', reader.read()); //test that the file has the correct output
        assertEquals('\n', reader.read());
        assertEquals('1', reader.read());
        assertEquals(' ', reader.read());
        assertEquals('1', reader.read());
        assertEquals('\n', reader.read());
        assertEquals('2', reader.read());
        assertEquals('5', reader.read());
        assertEquals('5', reader.read());
        assertEquals('\n', reader.read());
        assertEquals('0', reader.read());
        assertEquals('\n', reader.read());
        assertEquals('0', reader.read());
        assertEquals('\n', reader.read());
        assertEquals('0', reader.read());
        assertEquals('\n', reader.read());
      } catch (IOException e) {
        fail();
      }
    } catch (FileNotFoundException e) {
      fail("File not found!");
    }
  }

  @Test
  public void load() {
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    row.add(new RGBPixel(0, 0, 0, 255));
    pixels.add(row);
    IPicture pic = new Picture(pixels, "model");
    PictureModel model = new PictureModel();

    model.load("test.ppm", "pic");

    assertEquals(1, model.getHeight("pic"));
    //test the height to ensure picture loaded correctly
    assertEquals(1, model.getWidth("pic"));
    //test the width to ensure picture loaded correctly
  }

  @Test
  public void getWidth() {
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    row.add(new RGBPixel(0, 0, 0, 255));
    pixels.add(row);
    IPicture pic = new Picture(pixels, "model");
    PictureModel model = new PictureModel();
    model.add("pic", pic);

    assertEquals(1, model.getWidth("pic"));
  }

  @Test
  public void getHeight() {
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    row.add(new RGBPixel(0, 0, 0, 255));
    pixels.add(row);
    IPicture pic = new Picture(pixels, "model");
    PictureModel model = new PictureModel();
    model.add("pic", pic);

    assertEquals(1, model.getHeight("pic"));
  }

  @Test
  public void getPixelAt() {
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    row.add(new RGBPixel(0, 0, 0, 255));
    pixels.add(row);
    IPicture pic = new Picture(pixels, "model");
    PictureModel model = new PictureModel();
    model.add("pic", pic);

    IPixel pixel = new RGBPixel(0, 0, 0, 255);

    assertEquals(pixel, model.getPixelAt("pic", 0, 0));
  }
}