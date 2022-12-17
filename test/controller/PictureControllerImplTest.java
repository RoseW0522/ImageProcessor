package controller;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import model.IPictureModel;
import model.PictureModel;
import view.PictureSwingView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for the PictureControllerImpl.
 * Uses a mock model to test that inputs are correctly passed.
 */
public class PictureControllerImplTest {

  @Test
  public void constructorTest() {
    try {
      new PictureControllerImpl(null, null, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Model must not be null.", e.getMessage());
    }

    try {
      new PictureControllerImpl(new PictureModel(), null, null);
    } catch (IllegalArgumentException e) {
      assertEquals("View must not be null.", e.getMessage());
    }

    try {
      new PictureControllerImpl(new PictureModel(), new PictureSwingView(), null);
    } catch (IllegalArgumentException e) {
      assertEquals("Readable must not be null.", e.getMessage());
    }

    try {
      new PictureControllerImpl(new PictureModel(), new PictureSwingView(),
              new StringReader(""));
    } catch (IllegalArgumentException e) {
      fail("Failed to construct the controller.");
    }
  }

  @Test
  public void saveCommandTest() throws IOException {
    Appendable log = new StringBuilder();
    IPictureModel mock = new ConfirmInputsPictureModel(log);

    String input = "save file1 file2";
    Readable rd = new StringReader(input);

    PictureController controller = new PictureControllerImpl(mock,
            new PictureSwingView(), rd);

    controller.editPhoto();

    assertEquals("Save command called. Given inputs: fileName: file1 picName: file2",
            log.toString());
  }

  @Test
  public void loadCommandTest() throws IOException {
    Appendable log = new StringBuilder();
    IPictureModel mock = new ConfirmInputsPictureModel(log);

    String input = "load file1 file2";
    Readable rd = new StringReader(input);

    PictureController controller = new PictureControllerImpl(mock,
            new PictureSwingView(), rd);

    controller.editPhoto();

    assertEquals("Load command called. Given inputs: fileName: file1 picName: file2",
            log.toString());
  }

  @Test
  public void brightenCommandTest() throws IOException {
    Appendable log = new StringBuilder();
    IPictureModel mock = new ConfirmInputsPictureModel(log);

    String input = "brighten 10 file1 file2";
    Readable rd = new StringReader(input);

    PictureController controller = new PictureControllerImpl(mock,
            new PictureSwingView(), rd);

    controller.editPhoto();

    assertEquals("Brighten command called. Given inputs: fileName: file1, file2, 10",
            log.toString());
  }

  @Test
  public void redGreyscaleCommandTest() throws IOException {
    Appendable log = new StringBuilder();
    IPictureModel mock = new ConfirmInputsPictureModel(log);

    String input = "red-component file1 file2";
    Readable rd = new StringReader(input);

    PictureController controller = new PictureControllerImpl(mock,
            new PictureSwingView(), rd);

    controller.editPhoto();

    assertEquals("Greyscale command called. Given inputs: fileName: file1, file2, Red",
            log.toString());
  }

  @Test
  public void greenGreyscaleCommandTest() throws IOException {
    Appendable log = new StringBuilder();
    IPictureModel mock = new ConfirmInputsPictureModel(log);

    String input = "green-component file1 file2";
    Readable rd = new StringReader(input);

    PictureController controller = new PictureControllerImpl(mock,
            new PictureSwingView(), rd);

    controller.editPhoto();

    assertEquals("Greyscale command called. Given inputs: fileName: file1, file2, Green",
            log.toString());
  }

  @Test
  public void blueGreyscaleCommandTest() throws IOException {
    Appendable log = new StringBuilder();
    IPictureModel mock = new ConfirmInputsPictureModel(log);

    String input = "blue-component file1 file2";
    Readable rd = new StringReader(input);

    PictureController controller = new PictureControllerImpl(mock,
            new PictureSwingView(), rd);

    controller.editPhoto();

    assertEquals("Greyscale command called. Given inputs: fileName: file1, file2, Blue",
            log.toString());
  }

  @Test
  public void valueGreyscaleCommandTest() throws IOException {
    Appendable log = new StringBuilder();
    IPictureModel mock = new ConfirmInputsPictureModel(log);

    String input = "value-component file1 file2";
    Readable rd = new StringReader(input);

    PictureController controller = new PictureControllerImpl(mock,
            new PictureSwingView(), rd);

    controller.editPhoto();

    assertEquals("Greyscale command called. Given inputs: fileName: file1, file2, Value",
            log.toString());
  }

  @Test
  public void intensityGreyscaleCommandTest() throws IOException {
    Appendable log = new StringBuilder();
    IPictureModel mock = new ConfirmInputsPictureModel(log);

    String input = "intensity-component file1 file2";
    Readable rd = new StringReader(input);

    PictureController controller = new PictureControllerImpl(mock,
            new PictureSwingView(), rd);

    controller.editPhoto();

    assertEquals("Greyscale command called. Given inputs: fileName: file1, file2, Intensity",
            log.toString());
  }

  @Test
  public void lumaGreyscaleCommandTest() throws IOException {
    Appendable log = new StringBuilder();
    IPictureModel mock = new ConfirmInputsPictureModel(log);

    String input = "luma-component file1 file2";
    Readable rd = new StringReader(input);

    PictureController controller = new PictureControllerImpl(mock,
            new PictureSwingView(), rd);

    controller.editPhoto();

    assertEquals("Greyscale command called. Given inputs: fileName: file1, file2, Luma",
            log.toString());
  }

  @Test
  public void verticalFlipCommandTest() throws IOException {
    Appendable log = new StringBuilder();
    IPictureModel mock = new ConfirmInputsPictureModel(log);

    String input = "vertical-flip file1 file2";
    Readable rd = new StringReader(input);

    PictureController controller = new PictureControllerImpl(mock,
            new PictureSwingView(), rd);

    controller.editPhoto();

    assertEquals("Flip vertical command called. Given inputs: fileName: file1 picName: file2",
            log.toString());
  }

  @Test
  public void horizontalFlipCommandTest() throws IOException {
    Appendable log = new StringBuilder();
    IPictureModel mock = new ConfirmInputsPictureModel(log);

    String input = "horizontal-flip file1 file2";
    Readable rd = new StringReader(input);

    PictureController controller = new PictureControllerImpl(mock,
            new PictureSwingView(), rd);

    controller.editPhoto();

    assertEquals("Flip horizontal command called. Given inputs: fileName: file1 picName: file2",
            log.toString());
  }

  @Test
  public void bothFlipCommandTest() throws IOException {
    Appendable log = new StringBuilder();
    IPictureModel mock = new ConfirmInputsPictureModel(log);

    String input = "double-flip file1 file2";
    Readable rd = new StringReader(input);

    PictureController controller = new PictureControllerImpl(mock,
            new PictureSwingView(), rd);

    controller.editPhoto();

    assertEquals("Flip both command called. Given inputs: fileName: file1 picName: file2",
            log.toString());
  }
}