package controller;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.PictureModel;
import picture.IPicture;
import picture.IPixel;
import picture.Picture;
import picture.RGBPixel;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the Greyscale command.
 * Tests that images are properly converted to the right greyscale type.
 */
public class GreyscaleTest {

  @Test
  public void redGreyscale() throws IOException {
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    row.add(new RGBPixel(10, 0, 0, 255));
    row.add(new RGBPixel(110, 100, 100, 255));
    pixels.add(row);
    IPicture pic = new Picture(pixels, "model");
    PictureModel model = new PictureModel();
    model.add("pic", pic);

    PictureCommand redGreyscale = new Greyscale(Greyscale.Greytype.Red,
            "pic", "greyPic");
    redGreyscale.execute(model);

    assertEquals(10, model.getPixelAt("greyPic", 0, 0).getR());
    assertEquals(10, model.getPixelAt("greyPic", 0, 0).getG());
    assertEquals(10, model.getPixelAt("greyPic", 0, 0).getB());

    assertEquals(110, model.getPixelAt("greyPic", 0, 1).getR());
    assertEquals(110, model.getPixelAt("greyPic", 0, 1).getG());
    assertEquals(110, model.getPixelAt("greyPic", 0, 1).getB());
  }

  @Test
  public void greenGreyscale() throws IOException {
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    row.add(new RGBPixel(10, 20, 0, 255));
    row.add(new RGBPixel(110, 150, 100, 255));
    pixels.add(row);
    IPicture pic = new Picture(pixels, "model");
    PictureModel model = new PictureModel();
    model.add("pic", pic);

    PictureCommand redGreyscale = new Greyscale(Greyscale.Greytype.Green,
            "pic", "greyPic");
    redGreyscale.execute(model);

    assertEquals(20, model.getPixelAt("greyPic", 0, 0).getR());
    assertEquals(20, model.getPixelAt("greyPic", 0, 0).getG());
    assertEquals(20, model.getPixelAt("greyPic", 0, 0).getB());

    assertEquals(150, model.getPixelAt("greyPic", 0, 1).getR());
    assertEquals(150, model.getPixelAt("greyPic", 0, 1).getG());
    assertEquals(150, model.getPixelAt("greyPic", 0, 1).getB());
  }

  @Test
  public void blueGreyscale() throws IOException {
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    row.add(new RGBPixel(10, 20, 50, 255));
    row.add(new RGBPixel(110, 150, 10, 255));
    pixels.add(row);
    IPicture pic = new Picture(pixels, "model");
    PictureModel model = new PictureModel();
    model.add("pic", pic);

    PictureCommand redGreyscale = new Greyscale(Greyscale.Greytype.Blue,
            "pic", "greyPic");
    redGreyscale.execute(model);

    assertEquals(50, model.getPixelAt("greyPic", 0, 0).getR());
    assertEquals(50, model.getPixelAt("greyPic", 0, 0).getG());
    assertEquals(50, model.getPixelAt("greyPic", 0, 0).getB());

    assertEquals(10, model.getPixelAt("greyPic", 0, 1).getR());
    assertEquals(10, model.getPixelAt("greyPic", 0, 1).getG());
    assertEquals(10, model.getPixelAt("greyPic", 0, 1).getB());
  }

  @Test
  public void valueGreyscale() throws IOException {
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    row.add(new RGBPixel(10, 20, 50, 255));
    row.add(new RGBPixel(110, 150, 10, 255));
    pixels.add(row);
    IPicture pic = new Picture(pixels, "model");
    PictureModel model = new PictureModel();
    model.add("pic", pic);

    PictureCommand redGreyscale = new Greyscale(Greyscale.Greytype.Value,
            "pic", "greyPic");
    redGreyscale.execute(model);

    assertEquals(50, model.getPixelAt("greyPic", 0, 0).getR());
    assertEquals(50, model.getPixelAt("greyPic", 0, 0).getG());
    assertEquals(50, model.getPixelAt("greyPic", 0, 0).getB());

    assertEquals(150, model.getPixelAt("greyPic", 0, 1).getR());
    assertEquals(150, model.getPixelAt("greyPic", 0, 1).getG());
    assertEquals(150, model.getPixelAt("greyPic", 0, 1).getB());
  }

  @Test
  public void intensityGreyscale() throws IOException {
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    row.add(new RGBPixel(10, 20, 60, 255));
    row.add(new RGBPixel(100, 100, 250, 255));
    pixels.add(row);
    IPicture pic = new Picture(pixels, "model");
    PictureModel model = new PictureModel();
    model.add("pic", pic);

    PictureCommand redGreyscale = new Greyscale(Greyscale.Greytype.Intensity,
            "pic", "greyPic");
    redGreyscale.execute(model);

    assertEquals(30, model.getPixelAt("greyPic", 0, 0).getR());
    assertEquals(30, model.getPixelAt("greyPic", 0, 0).getG());
    assertEquals(30, model.getPixelAt("greyPic", 0, 0).getB());

    assertEquals(150, model.getPixelAt("greyPic", 0, 1).getR());
    assertEquals(150, model.getPixelAt("greyPic", 0, 1).getG());
    assertEquals(150, model.getPixelAt("greyPic", 0, 1).getB());
  }

  @Test
  public void lumaGreyscale() throws IOException {
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    row.add(new RGBPixel(10, 20, 60, 255));
    row.add(new RGBPixel(100, 100, 250, 255));
    pixels.add(row);
    IPicture pic = new Picture(pixels, "model");
    PictureModel model = new PictureModel();
    model.add("pic", pic);

    PictureCommand redGreyscale = new Greyscale(Greyscale.Greytype.Luma,
            "pic", "greyPic");
    redGreyscale.execute(model);

    assertEquals(21, model.getPixelAt("greyPic", 0, 0).getR());
    assertEquals(21, model.getPixelAt("greyPic", 0, 0).getG());
    assertEquals(21, model.getPixelAt("greyPic", 0, 0).getB());

    assertEquals(111, model.getPixelAt("greyPic", 0, 1).getR());
    assertEquals(111, model.getPixelAt("greyPic", 0, 1).getG());
    assertEquals(111, model.getPixelAt("greyPic", 0, 1).getB());
  }
}