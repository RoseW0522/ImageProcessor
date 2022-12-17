package picture;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * Test class for the RGBPixel class.
 */
public class PixelTest {

  @Test
  public void testConstructor() {

    try {
      new RGBPixel(275, 0, 0, 255);
      fail("Color value exceeds the cap.");
    } catch (IllegalArgumentException e) {
      assertEquals("Color value exceeds the cap.", e.getMessage());
    }

    try {
      new RGBPixel(25, 300, 0, 255);
      fail("Color value exceeds the cap.");
    } catch (IllegalArgumentException e) {
      assertEquals("Color value exceeds the cap.", e.getMessage());
    }

    try {
      new RGBPixel(25, 0, 290, 255);
      fail("Color value exceeds the cap.");
    } catch (IllegalArgumentException e) {
      assertEquals("Color value exceeds the cap.", e.getMessage());
    }

    try {
      new RGBPixel(-20, 0, 115, 255);
      fail("Color value cannot be negative.");
    } catch (IllegalArgumentException e) {
      assertEquals("Color value cannot be negative.", e.getMessage());
    }

    try {
      new RGBPixel(20, -45, 115, 255);
      fail("Color value cannot be negative.");
    } catch (IllegalArgumentException e) {
      assertEquals("Color value cannot be negative.", e.getMessage());
    }

    try {
      new RGBPixel(20, 0, -115, 255);
      fail("Color value cannot be negative.");
    } catch (IllegalArgumentException e) {
      assertEquals("Color value cannot be negative.", e.getMessage());
    }
  }

  @Test
  public void testValue() {
    RGBPixel pixel11 = null;
    RGBPixel pixel12 = null;
    RGBPixel pixel13 = null;

    try {
      pixel11 = new RGBPixel(20, 0, 0, 255);
    } catch (IllegalArgumentException e) {
      assertEquals(20, pixel11.value());
    }

    try {
      pixel12 = new RGBPixel(20, 40, 0, 255);
    } catch (IllegalArgumentException e) {
      assertEquals(40, pixel12.value());
    }

    try {
      pixel13 = new RGBPixel(20, 40, 60, 255);
    } catch (IllegalArgumentException e) {
      assertEquals(60, pixel13.value());
    }
  }

  @Test
  public void testIntensity() {
    RGBPixel pixel11 = null;
    RGBPixel pixel12 = null;
    RGBPixel pixel13 = null;

    try {
      pixel11 = new RGBPixel(20, 10, 0, 255);
    } catch (IllegalArgumentException e) {
      assertEquals(10, pixel11.intensity(), 0.0);
    }

    try {
      pixel12 = new RGBPixel(20, 40, 0, 255);
    } catch (IllegalArgumentException e) {
      assertEquals(20, pixel12.intensity(), 0.0);
    }

    try {
      pixel13 = new RGBPixel(20, 40, 60, 255);
    } catch (IllegalArgumentException e) {
      assertEquals(40, pixel13.intensity(), 0.0);
    }
  }

  @Test
  public void testLuma() {
    RGBPixel pixel11 = null;
    RGBPixel pixel12 = null;
    RGBPixel pixel13 = null;

    try {
      pixel11 = new RGBPixel(100, 0, 0, 255);
    } catch (IllegalArgumentException e) {
      assertEquals(21.26, pixel11.luma(), 0.0);
    }

    try {
      pixel12 = new RGBPixel(0, 100, 0, 255);
    } catch (IllegalArgumentException e) {
      assertEquals(71.52, pixel12.luma(), 0.0);
    }

    try {
      pixel13 = new RGBPixel(0, 0, 100, 255);
    } catch (IllegalArgumentException e) {
      assertEquals(7.22, pixel13.luma(), 0.0);
    }
  }

  @Test
  public void testGetRGB() {
    RGBPixel pixel11 = null;
    RGBPixel pixel12 = null;
    RGBPixel pixel13 = null;

    try {
      pixel11 = new RGBPixel(100, 40, 20, 255);
    } catch (IllegalArgumentException e) {
      assertEquals(100, pixel11.getR());
      assertEquals(40, pixel11.getG());
      assertEquals(20, pixel11.getB());
      assertEquals(255, pixel11.getCap());
    }

    try {
      pixel12 = new RGBPixel(240, 140, 210, 255);
    } catch (IllegalArgumentException e) {
      assertTrue(pixel12.getR() == 240);
      assertTrue(pixel12.getG() == 140);
      assertTrue(pixel12.getB() == 210);
      assertTrue(pixel12.getCap() == 255);
    }

    try {
      pixel13 = new RGBPixel(101, 24, 72, 255);
    } catch (IllegalArgumentException e) {
      assertTrue(pixel13.getR() == 101);
      assertTrue(pixel13.getG() == 24);
      assertTrue(pixel13.getB() == 72);
      assertTrue(pixel13.getCap() == 255);
    }
  }

  @Test
  public void testEditValue() {
    RGBPixel pixel11 = null;
    RGBPixel pixel12 = null;
    RGBPixel pixel13 = null;

    try {
      pixel11 = new RGBPixel(44, 55, 66, 255);
      pixel11.editPixel(100, 40, 20);
    } catch (IllegalArgumentException e) {
      assertTrue(pixel11.getR() == 100);
      assertTrue(pixel11.getG() == 40);
      assertTrue(pixel11.getB() == 20);
    }

    try {
      pixel12 = new RGBPixel(89, 145,214, 255);
      pixel12.editPixel(240, 140, 210);
    } catch (IllegalArgumentException e) {
      assertTrue(pixel12.getR() == 240);
      assertTrue(pixel12.getG() == 140);
      assertTrue(pixel12.getB() == 210);
    }

    try {
      pixel13 = new RGBPixel(240, 230, 220, 255);
      pixel13.editPixel(101, 24, 72);
    } catch (IllegalArgumentException e) {
      assertTrue(pixel13.getR() == 101);
      assertTrue(pixel13.getG() == 24);
      assertTrue(pixel13.getB() == 72);
      assertTrue(pixel13.getCap() == 255);
    }
  }

  @Test
  public void testEquals() {
    RGBPixel pixel11 = null;
    RGBPixel pixel12 = null;
    RGBPixel pixel13 = null;
    RGBPixel pixel14 = null;
    RGBPixel pixel15 = null;
    RGBPixel pixel16 = null;

    try {
      pixel11 = new RGBPixel(44, 55, 66, 255);
      pixel12 = new RGBPixel(44, 55, 66, 255);
    } catch (IllegalArgumentException e) {
      assertTrue(pixel11.equals(pixel12));
    }

    try {
      pixel13 = new RGBPixel(44, 55, 66, 255);
      pixel14 = new RGBPixel(89, 145,214, 255);
    } catch (IllegalArgumentException e) {
      assertFalse(pixel13.equals(pixel14));
    }

    try {
      pixel15 = new RGBPixel(240, 230, 220, 255);
      pixel16 = new RGBPixel(89, 145,214, 255);
    } catch (IllegalArgumentException e) {
      assertFalse(pixel15.equals(pixel16));
    }
  }

  @Test
  public void testHashCode() {
    RGBPixel pixel11 = null;
    RGBPixel pixel12 = null;
    RGBPixel pixel13 = null;

    try {
      pixel11 = new RGBPixel(100, 0, 0, 255);
    } catch (IllegalArgumentException e) {
      assertEquals(100, pixel11.hashCode(), 0.0);
    }

    try {
      pixel12 = new RGBPixel(0, 100, 0, 255);
    } catch (IllegalArgumentException e) {
      assertEquals(25600, pixel12.hashCode(), 0.0);
    }

    try {
      pixel13 = new RGBPixel(0, 0, 1, 255);
    } catch (IllegalArgumentException e) {
      assertEquals(65536, pixel13.hashCode(), 0.0);
    }
  }
}
