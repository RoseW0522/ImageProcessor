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
 * Test class for the Brighten Picture command.
 * Tests that pictures are properly brightened or darkened and do not exit their legal bounds.
 */
public class BrightenTest {

  @Test
  public void execute() throws IOException {
    List<List<IPixel>> pixels = new ArrayList<>();
    List<IPixel> row = new ArrayList<>();
    row.add(new RGBPixel(0, 1, 2, 255));
    row.add(new RGBPixel(100, 100, 100, 255));
    pixels.add(row);
    IPicture pic = new Picture(pixels, "model");
    PictureModel model = new PictureModel();
    model.add("pic", pic);

    PictureCommand brighten = new Brighten(200, "pic", "brighter");
    brighten.execute(model);

    IPixel pixel0 = model.getPixelAt("brighter", 0, 0);
    IPixel pixel1 = model.getPixelAt("brighter", 0, 1);

    assertEquals(200, pixel0.getR());
    assertEquals(201, pixel0.getG());
    assertEquals(202, pixel0.getB());

    assertEquals(255, pixel1.getR());
    assertEquals(255, pixel1.getG());
    assertEquals(255, pixel1.getB());

    PictureCommand darken = new Brighten(-50, "pic", "darker");
    darken.execute(model);

    IPixel pixel3 = model.getPixelAt("darker", 0, 0);
    IPixel pixel4 = model.getPixelAt("darker", 0, 1);

    assertEquals(0, pixel3.getR());
    assertEquals(0, pixel3.getG());
    assertEquals(0, pixel3.getB());

    assertEquals(50, pixel4.getR());
    assertEquals(50, pixel4.getG());
    assertEquals(50, pixel4.getB());
  }
}