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
 * Test class for the Flip Picture command.
 * Tests that pictures are properly flipped vertically, horizontally, and in both directions.
 */
public class FlipTest {

  @Test
  public void executeHorizontalFlip() throws IOException {
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    row.add(new RGBPixel(0, 0, 0, 255));
    row.add(new RGBPixel(100, 100, 100, 255));
    pixels.add(row);
    IPicture pic = new Picture(pixels, "model");
    PictureModel model = new PictureModel();
    model.add("pic", pic);

    PictureCommand flip = new Flip(Flip.Fliptype.Horizontal, "pic", "flipped");
    flip.execute(model);

    assertEquals(model.getPixelAt("pic", 0, 0),
            model.getPixelAt("flipped", 0, 1));
    assertEquals(100, model.getPixelAt("flipped", 0, 0).getG());
  }

  @Test
  public void executeVerticalFlip() throws IOException {
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    List<IPixel> row2 = new ArrayList<>();
    row.add(new RGBPixel(0, 0, 0, 255));
    row2.add(new RGBPixel(100, 100, 100, 255));
    pixels.add(row);
    pixels.add(row2);
    IPicture pic = new Picture(pixels, "model");
    PictureModel model = new PictureModel();
    model.add("pic", pic);

    PictureCommand flip = new Flip(Flip.Fliptype.Vertical, "pic", "flipped");
    flip.execute(model);

    assertEquals(model.getPixelAt("pic", 0, 0),
            model.getPixelAt("flipped", 1, 0));
  }

  @Test
  public void executeBothFlip() throws IOException {
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    List<IPixel> row2 = new ArrayList<>();
    row.add(new RGBPixel(0, 0, 0, 255));
    row.add(new RGBPixel(100, 100, 100, 255));
    row2.add(new RGBPixel(50, 50, 50, 255));
    row2.add(new RGBPixel(150, 150, 150, 255));
    pixels.add(row);
    pixels.add(row2);
    IPicture pic = new Picture(pixels, "model");
    PictureModel model = new PictureModel();
    model.add("pic", pic);

    PictureCommand flip = new Flip(Flip.Fliptype.Both, "pic", "flipped");
    flip.execute(model);

    assertEquals(model.getPixelAt("pic", 0, 0),
            model.getPixelAt("flipped", 1, 1));
  }
}